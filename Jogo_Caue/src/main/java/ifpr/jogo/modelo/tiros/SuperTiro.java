package ifpr.jogo.modelo.tiros;

import javax.swing.ImageIcon;

public class SuperTiro extends AbstractTiro {

    public boolean explodido;

    public SuperTiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        super.setPosicaoX(posicaoPersonagemX);
        super.setPosicaoY(posicaoPersonagemY);
        super.setDirecao(direcao);
        this.explodido = false;
        super.setVisivel(true);
        carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/granada.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));

    }

    public void explodir() {
        this.explodido = true;

        ImageIcon carregador = new ImageIcon(getClass().getResource("/explosao.gif"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));

        super.setPosicaoX(this.getPosicaoX() - 250);
        super.setPosicaoY(this.getPosicaoY() - 250);
    }
}
