package ds2.equipe1.restaurante;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeItem {
	
	private ArrayList <Item> itens;
	
	public Item consultarItem (String nome){
		for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getNome() == nome) {
				return itens.get(i);
			}
        }
        return null;
	}
}
