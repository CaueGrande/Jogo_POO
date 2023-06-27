package br.ifpr.jogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int velocidade = 5;

    private boolean visivel;

    private String direcao;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, String direcao) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
        this.direcao = direcao;
        this.visivel = true;
    }

    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\tiro_w.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        if (direcao.equals("d")) {
            posicaoEmX += velocidade;
        } else if (direcao.equals("w")) {
            posicaoEmY -= velocidade;
        } else if (direcao.equals("a")) {
            posicaoEmX -= velocidade;
        } else if (direcao.equals("s")) {
            posicaoEmY += velocidade;
        }
    }

    // GETTERS E SETTERS
    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
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

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public String getdirecao() {
        return direcao;
    }

    public void setdirecao(String direcao) {
        this.direcao = direcao;
    }

    public boolean isVisivel() {
        if (this.posicaoEmX > 1285 || this.posicaoEmX < 0 || this.posicaoEmY > 1085 || this.posicaoEmY < 0) {
            this.visivel = false;
        } else {
            this.visivel = true;
        }
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

}
