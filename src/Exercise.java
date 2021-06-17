package src;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Exercise {

    private String name;
    private static List<Exercise> exercises = new ArrayList<Exercise>();


    public Exercise(String name){
        this.name = name.toUpperCase();
    }

    public String getName(){
        return name;
    }

    public static void addExercise(Exercise e){
        exercises.add(e);
    }

    public static List<Exercise> exerciseList(){
        return exercises;
    }
    
    public static List<Exercise> initList() {
        File file = new File("files/exercises.txt");

        // read in existing exercises from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                exercises.add(new Exercise(line.toUpperCase()));
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }

        return exercises;
    }

    public static boolean containsExercise(Exercise e) {
        for (Exercise i : exercises){
            if (i.getName().equals(e.getName())){
                return true;
            }
        }
        return false;
    }
}
