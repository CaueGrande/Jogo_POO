package br.ifpr.jogo.principal;
import javax.swing.JFrame;


public class Principal extends JFrame {
    
    public Principal(){
        setVisible(true);
        setSize(1000,1000);
        setTitle("Meu jogo");
        setLocationRelativeTo(null);
}

    public static void main(String[] args){
        Principal principal = new Principal();
    }
}
