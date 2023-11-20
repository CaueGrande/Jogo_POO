package ifpr.jogo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ifpr.jogo.conexao.HibernateUtil;
import ifpr.jogo.controle.FaseEntidade;

public class FaseEntDaoImpl implements FaseEntDao {
    private Session sessao;

    public FaseEntDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<FaseEntidade> buscarTodos() {
        Query<FaseEntidade> query = this.sessao.createQuery("from FaseEntidade",
        FaseEntidade.class);
            List<FaseEntidade> personagens = query.getResultList();
            return personagens;
    }

    @Override
    public FaseEntidade buscarPorId(Integer id) {
        return this.sessao.find(FaseEntidade.class, id);
    }

    @Override
    public void inserir(FaseEntidade faseEnt) {
        try {
            sessao.beginTransaction();
            sessao.persist(faseEnt);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(FaseEntidade faseEnt) {
        try {
            sessao.beginTransaction();
            sessao.remove(faseEnt);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(FaseEntidade faseEnt) {
        try{
            sessao.beginTransaction();
            sessao.persist(faseEnt);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}