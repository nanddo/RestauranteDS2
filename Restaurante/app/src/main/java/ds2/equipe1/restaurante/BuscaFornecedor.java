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

import com.androidquery.callback.AjaxCallback;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.listas.FornecedorAdapter;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class BuscaFornecedor extends AppCompatActivity {

    private ControleDeFornecedor controleDeFornecedor;


    private ListView lvFornecedores;
    private FornecedorAdapter adapter;
    private ArrayList<Fornecedor> fornecedores;
    private EditText edtProcurar;
    private ImageView ivProcurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fornecedor);

        init();

        controleDeFornecedor = new ControleDeFornecedor(this);

        fornecedores = new ArrayList<>();
        adapter = new FornecedorAdapter(BuscaFornecedor.this, BuscaFornecedor.this.fornecedores);
        lvFornecedores.setAdapter(adapter);

        consultar("");
    }

    private void init(){
        //pegar referências dos componentes xml
        lvFornecedores = (ListView) findViewById(R.id.lvFornecedores);
        edtProcurar = (EditText) findViewById(R.id.edtProcurar);
        ivProcurar = (ImageView) findViewById(R.id.ivProcurar);

        //atribuir funcionalidades para alguns componentes
        ivProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtProcurar.getText().toString();

                consultar(texto);

                if (fornecedores.size() == 0){
                    Toast.makeText(BuscaFornecedor.this, "Nenhum resultado para \"" + texto + "\"", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //no clique do item o android nos dá a view, a posição do item na lista e o ID do item (que nós configuramos)
            //então passamos esse ID para
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BuscaFornecedor.this, CadastroFornecedor.class);
                Toast.makeText(BuscaFornecedor.this, "" + id, Toast.LENGTH_SHORT).show();
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void consultar(String consulta){
        controleDeFornecedor.consultarFornecedor(consulta, new RequestCallback<Fornecedor>(){
            @Override
            public void execute(ArrayList<Fornecedor> fornecedores) {
                BuscaFornecedor.this.fornecedores.clear();
                BuscaFornecedor.this.fornecedores.addAll(fornecedores);
                adapter.notifyDataSetChanged();

                super.execute(fornecedores);
            }
        });
    }
}
