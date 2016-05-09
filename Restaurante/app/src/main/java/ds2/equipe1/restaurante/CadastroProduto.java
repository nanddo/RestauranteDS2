package ds2.equipe1.restaurante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeItem;
import ds2.equipe1.restaurante.controles.ControleDeProduto;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Ingrediente;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Produto;

public class CadastroProduto extends AppCompatActivity {

    private ControleDeProduto controleDeProduto;
    private ControleDeItem controleDeItem;
    private EditText edtNome, edtPreco;
    private Button btnCadastrar, btnCancelar;
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
    }

    private void init(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtPreco = (EditText) findViewById(R.id.edtPreco);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnAddIngrediente = (ImageButton) findViewById(R.id.btnAddIngrediente);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarClick();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelarClick();
            }
        });

        btnAddIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddIngredienteClick();
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

        produto.save();

        new Utils(this).toast("Produto cadastrado!");
        finish();
    }

    private void onCancelarClick(){
        finish();
    }

    private void onAddIngredienteClick(){
        new Utils(this).selectPopup("Cadastrar ingrediente", new Utils.IngredienteCallback() {
            @Override
            public void execute(Item item, int quantidade) {
                produto.addIngrediente(new Ingrediente(CadastroProduto.this, item, quantidade));
            }
        }, itens);

        //Intent intent = new Intent(this, SelecionarIngredientes.class);
        //startActivityForResult(intent,1);

        //Implementar lista de selecao de ingredientes
    }
}
