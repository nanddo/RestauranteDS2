package ds2.equipe1.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ds2.equipe1.restaurante.controles.ControleDeFornecedor;
import ds2.equipe1.restaurante.controles.ControleDeImpressao;
import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Funcionario;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        preCadastroDeDados();

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

        findViewById(R.id.menu_funcionarios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(MenuFuncionarios.class);
            }
        });


    }

    public void open(Class activity){
        this.startActivity(new Intent(this, activity));
    }

    public void preCadastroDeDados(){
        ControleDeFornecedor controleDeFornecedor = new ControleDeFornecedor(this);
        //controleDeFornecedor.salvarFornecedor(new Fornecedor(this, "Fernando LTDA.", "79 99999-9999", "00.000.000/0000-0", "contato@fernandos.cc"));
        //controleDeFornecedor.salvarFornecedor(new Fornecedor(this, "Arquimago Tecnology", "79 99999-9999", "00.000.000/0000-0", "contato@arquimago.cc"));
        //controleDeFornecedor.salvarFornecedor(new Fornecedor(this, "Padaria Jõao Ferreira.", "79 99999-9999", "00.000.000/0000-0", "contato@padaria.cc"));
    }
}
