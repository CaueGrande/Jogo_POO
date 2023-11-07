package ifpr.jogo.modelo;

import java.awt.Rectangle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vida")
public abstract class AbstractVida extends AbstractEntidade {

    @Column(name = "vida")
    private int vida;

    // DEFINE UM RETANGULO AO REDOR DA IMAGEM
    public final Rectangle getRectangle() {
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
