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
import ds2.equipe1.restaurante.modelos.Produto;

public class ProdutoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Produto> produtos;

    public ProdutoAdapter(Context context, ArrayList<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Produto getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {
        if (root == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = vi.inflate(R.layout.item_produto, parent, false);
        }

        TextView edtNome,edtPreco;
        edtNome = (TextView) root.findViewById(R.id.tvNome);
        edtPreco = (TextView) root.findViewById(R.id.tvPreco);

        Produto produto = produtos.get(position);

        edtNome.setText(produto.getNome());
        edtPreco.setText(Float.toString(produto.getPreco()));

        new Utils(context).addFuncaoRemover(this, root.findViewById(R.id.ivRemover), produtos, position);

        return root;
    }
}
