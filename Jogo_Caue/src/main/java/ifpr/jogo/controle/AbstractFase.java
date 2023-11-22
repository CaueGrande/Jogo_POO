package ifpr.jogo.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import ifpr.jogo.modelo.paisagem.Animal;
import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;

public abstract class AbstractFase extends JPanel  implements KeyListener, ActionListener  {

    FaseEntidade faseEntidade;
    Personagem personagem;
    List<Lobo> lobos;
    List<Animal> abelhas;

    protected Timer timer;

    public AbstractFase(){
        faseEntidade = new FaseEntidade();
        personagem = new Personagem();
        lobos = new ArrayList<>();
        abelhas = new ArrayList<>();

        // LE AS TECLAS APERTADAS
        this.addKeyListener(this);
        
        this.setFocusable(true);
        this.setDoubleBuffered(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);


    
}
