package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

    private static final int LARGURA_DA_JANELA = 1285;
    private static final int ALTURA_DA_JANELA = 1085;
    
    
    public Fase(){
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("recursos\\fundo.png");
        this.fundo = carregando.getImage();

        this.addKeyListener(this);

        this.personagem = new Personagem(VELOCIDADE_DESLOCAMENTO);
        this.personagem.carregar();

        this.lobo = new Lobo();
        this.lobo.carregar();


        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;

        ArrayList<Tiro> tiros = personagem.getTiros();
        
        graficos.drawImage(this.fundo, 0,0,null);
        graficos.drawImage(this.personagem.getImagem(), this.personagem.getPosicaoX(), this.personagem.getPosicaoY(), null);
        graficos.drawImage(this.lobo.getImagem(), this.lobo.getPosicaoX(), this.lobo.getPosicaoY(), null);

        for (Tiro tiro : tiros) {
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
    }

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.personagem.atualizar();

        ArrayList<Tiro> tiros = personagem.getTiros();

        for (int contador = 0; contador < tiros.size(); contador++) {
            if (tiros.get(contador).getPosicaoEmX() > LARGURA_DA_JANELA)
                tiros.remove(contador);
            else
                tiros.get(contador).atualizar();
        }

        repaint();
    }
}
