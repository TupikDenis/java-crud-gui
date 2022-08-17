package com.example.lab11.repo;

import com.example.lab11.dto.EducationalInstitution;
import com.example.lab11.dto.Facultet;
import com.example.lab11.dto.Person;

import java.sql.*;
import java.util.ArrayList;

public class FacultetRepo implements CRUDRepo<Facultet> {
    private final String url = "jdbc:postgresql://localhost:5432/laba09";
    private final String user = "postgres";
    private final String password = "t7208588";

    @Override
    public void create(Facultet facultet) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO facultets(name, founded, id_educational_institution, id_person) " +
                    "VALUES (?, ?, (SELECT id FROM educational_institutions WHERE id=?), (SELECT id FROM persons WHERE id=?))";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, facultet.getName());
            statement.setInt(2, facultet.getFounded());
            statement.setLong(3, facultet.getIdEducationalInstitution());
            statement.setLong(4, facultet.getIdPerson());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New facultet was inserted successfully!");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public ArrayList<Facultet> findAll() {
        ArrayList<Facultet> allFacultets = new ArrayList<>();
        int count = 0;

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM facultets ORDER BY id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Facultet facultet = new Facultet();

                facultet.setId(result.getLong(1));
                facultet.setName(result.getString(2));
                facultet.setFounded(result.getInt(3));
                facultet.setIdEducationalInstitution(result.getLong(4));
                facultet.setIdPerson(result.getLong(5));

                allFacultets.add(facultet);

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

        return allFacultets;
    }

    @Override
    public Facultet findById(long id) {
        Facultet facultet = new Facultet();

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM facultets WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                facultet.setId(result.getLong(1));
                facultet.setName(result.getString(2));
                facultet.setFounded(result.getInt(3));
                facultet.setIdEducationalInstitution(result.getLong(4));
                facultet.setIdPerson(result.getLong(5));
            }

            conn.close();

        } catch(Exception ex){
            ex.printStackTrace();
        }

        return facultet;
    }

    @Override
    public void update(Facultet facultet, long id) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE facultets SET name=?, founded=?, id_educational_institution=?, id_person=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, facultet.getName());
            statement.setInt(2, facultet.getFounded());
            statement.setLong(3, facultet.getIdEducationalInstitution());
            statement.setLong(4, facultet.getIdPerson());
            statement.setLong(5, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing facultet was updated successfully!");
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
            String sql = "DELETE FROM facultets WHERE id= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A facultet was deleted successfully!");
            } else {
                System.out.println("Not found");
            }

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }
}
