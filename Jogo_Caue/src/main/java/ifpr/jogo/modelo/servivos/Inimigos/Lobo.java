package main.java.ifpr.jogo.modelo.servivos.Inimigos;

import javax.swing.ImageIcon;

import main.java.ifpr.jogo.modelo.AbstractVida;
import main.java.ifpr.jogo.modelo.servivos.Personagem;

public class Lobo extends AbstractVida{

    private Personagem personagem;

    public static final int PONTUACAO_POR_LOBO = 100;

    public Lobo(int posicaoX, int posicaoY, Personagem personagem) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVisivel(true);
        super.setVELOCIDADE(1);
        this.personagem = personagem;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/lobo_s.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getHeight(null) - 10);
        super.setLarguraImagem(getImagem().getWidth(null) - 70); 
    }

    @Override
    public void atualizar() {
        int personagemX = personagem.getPosicaoX() - (personagem.getLarguraImagem() / 2);
        int personagemY = personagem.getPosicaoY() - (personagem.getAlturaImagem() / 2);
        
        // REGISTRA A DIFERENCA ENTRE A POSICAO DO PERSONAGEM E DO LOBO
        int deltaX = personagemX - super.getPosicaoX();
        int deltaY = personagemY - super.getPosicaoY();
        // FOI CRIADO ESSA VARIAVEL COMPARATORIA POIS
        // COMPARAR AS VARIAVEIS DE POSICAO X E Y DO PERSONAGEM COM A DO LOBO ESTAVA BUGANDO GERANDO BUG

        // MOVE O LOBO DE ACORDO COM A DIFERENCA DE POSICAO
        if(deltaY > 0){
            super.setPosicaoY(super.getPosicaoY() + super.getVELOCIDADE());
        } else if(deltaY < 0){
            super.setPosicaoY(super.getPosicaoY() - super.getVELOCIDADE());
        }
        if(deltaX > 0){
            super.setPosicaoX(super.getPosicaoX() + super.getVELOCIDADE());
        }else if(deltaX < 0){
            super.setPosicaoX(super.getPosicaoX() - super.getVELOCIDADE());
        }
    }

    // GETTERS E SETTERS

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public static int getPontuacaoPorLobo() {
        return PONTUACAO_POR_LOBO;
    }
}