package com.example.lab11.repo;

import com.example.lab11.dto.Town;

import java.sql.*;
import java.util.ArrayList;

public class TownRepo implements CRUDRepo<Town>{
    private final String url = "jdbc:postgresql://localhost:5432/laba09";
    private final String user = "postgres";
    private final String password = "t7208588";

    @Override
    public void create(Town town) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO towns (town) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, town.getTownName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New town was inserted successfully!");
            }

            conn.close();

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }

    @Override
    public ArrayList<Town> findAll() {
        ArrayList<Town> allTowns = new ArrayList<>();
        int count = 0;

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM towns ORDER BY id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Town town = new Town();

                town.setId(result.getLong(1));
                town.setTownName(result.getString(2));

                allTowns.add(town);

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

        return allTowns;
    }

    @Override
    public Town findById(long id) {
        Town town = new Town();

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM towns WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                town.setId(result.getLong(1));
                town.setTownName(result.getString(2));
            }

            conn.close();

        } catch(Exception ex){
            ex.printStackTrace();
        }

        return town;
    }

    @Override
    public void update(Town town, long id) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE towns SET town=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, town.getTownName());
            statement.setLong(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing town was updated successfully!");
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
            String sql = "DELETE FROM towns WHERE id= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A town was deleted successfully!");
            } else {
                System.out.println("Not found");
            }

        } catch(Exception ex){
            System.out.println("Not ok");
        }
    }
}
