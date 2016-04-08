package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.controles.ControleDeImpressao;

public class MenuPrincipal extends AppCompatActivity {
    private static ControleDeFornecedor controleDeFornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ControleDeImpressao controleDeImpressao = new ControleDeImpressao(this);

        if (controleDeFornecedor == null){
            controleDeFornecedor = new ControleDeFornecedor();
        }

        findViewById(R.id.menu_fornecedores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(MenuFornecedores.class);
            }
        });

        findViewById(R.id.menu_garcom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(TelaGarcom.class);
            }
        });

        findViewById(R.id.menu_itens).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(MenuItens.class);
            }
        });

        findViewById(R.id.menu_produto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(MenuProdutos.class);
            }
        });

        findViewById(R.id.menu_relatorios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(MenuRelatorio.class);
            }
        });
    }

    public static ControleDeFornecedor getControleDeFornecedor(){
        return controleDeFornecedor;
    }

    public void open(Class activity){
        this.startActivity(new Intent(this, activity));
    }
}
