package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;

public class SuperTiro extends AbstractPosicionamento{
    private static int VELOCIDADE = 5;

    private boolean visivel;

    private int direcao;

    // CONSTRUTOR
    public SuperTiro(int posicaoPersonagemX, int posicaoPersonagemY, int direcao) {
        super.setPosicaoX(posicaoPersonagemX);
        super.setPosicaoY(posicaoPersonagemY);
        this.direcao = direcao;
        this.visivel = true;
        carregar();
    }

    // CARREGA A IMAGEM DOS SUPER TIROS
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\granada.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));

    }

    // MOVE A IMAGEM DO SUPER TIRO
    public void atualizar() {

        // MUDA A DIRECAO DO SUPER TIRO DE ACORDO COM A TECLA SENDO APERTADA PARA MOVER O PERSONAGEM
        if(direcao == Personagem.TECLA_W) {
            super.setPosicaoY(super.getPosicaoY() - VELOCIDADE);

        } else if(direcao == Personagem.TECLA_S) {
            super.setPosicaoY(super.getPosicaoY() + VELOCIDADE);
            
        } else if(direcao == Personagem.TECLA_D) {
            super.setPosicaoX(super.getPosicaoX() + VELOCIDADE);

        } else if(direcao == Personagem.TECLA_A) {
            super.setPosicaoX(super.getPosicaoX() - VELOCIDADE);
            
        } 
    }

    // GETTERS E SETTERS
    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }
}