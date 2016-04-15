package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuFuncionarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_funcionarios);

        findViewById(R.id.menu_cadastrar_funcionario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CadastroFuncionario.class);
            }
        });

        findViewById(R.id.menu_buscar_funcionario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BuscaFuncionario.class);
            }
        });

        findViewById(R.id.menu_alterar_funcionario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CadastroFuncionario.class);
            }
        });
    }

    public void open(Class activity){
        startActivity(new Intent(this, activity));
    }
}
