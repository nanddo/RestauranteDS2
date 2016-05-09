package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Produto;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;
import ds2.equipe1.restaurante.modelos.Produto;

public class ControleDeProduto {
    //Lista com a consulta mais recente de produtos no servidor.
    private ArrayList<Produto> produtos = new ArrayList<>();
    //Essa variavel serve para deixar uma referencia na memoria o tempo inteiro de qual esta selecionado,
    //para que quando haja uma edicao, a alteracao seja refletida em mais de uma tela (exemplo: editar um produto e atualizar na tela de busca).
    private static Produto selecionado;
    private Context context;

    public ControleDeProduto(Context context){
        this.context = context;
    }

    public void salvarProduto(Produto produto, RequestCallback<Model> callback){
        produto.save(callback);
    }

    public boolean excluirProduto(Produto produto){
        if (produto.getId() != -1) {
            produto.delete();
            return true;
        } else return false;
    }

    public void consultarProduto(String consulta, final RequestCallback<Produto> callback){
        //Se a consulta for vazio, pega todos os itens do banco de dados, e coloca na memoria ram
        if (consulta == null || produtos == null) {
            Model.find(context, Produto.class, new TypeToken<ArrayList<Produto>>() {
            }.getType(), new RequestCallback<Produto>() {
                @Override
                public void execute(ArrayList<Produto> lista) throws Exception {
                    super.execute(lista);

                    produtos.clear();
                    produtos.addAll(lista);

                    if (callback != null){
                        callback.execute(lista);
                    }
                }
            }, null);
        } else {
            consulta = consulta.toLowerCase();
            //Se tiver consulta, faz a pesquisa nos itens que ja estao na memoria ram
            try {
                ArrayList<Produto> produtosFiltrados = new ArrayList<>();

                for (Produto produto : produtos) {
                    if (produto.getNome().toLowerCase().contains(consulta)) {
                        produtosFiltrados.add(produto);
                    }
                }

                if (callback != null) {
                    callback.execute(produtosFiltrados);
                }
            } catch (Exception e){
                Log.e(Utils.TAG, "Erro ao consultar produtos: ", e);
            }
        }
    }

    public void relatorioProduto(Produto produto){
        //Banco de dados
        //Exibir Relatorio
    }

    public void relatorioItensPorProduto(Item item, Produto produto){
        //Banco de Dados
        //Exibir Relatorio
    }

    public static void selecionarParaEditar(Produto produto){
        ControleDeProduto.selecionado = produto;
    }

    public static Produto getSelecionado(){
        return selecionado;
    }

    public static void deselecionar(){
        selecionado = null;
    }
}
