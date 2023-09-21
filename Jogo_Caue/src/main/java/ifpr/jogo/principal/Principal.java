package main.java.ifpr.jogo.principal;

import javax.swing.JFrame;

import main.java.ifpr.jogo.modelo.Fase;
import main.java.ifpr.jogo.util.AbstractConstantes;

public class Principal extends JFrame{

    public Principal() {
        Fase fase = new Fase();
        super.add(fase);

        super.setTitle("Savanna Survivor");
        super.setVisible(true);
        super.setSize(AbstractConstantes.LARGURA_JANELA, AbstractConstantes.ALTURA_JANELA);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   public static void main(String[] args) {
        new Principal();
    }
}
