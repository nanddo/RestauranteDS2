package ds2.equipe1.restaurante;

/**
 * Created by Th on 24/03/2016.
 */
public class Item {
	
	private String nome;
	private int quantidade;
	private int limiteMinimo;
	
	public Item(String nome, int quantidade, int limiteMinimo){
		this.nome = nome;
		this.quantidade = quantidade;
		this.limiteMinimo = limiteMinimo;
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
    
	public void setNome(String nome){
		this.nome = nome;
  }
    
  public void setQuantidade(int quantidade){
    this.quantidade = quantidade;
  }
    
  public void setLimiteMinimo(int limiteMinimo){
		this.limiteMinimo = limiteMinimo;
  }
}
