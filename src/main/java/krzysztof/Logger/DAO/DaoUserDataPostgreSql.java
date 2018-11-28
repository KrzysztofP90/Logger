package krzysztof.Logger.DAO;

import krzysztof.Logger.DBconnector.DBConnector;
import krzysztof.Logger.DBconnector.DBconnectorPostgreSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoUserDataPostgreSql implements DaoUserData {

    private DBConnector dbConnector;


    public DaoUserDataPostgreSql() {
        this.dbConnector = new DBconnectorPostgreSql();
    }


    private void closeStatementAndConnection(Connection connection, Statement statement) {
        try{
            connection.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    private Statement getStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }



   public Map<String,String> getUserFromDataBase(String login, String password) {

        Connection connection = dbConnector.openDataBase();
        Statement statement = getStatement(connection);

        String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";

        ResultSet result = askDataBaseForData(query,connection,statement);

       String correctLogin = "";
       String correctPassword = "";


        try {
            while (result.next()) {
                correctLogin = result.getString("login");
                correctPassword = result.getString("password");
            }
            } catch(SQLException e){
                e.printStackTrace();
                System.out.println("Data base error - check Your internet connection or try later!");
            }

        Map<String,String> answer = new HashMap<>();
        answer.put(correctLogin,correctPassword);
        return answer;
   }



    public ResultSet askDataBaseForData(String query, Connection connection, Statement statement) {

        ResultSet result = null;
        try {
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");
        }
        return result;
    }


    public void editDataBase(String query, Connection connection, Statement statement) {

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");
        }
        closeStatementAndConnection(connection, statement);
    }
}
