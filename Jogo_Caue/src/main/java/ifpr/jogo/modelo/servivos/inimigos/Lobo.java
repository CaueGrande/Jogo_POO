package ifpr.jogo.modelo.servivos.inimigos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import ifpr.jogo.controle.FaseEntidade;
import ifpr.jogo.modelo.AbstractVida;
import ifpr.jogo.modelo.servivos.Personagem;

@Entity
@Table(name = "tb_lobo")
public class Lobo extends AbstractVida {

    @Transient
    public static final int PONTUACAO_POR_LOBO = 100;

    @Column(name="velocidade_lobos")
    public static int velocidadeLobos = 1;

    @ManyToOne 
    @JoinColumn(name = "fk_fase", referencedColumnName = "id_fase")
    private FaseEntidade faseId;

    @Transient
    private Personagem personagem;

    public Lobo(int posicaoX, int posicaoY, Personagem personagem) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVisivel(true);
        super.setVelocidade(velocidadeLobos);
        this.personagem = personagem;

    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/lobo_s.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getHeight(null) - 10);
        super.setLarguraImagem(getImagem().getWidth(null) - 70);
    }

    @Override
    public void atualizar() {
        int personagemX = personagem.getPosicaoX() - (personagem.getLarguraImagem() / 2);
        int personagemY = personagem.getPosicaoY() - (personagem.getAlturaImagem() / 2);

        // REGISTRA A DIFERENCA ENTRE A POSICAO DO PERSONAGEM E DO LOBO
        int deltaX = personagemX - super.getPosicaoX();
        int deltaY = personagemY - super.getPosicaoY();

        // MOVE O LOBO DE ACORDO COM A DIFERENCA DE POSICAO
    

        if (deltaY > 30) {
            super.setPosicaoY(super.getPosicaoY() + super.getVelocidade());
        } else if (deltaY < 30) {
            super.setPosicaoY(super.getPosicaoY() - super.getVelocidade());
        }

        if (deltaX > 30) {
            super.setPosicaoX(super.getPosicaoX() + super.getVelocidade());
        } else if (deltaX < 30) {
            super.setPosicaoX(super.getPosicaoX() - super.getVelocidade());
        }
    }

    
    public FaseEntidade getFaseId() {
        return faseId;
    }

    public void setFaseId(FaseEntidade idFase) {
        this.faseId = idFase;
    }
}
