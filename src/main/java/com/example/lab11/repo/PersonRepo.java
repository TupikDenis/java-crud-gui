package com.example.lab11.repo;

import com.example.lab11.dto.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonRepo implements CRUDRepo<Person>{
    private final String url = "jdbc:postgresql://localhost:5432/laba09";
    private final String user = "postgres";
    private final String password = "t7208588";

    @Override
    public void create(Person person) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO persons (first_name, last_name, birthday_year) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getBirthdayYear());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New person was inserted successfully!");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public ArrayList<Person> findAll() {
        ArrayList<Person> allPersons = new ArrayList<>();
        int count = 0;

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM persons ORDER BY id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Person person = new Person();

                person.setId(result.getLong(1));
                person.setLastName(result.getString(2));
                person.setFirstName(result.getString(3));
                person.setBirthdayYear(result.getInt(4));

                allPersons.add(person);

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

        return allPersons;
    }

    @Override
    public Person findById(long id) {
        Person person = new Person();

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM persons WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                person.setId(result.getLong(1));
                person.setLastName(result.getString(2));
                person.setFirstName(result.getString(3));
                person.setBirthdayYear(result.getInt(4));
            }

            conn.close();

        } catch(Exception ex){
            ex.printStackTrace();
        }

        return person;
    }

    @Override
    public void update(Person person, long id) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE persons SET last_name=?, first_name=?, birthday_year=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, person.getLastName());
            statement.setString(2, person.getFirstName());
            statement.setInt(3, person.getBirthdayYear());
            statement.setLong(4, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing person was updated successfully!");
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
            String sql = "DELETE FROM persons WHERE id= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A person was deleted successfully!");
            } else {
                System.out.println("Not found");
            }

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }
}
