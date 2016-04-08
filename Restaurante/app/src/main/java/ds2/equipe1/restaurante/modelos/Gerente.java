package ds2.equipe1.restaurante.modelos;

import android.content.Context;

/**
 * Created by Th on 24/03/2016.
 */
public class Gerente extends Funcionario {
    public Gerente(Context context, String nome, Endereco endereco, String telefone, String cpf, String nome_de_usuario) {
        super(context, nome, endereco, telefone, cpf, nome_de_usuario);
    }
}
