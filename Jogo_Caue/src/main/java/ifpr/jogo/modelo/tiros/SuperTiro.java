package ifpr.jogo.modelo.tiros;

import javax.swing.ImageIcon;

public class SuperTiro extends AbstractTiro{

    public SuperTiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        super.setPosicaoX(posicaoPersonagemX);
        super.setPosicaoY(posicaoPersonagemY);
        super.setDirecao(direcao);
        super.setVisivel(true);
        carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/granada.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null) );
        super.setLarguraImagem(super.getImagem().getWidth(null) );
    }

}