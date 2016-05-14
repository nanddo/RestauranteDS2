package ds2.equipe1.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ds2.equipe1.restaurante.controles.ControleDeEndereco;
import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Model;

public class CadastroFornecedor extends AppCompatActivity {
    private ControleDeFornecedor controleDeFornecedor;
    private EditText edtNome, edtCNPJ, edtEndereco, edtEmail, edtTelefone;
    private Button btnCadastrar, btnCadastrarEndereco, btnExcluir;

    private Fornecedor fornecedor;
    private boolean novoCadastro = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_fornecedor);

        init();

        fornecedor = new Fornecedor(this);
        controleDeFornecedor = new ControleDeFornecedor(this);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("alterar", false)){
            novoCadastro = false;
            carregarFornecedor();
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
        edtCNPJ = (EditText) findViewById(R.id.edtCNPJ);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrarEndereco = (Button) findViewById(R.id.btnCadastrarEndereco);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        btnCadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarEnderecoClick();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!novoCadastro && fornecedor.getId() != null) {
                    fornecedor.delete();
                    fornecedor.setId(null);
                    new Utils(CadastroFornecedor.this).toast("Fornecedor excluido!");
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

    private void onCadastrarEnderecoClick(){
        Intent intent = new Intent(this, CadastroEndereco.class);
        if (fornecedor.getEndereco() != null) {
            ControleDeEndereco.selecionarParaEditar(fornecedor.getEndereco());
            intent.putExtra("alterar", true);
        }
        startActivityForResult(intent,1);
    }

    private void onCadastrarClick(){
        final String nome = edtNome.getText().toString();
        final String CNPJ = edtCNPJ.getText().toString();
        final String email = edtEmail.getText().toString();
        final String telefone = edtTelefone.getText().toString();

        if (fornecedor.getEndereco() == null || nome.isEmpty() || CNPJ.isEmpty() || email.isEmpty() || telefone.isEmpty()){
            Toast.makeText(CadastroFornecedor.this, "Necessario todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        fornecedor.setNome(nome.trim());
        fornecedor.setCnpj(CNPJ.trim());
        fornecedor.setEmail(email.trim());
        fornecedor.setTelefone(telefone.replaceAll(" ", ""));
        controleDeFornecedor.salvarFornecedor(fornecedor, null);

        if (fornecedor.getId() == null) {
            new Utils(CadastroFornecedor.this).toast("Fornecedor cadastrado!");
        } else {
            new Utils(CadastroFornecedor.this).toast("Fornecedor alterado!");
        }

        ControleDeFornecedor.deselecionar();
        ControleDeEndereco.deselecionar();

        finish();
    }

    public void carregarFornecedor(){
        if (ControleDeFornecedor.getSelecionado() != null) {
            CadastroFornecedor.this.fornecedor = ControleDeFornecedor.getSelecionado();

            edtNome.setText(fornecedor.getNome());
            edtCNPJ.setText(fornecedor.getCnpj());
            edtTelefone.setText(fornecedor.getTelefone());
            edtEmail.setText(fornecedor.getEmail());
            if (fornecedor.getEndereco() != null) {
                btnCadastrarEndereco.setText("Alterar");
                edtEndereco.setText(fornecedor.getEndereco().getRua());
            } else {
                btnCadastrarEndereco.setText("Cadastrar");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            fornecedor.setEndereco(ControleDeEndereco.getSelecionado());
            edtEndereco.setText(fornecedor.getEndereco().getRua());
            btnCadastrarEndereco.setText("Alterar");
        }
    }
}
