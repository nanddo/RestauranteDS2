package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Fornecedor;

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
        startActivityForResult(intent,1);
    }

    private void onCadastrarClick(){
        final String nome = edtNome.getText().toString();
        final String CNPJ = edtCNPJ.getText().toString();
        final String email = edtEmail.getText().toString();
        final String telefone = edtTelefone.getText().toString();

/*        endereco.save(new RequestCallback() {
            @Override
            public void execute() {
                controleDeFornecedor.salvarFornecedor(fornecedor);
            }
        });*/

        fornecedor.setNome(nome);
        fornecedor.setCnpj(CNPJ);
        fornecedor.setEmail(email);
        fornecedor.setTelefone(telefone);
        controleDeFornecedor.salvarFornecedor(fornecedor);

        if (fornecedor.getId() == null) {
            new Utils(this).toast("Fornecedor cadastrado!");
        } else {
            new Utils(this).toast("Fornecedor alterado!");
        }

        ControleDeFornecedor.deselecionar();
        finish();
    }

    public void carregarFornecedor(){
        if (ControleDeFornecedor.getSelecionado() != null) {
            CadastroFornecedor.this.fornecedor = ControleDeFornecedor.getSelecionado();

            edtNome.setText(fornecedor.getNome());
            edtCNPJ.setText(fornecedor.getCnpj());
            edtTelefone.setText(fornecedor.getTelefone());
            edtEmail.setText(fornecedor.getEmail());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            if (data.hasExtra("rua")) {
                edtEndereco.setText(data.getStringExtra("rua"));
            }
            /*if (data.hasExtra("id_endereco")) {
                fornecedor.setIdEndereco(data.getIntExtra("id_endereco", -1));
            }*/
        }
    }
}
