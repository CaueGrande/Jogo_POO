package ifpr.jogo.dao;

import java.util.List;

import ifpr.jogo.controle.Fase;

public interface FaseDao {

    public List<Fase> buscarTodos();
    public Fase buscarPorId(Integer id);
    public void atualizar(Fase fase);
    public void excluir(Fase fase);
    public void inserir(Fase fase);
}