package br.ifpr.jogo.modelo.tiros;

import br.ifpr.jogo.modelo.AbstractDeslocamento;
import br.ifpr.jogo.modelo.servivos.Personagem;

public abstract class AbstractTiro extends AbstractDeslocamento{
    public static final int VELOCIDADE = 5;
    private boolean visivel;
    private int direcao;

    public void atualizar() {

        // MUDA A DIRECAO DO SUPER TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O PERSONAGEM
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

    public boolean isVisivel() {
        if (super.getPosicaoX() > 1285 || super.getPosicaoX() < 0 || super.getPosicaoY() > 1085 || super.getPosicaoY() < 0) {
            this.visivel = false;
        } else {
            this.visivel = true;
        }
        return this.visivel;
    }
    
    public boolean getVisivel() {
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
