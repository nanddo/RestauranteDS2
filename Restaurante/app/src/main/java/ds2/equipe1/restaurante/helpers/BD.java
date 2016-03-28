//tutorial https://jdbc.postgresql.org/documentation/head/connect.html

package ds2.equipe1.restaurante.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Fernando on 28/03/2016.
 */
public class BD {
    public void test() throws Exception {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user","fred");
        props.setProperty("password","secret");
        props.setProperty("ssl","true");
        Connection conn = DriverManager.getConnection(url, props);

        //url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
        //conn = DriverManager.getConnection(url);
    }
}
