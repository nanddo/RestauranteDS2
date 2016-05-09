package ds2.equipe1.restaurante.controles;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Compra;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

public class ControleDeCompra {
	//Lista com a consulta mais recente de compras no servidor.
	private ArrayList<Compra> compras = new ArrayList<>();
	private Context context;
	private static Compra selecionada;

	public ControleDeCompra(Context context){
		this.context = context;
	}

	public static void salvarCompra(Compra compra) {
		compra.save();
	}

	public static void excluirCompra(Compra compra) {
		compra.delete();
	}

	public void consultarCompra(String consulta, final RequestCallback<Compra> callback) {
		if (consulta.isEmpty()) {
			Model.find(context, Compra.class, new TypeToken<ArrayList<Compra>>() {
			}.getType(), new RequestCallback<Compra>() {
				@Override
				public void execute(ArrayList<Compra> lista) throws Exception {
					super.execute(lista);

					compras.clear();
					compras.addAll(lista);

					if (callback != null) {
						callback.execute(lista);
					}
				}
			}, null);
		} else {
			try {
				ArrayList<Compra> comprasFiltradas = new ArrayList<>();

				for (Compra compra : compras) {
					if (compra.getNomeDoItem().contains(consulta)) {
						comprasFiltradas.add(compra);
					}
				}

				if (callback != null) {
					callback.execute(comprasFiltradas);
				}
			} catch (Exception e) {
				Log.e(Utils.TAG, "Erro ao consultar fornecedores: ", e);
			}
		}
	}

	public static void selecionarParaEditar(Compra compra){
		ControleDeCompra.selecionada = compra;
	}

	public static Compra getSelecionada(){
		return selecionada;
	}

	public static void deselecionar(){
		selecionada = null;
	}
}
