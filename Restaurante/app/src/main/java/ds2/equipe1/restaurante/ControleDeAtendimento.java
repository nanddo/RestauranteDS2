package ds2.equipe1.restaurante;

import java.util.ArrayList;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeAtendimento {

    public ControleDeAtendimento(){

    }

    public void criarComanda(Mesa mesa, Pedido pedido){
        mesa.addComanda(new Comanda(pedido));
    }

    public void encerrarComanda(Comanda comanda){
        comanda.desativar();
    }

    public void imprimirPedido(Pedido pedido){
        ArrayList< Produto > listaDeProdutos = pedido.getProdutos();
        for(int i = 0; i < listaDeProdutos.size(); i++){
            System.out.println(listaDeProdutos.get(i).toString());
        }
    }

    

}
