package ds2.equipe1.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.listas.FornecedorAdapter;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class BuscaFornecedor extends AppCompatActivity {
    private ControleDeFornecedor controleDeFornecedor;

    private ListView lvFornecedores;
    private FornecedorAdapter adapter;
    //Fornecedores visiveis na tela
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
        adapter = new FornecedorAdapter(BuscaFornecedor.this, fornecedores);
        lvFornecedores.setAdapter(adapter);

        consultar(null);
    }

    private void init(){
        lvFornecedores = (ListView) findViewById(R.id.lvFornecedores);

        lvFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // no clique do item o android nos da a view, a posicao do item na lista e o ID do item
            // (que nos configuramos) entao passamos esse ID para
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ControleDeFornecedor.selecionarParaEditar(adapter.getItem(position));

                Intent intent = new Intent(BuscaFornecedor.this, CadastroFornecedor.class);
                intent.putExtra("alterar", true);
                startActivity(intent);
            }
        });
    }

    private void consultar(final String consulta){
        controleDeFornecedor.consultarFornecedor(consulta, new RequestCallback<Fornecedor>(this) {
            @Override
            public void execute(ArrayList<Fornecedor> fornecedores) throws Exception {
                super.execute(fornecedores);

                BuscaFornecedor.this.fornecedores.clear();
                BuscaFornecedor.this.fornecedores.addAll(fornecedores);

                adapter.notifyDataSetChanged();
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

    @Override
    protected void onResume() {
        //verificar se o fornecedor foi excluido para remover da tela.
        for (int i = 0; i < fornecedores.size(); i++){
            if (fornecedores.get(i).getId() == null){
                fornecedores.remove(i--);
            }
        }

        //Quando a tela reabrir, atualizar a interface com as informacoes alteradas do item selecionado.
        adapter.notifyDataSetChanged();

        super.onResume();
    }
}