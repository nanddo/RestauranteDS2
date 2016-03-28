package ds2.equipe1.restaurante.modelos;

/**
 * Created by Th on 24/03/2016.
 */
public class Compra {
	private Item item;
	private int quantidade;
	private float preco;
	private String data;
	
	public Compra(Item item, int quantidade, float preco, String data) {
		this.item = item;
		this.quantidade = quantidade;
		this.preco = preco;
		this.data = data;
	}
	
	public String getNomeDoItem(){
        return this.item.getNome();
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
}
