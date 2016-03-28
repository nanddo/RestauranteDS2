package ds2.equipe1.restaurante.modelos;

/**
 * Created by Th on 24/03/2016.
 */
public class Item {
	
	private String nome;
	private int quantidade;
	private int limiteMinimo;
	private boolean emUso;
	
	public Item(String nome, int quantidade, int limiteMinimo){
		this.nome = nome;
		this.quantidade = quantidade;
		this.limiteMinimo = limiteMinimo;
		emUso = false;
    }
    
    public Item (String nome){
		//criar objeto baseado nos dados do banco
	}
    
    public boolean AlertarItemAbaixoDoLimite(){
        if (quantidade < limiteMinimo) {
			return true;
		} else {
			return false;
		}
    }
    
	public String getNome(){
        return nome;
    }

    public int getQuantidade(){
        return quantidade;
    }
    
	public int getLimiteMinimo(){
        return limiteMinimo;
    }
    
    /* Nome deve ser imutavel
	public void setNome(String nome){
		this.nome = nome;
    }
    */
    
    public void setQuantidade(int quantidade){
		//alterar quantidade no banco
		this.quantidade = quantidade;
    }
    
    public void setLimiteMinimo(int limiteMinimo){
		//alterar limiteMinimo no banco
		this.limiteMinimo = limiteMinimo;
    }
    
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
