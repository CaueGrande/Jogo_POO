package br.ifpr.jogo.modelo.Inimigos;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class Lobo {
    private int posicaoX;
    private int posicaoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;

    private static ArrayList<Lobo> lobos;

    private static final int VELOCIDADE = 1;

    private boolean visibilidade;

    private Personagem personagem;

    // CONSTRUTOR
    public Lobo(int posicaoX, int posicaoY, Personagem personagem) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.visibilidade = true;
        this.personagem = personagem;
    }

    // CARREGA A IMAGEM INICIAL DO LOBO
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\lobo_s.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // MUDA A POSICAO DOS LOBOS
    public void atualizar() {
        int personagemX = personagem.getPosicaoX() - (personagem.getLarguraImagem() / 2);
        int personagemY = personagem.getPosicaoY() - (personagem.getAlturaImagem() / 2);
        
        // REGISTRA A DIFERENCA ENTRE A POSICAO DO PERSONAGEM E DO LOBO
        int deltaX = personagemX - this.getPosicaoX();
        int deltaY = personagemY - this.getPosicaoY();
        // FOI CRIADO ESSA VARIAVEL COMPARATORIA POIS
        // COMPARAR AS VARIAVEIS DE POSICAO X E Y DO PERSONAGEM COM A DO LOBO ESTAVA BUGANDO GERANDO BUG

        // MOVE O LOBO DE ACORDO COM A DIFERENCA DE POSICAO
        if(deltaY > 0){
            this.posicaoY += VELOCIDADE;
        } else if(deltaY < 0){
            this.posicaoY -= VELOCIDADE;
        }
        if(deltaX > 0){
            this.posicaoX += VELOCIDADE;
        }else if(deltaX < 0){
            this.posicaoX -= VELOCIDADE;
        }
        
        //ESSE IF SO SERA USADO QUANDO O MAPA FOR MAIOR E O INIMIGO PODERA SER DEIXADO PARA TRAS
        /*if (this.posicaoX < 0) {
            this.visibilidade = false;
        }
        if (this.posicaoX > 1300) {
            this.visibilidade = false;
        }
        if (this.posicaoY < 0) {
            this.visibilidade = false;
        }
        if (this.posicaoY > 1100) {
            this.visibilidade = false;
        }*/
    }

    // PEGA AS DIMENSOES E POSICOES DA IMAGEM, PARA QUE POSSA HAVER A COLISAO
    public Rectangle getRectangle(){
        return new Rectangle(this.posicaoX, this.posicaoY, (this.larguraImagem/2), (this.alturaImagem/2));

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

    public ArrayList<Lobo> getLobos() {
        return lobos;
    }

    public static void setLobos(ArrayList<Lobo> lobos) {
        Lobo.lobos = lobos;
    }

    public static int getVelocidade() {
        return VELOCIDADE;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

}
