package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoX;
    private int posicaoY;
    private int deslocamentoX;
    private int deslocamentoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int velocidadeDeDeslocamento = 3;
    private int direcao;

    private static final int POSICAO_INICIAL_X = 575;
    private static final int POSICAO_INICIAL_Y = 850;

    public static int TECLA_W = 0;
    public static int TECLA_S = 1;
    public static int TECLA_A = 2;
    public static int TECLA_D = 3;

    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;

    // CONSTRUTOR
    public Personagem() {
        // PADRONIZA O LOCAL INICIAL DO PERSONAGEM
        this.posicaoX = POSICAO_INICIAL_X;
        this.posicaoY = POSICAO_INICIAL_Y;
        // PADRONIZA A DIRECAO INICIAL DO TIRO
        this.direcao = TECLA_W;

        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
    }

    // CARREGA A IMAGEM INICIAL DO PERSONAGEM
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\personagem_w.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // ATUALIZA A POSICAO DO PERSONAGEM
    public void atualizar() {
        this.posicaoX += this.deslocamentoX;
        this.posicaoY += this.deslocamentoY;
    }
    
    // MOVE A POSICAO DO PERSONAGEM
    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        // DETECTA A TECLA APERTADA E MOVE SE ESTIVER LIBERADO
        if ((codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_UP)&& Fase.podeMover_W == true) {
            // MUDA A DIRECAO DO DESLOCAMENTO A SER FEITO
            this.deslocamentoY = -this.velocidadeDeDeslocamento;

            // CARREGA A IMAGEM CONDIZENTE A DIRECAO
            ImageIcon carregador_w = new ImageIcon("recursos\\personagem_w.png");
            this.imagem = carregador_w.getImage();

            // MUDA A VARIAVEL DIRECAO PARA A TECLA APERTADA
            this.direcao = TECLA_W;     

            // LIBERA O MOVIMENTO OPOSTO CASO ESTEJA TRAVADO 
            Fase.podeMover_S = true;
            
        }
        if ((codigo == KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN)&& Fase.podeMover_S == true) {
            this.deslocamentoY = this.velocidadeDeDeslocamento;

            ImageIcon carregador_s = new ImageIcon("recursos\\personagem_s.png");
            this.imagem = carregador_s.getImage();

            this.direcao = TECLA_S;

            Fase.podeMover_W = true;
    
        }
        if ((codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT) && Fase.podeMover_D == true) {
            this.deslocamentoX = this.velocidadeDeDeslocamento;

            ImageIcon carregador_d = new ImageIcon("recursos\\personagem_d.png");
            this.imagem = carregador_d.getImage();

            this.direcao = TECLA_D;

            Fase.podeMover_A = true;
            
        }
        if ((codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT) && Fase.podeMover_A == true) {
            this.deslocamentoX = -this.velocidadeDeDeslocamento;

            ImageIcon carregador_a = new ImageIcon("recursos\\personagem_a.png");
            this.imagem = carregador_a.getImage();

            this.direcao = TECLA_A;

            Fase.podeMover_D = true;
            
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
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) - 2;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) + 2;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if(this.direcao == Personagem.TECLA_S) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) - 8;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) - 2;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        }else if(this.direcao == Personagem.TECLA_D) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) + 8;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) - 20;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } else if(this.direcao == Personagem.TECLA_A) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) - 8;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) - 20;

            Tiro tiro = new Tiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.tiros.add(tiro);

        } 
    }

    public void superAtirar() {

        // VERIFICA A TECLA APERTADA E ALTERA O SUPER TIRO PARA A DIRECAO CORRESPONDENTE
        if(this.direcao == Personagem.TECLA_W) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) - 2;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) + 2;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if(this.direcao == Personagem.TECLA_S) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) - 8;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) - 2;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        }else if(this.direcao == Personagem.TECLA_D) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) + 8;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) - 20;

            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } else if(this.direcao == Personagem.TECLA_A) {
            int posicaoInicialTiroX = this.posicaoX + (this.larguraImagem / 2) - 8;
            int posicaoInicialTiroY = this.posicaoY + (this.alturaImagem / 2) - 20;
            
            SuperTiro superTiro = new SuperTiro(posicaoInicialTiroX, posicaoInicialTiroY, this.direcao);
            this.superTiros.add(superTiro);

        } 

    }

    // PEGA AS DIMENSOES E POSICOES DA IMAGEM, PARA QUE POSSA HAVER A COLISAO
    public Rectangle getRectangle(){
        return new Rectangle(this.posicaoX, this.posicaoY, (this.larguraImagem/3), (this.alturaImagem/2));

    }

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

    public int getVelocidadeDeDeslocamento() {
        return velocidadeDeDeslocamento;
    }

    public void setVelocidadeDeDeslocamento(int velocidadeDeDeslocamento) {
        this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;
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

}
