package ifpr.jogo.dao;

import java.util.List;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;

public interface LoboDao {

    public List<Lobo> buscarTodos();
    public List<Lobo> buscarPorId(Integer id, List<Lobo> lobos);
    public void atualizar(Lobo lobo);
    public void excluir(Lobo lobo);
    public void inserir(List<Lobo> lobos);
}