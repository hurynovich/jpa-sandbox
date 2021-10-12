package orm;

import org.hsqldb.jdbc.JDBCDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class JdbcHsqldbExperiments {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:hsqldb:mem:mydb";
        String user = "SA";
        String pass = "";

        var ds = new JDBCDataSource();
        ds.setURL(jdbcUrl);
        ds.setUser(user);
        ds.setPassword(pass);

        var conn = ds.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        var req = conn.createStatement();

        int updt = req.executeUpdate("CREATE TABLE pinguin (name VARCHAR(64))");
        System.out.println("Table created. Updated: " + updt);
        conn.commit();

        updt = req.executeUpdate(
                "INSERT INTO pinguin (name) VALUES ('Tux');\n" +
                "INSERT INTO pinguin (name) VALUES ('Lux');\n" +
                "INSERT INTO pinguin (name) VALUES ('Dug');\n" +
                "INSERT INTO pinguin (name) VALUES ('Fax');\n"
        );
        System.out.println("Updated rows: " + updt);

        String countRowsSql = "SELECT COUNT(*) FROM pinguin";
        var rs = req.executeQuery(countRowsSql);
        if(rs.next()) {
            System.out.println("Count by conn one: " + rs.getInt(1));
        }
        rs.close();
//        conn.close();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("----------------------");

        var conn2 = ds.getConnection();
        conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        var req2 = conn2.createStatement();
        String selectAllSql = "SELECT * FROM pinguin;";
        rs = req2.executeQuery(selectAllSql);
        while(rs.next()) {
            System.out.println("Count by conn2 two: " + rs.getString(1));
        }
        rs.close();
    }
}
