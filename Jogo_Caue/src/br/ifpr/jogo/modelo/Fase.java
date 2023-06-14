package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.ifpr.jogo.modelo.Inimigos.Lobo;

public class Fase extends JPanel{
    private Image fundo;
    private Personagem personagem;
    private Lobo lobo;
    
    
    public Fase(){
        ImageIcon carregando = new ImageIcon("recursos\\fundo.png");
        
        this.fundo = carregando.getImage();

        this.personagem = new Personagem();
        this.personagem.Carregar();

        this.lobo = new Lobo();
        this.lobo.Carregar();
        

    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(this.fundo, 0,0,null);
        graficos.drawImage(this.personagem.getImagem(), this.personagem.getPosicaoX(), this.personagem.getPosicaoY(), null);
        graficos.drawImage(this.lobo.getImagem(), this.lobo.getPosicaoX(), this.lobo.getPosicaoY(), null);

        g.dispose();

    }
}
