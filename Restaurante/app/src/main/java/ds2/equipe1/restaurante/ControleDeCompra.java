package ds2.equipe1.restaurante;

import java.util.ArrayList;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeCompra {
	
	private static ArrayList<Compra> compras;
	
	public static void comprarItem(Compra compra) {
		compras.add(compra);
		
		String sql = "INSERT INTO restaurante.compra VALUES( " + 
				compra.getNomeDoItem() + ", " + 
				compra.getQuantidade() + ", " + 
				compra.getPreco() + ", "+
				compra.getData() + ")";
	}
	
	public static void comprarItem(Item item, int quantidade, float preco, String data) {
		Compra compra = new Compra(item.getNome(), quantidade, preco, data);
		
		comprarItem(compra);
	}
	
	public static void relatorioDespesas() {
		for (int i = 0; i < compras.size(); i++) {
			// TODO: alterar para exibição na tela do android ou gerar pdf
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
