package krzysztof.Logger;

import krzysztof.Logger.DBconnector.DBconnectorPostgreSql;


public class App 
{
    public static void main( String[] args ) {

        DBconnectorPostgreSql db = new DBconnectorPostgreSql();
        db.openDataBase();
    }
}
