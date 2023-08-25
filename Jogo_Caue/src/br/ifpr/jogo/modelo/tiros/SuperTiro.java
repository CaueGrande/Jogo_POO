package br.ifpr.jogo.modelo.tiros;

import javax.swing.ImageIcon;

public class SuperTiro extends AbstractTiro{

    // CONSTRUTOR
    public SuperTiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        super.setPosicaoX(posicaoPersonagemX);
        super.setPosicaoY(posicaoPersonagemY);
        super.setDirecao(direcao);
        super.setVisivel(true);
        carregar();
    }

    // CARREGA A IMAGEM DOS SUPER TIROS
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\granada.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

}