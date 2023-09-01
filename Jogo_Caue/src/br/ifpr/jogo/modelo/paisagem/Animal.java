package br.ifpr.jogo.modelo.paisagem;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractPosicionamento;

public class Animal extends AbstractPosicionamento{

    public Animal(int posicaoX, int posicaoY) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVELOCIDADE(2);
        super.setVisivel(true);
    }

    @Override
    public void carregar() {
            ImageIcon carregador = new ImageIcon("recursos\\abelha.png");
            super.setImagem(carregador.getImage());
            super.setAlturaImagem(getImagem().getHeight(null));
            super.setLarguraImagem(getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoY(super.getPosicaoY() - super.getVELOCIDADE());
    }
    
}
