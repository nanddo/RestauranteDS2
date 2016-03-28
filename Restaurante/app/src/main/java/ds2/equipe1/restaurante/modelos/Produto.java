package ds2.equipe1.restaurante.modelos;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeItem;

/**
 * Created by Th on 24/03/2016.
 */
public class Produto {

	private int id;
	private String nome;
	private float preco;
    private ArrayList<Ingrediente> ingredientes;
    
	public Produto(String nome, float preco, int id, ArrayList<Ingrediente> ingredientes) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.ingredientes = ingredientes;
    }

	public static ArrayList<Produto> carrgarProdutos() {
		ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();

		ArrayList<Produto> lista = new ArrayList<Produto>();

		//conexao com o banco estabelecida
		for (int i = 0; i < lista.count(); i++) {
			listaDeProdutos.add(i, new Produto(lista.nome, lista.preco, lista.id, ...));
		}

		return listaDeProdutos; //(???)
	}
    
	public boolean validarProduto() {
		for (int i = 0; i < ingredientes.size(); i++) {
            if (ingredientes.get(i).getQuantidade() > ControleDeItem.consultarItem(ingredientes.get(i).getNome()).getQuantidade()) {
				return false;
			}
        }
        return true;
    }
    
	public String getNome(){
        return nome;
    }

    public float getPreco(){
        return preco;
    }
    
    /*
	public int getId(){
        return id;
    }
    */
    
    public ArrayList <Item> getItens(){
        return ingredientes;
    }
    
    /* Nome deve ser imutavel
	public void setNome(String nome){
		this.nome = nome;
    }
    */
    
    public void setPreco(float preco){
		this.preco = preco;
    }
    
    /*
    public void setId(int id){
		this.id = id;
    }
    */
    
    /*
    public void setItens(ArrayList <Item> itens){
		this.itens = itens;
    }
    */
}
