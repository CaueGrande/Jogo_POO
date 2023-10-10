package ifpr.jogo.modelo.servivos.inimigos;

import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import ifpr.jogo.modelo.AbstractVida;
import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.util.AbstractConstantes;


public class LoboServico {
    
    private static int POSICAO_INICIAL_X = -200;
    private static int POSICAO_INICIAL_Y = -200;
    private static int POSICAO_FINAL_X = (int) AbstractConstantes.LARGURA_JANELA;
    private static int POSICAO_FINAL_Y = (int) AbstractConstantes.ALTURA_JANELA;
   
    public static void gerarLobos(List lobos, Personagem personagem) {
        Random random = new Random();

        int posicaoXAleatoria = random.nextInt(AbstractConstantes.LARGURA_JANELA);
        Lobo novoLoboCima = new Lobo(posicaoXAleatoria, POSICAO_INICIAL_Y, personagem);

        posicaoXAleatoria = random.nextInt(AbstractConstantes.LARGURA_JANELA);
        Lobo novoLoboBaixo = new Lobo(posicaoXAleatoria, POSICAO_FINAL_Y, personagem);

        int posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA);
        Lobo novoLoboEsquerda = new Lobo(POSICAO_INICIAL_X, posicaoYAleatoria, personagem);

        posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA);
        Lobo novoLoboDireita = new Lobo(POSICAO_FINAL_X, posicaoYAleatoria, personagem);

        novoLoboCima.carregar();
        novoLoboBaixo.carregar();
        novoLoboEsquerda.carregar();
        novoLoboDireita.carregar();

        lobos.add(novoLoboCima);
        lobos.add(novoLoboBaixo);
        lobos.add(novoLoboEsquerda);
        lobos.add(novoLoboDireita);
    }
}
