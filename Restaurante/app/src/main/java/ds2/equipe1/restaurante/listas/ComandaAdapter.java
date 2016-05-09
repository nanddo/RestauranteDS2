package ds2.equipe1.restaurante.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.R;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Comanda;

/**
 * Created by elton on 09/05/2016.
 */

public class ComandaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Comanda> comandas;

    public ComandaAdapter(Context context, ArrayList<Comanda> comandas) {
        this.context = context;
        this.comandas = comandas;
    }

    @Override
    public int getCount() {
        return comandas.size();
    }

    @Override
    public Comanda getItem(int position) {
        return comandas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {
        if (root == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = vi.inflate(R.layout.item_comanda, parent, false);
        }

        TextView edtComanda, edtMesa, edtConta;
        edtComanda = (TextView) root.findViewById(R.id.tvComanda);
        edtMesa = (TextView) root.findViewById(R.id.tvMesa);
        edtConta = (TextView) root.findViewById(R.id.tvConta);

        Comanda comanda = comandas.get(position);

        //Corrigir
        //edtComanda.setText(comanda.getNome());
        //edtMesa.setText(comanda.getCnpj());
        //edtConta.setText(comanda.getTelefone());

        new Utils(context).addFuncaoRemover(this, root.findViewById(R.id.ivRemover), comandas, position);

        return root;
    }
}
