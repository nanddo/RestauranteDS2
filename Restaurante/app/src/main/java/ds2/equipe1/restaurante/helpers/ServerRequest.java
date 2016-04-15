package ds2.equipe1.restaurante.helpers;

import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ds2.equipe1.restaurante.modelos.Model;

/**
 * Created by Fernando on 07/04/2016.
 */
public class ServerRequest {
    public static HashMap<String, ArrayList<Model>> bancoSimulado = new HashMap<>();

    public static String SERVER_URL = "http://localhost/";
    private static final String TAG = Utils.TAG;

    public enum Action {
        INDEX, SAVE, DELETE, GET
    }

    private Context context;

    public ServerRequest(Context context) {
        this.context = context;
    }

    public void send(String controller, Model model, RequestCallback callback){
        Log.d(TAG, "SAVE: " + controller);

        if (!bancoSimulado.containsKey(controller)) {
            bancoSimulado.put(controller, new ArrayList<Model>());
        }
        ArrayList<Model> dados = bancoSimulado.get(controller);
        model.setId(dados.size()+1);
        dados.add(model);

        if (callback != null){
            callback.execute();
        }
    }

    public static ArrayList<Model> find(String controller){
        Log.d(TAG, "FIND: " + controller);

        if (!bancoSimulado.containsKey(controller)){
            bancoSimulado.put(controller, new ArrayList<Model>());
        }
        return bancoSimulado.get(controller);
    }

    public void send(String controller, Action action, String data, AjaxCallback<Object> ajaxCallback){
        Map<String, Object> params = getAuthParams();
        params.put("data", data);

        new AQuery(context).ajax(buildURL(controller, action), params, Object.class, ajaxCallback);
    }

    private Map<String, Object> getAuthParams(){
        Map<String, Object> params = new HashMap<>();
        //TODO: Remover padrao
        params.put("usuario", new Utils(context).getData("usuario", "user.default"));
        return params;
    }

    private String buildURL(String controller, Action action){
        return SERVER_URL + controller.toLowerCase() + "/" + action.name().toLowerCase() + "/";
    }
}
