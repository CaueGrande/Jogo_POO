package br.ifpr.jogo.util;

import java.awt.Dimension;
import java.awt.Toolkit;

public abstract class AbstractConstantes {
    // PEGA O TAMANHO DA JANELA DO JOGO DE ACORDO COM O MONITOR
    private static final Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int LARGURA_JANELA = (int) tamanhoTela.getWidth();
    public static final int ALTURA_JANELA = (int) tamanhoTela.getHeight();
}
