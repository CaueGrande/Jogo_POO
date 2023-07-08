package br.ifpr.jogo.modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.modelo.Inimigos.Lobo;

public class Fase extends JPanel implements KeyListener, ActionListener {
    private Image fundo;
    private Personagem personagem;
    private List<Lobo> lobo;
    private Timer timer;

    private static final int DELAY = 2;

    private final int TEMPO_SPAWN_INIMIGOS = 100;
    private final int TEMPO_SPAWN_TIROS = 30;
    private final int TEMPO_SPAWN_SUPER_TIROS = 200;

    private int contaTempoLobos;
    private int contaTempoTiros;
    private int contaTempoSuperTiros;

    private boolean podeAtirar = true;
    private boolean podeSuperAtirar = true;
    public static boolean podeMover_W = true;
    public static boolean podeMover_S = true;
    public static boolean podeMover_D = true;
    public static boolean podeMover_A = true;

    public Dimension tamanhoTela;
    public final int LARGURA_JANELA;
    public final int ALTURA_JANELA;
    protected int larguraImagem, alturaImagem;

    // CONSTRUTOR
    public Fase() {

        // AJUSTA O TAMANHO DA JANELA DO JOGO DE ACORDO COM O MONITOR
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        LARGURA_JANELA = (int) tamanhoTela.getWidth();
        ALTURA_JANELA = (int) tamanhoTela.getHeight();

        this.setFocusable(true);
        this.setDoubleBuffered(true);

        // CARREGA A IMAGEM INICIAL DA FASE
        ImageIcon carregando = new ImageIcon("recursos\\fundo.png");
        this.fundo = carregando.getImage();

        this.fundo = this.fundo.getScaledInstance(
            this.getLARGURA_JANELA(), this.getALTURA_JANELA(), Image.SCALE_FAST
            );
        this.alturaImagem = this.fundo.getWidth(null);
        this.larguraImagem = this.fundo.getHeight(null);

        // CRIA O PERSONAGEM NA FASE
        this.personagem = new Personagem();
        this.personagem.carregar();

        // LE AS TECLAS APERTADAS
        this.addKeyListener(this);

        // ADICIONA O DELAY NA FASE
        this.timer = new Timer(DELAY, this);
        this.timer.start();

        // CRIA OS LOBOS NA FASE
        lobo = new ArrayList<>();
    }

