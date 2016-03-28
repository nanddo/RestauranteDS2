package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaRelatorios extends AppCompatActivity {
    private TextView tvImpressora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_relatorios);
        init();

        Intent i = getIntent();

        if (i.hasExtra("dados")){
            tvImpressora.setText(i.getStringExtra("dados"));
        } else {
            tvImpressora.setText("Nenhum dado passado como parâmetro");
        }
    }

    private void init(){
        tvImpressora = (TextView) findViewById(R.id.tvImpressora);
    }
}
