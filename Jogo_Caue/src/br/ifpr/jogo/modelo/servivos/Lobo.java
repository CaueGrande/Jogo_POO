package br.ifpr.jogo.modelo.servivos;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractDeslocamento;

public class Lobo extends AbstractDeslocamento{

    private static ArrayList<Lobo> lobos;
    private static final int VELOCIDADE = 1;

    private boolean visibilidade;
    private Personagem personagem;

    // CONSTRUTOR
    public Lobo(int posicaoX, int posicaoY, Personagem personagem) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        this.visibilidade = true;
        this.personagem = personagem;
    }

    // CARREGA A IMAGEM INICIAL DO LOBO
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\lobo_s.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getWidth(null));
        super.setLarguraImagem(getImagem().getHeight(null)); 
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
            super.setPosicaoY(getPosicaoY() + VELOCIDADE);
        } else if(deltaY < 0){
            super.setPosicaoY(getPosicaoY() - VELOCIDADE);
        }
        if(deltaX > 0){
            super.setPosicaoX(getPosicaoX() + VELOCIDADE);
        }else if(deltaX < 0){
            super.setPosicaoX(getPosicaoX() - VELOCIDADE);
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
        return new Rectangle(super.getPosicaoX(), super.getPosicaoY(), (super.getLarguraImagem()/2), (super.getAlturaImagem()/2));

    }

    // GETTERS E SETTERS
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
