package com.example.crudscolarite.Dao;
import com.example.crudscolarite.Beans.Enseignant;
import com.example.crudscolarite.dbConnection.GetConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDao {

    public static final String INSERT_ENS ="insert into enseignant (nom_ens,prenom_ens,email_ens,telephone_ens) values (?,?,?,?)";
    public static final String SELECT_ALL_ENS = "select * from enseignant";
    public static final String DELETE_ENS="delete from enseignant where id_ens = ? ;";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;


    /*LIST ENS*/
    public List<Enseignant> listEns(){
        List<Enseignant>listens = null ;
        Enseignant ens = null;
        try {
            listens = new ArrayList<Enseignant>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet= statement.executeQuery(SELECT_ALL_ENS);
            while(resultSet.next()) {
             ens = new Enseignant();
             ens.setIdEns(resultSet.getInt("id_ens"));
             ens.setNomEns(resultSet.getString("nom_ens"));
             ens.setPrenomEns(resultSet.getString("prenom_ens"));
             ens.setEmailEns(resultSet.getString("email_ens"));
             ens.setTelephoneEns(resultSet.getString("telephone_ens"));
              listens.add(ens);
            }
        }catch(SQLException e){e.printStackTrace();}
        return  listens;
    }
    /*AJOUT ENS*/

     public void addEns(Enseignant enseignant) {
         try {
             connection = GetConnection.getConnection();
             preparedStatement = connection.prepareStatement(INSERT_ENS);
             preparedStatement.setString(1,enseignant.getNomEns());
             preparedStatement.setString(2,enseignant.getPrenomEns());
             preparedStatement.setString(3,enseignant.getEmailEns());
             preparedStatement.setString(4,enseignant.getTelephoneEns());
             preparedStatement.executeUpdate();

         }catch (Exception e) {e.printStackTrace();}
     }
    /*DELETE*/

    public void deleteEns(int id){
        try{
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ENS);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {e.printStackTrace();}
    }

    /*SELECT UN ENS*/
    public Enseignant getens(int id) {
        Enseignant ens = null;
        try {
            ens = new Enseignant();
            String sql = "SELECT * FROM enseignant where id_ens="+id;
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
              String nomens = resultSet.getString("nom_ens");
              String prenomens = resultSet.getString("prenom_ens");
              String emailens= resultSet.getString("email_ens");
              String telephoneEns=resultSet.getString("telephone_ens");
              ens = new Enseignant(id,nomens,prenomens,emailens,telephoneEns);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return ens;
    }

    /*UPDATE UN ENS*/
    public void updateEns(Enseignant ens) {

        try {
            String sql = "UPDATE enseignant SET nom_ens = '"+ens.getNomEns()+"', "
                    + "prenom_ens = '"+ens.getPrenomEns()+"', email_ens = '"+ens.getEmailEns()+"' , telephone_ens = '"+ens.getTelephoneEns()+"' where id_ens="+ens.getIdEns();
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }


}
