package com.example.crudscolarite.Dao;
import com.example.crudscolarite.Beans.Matiere;
import com.example.crudscolarite.Beans.Specialite;
import com.example.crudscolarite.dbConnection.GetConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SpecialiteDao {

    private static final String LIST_SPEC ="select* from specialite";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    /*LIST SPEC*/
    public List<Specialite>listSpec(){
        List<Specialite>listspec=null;
        Specialite spec =null;
        try {
            listspec = new ArrayList<Specialite>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet=statement.executeQuery(LIST_SPEC);
            while(resultSet.next()){
                spec = new Specialite();
                spec.setNom_spec(resultSet.getString("nom_specialite"));
                listspec.add(spec);
            }
        }catch (SQLException e){e.printStackTrace();}
        return listspec;
    }




}
