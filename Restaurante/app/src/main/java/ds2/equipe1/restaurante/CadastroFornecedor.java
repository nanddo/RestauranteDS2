package ds2.equipe1.restaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Endereco;
import ds2.equipe1.restaurante.modelos.Fornecedor;

public class CadastroFornecedor extends AppCompatActivity {
    private EditText edtNome, edtCNPJ, edtEndereco, edtEmail, edtTelefone;
    private int idEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_fornecedor);

        init();
    }

    private void init(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCNPJ = (EditText) findViewById(R.id.edtCNPJ);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
    }

    public void onCadastrarClick(View v){
        String nome = edtNome.getText().toString();
        String CNPJ = edtCNPJ.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();

        Fornecedor fornecedor = new Fornecedor(this, nome, telefone, CNPJ, email);
        MenuPrincipal.getControleDeFornecedor().cadastrarFornecedor(fornecedor);

        new Utils(this).toast("Fornecedor cadastrado!");
        finish();
    }
}
