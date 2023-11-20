package ifpr.jogo.servico;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ifpr.jogo.controle.FaseEntidade;
import ifpr.jogo.dao.FaseEntDao;
import ifpr.jogo.dao.FaseEntDaoImpl;


@Entity
@Table(name = "tb_fase_servico")
public class FaseServico {

    @Column(name = "pausar_fase")
    public static boolean pausarFase = false;

    public static void pausarFase(){
        if(pausarFase == false){
            pausarFase = true;

        } else{
            pausarFase = false;

        }
    }

    // -------------------------------- DAO --------------------------------
    private static FaseEntDao dao = new FaseEntDaoImpl();

    public static List<FaseEntidade> buscarTodos() {
        return dao.buscarTodos();
    }

    public static FaseEntidade buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(FaseEntidade faseEnt) {
        dao.inserir(faseEnt);
    }

    public static void atualizar(FaseEntidade faseEnt) {
        dao.atualizar(faseEnt);
    }

    public static void excluir(FaseEntidade faseEnt) {
        dao.excluir(faseEnt);
    }

}

