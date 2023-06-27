package br.ifpr.jogo.modelo.Inimigos;

import java.awt.Image;
import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class Lobo {
    private int posicaoX;
    private int posicaoY;
    private int deslocamentoX;
    private int deslocamentoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int velocidadeDeDeslocamento = 3;

    private Personagem personagem;

    public Lobo(){
        this.posicaoX = 530;
        this.posicaoY = 100;

        this.personagem = new Personagem();
    }


    public void carregar(){
        ImageIcon carregador = new ImageIcon("recursos\\lobo_s.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar(){
        this.posicaoX = this.posicaoX + this.deslocamentoX;
        this.posicaoY = this.posicaoY + this.deslocamentoY;
    }
    
    public void mover (){

        while(this.posicaoX > personagem.getPosicaoX()){
            this.deslocamentoX =- this.velocidadeDeDeslocamento;


        }
        while(this.posicaoX < personagem.getPosicaoX()){
            this.deslocamentoX =+ this.velocidadeDeDeslocamento;
        
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
