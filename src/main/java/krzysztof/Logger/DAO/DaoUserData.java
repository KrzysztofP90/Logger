package krzysztof.Logger.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public interface DaoUserData {

    ResultSet askDataBaseForData(String query, Connection connection, Statement statement);

    void editDataBase(String query, Connection connection, Statement statement);

    Map<String,String> getUserFromDataBase(String login, String password);
}
