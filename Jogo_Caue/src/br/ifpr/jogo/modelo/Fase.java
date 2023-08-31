package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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

import br.ifpr.jogo.modelo.servivos.Personagem;
import br.ifpr.jogo.modelo.servivos.Inimigos.Lobo;
import br.ifpr.jogo.modelo.tiros.SuperTiro;
import br.ifpr.jogo.modelo.tiros.Tiro;

public class Fase extends JPanel implements KeyListener, ActionListener, InterfaceTela {
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

    protected int larguraImagem, alturaImagem;

    private int pontuacao = 0;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Fase() {
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

                // VOLTA AS VARIAVEIS AO PADRAO
                this.contaTempoTiros = 0;
                this.podeAtirar = false;
            }
        
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

                // VOLTA AS VARIAVEIS AO PADRAO
                this.contaTempoSuperTiros = 0;
                this.podeSuperAtirar = false;
            }
        
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
            if (tiro.tiroVisivel()) {
                tiro.atualizar();
            } else {
                iteratorTiro.remove();
            }
        }

        // SO SPAWNA O SUPER TIRO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_SUPER_TIRO
        while (iteratorSuperTiro.hasNext()) {
            SuperTiro superTiro = iteratorSuperTiro.next();

            // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL, REMOVE OS SUPER TIROS
            if (superTiro.getVisivel()) {
                superTiro.atualizar();
            } else {
                iteratorSuperTiro.remove();
            }
        }
            

        // SO SPAWNA O INIMIGO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_INIMIGOS
        if (this.contaTempoLobos >= this.TEMPO_SPAWN_INIMIGOS) {
            Random random = new Random();
            int posicaoXInicio = -200;
            int posicaoYInicio = -200;
            int posicaoXAleatoria = random.nextInt(LARGURA_JANELA);
            int posicaoYAleatoria = random.nextInt(ALTURA_JANELA);  

            Lobo novoLoboCima = new Lobo(posicaoXAleatoria, posicaoYInicio, this.personagem);
            Lobo novoLoboEsquerda = new Lobo(posicaoXInicio, posicaoYAleatoria, this.personagem);
            novoLoboCima.carregar();
            novoLoboEsquerda.carregar();
            lobo.add(novoLoboCima);
            lobo.add(novoLoboEsquerda);
            
            this.contaTempoLobos = 0;
        }

        Iterator<Lobo> iteratorLobo = lobo.iterator();
        // ATUALIZA A POSICAO NA FASE OU REMOVE OS LOBOS
        while (iteratorLobo.hasNext()) {
            Lobo lobo = iteratorLobo.next();
            Rectangle formaLobo = lobo.getRectangle();
            
            // ENQUANTO O LOBO ESTIVER VISIVEL, ATUALIZA A MOVIMENTACAO
            if (lobo.getVisivel() == true) {
                lobo.atualizar();

                // REMOVE OS LOBOS AO COLIDIR COM O PERSONAGEM, TIRO E SUPERTIRO
                if (personagem.getRectangle().intersects(lobo.getRectangle())) {
                    lobo.setVisivel(false); 
                    personagem.setVida(personagem.getVida() - 1);
                }

                for(Tiro tiro : personagem.getTiros()){
                    Rectangle formaTiro = tiro.getRectangle();
                    if(formaTiro.intersects(formaLobo)){
                        lobo.setVisivel(false);
                        tiro.setVisivel(false);
                        this.pontuacao += 100;
                        System.out.println(this.pontuacao);
                    }
                }
                for(SuperTiro supertiro : personagem.getSuperTiros()){
                    Rectangle formaSuperTiro = supertiro.getRectangle();
                    if(formaSuperTiro.intersects(formaLobo)){
                        lobo.setVisivel(false);
                        supertiro.setVisivel(false);
                        this.pontuacao += 100;
                    }
                }
                
            } else {
                // SE O LOBO NAO ESTIVER VISIVEL ELE E REMOVIDO
                iteratorLobo.remove();
            }
        }

        // VAI PINTANDO AS IMAGENS DE ACORDO COM O DELAY DA FASE
        repaint();
    }


    // GETTERS E SETTERS
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

    public int getLARGURA_JANELA() {
        return LARGURA_JANELA;
    }

    public int getALTURA_JANELA() {
        return ALTURA_JANELA;
    }
}
