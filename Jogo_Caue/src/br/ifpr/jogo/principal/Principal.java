package br.ifpr.jogo.principal;

import javax.swing.JFrame;
import br.ifpr.jogo.modelo.Fase;


public class Principal extends JFrame {
    
    public Principal(){
        Fase fase = new Fase();
        super.add(fase);

        super.setVisible(true);
        super.setSize(1280, 1080);
        super.setTitle("Savanna Survivor");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

    public static void main(String[] args){
        Principal principal = new Principal();
    }
}
