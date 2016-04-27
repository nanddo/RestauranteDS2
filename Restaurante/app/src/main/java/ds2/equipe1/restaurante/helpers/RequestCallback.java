package ds2.equipe1.restaurante.helpers;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Fernando on 13/04/2016.
 */
public abstract class RequestCallback<T> {
    private Context context;

    //cria request callback SEM tela de loading
    public RequestCallback(){
        onStart();
    }

    //Cria request callback com uma tela de loading
    public RequestCallback(Context context){
        this.context = context;
        onStart();
    }

    public void onStart(){
        if (context != null){
            Utils.launchProgress(context);
        }
    }

    public void execute(){
        onFinish();
    }
    public void execute(ArrayList<T> lista) throws Exception {
        onFinish();
    }
    public void execute(T object) throws  Exception {
        onFinish();
    }

    public void onFinish(){
        if (context != null) {
            Utils.dismissProgress();
        }
    }
}
