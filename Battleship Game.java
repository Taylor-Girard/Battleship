
import java.util.Scanner;
import java.util.Arrays;

class BattleshipGame{

    public static void main(String[] args) {

        //initialize counters
        int i;
        int j;

        //initialize all variables/arrays that will be used
        String player1;
        char[][] player1Board;

        //print welcome message
        System.out.println("Welcome to Battleship!");

        //get player name
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        player1 = input.nextLine();
        System.out.println("Hi " + player1 + ". Thank you for playing!");

        //create and output the player's empty board
        player1Board = new char[13][13];

        //put the array into the functions to set up the board
        makeTopRows(player1Board);

        //output the empty board
        for (i = 0; i < 13; ++i) {
            System.out.println();
            for (j = 0; j < 13; ++j) {
                System.out.print(player1Board[i][j] + "\t");
            }
        }

        //close the input
        input.close();
    }





    //create method to print the top two rows of the player's board 
    public static char[][] makeTopRows(char[][] board) {

    //initialize counters
    int i;
    int j;

    //print the letters at the very top
    char letter = 'a';
    for (i = 0; i < 1; ++i) {
        for (j = 2; j < 12; ++j) {
            board[i][j] = letter;
            int num = letter;
            num += 1;
            letter = (char) num;
        }
    }
        
    return board;
        
    }

}