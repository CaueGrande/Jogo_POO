package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.modelo.Inimigos.Lobo;

public class Fase extends JPanel implements KeyListener, ActionListener{
    private Image fundo;
    private Personagem personagem;
    private Lobo lobo;
    private Timer timer;

    private static final int DELAY = 5;
    private static final int VELOCIDADE_DESLOCAMENTO = 3;
    
    
    public Fase(){
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("recursos\\fundo.png");
        
        this.fundo = carregando.getImage();

        this.addKeyListener(this);

        personagem = new Personagem(VELOCIDADE_DESLOCAMENTO);
        personagem.carregar();

        this.lobo = new Lobo();
        this.lobo.Carregar();

        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(this.fundo, 0,0,null);
        graficos.drawImage(this.personagem.getImagem(), this.personagem.getPosicaoX(), this.personagem.getPosicaoY(), null);
        graficos.drawImage(this.lobo.getImagem(), this.lobo.getPosicaoX(), this.lobo.getPosicaoY(), null);

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.personagem.atualizar();
        repaint();
    }
}
