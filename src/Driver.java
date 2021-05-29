import java.io.*;
import java.util.*;

public class Driver {

    public static List<String> exercises = new ArrayList<String>();
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);        

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
                for (String ex : exercises){
                    System.out.print(ex + ", ");
                }
                System.out.println();
            }

            // Add a new exercise
            else if (opt.equals("add")){
                System.out.println("Please type an exercise to add: ");
                String line = s.nextLine().toUpperCase();

                if (exercises.contains(line)){
                    System.out.println("Exercise already exists.");
                }
                else {
                    exercises.add(line);

                    // Adds to file
                    try (BufferedWriter output = new BufferedWriter(new FileWriter("files/exercises.txt", true))){
                        output.write(line);
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
