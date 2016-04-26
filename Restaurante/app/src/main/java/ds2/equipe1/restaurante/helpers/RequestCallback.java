package ds2.equipe1.restaurante.helpers;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Fernando on 13/04/2016.
 */
public abstract class RequestCallback<T> {
    private Context context;

    public RequestCallback(){
        onStart();
    }

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
