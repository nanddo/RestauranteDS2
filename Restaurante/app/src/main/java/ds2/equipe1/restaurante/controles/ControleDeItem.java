package ds2.equipe1.restaurante.controles;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Item;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeItem {
	
	private static ArrayList <Item> itens;
	
	public static void cadastrarItem(Item item) {
		itens.add(item);
		// TODO: realizar consulta SQL
		// String sql = "INSERT INTO restaurante.item VALUES (" + item.getNome() + "," + item.getQuantidade() + "," + item.getLimiteMinimo()+ ")";
	}
	
	public static void alterarItemQuantidade(String nome, int quantidade) {
		Item item = consultarItem(nome);
		
		if (item != null) {
			item.setQuantidade(quantidade);
			// TODO: realizar consulta SQL (na classe item fica melhor)
			// String sql = "UPDATE restaurante.item SET quantidade = '" + quantidade + "' WHERE nome = '" + nome + "'";
		}
	}
	
	public static void alterarItemLimiteMinimo(String nome, int limiteMinimo) {
		Item item = consultarItem(nome);
		
		if (item != null) {
			item.setLimiteMinimo(limiteMinimo);
			// TODO: realizar consulta SQL (na classe item fica melhor)
			// String sql = "UPDATE restaurante.item SET limiteMinimo = '" + limiteMinimo + "' WHERE nome = '" + nome + "'";
		}
	}
	
	public static void alterarItem(String nome, int quantidade, int limiteMinimo) {
		Item item = consultarItem(nome);
		
		if (item != null) {
			if (quantidade >= 0) {
				item.setQuantidade(quantidade);
				// TODO: realizar consulta SQL (na classe item fica melhor)
				// String sql = "UPDATE restaurante.item SET quantidade = '" + quantidade + "' WHERE nome = '" + nome + "'";
			}
			
			if (limiteMinimo >= 0) {
				item.setLimiteMinimo(limiteMinimo);
				// TODO: realizar consulta SQL (na classe item fica melhor)
				// String sql = "UPDATE restaurante.item SET limiteMinimo = '" + limiteMinimo + "' WHERE nome = '" + nome + "'";
			}
		}
	}
	
	public static void excluirItem(String nome) {
		Item item = consultarItem(nome);
		if (item != null) {
			itens.remove(item);
			// TODO: realizar consulta SQL
			// String sql = "DELETE FROM restaurante.item WHERE nome = '" + nome + "'";
		}
	}
	
	public static Item consultarItem(String nome) {
		for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getNome() == nome) {
				return itens.get(i);
			}
        }
        return null;
	}
	
	public static void relatorioItens() {
		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
			// TODO: alterar para exibi��o na tela do android ou gerar pdf
			System.out.println(
				"Item: " + item.getNome() + 
				", Quantidade Disponivel: " + item.getQuantidade() + 
				", Limite Minimo: " + item.getLimiteMinimo()
			);
        }
	}
	
	public static void relatorioItensEmFalta() {
		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
			if (item.getQuantidade() < item.getLimiteMinimo()) {
				// TODO: alterar para exibi��o na tela do android ou gerar pdf
				System.out.println(
					"Item em Falta: " + item.getNome() + 
					", Quantidade Disponivel: " + item.getQuantidade() + 
					", Limite Minimo: " + item.getLimiteMinimo()
				);
			}
        }
	}
}
