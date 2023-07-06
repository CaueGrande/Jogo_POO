package br.ifpr.jogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tiro {
    private int posicaoX;
    private int posicaoY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int VELOCIDADE = 5;

    private boolean visivel;

    private int direcao;

    // CONSTRUTOR
    public Tiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        this.posicaoX = posicaoPersonagemX;
        this.posicaoY = posicaoPersonagemY;
        this.direcao = direcao;
        this.visivel = true;
        carregar();
    }

    // CARREGA A IMAGEM INICIAL DOS TIROS
    public void carregar() {
        if(direcao == Personagem.TECLA_W) {
            ImageIcon carregador = new ImageIcon("recursos\\tiro_w.png");
            this.imagem = carregador.getImage();
            
            this.alturaImagem = this.imagem.getWidth(null);
            this.larguraImagem = this.imagem.getHeight(null);

        }
        if(direcao == Personagem.TECLA_S) {
            ImageIcon carregador = new ImageIcon("recursos\\tiro_s.png");
            this.imagem = carregador.getImage();
        
            this.alturaImagem = this.imagem.getWidth(null);
            this.larguraImagem = this.imagem.getHeight(null);
            
        }
        if(direcao == Personagem.TECLA_D) {
            ImageIcon carregador = new ImageIcon("recursos\\tiro_d.png");
            this.imagem = carregador.getImage();

            this.alturaImagem = this.imagem.getWidth(null);
            this.larguraImagem = this.imagem.getHeight(null);

        }
        if(direcao == Personagem.TECLA_A) {
            ImageIcon carregador = new ImageIcon("recursos\\tiro_a.png");
            this.imagem = carregador.getImage();

            this.alturaImagem = this.imagem.getWidth(null);
            this.larguraImagem = this.imagem.getHeight(null);

        }
        
        
    }

    // MUDA A POSICAO DO TIRO
    public void atualizar() {

        // MUDA A DIRECAO DO TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O PERSONAGEM
        if(direcao == Personagem.TECLA_W) {
            posicaoY -= VELOCIDADE;

        } else if(direcao == Personagem.TECLA_S) {
            posicaoY += VELOCIDADE;
            
        } else if(direcao == Personagem.TECLA_D) {
            posicaoX += VELOCIDADE;

        } else if(direcao == Personagem.TECLA_A) {
            posicaoX -= VELOCIDADE;
            
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
        return VELOCIDADE;
    }

    public void setVelocidade(int velocidade) {
        this.VELOCIDADE = velocidade;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public boolean isVisivel() {
        if (this.posicaoX > 1285 || this.posicaoX < 0 || this.posicaoY > 1085 || this.posicaoY < 0) {
            this.visivel = false;
        } else {
            this.visivel = true;
        }
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

}
