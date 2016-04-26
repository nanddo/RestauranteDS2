package ds2.equipe1.restaurante.modelos;

import android.content.Context;

/**
 * Created by Th on 24/03/2016.
 */
public class Endereco extends Model<Endereco> {
    private String logradouro;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco(Context context, String logradouro, String rua, int numero, String bairro, String cidade, String cep) {
        super(context);
        this.logradouro = logradouro;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getRua() {
        return rua;
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

    public String getCep() {
        return cep;
    }
}
