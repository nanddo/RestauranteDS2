package ds2.equipe1.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeItem;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.listas.ItemAdapter;
import ds2.equipe1.restaurante.modelos.Item;


public class BuscaItem extends AppCompatActivity {

    private ControleDeItem controleDeItem;

    private ListView lvItens;
    private ItemAdapter adapter;

    private ArrayList<Item> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fornecedor);

        init();

        controleDeItem = new ControleDeItem(this);

        itens = new ArrayList<>();
        adapter = new ItemAdapter(BuscaItem.this, itens);
        lvItens.setAdapter(adapter);

        consultar(null);
    }

    private void init(){
        //pegar referencias dos componentes xml
        lvItens = (ListView) findViewById(R.id.lvFornecedores);

        lvItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ControleDeItem.selecionarParaEditar(adapter.getItem(position));
                Intent intent = new Intent(BuscaItem.this, CadastroItem.class);
                intent.putExtra("alterar", true);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.busca, menu);
        Utils.prepararSearchMenu(this, menu, new Utils.DialogCallback() {
            @Override
            public void execute(String text) {
                consultar(text);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void consultar(String consulta){
        controleDeItem.consultarItem(consulta, new RequestCallback<Item>(){
            @Override
            public void execute(ArrayList<Item> itens) throws Exception {
                BuscaItem.this.itens.clear();
                BuscaItem.this.itens.addAll(itens);
                adapter.notifyDataSetChanged();

                super.execute(itens);
            }
        });
    }
}
