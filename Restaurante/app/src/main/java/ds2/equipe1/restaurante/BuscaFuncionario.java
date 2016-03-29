package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ds2.equipe1.restaurante.controles.ControleDeFuncionario;

public class BuscaFuncionario extends AppCompatActivity {

    private ControleDeFuncionario controleDeFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_funcionario);

        controleDeFuncionario = new ControleDeFuncionario(this);
    }

    public void onItemClick(View v){
        startActivity(new Intent(this, CadastroFuncionario.class));
    }
}
