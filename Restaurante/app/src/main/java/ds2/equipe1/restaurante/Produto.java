package ds2.equipe1.restaurante;

/**
 * Created by Th on 24/03/2016.
 */
public class Produto {

	private String nome;
    private float preco;
    private int id;
    private ArrayList <Item> itens = new ArrayList <Item>();
    
	public Produto(String nome, float preco, int id, ArrayList <Item> itens){
		this.nome = nome;
		this.preco = preco;
		this.id = id;
		this.itens = itens;
    }
    
	public boolean validarProduto(){
		for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getQuantidade() < 0) {
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
    
	public int getId(){
        return id;
    }
    
    public ArrayList <Item> getItens(){
        return itens;
    }
    
	public void setNome(String nome){
		this.nome = nome;
    }
    
    public void setPreco(float preco){
		this.preco = preco;
    }
    
    public void setId(int id){
		this.id = id;
    }
    
    public void setItens(ArrayList <Item> itens){
		this.itens = itens;
    }
}
