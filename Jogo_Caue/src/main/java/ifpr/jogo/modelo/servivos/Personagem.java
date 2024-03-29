package ifpr.jogo.modelo.servivos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ifpr.jogo.modelo.AbstractVida;
import ifpr.jogo.modelo.tiros.SuperTiro;
import ifpr.jogo.modelo.tiros.Tiro;
import ifpr.jogo.util.AbstractConstantes;

@Entity
@Table(name = "tb_personagem")
public class Personagem extends AbstractVida {

    @Transient
    private static final int POSICAO_INICIAL_X = AbstractConstantes.LARGURA_JANELA * 3 / 7;
    @Transient
    private static final int POSICAO_INICIAL_Y = AbstractConstantes.ALTURA_JANELA * 2 / 3;

    @Transient
    public static final int TECLA_W = 0;
    @Transient
    public static final int TECLA_S = 1;
    @Transient
    public static final int TECLA_A = 2;
    @Transient
    public static final int TECLA_D = 3;

    @Column(name = "pode_mover_w")
    private boolean podeMoverW = true;
    @Column(name = "pode_mover_s")
    private boolean podeMoverS = true;
    @Column(name = "pode_mover_a")
    private boolean podeMoverA = true;
    @Column(name = "pode_mover_d")
    private boolean podeMoverD = true;

    @Transient
    private int deslocamentoX;
    @Transient
    private int deslocamentoY;

    @Column(name = "direcao")
    private int direcao;

    @Column(name = "pontuacao")
    private int pontuacao = 0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_id")
    private List<Tiro> tiros;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_super_id")
    private List<SuperTiro> superTiros;

    // PADRONIZA A POSICAO INICIAL, VIDA, VELOCIDADE E A DIRECAO DO TIRO
    public Personagem() {
        super.setPosicaoX(POSICAO_INICIAL_X);
        super.setPosicaoY(POSICAO_INICIAL_Y);

        super.setVida(5);
        super.setVelocidade(3);

        this.direcao = TECLA_W;

        this.tiros = new ArrayList<>();
        this.superTiros = new ArrayList<>();
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/personagem_w.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getHeight(null));
        super.setLarguraImagem(getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoX(getPosicaoX() + this.deslocamentoX);
        super.setPosicaoY(getPosicaoY() + this.deslocamentoY);

        // VERIFICA SE O PERSONAGEM NAO ESTA SAINDO DA JANELA
        if (this.getPosicaoY() < AbstractConstantes.ALTURA_JANELA - 200) {
            this.podeMoverS = true;
        } else {
            this.podeMoverS = false;
            this.setPosicaoY(AbstractConstantes.ALTURA_JANELA - 200);
        }
        if (this.getPosicaoY() > 20) {
            this.podeMoverW = true;
        } else {
            this.podeMoverW = false;
            this.setPosicaoY(20);
        }
        if (this.getPosicaoX() < AbstractConstantes.LARGURA_JANELA - 150) {
            this.podeMoverD = true;
        } else {
            this.podeMoverD = false;
            this.setPosicaoX(AbstractConstantes.LARGURA_JANELA - 150);
        }
        if (this.getPosicaoX() > 10) {
            this.podeMoverA = true;
        } else {
            this.podeMoverA = false;
            this.setPosicaoX(10);
        }
    }

    // MOVE A POSICAO DO PERSONAGEM
    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        // DETECTA A TECLA APERTADA E MOVE SE ESTIVER LIBERADO
        if (this.podeMoverW) {
            if (codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_UP) {
                // MUDA A DIRECAO DO DESLOCAMENTO A SER FEITO
                this.deslocamentoY = -super.getVelocidade();

                // CARREGA A IMAGEM CONDIZENTE A DIRECAO
                ImageIcon carregador_w = new ImageIcon(getClass().getResource("/personagem_w.png"));
                super.setImagem(carregador_w.getImage());

                // MUDA A VARIAVEL DIRECAO PARA A TECLA APERTADA
                this.direcao = TECLA_W;

            }
        }
        if (this.podeMoverS = true) {
            if (codigo == KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN) {
                this.deslocamentoY = super.getVelocidade();

                ImageIcon carregador_s = new ImageIcon(getClass().getResource("/personagem_s.png"));
                super.setImagem(carregador_s.getImage());

                this.direcao = TECLA_S;
            }
        }
        if (this.podeMoverD == true) {
            if (codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT) {
                this.deslocamentoX = super.getVelocidade();

                ImageIcon carregador_d = new ImageIcon(getClass().getResource("/personagem_d.png"));
                super.setImagem(carregador_d.getImage());

                this.direcao = TECLA_D;
            }
        }
        if (this.podeMoverA == true) {
            if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT) {
                this.deslocamentoX = -super.getVelocidade();

                ImageIcon carregador_a = new ImageIcon(getClass().getResource("/personagem_a.png"));
                super.setImagem(carregador_a.getImage());

                this.direcao = TECLA_A;
            }
        }

    }

    // PARA O MOVIMENTO DO PERSONAGEM
    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_UP) {
            this.deslocamentoY = 0;

        } else if (codigo == KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN) {
            this.deslocamentoY = 0;

        } else if (codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT) {
            this.deslocamentoX = 0;

        } else if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT) {
            this.deslocamentoX = 0;

        }
    }

    public void atirar() {

        // VERIFICA A TECLA APERTADA E ALTERA O TIRO PARA A DIRECAO CORRESPONDENTE
        if (this.direcao == Personagem.TECLA_W) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 2;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) + 2;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if (this.direcao == Personagem.TECLA_S) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 2;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if (this.direcao == Personagem.TECLA_D) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) + 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if (this.direcao == Personagem.TECLA_A) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        }
    }

    public void superAtirar() {

        // VERIFICA A TECLA APERTADA E ALTERA O SUPER TIRO PARA A DIRECAO CORRESPONDENTE
        if (this.direcao == Personagem.TECLA_W) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 2;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) + 2;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if (this.direcao == Personagem.TECLA_S) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 2;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if (this.direcao == Personagem.TECLA_D) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) + 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if (this.direcao == Personagem.TECLA_A) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        }
    }

    // GETTERS E SETTERS
    public int getDeslocamentoX() {
        return deslocamentoX;
    }
    public void setDeslocamentoX(int deslocamentoX) {
        this.deslocamentoX = deslocamentoX;
    }
    public int getDeslocamentoY() {
        return deslocamentoY;
    }
    public void setDeslocamentoY(int deslocamentoY) {
        this.deslocamentoY = deslocamentoY;
    }

    public static int getPosicaoInicialX() {
        return POSICAO_INICIAL_X;
    }
    public static int getPosicaoInicialY() {
        return POSICAO_INICIAL_Y;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }
    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }
    public List<SuperTiro> getSuperTiros() {
        return superTiros;
    }
    public void setSuperTiros(List<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public boolean getPodeMoverW() {
        return podeMoverW;
    }
    public void setPodeMoverW(boolean podeMoverW) {
        this.podeMoverW = podeMoverW;
    }
    public boolean getPodeMoverS() {
        return podeMoverS;
    }
    public void setPodeMoverS(boolean podeMoverS) {
        this.podeMoverS = podeMoverS;
    }
    public boolean getPodeMoverA() {
        return podeMoverA;
    }
    public void setPodeMoverA(boolean podeMoverA) {
        this.podeMoverA = podeMoverA;
    }
    public boolean getPodeMoverD() {
        return podeMoverD;
    }
    public void setPodeMoverD(boolean podeMoverD) {
        this.podeMoverD = podeMoverD;
    }
}
