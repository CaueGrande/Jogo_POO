package ifpr.jogo.controle;

import java.awt.Image;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.Timer;

import java.util.List;

import ifpr.jogo.modelo.paisagem.Animal;
import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;

@Entity
@Table(name = "tb_fase")
public class FaseEntidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fase", unique = true, nullable = false)
    private Integer idFase;

    @Transient // @OneToMany(mappedBy = "tb_lobo")
    private List<Lobo> lobos;

    @Transient // @OneToOne(mappedBy = "tb_personagem")
    private Personagem personagem;

    @Transient
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

    @Transient
    private Timer timer;

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
        lobos = new ArrayList<Lobo>();
    }

    
    // GETTERS E SETTERS

    public Integer getFaseId() {
        return idFase;
    }

    public void setFaseId(Integer idFase) {
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
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

