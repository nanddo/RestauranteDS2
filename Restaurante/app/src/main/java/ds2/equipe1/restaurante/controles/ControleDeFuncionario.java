package ds2.equipe1.restaurante.controles;

import android.content.Context;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Funcionario;
import ds2.equipe1.restaurante.modelos.Item;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeFuncionario {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ControleDeImpressao controleDeImpressao;

    public ControleDeFuncionario(Context context){
        controleDeImpressao = new ControleDeImpressao(context);
    }

    public void cadastrarFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
        funcionario.save();

        //Banco de Dados
        //SQL (Sem o Endereço)
        //INSERT INTO funcionario (nome, telefone, cnpj, email) VALUES (funcionario.getNome(), funcionario.getTelefone(), funcionario.getCnpj(), funcionario.getEmail());
    }

    public boolean alterarFuncionario(Funcionario funcionario){
        if (funcionario.getId() != -1) {
            funcionario.save();
            return true;
        } else return false;

        //Banco de Dados
        //SQL (Sem o Endereço)
        //UPDATE funcionario SET nome = funcionario.getNome(), telefone = funcionario.getNome(), email = funcionario.getEmail WHERE cnpj = funcionario.getCnpj();
    }

    public void excluirFuncionario(Funcionario funcionario){
        funcionario.delete();
        funcionarios.remove(funcionario);
        //Banco de Dados
        //SQL
        //DELETE FROM funcionario WHERE cnpj = funcionario.getCnpj();
    }

    public ArrayList<Funcionario> consultarFuncionario(String consulta){
        ArrayList<Funcionario> resultado = new ArrayList<Funcionario>();
        for (Funcionario funcionario : this.funcionarios){
            if (funcionario.getNome().contains(consulta) || funcionario.getCpf().contains(consulta)){
                resultado.add(funcionario);
            }
        }
        return resultado;
        //Banco de Dados
        //SQL
        //SELECT id, nome, cnpj, telefone, email, id_endereco WHERE cnpj = funcionario.getCnpj() OR nome = funcionario.getNome();
    }

    public void relatorioFuncionario(Funcionario funcionario){
        //Banco de dados
        //Exibir Relatório
    }

    public void relatorioItensPorFuncionario(Item item, Funcionario funcionario){
        //Banco de Dados
        //Exibir Relatório
    }

}
