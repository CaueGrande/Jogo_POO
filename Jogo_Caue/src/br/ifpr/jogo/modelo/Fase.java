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
    private Lobo lobo;
    private Timer timer;

    private static final int DELAY = 5;

    // private static final int LARGURA_JANELA = 1285;
    // private static final int ALTURA_JANELA = 1085;

    public Fase() {
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        // IMAGEM DA FASE
        ImageIcon carregando = new ImageIcon("recursos\\fundo.png");
        this.fundo = carregando.getImage();

        // CRIA O PERSONAGEM NA FASE
        this.personagem = new Personagem();
        this.personagem.carregar();

        this.lobo = new Lobo();
        this.lobo.carregar();

        this.addKeyListener(this);

        // ADICIONA O DELAY NA FASE
        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    // DESENHA AS IMAGENS NA TELA
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        // VARIAVEL LISTA DE TIROS

        ArrayList<Tiro> tiros = personagem.getTiros();
        graficos.drawImage(this.fundo, 0, 0, null);

        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
        }

        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoX(), personagem.getPosicaoY(), this);

        graficos.drawImage(lobo.getImagem(), lobo.getPosicaoX(), lobo.getPosicaoY(), null);

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // ACAO AO APERTAR TECLA
    @Override
    public void keyPressed(KeyEvent e) {
        this.personagem.atirar();
        this.personagem.mover(e);

    }

    // ACAO AO SOLTAR TECLA
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

            // MOVE E REMOVE OS TIROS
            if (tiro.isVisivel()) {
                tiro.atualizar();
            } else {
                iteratorTiro.remove();
            }
        }

        repaint();
    }
}
