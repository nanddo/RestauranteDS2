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

import ds2.equipe1.restaurante.controles.ControleDeProduto;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.listas.ProdutoAdapter;
import ds2.equipe1.restaurante.modelos.Produto;

public class BuscaProduto extends AppCompatActivity {
    private ControleDeProduto controleDeProduto;

    private ListView lvProdutos;
    private ProdutoAdapter adapter;
    //Produtos visiveis na tela
    private ArrayList<Produto> produtos;
    private EditText edtProcurar;
    private ImageView ivProcurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_produto);

        init();

        controleDeProduto = new ControleDeProduto(this);

        produtos = new ArrayList<>();
        adapter = new ProdutoAdapter(BuscaProduto.this, produtos);
        lvProdutos.setAdapter(adapter);

        consultar(null);
    }
    private void init(){
        lvProdutos = (ListView) findViewById(R.id.lvProdutos);

        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // no clique do item o android nos da a view, a posicao do item na lista e o ID do item
            // (que nos configuramos) entao passamos esse ID para
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ControleDeProduto.selecionarParaEditar(adapter.getItem(position));

                Intent intent = new Intent(BuscaProduto.this, CadastroProduto.class);
                intent.putExtra("alterar", true);
                startActivity(intent);
            }
        });
    }

    private void consultar(final String consulta){
        controleDeProduto.consultarProduto(consulta, new RequestCallback<Produto>(this) {
            @Override
            public void execute(ArrayList<Produto> produtos) throws Exception {
                super.execute(produtos);

                BuscaProduto.this.produtos.clear();
                BuscaProduto.this.produtos.addAll(produtos);

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
        //verificar se o produto foi excluido para remover da tela.
        for (int i = 0; i < produtos.size(); i++){
            if (produtos.get(i).getId() == null){
                produtos.remove(i--);
            }
        }

        //Quando a tela reabrir, atualizar a interface com as informacoes alteradas do item selecionado.
        adapter.notifyDataSetChanged();

        super.onResume();
    }

    public void onItemClick(View v){
        startActivity(new Intent(this, CadastroProduto.class));
    }
}
