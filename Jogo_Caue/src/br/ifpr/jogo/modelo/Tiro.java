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

    private int direcao;

    // CONSTRUTOR
    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, int direcao) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
        this.direcao = direcao;
        this.visivel = true;
    }

    // CARREGA A IMAGEM INICIAL DOS TIROS
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\tiro_w.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    //// MOVE A IMAGEM DO TIRO
    public void atualizar() {

        // MUDA A DIRECAO DO TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O
        // PERSONAGEM
        if (direcao == Personagem.D_RIGTH) {
            posicaoEmX += velocidade;
        } else if (direcao == Personagem.W_TOP) {
            posicaoEmY -= velocidade;
        } else if (direcao == Personagem.A_LEFT) {
            posicaoEmX -= velocidade;
        } else if (direcao == Personagem.S_BOTTOM) {
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

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
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
