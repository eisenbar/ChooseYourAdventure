import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException; 
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Adventure1 extends LevelObject{

   

	/*
    *TODO: Support for input adventure JSON file
    *TODO: ADD in JSON libraries
    *TODO: Support for voice input
    *
    */
	 public Adventure1(String level) {
			super(level);
			
	}
	 
    private static int startGame() {

        int binaryDecision = -1;

        System.out.println("Welcome to the Game!");
        System.out.println("The Goal is to find the treasure!");
        System.out.println("Do you want to go left or right");
        binaryDecision = getInput("Left", "Right");

        if(binaryDecision == 0){
            System.out.println("You went left!");
        }

        else if(binaryDecision == 1){
            System.out.println("You went right!");
        }

        else{
            System.out.println("Game Over!"); 
        }

        return 0;
    }

    /*
    *Takes in two options and grabs the input from the 
    *user. Supports keyboard input and 2 decisions.
    */
    private static int getInput(String option1, String option2) {
        String d = "";
        Scanner input = new Scanner(System.in);
        boolean loop = true;

        System.out.print("(" + option1 + " or "+ option2 + "): ");

        while (loop) {
            d = input.nextLine();
            
            //option1 chosen
            if (d.equalsIgnoreCase(option1)) {
                input.close();
                return 0;
            } 
            
            //option2 chosen
            else if (d.equalsIgnoreCase(option2)) {
                input.close();
                return 1;
            } 
            
            //quits
            else if (d.equalsIgnoreCase("quit")) {
                loop = false;
            } 
            
            //bad input
            else {
                System.out.println("Try Again");
                System.out.print("(" + option1 + " or "+ option2 + "): ");
            }
        }

        input.close();
        return -1;
    }
    
    public static void main(String args[]){

        //int result;
        String input = "";
        
        
        try{
        	
        	File file = new File("adv1.json");
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\0");
            input = sc.next();
            sc.close();
            
            LevelObject level = new LevelObject(input);
            System.out.println(level.getRound(0));
        
        }
        catch (FileNotFoundException e){
        	e.printStackTrace();
        }
        
       // System.out.println(input);
       // Adventure1 adv1 = new Adventure1();
        //result = startGame();

        return;
    }
}