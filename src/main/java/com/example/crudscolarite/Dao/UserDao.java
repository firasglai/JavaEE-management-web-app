package com.example.crudscolarite.Dao;
import com.example.crudscolarite.dbConnection.GetConnection;
import com.example.crudscolarite.Beans.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    public static final String INSERT_USER ="insert into users(username,password,email,phone) values (?,?,?,?)";

    public static final String SELECT_ALL ="select * from users";
    public static final String DELETE_USER ="delete from users where idusers = ? ;";
Connection connection = null;
Statement statement = null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;



/*LIST USER*/
    public List<Users> getUser(){
    List<Users>list = null ;
    Users user = null;

    try {
        list = new ArrayList<Users>();
        connection = GetConnection.getConnection();
        statement = connection.createStatement();
        resultSet= statement.executeQuery(SELECT_ALL);
        while(resultSet.next()) {
            user = new Users();
            user.setId(resultSet.getInt("idusers"));
            user.setUname(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            list.add(user);
        }

    }catch(SQLException e){e.printStackTrace();}
return  list;
}

/*SELECT BY ID*/
public Users getoneUser(int id) {
    Users user = null;
    try {
        user = new Users();
        String sql = "SELECT * FROM users where idusers="+id;
        connection = GetConnection.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        if(resultSet.next()) {
            String username = resultSet.getString("username");
            String password=resultSet.getString("password");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("Phone");
            user = new Users(id, username,password,email,phone);
        }
    }catch(SQLException e) {
        e.printStackTrace();
    }
    return user;
}

/*ADD User BOOLEAN*/
public boolean addUserbolean(Users user) {
    boolean flag = false;
    try  {
        connection = GetConnection.getConnection();
        preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1,user.getUname());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setString(4,user.getPhone());
        preparedStatement.executeUpdate();
        flag = true;

    }catch (Exception e) {e.printStackTrace();}
    return flag;
}
/*ADD user Void*/
    public void addUser(Users user){
        try  {
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1,user.getUname());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPhone());
            preparedStatement.executeUpdate();


        }catch (Exception e) {e.printStackTrace();}
    }

    /*Update User void*/
    public void updateUser(Users user) {

        try {
            String sql = "UPDATE users SET username = '"+user.getUname()+"', "
                    + "password = '"+user.getPassword()+"', email = '"+user.getEmail()+"' , phone = '"+user.getPhone()+"' where idusers="+user.getId();
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    /*UPDATE Boolean*/

    /*Update VOL2*/

    /*DELETE*/
    public void deleteUser (int id){

        try  {
           // String sql = "DELETE * FROM users where idusers="+id;
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER);
             preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate() ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
