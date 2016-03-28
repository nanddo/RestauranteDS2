package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuItens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_itens);

        findViewById(R.id.menu_cadastrar_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CadastroItem.class);
            }
        });

        findViewById(R.id.menu_alterar_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CadastroItem.class);
            }
        });

        findViewById(R.id.menu_buscar_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BuscaItem.class);
            }
        });
    }

    public void open(Class activity){
        startActivity(new Intent(this, activity));
    }
}
