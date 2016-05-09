package ds2.equipe1.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.controles.ControleDeItem;
import ds2.equipe1.restaurante.controles.ControleDeProduto;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.listas.IngredienteAdapter;
import ds2.equipe1.restaurante.modelos.Ingrediente;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Produto;

public class CadastroProduto extends AppCompatActivity {

    private ControleDeProduto controleDeProduto;
    private ControleDeItem controleDeItem;
    private EditText edtNome, edtPreco;
    private ListView lvIngredientes;
    private IngredienteAdapter adapter;
    private Button btnCadastrar, btnExcluir;
    private ImageButton btnAddIngrediente;
    private ArrayList<Item> itens = new ArrayList<>(); //apenas para o dropdown
    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        init();

        produto = new Produto(this);

        controleDeProduto = new ControleDeProduto(this);
        controleDeItem = new ControleDeItem(this);

        controleDeItem.consultarItem(null, new RequestCallback<Item>() {
            @Override
            public void execute(ArrayList<Item> lista) throws Exception {
                itens.clear();
                itens.addAll(lista);
                super.execute(lista);
            }
        });


        boolean novoCadastro = true;
        Intent intent = getIntent();
        if (intent.getBooleanExtra("alterar", false)){
            novoCadastro = false;
            carregarProduto();
        }

        if (novoCadastro){
            btnExcluir.setVisibility(View.GONE);
        } else {
            btnCadastrar.setText("Alterar");
            btnExcluir.setVisibility(View.VISIBLE);
        }
    }

    private void init(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtPreco = (EditText) findViewById(R.id.edtPreco);
        lvIngredientes = (ListView) findViewById(R.id.lvIngredientes);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        //btnAddIngrediente = (ImageButton) findViewById(R.id.btnAddIngrediente);

        adapter = new IngredienteAdapter(this, ingredientes);
        lvIngredientes.setAdapter(adapter);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarClick();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelarClick();
            }
        });
    }

    private void onCadastrarClick(){
        final String nome = edtNome.getText().toString();
        final Float preco = Float.parseFloat(edtPreco.getText().toString());

        if (nome.isEmpty()){
            Toast.makeText(CadastroProduto.this, "Necessario todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setIngredientes(ingredientes);

        produto.save();

        new Utils(this).toast("Produto cadastrado!");
        finish();
    }

    private void onCancelarClick(){
        finish();
    }

    public void onAddIngredienteClick(){
        new Utils(this).selectPopup("Cadastrar ingrediente", new Utils.IngredienteCallback() {
            @Override
            public void execute(Item item, int quantidade) {
                ingredientes.add(new Ingrediente(CadastroProduto.this, item, quantidade));
                adapter.notifyDataSetChanged();
            }
        }, itens);
    }

    private void carregarProduto(){
        if (ControleDeProduto.getSelecionado() != null) {
            produto = ControleDeProduto.getSelecionado();

            edtNome.setText(produto.getNome());
            edtPreco.setText(""+produto.getPreco());
            ingredientes.clear();
            ingredientes.addAll(produto.getIngredientes());
            adapter.notifyDataSetChanged();
        }
    }
}
