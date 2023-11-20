package ifpr.jogo.dao;

import java.util.List;
import ifpr.jogo.modelo.servivos.Personagem;

public interface PersonagemDao {

    public List<Personagem> buscarTodos();
    public Personagem buscarPorId(Integer id);
    public void atualizar(Personagem personagem);
    public void excluir(Personagem personagem);
    public void inserir(Personagem personagem);
}