package ds2.equipe1.restaurante.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ds2.equipe1.restaurante.R;
import ds2.equipe1.restaurante.modelos.Item;
import ds2.equipe1.restaurante.modelos.Model;

public class Utils {
    public static final String TAG = "Restaurante";
    private Context context;
    private static ProgressDialog progress;

    public Utils(Context context) {
        this.context = context;
    }

    public void deleteData(String key){
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
        SharedPreferences.Editor e = settings.edit();
        e.remove(key);
        e.commit();
    }

    public float getData(String key, float defValue) {
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
        return settings.getFloat(key, defValue);
    }

    public String getData(String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
        return settings.getString(key, defValue);
    }

    public int getData(String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
        return settings.getInt(key, defValue);
    }

    public long getData(String key, long defValue) {
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
        return settings.getLong(key, defValue);
    }

    public boolean getData(String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
        return settings.getBoolean(key, defValue);
    }

    public void setData(String key, float value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(TAG, 0).edit();
        settings.putFloat(key, value);
        settings.commit();
    }

    public void setData(String key, String value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(TAG, 0).edit();
        settings.putString(key, value);
        settings.commit();
    }

    public void setData(String key, int value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(TAG, 0).edit();
        settings.putInt(key, value);
        settings.commit();
    }

    public void setData(String key, long value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(TAG, 0).edit();
        settings.putLong(key, value);
        settings.commit();
    }

    public void setData(String key, boolean value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(TAG, 0).edit();
        settings.putBoolean(key, value);
        settings.commit();
    }

    public void clearData(){
        SharedPreferences settings = context.getSharedPreferences(TAG, 0);
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

    public void inputDialog(String title, String defValue, String hint, final DialogCallback callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        // Set up the input
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) li.inflate(R.layout.input_dialog, null);
        final EditText edtInput = (EditText) linearLayout.findViewById(R.id.edtInput);
        edtInput.setHint(hint);
        edtInput.setText(defValue);
        builder.setView(linearLayout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback != null) {
                    callback.execute(edtInput.getText().toString());
                }
            }
        });
        builder.setNegativeButton("Cancelar", null);

        builder.show();
    }

    public void selectPopup(String title, final IngredienteCallback callback, final ArrayList<Item> itens){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        // Set up the input
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) li.inflate(R.layout.select_popup, null);

        final Spinner spinnerItens = (Spinner) linearLayout.findViewById(R.id.spinnerItens);
        List<String> spinnerArray =  new ArrayList<>();
        for (Item item : itens) {
            spinnerArray.add(item.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItens.setAdapter(adapter);

        final EditText edtInput = (EditText) linearLayout.findViewById(R.id.edtInput);
        edtInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(linearLayout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback != null) {
                    callback.execute(itens.get(spinnerItens.getSelectedItemPosition()), Integer.parseInt(edtInput.getText().toString()));
                }
            }
        });
        builder.setNegativeButton("Cancelar", null);

        builder.show();
    }

    public void simNaoDialog(String titulo, String mensagem, final DialogCallback callbackSim, final DialogCallback callbackNao){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);

        // Set up the buttons
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callbackSim != null) {
                    callbackSim.execute("");
                }
            }
        });
        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callbackNao != null){
                    callbackNao.execute("");
                }
            }
        });

        builder.show();
    }

    public interface IngredienteCallback {
        public void execute(Item item, int quantidade);
    }

    public interface DialogCallback {
        public void execute(String text);
    }

    public static void prepararSearchMenu(Activity activity, Menu menu, final DialogCallback dialogCallback){
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
            SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                public boolean onQueryTextChange(String search) {
                    dialogCallback.execute(search);
                    return true;
                }

                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
            };

            searchView.setOnQueryTextListener(queryTextListener);
        }
    }

    public <T> void addFuncaoRemover(final BaseAdapter adapter, View view, final ArrayList<T> arr, final int position){
        if (view != null)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    simNaoDialog("Excluir", "Deseja excluir este item?", new DialogCallback() {
                        @Override
                        public void execute(String text) {
                            Model model = (Model) arr.get(position);
                            model.delete();
                            arr.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }, null);
                }
            });
    }
}
