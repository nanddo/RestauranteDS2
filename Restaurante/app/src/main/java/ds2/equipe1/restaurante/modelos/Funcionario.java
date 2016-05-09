package ds2.equipe1.restaurante.modelos;

import android.content.Context;
import android.content.Intent;

public class Funcionario extends Model<Funcionario> {
    public static final int GARCOM = 1;
    public static final int GERENTE = 1;

    private String nome;
    private String telefone;
    private String cpf;
    private String nome_de_usuario;
    private Integer tipo;
    private Endereco endereco;

    public Funcionario(Context context){
        super(context);
    }

    public Funcionario(Context context, String nome, String telefone, String cpf, String nome_de_usuario, Integer tipo) {
        super(context);
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome_de_usuario = nome_de_usuario;
        this.tipo = tipo;
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

    public String getNome_de_usuario() {
        return nome_de_usuario;
    }

    public Integer getTipo () { return tipo; };

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setTelefone (String telefone){
        this.telefone = telefone;
    }

    public void setCpf(String cnpj){
        this.cpf = cnpj;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setNome_de_usuario (String nome_de_usuario) { this.nome_de_usuario = nome_de_usuario; }

    public void setTipo (Integer tipo) {this.tipo = tipo; };
}
