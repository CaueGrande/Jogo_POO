package ifpr.jogo.servico;

import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;
import ifpr.jogo.util.AbstractConstantes;

@Entity
@Table(name = "tb_lobo_servico")
public class LoboServico {

    @Transient      
    private static final int POSICAO_INICIAL_X = -200;
    @Transient
    private static final int POSICAO_INICIAL_Y = -200;
    @Transient
    private static final int POSICAO_FINAL_X = (int) AbstractConstantes.LARGURA_JANELA;
    @Transient
    private static final int POSICAO_FINAL_Y = (int) AbstractConstantes.ALTURA_JANELA;

    @Column(name="pode_aumentar_velocidade")
    private static boolean podeAumentarVelocidade = false;

    public static void gerarLobos(List<Lobo> lobos, Personagem personagem) {

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

    public static void aumentaVelocidadeLobos(Personagem personagem) {
        if (personagem.getPontuacao() % 2000 != 0 && podeAumentarVelocidade != true) {
            podeAumentarVelocidade = true;
        }

        if (personagem.getPontuacao() % 2000 == 0 && podeAumentarVelocidade == true) {
            Lobo.velocidadeLobos++;
            podeAumentarVelocidade = false;
        }
    }

}
