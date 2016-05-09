package ds2.equipe1.restaurante.modelos;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

public class Comanda extends Model<Comanda> {

    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private String data;
    private boolean ativa = false;
    private String nome;
    private int mesa;

    public Comanda(Context context, String nome, int mesa, Pedido primeiro){
        super(context);
        Date agora = new Date();
        pedidos.add(primeiro);
        this.mesa = mesa;
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

    public String getData() {
        return data;
    }

    public int getMesa() {
        return mesa;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public String getNome() {
        return nome;
    }
}
