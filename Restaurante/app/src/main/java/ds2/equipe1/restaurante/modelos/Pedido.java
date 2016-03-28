package ds2.equipe1.restaurante.modelos;

import java.util.ArrayList;

/**
 * Created by Th on 24/03/2016.
 */

public class Pedido {

    int quantidadeDeProdutos;
    boolean entregue;
    ArrayList <Produto> produtos = new  ArrayList <Produto>();

    public Pedido(ArrayList <Produto> produtos){
        this.produtos = produtos;
        quantidadeDeProdutos = produtos.size();
        entregue = false;
    }

    public Pedido(int id){
        //carregar do banco
    }

    public float getCusto(){
        float custo = 0;
        for (int i =0;i< produtos.size();i++) {
            custo += produtos.get(i).getPreco();
        }
        return custo;
    }

    public void setEntregue(){
        entregue = true;
    }

    public int getQuantidadeDeProdutos(){
        return quantidadeDeProdutos;
    }

    public ArrayList <Produto> getProdutos(){
        return produtos;
    }

    public Produto getProduto(int indice){
        return produtos.get(indice);
    }

}
