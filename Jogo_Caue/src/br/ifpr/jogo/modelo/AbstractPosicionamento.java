package br.ifpr.jogo.modelo;

import java.awt.Image;

public abstract class AbstractPosicionamento {
    private int posicaoX;
    private int posicaoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;

    public abstract void atualizar();
    public abstract void carregar();
    
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
}
