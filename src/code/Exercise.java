package code;


import java.util.*;
import java.sql.*;


public class Exercise {

    private String name;
    private String description;

    private static List<Exercise> exercises = new ArrayList<Exercise>();
    private static final String[] defaults = { "bench press", "squat", "deadlift"};

    
    // For SQL Statements.
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;


    public Exercise(String name){
        this.name = name;
        this.description = "No description. Use 'update' feature to add one.";
    }

    public Exercise(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public static void addExercise(Exercise e){
        exercises.add(e);
    }
    
    public static Exercise getExercise(String name) {
    	for (Exercise e : exercises) {
    		if (e.getName().equals(name)) {
    			return e;
    		}
    	}
    	return null;
    }

    public static List<Exercise> exerciseList(){
        return exercises;
    }
    
    // Connects to SQLite local database in the "files" folder under the repo.
    public static List<Exercise> startUp() {
        try {
        	Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:./files/exercises.db");

            statement = connect.createStatement();

            resultSet = statement.executeQuery("SELECT name, description FROM exercises");


            while (resultSet.next()) {
                String exName = resultSet.getString("name");
                String exDesc = resultSet.getString("description");
                exercises.add(new Exercise(exName, exDesc));
                
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        catch (ClassNotFoundException c) {
        	c.printStackTrace();
        	System.exit(2);
        }

        

        return exercises;
    }

    // Add SQL Query for the DB.
    public static boolean addQuery(Exercise e) {
    	String name = e.getName();
    	String desc = e.getDescription();
    	try {
    		preparedStatement = connect.prepareStatement("INSERT INTO exercises VALUES (?,?)");
    		preparedStatement.setString(1, name);
    		preparedStatement.setString(2, desc);
    		preparedStatement.execute();
    	}
    	catch (SQLException s) {
    		s.printStackTrace();
    		return false;
    	}
    	
    	return true;
    }
    
    // Remove Query.
    public static boolean removeQuery(Exercise e) {
    	String name = e.getName();
    	if (name.equals(defaults[0]) || name.equals(defaults[1]) || name.equals(defaults[2])) {
    		System.out.println("Cannot delete default exercise.");
    		return false;
    	}
    	try {
    		preparedStatement = connect.prepareStatement("DELETE FROM exercises WHERE name = ?");
    		preparedStatement.setString(1, name);
    		preparedStatement.execute();
    		System.out.println("Successfully removed " + name + ".");
    	}
    	catch (SQLException s) {
    		s.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    // Update Description.
    public void setDesc(String n, String d) {
    	try {
    		preparedStatement = connect.prepareStatement("UPDATE exercises SET description = ? WHERE name = ?");
    		preparedStatement.setString(1, d);
    		preparedStatement.setString(2, n);
    		preparedStatement.execute();
    		System.out.println("Successfully updated " + n + ".");
    		
    		for (Exercise e : exercises) {
    			if (e.getName().equals(n)) {
    				e.description = d;
    			}
    		}
    		return;
    	}
    	catch (SQLException s) {
    		s.printStackTrace();
    		return;
    	}
    }
    
    
    // Checking if exercise exists.
    public static boolean containsExercise(Exercise e) {
        for (Exercise i : exercises){
            if (i.getName().equals(e.getName())){
                return true;
            }
        }
        return false;
    }
    
    // Resets to application defaults.
    public static boolean reset() {
    	try {
    	
    		// delete all except for defaults specified above.
    		
	    	preparedStatement = connect.prepareStatement("DELETE FROM exercises "
	    			+ "WHERE name <> ? AND name <> ? AND name <> ? ");
	    	preparedStatement.setString(1, defaults[0]);
			preparedStatement.setString(2, defaults[1]);
			preparedStatement.setString(3, defaults[2]);
			preparedStatement.execute();
			
			
			// Clear list, then add from database.
			exercises.clear();
			
			statement = connect.createStatement();

            resultSet = statement.executeQuery("SELECT name, description FROM exercises");


            while (resultSet.next()) {
                String exName = resultSet.getString("name");
                String exDesc = resultSet.getString("description");
                exercises.add(new Exercise(exName, exDesc));
                
            }
            
            System.out.println("Successfully reset application.");
            return true;
			
			
    	}
    	catch (SQLException s) {
    		s.printStackTrace();
    		return false;
    	}
    	
    }
}
