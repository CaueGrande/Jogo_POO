package ifpr.jogo.dao;

import java.util.List;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;

public interface LoboDao {

    public List<Lobo> buscarTodos();
    public Lobo buscarPorId(Integer id);
    public void atualizar(Lobo lobo);
    public void excluir(Lobo lobo);
    public void inserir(List<Lobo> Lobos);
}