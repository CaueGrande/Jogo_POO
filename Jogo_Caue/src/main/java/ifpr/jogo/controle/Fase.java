package ifpr.jogo.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public abstract class Fase extends JPanel  implements KeyListener, ActionListener  {

    FaseEntidade faseEntidade;

    public Fase(){
        faseEntidade = new FaseEntidade();
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
