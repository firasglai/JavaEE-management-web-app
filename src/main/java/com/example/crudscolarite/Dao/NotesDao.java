package com.example.crudscolarite.Dao;
import com.example.crudscolarite.Beans.Note;
import com.example.crudscolarite.dbConnection.GetConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotesDao {

    public static final String INSERT_NOTE ="insert into note (note_pratique,note_examen,note_controle,id_etudiant,code_mat)values(?,?,?,?,?)";
    public static final String SELECT_ALL_NOTE =" select * from note";
    public static final String SELECT_NOTE_ETU="select * from note where id_etudiant = ? ";
    public static final String DELETE_NOTE ="delete from note where idnote = ?";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public List<Note>listAllNote(){
        List<Note> listnote = null;
        Note note= null;
        try{
            listnote= new ArrayList<Note>();
            connection = GetConnection.getConnection();
            statement = connection.createStatement();
            resultSet=statement.executeQuery(SELECT_ALL_NOTE);
            while(resultSet.next()){
               note = new Note();
               note.setIdnote(resultSet.getInt("idnote"));
               note.setNote_pratique(resultSet.getFloat("note_pratique"));
               note.setNote_examen(resultSet.getFloat("note_examen"));
               note.setNote_controle(resultSet.getFloat("note_controle"));
               note.setId_etu(resultSet.getInt("id_etudiant"));
               note.setCode_mat(resultSet.getInt("code_mat"));
               listnote.add(note);
            }

        }catch(Exception e){e.printStackTrace();}
        return listnote;
    }
    /*LIST NOTE PAR IDETUDIANT*/
    public  List<Note>ListNoteParEtu(int id_etu){
        List<Note> listnote = null;
        Note note= null;
        try{
            listnote= new ArrayList<Note>();
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(SELECT_NOTE_ETU);
            preparedStatement.setInt(1,id_etu);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                note = new Note();
                note.setIdnote(resultSet.getInt("idnote"));
                note.setNote_pratique(resultSet.getFloat("note_pratique"));
                note.setNote_examen(resultSet.getFloat("note_examen"));
                note.setNote_controle(resultSet.getFloat("note_controle"));
                note.setId_etu(resultSet.getInt("id_etudiant"));
                note.setCode_mat(resultSet.getInt("code_mat"));
                listnote.add(note);
            }
        }catch(Exception e){e.printStackTrace();}
        return listnote;
    }


    /*AJOUT NOTE*/

    public void ajouterNote(Note note){

        try{
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(INSERT_NOTE);
            preparedStatement.setFloat(1,note.getNote_pratique());
            preparedStatement.setFloat(2,note.getNote_examen());
            preparedStatement.setFloat(3,note.getNote_controle());
            preparedStatement.setInt(4,note.getId_etu());
            preparedStatement.setInt(5,note.getCode_mat());
            preparedStatement.executeUpdate();

        }catch(Exception e){e.printStackTrace();}
    }

    /*Suppression du note*/

    public void deleteNote (int note_id){
        try{
            connection = GetConnection.getConnection();
            preparedStatement=connection.prepareStatement(DELETE_NOTE);
            preparedStatement.setInt(1,note_id);
            preparedStatement.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }
    /*UPDATE NOTE*/


}
