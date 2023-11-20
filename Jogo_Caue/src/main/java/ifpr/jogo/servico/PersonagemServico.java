package ifpr.jogo.servico;

import ifpr.jogo.modelo.servivos.Personagem;
import ifpr.jogo.dao.PersonagemDao;
import ifpr.jogo.dao.PersonagemDaoImpl;
import java.util.List;

public class PersonagemServico {
    private static PersonagemDao dao = new PersonagemDaoImpl();

    public static List<Personagem> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Personagem buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Personagem personagem) {
        dao.inserir(personagem);
    }

    public static void atualizar(Personagem personagem) {
        dao.atualizar(personagem);
    }

    public static void excluir(Personagem personagem) {
        dao.excluir(personagem);
    }

   
}
