package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuGarcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_garcom);

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

        findViewById(R.id.menu_comandas_ativas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuGarcom.this, "Falta criar tela e adicionar Requisito Funcional, Diagramas etc", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void open(Class activity){
        startActivity(new Intent(this, activity));
    }
}
