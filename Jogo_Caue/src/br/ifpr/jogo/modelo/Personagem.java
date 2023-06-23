package br.ifpr.jogo.modelo;

import java.awt.Image;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

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

    private ArrayList<Tiro> tiros;

    //LOCAL INICIAL DO PERSONAGEM
    public Personagem(int velocidadeDeDeslocamento){
        this.posicaoX = POSICAO_INICIAL_X;
        this.posicaoY = POSICAO_INICIAL_Y;

        this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;

        this.tiros = new ArrayList<Tiro> ();
    }

    //IMAGEM INICIAL DO PERSONAGEM
    public void carregar(){
        ImageIcon carregador = new ImageIcon("recursos\\personagem_w.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }
        
    
    //MOVE O PERSONAGEM
    public void atualizar(){
        this.posicaoX = this.posicaoX + this.deslocamentoX;
        this.posicaoY = this.posicaoY + this.deslocamentoY;
    }

    public void atirar(){
        int Posicao_inicial_tiroX = this.posicaoX + (this.larguraImagem / 2);
        int Posicao_inicial_tiroY = this.posicaoY + (this.alturaImagem / 2);

        Tiro tiro = new Tiro(Posicao_inicial_tiroX, Posicao_inicial_tiroY);
        this.tiros.add(tiro);
    }

    public void mover (KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W){
            ImageIcon carregador_w = new ImageIcon("recursos\\personagem_w.png");
            this.imagem = carregador_w.getImage();

            this.deslocamentoY = - this.velocidadeDeDeslocamento;

        } else if(codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S){
            ImageIcon carregador_s = new ImageIcon("recursos\\personagem_s.png");
            this.imagem = carregador_s.getImage();

            this.deslocamentoY = this.velocidadeDeDeslocamento;

        } else if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D){
            ImageIcon carregador_d = new ImageIcon("recursos\\personagem_d.png");
            this.imagem = carregador_d.getImage();

            this.deslocamentoX = this.velocidadeDeDeslocamento;

        } else if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A){
            ImageIcon carregador_a = new ImageIcon("recursos\\personagem_a.png");
            this.imagem = carregador_a.getImage();

            this.deslocamentoX = - this.velocidadeDeDeslocamento;
        }        

    }

    public void parar(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        //PARAR OS MOVIMENTOS
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

    //GETTERS E SETTERS
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

    public int getVelocidadeDeDeslocamento() {
        return velocidadeDeDeslocamento;
    }

    public void setVelocidadeDeDeslocamento(int velocidadeDeDeslocamento) {
        this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;
    }

    public static int getPosicaoInicialX() {
        return POSICAO_INICIAL_X;
    }

    public static int getPosicaoInicialY() {
        return POSICAO_INICIAL_Y;
    }

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    
}
