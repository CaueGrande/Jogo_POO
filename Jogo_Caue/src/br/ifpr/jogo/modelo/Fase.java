package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.modelo.Inimigos.Lobo;

public class Fase extends JPanel implements KeyListener, ActionListener {
    private Image fundo;
    private Personagem personagem;
    private List<Lobo> lobo;
    private Timer timer;

    private static final int DELAY = 5;

    private static final int LARGURA_JANELA = 1285;
    private static final int ALTURA_JANELA = 1085;

    // CONSTRUTOR
    public Fase() {
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        // CARREGA A IMAGEM INICIAL DA FASE
        ImageIcon carregando = new ImageIcon("recursos\\fundo.png");
        this.fundo = carregando.getImage();

        // CRIA O PERSONAGEM NA FASE
        this.personagem = new Personagem();
        this.personagem.carregar();

        this.addKeyListener(this);

        // ADICIONA O DELAY NA FASE
        this.timer = new Timer(DELAY, this);
        this.timer.start();

        // CRIA OS LOBOS NA FASE
        inicializaLobo();
    }

    public void inicializaLobo() {
        lobo = new ArrayList<Lobo>();
        int alturaInimigo = 400;
        
        // TIMER PARA SPAWNAR OS LOBOS
        Timer lobotimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (1280 - alturaInimigo));
                lobo.add(new Lobo(posicaoEmX, posicaoEmY));
            }
        });
        lobotimer.setRepeats(true);
        lobotimer.start();
    }// FIM DO MÉTODO

    // DESENHA AS IMAGENS NA TELA
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        ArrayList<Tiro> tiros = personagem.getTiros();

        // IMAGEM DE FUNDO
        graficos.drawImage(this.fundo, 0, 0, null);

        // IMAGEM DOS TIROS
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
        }

        // IMAGEM DO PERSONAGEM
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoX(), personagem.getPosicaoY(), this);

        // IMAGEM DOS LOBOS
        for (int i = 0; i < lobo.size(); i++) {
            Lobo lobo_inimigo = lobo.get(i);
            lobo_inimigo.carregar();
            graficos.drawImage(lobo_inimigo.getImagem(), lobo_inimigo.getPosicaoX(), lobo_inimigo.getPosicaoY(), null);
        }

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // ACOES AO APERTAR TECLA
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            this.personagem.atirar();
        } else {
            this.personagem.mover(e);
        } 
    }

    // ACOES AO SOLTAR TECLA
    @Override
    public void keyReleased(KeyEvent e) {
        this.personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.personagem.atualizar();

        ArrayList<Tiro> tiros = personagem.getTiros();
        Iterator<Tiro> iteratorTiro = tiros.iterator();

        // PASSA PELOS TIROS QUE ESTIVEREM NA LISTA
        while (iteratorTiro.hasNext()) {
            Tiro tiro = iteratorTiro.next();

            // ATUALIZA A POSICAO OU REMOVE OS TIROS
            if (tiro.isVisivel()) {
                tiro.atualizar();
            } else {
                iteratorTiro.remove();
            }
        }

        // ATUALIZA A POSICAO OU REMOVE OS LOBOS
        Iterator<Lobo> iteratorLobo = lobo.iterator();
        while (iteratorLobo.hasNext()) {
            Lobo lobo_inimigo = iteratorLobo.next();
            if (lobo_inimigo.isVisibilidade()) {
                lobo_inimigo.atualizar();
            } else {
                iteratorLobo.remove();
            }
        } 

        repaint();
    }
}
