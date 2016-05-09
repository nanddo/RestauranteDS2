package ds2.equipe1.restaurante;

//(edt.*?)\.setText\(endereco.get(.*?)\(\)\);
//endereco.set$2($1.getText().toString());

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ds2.equipe1.restaurante.controles.ControleDeEndereco;
import ds2.equipe1.restaurante.modelos.Endereco;

public class CadastroEndereco extends AppCompatActivity {
    private EditText edtCEP, edtLogradouro, edtRua, edtBairro, edtCidade, edtEstado, edtNumero;
    private Button btnCadastrar;
    private Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        init();

        Intent intent = getIntent();

        if (intent.getBooleanExtra("alterar", false)){
            carregarEndereco();
            btnCadastrar.setText("Alterar");
        } else {
            endereco = new Endereco(this);
            ControleDeEndereco.selecionarParaEditar(endereco);
        }
    }

    private void init(){
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        edtRua = (EditText) findViewById(R.id.edtRua);
        edtCEP = (EditText) findViewById(R.id.edtCEP);
        edtLogradouro = (EditText) findViewById(R.id.edtLogradouro);
        edtBairro = (EditText) findViewById(R.id.edtBairro);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtEstado = (EditText) findViewById(R.id.edtEstado);
        edtNumero = (EditText) findViewById(R.id.edtNumero);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                endereco.setLogradouro(edtLogradouro.getText().toString());
                endereco.setRua(edtRua.getText().toString());
                endereco.setCep(edtCEP.getText().toString());
                endereco.setLogradouro(edtLogradouro.getText().toString());
                endereco.setBairro(edtBairro.getText().toString());
                endereco.setCidade(edtCidade.getText().toString());
                endereco.setEstado(edtEstado.getText().toString());
                endereco.setNumero(Integer.parseInt(edtNumero.getText().toString()));

                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    private void carregarEndereco(){
        endereco = ControleDeEndereco.getSelecionado();
        if (endereco != null){
            edtLogradouro.setText(endereco.getLogradouro());
            edtRua.setText(endereco.getRua());
            edtCEP.setText(endereco.getCep());
            edtLogradouro.setText(endereco.getLogradouro());
            edtBairro.setText(endereco.getBairro());
            edtCidade.setText(endereco.getCidade());
            edtEstado.setText(endereco.getEstado());
            edtNumero.setText(String.valueOf(endereco.getNumero()));
        }
    }
}
