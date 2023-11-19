package ifpr.jogo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ifpr.jogo.conexao.HibernateUtil;
import ifpr.jogo.controle.Fase;

public class FaseDaoImpl implements FaseDao {
    private Session sessao;

    public FaseDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Fase> buscarTodos() {
        Query<Fase> query = this.sessao.createQuery("from Fase",
        Fase.class);
            List<Fase> personagens = query.getResultList();
            return personagens;
    }

    @Override
    public Fase buscarPorId(Integer id) {
        return this.sessao.find(Fase.class, id);
    }

    @Override
    public void inserir(Fase fase) {
        try {
            sessao.beginTransaction();
            sessao.persist(fase);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Fase fase) {
        try {
            sessao.beginTransaction();
            sessao.remove(fase);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Fase fase) {
        try{
            sessao.beginTransaction();
            sessao.persist(fase);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}