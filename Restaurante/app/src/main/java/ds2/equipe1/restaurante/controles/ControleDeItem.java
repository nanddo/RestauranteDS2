package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeItem {
    //Lista com a consulta mais recente de itens no servidor.
    private ArrayList<Item> itens = new ArrayList<>();
    private Context context;
    private static Item selecionado;

    public ControleDeItem(Context context) {
        this.context = context;
    }

    public static void salvarItem(Item item) {
        item.save();
    }

    public static void alterarItemQuantidade(Item item, int quantidade) {
        item.setQuantidade(quantidade);
        salvarItem(item);
    }

    public static void alterarItemLimiteMinimo(Item item, int limiteMinimo) {
        item.setLimiteMinimo(limiteMinimo);
        salvarItem(item);
    }

    public static void alterarItem(Item item, int quantidade, int limiteMinimo) {
        if (quantidade >= 0) {
            item.setQuantidade(quantidade);
        }

        if (limiteMinimo >= 0) {
            item.setLimiteMinimo(limiteMinimo);
        }

        salvarItem(item);
    }

    public static void excluirItem(Item item) {
        item.delete();
    }

    public void consultarItem(String consulta, final RequestCallback<Item> callback) {
        //Se a consulta for vazio, pega todos os itens do banco de dados, e coloca na memória ram
        if (consulta.isEmpty()) {
            Model.find(context, Item.class, new TypeToken<ArrayList<Item>>() {
            }.getType(), new RequestCallback<Item>() {
                @Override
                public void execute(ArrayList<Item> lista) throws Exception {
                    super.execute(lista);

                    itens.clear();
                    itens.addAll(lista);

                    if (callback != null) {
                        callback.execute(lista);
                    }
                }
            }, null);
        } else {
            //Se tiver consulta, faz a pesquisa nos itens que já estão na memória ram
            try {
                ArrayList<Item> itensFiltrados = new ArrayList<>();

                for (Item item : itens) {
                    if (item.getNome().contains(consulta)) {
                        itensFiltrados.add(item);
                    }
                }

                if (callback != null) {
                    callback.execute(itensFiltrados);
                }
            } catch (Exception e) {
                Log.e(Utils.TAG, "Erro ao consultar fornecedores: ", e);
            }
        }
    }

    public static void relatorioItens(ArrayList<Item> itens) {
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            // TODO: alterar para exibi��o na tela do android ou gerar pdf
            System.out.println(
                    "Item: " + item.getNome() +
                            ", Quantidade Disponivel: " + item.getQuantidade() +
                            ", Limite Minimo: " + item.getLimiteMinimo()
            );
        }
    }

    public static void relatorioItensEmFalta(ArrayList<Item> itens) {
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            if (item.getQuantidade() < item.getLimiteMinimo()) {
                // TODO: alterar para exibi��o na tela do android ou gerar pdf
                System.out.println(
                        "Item em Falta: " + item.getNome() +
                                ", Quantidade Disponivel: " + item.getQuantidade() +
                                ", Limite Minimo: " + item.getLimiteMinimo()
                );
            }
        }
    }

    public static void selecionarParaEditar(Item item){
        ControleDeItem.selecionado = item;
    }

    public static Item getSelecionado(){
        return selecionado;
    }

    public static void deselecionar(){
        selecionado = null;
    }

}
