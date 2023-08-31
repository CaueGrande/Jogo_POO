package br.ifpr.jogo.modelo;

import java.awt.Dimension;
import java.awt.Toolkit;

public interface InterfaceTela {
    // PEGA O TAMANHO DA JANELA DO JOGO DE ACORDO COM O MONITOR
    public Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
    public final int LARGURA_JANELA = (int) tamanhoTela.getWidth();
    public final int ALTURA_JANELA = (int) tamanhoTela.getHeight();
}
