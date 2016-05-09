package ds2.equipe1.restaurante.modelos;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Th on 24/03/2016.
 */
public class Funcionario extends Model<Funcionario> {
    private String nome;
    private Integer id_endereco;
    private String telefone;
    private String cpf;
    private String nome_de_usuario;
    private Integer tipo;

    public Funcionario(Context context){
        super(context);
    }

    public Funcionario(Context context, String nome, Integer id_endereco, String telefone, String cpf, String nome_de_usuario, Integer tipo) {
        super(context);
        this.nome = nome;
        this.id_endereco = id_endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome_de_usuario = nome_de_usuario;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdEndereco() {
        return id_endereco;
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

    public void setIdEndereco(Integer id_endereco) {
        this.id_endereco = id_endereco;
    }

    public void setNome_de_usuario (String nome_de_usuario) { this.nome_de_usuario = nome_de_usuario; }

    public void setTipo (Integer tipo) {this.tipo = tipo; };
}
