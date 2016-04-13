package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
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
        fornecedores.addAll(controleDeFornecedor.consultarFornecedor(""));
        adapter = new FornecedorAdapter(this, fornecedores);
        lvFornecedores.setAdapter(adapter);
    }

    private void init(){
        lvFornecedores = (ListView) findViewById(R.id.lvFornecedores);
        edtProcurar = (EditText) findViewById(R.id.edtProcurar);
        ivProcurar = (ImageView) findViewById(R.id.ivProcurar);
        ivProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String procurar = edtProcurar.getText().toString();

                fornecedores.clear();
                fornecedores.addAll(controleDeFornecedor.consultarFornecedor(procurar));

                adapter.notifyDataSetChanged();

                if (fornecedores.size() == 0){
                    Toast.makeText(BuscaFornecedor.this, "Nenhum resultado para \"" + procurar + "\"", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onItemClick(View v){
        startActivity(new Intent(this, CadastroFornecedor.class));
    }
}
