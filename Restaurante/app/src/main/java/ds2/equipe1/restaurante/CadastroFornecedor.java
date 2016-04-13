package ds2.equipe1.restaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Endereco;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class CadastroFornecedor extends AppCompatActivity {
    private ControleDeFornecedor controleDeFornecedor;
    private EditText edtNome, edtCNPJ, edtEndereco, edtEmail, edtTelefone;
    private Button btnCadastrar;
    private int idEndereco;

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

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarClick();
            }
        });
    }

    private void onCadastrarClick(){
        String nome = edtNome.getText().toString();
        String CNPJ = edtCNPJ.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();

        //TODO: Logica do endereço.
        //Endereco endereco = new Endereco(bla, bla, bla);
        //endereco.save();
        Fornecedor fornecedor = new Fornecedor(this, nome, telefone, CNPJ, email);
        controleDeFornecedor.cadastrarFornecedor(fornecedor);

        new Utils(this).toast("Fornecedor cadastrado!");

        finish();
    }
}
