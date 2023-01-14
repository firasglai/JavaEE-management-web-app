package com.example.crudscolarite.Dao;

import com.example.crudscolarite.Beans.Matiere;
import com.example.crudscolarite.dbConnection.GetConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDao {

    public static final String INSERT_MAT ="insert into matiere (nom_mat,coef_mat,nom_specialite,id_niveau) values (?,?,?,?)";
    public static final String SELECT_ALL_MAT = "select * from matiere ";
    public static final String SELECT_MAT_NIV = "select * from matiere where nom_specialite = ? AND id_niveau = ?";
    public static final String SELECT_MAT_SPEC = "select * from matiere where nom_specialite = ?";

    public static final String MAT_JOIN ="select nom_mat from matiere INNER JOIN note WHERE matiere.code_mat = ?";
    public static final String UPDATE_MAT ="update matiere set nom_mat=?, coef_mat=?, nom_specialite=?, id_niveau=? where code_mat=?";

    public static final String DELETE_MAT="delete from matiere where code_mat = ? ";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /*List Matiere par specialite et par niveau */
    public List<Matiere>listMatParNiv(String nom_spec , int id_niv){
        List<Matiere>listmat=null;
        Matiere mat =null;
        try {
            listmat = new ArrayList<Matiere>();
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(SELECT_MAT_NIV);
            preparedStatement.setString(1,nom_spec);
            preparedStatement.setInt(2,id_niv);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                mat= new Matiere();
                mat.setCode_mat(resultSet.getInt("code_mat"));
                mat.setNom_mat(resultSet.getString("nom_mat"));
                mat.setCoef(resultSet.getInt("coef_mat"));
                mat.setNom_spec(resultSet.getString("nom_specialite"));
                mat.setId_niv(resultSet.getInt("id_niveau"));
                listmat.add(mat);
            }
        }catch (SQLException e){e.printStackTrace();}
        return listmat;
    }

    /*LIST PAR SPECIALITE*/
    public List<Matiere>listMatParSec(String nom_spec ){
        List<Matiere>listmat=null;
        Matiere mat =null;
        try {
            listmat = new ArrayList<Matiere>();
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(SELECT_MAT_SPEC);
            preparedStatement.setString(1,nom_spec);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                mat= new Matiere();
                mat.setCode_mat(resultSet.getInt("code_mat"));
                mat.setNom_mat(resultSet.getString("nom_mat"));
                mat.setCoef(resultSet.getInt("coef_mat"));
                mat.setNom_spec(resultSet.getString("nom_specialite"));
                mat.setId_niv(resultSet.getInt("id_niveau"));
                listmat.add(mat);
            }
        }catch (SQLException e){e.printStackTrace();}
        return listmat;
    }
    /*LIST MATIERE BY JOIN*/

    public List<Matiere>listMatParnote(int note_id ){
        List<Matiere>listmat=null;
        Matiere mat =null;
        try {
            listmat = new ArrayList<Matiere>();
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(MAT_JOIN);
            preparedStatement.setInt(1,note_id);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                mat= new Matiere();
                mat.setCode_mat(resultSet.getInt("code_mat"));
                mat.setNom_mat(resultSet.getString("nom_mat"));
                mat.setCoef(resultSet.getInt("coef_mat"));
                mat.setNom_spec(resultSet.getString("nom_specialite"));
                mat.setId_niv(resultSet.getInt("id_niveau"));
                listmat.add(mat);
            }
        }catch (SQLException e){e.printStackTrace();}
        return listmat;
    }

/*LIST MATIERE*/
    public List<Matiere>listMat(){
        List<Matiere>listmat=null;
        Matiere mat =null;
        try {
            listmat = new ArrayList<Matiere>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet=statement.executeQuery(SELECT_ALL_MAT);
            while(resultSet.next()){
                mat= new Matiere();
                mat.setCode_mat(resultSet.getInt("code_mat"));
                mat.setNom_mat(resultSet.getString("nom_mat"));
                mat.setCoef(resultSet.getInt("coef_mat"));
                mat.setNom_spec(resultSet.getString("nom_specialite"));
                mat.setId_niv(resultSet.getInt("id_niveau"));
                listmat.add(mat);
            }
        }catch (SQLException e){e.printStackTrace();}
        return listmat;
    }


    /*Ajouter Matiere*/
    public void addMat(Matiere matiere) {
        try {
            connection = GetConnection.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_MAT);
            preparedStatement.setString(1,matiere.getNom_mat());
            preparedStatement.setInt(2,matiere.getCoef());
            preparedStatement.setString(3,matiere.getNom_spec());
            preparedStatement.setInt(4,matiere.getId_niv());
            preparedStatement.executeUpdate();

        }catch (Exception e) {e.printStackTrace();}
    }
    /*SUPRESSION DU MATIERE*/

public void deleteMat(int code_mat){
    try{
        connection = GetConnection.getConnection();
        preparedStatement = connection.prepareStatement(DELETE_MAT);
        preparedStatement.setInt(1,code_mat);
        preparedStatement.executeUpdate();
    }catch (SQLException e){e.printStackTrace();}
}
public void UpdateMat(Matiere mat){

    try {
        connection = GetConnection.getConnection();
        preparedStatement=connection.prepareStatement(UPDATE_MAT);
        preparedStatement.setString(1, mat.getNom_mat());
        preparedStatement.setInt(2,mat.getCoef());
        preparedStatement.setString(3,mat.getNom_spec());
        preparedStatement.setInt(4,mat.getId_niv());
        preparedStatement.setInt(5,mat.getCode_mat());
    }catch (SQLException e){e.printStackTrace();}
}

public void UpdateMatvol2(Matiere mat){
    try{
        String sql = "UPDATE matiere SET nom_mat = '"+mat.getNom_mat()+"', "
                + "coef_mat = '"+mat.getCoef()+"', nom_specialite = '"+mat.getNom_spec()+"' , id_niveau = '"+mat.getId_niv()+"' where code_mat="+mat.getCode_mat();
        connection = GetConnection.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }catch (SQLException e){e.printStackTrace();}

}





}
