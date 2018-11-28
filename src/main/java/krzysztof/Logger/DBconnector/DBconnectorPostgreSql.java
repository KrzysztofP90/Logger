package krzysztof.Logger.DBconnector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnectorPostgreSql {

    public Connection openDataBase() {

        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/guestBook", "Krzysztof", "webhardcore");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection;
    }

    private String[] getDataBaseLogData(String filePath) {
        FileReader fileReader = new FileReader();
        BufferedReader bf = new BufferedReader();

    }


}
