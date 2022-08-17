package com.example.lab11.repo;

import com.example.lab11.dto.Type;

import java.sql.*;
import java.util.*;

public class TypeRepo implements CRUDRepo<Type>{
    private final String url = "jdbc:postgresql://localhost:5432/laba09";
    private final String user = "postgres";
    private final String password = "t7208588";

    @Override
    public void create(Type type) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO types_edu (type) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, type.getType());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New type was inserted successfully!");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public ArrayList<Type> findAll() {
        ArrayList<Type> allTypes = new ArrayList<>();
        int count = 0;

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM types_edu ORDER BY id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Type type = new Type();

                type.setId(result.getLong(1));
                type.setType(result.getString(2));

                allTypes.add(type);

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

        return allTypes;
    }

    @Override
    public Type findById(long id) {
        Type type = new Type();

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM types_edu WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                type.setId(result.getLong(1));
                type.setType(result.getString(2));
            }

            conn.close();

        } catch(Exception ex){
            ex.printStackTrace();
        }

        return type;
    }

    @Override
    public void update(Type type, long id) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE types_edu SET type=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, type.getType());
            statement.setLong(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing type was updated successfully!");
            } else {
                System.out.println("Not found");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public void delete(long id){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM types_edu WHERE id= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A type was deleted successfully!");
            } else {
                System.out.println("Not found");
            }

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }
}
