package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.content.Intent;

import ds2.equipe1.restaurante.TelaRelatorios;

/**
 * Created by Fernando on 28/03/2016.
 */
public class ControleDeImpressao {
    private Context context;

    public ControleDeImpressao(Context context) {
        this.context = context;
    }

    public void imprimir(String dados){
        Intent i = new Intent(context, TelaRelatorios.class);
        i.putExtra("dados", dados);
        context.startActivity(i);
    }
}
