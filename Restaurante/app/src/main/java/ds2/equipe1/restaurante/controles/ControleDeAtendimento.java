package ds2.equipe1.restaurante.controles;

import android.content.Context;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Comanda;
import ds2.equipe1.restaurante.modelos.Mesa;
import ds2.equipe1.restaurante.modelos.Pedido;
import ds2.equipe1.restaurante.modelos.Produto;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeAtendimento {
    private Context context;
    private ControleDeImpressao controleDeImpressao;

    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();

    public ControleDeAtendimento(Context context){
        this.context = context;
        this.controleDeImpressao = new ControleDeImpressao(context);

        //inserir comando sql pra saber numero de mesas
        int numeroDeMesas = 20; //puxar número do sql
        for(int i = 1; i <= numeroDeMesas; i++) {
            mesas.add(i, new Mesa(context, i));
        }
    }

    public void criarComanda(Mesa mesa, Pedido pedido, String nome){
        mesa.addComanda(new Comanda(context, nome, pedido));
    }

    public void encerrarComanda(Comanda comanda){
        comanda.desativar();
    }

    public void imprimirPedido(Pedido pedido){
        ArrayList<Produto> listaDeProdutos = pedido.getProdutos();
        for(int i = 0; i < listaDeProdutos.size(); i++){
            controleDeImpressao.imprimir(listaDeProdutos.get(i).getNome());
        }
    }

    public void imprimirConta(Comanda comanda){
        String saida = "";
        ArrayList < Pedido > listaDePedidos = comanda.getPedidos();
        for(int i = 0; i < listaDePedidos.size(); i++) {
            ArrayList < Produto > listaDeProdutos = listaDePedidos.get(i).getProdutos();
            for(int j = 0; j < listaDeProdutos.size(); j++) {
                saida += listaDeProdutos.get(i).getNome() + " " + listaDeProdutos.get(i).getPreco() + "\n";
            }
        }
        float total = comanda.getTotal();
        saida += "Total de Pedidos" + total + "\n 10% = " + 0.1*total + "\n Total " + 1.1*total;

        controleDeImpressao.imprimir(saida);
    }

    public ArrayList < Produto > consultarPedido(Pedido pedido){
        return pedido.getProdutos();
    }

    public void incluirPedido(Comanda comanda, Pedido pedido){
        comanda.addPedido(pedido);
    }

    public void entregarPedido(Pedido pedido){
        pedido.setEntregue();
    }

    public void cancelarPedido(Comanda comanda, Pedido pedido) {
        comanda.removerPedido(pedido);
    }

    public ArrayList < Produto > consultarCardapio() {
        ArrayList < Produto > cardapio = new ArrayList < Produto >();
        //Aqui só com consulta ao DB né? pq precisa pegar a lista de produtos.
        //fica pra outra hora beijo no coração!
        return cardapio;
    }

    public ArrayList < Comanda > relatorioComandasAtivas(){
        ArrayList < Comanda > ativas = new ArrayList < Comanda >();
        //preciso de consulta ao banco neste ponto
        return ativas;
    }


    public ArrayList < Comanda > relatorioComandasEncerradas(){
        ArrayList < Comanda > encerradas = new ArrayList < Comanda >();
        //preciso de consulta ao banco neste ponto
        return encerradas;
    }

    public void relatorioArrecadacaoBruta(){
        //preciso de consulta ao banco neste ponto
    }

}
