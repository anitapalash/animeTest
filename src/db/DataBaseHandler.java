package db;

import libraries.Access;
import libraries.Configs;
import libraries.Const;
import services.Main;
import users.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public DataBaseHandler() {
        try {
            dbConnection = getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Connection getDbConnection() throws ClassNotFoundException, SQLException, IOException {
        String connectionString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        System.out.println("DB connected");
        runScript(dbConnection);
        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_ID + ", " + Const.USERS_FIRSTNAME + ", " +
                Const.USERS_LASTNAME + ", " + Const.USERS_USERNAME + ", " +
                Const.USERS_PASSWORD + ", " + Const.USERS_GROUP + ", " + Const.USERS_ACCESS + ", " +
                Const.USERS_GENDER + ", passedgl, passedop, passeddn, passedat, passedn, passedgen) " + "VALUES ("
                + getNewId() + ", \'" + user.getFirstName() + "\', \'" + user.getLastName() + "\', \'" + user.getUserName()
                + "\', \'" + user.getPassword() + "\', \'" + user.getGroup() + "\', \'" + user.getAccess().toString()
                + "\', \'" + user.getGender() + "\', " + false + ", " + false + ", " + false + ", " + false +
                ", " + false + ", " + false + ")";

        try {
            user.setId(getNewId());
            dbConnection.createStatement().execute(insert);
            System.out.println("User inserted to db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(User user) {// массив пользователей
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USERS_USERNAME + " = \'" + user.getUserName() + "\'";
        User endUser = new User();
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                endUser.setId(resSet.getLong("id"));
                endUser.setUserName(resSet.getString(Const.USERS_USERNAME));
                endUser.setPassword(user.getPassword());
                endUser.setFirstName(resSet.getString(Const.USERS_FIRSTNAME));
                endUser.setLastName(resSet.getString(Const.USERS_LASTNAME));
                endUser.setAccess(Access.valueOf(resSet.getString(Const.USERS_ACCESS)));
                endUser.setGender(resSet.getString(Const.USERS_GENDER));
                endUser.setGroup(resSet.getString(Const.USERS_GROUP));
                endUser.setPassedGL(resSet.getBoolean("passedgl"));
                endUser.setPassedGB(resSet.getBoolean("passedop"));
                endUser.setPassedDN(resSet.getBoolean("passeddn"));
                endUser.setPassedAT(resSet.getBoolean("passedat"));
                endUser.setPassedN(resSet.getBoolean("passedn"));
                endUser.setPassedGen(resSet.getBoolean("passedgen"));
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endUser;
    }

    public User getUserById (Long id) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USERS_ID + " = " + id;
        User endUser = new User();

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                endUser.setId(resSet.getLong("id"));
                endUser.setUserName(resSet.getString(Const.USERS_USERNAME));
                endUser.setPassword(resSet.getString(Const.USERS_PASSWORD));
                endUser.setFirstName(resSet.getString(Const.USERS_FIRSTNAME));
                endUser.setLastName(resSet.getString(Const.USERS_LASTNAME));
                endUser.setAccess(Access.valueOf(resSet.getString(Const.USERS_ACCESS).toUpperCase()));
                endUser.setGender(resSet.getString(Const.USERS_GENDER));
                endUser.setGroup(resSet.getString(Const.USERS_GROUP));
                endUser.setPassedGL(resSet.getBoolean("passedgl"));
                endUser.setPassedGB(resSet.getBoolean("passedop"));
                endUser.setPassedDN(resSet.getBoolean("passeddn"));
                endUser.setPassedAT(resSet.getBoolean("passedat"));
                endUser.setPassedN(resSet.getBoolean("passedn"));
                endUser.setPassedGen(resSet.getBoolean("passedgen"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endUser;
    }


    private void runScript(Connection c) throws IOException, SQLException, ClassNotFoundException {
        try {
            FileReader fr = new FileReader(new File(Paths.get("").toAbsolutePath() + "\\src\\db\\data.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non alphabetical character

            String s = new String();
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Long getNewId() {
        try {
            String request = "SELECT COUNT(*) FROM " + Const.USER_TABLE + ";";
            PreparedStatement preparedStatement = Main.dbHandler.dbConnection.prepareStatement(request);
            ResultSet res = preparedStatement.executeQuery();
            res.next();
            return Long.valueOf(res.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser (User user) {
        String request = "UPDATE " + Const.USER_TABLE + " SET " +
                Const.USERS_USERNAME + "=\'" + user.getUserName() + "\', " +
                Const.USERS_PASSWORD + "=\'" + user.getPassword() + "\', " +
                Const.USERS_FIRSTNAME + "=\'" + user.getFirstName() + "\', " +
                Const.USERS_LASTNAME + "=\'" + user.getLastName() + "\', " +
                Const.USERS_ACCESS + "=\'" + user.getAccess().toString() + "\', " +
                Const.USERS_GENDER + "=\'" + user.getGender() + "\', " +
                Const.USERS_GROUP + "=\'" + user.getGroup() + "\', " +
                "passedgl=\'" + user.isPassedGL() + "\', " +
                "passedop=\'" + user.isPassedGB() + "\', " +
                "passeddn=\'" + user.isPassedDN() + "\', " +
                "passedat=\'" + user.isPassedAT() + "\', " +
                "passedn=\'" + user.isPassedN() + "\', " +
                "passedgen=\'" + user.isPassedGen() + "\' " +
                "WHERE id=" + user.getId() + ";";

        try {
            dbConnection.createStatement().execute(request);
            System.out.println("User was updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
