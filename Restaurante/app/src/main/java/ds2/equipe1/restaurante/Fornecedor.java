package ds2.equipe1.restaurante;

/**
 * Created by Th on 24/03/2016.
 */
public class Fornecedor {

    private String nome;
    private String telefone;
    private String cnpj;
    private String email;

    public Fornecedor(String nome, String telefone, String cnpj, String email){
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

}
