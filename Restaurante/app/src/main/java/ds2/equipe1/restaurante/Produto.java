package ds2.equipe1.restaurante;

/**
 * Created by Th on 24/03/2016.
 */
public class Produto {

	private String nome;
    private float preco;
    //private int id;
    private ArrayList <Item> ingredientes;
    private boolean emUso;
    
	public Produto(String nome, float preco, /*int id,*/ ArrayList <Item> ingredientes){
		this.nome = nome;
		this.preco = preco;
		//this.id = id;
		this.ingredientes = ingredientes;
    }
    
    public Produto(String nome){
		//criar objeto baseado nos dados do banco
	}
    
	public boolean validarProduto(){
		for (int i = 0; i < ingredientes.size(); i++) {
            if (ingredientes.get(i).getQuantidade() > ControleDeItem.consultarItem(ingredientes.get(i).getNome()).getQuantidade()) {
				return false;
			}
        }
        return true;
    }
    
	public String getNome(){
        return nome;
    }

    public float getPreco(){
        return preco;
    }
    
    /*
	public int getId(){
        return id;
    }
    */
    
    public ArrayList <Item> getItens(){
        return itens;
    }
    
    /* Nome deve ser imutavel
	public void setNome(String nome){
		this.nome = nome;
    }
    */
    
    public void setPreco(float preco){
		this.preco = preco;
    }
    
    /*
    public void setId(int id){
		this.id = id;
    }
    */
    
    /*
    public void setItens(ArrayList <Item> itens){
		this.itens = itens;
    }
    */
    
	public boolean solicitarUso(){
		if (emUso) {
			return false;
		} else {
			emUso = true;
			return true;
		}
	}
	
	public void liberarUso(){
		emUso = false;
	}
}
