package br.ifpr.jogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int Velocidade_Positiva = 5;
    private static int Velocidade_Negativa = -5;


    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
    this.posicaoEmX = posicaoPersonagemEmX;
    this.posicaoEmY = posicaoPersonagemEmY;
    }

    public void carregar(){

        ImageIcon carregador = new ImageIcon("recursos\\tiro.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX + Velocidade_Negativa;
        this.posicaoEmY = this.posicaoEmY + Velocidade_Negativa;
}


    //GETTERS E SETTERS
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

    public static int getVelocidade_Positiva() {
        return Velocidade_Positiva;
    }

    public static void setVelocidade_Positiva(int velocidade_Positiva) {
        Velocidade_Positiva = velocidade_Positiva;
    }

    public static int getVelocidade_Negativa() {
        return Velocidade_Negativa;
    }

    public static void setVelocidade_Negativa(int velocidade_Negativa) {
        Velocidade_Negativa = velocidade_Negativa;
    }

}
