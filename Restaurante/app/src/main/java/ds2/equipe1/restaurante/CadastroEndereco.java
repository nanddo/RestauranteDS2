package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.modelos.Endereco;
import ds2.equipe1.restaurante.modelos.Model;

public class CadastroEndereco extends AppCompatActivity {

    private EditText edtCEP, edtLogradouro, edtRua, edtBairro, edtCidade, edtEstado, edtNumero;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        init();
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

                final Endereco endereco = new Endereco(CadastroEndereco.this, edtLogradouro.getText().toString(), edtRua.getText().toString(), Integer.parseInt(edtNumero.getText().toString()), edtBairro.getText().toString(), edtCidade.getText().toString(), edtCEP.getText().toString());

                endereco.save(new RequestCallback<Model>() {
                    @Override
                    public void execute(Model model) throws Exception {
                        super.execute(model);

                        Intent i = getIntent();
                        i.putExtra("rua", endereco.getRua());
                        i.putExtra("id_endereco", model.getId());
                        setResult(RESULT_OK, i);
                        finish();
                    }
                });
            }
        });
    }
}