    // DESENHA AS IMAGENS NA TELA
    public void paint(Graphics graphics) {
        Graphics2D graficos = (Graphics2D) graphics;

        ArrayList<Tiro> tiros = personagem.getTiros();
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();

        // IMAGEM DE FUNDO
        graficos.drawImage(this.fundo, 0, 0, null);

        // IMAGEM DOS TIROS
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoX(), tiro.getPosicaoY(), null);
        }

        // IMAGEM DO SUPER TIRO
        for (int J = 0; J < superTiros.size(); J++) {
            SuperTiro superTiro = superTiros.get(J);
            superTiro.carregar();
            graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoX(), superTiro.getPosicaoY(), null);
        }

        // IMAGEM DO PERSONAGEM
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoX(), personagem.getPosicaoY(), this);

        // IMAGEM DOS LOBOS
        for (Lobo lobo : this.lobo) {
            graficos.drawImage(lobo.getImagem(), lobo.getPosicaoX(), lobo.getPosicaoY(), null);
        }

        graphics.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // ACOES AO APERTAR TECLA
    @Override
    public void keyPressed(KeyEvent e) {
        // TEMPORIZADORES EM MILISEGUNDOS 
        this.contaTempoTiros++;
        this.contaTempoSuperTiros++;

        // SE O TEMPORIZADOR CHEGAR NO TEMPO DEFINIDO, E DADO PERMISSAO PARA ATIRAR
        if (this.contaTempoTiros >= this.TEMPO_SPAWN_TIROS) {
            this.podeAtirar = true;

        }
        // SE TIVER PERMISSAO, ATIRA
        if(this.podeAtirar == true){

            // ATIRA COM O ESPACO
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                this.personagem.atirar();
            }

            // VOLTA AS VARIAVEIS AO PADRAO
            this.contaTempoTiros = 0;
            this.podeAtirar = false;
        
        }
    
        // SE O TEMPORIZADOR CHEGAR NO TEMPO DEFINIDO, E DADO PERMISSAO PARA SUPER ATIRAR
        if (this.contaTempoSuperTiros >= this.TEMPO_SPAWN_SUPER_TIROS) {
            this.podeSuperAtirar = true;

        }

        // SE TIVER PERMISSAO, SUPER ATIRA
        if(this.podeSuperAtirar == true){

            // DA O SUPER TIRO COM A TECLA Q
            if (e.getKeyCode() == KeyEvent.VK_Q){
                this.personagem.superAtirar();
            }

            // VOLTA AS VARIAVEIS AO PADRAO
            this.contaTempoSuperTiros = 0;
            this.podeSuperAtirar = false;
        
        }
        
        // VERIFICA SE O PERSONAGEM NAO ESTA SAINDO DA JANELA
        if(personagem.getPosicaoY() < 30){
            Fase.podeMover_W = false;

        }
        if(personagem.getPosicaoY() > 1080){
            Fase.podeMover_S = false;

        }
        if(personagem.getPosicaoX() > 1150){
            Fase.podeMover_D = false;

        }
        if(personagem.getPosicaoX() < 10){
            Fase.podeMover_A = false;

        }
        
        // PARA O PERSONAGEM CASO ESTEJA SAINDO DA JANELA
        if(Fase.podeMover_W == false || Fase.podeMover_S == false ||  Fase.podeMover_A == false || Fase.podeMover_D == false){
            this.personagem.parar(e);

        } 
        if(podeMover_D == false){
            this.personagem.parar(e);
        }
        // MOVE O PERSONAGEM
        this.personagem.mover(e);
        
    }

    // ACOES AO SOLTAR TECLA
    @Override
    public void keyReleased(KeyEvent e) {
        // PARA O PERSONAGEM
        this.personagem.parar(e);
    }

    // ATIVA AS ACOES
    @Override
    public void actionPerformed(ActionEvent e) {

        this.personagem.atualizar();

        // CONTAGENS DE TEMPO
        this.contaTempoLobos++;
        this.contaTempoTiros++;
        this.contaTempoSuperTiros++;

        ArrayList<Tiro> tiros = personagem.getTiros();
        Iterator<Tiro> iteratorTiro = tiros.iterator();
        
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        Iterator<SuperTiro> iteratorSuperTiro = superTiros.iterator();

        // PASSA PELOS TIROS QUE ESTIVEREM NA LISTA
        while (iteratorTiro.hasNext()) {
            Tiro tiro = iteratorTiro.next();

            // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL, REMOVE OS TIROS
            if (tiro.isVisivel()) {
                tiro.atualizar();
            } else {
                iteratorTiro.remove();
            }
        }

        // SO SPAWNA O SUPER TIRO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_SUPER_TIRO
        while (iteratorSuperTiro.hasNext()) {
            SuperTiro superTiro = iteratorSuperTiro.next();

            // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL, REMOVE OS SUPER TIROS
            if (superTiro.isVisivel()) {
                superTiro.atualizar();
            } else {
                iteratorSuperTiro.remove();
            }
        }
            

        // SO SPAWNA O INIMIGO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_INIMIGOS
        if (this.contaTempoLobos >= this.TEMPO_SPAWN_INIMIGOS) {
            Random random = new Random();
            int posicaoX = random.nextInt(1280);
            int posicaoY = random.nextInt(1080);  

            Lobo novolobo = new Lobo(posicaoX, posicaoY, this.personagem);
            novolobo.carregar();
            lobo.add(novolobo);
            
            this.contaTempoLobos = 0;
        }

        // ATUALIZA A POSICAO NA FASE OU REMOVE OS LOBOS
        Iterator<Lobo> iteratorLobo = lobo.iterator();
        while (iteratorLobo.hasNext()) {
            // ENQUANTO O LOBO ESTIVER NA TELA, ATUALIZA A MOVIMENTACAO
            Lobo lobo = iteratorLobo.next();
            if (lobo.isVisibilidade() == true) {
                lobo.atualizar();

                // REMOVE OS LOBOS AO COLIDIR COM O PERSONAGEM
                if (personagem.getRectangle().intersects(lobo.getRectangle())) {
                    iteratorLobo.remove(); // Remover o inimigo da lista
                    lobo.setVisibilidade(false); // Marcar o inimigo como invisível
                }
                
            } else {
                // SE O LOBO NAO ESTIVER NA TELA ELE E REMOVIDO
                iteratorLobo.remove();
            }
        }

        // VAI PINTANDO AS IMAGENS DE ACORDO COM O DELAY DA FASE
        repaint();
    }

    public Image getFundo() {
        return fundo;
    }

    public void setFundo(Image fundo) {
        this.fundo = fundo;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public List<Lobo> getLobo() {
        return lobo;
    }

    public void setLobo(List<Lobo> lobo) {
        this.lobo = lobo;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public static int getDelay() {
        return DELAY;
    }

    public int getTEMPO_SPAWN_INIMIGOS() {
        return TEMPO_SPAWN_INIMIGOS;
    }

    public int getTEMPO_SPAWN_TIROS() {
        return TEMPO_SPAWN_TIROS;
    }

    public int getTEMPO_SPAWN_SUPER_TIROS() {
        return TEMPO_SPAWN_SUPER_TIROS;
    }

    public int getContaTempoLobos() {
        return contaTempoLobos;
    }

    public void setContaTempoLobos(int contaTempoLobos) {
        this.contaTempoLobos = contaTempoLobos;
    }

    public int getContaTempoTiros() {
        return contaTempoTiros;
    }

    public void setContaTempoTiros(int contaTempoTiros) {
        this.contaTempoTiros = contaTempoTiros;
    }

    public int getContaTempoSuperTiros() {
        return contaTempoSuperTiros;
    }

    public void setContaTempoSuperTiros(int contaTempoSuperTiros) {
        this.contaTempoSuperTiros = contaTempoSuperTiros;
    }

    public boolean isPodeAtirar() {
        return podeAtirar;
    }

    public void setPodeAtirar(boolean podeAtirar) {
        this.podeAtirar = podeAtirar;
    }

    public boolean isPodeSuperAtirar() {
        return podeSuperAtirar;
    }

    public void setPodeSuperAtirar(boolean podeSuperAtirar) {
        this.podeSuperAtirar = podeSuperAtirar;
    }

    public static boolean isPodeMover_W() {
        return podeMover_W;
    }

    public static void setPodeMover_W(boolean podeMover_W) {
        Fase.podeMover_W = podeMover_W;
    }

    public static boolean isPodeMover_S() {
        return podeMover_S;
    }

    public static void setPodeMover_S(boolean podeMover_S) {
        Fase.podeMover_S = podeMover_S;
    }

    public static boolean isPodeMover_D() {
        return podeMover_D;
    }

    public static void setPodeMover_D(boolean podeMover_D) {
        Fase.podeMover_D = podeMover_D;
    }

    public static boolean isPodeMover_A() {
        return podeMover_A;
    }

    public static void setPodeMover_A(boolean podeMover_A) {
        Fase.podeMover_A = podeMover_A;
    }

    public Dimension getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(Dimension tamanhoTela) {
        this.tamanhoTela = tamanhoTela;
    }

    public int getLARGURA_JANELA() {
        return LARGURA_JANELA;
    }

    public int getALTURA_JANELA() {
        return ALTURA_JANELA;
    }
}
