package br.ifpr.jogo.modelo.Inimigos;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class Lobo {
    private int posicaoX;
    private int posicaoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;

    private static ArrayList<Lobo> lobos;

    private static final int VELOCIDADE = 3;
    private static final int QTDE_INIMIGOS = 10;

    private boolean visibilidade;

    private Personagem personagem;

    // CONSTRUTOR
    public Lobo(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.visibilidade = true;
        this.personagem = new Personagem();
    }

    // CARREGA A IMAGEM INICIAL DO PERSONAGEM
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\lobo_s.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // MUDA A POSICAO DOS LOBOS
    public void atualizar() {
        this.posicaoY += VELOCIDADE;
        // this.posicaoX += VELOCIDADE;
        if (this.posicaoY > 1080) {
            visibilidade = false;
        }
    }

    // GETTERS E SETTERS
    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public ArrayList<Lobo> getLobos() {
        return lobos;
    }

    public static void setLobos(ArrayList<Lobo> lobos) {
        Lobo.lobos = lobos;
    }

    public static int getVelocidade() {
        return VELOCIDADE;
    }

    public static int getQtdeInimigos() {
        return QTDE_INIMIGOS;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

}
