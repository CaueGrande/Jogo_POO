package ifpr.jogo.servico;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ifpr.jogo.controle.Fase;
import ifpr.jogo.dao.FaseDao;
import ifpr.jogo.dao.FaseDaoImpl;


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
    private static FaseDao dao = new FaseDaoImpl();

    public static List<Fase> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Fase buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Fase fase) {
        dao.inserir(fase);
    }

    public static void atualizar(Fase fase) {
        dao.atualizar(fase);
    }

    public static void excluir(Fase fase) {
        dao.excluir(fase);
    }

}

