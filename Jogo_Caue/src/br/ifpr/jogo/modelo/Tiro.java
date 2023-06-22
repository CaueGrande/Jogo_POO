package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Tiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 2;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
    this.posicaoEmX = posicaoPersonagemEmX;
    this.posicaoEmY = posicaoPersonagemEmY;
    }

    public void Carregar(){

        ImageIcon carregador = new ImageIcon("recursos\\tiro.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX + VELOCIDADE;
        this.posicaoEmY = this.posicaoEmY + VELOCIDADE;
}

}
