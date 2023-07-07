package br.ifpr.jogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SuperTiro {
    private int posicaoX;
    private int posicaoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 5;

    private boolean visivel;

    private int direcao;

    // CONSTRUTOR
    public SuperTiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        this.posicaoX = posicaoPersonagemX;
        this.posicaoY = posicaoPersonagemY;
        this.direcao = direcao;
        this.visivel = true;
        carregar();
    }

    // CARREGA A IMAGEM DOS SUPER TIROS
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\granada.png");
        this.imagem = carregador.getImage();

        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);

    }

    // MOVE A IMAGEM DO SUPER TIRO
    public void atualizar() {

        // MUDA A DIRECAO DO SUPER TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O PERSONAGEM
        if(direcao == Personagem.TECLA_W) {
            posicaoY -= VELOCIDADE;

        } else if(direcao == Personagem.TECLA_S) {
            posicaoY += VELOCIDADE;
            
        } else if(direcao == Personagem.TECLA_D) {
            posicaoX += VELOCIDADE;

        } else if(direcao == Personagem.TECLA_A) {
            posicaoX -= VELOCIDADE;
            
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

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }
}