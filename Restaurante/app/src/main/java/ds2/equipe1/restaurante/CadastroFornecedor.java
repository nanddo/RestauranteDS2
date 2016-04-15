package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Endereco;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class CadastroFornecedor extends AppCompatActivity {
    private ControleDeFornecedor controleDeFornecedor;
    private EditText edtNome, edtCNPJ, edtEndereco, edtEmail, edtTelefone;
    private Button btnCadastrar, btnCadastrarEndereco;
    private Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_fornecedor);

        init();

        controleDeFornecedor = new ControleDeFornecedor(this);
    }

    private void init(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCNPJ = (EditText) findViewById(R.id.edtCNPJ);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrarEndereco = (Button) findViewById(R.id.btnCadastrarEndereco);

        btnCadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarEnderecoClick();
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
        startActivityForResult(intent, 1);
    }

    private void onCadastrarClick(){
        final String nome = edtNome.getText().toString();
        final String CNPJ = edtCNPJ.getText().toString();
        final String email = edtEmail.getText().toString();
        final String telefone = edtTelefone.getText().toString();

/*        endereco.save(new RequestCallback() {
            @Override
            public void execute() {
                Fornecedor fornecedor = new Fornecedor(CadastroFornecedor.this, nome, telefone, CNPJ, email);
                controleDeFornecedor.cadastrarFornecedor(fornecedor);
            }
        });*/

        Fornecedor fornecedor = new Fornecedor(CadastroFornecedor.this, nome, telefone, CNPJ, email);
        controleDeFornecedor.cadastrarFornecedor(fornecedor);

        new Utils(this).toast("Fornecedor cadastrado!");

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            edtEndereco.setText(data.getStringExtra("rua"));
            //String enderecoSerializado = data.getStringExtra("endereco");
            //this.endereco = new Gson().fromJson(enderecoSerializado, Endereco.class);
        }
    }
}
