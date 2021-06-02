


import java.util.*;
import java.io.*;

public class Exercise {

    private String name;
    private static List<Exercise> exercises = new ArrayList<Exercise>();


    public Exercise(String name){
        this.name = name;
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
}
