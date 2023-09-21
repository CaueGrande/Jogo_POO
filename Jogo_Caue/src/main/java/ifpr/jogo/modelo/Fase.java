package main.java.ifpr.jogo.modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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

import main.java.ifpr.jogo.modelo.paisagem.Animal;
import main.java.ifpr.jogo.modelo.servivos.Personagem;
import main.java.ifpr.jogo.modelo.servivos.Inimigos.Lobo;
import main.java.ifpr.jogo.modelo.tiros.SuperTiro;
import main.java.ifpr.jogo.modelo.tiros.Tiro;
import main.java.ifpr.jogo.util.AbstractConstantes;

public class Fase extends JPanel implements KeyListener, ActionListener{
    private Image fundo;
    private Image fimDeJogo;
    private Personagem personagem;
    private List<Lobo> lobos;
    private List<Animal> abelhas;
    private Timer timer;

    private static final int DELAY = 2;

    private final int TEMPO_SPAWN_ANIMAIS = 600;
    private final int TEMPO_SPAWN_INIMIGOS = 220;
    private final int TEMPO_SPAWN_TIROS = 20;
    private final int TEMPO_SPAWN_SUPER_TIROS = 150;

    private int contaTempoAnimais;
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


    public Fase() {
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        // CARREGA A IMAGEM INICIAL DA FASE
        ImageIcon carregarFundo = new ImageIcon(getClass().getResource("/fundo.png"));
        this.fundo = carregarFundo.getImage();

        this.fundo = this.fundo.getScaledInstance(AbstractConstantes.LARGURA_JANELA, AbstractConstantes.ALTURA_JANELA, Image.SCALE_FAST);
        this.alturaImagem = this.fundo.getHeight(null);
        this.larguraImagem = this.fundo.getWidth(null);

        // CRIA O PERSONAGEM NA FASE
        this.personagem = new Personagem();
        this.personagem.carregar();

        // LE AS TECLAS APERTADAS
        this.addKeyListener(this);

        // ADICIONA O DELAY NA FASE
        this.timer = new Timer(DELAY, this);
        this.timer.start();

        // CRIA OS (INIMIGOS)LOBOS E (ANIMAIS INTANGIVEIS)ABELHAS NA FASE
        this.lobos = new ArrayList<>();
        this.abelhas = new ArrayList<>();

        ImageIcon carregarFimJogo = new ImageIcon(getClass().getResource("/gameover.png"));
        this.fimDeJogo = carregarFimJogo.getImage();
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
        for (Lobo lobo : this.lobos) {
            graficos.drawImage(lobo.getImagem(), lobo.getPosicaoX(), lobo.getPosicaoY(), null);
        }

        // IMAGEM DAS ABELHAS 
        for (Animal abelha : this.abelhas) {
            graficos.drawImage(abelha.getImagem(), abelha.getPosicaoX(), abelha.getPosicaoY(), null);
        }

        // DESENHA A STRING "PONTOS:" NA TELA
        this.desenhaPontuacao(graficos);
        // DESENHA A STRING "VIDAS:" NA TELA
        this.desenhaVida(graficos);

        if(personagem.getVida() == 0){
            int x = (AbstractConstantes.LARGURA_JANELA/2) - 250; 
            int y = (AbstractConstantes.ALTURA_JANELA/2) - 400; 
            // IMAGEM DE GAMEOVER
            graficos.drawImage(this.fimDeJogo, x , y, null);
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
        if(personagem.getPosicaoY() <= 20){
            Fase.podeMover_W = false;
            this.personagem.parar(e);
            personagem.setPosicaoY(20);

        }else if (personagem.getPosicaoX() > 20){
            Fase.podeMover_W = true;
        }

        if(personagem.getPosicaoY() >= AbstractConstantes.ALTURA_JANELA - 20){
            Fase.podeMover_S = false;
            this.personagem.parar(e);

        }else if (personagem.getPosicaoX() < AbstractConstantes.ALTURA_JANELA - 20){
            Fase.podeMover_S = true;
        }

        if(personagem.getPosicaoX() >= AbstractConstantes.LARGURA_JANELA - 20){
            Fase.podeMover_D = false;
            this.personagem.parar(e);

        }else if (personagem.getPosicaoX() < AbstractConstantes.LARGURA_JANELA - 20){
            Fase.podeMover_D = true;
        }

        if(personagem.getPosicaoX() <= 20){
            Fase.podeMover_A = false;
            this.personagem.parar(e);

        }else if (personagem.getPosicaoX() > 20){
            Fase.podeMover_A = true;
        }
        
        // MOVE O PERSONAGEM
        if(personagem.getVida() > 0){
            this.personagem.mover(e);
        }
        
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
        Random random = new Random();

        if(personagem.getVida() > 0){
            this.personagem.atualizar();
        }

        // CONTAGENS DE TEMPO
        if(this.contaTempoAnimais < TEMPO_SPAWN_ANIMAIS){
            this.contaTempoAnimais++;
        }
        if(this.contaTempoLobos < TEMPO_SPAWN_INIMIGOS){
            this.contaTempoLobos++;
        }
        if(this.contaTempoTiros < TEMPO_SPAWN_TIROS){
            this.contaTempoTiros++;
        }
        if(this.contaTempoSuperTiros < TEMPO_SPAWN_SUPER_TIROS){
            this.contaTempoSuperTiros++;
        }

        ArrayList<Tiro> tiros = personagem.getTiros();
        Iterator<Tiro> iteratorTiro = tiros.iterator();
        
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        Iterator<SuperTiro> iteratorSuperTiro = superTiros.iterator();

        // PASSA PELOS TIROS QUE ESTIVEREM NA LISTA
        while (iteratorTiro.hasNext()) {
            Tiro tiro = iteratorTiro.next();

            if(personagem.getVida() > 0){
                // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL, REMOVE OS TIROS
                if (tiro.getVisivel() == true) {
                    tiro.atualizar();
                } else {
                    iteratorTiro.remove();
                }
            }
        }

        // SO SPAWNA O SUPER TIRO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_SUPER_TIROS
        while (iteratorSuperTiro.hasNext()) {
            SuperTiro superTiro = iteratorSuperTiro.next();

            if(personagem.getVida() > 0){
                // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL, REMOVE OS SUPER TIROS
                if (superTiro.getVisivel() == true) {
                    superTiro.atualizar();
                } else {
                    iteratorSuperTiro.remove();
                }
            }
        }

        // SO SPAWNA O ANIMAL APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_ANIMAIS
        if(this.contaTempoAnimais >= TEMPO_SPAWN_ANIMAIS && personagem.getVida() > 0){
            int posicaoXFim = AbstractConstantes.LARGURA_JANELA + 200;
            int posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA); 

            Animal novaAbelha = new Animal(posicaoXFim, posicaoYAleatoria);
            novaAbelha.carregar();
            abelhas.add(novaAbelha);

            this.contaTempoAnimais = 0;
        }

        Iterator<Animal> iteratorAbelha = abelhas.iterator();
        // ATUALIZA A POSICAO NA FASE OU REMOVE OS ANIMAIS
        while (iteratorAbelha.hasNext()) {
            Animal abelha = iteratorAbelha.next();
            
            // ENQUANTO O ANIMAL ESTIVER VISIVEL, ATUALIZA A MOVIMENTACAO
            if (abelha.getVisivel() == true && personagem.getVida() > 0) {
                abelha.atualizar();

                if(abelha.getPosicaoX() <= -50){
                    abelha.setVisivel(false);
                }
            } else{
                iteratorAbelha.remove();
            }
        }

        
        // SO SPAWNA O INIMIGO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_INIMIGOS
        if (this.contaTempoLobos >= this.TEMPO_SPAWN_INIMIGOS && personagem.getVida() > 0) {
            int posicaoXInicio = -200;
            int posicaoYInicio = -200;
            int posicaoXFim = (int) AbstractConstantes.LARGURA_JANELA;
            int posicaoYFim = (int) AbstractConstantes.ALTURA_JANELA;
            int posicaoXAleatoria = random.nextInt(AbstractConstantes.LARGURA_JANELA);
            int posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA);

            Lobo novoLoboCima = new Lobo(posicaoXAleatoria, posicaoYInicio, this.personagem);
            Lobo novoLoboBaixo = new Lobo(posicaoXAleatoria, posicaoYFim, this.personagem);
            Lobo novoLoboEsquerda = new Lobo(posicaoXInicio, posicaoYAleatoria, this.personagem);
            Lobo novoLoboDireita = new Lobo(posicaoXFim, posicaoYAleatoria, this.personagem);
            novoLoboCima.carregar();
            novoLoboBaixo.carregar();
            novoLoboEsquerda.carregar();
            novoLoboDireita.carregar();
            this.lobos.add(novoLoboCima);
            this.lobos.add(novoLoboBaixo);
            this.lobos.add(novoLoboEsquerda);
            this.lobos.add(novoLoboDireita);
            
            this.contaTempoLobos = 0;
        }

