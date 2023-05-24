package br.ifpr.jogo.principal;
import javax.swing.JFrame;


public class Principal extends JFrame {
    
    public Principal(){
        super.setVisible(true);
        super.setSize(500,500);
        super.setTitle("Meu jogo");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

    public static void main(String[] args){
        Principal principal = new Principal();
    }
}
