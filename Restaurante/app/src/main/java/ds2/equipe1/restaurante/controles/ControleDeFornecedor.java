package ds2.equipe1.restaurante.controles;

import android.content.Context;

import com.androidquery.callback.AjaxCallback;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

/**
 * Created by Th on 24/03/2016.
 */

public class ControleDeFornecedor {
    //Essa variável serve para deixar uma referência na memória o tempo inteiro de qual está selecionado,
    //para que quando haja uma edição, a alteração seja refletida em mais de uma tela (exemplo: editar um fornecedor e atualizar na tela de busca).
    private static Fornecedor selecionado;
    private Context context;

    public ControleDeFornecedor(Context context){
        this.context = context;
    }

    public void salvarFornecedor(Fornecedor fornecedor){
        fornecedor.save();
    }

    public boolean excluirFornecedor(Fornecedor fornecedor){
        if (fornecedor.getId() != -1) {
            fornecedor.delete();
            return true;
        } else return false;
    }

    public void consultarFornecedor(final RequestCallback<Fornecedor> callback){
        Model.find(context, Fornecedor.class, new TypeToken<ArrayList<Fornecedor>>(){}.getType(), callback, null);
    }

    public void relatorioFornecedor(Fornecedor fornecedor){
        //Banco de dados
        //Exibir Relatório
    }

    public void relatorioItensPorFornecedor(Item item, Fornecedor fornecedor){
        //Banco de Dados
        //Exibir Relatório
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
