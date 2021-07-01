package code;


import java.io.*;
import java.util.*;
import java.sql.*;

public class Driver {

    public static List<Exercise> exercises = new ArrayList<Exercise>();

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
               
        exercises = Exercise.startUp();
               
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
                System.out.println("Commands:\nprint:\t Print existing exercises."
                		+ "\nadd: Add a new exercise."
                		+ "\nremove: Delete an existing exercise."
                		+ "\nupdate: Update description of exercise."
                		+ "\ndescribe: Get description of exericse."
                		+ "\nreset: Reset app to defaults (Deletes all added exercises)."
                		+ "\nexit: Exit application.");
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
                    // Update local db
                    Exercise.addQuery(e);
                }
            }
            // Removes exercise
            else if (opt.equals("remove")) {
            	System.out.println("Please type an exercise to remove: ");
            	String line = s.nextLine().toLowerCase();
            	Exercise e = new Exercise(line);
            	
            	if (!Exercise.containsExercise(e)) {
            		System.out.println("Exercise does not exist.");
            	}
            	else {
            		for (Exercise x : exercises) {
            			if (x.getName().equals(line)) {
            				e = x;
            			}
            		}
            		exercises.remove(e);
            		// Updates local DB
            		Exercise.removeQuery(e);
            	}
            }
            // Update existing exercise.
            else if (opt.equals("update")) {
            	System.out.println("Please type an exercise to update: ");
            	String line = s.nextLine().toLowerCase();
            	System.out.println("Please type a new description: ");
            	String desc = s.nextLine();
            	Exercise e = new Exercise(line);
            	
            	if (Exercise.containsExercise(e)) {
            		for (Exercise x : exercises) {
            			if (x.getName().equals(e.getName())) {
            				// Updates local DB and updates list.
            				x.setDesc(x.getName(), desc);
            			}
            		}
            	}
            }
            // Prints description
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
            // Resets application to default, given confirmation.
            else if (opt.equals("reset")) {
            	System.out.println("Are you sure you want to reset? This will restore the app to its defaults. "
            			+ "Any custom exercises and descriptions will be lost. "
            			+ "Please type 'yes' to continue.");
            	
            	if (s.nextLine().equals("yes")) {
            		Exercise.reset();
            	}
            	
            	else {
            		System.out.println("Reset cancelled.");
            	}
            }
            // Invalid commands
            else {
                System.out.println("Invalid command. Please type 'help' for options.");
            }
            
        }

        s.close();
    }
}
