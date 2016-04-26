package ds2.equipe1.restaurante.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.widget.Toast;

/**
 * Created by Fernando on 28/03/2016.
 */
public class Utils {
    public static final String TAG = "Restaurante";
    private Context context;
    private static ProgressDialog progress;

    public Utils(Context context) {
        this.context = context;
    }

    public void deleteData(String key){
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        SharedPreferences.Editor e = settings.edit();
        e.remove(key);
        e.commit();
    }

    public float getData(String key, float defValue) {
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        return settings.getFloat(key, defValue);
    }

    public String getData(String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        return settings.getString(key, defValue);
    }

    public int getData(String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        return settings.getInt(key, defValue);
    }

    public long getData(String key, long defValue) {
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        return settings.getLong(key, defValue);
    }

    public boolean getData(String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        return settings.getBoolean(key, defValue);
    }

    public void setData(String key, float value) {
        SharedPreferences.Editor settings = context.getSharedPreferences("EsporteNet", 0).edit();
        settings.putFloat(key, value);
        settings.commit();
    }

    public void setData(String key, String value) {
        SharedPreferences.Editor settings = context.getSharedPreferences("EsporteNet", 0).edit();
        settings.putString(key, value);
        settings.commit();
    }

    public void setData(String key, int value) {
        SharedPreferences.Editor settings = context.getSharedPreferences("EsporteNet", 0).edit();
        settings.putInt(key, value);
        settings.commit();
    }

    public void setData(String key, long value) {
        SharedPreferences.Editor settings = context.getSharedPreferences("EsporteNet", 0).edit();
        settings.putLong(key, value);
        settings.commit();
    }

    public void setData(String key, boolean value) {
        SharedPreferences.Editor settings = context.getSharedPreferences("EsporteNet", 0).edit();
        settings.putBoolean(key, value);
        settings.commit();
    }

    public void clearData(){
        SharedPreferences settings = context.getSharedPreferences("EsporteNet", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public static void launchProgress(Context context){
        launchProgress(context, null, "Carregando...");
    }

    public static void launchProgress(Context context, String title, String text) {
        launchProgress(context, title, text, null);
    }

    public static void launchProgress(Context context, String title, String text, DialogInterface.OnDismissListener func) {
        progress = ProgressDialog.show(context, title, text, true);
        progress.setCancelable(true);
        if (func != null) progress.setOnDismissListener(func);
    }

    public static void dismissProgress() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    public void toast(String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
