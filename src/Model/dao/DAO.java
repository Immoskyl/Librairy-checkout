package Model.dao;

import Model.UserFactory;
import Model.newUser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * Created by immoskyl on 20/10/16.
 */
public class DAO implements IDAO{

    // constants ------------------------------------

    private final String databaseHost = "mysql-immosite.alwaysdata.neto";
    private final String databaseName = "immosite_ul-librairy-checkout";
    private final String databaseUser = "immosite";
    private final String databasePassword = "AEd021096=";

    private final int dbPersonRowNumber = 6;

    private static DAO instance = null;

    // setup ------------------------------------

    /**
     * getInstance
     * Singleton pattern
     * returns the only instance of the DAO
     * @return DAO
     */
    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    } //getInstance()

    /**
     * DAO
     * Singleton pattern
     * private constructor
     */
    private DAO(){}

    /**
     * setConnexion
     * loads mysql driver (uncomment l.63 or oracle driver too) and sets connexion to the database registered in the constants
     * @return Connexion database connexion
     */
    private Connection setConnection () {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
          //Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
             connection =  DriverManager.getConnection(databaseHost + "/" + databaseName,databaseUser,databasePassword);
          /* connection =  DriverManager.getConnection("jdbc:mysql://"
                    +databaseHost+
                    ":3306/"+databaseName
                    + "",""+databaseUser+"",""+databasePassword+""); */
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return connection;
    } //setConnexion()

    /**
     * listDrivers
     * for debug and test purposes
     */
    public static void listDriver() {
        for (Enumeration<Driver> e = DriverManager.getDrivers(); e.hasMoreElements();) {
            System.out.println(e.nextElement());
        }
    } //listDrivers()


    //--------------------------------------------------------------------------------------------------------------


    // prepared query automation ------------------------------------

    /**
     * selectionQueryBuilder
     * creates a monotable selection query in the form of:
     * SELECT rowList FROM table WHERE rowCondition = argCondition
     * @param table
     * @param rowList
     * @param rowCondition
     * @param argCondition
     * @return String query
     */
    private String selectionQueryBuilder (String table, List<String> rowList, List<String> rowCondition, List<String> argCondition) {
        String query = "SELECT ";
        for (int i = 0; i != rowList.size(); ++i) {
            query += rowList.get(i);
            query += (i == rowList.size()-1) ? " " :", ";
        }
        query += "FROM ";
        query += table;
        if (!rowCondition.isEmpty() && rowCondition.size() == argCondition.size()) {
            query += " WHERE ";
            for (int i = 0; i !=rowCondition.size();) {
                if (rowCondition.size() == argCondition.size()) {
                    query += rowCondition.get(i);
                    query += " = ";
                    query += argCondition.get(i);
                    query += (i == rowCondition.size() - 1) ? ";" : " AND ";
                }
            }
        }
        return query;
    } //selectionQueryBuiler()

    /**
     * intertionDelationQueryBuilder
     * creates a inserting or deleting query in the form of:
     * INSERT INTO table VALUES rowList
     * @param table
     * @param rowList
     * @param isInsertionQuery
     * @return String query
     */
    private String intertionDelationQueryBuilder(String table, List<String> rowList, boolean isInsertionQuery) {
        String query = isInsertionQuery ?  "INSERT INTO " : "REMOVE FROM ";
        query += table;
        query += " (";
        for (int i = 0; i != rowList.size(); ++i) {
            query += rowList.get(i);
            query += (i == rowList.size()-1) ? ") values (" :", ";
        }
        for (int i = 0; i != rowList.size(); ++i) {
            query += (i == rowList.size()-1) ? "?)" :"?, ";
        }
        System.out.println(query);      //debug
        return query;
    } //insertionDelationQuery()

    /**
     * selectionQuery
     * creates a prepared query and executes it on the db registered in the constants
     * @param table
     * @param rowList
     * @param argList
     * @param rowCondition
     * @param argCondition
     * @return List<String> result of the query, each row in a different String
     */
    private List<String> selectionQuery (String table, List<String> rowList, List<String> argList, List<String> rowCondition, List<String> argCondition) { //uses prepared queries
        PreparedStatement stmt = null;
        Connection connection = this.setConnection();
        List<String> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement( selectionQueryBuilder(table, rowList, rowCondition, argCondition)); //builds the statement

            for (int i = 0; i != argList.size(); i++) {
                stmt.setString(i, argList.get(i));         //builds the prepared query args
            }

            ResultSet result = stmt.executeQuery();         //exec query
            while(result.next())
            {
                list.add(result.getString(0));
            }
            //listDriver()
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                // log this error
                e.printStackTrace();
            }
            try {
                if (connection != null) { connection.close(); }
            }
            catch (Exception e) {
                // log this error
                e.printStackTrace();
            }
        }
        return list;
    } //selectionQuery()

    /**
     * insertionDelationQuery
     * creates a prepared query and executes it on the db registered in the constants
     * @param table
     * @param rowList
     * @param argList
     * @param isAnInsertionQuery
     * it returns nothing because the writting query is assumed to success or to rightly be handled by the exceptions
     */
    private void insertionDelationQuery(String table, List<String> rowList, List<String> argList, boolean isAnInsertionQuery) {
        PreparedStatement stmt = null;
        Connection connection = this.setConnection();
        try {
                stmt = connection.prepareStatement(intertionDelationQueryBuilder(table, rowList, isAnInsertionQuery));

            for (int i = 0; i != argList.size(); i++) {
                stmt.setString(i, argList.get(i));         //builds the prepared query args
            }

            //listDriver()
            stmt.executeUpdate();                           //exec query
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                // log this error
                e.printStackTrace();
            }
            try {
                if (connection != null) { connection.close(); }
            }
            catch (Exception e) {
                // log this error
                e.printStackTrace();
            }
        }
    } //intersionDelationQuery()



    //------------------------------------------------------------------------------------------------------------------


    // object recovery ------------------------------------

    /**
     * recoverUser
     * instanciate a newUser according to raw values selected from PERSON table from the db
     * @param userlist
     * @return newUser user of the right type
     */
    private newUser recoverUser (List<String> userlist) {
        if (userlist.size() == dbPersonRowNumber) {
            return UserFactory.makeNewUser(getUserType(Integer.parseInt(userlist.get(5))), //type
                    userlist.get(1),    //name
                    userlist.get(4),    //address
                    userlist.get(3),    //mail
                    userlist.get(0),    //ID
                    userlist.get(2));   //hashed password
        }
        else {
            return null;
        }
    } //recoverUser()


    //------------------------------------------------------------------------------------------------------------------


    // public functions ------------------------------------

    // USER ------------------------------------

    public void createUser (newUser person) {
        //no verification if the tuple already exists
        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();

        rowList.add("NAME");
        argList.add(person.getName());
        rowList.add("PASSWORD");
        argList.add(person.getPassword());
        //rowList.add("TYPE");
        //argList.add(person.);
        rowList.add("ID");
        argList.add(person.getUserId());

        insertionDelationQuery("PERSON", rowList, argList, true);
    }

    public void deleteUser (newUser person) {
        //no verification if the tuple already exists
        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();

        rowList.add("ID");
        argList.add(person.getUserId());

        insertionDelationQuery("PERSON", rowList, argList, false);
    }

    public List<newUser> getAllUsers () {

        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();
        List<String> argCondition = new ArrayList<>();
        List<String> rowCondition = new ArrayList<>();

        rowList.add("*");

        List<newUser> returnList = new ArrayList<>();
        List<String> queryList = selectionQuery("PERSON", rowList, argList, rowCondition, argCondition);

        for (int i  = 0; i != queryList.size(); i+=dbPersonRowNumber) { //parsing all users
            returnList.add(recoverUser(queryList.subList(i, i+dbPersonRowNumber-1)));
        }

        return returnList;
    }

    public newUser getUser (int iD) {

        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();
        List<String> argCondition = new ArrayList<>();
        List<String> rowCondition = new ArrayList<>();

        rowList.add("*");
        rowCondition.add("ID");
        argCondition.add(Integer.toString(iD));

       return recoverUser(selectionQuery("PERSON", rowList, argList, rowCondition, argCondition));

    }


    public void updateUsers (List<newUser> users) {     // verry basic and unsafe implementation
        for (newUser user : users) {
           deleteUser(user);
           createUser(user);
        }
    }


    public String getUserType (int dbType) {
        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();
        List<String> argCondition = new ArrayList<>();
        List<String> rowCondition = new ArrayList<>();


        rowList.add("TYPE");
        rowCondition.add("PERSONTYPEID");
        argCondition.add(Integer.toString(dbType));

        return selectionQuery("PERSONTYPE", rowList, argList, rowCondition, argCondition).get(0);
    }


    // PRODUCT ------------------------------------

    public void createProduct (Product product) { //todo
        //no verification if the tuple already exists
        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();

        rowList.add("ID");
        argList.add(Integer.toString(product.ID));
        rowList.add("NAME");
        argList.add(product.name);
        rowList.add("DESCRIPTION");
        argList.add(product.description);

        insertionDelationQuery("PRODUCT", rowList, argList, true);
    }
}
