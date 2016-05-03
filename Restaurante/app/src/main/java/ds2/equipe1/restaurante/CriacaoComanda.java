package ds2.equipe1.restaurante;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ds2.equipe1.restaurante.controles.ControleDeAtendimento;
import ds2.equipe1.restaurante.modelos.Comanda;

public class CriacaoComanda extends AppCompatActivity {

    private ControleDeAtendimento controleDeAtendimento;
    Comanda comanda;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_comanda);
        controleDeAtendimento = new ControleDeAtendimento(this);

        findViewById(R.id.btnAdicionarPedido).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CriacaoComanda.this, BuscaProduto.class));
            }
        });

        findViewById(R.id.btnEncerrarComanda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //encerrar comanda
                //imprimir comanda
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comandas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemClick(View v){
        startActivity(new Intent(this, ExibirPedido.class));
    }
}
