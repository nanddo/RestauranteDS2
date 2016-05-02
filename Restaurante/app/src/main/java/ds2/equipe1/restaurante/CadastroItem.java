package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ds2.equipe1.restaurante.controles.ControleDeItem;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Item;

public class CadastroItem extends AppCompatActivity {

    private EditText edtNome,edtQuantidade,edtUnidade,edtLimiteMinimo;
    private Button btnCadastrar, btnExcluir;
    private ControleDeItem controleDeItem;
    private Item item;

    private boolean novoCadastro = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        init();

        item = new Item(this);
        controleDeItem = new ControleDeItem(this);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("alterar", false)){
            novoCadastro = false;
            carregarItem();
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
        edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
        edtUnidade = (EditText) findViewById(R.id.edtUnidade);
        edtLimiteMinimo = (EditText) findViewById(R.id.edtLimiteMinimo);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!novoCadastro && item.getId() != null) {
                    item.delete();
                    item.setId(null);
                    new Utils(CadastroItem.this).toast("Item excluído!");
                    finish();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarClick();
            }
        });
    }


    private void onCadastrarClick() {
        final String nome = edtNome.getText().toString();
        final int quantidade = Integer.parseInt(edtQuantidade.getText().toString());
        final String unidade = edtUnidade.getText().toString();
        final int limite = Integer.parseInt(edtLimiteMinimo.getText().toString());

        item.setNome(nome);
        item.setQuantidade(quantidade);
        item.setUnidade(unidade);
        item.setLimiteMinimo(limite);

        controleDeItem.salvarItem(item);

        if (item.getId() == null) {
            new Utils(this).toast("Item cadastrado!");
        } else {
            new Utils(this).toast("Item alterado!");
        }

    }

    private void carregarItem(){
        if (ControleDeItem.getSelecionado() != null) {
            CadastroItem.this.item = ControleDeItem.getSelecionado();

            edtNome.setText(item.getNome());
            edtQuantidade.setText(item.getQuantidade());
            edtUnidade.setText(item.getQuantidade());
            edtLimiteMinimo.setText(item.getLimiteMinimo());
        }
    }


}
