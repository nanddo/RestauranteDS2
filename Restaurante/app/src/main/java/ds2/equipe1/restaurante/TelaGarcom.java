package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaGarcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_garcom);

        findViewById(R.id.menu_criar_comanda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CriacaoComanda.class);
            }
        });

        findViewById(R.id.menu_consulta_cardapio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BuscaProduto.class);
            }
        });
    }

    public void open(Class activity){
        startActivity(new Intent(this, activity));
    }
}
