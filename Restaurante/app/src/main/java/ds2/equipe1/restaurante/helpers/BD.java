//documentação https://jdbc.postgresql.org/documentation/head/connect.html

package ds2.equipe1.restaurante.helpers;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Fernando on 28/03/2016.
 */
public class BD {
    public static void test(final Context context) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    Class.forName("org.postgresql.Driver");
                    String url = "jdbc:postgresql://192.168.1.11:5432/restaurante";
                    Properties props = new Properties();
                    props.setProperty("user", "postgres");
                    props.setProperty("password", "admin");
                    props.setProperty("connectTimeout", "4");
                    //props.setProperty("ssl", "true");
                    Connection conn = DriverManager.getConnection(url, props);

                    return conn.getMetaData().toString();
                } catch (Exception e) {
                    Log.e(Utils.TAG, e.getMessage(), e);
                    return e.toString();
                }
            }
        }.execute();
        //url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
        //conn = DriverManager.getConnection(url);
    }
}
