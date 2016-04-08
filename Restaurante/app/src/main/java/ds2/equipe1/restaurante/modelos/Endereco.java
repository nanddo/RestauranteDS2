package ds2.equipe1.restaurante.modelos;

import android.content.Context;

/**
 * Created by Th on 24/03/2016.
 */
public class Endereco extends Model<Endereco> {
    private String logradouro;
    private String nome;
    private int numero;
    private String bairro;
    private String cidade;
    private String CEP;

    public Endereco(Context context, String logradouro, String nome, int numero, String bairro, String cidade, String CEP) {
        super(context);
        this.logradouro = logradouro;
        this.nome = nome;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCEP() {
        return CEP;
    }
}
