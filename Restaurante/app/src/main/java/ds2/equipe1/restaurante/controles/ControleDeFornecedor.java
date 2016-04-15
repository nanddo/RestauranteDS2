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
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();

    public ControleDeFornecedor(Context context){
        this.context = context;
        //fornecedores.add(new Fornecedor(context, "Fernandos LTDA.", "79 98388428", "000.000.000-99", "fernandos@nada.com"));
        //fornecedores.add(new Fornecedor(context, "Arquimago Tecnology", "66 666-666-666", "666.666.666-66", "arquimago@666.com"));
        //fornecedores.add(new Fornecedor(context, "Loja de Sei lá, eu nunca pensei em abrir não Tecnology", "79 9999-9999", "0000;00000,0000", "seila@666.com"));
    }

    public void cadastrarFornecedor(Fornecedor fornecedor){
        fornecedores.add(fornecedor);
        fornecedor.save();

        //Banco de Dados
        //SQL (Sem o Endereço)
        //INSERT INTO fornecedor (nome, telefone, cnpj, email) VALUES (fornecedor.getNome(), fornecedor.getTelefone(), fornecedor.getCnpj(), fornecedor.getEmail());
    }

    public boolean alterarFornecedor(Fornecedor fornecedor){
        if (fornecedor.getId() != -1) {
            fornecedor.save();
            return true;
        } else return false;

        //Banco de Dados
        //SQL (Sem o Endereço)
        //UPDATE fornecedor SET nome = fornecedor.getNome(), telefone = fornecedor.getNome(), email = fornecedor.getEmail WHERE cnpj = fornecedor.getCnpj();
    }

    public void excluirFornecedor(Fornecedor fornecedor){
        fornecedor.delete();
        fornecedores.remove(fornecedor);
        //Banco de Dados
        //SQL
        //DELETE FROM fornecedor WHERE cnpj = fornecedor.getCnpj();
    }

    public void consultarFornecedor(final String consulta, final RequestCallback<Fornecedor> callback){
        Model.find(context, Fornecedor.class, new RequestCallback<Fornecedor>() {
            @Override
            public void execute(ArrayList<Fornecedor> fornecedores) {
                //ArrayList<Fornecedor> fornecedores = new ArrayList<>();
                //fornecedores.addAll((ArrayList<Fornecedor>) ((ArrayList<?>) models));

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
        //Banco de Dados
        //SQL
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
