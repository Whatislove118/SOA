package services;

import java.util.HashSet;
import java.util.Random;

public class IdGenerator {

    private static HashSet<Long> usingHumanId = new HashSet<>();
    private static HashSet<Long> usingCoordinatesId = new HashSet<>();;
    private static HashSet<Long> usingCityId = new HashSet<>();

    public static long generateId(String className){
        HashSet<Long> classId = null;
        switch (className){
            case "Human":
                classId = IdGenerator.usingHumanId;
                break;
            case "Coordinates":
                classId = IdGenerator.usingCoordinatesId;
                break;
            case "City":
                classId = IdGenerator.usingCityId;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + className);
        }
        System.out.println(classId);
        Random random = new Random();
        while (true) {
            long uncheckedId = Math.abs(random.nextLong());
            if (!classId.contains(uncheckedId)){
                classId.add(uncheckedId);
                return uncheckedId;
            }
        }
    }

}
