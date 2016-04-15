package ds2.equipe1.restaurante.modelos;

import android.content.Context;
import android.util.Log;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.ServerRequest;
import ds2.equipe1.restaurante.helpers.Utils;

/**
 * Created by Fernando on 07/04/2016.
 */
public class Model<T> {
    private ServerRequest serverRequest;

    private int id;

    public Model(Context context){
        this.serverRequest = new ServerRequest(context);
    }

    public void save(){
        save(null);
    }

    public void save(final RequestCallback requestCallback){
        serverRequest.send(getTableName(), this, requestCallback);

        /*serverRequest.send(this.getClass().getName(), ServerRequest.Action.SAVE, new Gson().toJson(this), new AjaxCallback<Object>(){
            @Override
            public void callback(String url, Object object, AjaxStatus status) {
                if (object != null) {
                    try {
                        JSONObject json = (JSONObject) object;
                        setId(json.getInt("id"));

                        if (requestCallback != null) {
                            requestCallback.execute();
                        }
                    } catch (Exception e){
                        Log.d(Utils.TAG, "Falha ao setar ID: ", e);
                    }
                }
                super.callback(url, object, status);
            }
        });*/
    }

    public void delete(){
        serverRequest.send(getTableName(), ServerRequest.Action.DELETE, new Gson().toJson(this), null);
    }

    public static <T> void find(Context context, Class<?> klass, RequestCallback callback){
        String controller = ((Class<T>) ((ParameterizedType) klass.getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();

        if (callback != null){
            callback.execute(ServerRequest.find(controller));
        }

        //find(context, klass, ajaxCallback, null);
    }

//    public static <T> void find(Context context, Class<T> klass, AjaxCallback<T> ajaxCallback, Integer id){
//        ServerRequest server = new ServerRequest(context);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected String getTableName(){
        return this.getClass().getSimpleName();
    }
}
