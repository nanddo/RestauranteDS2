package ds2.equipe1.restaurante.modelos;

import android.content.Context;

public class Compra extends Model<Compra> {
	private Item item;
	private int quantidade;
	private float preco;
	private String data;
	private Fornecedor fornecedor;

	public Compra(Context context){
		super(context);
	}

	public Compra(Context context, Item item, int quantidade, float preco, String data, Fornecedor fornecedor) {
		super(context);
		this.item = item;
		this.quantidade = quantidade;
		this.preco = preco;
		this.data = data;
		this.fornecedor = fornecedor;
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

	public Fornecedor getFornecedor() { return this.fornecedor; }
}
