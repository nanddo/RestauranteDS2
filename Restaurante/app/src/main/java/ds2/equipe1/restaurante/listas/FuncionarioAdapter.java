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
import ds2.equipe1.restaurante.modelos.Funcionario;

public class FuncionarioAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<Funcionario> funcionarios;

    public FuncionarioAdapter(Context context, ArrayList<Funcionario> funcionarios) {
        this.context = context;
        this.funcionarios = funcionarios;
    }

    @Override
    public int getCount() {
        return funcionarios.size();
    }

    @Override
    public Funcionario getItem(int position) {
        return funcionarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {
        if (root == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = vi.inflate(R.layout.item_funcionario, parent, false);
        }

        TextView edtNome, edtCPF, edtTelefone;
        edtNome = (TextView) root.findViewById(R.id.tvNome);
        edtCPF = (TextView) root.findViewById(R.id.tvCPF);
        edtTelefone = (TextView) root.findViewById(R.id.tvTelefone);

        Funcionario funcionario = funcionarios.get(position);

        edtNome.setText(funcionario.getNome());
        edtCPF.setText(funcionario.getCpf());
        edtTelefone.setText(funcionario.getTelefone());

        new Utils(context).addFuncaoRemover(this, root.findViewById(R.id.ivRemover), funcionarios, position);

        return root;
    }
}
