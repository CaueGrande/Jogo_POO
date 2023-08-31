package br.ifpr.jogo.modelo;

import java.awt.Rectangle;

public abstract class AbstractVida extends AbstractPosicionamento{
    private int vida;

    // DEFINE UM RETANGULO AO REDOR DA IMAGEM 
    public final Rectangle getRectangle(){
        return new Rectangle(super.getPosicaoX(), super.getPosicaoY(), super.getLarguraImagem(), super.getAlturaImagem());

    }

    // GETTERS E SETTERS
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
