package ifpr.jogo.modelo.paisagem;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import ifpr.jogo.modelo.AbstractEntidade;

@Entity
@Table(name="tb_animal")
public class Animal extends AbstractEntidade{

    public Animal(){

    }
    
    public Animal(int posicaoX, int posicaoY) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVelocidade(2);
        super.setVisivel(true);
    }

    @Override
    public void carregar() {
            ImageIcon carregador = new ImageIcon(getClass().getResource("/abelha.png"));
            super.setImagem(carregador.getImage());
            super.setAlturaImagem(getImagem().getHeight(null));
            super.setLarguraImagem(getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoX(super.getPosicaoX() - super.getVelocidade());
    }
    
}
