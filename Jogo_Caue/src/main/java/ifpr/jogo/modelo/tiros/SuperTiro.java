package ifpr.jogo.modelo.tiros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

@Entity
@Table(name = "tb_super_tiro")
public class SuperTiro extends AbstractTiro {

    @Column(name="explodindo")
    private boolean explodido;

    public SuperTiro(){
        
    }

    public SuperTiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        super.setPosicaoX(posicaoPersonagemX);
        super.setPosicaoY(posicaoPersonagemY);
        super.setDirecao(direcao);
        this.explodido = false;
        super.setVisivel(true);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/granada.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));

    }

    public void carregarExplodido() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/explosao.gif"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));

    }

    public void explodir() {
        if(this.explodido == false){
            super.setPosicaoX(this.getPosicaoX() - 250);
            super.setPosicaoY(this.getPosicaoY() - 250);
        }
        
        this.explodido = true;
    }


    public boolean getExplodido() {
        return explodido;
    }
    public void setExplodido(boolean explodido) {
        this.explodido = explodido;
    }
}
