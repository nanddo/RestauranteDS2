package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import ds2.equipe1.restaurante.listas.FornecedorAdapter;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class BuscaFornecedor extends AppCompatActivity {

    private ListView lvFornecedores;
    private FornecedorAdapter adapter;
    private ArrayList<Fornecedor> tempFornecedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fornecedor);

        init();
    }

    private void init(){
        lvFornecedores = (ListView) findViewById(R.id.lvFornecedores);
        tempFornecedores = new ArrayList<>();
        tempFornecedores.addAll(MenuPrincipal.getControleDeFornecedor().consultarFornecedor(""));
        adapter = new FornecedorAdapter(this, tempFornecedores);
        lvFornecedores.setAdapter(adapter);
    }

    public void onItemClick(View v){
        startActivity(new Intent(this, CadastroFornecedor.class));
    }
}
