package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Funcionario;
import ds2.equipe1.restaurante.modelos.Model;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeFuncionario {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    private static Funcionario selecionado;
    private Context context;

    private ControleDeImpressao controleDeImpressao;

    public ControleDeFuncionario(Context context){
        controleDeImpressao = new ControleDeImpressao(context);
    }

    public void salvarFuncionario(Funcionario funcionario){
        funcionario.save();
    }
    public void cadastrarFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
        funcionario.save();

        //Banco de Dados
        //SQL (Sem o Endereço)
        //INSERT INTO funcionario (nome, telefone, cnpj, email) VALUES (funcionario.getRua(), funcionario.getTelefone(), funcionario.getCnpj(), funcionario.getEmail());
    }

    public boolean alterarFuncionario(Funcionario funcionario){
        if (funcionario.getId() != -1) {
            funcionario.save();
            return true;
        } else return false;

        //Banco de Dados
        //SQL (Sem o Endereço)
        //UPDATE funcionario SET nome = funcionario.getRua(), telefone = funcionario.getRua(), email = funcionario.getEmail WHERE cnpj = funcionario.getCnpj();
    }

    public boolean excluirFuncionario(Funcionario funcionario){
        if (funcionario.getId() != -1) {
            funcionario.delete();
            return true;
        } else return false;
    }

    public void consultarFuncionario(String consulta, final RequestCallback<Funcionario> callback){
        //Se a consulta for vazio, pega todos os itens do banco de dados, e coloca na memória ram
        if (consulta.isEmpty()) {
            Model.find(context, Funcionario.class, new TypeToken<ArrayList<Funcionario>>() {
            }.getType(), new RequestCallback<Funcionario>() {
                @Override
                public void execute(ArrayList<Funcionario> lista) throws Exception {
                    super.execute(lista);

                    funcionarios.clear();
                    funcionarios.addAll(lista);

                    if (callback != null){
                        callback.execute(lista);
                    }
                }
            }, null);
        } else {
            //Se tiver consulta, faz a pesquisa nos itens que já estão na memória ram
            try {
                ArrayList<Funcionario> funcionariosFiltrados = new ArrayList<>();

                for (Funcionario funcionario : funcionarios) {
                    if (funcionario.getNome().contains(consulta) || funcionario.getCpf().contains(consulta)) {
                        funcionariosFiltrados.add(funcionario);
                    }
                }

                if (callback != null) {
                    callback.execute(funcionariosFiltrados);
                }
            } catch (Exception e){
                Log.e(Utils.TAG, "Erro ao consultar funcionarios: ", e);
            }
        }
    }

    public void relatorioFuncionario(Funcionario funcionario){
        //Banco de dados
        //Exibir Relatório
    }

    public static void selecionarParaEditar(Funcionario funcionario){
        ControleDeFuncionario.selecionado = funcionario;
    }

    public static Funcionario getSelecionado(){
        return selecionado;
    }

    public static void deselecionar(){
        selecionado = null;
    }

}
