package krzysztof.Logger.DBconnector;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnectorPostgreSql implements DBConnector {



    public Connection openDataBase() {

        Connection connection = null;
        String[] arrayOfLoginData = getDataBaseLogData("dataBaseLoginData.csv");

        try {
            connection = DriverManager.getConnection(arrayOfLoginData[0], arrayOfLoginData[1], arrayOfLoginData[2]);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection;
    }



    private String[] getDataBaseLogData(String filePath) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("dataBaseLoginData.csv");
        } catch(FileNotFoundException   e) {
            e.printStackTrace();
            System.out.println("You havent't file with data for login to data base!");
        }
        BufferedReader bf = new BufferedReader(fileReader);
        String data = "";
        try{
            data = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File is incorrect!");
        }
        return data.split(",");
    }


}
