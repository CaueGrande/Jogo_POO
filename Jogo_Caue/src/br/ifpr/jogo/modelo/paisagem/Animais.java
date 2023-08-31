package br.ifpr.jogo.modelo.paisagem;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractDeslocamento;

public class Animais extends AbstractDeslocamento{



    @Override
    public void atualizar() {
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\abelha.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getWidth(null));
        super.setLarguraImagem(getImagem().getHeight(null)); 
    }
    
}
