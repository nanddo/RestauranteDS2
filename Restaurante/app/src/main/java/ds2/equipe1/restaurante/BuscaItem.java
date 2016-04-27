package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeItem;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.listas.ItemAdapter;
import ds2.equipe1.restaurante.modelos.Item;


public class BuscaItem extends AppCompatActivity {

    private ControleDeItem controleDeItem;

    private ListView lvItens;
    private ItemAdapter adapter;

    private ArrayList<Item> itens;
    private EditText edtProcurar;
    private ImageView ivProcurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fornecedor);

        init();

        controleDeItem = new ControleDeItem(this);

        itens = new ArrayList<>();
        adapter = new ItemAdapter(BuscaItem.this, itens);
        lvItens.setAdapter(adapter);

        consultar("");
    }

    private void init(){
        //pegar referências dos componentes xml
        lvItens = (ListView) findViewById(R.id.lvFornecedores);
        edtProcurar = (EditText) findViewById(R.id.edtProcurar);
        ivProcurar = (ImageView) findViewById(R.id.ivProcurar);

        //atribuir funcionalidades para alguns componentes
        ivProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtProcurar.getText().toString();

                consultar(texto);

                if (itens.size() == 0){
                    Toast.makeText(BuscaItem.this, "Nenhum resultado para \"" + texto + "\"", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //no clique do item o android nos dá a view, a posição do item na lista e o ID do item (que nós configuramos)
            //então passamos esse ID para
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BuscaItem.this, CadastroItem.class);
                Toast.makeText(BuscaItem.this, "" + id, Toast.LENGTH_SHORT).show();
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
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
