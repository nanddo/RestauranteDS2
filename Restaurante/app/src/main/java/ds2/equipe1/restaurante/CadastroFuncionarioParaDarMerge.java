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
import ds2.equipe1.restaurante.controles.ControleDeFuncionario;
import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Funcionario;
import ds2.equipe1.restaurante.modelos.Model;

public class CadastroFuncionarioParaDarMerge extends AppCompatActivity {

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
                    new Utils(CadastroFuncionarioParaDarMerge.this).toast("Funcionario excluido!");
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
        if (funcionario.getEndereco() != null) {
            ControleDeEndereco.selecionarParaEditar(funcionario.getEndereco());
            intent.putExtra("alterar", true);
        }
        startActivityForResult(intent,1);
    }

    private void onCadastrarClick(){
        final String nome = edtNome.getText().toString();
        final String CPF = edtCPF.getText().toString();
        final String telefone = edtTelefone.getText().toString();
        final String nome_de_usuario = edtNome_de_usuario.getText().toString();

        if (funcionario.getEndereco() == null || nome.isEmpty() || CPF.isEmpty() || nome_de_usuario.isEmpty() || telefone.isEmpty()){
            Toast.makeText(CadastroFuncionarioParaDarMerge.this, "Necessario todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        funcionario.setNome(nome);
        funcionario.setCpf(CPF);
        funcionario.setNome_de_usuario(nome_de_usuario);
        funcionario.setTelefone(telefone);

        controleDeFuncionario.salvarFuncionario(funcionario, new RequestCallback<Model>() {
            @Override
            public void execute(Model object) throws Exception {
                if (funcionario.getId() == null) {
                    new Utils(CadastroFuncionarioParaDarMerge.this).toast("Funcionario cadastrado!");
                } else {
                    new Utils(CadastroFuncionarioParaDarMerge.this).toast("Funcionario alterado!");
                }

                ControleDeFornecedor.deselecionar();
                ControleDeEndereco.deselecionar();

                finish();
                super.execute(object);
            }
        });

    }

    public void carregarFuncionario(){
        if (ControleDeFuncionario.getSelecionado() != null) {
            CadastroFuncionarioParaDarMerge.this.funcionario = ControleDeFuncionario.getSelecionado();

            edtNome.setText(funcionario.getNome());
            edtCPF.setText(funcionario.getCpf());
            edtNome_de_usuario.setText(funcionario.getNome_de_usuario());
            edtTelefone.setText(funcionario.getTelefone());
            if (funcionario.getEndereco() != null) {
                btnCadastrarEndereco.setText("Alterar");
                edtEndereco.setText(funcionario.getEndereco().getRua());
            } else {
                btnCadastrarEndereco.setText("Cadastrar");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            funcionario.setEndereco(ControleDeEndereco.getSelecionado());
            edtEndereco.setText(funcionario.getEndereco().getRua());
            btnCadastrarEndereco.setText("Alterar");
        }
    }
}
