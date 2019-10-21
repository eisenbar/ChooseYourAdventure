import java.util.Scanner;

public class Adventure1 {

    /*
    *TODO: Support for input adventure txt file
    *
    *TODO: Support for voice input
    *
    */

    Adventure1() {
        return;
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

    public static void main(String args[]) {

        int result;
        Adventure1 adv1 = new Adventure1();
        result = startGame();

        return;
    }
}