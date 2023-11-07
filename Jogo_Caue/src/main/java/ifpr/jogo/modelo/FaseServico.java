package ifpr.jogo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}

