package ifpr.jogo.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ifpr.jogo.conexao.HibernateUtil;
import ifpr.jogo.modelo.servivos.inimigos.Lobo;

public class LoboDaoImpl implements LoboDao {
    private Session sessao;

    public LoboDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Lobo> buscarTodos() {
        Query<Lobo> query = this.sessao.createQuery("from Lobo", Lobo.class);
        List<Lobo> lobos = query.getResultList();
        return lobos;
    }

    @Override
    public Lobo buscarPorId(Integer id) {
        return this.sessao.find(Lobo.class, id);
    }

    @Override
    public void inserir(List<Lobo> lobos) {
        Iterator<Lobo> iterator = lobos.iterator();

        while (iterator.hasNext()) {
            Lobo lobo = iterator.next();

            try {
                sessao.beginTransaction();
                sessao.persist(lobo);
                sessao.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(Lobo lobo) {
        try {
            sessao.beginTransaction();
            sessao.remove(lobo);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Lobo lobo) {
        try {
            sessao.beginTransaction();
            sessao.persist(lobo);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
