package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ds2.equipe1.restaurante.controles.ControleDeEndereco;
import ds2.equipe1.restaurante.controles.ControleDeFuncionario;
import ds2.equipe1.restaurante.helpers.Utils;
import ds2.equipe1.restaurante.modelos.Funcionario;

public class CadastroFuncionario extends AppCompatActivity {

    private ControleDeFuncionario controleDeFuncionario;
    private EditText edtNome, edtCPF, edtEndereco, edtTelefone, edtNome_de_usuario;
    private Spinner spTipo;

    private Button btnCadastrar, btnCadastrarEndereco, btnExcluir;

    private Funcionario funcionario;
    private boolean novoCadastro = true;
    private String[] arraySpinner;

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
        spTipo = (Spinner) findViewById(R.id.edtTipo);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrarEndereco = (Button) findViewById(R.id.btnCadastrarEndereco);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        this.arraySpinner = new String[]{
                "Garcom", "Gerente"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adapter);

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
            Toast.makeText(CadastroFuncionario.this, "Necessario todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        final int tipo = spTipo.getSelectedItemPosition() + 1;

        funcionario.setNome(nome.trim());
        funcionario.setCpf(CPF.trim());
        funcionario.setNome_usuario(nome_de_usuario.trim());
        funcionario.setTelefone(telefone.replaceAll(" ", ""));
        funcionario.setTipo(tipo);
        controleDeFuncionario.salvarFuncionario(funcionario, null);

        if (funcionario.getId() == null) {
            new Utils(this).toast("Funcionario cadastrado!");
        } else {
            new Utils(this).toast("Funcionario alterado!");
        }

        ControleDeFuncionario.deselecionar();
        ControleDeEndereco.deselecionar();
        finish();
    }

    public void carregarFuncionario(){
        if (ControleDeFuncionario.getSelecionado() != null) {
            CadastroFuncionario.this.funcionario = ControleDeFuncionario.getSelecionado();

            edtNome.setText(funcionario.getNome());
            edtCPF.setText(funcionario.getCpf());
            edtNome_de_usuario.setText(funcionario.getNome_usuario().toLowerCase());
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
