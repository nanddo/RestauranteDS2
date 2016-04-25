package ds2.equipe1.restaurante;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ds2.equipe1.restaurante.controles.ControleDeProduto;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Ingrediente;
import ds2.equipe1.restaurante.modelos.Produto;

public class CadastroProduto extends AppCompatActivity {

    private ControleDeProduto controleDeProduto;
    private EditText edtNome, edtPreco;
    private Button btnCadastrar, btnCancelar, btnAddIngrediente;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        //this.context = ??? PEGAR CONTEXTO;

        init();

        controleDeProduto = new ControleDeProduto(this);
    }

    private void init(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtPreco = (EditText) findViewById(R.id.edtPreco);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnAddIngrediente = (Button) findViewById(R.id.btnAddIngrediente);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarClick();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelarClick();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddIngredienteClick();
            }
        });
    }

    private void onCadastrarClick(){
        final String nome = edtNome.getText().toString();
        final Float preco = Float.parseFloat(edtPreco.getText().toString());

        Produto produto = new Produto(context,nome,preco,ingredientes);


        new Utils(this).toast("Produto cadastrado!");
        finish();
    }

    private void onCancelarClick(){
        finish();
    }

    private void onAddIngredienteClick(){
        Intent intent = new Intent(this, SelecionarIngredientes.class);
        startActivityForResult(intent,1);

        //Implementar lista de seleção de ingredientes

        finish();
    }
}
