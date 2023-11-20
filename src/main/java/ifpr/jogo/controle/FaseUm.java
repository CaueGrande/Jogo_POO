package ifpr.jogo.controle;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import ifpr.jogo.modelo.paisagem.Animal;
import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;
import ifpr.jogo.modelo.tiros.SuperTiro;
import ifpr.jogo.modelo.tiros.Tiro;
import ifpr.jogo.servico.FaseServico;
import ifpr.jogo.servico.LoboServico;
import ifpr.jogo.servico.PersonagemServico;
import ifpr.jogo.util.AbstractConstantes;

public class FaseUm extends Fase{

    public FaseUm() {
        super();

        // CARREGA A IMAGEM INICIAL DA FASE
        ImageIcon carregarFundo = new ImageIcon(getClass().getResource("/fundo.png"));
        faseEntidade.setFundo(carregarFundo.getImage());

        faseEntidade.setFundo(faseEntidade.getFundo().getScaledInstance(AbstractConstantes.LARGURA_JANELA, AbstractConstantes.ALTURA_JANELA,
                Image.SCALE_FAST));
        faseEntidade.setAlturaImagemFundo(faseEntidade.getFundo().getHeight(null));
        faseEntidade.setLarguraImagemFundo(faseEntidade.getFundo().getWidth(null));

        // CRIA O PERSONAGEM NA FASE
        faseEntidade.setPersonagem(new Personagem());
        faseEntidade.getPersonagem().carregar();
        PersonagemServico.inserir(faseEntidade.getPersonagem());


        // ADICIONA O DELAY NA FASE
        faseEntidade.setTimer(new Timer(AbstractConstantes.DELAY, this));
        faseEntidade.getTimer().start();

        // CRIA OS LOBOS E ABELHAS NA FASE
        faseEntidade.setLobos(new ArrayList<>());
        LoboServico.inserir(faseEntidade.getLobos());

        faseEntidade.setAbelhas(new ArrayList<>());

        ImageIcon carregarFimJogo = new ImageIcon(getClass().getResource("/gameover.png"));
        faseEntidade.setFimDeJogo(carregarFimJogo.getImage());

        FaseServico.inserir(faseEntidade);
    }

