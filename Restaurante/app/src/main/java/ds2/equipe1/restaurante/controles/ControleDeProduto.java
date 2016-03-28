package ds2.equipe1.restaurante.controles;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Produto;

/**
 * Created by Th on 24/03/2016.
 */
public class ControleDeProduto {
	
	private ArrayList <Produto> produtos;
	
	public ControleDeProduto(){
		//classe estatica tem construtor?
		// Fernando Melo: deixei a classe sem ser est�tica por enquanto
    }
    
    public void carregarProdutosDoBanco(){
		produtos = new ArrayList <Produto>();
		int tamanhoDoCardapio = 5; //Ler o tamanho do banco
		
		for (int i = 0; i < tamanhoDoCardapio; i++) {
			String nome = "teste"; //Ler o nome do banco
            produtos.add(new Produto(nome));
        }
	}
    
    public void cadastrarProduto(String nome, float preco, /*int id,*/ ArrayList <Item> ingredientes){
		produtos.add(new Produto(nome, preco, ingredientes));
    }
    
    public void excluirProduto(String nome){
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome() == nome) {
				//Remover do banco
				produtos.remove(i); //ver se e este mesmo o metodo de remocao
			}
        }
    }
    
    public void alterarPrecoDeProduto(String nome, float novoPreco){
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome() == nome) {
				//Alterar preco no banco
				produtos.get(i).setPreco(novoPreco);
			}
        }
    }
    
    public Produto consultarProduto(String nome){
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome() == nome) {
				return produtos.get(i);
			}
        }
        return null;
    }
    
	public void relatorioProdutos(){
        //Gerar pdf?
    }
    
	public void relatorioItensPorProduto(){
        //Gerar pdf?
    }
	
}