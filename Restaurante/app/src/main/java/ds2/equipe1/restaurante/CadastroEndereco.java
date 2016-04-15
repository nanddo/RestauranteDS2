package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroEndereco extends AppCompatActivity {

    private EditText edtRua;
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

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("rua", edtRua.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
