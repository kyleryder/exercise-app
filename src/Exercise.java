

import java.util.*;
import java.sql.*;


public class Exercise {

    private String name;
    private String description;

    private static List<Exercise> exercises = new ArrayList<Exercise>();

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;


    public Exercise(String name){
        this.name = name;
        this.description = "";
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
    
    public static List<Exercise> startUp(String user, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/exercises", user, pass);

            statement = connect.createStatement();

            resultSet = statement.executeQuery("select name, description from exercises");


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
    
    public static boolean containsExercise(Exercise e) {
        for (Exercise i : exercises){
            if (i.getName().equals(e.getName())){
                return true;
            }
        }
        return false;
    }
}
