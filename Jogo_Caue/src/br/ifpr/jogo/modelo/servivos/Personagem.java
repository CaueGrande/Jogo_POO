package br.ifpr.jogo.modelo.servivos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractVida;
import br.ifpr.jogo.modelo.Fase;
import br.ifpr.jogo.modelo.tiros.SuperTiro;
import br.ifpr.jogo.modelo.tiros.Tiro;
import br.ifpr.jogo.util.AbstractConstantes;

public class Personagem extends AbstractVida {
    private int deslocamentoX;
    private int deslocamentoY;
    private int direcao;

    private static final int POSICAO_INICIAL_X = AbstractConstantes.LARGURA_JANELA * 3/7;
    private static final int POSICAO_INICIAL_Y = AbstractConstantes.ALTURA_JANELA * 2/3;

    public static int TECLA_W = 0;
    public static int TECLA_S = 1;
    public static int TECLA_A = 2;
    public static int TECLA_D = 3;

    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;

    private int pontuacao = 0;

    public Personagem() {
        // PADRONIZA O LOCAL INICIAL DO PERSONAGEM
        super.setPosicaoX(POSICAO_INICIAL_X);
        super.setPosicaoY(POSICAO_INICIAL_Y);
        super.setVida(5);
        super.setVELOCIDADE(3);
        // PADRONIZA A DIRECAO INICIAL DO TIRO
        this.direcao = TECLA_W;

        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\personagem_w.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getHeight(null));
        super.setLarguraImagem(getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoX(getPosicaoX() + this.deslocamentoX);
        super.setPosicaoY(getPosicaoY() + this.deslocamentoY);
    }
    
    // MOVE A POSICAO DO PERSONAGEM
    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        // DETECTA A TECLA APERTADA E MOVE SE ESTIVER LIBERADO
        if(Fase.podeMover_W == true){
            if (codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_UP) {
                // MUDA A DIRECAO DO DESLOCAMENTO A SER FEITO
                this.deslocamentoY = -super.getVELOCIDADE();

                // CARREGA A IMAGEM CONDIZENTE A DIRECAO
                ImageIcon carregador_w = new ImageIcon("recursos\\personagem_w.png");
                super.setImagem(carregador_w.getImage());

                // MUDA A VARIAVEL DIRECAO PARA A TECLA APERTADA
                this.direcao = TECLA_W;     

            }
        }
        if(Fase.podeMover_S == true){
            if (codigo == KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN) {
                this.deslocamentoY = super.getVELOCIDADE();

                ImageIcon carregador_s = new ImageIcon("recursos\\personagem_s.png");
                super.setImagem(carregador_s.getImage());

                this.direcao = TECLA_S;
            }
        }
        if(Fase.podeMover_D == true){
            if (codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT) {
                this.deslocamentoX = super.getVELOCIDADE();

                ImageIcon carregador_d = new ImageIcon("recursos\\personagem_d.png");
                super.setImagem(carregador_d.getImage());

                this.direcao = TECLA_D;           
            }
        }
        if(Fase.podeMover_A == true){
            if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT) {
                this.deslocamentoX = -super.getVELOCIDADE();

                ImageIcon carregador_a = new ImageIcon("recursos\\personagem_a.png");
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
        if(this.direcao == Personagem.TECLA_W) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 2;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) + 2;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if(this.direcao == Personagem.TECLA_S) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 2;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        }else if(this.direcao == Personagem.TECLA_D) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) + 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if(this.direcao == Personagem.TECLA_A) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } 
    }

    public void superAtirar() {

        // VERIFICA A TECLA APERTADA E ALTERA O SUPER TIRO PARA A DIRECAO CORRESPONDENTE
        if(this.direcao == Personagem.TECLA_W) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 2;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) + 2;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if(this.direcao == Personagem.TECLA_S) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) - 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 2;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        }else if(this.direcao == Personagem.TECLA_D) {
            int posicaoInicialTiroX = super.getPosicaoX() + (super.getLarguraImagem() / 2) + 8;
            int posicaoInicialTiroY = super.getPosicaoY() + (super.getAlturaImagem() / 2) - 20;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if(this.direcao == Personagem.TECLA_A) {
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

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return superTiros;
    }

    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
