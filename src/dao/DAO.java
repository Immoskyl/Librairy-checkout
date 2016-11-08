package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by immoskyl on 20/10/16.
 */
public class DAO implements IDAO{

    private final String databaseHost = "mysql-immosite.alwaysdata.net";
    private final String databaseUser = "immosite";
    private final String databasePassword = "AEd021096=";


    private static DAO instance = null;

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    private DAO(){}

    private int hashMD31(String strToHash) {
        int hash = 7;
        for (int i = 0; i < strToHash.length(); i++) {
            hash = hash*31 + strToHash.charAt(i);
        }
        return hash;
    }


    private Connection setConnection () {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
             connection =  DriverManager.getConnection(databaseHost,databaseUser,databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * debug and test purposes
     */
    public static void listDriver() {
        for (Enumeration<Driver> e = DriverManager.getDrivers(); e.hasMoreElements();) {
            System.out.println(e.nextElement());
        }
    }


    //--------------------------------------------------------------------------------------------------------------

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
    }


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
    }

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
    }

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
    }


    //------------------------------------------------------------------------------------------------------------------


    // USER ------------------------------------

    public void createUser (Person person) {
        //no verification if the tuple already exists
        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();

        rowList.add("NAME");
        argList.add(person.name);
        rowList.add("PASSWORD");
        argList.add(person.pwd);
        rowList.add("TYPE");
        argList.add(person.type);
        rowList.add("ID");
        argList.add(Integer.toString(person.iD));

        insertionDelationQuery("PERSON", rowList, argList, true);
    }

    public void deleteUser (Person person) { //todo
        //no verification if the tuple already exists
        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();

        rowList.add("ID");
        argList.add(Integer.toString(person.iD));

        insertionDelationQuery("PERSON", rowList, argList, false);
    }

    public List<String> getAllUsers () { //todo

        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();
        List<String> argCondition = new ArrayList<>();
        List<String> rowCondition = new ArrayList<>();

        rowList.add("*");

        return selectionQuery("PERSON", rowList, argList, rowCondition, argCondition);
    }

    public List<String> getUser (int iD) { //todo

        List<String> rowList = new ArrayList<>();
        List<String> argList = new ArrayList<>();
        List<String> argCondition = new ArrayList<>();
        List<String> rowCondition = new ArrayList<>();

        rowList.add("*");
        rowCondition.add("ID");
        argCondition.add(Integer.toString(iD));

        return selectionQuery("PERSON", rowList, argList, rowCondition, argCondition);
    }


    public void updateAllUsers () {
        //todo
    }

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
