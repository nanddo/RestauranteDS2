package ds2.equipe1.restaurante.modelos;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Th on 24/03/2016.
 */

public class Comanda extends Model<Comanda> {

    private ArrayList< Pedido > pedidos = new ArrayList< Pedido >();
    private String data;
    private boolean ativa = false;
    private float total = 0;
    private String cliente;

    public Comanda(Context context, String nome, Pedido primeiro){
        super(context);
        Date agora = new Date();
        pedidos.add(primeiro);
        data = agora.toString();
        cliente = nome;
        ativa = true;
        total += primeiro.getCusto();
    }

    public void addPedido(Pedido outro){
        pedidos.add(outro);
        total += outro.getCusto();
    }

    public ArrayList< Pedido > getPedidos(){
        return pedidos;
    }

    public Pedido getPedido(int indice){
        return pedidos.get(indice);
    }

    public void removerPedido(Pedido pedido){
        pedidos.remove(pedido);
    }

    public boolean estaAtiva(){
        return ativa;
    }

    public void desativar(){
        ativa = false;
    }

    public float getTotal(){
        return total;
    }

}
