package ds2.equipe1.restaurante;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Th on 24/03/2016.
 */

public class Mesa {

    int id;
    private ArrayList< Comanda > comandas = new ArrayList< Comanda >();

    public Mesa(int id){
        this.id = id;
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

    public void apagarComanda(Comanda comanda){
        comandas.remove(comanda);
    }
}
