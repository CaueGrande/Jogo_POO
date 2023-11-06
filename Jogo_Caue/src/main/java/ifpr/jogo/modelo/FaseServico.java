package ifpr.jogo.modelo;

public class FaseServico {

    public static boolean pausarFase = false;

    public static void pausarFase(){
        if(pausarFase == false){
            pausarFase = true;

        } else{
            pausarFase = false;

        }
    }

}

