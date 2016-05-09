package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produtos);

        findViewById(R.id.menu_cadastrar_produto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CadastroProduto.class);
            }
        });

        findViewById(R.id.menu_buscar_produto).setOnClickListener(new View.OnClickListener() {
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
