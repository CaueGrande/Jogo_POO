package br.ifpr.jogo.principal;

import javax.swing.JFrame;
import br.ifpr.jogo.modelo.Fase;
import br.ifpr.jogo.modelo.InterfaceTela;

public class Principal extends JFrame implements InterfaceTela{

    public Principal() {
        Fase fase = new Fase();
        super.add(fase);

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
