package ds2.equipe1.restaurante.modelos;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Th on 24/03/2016.
 */

public class Mesa extends Model<Mesa> {
    private int numero;
    private ArrayList<Comanda> comandas = new ArrayList< Comanda >();

    public Mesa(Context context, int numero){
        super(context);
        //inserir chamada do SQL para carregar comandas ativas
        this.numero = numero;
    }

    public void addComanda(Comanda nova){
        comandas.add(nova);
    }

    public ArrayList< Comanda > getComandas(){
        return comandas;
    }

    public ArrayList < Comanda > getComandasAtivas(){
        ArrayList < Comanda > ativas = new ArrayList < Comanda >();
        for (int i = 0; i < comandas.size(); i++) {
            Comanda atual = comandas.get(i);
            if(atual.estaAtiva()){
                ativas.add(atual);
            }
        }
        return ativas;
    }

    public Comanda getComanda(int indice){
        return comandas.get(indice);
    }

}
