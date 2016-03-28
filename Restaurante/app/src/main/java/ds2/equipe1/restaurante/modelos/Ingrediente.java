package ds2.equipe1.restaurante.modelos;

/**
 * Created by Fernando on 28/03/2016.
 */
public class Ingrediente {
    private Item item;
    private int quantidade;

    public Ingrediente(Item item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
