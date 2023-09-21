package main.java.ifpr.jogo.modelo.tiros;

import main.java.ifpr.jogo.modelo.AbstractVida;
import main.java.ifpr.jogo.modelo.servivos.Personagem;

public abstract class AbstractTiro extends AbstractVida{
    public static final int VELOCIDADE = 5;
    private int direcao;

    @Override
    public final void atualizar() {

        // MUDA A DIRECAO DO TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O PERSONAGEM
        if(this.direcao == Personagem.TECLA_W) {
            super.setPosicaoY(super.getPosicaoY() - VELOCIDADE);

        } else if(this.direcao == Personagem.TECLA_S) {
            super.setPosicaoY(super.getPosicaoY() + VELOCIDADE);
            
        } else if(this.direcao == Personagem.TECLA_D) {
            super.setPosicaoX(super.getPosicaoX() + VELOCIDADE);

        } else if(this.direcao == Personagem.TECLA_A) {
            super.setPosicaoX(super.getPosicaoX() - VELOCIDADE);
        }
    }

    public final boolean tiroDentroJanela() {
        if(super.getVisivel() == true){
            if (super.getPosicaoX() > 1285 || super.getPosicaoX() < 0 || super.getPosicaoY() > 1085 || super.getPosicaoY() < 0) {
                super.setVisivel(false);

            } else {
                super.setVisivel(true);
            }
        }
        
        return super.getVisivel();
    }
    
    // GETTERS E SETTERS
    public int getDirecao() {
        return direcao;
    }
    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }
}
