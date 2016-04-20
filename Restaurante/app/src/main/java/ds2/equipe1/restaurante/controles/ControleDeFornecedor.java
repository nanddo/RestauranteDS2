package ds2.equipe1.restaurante.controles;

import android.content.Context;

import com.androidquery.callback.AjaxCallback;

import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

/**
 * Created by Th on 24/03/2016.
 */

public class ControleDeFornecedor {
    private Context context;

    public ControleDeFornecedor(Context context){
        this.context = context;
    }

    public void cadastrarFornecedor(Fornecedor fornecedor){
        fornecedor.save();
    }

    public boolean alterarFornecedor(Fornecedor fornecedor){
        if (fornecedor.getId() != -1) {
            fornecedor.save();
            return true;
        } else return false;
    }

    public boolean excluirFornecedor(Fornecedor fornecedor){
        if (fornecedor.getId() != -1) {
            fornecedor.delete();
            return true;
        } else return false;
    }

    public void consultarFornecedor(final String consulta, final RequestCallback<Fornecedor> callback){
        Model.find(context, Fornecedor.class, new RequestCallback<Fornecedor>() {
            @Override
            public void execute(ArrayList<Fornecedor> fornecedores) {
                ArrayList<Fornecedor> filtrado = new ArrayList<>();

                for (Fornecedor fornecedor : fornecedores) {
                    if (consulta == null || consulta.isEmpty() || fornecedor.getNome().contains(consulta) || fornecedor.getCnpj().contains(consulta)) {
                        filtrado.add(fornecedor);
                    }
                }

                if (callback != null){
                    callback.execute(filtrado);
                }

                super.execute(filtrado);
            }
        });
    }

    public void consultarFornecedor(final int id, final RequestCallback<Fornecedor> callback){
        Model.find(context, Fornecedor.class, callback, id);

        //SELECT id, nome, cnpj, telefone, email, id_endereco WHERE cnpj = funcionario.getCnpj() OR nome = funcionario.getNome();
    }

    public void relatorioFornecedor(Fornecedor fornecedor){
        //Banco de dados
        //Exibir Relatório
    }

    public void relatorioItensPorFornecedor(Item item, Fornecedor fornecedor){
        //Banco de Dados
        //Exibir Relatório
    }

}
