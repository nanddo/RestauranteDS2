package ds2.equipe1.restaurante.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.R;
import ds2.equipe1.restaurante.modelos.Item;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> itens;

    public ItemAdapter(Context context, ArrayList<Item> fornecedores) {
        this.context = context;
        this.itens = fornecedores;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Item getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itens.get(position).getId();
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {
        if (root == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = vi.inflate(R.layout.item_itens, parent, false);
        }

        TextView edtNome,edtEstoque;
        edtNome = (TextView) root.findViewById(R.id.tvNome);
        edtEstoque = (TextView) root.findViewById(R.id.tvEstoque);

        Item item = itens.get(position);
        edtNome.setText(item.getNome());
        edtEstoque.setText(item.getQuantidade());

        return root;
    }
}
