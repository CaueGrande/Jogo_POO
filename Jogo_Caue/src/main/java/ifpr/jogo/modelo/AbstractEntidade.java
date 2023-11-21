package ifpr.jogo.modelo;

import java.awt.Image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_entidade")
    private int idEntidade;

    @Column(name="posicao_x")
    private int posicaoX;

    @Column(name="posicao_y")
    private int posicaoY;

    @Transient
    private Image imagem;

    @Transient
    private int larguraImagem;

    @Transient
    private int alturaImagem;

    @Column(name="velocidade")
    private int velocidade;

    @Column(name="visivel")
    private boolean visivel;
    
    // CARREGA OS DADOS NA FASE
    public abstract void carregar();
    // ALTERA DADOS
    public abstract void atualizar();
    
    // GETTERS E SETTERS
    public int getPosicaoX() {
        return posicaoX;
    }
    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }
    public int getPosicaoY() {
        return posicaoY;
    }
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }
    public Image getImagem() {
        return imagem;
    }
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
    public int getLarguraImagem() {
        return larguraImagem;
    }
    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }
    public int getAlturaImagem() {
        return alturaImagem;
    }
    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }
    public int getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    public boolean getVisivel() {
        return visivel;
    }
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
}
