package br.ifpr.jogo.modelo;

import java.awt.Image;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoX;
    private int posicaoY;
    private int deslocamentoX;
    private int deslocamentoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int velocidadeDeDeslocamento;

    private static final int POSICAO_INICIAL_X = 575;
    private static final int POSICAO_INICIAL_Y = 850;

    public Personagem(int velocidadeDeDeslocamento){
        this.posicaoX = POSICAO_INICIAL_X;
        this.posicaoY = POSICAO_INICIAL_Y;
        this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;
    }

    public void Carregar(){
        ImageIcon carregador = new ImageIcon("recursos\\personagem.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar(){
        this.posicaoX = this.posicaoX + this.deslocamentoX;
        this.posicaoY = this.posicaoY + this.deslocamentoY;
    }

    public void mover(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        
        switch(codigo){

            case KeyEvent.VK_UP:
                this.deslocamentoY = - this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_W:
                this.deslocamentoY = - this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoY = this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_S:
                this.deslocamentoY = this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoX = this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_D:
                this.deslocamentoX = this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoX = - this.velocidadeDeDeslocamento;
                break;
            case KeyEvent.VK_A:
                this.deslocamentoX = - this.velocidadeDeDeslocamento;
                break;
            default:
                break;
        }
    }

    public void parar(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        switch(codigo){

            case KeyEvent.VK_UP:
                this.deslocamentoY = 0;
                break;
            case KeyEvent.VK_W:
                this.deslocamentoY = 0;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoY = 0;
                break;
            case KeyEvent.VK_S:
                this.deslocamentoY = 0;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoX = 0;
                break;
             case KeyEvent.VK_D:
                this.deslocamentoX = 0;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoX = 0;
                break;
            case KeyEvent.VK_A:
                this.deslocamentoX = 0;
                break;
            default:
                break;
        }
    }

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

    public int getDeslocamentoX() {
        return deslocamentoX;
    }

    public void setDeslocamentoX(int deslocamentoX) {
        this.deslocamentoX = deslocamentoX;
    }

    public int getDeslocamentoY() {
        return deslocamentoY;
    }

    public void setDeslocamentoY(int deslocamentoY) {
        this.deslocamentoY = deslocamentoY;
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
