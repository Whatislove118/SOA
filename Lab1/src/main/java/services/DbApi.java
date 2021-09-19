package services;

import data.Climate;
import data.Coordinates;
import data.Human;
import exceptions.ValidationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class DbApi {

    public static Long createObject(Object obj, String className) throws ValidationException{
        try{
            switch (className) {
                case "Coordinates":
                    Connection connection = getNewConnections();
                    Coordinates coordinates = (Coordinates) obj;
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO coordinates values (?, ?)");
                    preparedStatement.setLong(1, coordinates.getX());
                    preparedStatement.setDouble(2, coordinates.getY());
                    boolean hasResult = preparedStatement.execute();
                    System.out.println(hasResult);
                case "Human":
                    return null;
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

    public static Connection getNewConnections() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s263156", "ygt183");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                    return new Human(180.5, ZonedDateTime.now());
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
