
import java.util.Scanner;
import java.util.Arrays;

class BattleshipGame{

    public static void main(String[] args) {

        //initialize all variables/arrays that will be used
        String player1;
        char[][] player1Board;
        int carrierPieces = 5;
        int battleshipPieces = 4;
        int cruiserPieces = 3;
        int submarinePieces = 3;
        int destroyerPieces = 2;
        String carrierPos;
        String carrierDir;

        //print welcome message
        System.out.println("Welcome to Battleship!");
        System.out.println();

        //get player name
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        player1 = input.nextLine();
        System.out.println();

        //pause until player is ready to output the board
        System.out.print("Hi " + player1 + ". Thank you for playing! Press Enter twice to generate your empty board: ");
        input.nextLine();
        input.nextLine();

        //create and output the player's empty board
        player1Board = new char[13][13];

        //put the array into the functions to set up the player's board
        setUpBoard(player1Board);

        //output the empty board
        outputBoard(player1Board);

        do {
            //Ask user to place carrier & in what direction
            System.out.println("Where would you like to place the front of your carrier (5 spaces)?");
            System.out.print("Please enter in the format a1, b2, etc: ");

            carrierPos = input.nextLine();

            System.out.print("What direction do you want to ship to go in? Enter h for horizontal and v for vertical: ");
            carrierDir = input.nextLine();

        } while (checkPiece(carrierPos, carrierDir, carrierPieces));

        //set the carrier down based on what the user put
        addPiece(carrierPos, carrierDir, carrierPieces, player1Board);

        //output board with new piece
        outputBoard(player1Board);

        //close the input
        input.close();
    }





    //create method to make the empty board
    public static char[][] setUpBoard(char[][] board) {

        //initialize counters
        int i;
        int j;

        //create the letters at the very top
        char letter = 'a';
        for (i = 0; i < 1; ++i) {
            for (j = 2; j < 12; ++j) {
                board[i][j] = letter;
                int num = letter;
                num += 1;
                letter = (char) num;
            }
        }

        //create top row
        for (i = 1; i < 2; ++i) {
            for (j = 2; j < 12; ++j) {
                board[i][j] = '-';
            }
        }

        //create numbers at the side
        char number = '0';
        for (i = 2; i < 12; ++i) {
            for (j = 0; j < 1; ++j) {
                board[i][j] = number;
                int changeNum = number;
                changeNum += 1;
                number = (char) changeNum;
            }
        }

        //create left side row
        for (i = 2; i < 12; ++i) {
            for (j = 1; j < 2; ++j) {
                board[i][j] = '|';
            }
        }

        //create right side row
        for (i = 2; i < 12; ++i) {
            for (j = 12; j < 13; ++j) {
                board[i][j] = '|';
            }
        }

        //create bottom row
        for (i = 12; i < 13; ++i) {
            for (j = 2; j < 12; ++j) {
                board[i][j] = '-';
            }
        }

        //return the final empty board
        return board;

    }
    
    //create method to output board
    public static void outputBoard(char[][] board) {

        //initialize counters
        int i;
        int j;

        //output board by looping through indexes and printing
        for (i = 0; i < 13; ++i) {
            System.out.println();
            System.out.println();
            for (j = 0; j < 13; ++j) {
                System.out.print(board[i][j] + "\t");
            }
        }
        System.out.println();
    }
    
    //create method to check if a piece can fit in the window with the given position & direction
    public static Boolean checkPiece(String pos, String dir, int numPieces) {
       
        //initialize needed variables
        char column = pos.charAt(0);
        char row = pos.charAt(1);
        char direction = dir.charAt(0);
        boolean bool = false;
        int rowNum = Character.getNumericValue(row);

        //check for both vertical and horizontal placement if user chose a spot with enough space for the piece
        if ((direction == 'v') && ((rowNum + numPieces) > 10)) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 5) && ((column == 'g') || (column == 'h') || (column == 'i') 
                || (column == 'j')))) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 4) && ((column == 'h') || (column == 'i') || (column == 'j')))) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 3) && ((column == 'i') || (column == 'j')))) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 2) && (column == 'j'))) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    //create a method to place down the pieces for you
    public static void addPiece(String pos, String dir, int numPieces, char[][] board) {
        
        // initialize needed variables
        char column = pos.charAt(0);
        char row = pos.charAt(1);
        char direction = dir.charAt(0);
        int rowNum = Character.getNumericValue(row);
        int columnNum = Character.getNumericValue(column) - 8;
        int i;
        int j;

        if (direction == 'h') {

            for (i = rowNum + 2; i < rowNum + 3; ++i) {
                for (j = columnNum; j < columnNum + numPieces; ++j) {
                    board[i][j] = '+';
                }
            }

        }else{

        
            for (i = rowNum + 2 ; i < rowNum + numPieces + 2; ++i) {
                for (j = columnNum; j < columnNum + 1; ++j) {
                    board[i][j] = '+';
                }
            }

        }
    }

        
}
