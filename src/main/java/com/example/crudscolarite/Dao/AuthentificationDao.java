package com.example.crudscolarite.Dao;
import java.sql.*;
import com.example.crudscolarite.Beans.Users;
import com.example.crudscolarite.dbConnection.GetConnection;

public class AuthentificationDao {

    public String loginCheck(Users user) {
        String sql = "select * from users where username=? and password=? ";
        try {
          Connection con =GetConnection.getConnection();
          PreparedStatement ps =con.prepareStatement(sql);
          ps.setString(1,user.getUname());
          ps.setString(2,user.getPassword());
          ResultSet rs=ps.executeQuery();

          if(rs.next()){
              return "true";
          }
          else {
              return "false";
          }
        }catch (Exception e){e.printStackTrace();}
        return "error";
    }

    /*Registration*/
    public String register(Users user) {

         String result = "true";
        String query = "insert into users(username,password,email,phone) values (?,?,?,?) ";

        PreparedStatement ps;
        try {
            Connection con =GetConnection.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user.getUname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = " false";
        }
        return result;

    }



}
