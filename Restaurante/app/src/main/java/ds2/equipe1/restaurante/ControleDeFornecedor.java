package ds2.equipe1.restaurante;

/**
 * Created by Th on 24/03/2016.
 */

public class ControleDeFornecedor {

    public void cadastrarFornecedor(Fornecedor fornecedor){
        //Banco de Dados
        //SQL (Sem o Endereço)
        //INSERT INTO fornecedor (nome, telefone, cnpj, email) VALUES (fornecedor.getNome(), fornecedor.getTelefone(), fornecedor.getCnpj(), fornecedor.getEmail());
    }

    public void alterarFornecedor(Fornecedor fornecedor){
        //Banco de Dados
        //SQL (Sem o Endereço)
        //UPDATE fornecedor SET nome = fornecedor.getNome(), telefone = fornecedor.getNome(), email = fornecedor.getEmail WHERE cnpj = fornecedor.getCnpj();
    }

    public void excluirFornecedor(Fornecedor fornecedor){
        //Banco de Dados
        //SQL
        //DELETE FROM fornecedor WHERE cnpj = fornecedor.getCnpj();
    }

    public void consultarFornecedor(Fornecedor fornecedor){
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