    // DESENHA AS IMAGENS NA TELA
    public void paint(Graphics graphics) {
        Graphics2D graficos = (Graphics2D) graphics;

        ArrayList<Tiro> tiros = faseEntidade.getPersonagem().getTiros();
        ArrayList<SuperTiro> superTiros = faseEntidade.getPersonagem().getSuperTiros();

        // IMAGEM DE FUNDO
        graficos.drawImage(faseEntidade.getFundo(), 0, 0, null);

        // IMAGEM DOS TIROS
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoX(), tiro.getPosicaoY(), null);
        }

        // IMAGEM DO SUPER TIRO
        for (int J = 0; J < superTiros.size(); J++) {
            SuperTiro superTiro = superTiros.get(J);

            if (superTiro.getExplodido() == false) {
                superTiro.carregar();
            } else {
                superTiro.carregarExplodido();
            }
            graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoX(), superTiro.getPosicaoY(), null);
        }

        // IMAGEM DO PERSONAGEM
        graficos.drawImage(faseEntidade.getPersonagem().getImagem(), faseEntidade.getPersonagem().getPosicaoX(), faseEntidade.getPersonagem().getPosicaoY(), this);

        // IMAGEM DOS LOBOS
        for (Lobo lobo : faseEntidade.getLobos()) {
            graficos.drawImage(lobo.getImagem(), lobo.getPosicaoX(), lobo.getPosicaoY(), null);
        }

        // IMAGEM DAS ABELHAS
        for (Animal abelha : faseEntidade.getAbelhas()) {
            graficos.drawImage(abelha.getImagem(), abelha.getPosicaoX(), abelha.getPosicaoY(), null);
        }

        // DESENHA A STRING "PONTOS:" NA TELA
        this.desenhaPontuacao(graficos);
        // DESENHA A STRING "VIDAS:" NA TELA
        this.desenhaVida(graficos);

        if (faseEntidade.getPersonagem().getVida() == 0) {
            int x = (AbstractConstantes.LARGURA_JANELA / 2) - 250;
            int y = (AbstractConstantes.ALTURA_JANELA / 2) - 400;
            // IMAGEM DE GAMEOVER
            graficos.drawImage(faseEntidade.getFimDeJogo(), x, y, null);
        }

        graphics.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // ACOES AO APERTAR TECLA
    @Override
    public void keyPressed(KeyEvent e) {

        // PAUSA A FASE
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            FaseServico.pausarFase();
        }

        // SALVA A FASE
        if (e.getKeyCode() == KeyEvent.VK_L) {
            PersonagemServico.inserir(faseEntidade.getPersonagem());
            LoboServico.inserir(faseEntidade.getLobos());

            FaseServico.inserir(faseEntidade);
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            String idDigitado = JOptionPane.showInputDialog("Digite seu ID:");

            if (idDigitado != null && ! idDigitado.isEmpty()) {
                int id = Integer.parseInt(idDigitado);
                faseEntidade.setPersonagem(PersonagemServico.buscarPorId(id));
                faseEntidade.getPersonagem().carregar();
                //faseEntidade.setLobos(LoboServico.buscarPorId(id,faseEntidade.getLobos()));
                //faseEntidade.getLobos().carregar();
            }
        }

        // TEMPORIZADORES EM MILISEGUNDOS
        faseEntidade.setContaTempoTiros(faseEntidade.getContaTempoTiros() + 1);
        faseEntidade.setContaTempoSuperTiros(faseEntidade.getContaTempoSuperTiros() + 1);

        // SE O TEMPORIZADOR CHEGAR NO TEMPO DEFINIDO, E DADO PERMISSAO PARA ATIRAR
        if (faseEntidade.getContaTempoTiros() >= AbstractConstantes.TEMPO_SPAWN_TIROS) {
            faseEntidade.setPodeAtirar(true);

        }
        // SE TIVER PERMISSAO, ATIRA
        if (faseEntidade.getPodeAtirar() == true) {

            // ATIRA COM O ESPACO
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                faseEntidade.getPersonagem().atirar();

                // VOLTA AS VARIAVEIS AO PADRAO
                faseEntidade.setContaTempoTiros(0);
                faseEntidade.setPodeAtirar(false);
            }

        }

        // SE O TEMPORIZADOR CHEGAR NO TEMPO DEFINIDO, E DADO PERMISSAO PARA SUPER
        // ATIRAR
        if (faseEntidade.getContaTempoSuperTiros() >= AbstractConstantes.TEMPO_SPAWN_SUPER_TIROS) {
            faseEntidade.setPodeSuperAtirar( true);
        }

        // SE TIVER PERMISSAO, SUPER ATIRA
        if (faseEntidade.getPodeSuperAtirar() == true) {

            // DA O SUPER TIRO COM A TECLA Q
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                faseEntidade.getPersonagem().superAtirar();

                // VOLTA AS VARIAVEIS AO PADRAO
                faseEntidade.setContaTempoSuperTiros(0);
                faseEntidade.setPodeSuperAtirar( false);
            }

        }

        // MOVE O PERSONAGEM
        if (faseEntidade.getPersonagem().getVida() > 0) {
            faseEntidade.getPersonagem().mover(e);
        }

    }

    // ACOES AO SOLTAR TECLA
    @Override
    public void keyReleased(KeyEvent e) {
        // PARA O PERSONAGEM
        faseEntidade.getPersonagem().parar(e);
    }

    // ATIVA AS ACOES
    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();

        if (faseEntidade.getPersonagem().getVida() > 0 && FaseServico.pausarFase == false) {
            faseEntidade.getPersonagem().atualizar();

            // CONTAGENS DE TEMPO
            if (faseEntidade.getContaTempoAnimais() < AbstractConstantes.TEMPO_SPAWN_ANIMAIS) {
                faseEntidade.setContaTempoAnimais(faseEntidade.getContaTempoAnimais() + 1);
            }
            if (faseEntidade.getContaTempoLobos() < AbstractConstantes.TEMPO_SPAWN_INIMIGOS) {
                faseEntidade.setContaTempoLobos(faseEntidade.getContaTempoLobos() + 1);
            }
            if (faseEntidade.getContaTempoTiros() < AbstractConstantes.TEMPO_SPAWN_TIROS) {
                faseEntidade.setContaTempoTiros(faseEntidade.getContaTempoTiros() + 1);
            }
            if (faseEntidade.getContaTempoSuperTiros() < AbstractConstantes.TEMPO_SPAWN_SUPER_TIROS) {
                faseEntidade.setContaTempoSuperTiros(faseEntidade.getContaTempoSuperTiros() + 1);
            }

            ArrayList<Tiro> tiros = faseEntidade.getPersonagem().getTiros();
            Iterator<Tiro> iteratorTiro = tiros.iterator();

            ArrayList<SuperTiro> superTiros = faseEntidade.getPersonagem().getSuperTiros();
            Iterator<SuperTiro> iteratorSuperTiro = superTiros.iterator();

            // PASSA PELOS TIROS QUE ESTIVEREM NA LISTA
            while (iteratorTiro.hasNext()) {
                Tiro tiro = iteratorTiro.next();

                // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL,
                // REMOVE OS TIROS
                if (tiro.getVisivel() == true) {
                    tiro.atualizar();
                } else {
                    iteratorTiro.remove();
                }
            }

            // SO SPAWNA O SUPER TIRO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_SUPER_TIROS
            while (iteratorSuperTiro.hasNext()) {
                SuperTiro superTiro = iteratorSuperTiro.next();

                if (superTiro.getExplodido() == true) {
                    faseEntidade.setContaTempoExplosao(faseEntidade.getContaTempoExplosao() + 1);
                }

                // VAI ATUALIZANDO AS POSICOES ENQUANTO ESTIVER VISIVEL, SE NAO ESTIVER VISIVEL,
                // REMOVE OS SUPER TIROS
                if (superTiro.getVisivel() == true && superTiro.getExplodido() == false) {
                    superTiro.atualizar();
                } else if (superTiro.getVisivel() == false) {
                    iteratorSuperTiro.remove();
                }
            }

            // SO SPAWNA O ANIMAL APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_ANIMAIS
            if (faseEntidade.getContaTempoAnimais() >= AbstractConstantes.TEMPO_SPAWN_ANIMAIS) {
                int posicaoXFim = AbstractConstantes.LARGURA_JANELA + 200;
                int posicaoYAleatoria = random.nextInt(AbstractConstantes.ALTURA_JANELA);

                Animal novaAbelha = new Animal(posicaoXFim, posicaoYAleatoria);
                novaAbelha.carregar();
                faseEntidade.getAbelhas().add(novaAbelha);

                faseEntidade.setContaTempoAnimais(0);
            }

            Iterator<Animal> iteratorAbelha = faseEntidade.getAbelhas().iterator();
            // ATUALIZA A POSICAO NA FASE OU REMOVE OS ANIMAIS
            while (iteratorAbelha.hasNext()) {
                Animal abelha = iteratorAbelha.next();

                // ENQUANTO O ANIMAL ESTIVER VISIVEL, ATUALIZA A MOVIMENTACAO
                if (abelha.getVisivel() == true) {
                    abelha.atualizar();

                    if (abelha.getPosicaoX() <= -50) {
                        abelha.setVisivel(false);
                    }
                } else {
                    iteratorAbelha.remove();
                }
            }

            // SO SPAWNA O INIMIGO APOS O TEMPO DA VARIAVEL TEMPO_SPAWN_INIMIGOS
            if (faseEntidade.getContaTempoLobos() >= AbstractConstantes.TEMPO_SPAWN_INIMIGOS) {
                LoboServico.gerarLobos(faseEntidade.getLobos(), faseEntidade.getPersonagem());
                faseEntidade.setContaTempoLobos(0);
            }

            Iterator<Lobo> iteratorLobo = faseEntidade.getLobos().iterator();
            // ATUALIZA A POSICAO NA FASE OU REMOVE OS LOBOS
            while (iteratorLobo.hasNext()) {
                Lobo lobo = iteratorLobo.next();
                Rectangle formaLobo = lobo.getRectangle();

                // ENQUANTO O LOBO ESTIVER VISIVEL, ATUALIZA A MOVIMENTACAO
                if (lobo.getVisivel() == true) {
                    lobo.atualizar();

                    // REMOVE OS LOBOS AO COLIDIR COM O PERSONAGEM
                    if (faseEntidade.getPersonagem().getRectangle().intersects(lobo.getRectangle())) {
                        lobo.setVisivel(false);
                        faseEntidade.getPersonagem().setVida(faseEntidade.getPersonagem().getVida() - 1);
                    }

                    // REMOVE LOBOS E TIROS AO COLIDIR COM TIROS E SUPER TIROS
                    for (Tiro tiro : faseEntidade.getPersonagem().getTiros()) {
                        Rectangle formaTiro = tiro.getRectangle();
                        if (formaTiro.intersects(formaLobo)) {
                            lobo.setVisivel(false);
                            tiro.setVisivel(false);
                            faseEntidade.getPersonagem().setPontuacao(faseEntidade.getPersonagem().getPontuacao() + Lobo.PONTUACAO_POR_LOBO);
                        }
                    }
                    for (SuperTiro supertiro : faseEntidade.getPersonagem().getSuperTiros()) {
                        Rectangle formaSuperTiro = supertiro.getRectangle();

                        if (formaSuperTiro.intersects(formaLobo)) {
                            lobo.setVisivel(false);
                            supertiro.explodir();

                            faseEntidade.getPersonagem().setPontuacao(faseEntidade.getPersonagem().getPontuacao() + Lobo.PONTUACAO_POR_LOBO);
                        }

                        if (faseEntidade.getContaTempoExplosao() >= 20) {
                            supertiro.setVisivel(false);
                            faseEntidade.setContaTempoExplosao(0);
                        }
                    }

                } else {
                    // SE O LOBO NAO ESTIVER VISIVEL ELE E REMOVIDO
                    iteratorLobo.remove();
                }
            }

            LoboServico.aumentaVelocidadeLobos(faseEntidade.getPersonagem());

            // VAI PINTANDO AS IMAGENS DE ACORDO COM O DELAY DA FASE
            repaint();
        }
    }

    // PONTUACAO DO PERSONAGEM
    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + faseEntidade.getPersonagem().getPontuacao();
        Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22);
        FontMetrics metrics = graficos.getFontMetrics(fonte);
        int larguraTexto = metrics.stringWidth(textoPontuacao);
        int alturaTexto = metrics.getHeight();

        // DESENHA O FUNDO BRANCO DO TEXTO
        graficos.setColor(Color.WHITE);
        graficos.fillRect(20, 5, larguraTexto + 10, alturaTexto);

        // DESENHA O TEXTO "PONTOS:"
        graficos.setFont(fonte);
        graficos.setColor(Color.BLACK);
        graficos.drawString(textoPontuacao, 20, 25);
    }

    // VIDA DO PERSONAGEM
    public void desenhaVida(Graphics2D graficos) {
        String textoVida = "VIDAS: " + faseEntidade.getPersonagem().getVida();
        Font fonte = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22);
        FontMetrics metrics = graficos.getFontMetrics(fonte);
        int larguraTexto = metrics.stringWidth(textoVida);
        int alturaTexto = metrics.getHeight();

        // DESENHA O FUNDO BRANCO DO TEXTO
        graficos.setColor(Color.WHITE);
        graficos.fillRect((AbstractConstantes.LARGURA_JANELA - 120), 5, larguraTexto + 10, alturaTexto);

        // DESENHA O TEXTO "VIDA:"
        graficos.setFont(fonte);
        graficos.setColor(Color.BLACK);
        graficos.drawString(textoVida, (AbstractConstantes.LARGURA_JANELA - 120), 25);
    }
}
