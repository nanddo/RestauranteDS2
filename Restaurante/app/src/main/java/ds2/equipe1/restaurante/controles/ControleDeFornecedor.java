package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

public class ControleDeFornecedor {
    //Lista com a consulta mais recente de fornecedores no servidor.
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    //Essa variavel serve para deixar uma referencia na memoria o tempo inteiro de qual esta selecionado,
    //para que quando haja uma edicao, a alteracao seja refletida em mais de uma tela (exemplo: editar um fornecedor e atualizar na tela de busca).
    private static Fornecedor selecionado;
    private Context context;

    public ControleDeFornecedor(Context context){
        this.context = context;
    }

    public void salvarFornecedor(Fornecedor fornecedor, RequestCallback<Model> callback){
        fornecedor.save(callback);
    }

    public boolean excluirFornecedor(Fornecedor fornecedor){
        if (fornecedor.getId() != -1) {
            fornecedor.delete();
            return true;
        } else return false;
    }

    public void consultarFornecedor(String consulta, final RequestCallback<Fornecedor> callback){
        //Se a consulta for vazio, pega todos os itens do banco de dados, e coloca na memoria ram
        if (consulta.isEmpty()) {
            Model.find(context, Fornecedor.class, new TypeToken<ArrayList<Fornecedor>>() {
                    }.getType(), new RequestCallback<Fornecedor>() {
                        @Override
                        public void execute(ArrayList<Fornecedor> lista) throws Exception {
                            super.execute(lista);

                            fornecedores.clear();
                            fornecedores.addAll(lista);

                            if (callback != null){
                                callback.execute(lista);
                            }
                        }
                    }, null);
        } else {
            //Se tiver consulta, faz a pesquisa nos itens que ja estao na memoria ram
            try {
                ArrayList<Fornecedor> fornecedoresFiltrados = new ArrayList<>();

                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getNome().contains(consulta) || fornecedor.getCnpj().contains(consulta)) {
                        fornecedoresFiltrados.add(fornecedor);
                    }
                }

                if (callback != null) {
                    callback.execute(fornecedoresFiltrados);
                }
            } catch (Exception e){
                Log.e(Utils.TAG, "Erro ao consultar fornecedores: ", e);
            }
        }
    }

    public void relatorioFornecedor(Fornecedor fornecedor){
        //Banco de dados
        //Exibir Relatorio
    }

    public void relatorioItensPorFornecedor(Item item, Fornecedor fornecedor){
        //Banco de Dados
        //Exibir Relatorio
    }

    public static void selecionarParaEditar(Fornecedor fornecedor){
        ControleDeFornecedor.selecionado = fornecedor;
    }

    public static Fornecedor getSelecionado(){
        return selecionado;
    }

    public static void deselecionar(){
        selecionado = null;
    }
}
