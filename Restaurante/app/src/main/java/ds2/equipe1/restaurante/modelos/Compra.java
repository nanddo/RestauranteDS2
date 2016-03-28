package ds2.equipe1.restaurante.modelos;

/**
 * Created by Th on 24/03/2016.
 */
public class Compra {
	
	/* Uma vez que a compra tem v�rios items, talvez o correto seja o que est� comentado abaixo: (?)
	 *
	 * private ArrayList <Item> itens;
	 * private float preco;
	 * private String data;
	 */
	
	private String nomeDoItem;
	private int quantidade;
	private float preco;
	private String data;
	// t� correto isso aqui?
	//private boolean emUso;
	
	public Compra(String nomeDoItem, int quantidade, float preco, String data) {
		this.nomeDoItem = nomeDoItem;
		this.quantidade = quantidade;
		this.preco = preco;
		this.data = data;
	}
	
	public String getNomeDoItem(){
        return this.nomeDoItem;
    }

    public int getQuantidade(){
        return this.quantidade;
    }
    
    public float getPreco() {
    	return this.preco;
    }
    
    public String getData() {
    	return this.data;
    }
    
    public void setNomeDoItem(String nomeDoItem) {
    	this.nomeDoItem = nomeDoItem;
    }
    
    public void setQuantidade(int quantidade) {
    	this.quantidade = quantidade;
    }
    
    public void setPreco(float preco) {
    	this.preco = preco;
    }
    
    public void setData(String data) {
    	this.data = data;
    }
}
