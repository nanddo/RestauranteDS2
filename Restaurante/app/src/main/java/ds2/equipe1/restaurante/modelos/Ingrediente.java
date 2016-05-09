package ds2.equipe1.restaurante.modelos;

import android.content.Context;

public class Ingrediente extends Model<Ingrediente> {
    private Item item;
    private int quantidade;

    public Ingrediente(Context context, Item item, int quantidade) {
        super(context);
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
