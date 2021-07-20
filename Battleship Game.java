
import java.util.Scanner;

class BattleshipGame{

    public static void main(String[] args) {
        
        //initialize all variables/arrays that will be used
        String player1;
        char[][] player1Board;
        char[][] opponentBoardReal;
        char[][] opponentBoardFake;
        int carrierPieces = 5;
        int battleshipPieces = 4;
        int cruiserPieces = 3;
        int submarinePieces = 3;
        int destroyerPieces = 2;
        int playerHitCount = 0;
        int opponentHitCount = 0;
        
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
        
        
        //create player's empty board
        player1Board = new char[13][13];
        
        //put the array into the functions to set up the player's board
        setUpBoard(player1Board);
        
        //output the empty board
        outputBoard(player1Board);
        
        //set piece name to carrier and piece number to 5
        String nameAndNumber = "carrier (5 pieces)";
        
        getPieceAndAdd(player1Board, carrierPieces, player1, nameAndNumber);
        
        // set piece name to battleship and piece number to 4
        nameAndNumber = "battleship (4 pieces)";
        
        //get the position of the battleship and place it down
        getPieceAndAdd(player1Board, battleshipPieces, player1, nameAndNumber);
        
        // set piece name to cruiser and piece number to 3
        nameAndNumber = "cruiser (3 pieces)";
        
        // get the position of the cruiser and place it down
        getPieceAndAdd(player1Board, cruiserPieces, player1, nameAndNumber);
        
        // set piece name to submarine and piece number to 3
        nameAndNumber = "submarine (3 pieces)";
        
        // get the position of the submarine and place it down
        getPieceAndAdd(player1Board, submarinePieces, player1, nameAndNumber);
        
        // set piece name to submarine and piece number to 3
        nameAndNumber = "destroyer (2 pieces)";
        
        // get the position of the submarine and place it down
        getPieceAndAdd(player1Board, destroyerPieces, player1, nameAndNumber);
        
        //set up opponent's board (real)
        opponentBoardReal = new char[13][13];
        setUpBoard(opponentBoardReal);

        //add carrier
        getRandomPiece(opponentBoardReal, carrierPieces);

        //add battleship
        getRandomPiece(opponentBoardReal, battleshipPieces);

        //add cruiser
        getRandomPiece(opponentBoardReal, cruiserPieces);

        //add submarine
        getRandomPiece(opponentBoardReal, submarinePieces);

        //add destroyer
        getRandomPiece(opponentBoardReal, destroyerPieces);
        
        //create and display fake opponent board that will be displayed to player
        opponentBoardFake = new char[13][13];
        setUpBoard(opponentBoardFake);

        //have player and opponent switch off picking spots until game is over
        do {

            // Wait for player to show the opponent's move
            System.out.println("Hit enter twice to see the opponent's board:");
            input.nextLine();
            input.nextLine();

            //show opponent board
            outputBoard(opponentBoardReal);
            System.out.println("Here is your opponent's board:");
            outputBoard(opponentBoardFake);

            //ask player what space they want to choose
            System.out.println(player1 + ", pick which space you want to attack");
            String playerChoice = input.nextLine();
            System.out.println();
            opponentHitCount = hitOrMissOpponent(opponentBoardReal, opponentBoardFake, playerChoice, opponentHitCount);

            //if the player wins in this turn, skip to the winning outputs
            if (opponentHitCount >= 17) {
                break;
            }
        
            //Wait for player to show the opponent's move
            System.out.println();
            System.out.println("Hit enter twice to see the opponent's move:");
            input.nextLine();
            input.nextLine();

            //do the opponent's move based on random chance
            playerHitCount = hitOrMissPlayer(player1Board, playerHitCount);
            outputBoard(player1Board);

        } while ((playerHitCount < 17) && (opponentHitCount < 17));

        //let the player know if they won or not
        if (opponentHitCount >= 17) {
            System.out.println("Congratulations! You sunk all of the opponent's battleships and won the game :)");
        } else {
            System.out.println("Oh no! The opponent sunk all of your ships. You lose :(");
        }

        //output both boards to compare
        System.out.println("Hit enter twice to see all of the final boards:");
        input.nextLine();
        input.nextLine();
        System.out.println();
        System.out.println("Your board:");
        outputBoard(player1Board);

        System.out.println();
        System.out.println("Opponent board:");
        outputBoard(opponentBoardFake);
        System.out.println("Their battleship placement:");
        outputBoard(opponentBoardReal);



        
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
    public static void getPieceAndAdd(char[][] board, int pieces, String playerName, String pieceName) {

        //initialize needed variables
        String pos;
        String dir;
        Scanner input = new Scanner(System.in);

        // have the user choose where they want to put their battleship
        do {
            // Ask user where to place pice
            System.out.println();
            System.out.println("Where would you like to place the front of your " + pieceName + "?");
            System.out.print("Please enter in the format a1, b2, etc: ");
            pos = input.nextLine();

            // Ask user what direction they want the piece to go in
            System.out.println();
            System.out.print(
                    "What direction do you want the piece to go in? Enter h for horizontal and v for vertical: ");
            dir = input.nextLine();
            System.out.println();

        } while (checkPiece(pos, dir, pieces) || (checkOverlap(pos, dir, pieces, board)));

        // set the piece down based on what the user put
        addPiece(pos, dir, pieces, board);

        // have the user hit enter twice to see their new board
        System.out.print(playerName + ", press Enter twice to generate the board with the piece added: ");
        input.nextLine();
        input.nextLine();

        // output board with new piece
        outputBoard(board);

    }
    
    //method to set up the opponent's board
    public static void getRandomPiece(char[][] board, int pieces) {

        //initialize needed values
        int rowNum;
        int colNum;
        char dir;

        do {

            //get random row and column numbers
            rowNum = (int) (Math.random() * 9 + 2);
            colNum = (int) (Math.random() * 9 + 2);
            //get a random direction
            double dirNum =  Math.random();

            if (dirNum >= .5) {
                dir = 'v';
            } else {
                dir = 'h';
            }

        } while (checkRandomPiece(colNum, rowNum, dir, pieces) || (checkRandomOverlap(colNum, rowNum, dir, pieces, board)));
            
        addRandomPiece(colNum, rowNum, dir, pieces, board);

    }
    /* method to check if the random piece can fit in the window with the given position &
    direction */
    public static Boolean checkRandomPiece(int colNum, int rowNum, char direction, int numPieces) {

        //initialize values needed
        Boolean bool = false;

        /* check for both the vertical and horizontal placement if user chose a spot
        with enough space for the piece */
        if ((direction == 'v') && ((rowNum + numPieces + 2) > 10)) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 5) && ((colNum >= 8) && (colNum <= 12)))) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 4) && ((colNum >= 9) && (colNum <= 12)))) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 3) && ((colNum >= 10) && (colNum <= 12)))) {
            bool = true;
        } else if ((direction == 'h' && (numPieces == 2) && (colNum == 11))) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }
    
    // method to check that random piece is not overlapping another piece
    public static Boolean checkRandomOverlap(int columnNum, int rowNum, char direction, int numPieces, char[][] board) {

        // initialize needed variables
        Boolean bool;
        int i;
        int j;

        // for either direction, check if there are any overlapping pieces in the path
        // of the newly placed piece
        if (direction == 'h') {

            for (i = rowNum; i < rowNum + 1; ++i) {
                for (j = columnNum; j < columnNum + numPieces; ++j) {
                    if (board[i][j] == '+') {
                        bool = true;
                        return bool;
                    }

                }
            }

        } else {

            for (i = rowNum; i <= rowNum + numPieces; ++i) {
                for (j = columnNum; j < columnNum + 1; ++j) {
                    if (board[i][j] == '+') {
                        bool = true;
                        return bool;
                    }
                }
            }

        }

        // return the boolean as false value if nothing overlaps
        bool = false;
        return bool;
    }

    // method to place down the pieces for you
    public static void addRandomPiece(int columnNum, int rowNum, char direction, int numPieces, char[][] board) {

        // initialize needed variables
        int i;
        int j;

        // if the direction is horizontal, place the piece across the correct number of columns
        if (direction == 'h') {

            for (i = rowNum; i < rowNum + 1; ++i) {
                for (j = columnNum; j < columnNum + numPieces; ++j) {
                    board[i][j] = '+';
                }
            }

        // if the direction is vertical, place the piece across the correct number of rows
        } else {

            for (i = rowNum; i < rowNum + numPieces; ++i) {
                for (j = columnNum; j < columnNum + 1; ++j) {
                    board[i][j] = '+';
                }
            }

        }
    }
    
    //create function to add a hit or miss symbol onto opponent's board depending player's choice
    public static int hitOrMissOpponent(char[][] realBoard, char[][] fakeBoard, String pos, int hitCount) {

        //initialize needed variables
        char column = pos.charAt(0);
        char row = pos.charAt(1);
        int rowNum = Character.getNumericValue(row);
        int columnNum = Character.getNumericValue(column) - 8;

        //determine if the player hit or miss the ships and put appropriate character
        if (realBoard[rowNum + 2][columnNum] == '+') {
            System.out.println("HIT!!");
            fakeBoard[rowNum + 2][columnNum] = 'X';
            hitCount += 1;
        } else {
            System.out.println("Miss :(");
            fakeBoard[rowNum + 2][columnNum] = '~';
        }
        return hitCount;
    }
    
    // create function to add a hit or miss symbol onto opponent's board depending on random choice choice
    public static int hitOrMissPlayer(char[][] board, int hitCount) {

        //initialize needed variables
        int rowNum;
        int colNum;

        //use computer's random guess (may be changed later to be more advanced) to choose space on player's board
        do{
        rowNum = (int) (Math.random() * 9 + 2.99);
        colNum = (int) (Math.random() * 9 + 2.99);
        } while ((board[rowNum][colNum] == '~') || (board[rowNum][colNum] == 'X'));

        //determine if it is a hit or miss and put the appropriate character
        if (board[rowNum][colNum] == '+') {
            System.out.println("The opponent hit your ship!");
            board[rowNum][colNum] = 'X';
            hitCount += 1;
        } else {
            System.out.println("Yes! The opponent missed your ship.");
            board[rowNum][colNum] = '~';
        }
        return hitCount;
    }

}
