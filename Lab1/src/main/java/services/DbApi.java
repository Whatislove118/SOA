package services;

import data.Climate;
import data.Coordinates;
import data.Human;
import exceptions.ValidationException;

import java.sql.*;
import java.time.ZonedDateTime;

public class DbApi {

    public static Long createObject(Object obj, String className) throws ValidationException{
        try{
            Connection connection = getNewConnections();
            PreparedStatement preparedStatement = null;
            boolean hasResult = false;

            switch (className) {
                case "Coordinates":
                    Coordinates coordinates = (Coordinates) obj;
                    preparedStatement = connection.prepareStatement("INSERT INTO coordinates(x,y) values(?, ?)");
                    preparedStatement.setLong(1, coordinates.getX());
                    preparedStatement.setDouble(2, coordinates.getY());
                    hasResult = preparedStatement.execute();
                    System.out.println(hasResult);
                    break;
                case "Human":
                    Human human = (Human) obj;
                    preparedStatement = connection.prepareStatement("INSERT INTO human(height,birthday) values(?, ?)");
                    preparedStatement.setDouble(1, human.getHeight());
                    preparedStatement.setString(2, human.getBirthday().toString());
                    hasResult = preparedStatement.execute();
                    System.out.println(hasResult);
                    break;
                case "City":
                    return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getNewConnections() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s263156", "ygt183");
        } catch (SQLException | ClassNotFoundException e)  {
            e.printStackTrace();
        }
        return null;
    }

    public static Object findById(Long id, String className) throws ValidationException {
        try {
            switch (className) {
                case "Coordinates":
                    Connection connection = getNewConnections();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM coordinates where id=?");
                    preparedStatement.setLong(1, id);
                    boolean hasResult = preparedStatement.execute();
                    System.out.println(hasResult);
                    return new Coordinates(1L, 2.0);
                case "Human":
                    //return new Human(180.5, ZonedDateTime.now());
                case "Climate":
                    return null;
                case "City":
                    return null;
                case "Government":
                    return null;
            }
        }catch (SQLException e){
            throw new ValidationException("параметры неверны");
        }
        return null;
    }

    public static void deleteById(Long id){
        //delete
    }



    public static Object getObjectById(Long id){
        return 1L;
    }

}
