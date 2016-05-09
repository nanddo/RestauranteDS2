package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeFuncionario;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.listas.FuncionarioAdapter;
import ds2.equipe1.restaurante.modelos.Funcionario;

public class BuscaFuncionario extends AppCompatActivity {
    public static ControleDeFuncionario controleDeFuncionario;

    private ListView lvFuncionarios;
    private FuncionarioAdapter adapter;
    private ArrayList<Funcionario> funcionarios;
    private EditText edtProcurar;
    private ImageView ivProcurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_funcionario);

        init();

        controleDeFuncionario = new ControleDeFuncionario(this);

        funcionarios = new ArrayList<>();
        adapter = new FuncionarioAdapter(BuscaFuncionario.this, funcionarios);
        lvFuncionarios.setAdapter(adapter);

        consultar("");
    }
    private void init(){
        //pegar referencias dos componentes xml
        lvFuncionarios = (ListView) findViewById(R.id.lvFuncionarios);
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

        lvFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //no clique do item o android nos da a view, a posicao do item na lista e o ID do item (que nos configuramos)
            //entao passamos esse ID para
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ControleDeFuncionario.selecionarParaEditar(funcionarios.get(position));

                Intent intent = new Intent(BuscaFuncionario.this, CadastroFuncionario.class);
                intent.putExtra("alterar", true);
                startActivity(intent);
            }
        });
    }

    private void consultar(final String consulta){
        controleDeFuncionario.consultarFuncionario(consulta, new RequestCallback<Funcionario>(this) {
            @Override
            public void execute(ArrayList<Funcionario> funcionarios) throws Exception {
                super.execute(funcionarios);

                BuscaFuncionario.this.funcionarios.clear();
                BuscaFuncionario.this.funcionarios.addAll(funcionarios);

                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        //verificar se o Funcionario foi excluido para remover da tela.
        for (int i = 0; i < funcionarios.size(); i++){
            if (funcionarios.get(i).getId() == null){
                funcionarios.remove(i--);
            }
        }

        //Quando a tela reabrir, atualizar a interface com as informacoes alteradas do item selecionado.
        adapter.notifyDataSetChanged();

        super.onResume();
    }



    public void onItemClick(View v){
        startActivity(new Intent(this, CadastroFuncionario.class));
    }


}
