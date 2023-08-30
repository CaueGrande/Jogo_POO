package br.ifpr.jogo.modelo.servivos.Inimigos;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractVida;
import br.ifpr.jogo.modelo.servivos.Personagem;

public class Lobo extends AbstractVida{

    private static ArrayList<Lobo> lobos;
    private static final int VELOCIDADE = 1;

    private Personagem personagem;

    public Lobo(int posicaoX, int posicaoY, Personagem personagem) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVisivel(true);
        this.personagem = personagem;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\lobo_s.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getWidth(null));
        super.setLarguraImagem(getImagem().getHeight(null)); 
    }

    @Override
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
        /*if (super.getPosicaoX() < 0) {
            super.setVisivel(false);
        }
        if (super.getPosicaoX() > 1300) {
            super.setVisivel(false);
        }
        if (super.getPosicaoY() < 0) {
            super.setVisivel(false);
        }
        if (super.getPosicaoY() > 1100) {
            super.setVisivel(false);
        }*/
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
}
