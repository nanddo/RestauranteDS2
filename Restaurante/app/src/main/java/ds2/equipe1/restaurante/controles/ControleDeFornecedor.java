package ds2.equipe1.restaurante.controles;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;

/**
 * Created by Th on 24/03/2016.
 */

public class ControleDeFornecedor {
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();

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

    public ArrayList<Fornecedor> consultarFornecedor(String consulta){
        ArrayList<Fornecedor> resultado = new ArrayList<Fornecedor>();
        for (Fornecedor fornecedor : this.fornecedores){
            if (consulta == null || consulta.isEmpty() || fornecedor.getNome().contains(consulta) || fornecedor.getCnpj().contains(consulta)){
                resultado.add(fornecedor);
            }
        }
        return resultado;
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
