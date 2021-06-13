
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

        getPieceAndAdd(player1Board, carrierPieces, player1);
        
        //get the position of the battleship and place it down
        getPieceAndAdd(player1Board, battleshipPieces, player1);

        //close the input
        input.close();
    }

    //method to make the empty board
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
    
    //method to output board
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
    
    //method to check if a piece can fit in the window with the given position & direction
    public static Boolean checkPiece(String pos, String dir, int numPieces) {
       
        //initialize needed variables
        char column = pos.charAt(0);
        char row = pos.charAt(1);
        char direction = dir.charAt(0);
        boolean bool = false;
        int rowNum = Character.getNumericValue(row);

        //check for both the vertical and horizontal placement if user chose a spot with enough space for the piece
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

    //method to place down the pieces for you
    public static void addPiece(String pos, String dir, int numPieces, char[][] board) {

        // initialize needed variables
        char column = pos.charAt(0);
        char row = pos.charAt(1);
        char direction = dir.charAt(0);
        int rowNum = Character.getNumericValue(row);
        int columnNum = Character.getNumericValue(column) - 8;
        int i;
        int j;

        //if the direction is horizontal, place the piece across the correct number of columns
        if (direction == 'h') {

            for (i = rowNum + 2; i < rowNum + 3; ++i) {
                for (j = columnNum; j < columnNum + numPieces; ++j) {
                    board[i][j] = '+';
                }
            }

            //if the direction is vertical, place the piece across the correct number of rows
        } else {

            for (i = rowNum + 2; i < rowNum + numPieces + 2; ++i) {
                for (j = columnNum; j < columnNum + 1; ++j) {
                    board[i][j] = '+';
                }
            }

        }
    }
    
    //method to check that piece is not overlapping another piece
    public static Boolean checkOverlap(String pos, String dir, int numPieces, char[][] board) {

        // initialize needed variables
        char column = pos.charAt(0);
        char row = pos.charAt(1);
        char direction = dir.charAt(0);
        Boolean bool;
        int rowNum = Character.getNumericValue(row);
        int columnNum = Character.getNumericValue(column) - 8;
        int i;
        int j;

        //for either direction, check if there are any overlapping pieces in the path of the newly placed piece
        if (direction == 'h') {

            for (i = rowNum + 2; i < rowNum + 3; ++i) {
                for (j = columnNum; j < columnNum + numPieces; ++j) {
                    if (board[i][j] == '+') {
                        bool = true;
                        return bool;
                    }

                }
            }

        } else {

            for (i = rowNum + 2; i < rowNum + numPieces + 2; ++i) {
                for (j = columnNum; j < columnNum + 1; ++j) {
                    if (board[i][j] == '+') {
                        bool = true;
                        return bool;
                    }
                }
            }

        }

        //return the boolean as false value if nothing overlaps
        bool = false;
        return bool;
    }
    
    //method that gets the piece and places it down
    public static void getPieceAndAdd(char[][] board, int pieces, String playerName) {
        
        //initialize needed variables
        String pos;
        String dir;
        Scanner input = new Scanner(System.in);


        // have the user choose where they want to put their battleship
        do {
            // Ask user where to place battleship
            System.out.println();
            System.out.println("Where would you like to place the front of your battleship (4 spaces)?");
            System.out.print("Please enter in the format a1, b2, etc: ");
            pos = input.nextLine();

            // Ask user what direction they want the battleship to go in
            System.out.println();
            System.out
                    .print("What direction do you want to ship to go in? Enter h for horizontal and v for vertical: ");
            dir = input.nextLine();
            System.out.println();

        } while (checkPiece(pos, dir, pieces)
                || (checkOverlap(pos, dir, pieces, board)));

        // set the battleship down based on what the user put
        addPiece(pos, dir, pieces, board);

        // have the user hit enter twice to see their new board
        System.out.print(playerName + ", press Enter twice to generate the board with the battleship added: ");
        input.nextLine();
        input.nextLine();

        // output board with new piece
        outputBoard(board);


    }
    
}
