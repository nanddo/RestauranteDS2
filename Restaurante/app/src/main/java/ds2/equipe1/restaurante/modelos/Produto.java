package ds2.equipe1.restaurante.modelos;

import android.content.Context;

import java.util.ArrayList;

public class Produto extends Model<Produto> {
	private String nome;
	private float preco;
    private ArrayList<Ingrediente> ingredientes;

    public Produto(Context context, String nome, float preco, ArrayList<Ingrediente> ingredientes) {
        super(context);
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

	/*public static ArrayList<Produto> carregarProdutos() {
		ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();

		ArrayList<Produto> lista = new ArrayList<Produto>();

		//conexao com o banco estabelecida
		for (int i = 0; i < lista.count(); i++) {
			listaDeProdutos.add(i, new Produto(lista.nome, lista.preco, lista.id, ...));
		}

		return listaDeProdutos; //(???)
	}*/
    
	public boolean validarProduto() {
		/*for (int i = 0; i < ingredientes.size(); i++) {
            if (ingredientes.get(i).getQuantidade() > ControleDeItem.consultarItem(ingredientes.get(i).getItem().getNome(), new RequestCallback<Item>() {
                @Override
                public void execute(ArrayList<Item> itens) {
                    BuscaItem.this.itens.clear();
                    BuscaItem.this.itens.addAll(itens);
                    adapter.notifyDataSetChanged();
                    super.execute(itens);
                }
            }).getQuantidade()) {
				return false;
			}
        }*/
        return true;
    }

    //Deve ser chamado quando o garcom conclui o pedido.
    public void alertarSobreItensAbaixoDoLimite(){
        for (Ingrediente ingrediente : getIngredientes()){
            Item item = ingrediente.getItem();
            item.verificarItemAbaixoDoLimite(ingrediente.getQuantidade());
        }
    }
    
	public String getNome(){
        return nome;
    }

    public float getPreco(){
        return preco;
    }
    
    public ArrayList <Ingrediente> getIngredientes(){
        return ingredientes;
    }
    
    public void setPreco(float preco){
		this.preco = preco;
    }
}
