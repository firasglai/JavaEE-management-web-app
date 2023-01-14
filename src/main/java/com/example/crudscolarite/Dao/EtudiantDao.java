package com.example.crudscolarite.Dao;
import com.example.crudscolarite.Beans.Enseignant;
import com.example.crudscolarite.Beans.Groupe;
import com.example.crudscolarite.Beans.Matiere;
import com.example.crudscolarite.dbConnection.GetConnection;
import com.example.crudscolarite.Beans.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao {

    public static final String INSERT_ETU = "insert into etudiant(nom_etu,prenom_etu,email_etu,nom_groupe)values(?,?,?,?)";
    public static final String SELECT_ALL_ETU = "select * from etudiant";
    public static final String SELECT_ETU_IRM = "select * from etudiant where nom_groupe = 'IRM 1-1' or nom_groupe = 'IRM 1-2'";
    public static final String SELECT_ETU_GRP = "select * from etudiant where nom_groupe = ?";
    public static final String UPDATE_ETU = "update etudiant set nom_etu = ?,prenom_etu=? ,email_etu=?,nom_groupe=? where idetudiant = ?";

    public static final String DELETE_ETU = "delete from etudiant where idetudiant = ? ;";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /*LIST ETU*/
    public List<Etudiant> listEtu() {
        List<Etudiant> listetu = null;
        Etudiant etu = null;
        try {
            listetu = new ArrayList<Etudiant>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_ETU);
            while (resultSet.next()) {
                etu = new Etudiant();
                etu.setIdEtu(resultSet.getInt("idetudiant"));
                etu.setNomEtu(resultSet.getString("nom_etu"));
                etu.setPrenomEtu(resultSet.getString("prenom_etu"));
                etu.setEmailEtu(resultSet.getString("email_etu"));
                etu.setGroupNom(resultSet.getString("nom_groupe"));
              listetu.add(etu);            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listetu;
    }
    /*LIST ETUDIANT PAR ID*/
    public List<Etudiant> listEtuParID(int id) {
        List<Etudiant> listetu = null;
        Etudiant etu = null;
        try {
            listetu = new ArrayList<Etudiant>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            String sql= "select * from etudiant where idetudiant ="+id;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                etu = new Etudiant();
                etu.setIdEtu(resultSet.getInt("idetudiant"));
                etu.setNomEtu(resultSet.getString("nom_etu"));
                etu.setPrenomEtu(resultSet.getString("prenom_etu"));
                etu.setEmailEtu(resultSet.getString("email_etu"));
                etu.setGroupNom(resultSet.getString("nom_groupe"));
                listetu.add(etu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listetu;
    }

    /*LIST etudiant par groupe */
    public List<Etudiant> listEtuParGrp(String grpNom) {
        List<Etudiant> listetu = null;
        Etudiant etu = null;
        try {
            listetu = new ArrayList<Etudiant>();
            connection = GetConnection.getConnection();
           String sql= "select * from etudiant where nom_groupe = ?";
           preparedStatement =connection.prepareStatement(sql);
           preparedStatement.setString(1,grpNom);
           resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                etu = new Etudiant();
                etu.setIdEtu(resultSet.getInt("idetudiant"));
                etu.setNomEtu(resultSet.getString("nom_etu"));
                etu.setPrenomEtu(resultSet.getString("prenom_etu"));
                etu.setEmailEtu(resultSet.getString("email_etu"));
                etu.setGroupNom(resultSet.getString("nom_groupe"));
                listetu.add(etu);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listetu;
    }

    /*AJOUT ETU*/
    public void addEtu(Etudiant etudiant) {
        try {
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ETU);
            preparedStatement.setString(1, etudiant.getNomEtu());
            preparedStatement.setString(2, etudiant.getPrenomEtu());
            preparedStatement.setString(3, etudiant.getEmailEtu());
            preparedStatement.setString(4,etudiant.getGroupNom());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*SELECT UN ETUDIANT */
    public Etudiant getEtu(int id) {
        Etudiant etu = null;
        try {
            etu = new Etudiant();
            String sql = "SELECT * FROM etudiant where idetudiant=" + id;
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String nometu = resultSet.getString("nom_etu");
                String prenometu = resultSet.getString("prenom_etu");
                String emailetu = resultSet.getString("email_etu");
                /*Lezem nekhouha value men <option>value</option>*/

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etu;
    }



    /*UPDATE ETU*/
    public void UpdateEtu(Etudiant etu){

        try {
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(UPDATE_ETU);
            preparedStatement.setString(1,etu.getNomEtu());
            preparedStatement.setString(2,etu.getPrenomEtu());
            preparedStatement.setString(3,etu.getEmailEtu());
            preparedStatement.setString(4,etu.getGroupNom());
            preparedStatement.setInt(5,etu.getIdEtu());
        }catch (SQLException e){e.printStackTrace();}
    }

    /*UPDATE VOL2 */
    public void updateEtuVol2(Etudiant etu) {

        try {
            String sql = "UPDATE etudiant SET nom_etu = '"+etu.getNomEtu()+"', "
                    + "prenom_etu = '"+etu.getPrenomEtu()+"', email_etu = '"+etu.getEmailEtu()+"' , nom_groupe = '"+etu.getGroupNom()+"' where idetudiant="+etu.getIdEtu();
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }


    /*SUPPRIMER ETU*/
    public void deleteEns(int id){
        try{
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ETU);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {e.printStackTrace();}
    }

}
