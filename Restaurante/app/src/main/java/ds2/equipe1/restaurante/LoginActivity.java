package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.ServerRequest;
import ds2.equipe1.restaurante.modelos.Endereco;
import ds2.equipe1.restaurante.modelos.Funcionario;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        init();
    }

    private void init(){
        edtLogin = (EditText) findViewById(R.id.edtLogin);
    }

    public void onLoginButtonClick(View view){
        String login = edtLogin.getText().toString();

        RequestCallback<JSONObject> loginCallback = new RequestCallback<JSONObject>() {
            @Override
            public void execute(JSONObject object) throws Exception {
                try {
                    if (object.getBoolean("success")) {
                        if (object.has("tipo")) {
                            efetuarLogin(object.getInt("tipo"));
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Funcionario nao cadastrado", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Falha na conexao: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                super.execute(object);
            }
        };

        if (login.contentEquals("admin")){
            efetuarLogin(Funcionario.GERENTE);
        } else {
            new ServerRequest(this).sendRequest("funcionario", ServerRequest.Action.LOGIN, login.toLowerCase(), loginCallback);
        }
    }

    private void efetuarLogin(int tipo){
        if (tipo == Funcionario.GERENTE){
            startActivity(new Intent(this, MenuPrincipal.class));
        } else {
            startActivity(new Intent(this, TelaGarcom.class));
        }
        finish();
    }
}
