package br.ifpr.jogo.modelo.paisagem;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractDeslocamento;

public class Animal extends AbstractDeslocamento{

    public Animal(int posicaoX, int posicaoY) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVELOCIDADE(2);
        super.setVisivel(true);
    }

    @Override
    public void carregar() {
            ImageIcon carregador = new ImageIcon("recursos\\personagem_a.png");
            super.setImagem(carregador.getImage());
            super.setAlturaImagem(getImagem().getWidth(null));
            super.setLarguraImagem(getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoY(super.getPosicaoY() - super.getVELOCIDADE());
    }
    
}
