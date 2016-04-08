package ds2.equipe1.restaurante.controles;

import android.content.Context;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Compra;
import ds2.equipe1.restaurante.modelos.Item;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeCompra {
	private Context context;
	private static ArrayList<Compra> compras;

	public ControleDeCompra(Context context){
		this.context = context;
	}

	public static void comprarItem(Compra compra) {
		compras.add(compra);
		
		String sql = "INSERT INTO restaurante.compra VALUES (" +
				compra.getNomeDoItem() + ", " + 
				compra.getQuantidade() + ", " + 
				compra.getPreco() + ", "+
				compra.getData() + ")";
	}
	
	public void comprarItem(Item item, int quantidade, float preco, String data) {
		Compra compra = new Compra(context, item, quantidade, preco, data);
		
		comprarItem(compra);
	}
	
	public void relatorioDespesas() {
		for (int i = 0; i < compras.size(); i++) {
			// TODO: alterar para exibi??o na tela do android ou gerar pdf
			Compra compra = compras.get(i);
			System.out.println(
				"Item Comprado: " + compra.getNomeDoItem() + 
				", Quantidade: " + compra.getQuantidade() + 
				", Preço: " + compra.getPreco() +
				", Data: " + compra.getData()
			);
		}
	}
}
