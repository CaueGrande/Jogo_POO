package br.ifpr.jogo.principal;

import javax.swing.JFrame;
import br.ifpr.jogo.modelo.Fase;

public class Principal extends JFrame {
    
    public final int LARGURA_JANELA = 1290;
    public final int ALTURA_JANELA = 1080;

    // CONSTRUTOR
    public Principal() {
        Fase fase = new Fase();
        super.add(fase);

        super.setVisible(true);
        super.setSize(LARGURA_JANELA, ALTURA_JANELA);
        super.setTitle("Savanna Survivor");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   public static void main(String[] args) {
        new Principal();
    }
}
