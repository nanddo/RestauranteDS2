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
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class FornecedorAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Fornecedor> fornecedores;

    public FornecedorAdapter(Context context, ArrayList<Fornecedor> fornecedores) {
        this.context = context;
        this.fornecedores = fornecedores;
    }

    @Override
    public int getCount() {
        return fornecedores.size();
    }

    @Override
    public Fornecedor getItem(int position) {
        return fornecedores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return fornecedores.get(position).getId();
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {
        if (root == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = vi.inflate(R.layout.item_fornecedor, parent, false);
        }

        TextView edtNome, edtCNPJ, edtTelefone;
        edtNome = (TextView) root.findViewById(R.id.tvNome);
        edtCNPJ = (TextView) root.findViewById(R.id.tvCNPJ);
        edtTelefone = (TextView) root.findViewById(R.id.tvTelefone);

        Fornecedor fornecedor = fornecedores.get(position);

        edtNome.setText(fornecedor.getNome());
        edtCNPJ.setText(fornecedor.getCnpj());
        edtTelefone.setText(fornecedor.getTelefone());

        return root;
    }
}
