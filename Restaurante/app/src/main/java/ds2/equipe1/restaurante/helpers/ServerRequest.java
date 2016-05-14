package ds2.equipe1.restaurante.helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ds2.equipe1.restaurante.modelos.Fornecedor;
import ds2.equipe1.restaurante.modelos.Ingrediente;
import ds2.equipe1.restaurante.modelos.Model;
import ds2.equipe1.restaurante.modelos.Produto;

/**
 * Created by Fernando on 07/04/2016.
 */
public class ServerRequest {
    private static final String TAG = Utils.TAG;

    public enum Action {
        INDEX, SAVE, DELETE, GET, FIND, LOGIN
    }

    private Context context;

    public ServerRequest(Context context) {
        this.context = context;
    }

    public void sendRequest(final String controller, Action action, String data, final RequestCallback<JSONObject> callback){
        Map<String, Object> params = getAuthParams();
        params.put("data", data);

        new AQuery(context).ajax(buildURL(controller, action), params, String.class, new AjaxCallback<String>(){
            @Override
            public void callback(String url, String str, AjaxStatus status) {
                try {
                    Log.w(TAG, "[" + url + "]:");
                    JSONObject json = new JSONObject(str);
                    Log.w(TAG, json.toString());
                    if (json.getBoolean("success")) {
                        if (callback != null) {
                            callback.execute(json);
                        }
                    } else {
                        String errors = "";
                        if (json.has("errors")) {
                            JSONArray msgs = json.getJSONArray("errors");
                            for (int i = 0; i < msgs.length(); i++){
                                errors += " " + msgs.getString(i);
                            }
                        } else {
                            errors = "Erro desconhecido no servidor";
                        }
                        Toast.makeText(context, errors, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(context, "Falha ao processar resposta:\n\n" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Erro no processamento de dados: ", e);
                    if (str != null)
                        Log.e(TAG, str);
                    Log.e(TAG, "AjaxStatus: " + status.getError() + " --- " + status.getCode() + " --- " + status.getMessage());
                    if (status.getMessage().contains("netword")){
                        Toast.makeText(context, "Falha na conexao com " + url, Toast.LENGTH_LONG).show();
                    }
                }
                super.callback(url, str, status);
            }

            @Override
            public void failure(int code, String message) {
                Log.e(TAG, "Falha de conexao: " + message + " " + code);
                super.failure(code, message);
            }
        }.timeout(5000));
    }

    private Map<String, Object> getAuthParams(){
        Map<String, Object> params = new HashMap<>();
        //TODO: Remover padrao
        params.put("usuario", new Utils(context).getData("usuario", "user.default"));
        params.put("senha", new Utils(context).getData("senha", "senha.default"));
        return params;
    }

    private String buildURL(String controller, Action action){
        return new Utils(context).getData("host", "http://179.232.16.215:8080/") + controller.toLowerCase() + "/" + action.name().toLowerCase() + "/";
    }
}
