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

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.listas.FornecedorAdapter;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class BuscaFornecedor extends AppCompatActivity {
    private ControleDeFornecedor controleDeFornecedor;

    private ListView lvFornecedores;
    private FornecedorAdapter adapter;
    private ArrayList<Fornecedor> todosFornecedores, fornecedoresFiltrados;
    private EditText edtProcurar;
    private ImageView ivProcurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fornecedor);

        init();

        controleDeFornecedor = new ControleDeFornecedor(this);

        todosFornecedores = new ArrayList<>();
        fornecedoresFiltrados = new ArrayList<>();
        adapter = new FornecedorAdapter(BuscaFornecedor.this, BuscaFornecedor.this.fornecedoresFiltrados);
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
            }
        });

        lvFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //no clique do item o android nos dá a view, a posição do item na lista e o ID do item (que nós configuramos)
            //então passamos esse ID para
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ControleDeFornecedor.selecionarParaEditar(fornecedoresFiltrados.get(position));

                Intent intent = new Intent(BuscaFornecedor.this, CadastroFornecedor.class);
                intent.putExtra("alterar", true);
                startActivity(intent);
            }
        });
    }

    private void consultar(final String consulta){
        if (consulta.isEmpty()) {
            controleDeFornecedor.consultarFornecedor(new RequestCallback<Fornecedor>(this) {
                @Override
                public void execute(ArrayList<Fornecedor> fornecedores) throws Exception {
                    super.execute(fornecedores);

                    todosFornecedores.clear();
                    fornecedoresFiltrados.clear();

                    todosFornecedores.addAll(fornecedores);
                    fornecedoresFiltrados.addAll(fornecedores);
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            fornecedoresFiltrados.clear();
            for (Fornecedor fornecedor : todosFornecedores){
                if (fornecedor.getNome().contains(consulta) || fornecedor.getCnpj().contains(consulta)){
                    fornecedoresFiltrados.add(fornecedor);
                }
            }

            adapter.notifyDataSetChanged();

            if (fornecedoresFiltrados.size() == 0){
                Toast.makeText(BuscaFornecedor.this, "Nenhum resultado para \"" + consulta + "\"", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        //verificar se o fornecedor foi excluido para remover da tela.
        for (int i = 0; i < fornecedoresFiltrados.size(); i++){
            if (fornecedoresFiltrados.get(i).getId() == null){
                fornecedoresFiltrados.remove(i--);
            }
        }

        //Quando a tela reabrir, atualizar a interface com as informações alteradas do item selecionado.
        adapter.notifyDataSetChanged();

        super.onResume();
    }
}
