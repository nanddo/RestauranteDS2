package ds2.equipe1.restaurante.controles;

import android.content.Context;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeFuncionario {
    private ControleDeImpressao controleDeImpressao;

    public ControleDeFuncionario(Context context){
        controleDeImpressao = new ControleDeImpressao(context);
        
    }
}
