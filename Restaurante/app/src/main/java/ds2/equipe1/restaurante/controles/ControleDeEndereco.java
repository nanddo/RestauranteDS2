package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Endereco;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

public class ControleDeEndereco {
    //Lista com a consulta mais recente de enderecos no servidor.
    private static ArrayList<Endereco> enderecos = new ArrayList<>();
    //Essa variavel serve para deixar uma referencia na memoria o tempo inteiro de qual esta selecionado,
    //para que quando haja uma edicao, a alteracao seja refletida em mais de uma tela (exemplo: editar um endereco e atualizar na tela de busca).
    private static Endereco selecionado;
    private Context context;

    public ControleDeEndereco(Context context){
        this.context = context;
    }

    public void salvarEndereco(Endereco endereco){
        endereco.save();
        enderecos.add(endereco);
    }

    public Endereco getEnderecoById(int id){
        for (Endereco endereco : enderecos) {
            if (endereco.getId() == id) {
                return endereco;
            }
        }
        return null;
    }

    public boolean excluirEndereco(Endereco endereco){
        if (endereco.getId() != -1) {
            endereco.delete();
            enderecos.remove(endereco);
            return true;
        } else return false;
    }

    public void consultarEndereco(String consulta, final RequestCallback<Endereco> callback){
        //Se a consulta for vazio, pega todos os itens do banco de dados, e coloca na memoria ram
        if (consulta.isEmpty()) {
            Model.find(context, Endereco.class, new TypeToken<ArrayList<Endereco>>() {
                    }.getType(), new RequestCallback<Endereco>() {
                        @Override
                        public void execute(ArrayList<Endereco> lista) throws Exception {
                            super.execute(lista);

                            enderecos.clear();
                            enderecos.addAll(lista);

                            if (callback != null){
                                callback.execute(lista);
                            }
                        }
                    }, null);
        } else {
            //Se tiver consulta, faz a pesquisa nos itens que já estão na memória ram
            try {
                ArrayList<Endereco> enderecosFiltrados = new ArrayList<>();

                for (Endereco endereco : enderecos) {
                    if (endereco.getRua().contains(consulta) || endereco.getBairro().contains(consulta)) {
                        enderecosFiltrados.add(endereco);
                    }
                }

                if (callback != null) {
                    callback.execute(enderecosFiltrados);
                }
            } catch (Exception e){
                Log.e(Utils.TAG, "Erro ao consultar enderecos: ", e);
            }
        }
    }

    public void relatorioFornecedor(Fornecedor fornecedor){
        //Banco de dados
        //Exibir Relatório
    }

    public void relatorioItensPorFornecedor(Item item, Fornecedor fornecedor){
        //Banco de Dados
        //Exibir Relatório
    }

    public static void selecionarParaEditar(Endereco endereco){
        ControleDeEndereco.selecionado = endereco;
    }

    public static Endereco getSelecionado(){
        return selecionado;
    }

    public static void deselecionar(){
        selecionado = null;
    }
}
