package ifpr.jogo.dao;

import java.util.List;

import ifpr.jogo.controle.FaseEntidade;

public interface FaseEntDao {

    public List<FaseEntidade> buscarTodos();
    public FaseEntidade buscarPorId(Integer id);
    public void atualizar(FaseEntidade faseEnt);
    public void excluir(FaseEntidade faseEnt);
    public void inserir(FaseEntidade faseEnt);
}