package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ds2.equipe1.restaurante.controles.ControleDeFuncionario;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Funcionario;

public class CadastroFuncionario extends AppCompatActivity {

    private ControleDeFuncionario controleDeFuncionario;
    private EditText edtNome, edtCPF, edtEndereco, edtTelefone, edtNome_de_usuario;
    private Button btnCadastrar, btnCadastrarEndereco, btnExcluir;

    private Funcionario funcionario;
    private boolean novoCadastro = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        init();

        funcionario = new Funcionario(this);
        controleDeFuncionario = new ControleDeFuncionario(this);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("alterar", false)){
            novoCadastro = false;
            carregarFuncionario();
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
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtNome_de_usuario = (EditText) findViewById(R.id.edtNome_de_usuario);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
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
                if (!novoCadastro && funcionario.getId() != null) {
                    funcionario.delete();
                    funcionario.setId(null);
                    new Utils(CadastroFuncionario.this).toast("Funcionario excluido!");
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
        final String CPF = edtCPF.getText().toString();
        final String telefone = edtTelefone.getText().toString();
        final String nome_de_usuario = edtNome_de_usuario.getText().toString();

        funcionario.setNome(nome);
        funcionario.setCpf(CPF);
        funcionario.setNome_de_usuario(nome_de_usuario);
        funcionario.setTelefone(telefone);
        controleDeFuncionario.salvarFuncionario(funcionario);

        if (funcionario.getId() == null) {
            new Utils(this).toast("Funcionario cadastrado!");
        } else {
            new Utils(this).toast("Funcionario alterado!");
        }

        ControleDeFuncionario.deselecionar();
        finish();
    }

    public void carregarFuncionario(){
        if (ControleDeFuncionario.getSelecionado() != null) {
            CadastroFuncionario.this.funcionario = ControleDeFuncionario.getSelecionado();

            edtNome.setText(funcionario.getNome());
            edtCPF.setText(funcionario.getCpf());
            edtNome_de_usuario.setText(funcionario.getNome_de_usuario());
            edtTelefone.setText(funcionario.getTelefone());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            if (data.hasExtra("rua")) {
                edtEndereco.setText(data.getStringExtra("rua"));
            }
            if (data.hasExtra("id_endereco")) {
                funcionario.setIdEndereco(data.getIntExtra("id_endereco", -1));
            }
        }
    }
}
