package ds2.equipe1.restaurante.modelos;

import android.content.Context;

public class Funcionario extends Model<Funcionario> {
    public static final int GARCOM = 1;
    public static final int GERENTE = 2;

    private String nome;
    private String telefone;
    private String cpf;
    private String nome_usuario;
    private Integer tipo;
    private Endereco endereco;

    public Funcionario(Context context){
        super(context);
    }

    public Funcionario(Context context, String nome, Endereco endereco, String telefone, String cpf, String nome_usuario, Integer tipo) {
        super(context);
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.nome_usuario = nome_usuario;
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

    public String getNome_usuario() {
        return nome_usuario;
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

    public void setNome_usuario(String nome_usuario) { this.nome_usuario = nome_usuario; }

    public void setTipo (Integer tipo) {this.tipo = tipo; };
}
