package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;

public class FimDeJogo extends AbstractPosicionamento implements InterfaceTela{

    public FimDeJogo() {
        super.setPosicaoX(LARGURA_JANELA/2);
        super.setPosicaoY(ALTURA_JANELA/2);
        super.setVisivel(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\gameover.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getWidth(null));
        super.setLarguraImagem(getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        // SERA IMPLEMENTADO QUANDO FOR DISPONIBILIZADO OPCOES DE RECOMECAR OU VOLTAR AO MENU
    }
}
