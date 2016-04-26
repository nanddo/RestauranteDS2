package ds2.equipe1.restaurante.modelos;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import ds2.equipe1.restaurante.helpers.RequestCallback;
import ds2.equipe1.restaurante.helpers.ServerRequest;
import ds2.equipe1.restaurante.helpers.Utils;

/**
 * Created by Fernando on 07/04/2016.
 */
public class Model<T> {
    //transient para a serialação não incluir.
    private transient Context context;

    private Integer id;

    public Model(Context context){
        this.context = context;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void save(){
        save(null);
    }

    public void save(final RequestCallback<Model> callback){
        new ServerRequest(context).sendRequest(getControllerName(), ServerRequest.Action.SAVE, new Gson().toJson(this), new RequestCallback<JSONObject>() {
            @Override
            public void execute(JSONObject json) throws Exception {
                setId(json.getInt("id"));
                if (callback != null){
                    callback.execute(Model.this);
                }

                super.execute(json);
            }
        });
    }

    public void delete(){
        new ServerRequest(context).sendRequest(getControllerName(), ServerRequest.Action.DELETE, new Gson().toJson(this), null);
    }

    public static <T extends Model> void find(Context context, Class<T> klass, final Type typeArray, final RequestCallback<T> callback, int id){
        find(context, klass, typeArray, callback, id + "");
    }

    public static <T extends Model> void find(final Context context, Class<T> klass, final Type typeArray, final RequestCallback<T> callback, String where){
        ServerRequest serverRequest = new ServerRequest(context);
        String controller = ((Class<T>) ((ParameterizedType) klass.getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        serverRequest.sendRequest(controller, ServerRequest.Action.FIND, where, new RequestCallback<JSONObject>() {
            @Override
            public void execute(JSONObject json) throws Exception {
                Log.w(Utils.TAG, "Trying cast to " + typeArray.toString());

                ArrayList<T> lista = new Gson().fromJson(json.getJSONArray("data").toString(), typeArray);
                for (T item : lista){
                    item.setContext(context);
                }
                if (callback != null){
                    if (lista.size() > 0) {
                        callback.execute(lista.get(0));
                    }
                    callback.execute(lista);
                }
                super.execute(json);
            }
        });
    }

//    public static <T extends Model> void find(Context context, Class<T> klass, RequestCallback callback){
//        find(context, klass, callback, "");
//    }

    public Integer getId() {
        return id;
    }

    public T setId(Integer id) {
        this.id = id;
        return (T) this;
    }

    protected String getControllerName(){
        return this.getClass().getSimpleName();
    }
}
