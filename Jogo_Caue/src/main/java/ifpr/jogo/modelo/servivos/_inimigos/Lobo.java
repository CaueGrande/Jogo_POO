package ifpr.jogo.modelo.servivos._inimigos;

import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import ifpr.jogo.modelo.AbstractVida;
import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.util.AbstractConstantes;

@Entity
@Table(name = "tb_lobo")
public class Lobo extends AbstractVida {

    @Transient
    private Personagem personagem;

    @Transient
    Random random = new Random();

    private static int POSICAO_INICIAL_X = -200;
    private static int POSICAO_INICIAL_Y = -200;
    private static int POSICAO_FINAL_X = (int) AbstractConstantes.LARGURA_JANELA;
    private static int POSICAO_FINAL_Y = (int) AbstractConstantes.ALTURA_JANELA;

    public static final int PONTUACAO_POR_LOBO = 100;

    public Lobo(int posicaoX, int posicaoY, Personagem personagem) {
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setVisivel(true);
        super.setVELOCIDADE(1);
        this.personagem = personagem;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource("/lobo_s.png"));
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getHeight(null) - 10);
        super.setLarguraImagem(getImagem().getWidth(null) - 70);
    }

    public void gerarLobos(List lobos) {

        int posicaoXAleatoria = random.nextInt(AbstractConstantes.LARGURA_JANELA);
        Lobo novoLoboCima = new Lobo(posicaoXAleatoria, POSICAO_INICIAL_Y, this.personagem);

        posicaoXAleatoria = random.nextInt(AbstractConstantes.LARGURA_JANELA);
        Lobo novoLoboBaixo = new Lobo(posicaoXAleatoria, POSICAO_FINAL_Y, this.personagem);

        int posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA);
        Lobo novoLoboEsquerda = new Lobo(POSICAO_INICIAL_X, posicaoYAleatoria, this.personagem);

        posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA);
        Lobo novoLoboDireita = new Lobo(POSICAO_FINAL_X, posicaoYAleatoria, this.personagem);

        novoLoboCima.carregar();
        novoLoboBaixo.carregar();
        novoLoboEsquerda.carregar();
        novoLoboDireita.carregar();

        lobos.add(novoLoboCima);
        lobos.add(novoLoboBaixo);
        lobos.add(novoLoboEsquerda);
        lobos.add(novoLoboDireita);
    }

    @Override
    public void atualizar() {
        int personagemX = personagem.getPosicaoX() - (personagem.getLarguraImagem() / 2);
        int personagemY = personagem.getPosicaoY() - (personagem.getAlturaImagem() / 2);

        // REGISTRA A DIFERENCA ENTRE A POSICAO DO PERSONAGEM E DO LOBO
        int deltaX = personagemX - super.getPosicaoX();
        int deltaY = personagemY - super.getPosicaoY();
        // FOI CRIADO ESSA VARIAVEL COMPARATORIA POIS
        // COMPARAR AS VARIAVEIS DE POSICAO X E Y DO PERSONAGEM COM A DO LOBO ESTAVA
        // BUGANDO GERANDO BUG

        // MOVE O LOBO DE ACORDO COM A DIFERENCA DE POSICAO
        if (deltaY > 0) {
            super.setPosicaoY(super.getPosicaoY() + super.getVELOCIDADE());
        } else if (deltaY < 0) {
            super.setPosicaoY(super.getPosicaoY() - super.getVELOCIDADE());
        }
        if (deltaX > 0) {
            super.setPosicaoX(super.getPosicaoX() + super.getVELOCIDADE());
        } else if (deltaX < 0) {
            super.setPosicaoX(super.getPosicaoX() - super.getVELOCIDADE());
        }
    }

    // GETTERS E SETTERS

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public static int getPontuacaoPorLobo() {
        return PONTUACAO_POR_LOBO;
    }
}
