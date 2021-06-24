

import java.io.*;
import java.util.*;
import java.sql.*;

public class Driver {

    public static List<Exercise> exercises = new ArrayList<Exercise>();

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Username: ");
        String user = s.nextLine();
        System.out.print("Password: ");
        String pass = s.nextLine();
        
        exercises = Exercise.startUp(user, pass);
        System.out.println();
        
        //Gui.launchGui();

        while (true) {

            System.out.println("Please enter a command: ");
            String opt = s.nextLine();
            
            // Exit program
            if (opt.equals("exit")){
                System.out.println("Exiting...");
                break;
            }

            // Help
            else if (opt.equals("help")){
                System.out.println("Commands:\tprint\tadd\tdescribe\texit");
            }

            // Print existing exercises
            else if (opt.equals("print")){
                System.out.print("Known exercises are: ");
                for (int i = 0; i < exercises.size(); i++){
                    if (i == exercises.size() - 1){
                        System.out.print(exercises.get(i).getName().toUpperCase());
                    }
                    else {
                        System.out.print(exercises.get(i).getName().toUpperCase() + ", ");
                    }
                   
                }
                System.out.println();
            }

            // Add a new exercise
            else if (opt.equals("add")){
                System.out.println("Please type an exercise to add: ");
                String line = s.nextLine().toLowerCase();
                System.out.println("Please enter a description: ");
                String desc = s.nextLine();
                Exercise e = new Exercise(line, desc);

                if (Exercise.containsExercise(e)){
                    System.out.println("Exercise already exists.");
                }
                else {
                    exercises.add(e);
                    Exercise.addQuery(e);
                }
            }
            else if (opt.equals("describe")) {
            	System.out.println("Please type an exercise name: ");
            	String line = s.nextLine().toLowerCase();
            	
            	Exercise e = Exercise.getExercise(line);
            	if (e == null) {
            		System.out.println("Exercise not found. Please type an existing exercise");
            	}
            	else {
            		System.out.println(e.getDescription());
            	}
            }
            else {
                System.out.println("Invalid command. Please type 'help' for options.");
            }
            
        }

        s.close();
    }
}
