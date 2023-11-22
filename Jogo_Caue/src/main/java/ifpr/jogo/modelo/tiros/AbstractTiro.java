package ifpr.jogo.modelo.tiros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import ifpr.jogo.modelo.AbstractVida;
import ifpr.jogo.modelo.servivos.Personagem;

@Entity
@Table(name = "tb_abst_tiro")
public abstract class AbstractTiro extends AbstractVida{

    @Transient
    public static final int ABSTRACT_VELOCIDADE_TIROS = 5;

    @Column(name="direcao")
    private int direcao;

    @Override
    public final void atualizar() {

        // MUDA A DIRECAO DO TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O PERSONAGEM
        if(this.direcao == Personagem.TECLA_W) {
            super.setPosicaoY(super.getPosicaoY() - ABSTRACT_VELOCIDADE_TIROS);

        } else if(this.direcao == Personagem.TECLA_S) {
            super.setPosicaoY(super.getPosicaoY() + ABSTRACT_VELOCIDADE_TIROS);
            
        } else if(this.direcao == Personagem.TECLA_D) {
            super.setPosicaoX(super.getPosicaoX() + ABSTRACT_VELOCIDADE_TIROS);

        } else if(this.direcao == Personagem.TECLA_A) {
            super.setPosicaoX(super.getPosicaoX() - ABSTRACT_VELOCIDADE_TIROS);
        }
    }

    public final boolean tiroDentroJanela() {
        if(super.getVisivel() == true){
            if (super.getPosicaoX() > 1285 || super.getPosicaoX() < 0 || super.getPosicaoY() > 1085 || super.getPosicaoY() < 0) {
                super.setVisivel(false);

            } else {
                super.setVisivel(true);
            }
        }
        
        return super.getVisivel();
    }
    
    // GETTERS E SETTERS
    public int getDirecao() {
        return direcao;
    }
    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }
}
