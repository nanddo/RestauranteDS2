package ds2.equipe1.restaurante.modelos;

import android.content.Context;

import java.util.Date;

/**
 * Created by Fernando on 06/05/2016.
 */
public class Aviso extends Model<Aviso> {
    private Item item;
    private Date data;

    public Aviso(Context context, Item item) {
        super(context);
        this.item = item;
        this.data = new Date();
    }
}
