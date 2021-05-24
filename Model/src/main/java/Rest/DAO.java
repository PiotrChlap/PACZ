package Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private final String url = "jdbc:postgresql://localhost/database";
    private final String user = "postgres";
    private final String password = "ramzes";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Przeczytaj plik README.txt!!!!!!!!!");
        }

        return conn;
    }
}
