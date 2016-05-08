package ds2.equipe1.restaurante.modelos;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Th on 24/03/2016.
 */

public class Comanda extends Model<Comanda> {

    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private String data;
    private boolean ativa = false;
    private String nome;

    public Comanda(Context context, String nome, Pedido primeiro){
        super(context);
        Date agora = new Date();
        pedidos.add(primeiro);
        data = agora.toString();
        this.nome = nome;
        ativa = true;
    }

    public void addPedido(Pedido outro){
        pedidos.add(outro);
    }

    public void setNome(String nome){
        this.nome=nome;
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

    public float getCustoTotal(){
        float custo = 0;
        for (Pedido pedido : pedidos){
            custo+= pedido.getCusto();
        };
        return custo;
    }

}
