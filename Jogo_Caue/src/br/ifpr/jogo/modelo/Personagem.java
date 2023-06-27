package br.ifpr.jogo.modelo;

import java.awt.Image;

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

    public static int W_TOP = 0;
    public static int S_BOTTOM = 1;
    public static int A_LEFT = 2;
    public static int D_RIGTH = 3;

    private ArrayList<Tiro> tiros;

    // CONSTRUTOR
    public Personagem() {
        // PADRONIZA O LOCAL INICIAL DO PERSONAGEM
        this.posicaoX = POSICAO_INICIAL_X;
        this.posicaoY = POSICAO_INICIAL_Y;
        this.direcao = W_TOP;

        this.tiros = new ArrayList<Tiro>();
    }

    // CARREGA A IMAGEM INICIAL DO PERSONAGEM
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\personagem_w.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // MOVE A IMAGEM DO PERSONAGEM
    public void atualizar() {
        this.posicaoX += this.deslocamentoX;
        this.posicaoY += this.deslocamentoY;
    }

    // DETECTA A TECLA APERTADA E MOVE DE ACORDO COM ELA
    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            this.deslocamentoY = -this.velocidadeDeDeslocamento;
            ImageIcon carregador_w = new ImageIcon("recursos\\personagem_w.png");
            this.imagem = carregador_w.getImage();
            this.direcao = W_TOP;

        }
        if (codigo == KeyEvent.VK_S) {
            this.deslocamentoY = this.velocidadeDeDeslocamento;
            ImageIcon carregador_s = new ImageIcon("recursos\\personagem_s.png");
            this.imagem = carregador_s.getImage();
            this.direcao = S_BOTTOM;

        }
        if (codigo == KeyEvent.VK_D) {
            this.deslocamentoX = this.velocidadeDeDeslocamento;

            ImageIcon carregador_d = new ImageIcon("recursos\\personagem_d.png");
            this.imagem = carregador_d.getImage();
            this.direcao = D_RIGTH;
        }
        if (codigo == KeyEvent.VK_A) {
            this.deslocamentoX = -this.velocidadeDeDeslocamento;
            ImageIcon carregador_a = new ImageIcon("recursos\\personagem_a.png");
            this.imagem = carregador_a.getImage();
            this.direcao = A_LEFT;
        }

    }

    // PARA O MOVIMENTO DO PERSONAGEM
    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            this.deslocamentoY = 0;

        } else if (codigo == KeyEvent.VK_S) {
            this.deslocamentoY = 0;

        } else if (codigo == KeyEvent.VK_D) {
            this.deslocamentoX = 0;

        } else if (codigo == KeyEvent.VK_A) {
            this.deslocamentoX = 0;

        }

    }

    // DETECTA A TECLA APERTADA E ATIRA PARA A DIRECAO CORRESPONDENTE
    public void atirar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (this.direcao == Personagem.D_RIGTH) {

            int Posicao_inicial_tiroX = this.posicaoX + (this.larguraImagem / 2) + 8;
            int Posicao_inicial_tiroY = this.posicaoY + (this.alturaImagem / 2) - 20;
            Tiro tiro = new Tiro(Posicao_inicial_tiroX, Posicao_inicial_tiroY, this.direcao);
            this.tiros.add(tiro);
        } else if (this.direcao == Personagem.W_TOP) {

            int Posicao_inicial_tiroX = this.posicaoX + (this.larguraImagem / 2) - 2;
            int Posicao_inicial_tiroY = this.posicaoY + (this.alturaImagem / 2) + 2;
            Tiro tiro = new Tiro(Posicao_inicial_tiroX, Posicao_inicial_tiroY, this.direcao);
            this.tiros.add(tiro);
        } else if (this.direcao == Personagem.A_LEFT) {
            int Posicao_inicial_tiroX = this.posicaoX + (this.larguraImagem / 2) - 8;
            int Posicao_inicial_tiroY = this.posicaoY + (this.alturaImagem / 2) - 20;
            Tiro tiro = new Tiro(Posicao_inicial_tiroX, Posicao_inicial_tiroY, this.direcao);
            this.tiros.add(tiro);
        } else if (this.direcao == Personagem.S_BOTTOM) {
            int Posicao_inicial_tiroX = this.posicaoX + (this.larguraImagem / 2) - 8;
            int Posicao_inicial_tiroY = this.posicaoY + (this.alturaImagem / 2) - 2;
            Tiro tiro = new Tiro(Posicao_inicial_tiroX, Posicao_inicial_tiroY, this.direcao);
            this.tiros.add(tiro);
        }

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

}
