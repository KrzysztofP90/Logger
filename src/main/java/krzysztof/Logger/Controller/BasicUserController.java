package krzysztof.Logger.Controller;

import krzysztof.Logger.DAO.DaoUserData;
import krzysztof.Logger.DAO.DaoUserDataPostgreSql;

import java.util.Map;

public class BasicUserController {

    DaoUserData dao;

    public BasicUserController() {
        this.dao = new DaoUserDataPostgreSql();
    }


    public boolean evaluateLoginAndPassword(String login, String password) {

        Map<String,String> answer = dao.getUserFromDataBase(login, password);

        if (answer.containsKey(null) || answer.containsKey("")) {
            return false;
        }
        else {
            return true;
        }


    }
}
