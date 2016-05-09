package ds2.equipe1.restaurante.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.CadastroProduto;
import ds2.equipe1.restaurante.R;
import ds2.equipe1.restaurante.modelos.Ingrediente;
import ds2.equipe1.restaurante.modelos.Item;

/**
 * Created by Fernando on 09/05/2016.
 */
public class IngredienteAdapter extends BaseAdapter {
    private CadastroProduto cadastroProduto;
    private ArrayList<Ingrediente>  ingredientes;

    public IngredienteAdapter(CadastroProduto cadastroProduto, ArrayList<Ingrediente> ingredientes) {
        this.cadastroProduto = cadastroProduto;
        this.ingredientes = ingredientes;
    }

    @Override
    public int getCount() {
        return ingredientes.size() +1;
    }

    @Override
    public Ingrediente getItem(int position) {
        return ingredientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View root, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) cadastroProduto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = vi.inflate(R.layout.item_item, parent, false);

        TextView edtNome,edtEstoque;
        edtNome = (TextView) root.findViewById(R.id.tvNome);
        edtEstoque = (TextView) root.findViewById(R.id.tvEstoque);

        if (position < ingredientes.size()) {
            Ingrediente ingrediente = getItem(position);
            Item item = ingrediente.getItem();
            edtNome.setText(item.getNome());
            edtEstoque.setText("" + ingrediente.getQuantidade() + item.getUnidade());

            root.findViewById(R.id.ivRemover).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ingredientes.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else {
            edtNome.setText("Adicionar ingrediente");
            edtEstoque.setText("Clique no ícone ao lado");

            root.findViewById(R.id.ivIcone).setVisibility(View.INVISIBLE);
            ((ImageView)root.findViewById(R.id.ivRemover)).setImageDrawable(cadastroProduto.getResources().getDrawable(R.drawable.ic_add));
            root.findViewById(R.id.ivRemover).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cadastroProduto.onAddIngredienteClick();
                }
            });
        }

        return root;
    }
}
