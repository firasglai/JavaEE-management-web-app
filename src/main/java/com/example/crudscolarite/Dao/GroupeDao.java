package com.example.crudscolarite.Dao;
import com.example.crudscolarite.Beans.Enseignant;
import com.example.crudscolarite.Beans.Groupe;
import com.example.crudscolarite.dbConnection.GetConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupeDao {

    public static final String SELECT_GRP_IRM = "select * from groupe where id_specialite = 1";
    public static final String SELECT_GRP_GI = "select * from groupe where id_specialite = 2";
    public static final String SELECT_GRP_MC = "select * from groupe where id_specialite = 3";
    public static final String SELECT_ALL_GROUPS = "select * from groupe ";

   // public static final String SELECT_GRP= "select * from groupe where nom_spec = ?";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /*IRM*/
    public List<Groupe> listgrpIRM(){
        List<Groupe>listgrpirm = null ;
        Groupe grp =null;
        try {
            listgrpirm= new ArrayList<Groupe>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet= statement.executeQuery(SELECT_GRP_IRM);
            while(resultSet.next()) {
                grp = new Groupe();
                grp.setNom_grp(resultSet.getString("nom_groupe"));
                listgrpirm.add(grp);
            }
        }catch(SQLException e){e.printStackTrace();}
        return  listgrpirm;
    }
    /*GI*/
    /*LIST ALL GROUPS*/
    public List<Groupe> listallgrp(){
        List<Groupe>listallgrp = null ;
        Groupe grp =null;
        try {
            listallgrp= new ArrayList<Groupe>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet= statement.executeQuery(SELECT_ALL_GROUPS);
            while(resultSet.next()) {
                grp = new Groupe();
                grp.setNom_grp(resultSet.getString("nom_groupe"));
                listallgrp.add(grp);
            }
        }catch(SQLException e){e.printStackTrace();}
        return  listallgrp;
    }







    /*PAR NOM_SPEC*/
    public List<Groupe> listgrp(String nom_spec){
        List<Groupe>listgrp = null ;
        Groupe grp =null;
        try {
            listgrp= new ArrayList<Groupe>();
            String sql ="Select * from groupe where nom_spec= ? ";
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,nom_spec);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                grp = new Groupe();
                grp.setNom_grp(resultSet.getString("nom_groupe"));
                listgrp.add(grp);
            }
        }catch(SQLException e){e.printStackTrace();}
        return  listgrp;
    }
}
