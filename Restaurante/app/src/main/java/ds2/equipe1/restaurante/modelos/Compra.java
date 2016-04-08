package ds2.equipe1.restaurante.modelos;

import android.content.Context;

/**
 * Created by Th on 24/03/2016.
 */
public class Compra extends Model<Compra> {
	private Item item;
	private int quantidade;
	private float preco;
	private String data;
	
	public Compra(Context context, Item item, int quantidade, float preco, String data) {
		super(context);
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
