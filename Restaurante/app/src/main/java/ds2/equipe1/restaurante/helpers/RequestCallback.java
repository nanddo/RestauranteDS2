package ds2.equipe1.restaurante.helpers;

import java.util.ArrayList;

import ds2.equipe1.restaurante.modelos.Model;

/**
 * Created by Fernando on 13/04/2016.
 */
public abstract class RequestCallback<T> {
    public void execute(){}
    public void execute(ArrayList<T> lista){}
    public void execute(T model){}
}
