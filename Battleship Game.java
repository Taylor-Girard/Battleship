
import java.util.Scanner;

class BattleshipGame{

    public static void main(String[] args) {
        
        //initialize all variables/arrays that will be used
        String player1;
        
        //print welcome message
        System.out.println("Welcome to Battleship!");

        //get player name
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        player1 = input.nextLine();
        System.out.println("Hi " + player1 + ". Thank you for playing!");


       

    }
}