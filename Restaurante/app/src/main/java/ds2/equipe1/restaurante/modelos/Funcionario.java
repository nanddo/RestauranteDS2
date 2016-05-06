package ds2.equipe1.restaurante.modelos;

import android.content.Context;

/**
 * Created by Th on 24/03/2016.
 */
public class Funcionario extends Model<Funcionario> {
    private String nome;
    private Endereco endereco;
    private String telefone;
    private String cpf;
    private String nome_de_usuario;

    public Funcionario(Context context, String nome, Endereco endereco, String telefone, String cpf, String nome_de_usuario) {
        super(context);
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome_de_usuario = nome_de_usuario;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeDeUsuario() {
        return nome_de_usuario;
    }
}
