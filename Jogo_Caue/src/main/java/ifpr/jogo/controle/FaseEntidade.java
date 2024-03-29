package ifpr.jogo.controle;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.List;

import ifpr.jogo.modelo.paisagem.Animal;
import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;

@Entity
@Table(name = "tb_fase")
public class FaseEntidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fase")
    public Integer idFase;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lobo_id")
    private List<Lobo> lobos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "abelhas_id")
    private List<Animal> abelhas;

    // EX:  @Column(name = "nome", unique = true, nullable = false, length = 100)

    @Transient
    private int larguraImagemFundo;
    @Transient
    private int alturaImagemFundo;
    @Transient
    private Image fundo;
    @Transient
    private Image fimDeJogo;

    @Column(name = "conta_tempo_animais")
    private int contaTempoAnimais;
    @Column(name = "conta_tempo_lobos")
    private int contaTempoLobos;
    @Column(name = "conta_tempo_tiros")
    private int contaTempoTiros;
    @Column(name = "conta_tempo_superTiros")
    private int contaTempoSuperTiros;
    @Column(name = "conta_tempo_explosao")
    private int contaTempoExplosao;
    
    @Column(name = "pode_atirar")
    private boolean podeAtirar = true;
    @Column(name = "pode_super_atirar")
    private boolean podeSuperAtirar = true;

    public FaseEntidade() {
        lobos = new ArrayList<>();
        personagem = new Personagem();
        abelhas = new ArrayList<>();

    }

    public void removerLobos(List<Lobo> lobos) {
        Iterator<Lobo> iteratorLobo = lobos.iterator();

        while (iteratorLobo.hasNext()) {
            iteratorLobo.next();
            iteratorLobo.remove();
        }
    }

    public void carregarLobos(List<Lobo> lobos, Personagem personagem) {

        Iterator<Lobo> iteratorLobo = lobos.iterator();

        while (iteratorLobo.hasNext()) {
            Lobo lobo = iteratorLobo.next();
            lobo.carregar();
        }
    }

    
    // GETTERS E SETTERS

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public int getLarguraImagemFundo() {
        return larguraImagemFundo;
    }

    public void setLarguraImagemFundo(int larguraImagemFundo) {
        this.larguraImagemFundo = larguraImagemFundo;
    }

    public int getAlturaImagemFundo() {
        return alturaImagemFundo;
    }

    public void setAlturaImagemFundo(int alturaImagemFundo) {
        this.alturaImagemFundo = alturaImagemFundo;
    }

    public Image getFundo() {
        return fundo;
    }

    public void setFundo(Image image) {
        this.fundo = image;
    }

    public Image getFimDeJogo() {
        return fimDeJogo;
    }

    public void setFimDeJogo(Image fimDeJogo) {
        this.fimDeJogo = fimDeJogo;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public List<Lobo> getLobos() {
        return lobos;
    }

    public void setLobos(List<Lobo> lobos) {
        this.lobos = lobos;
    }

    public List<Animal> getAbelhas() {
        return abelhas;
    }

    public void setAbelhas(List<Animal> abelhas) {
        this.abelhas = abelhas;
    }

    public int getContaTempoAnimais() {
        return contaTempoAnimais;
    }

    public void setContaTempoAnimais(int contaTempoAnimais) {
        this.contaTempoAnimais = contaTempoAnimais;
    }

    public int getContaTempoLobos() {
        return contaTempoLobos;
    }

    public void setContaTempoLobos(int contaTempoLobos) {
        this.contaTempoLobos = contaTempoLobos;
    }

    public int getContaTempoTiros() {
        return contaTempoTiros;
    }

    public void setContaTempoTiros(int contaTempoTiros) {
        this.contaTempoTiros = contaTempoTiros;
    }

    public int getContaTempoSuperTiros() {
        return contaTempoSuperTiros;
    }

    public void setContaTempoSuperTiros(int contaTempoSuperTiros) {
        this.contaTempoSuperTiros = contaTempoSuperTiros;
    }

    public int getContaTempoExplosao() {
        return contaTempoExplosao;
    }

    public void setContaTempoExplosao(int contaTempoExplosao) {
        this.contaTempoExplosao = contaTempoExplosao;
    }

    public boolean getPodeAtirar() {
        return podeAtirar;
    }

    public void setPodeAtirar(boolean podeAtirar) {
        this.podeAtirar = podeAtirar;
    }

    public boolean getPodeSuperAtirar() {
        return podeSuperAtirar;
    }

    public void setPodeSuperAtirar(boolean podeSuperAtirar) {
        this.podeSuperAtirar = podeSuperAtirar;
    }
}

