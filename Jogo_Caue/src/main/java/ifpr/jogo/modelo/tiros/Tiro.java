package ifpr.jogo.modelo.tiros;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import ifpr.jogo.modelo.servivos.Personagem;

@Entity
@Table(name = "tb_tiro")
public class Tiro extends AbstractTiro {

    public Tiro(){

    }

    public Tiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        super.setPosicaoX(posicaoPersonagemX);
        super.setPosicaoY(posicaoPersonagemY);
        super.setDirecao(direcao);
        super.setVisivel(true);
        carregar();
    }

    @Override
    public void carregar() {
        if (super.getDirecao() == Personagem.TECLA_W) {
            ImageIcon carregador = new ImageIcon(getClass().getResource("/tiro_w.png"));
            super.setImagem(carregador.getImage());

            super.setAlturaImagem(super.getImagem().getHeight(null));
            super.setLarguraImagem(super.getImagem().getWidth(null));
        }
        if (super.getDirecao() == Personagem.TECLA_S) {
            ImageIcon carregador = new ImageIcon(getClass().getResource("/tiro_s.png"));
            super.setImagem(carregador.getImage());

            super.setAlturaImagem(super.getImagem().getHeight(null));
            super.setLarguraImagem(super.getImagem().getWidth(null));

        }
        if (super.getDirecao() == Personagem.TECLA_D) {
            ImageIcon carregador = new ImageIcon(getClass().getResource("/tiro_d.png"));
            super.setImagem(carregador.getImage());

            super.setAlturaImagem(super.getImagem().getHeight(null));
            super.setLarguraImagem(super.getImagem().getWidth(null));

        }
        if (super.getDirecao() == Personagem.TECLA_A) {
            ImageIcon carregador = new ImageIcon(getClass().getResource("/tiro_a.png"));
            super.setImagem(carregador.getImage());

            super.setAlturaImagem(super.getImagem().getHeight(null));
            super.setLarguraImagem(super.getImagem().getWidth(null));

        }
    }
}
