package ifpr.jogo.visao;

import javax.swing.JFrame;

import org.hibernate.Session;

import ifpr.jogo.conexao.HibernateUtil;
import ifpr.jogo.controle.FaseUm;
import ifpr.jogo.util.AbstractConstantes;

public class Principal extends JFrame{

    public Principal() {
        FaseUm faseUm = new FaseUm();
        super.add(faseUm);
        
        super.setTitle("Savanna Survivor");
        super.setVisible(true);
        super.setSize(AbstractConstantes.LARGURA_JANELA, AbstractConstantes.ALTURA_JANELA);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   public static void main(String[] args) {
        Session sessao = HibernateUtil.getSession(); 
        new Principal();
    }
}
