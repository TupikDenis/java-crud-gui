package com.example.lab11.repo;

import com.example.lab11.dto.EducationalInstitution;

import java.sql.*;
import java.util.ArrayList;

public class EducationalInstitutionRepo implements CRUDRepo<EducationalInstitution>{
    private final String url = "jdbc:postgresql://localhost:5432/laba09";
    private final String user = "postgres";
    private final String password = "t7208588";

    @Override
    public void create(EducationalInstitution educationalInstitution) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO educational_institutions(name, founded, id_town, id_type, id_person) " +
                    "VALUES (?, ?, (SELECT id from towns WHERE id=?), (SELECT id from types_edu WHERE id=?), (SELECT id from persons WHERE id=?))";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, educationalInstitution.getName());
            statement.setInt(2, educationalInstitution.getFounded());
            statement.setLong(3, educationalInstitution.getIdTown());
            statement.setLong(4, educationalInstitution.getIdType());
            statement.setLong(5, educationalInstitution.getIdPerson());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New educational institution was inserted successfully!");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public ArrayList<EducationalInstitution> findAll() {
        ArrayList<EducationalInstitution> allEducationalInstitutions = new ArrayList<>();
        int count = 0;

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM educational_institutions ORDER BY id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                EducationalInstitution educationalInstitution = new EducationalInstitution();

                educationalInstitution.setId(result.getLong(1));
                educationalInstitution.setName(result.getString(2));
                educationalInstitution.setFounded(result.getInt(3));
                educationalInstitution.setIdTown(result.getLong(4));
                educationalInstitution.setIdType(result.getLong(5));
                educationalInstitution.setIdPerson(result.getLong(6));

                allEducationalInstitutions.add(educationalInstitution);

                count++;
            }
            conn.close();
        } catch(Exception ex){
            System.out.println("Not ok");
        }

        switch(count){
            case 0:
                System.out.println("Not elements");
                break;
            case 1:
                System.out.println(count + "element");
            default:
                System.out.println(count + "elements");
        }

        return allEducationalInstitutions;
    }

    @Override
    public EducationalInstitution findById(long id) {
        EducationalInstitution educationalInstitution = new EducationalInstitution();

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM educational_institutions WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                educationalInstitution.setId(result.getLong(1));
                educationalInstitution.setName(result.getString(2));
                educationalInstitution.setFounded(result.getInt(3));
                educationalInstitution.setIdTown(result.getLong(4));
                educationalInstitution.setIdType(result.getLong(5));
                educationalInstitution.setIdPerson(result.getLong(6));
            }

            conn.close();

        } catch(Exception ex){
            ex.printStackTrace();
        }

        return educationalInstitution;
    }

    @Override
    public void update(EducationalInstitution educationalInstitution, long id) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE educational_institutions SET name=?, founded=?, id_town=?, id_type=?, id_person=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, educationalInstitution.getName());
            statement.setInt(2, educationalInstitution.getFounded());
            statement.setLong(3, educationalInstitution.getIdTown());
            statement.setLong(4, educationalInstitution.getIdType());
            statement.setLong(5, educationalInstitution.getIdPerson());
            statement.setLong(6, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing educational institution was updated successfully!");
            } else {
                System.out.println("Not found");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public void delete(long id) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM educational_institutions WHERE id= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An educational institution was deleted successfully!");
            } else {
                System.out.println("Not found");
            }

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }
}
