package src;

import java.io.*;
import java.util.*;

public class Driver {

    public static List<Exercise> exercises = new ArrayList<Exercise>();
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        
        exercises = Exercise.initList();
        
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
                System.out.println("Commands:\tprint\tadd\texit");
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

                if (Exercise.containsExercise(new Exercise(line))){
                    System.out.println("Exercise already exists.");
                }
                else {
                    exercises.add(new Exercise(line));

                    // Adds to file
                    try (BufferedWriter output = new BufferedWriter(new FileWriter("files/exercises.txt", true))){
                    	output.write("\n" + line);
                    }
                    catch (Exception e){
                        System.out.println(e);
                        System.exit(1);
                    }
                }
            }
            else {
                System.out.println("Invalid command. Please type 'help' for options.");
            }
            
        }

        s.close();
    }
}
