package ds2.equipe1.restaurante.modelos;

import android.content.Context;

public class Gerente extends Funcionario {
    public Gerente(Context context, String nome, String telefone, String cpf, String nome_de_usuario) {
        super(context, nome, telefone, cpf, nome_de_usuario, GERENTE);
    }
}
