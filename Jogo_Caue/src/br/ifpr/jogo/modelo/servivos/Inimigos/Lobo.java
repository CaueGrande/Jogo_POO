package br.ifpr.jogo.modelo.servivos.Inimigos;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.AbstractVida;
import br.ifpr.jogo.modelo.InterfaceTela;
import br.ifpr.jogo.modelo.servivos.Personagem;

public class Lobo extends AbstractVida implements InterfaceTela{

    private Personagem personagem;
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
        ImageIcon carregador = new ImageIcon("recursos\\lobo_s.png");
        super.setImagem(carregador.getImage());
        super.setAlturaImagem(getImagem().getWidth(null));
        super.setLarguraImagem(getImagem().getHeight(null)); 
    }

    @Override
    public void atualizar() {
        int personagemX = personagem.getPosicaoX() - (personagem.getLarguraImagem() / 2);
        int personagemY = personagem.getPosicaoY() - (personagem.getAlturaImagem() / 2);
        
        // REGISTRA A DIFERENCA ENTRE A POSICAO DO PERSONAGEM E DO LOBO
        int deltaX = personagemX - super.getPosicaoX();
        int deltaY = personagemY - super.getPosicaoY();
        // FOI CRIADO ESSA VARIAVEL COMPARATORIA POIS
        // COMPARAR AS VARIAVEIS DE POSICAO X E Y DO PERSONAGEM COM A DO LOBO ESTAVA BUGANDO GERANDO BUG

        // MOVE O LOBO DE ACORDO COM A DIFERENCA DE POSICAO
        if(deltaY > 0){
            super.setPosicaoY(super.getPosicaoY() + super.getVELOCIDADE());
        } else if(deltaY < 0){
            super.setPosicaoY(super.getPosicaoY() - super.getVELOCIDADE());
        }
        if(deltaX > 0){
            super.setPosicaoX(super.getPosicaoX() + super.getVELOCIDADE());
        }else if(deltaX < 0){
            super.setPosicaoX(super.getPosicaoX() - super.getVELOCIDADE());
        }
        

    
    }

    /*  TENTEI CRIAR ESSE METODO PARA NAO FICAR TUDO ISSO NA CLASSE FASE, DEU ERRADO
    public void spawnaLobo(){
            Random random = new Random();
            int posicaoXInicio = -200;
            int posicaoYInicio = -200;
            int posicaoXAleatoria = random.nextInt(LARGURA_JANELA);
            int posicaoYAleatoria = random.nextInt(ALTURA_JANELA);  

            Lobo novoLoboCima = new Lobo(posicaoXAleatoria, posicaoYInicio, this.personagem);
            Lobo novoLoboEsquerda = new Lobo(posicaoXInicio, posicaoYAleatoria, this.personagem);
            novoLoboCima.carregar();
            novoLoboEsquerda.carregar();
    }*/


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