        Iterator<Lobo> iteratorLobo = this.lobos.iterator();
        // ATUALIZA A POSICAO NA FASE OU REMOVE OS LOBOS
        while (iteratorLobo.hasNext()) {
            Lobo lobo = iteratorLobo.next();
            Rectangle formaLobo = lobo.getRectangle();
            
            // ENQUANTO O LOBO ESTIVER VISIVEL, ATUALIZA A MOVIMENTACAO
            if (lobo.getVisivel() == true && personagem.getVida() > 0) {
                lobo.atualizar();

                // REMOVE OS LOBOS AO COLIDIR COM O PERSONAGEM
                if (personagem.getRectangle().intersects(lobo.getRectangle())) {
                    lobo.setVisivel(false); 
                    personagem.setVida(personagem.getVida() - 1);
                }

                // REMOVE LOBOS E TIROS AO COLIDIR COM TIROS E SUPER TIROS
                for(Tiro tiro : personagem.getTiros()){
                    Rectangle formaTiro = tiro.getRectangle();
                    if(formaTiro.intersects(formaLobo)){
                        lobo.setVisivel(false);
                        tiro.setVisivel(false);
                        personagem.setPontuacao(personagem.getPontuacao() + Lobo.getPontuacaoPorLobo());
                    }
                }
                for(SuperTiro supertiro : personagem.getSuperTiros()){
                    Rectangle formaSuperTiro = supertiro.getRectangle();
                    if(formaSuperTiro.intersects(formaLobo)){
                        lobo.setVisivel(false);
                        supertiro.setVisivel(false);
                        personagem.setPontuacao(personagem.getPontuacao() + Lobo.getPontuacaoPorLobo());
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

    // PONTUACAO DO PERSONAGEM
    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
        Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22);
        FontMetrics metrics = graficos.getFontMetrics(fonte);
        int larguraTexto = metrics.stringWidth(textoPontuacao);
        int alturaTexto = metrics.getHeight();

        // DESENHA O FUNDO BRANCO DO TEXTO
        graficos.setColor(Color.WHITE);
        graficos.fillRect(20, 5, larguraTexto + 10, alturaTexto);

        // DESENHA O TEXTO "PONTOS:"
        graficos.setFont(fonte);
        graficos.setColor(Color.BLACK);
        graficos.drawString(textoPontuacao, 20, 25);
    }
    
    // VIDA DO PERSONAGEM
    public void desenhaVida(Graphics2D graficos) {
        String textoVida = "VIDAS: " + personagem.getVida();
        Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22);
        FontMetrics metrics = graficos.getFontMetrics(fonte);
        int larguraTexto = metrics.stringWidth(textoVida);
        int alturaTexto = metrics.getHeight();

        // DESENHA O FUNDO BRANCO DO TEXTO
        graficos.setColor(Color.WHITE);
        graficos.fillRect( (AbstractConstantes.LARGURA_JANELA - 120), 5, larguraTexto + 10, alturaTexto);

        // DESENHA O TEXTO "VIDA:"
        graficos.setFont(fonte);
        graficos.setColor(Color.BLACK);
        graficos.drawString(textoVida, (AbstractConstantes.LARGURA_JANELA - 120), 25);
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
        return lobos;
    }

    public void setLobo(List<Lobo> lobos) {
        this.lobos = lobos;
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

    public boolean getPodeAtirar() {
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
}