package ifpr.jogo.modelo.paisagem;

import javax.swing.ImageIcon;

import ifpr.jogo.modelo.AbstractPosicionamento;

public class Animal extends AbstractPosicionamento{
    private final int TEMPO_SPAWN_ANIMAIS = 400;

    public Animal(int posicaoX, int posicaoY) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVELOCIDADE(2);
        super.setVisivel(true);
    }

    @Override
    public void carregar() {
            ImageIcon carregador = new ImageIcon(getClass().getResource("/abelha.png"));
            super.setImagem(carregador.getImage());
            super.setAlturaImagem(getImagem().getHeight(null));
            super.setLarguraImagem(getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoX(super.getPosicaoX() - super.getVELOCIDADE());
    }
    
    public int getTEMPO_SPAWN_ANIMAIS() {
        return TEMPO_SPAWN_ANIMAIS;
    }
}
