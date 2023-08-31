package br.ifpr.jogo.modelo;

import java.awt.Image;

public abstract class AbstractDeslocamento {
    private int posicaoX;
    private int posicaoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int VELOCIDADE;
    private boolean visivel;
    
    // CARREGA OS DADOS NA FASE
    public abstract void carregar();
    // ALTERA DADOS
    public abstract void atualizar();
    
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
    public int getVELOCIDADE() {
        return VELOCIDADE;
    }
    public void setVELOCIDADE(int VELOCIDADE) {
        this.VELOCIDADE = VELOCIDADE;
    }
    public boolean getVisivel() {
        return visivel;
    }
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
}
