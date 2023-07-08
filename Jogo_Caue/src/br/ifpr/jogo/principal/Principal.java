package br.ifpr.jogo.principal;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import br.ifpr.jogo.modelo.Fase;

public class Principal extends JFrame {
    
    public Dimension tamanhoTela;
    public final int LARGURA_JANELA;
    public final int ALTURA_JANELA;


    // CONSTRUTOR
    public Principal() {
        Fase fase = new Fase();
        super.add(fase);
        
        // AJUSTA O TAMANHO DA JANELA DO JOGO DE ACORDO COM O MONITOR
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        LARGURA_JANELA = (int) tamanhoTela.getWidth();
        ALTURA_JANELA = (int) tamanhoTela.getHeight();

        super.setTitle("Savanna Survivor");
        super.setVisible(true);
        super.setSize(LARGURA_JANELA, ALTURA_JANELA);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   public static void main(String[] args) {
        new Principal();
    }
}
