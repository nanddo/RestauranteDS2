package ds2.equipe1.restaurante.modelos;

import android.content.Context;

public class Fornecedor extends Model<Fornecedor> {
    private String nome;
    private String telefone;
    private String cnpj;
    private String email;
    private Endereco endereco;

    public Fornecedor(Context context){
        super(context);
    }

    public Fornecedor(Context context, String nome, String telefone, String cnpj, String email){
        super(context);
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.email = email;
    }

    public String getNome(){
        return nome;
    }

    public String getTelefone (){
        return telefone;
    }

    public String getCnpj(){
        return cnpj;
    }

    public String getEmail(){
        return email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setTelefone (String telefone){
        this.telefone = telefone;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
}
