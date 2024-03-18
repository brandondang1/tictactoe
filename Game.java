import java.util.Scanner;
import java.lang.Math;
import java.util.Random;


/**
 * Class containing functions and variables to run game.
 *
 * @author Brandon Dang
 * @version 2024-03-18
 */
public class Game {

    int[][] board;
    int side;
    int turn;
    int spot;
    int players;

    //constructor
    public Game() {

        board = new int[3][3];
        side = 1;
        turn = 1;
        spot = 0;
        players = 0;

    }

    //getter/setters
    public void setPlayers(int players){

        this.players= players;


    }

    /**
     * Print Tic Tac Toe Board
     * loops through 2D integer array and prints symbols to represent tiles on board
     *
     * @return void
     */
    public void print_board() {

        String line = "";

        for (int i = 0; i < board.length; i++) {

            line = "";

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == 1) {

                    line += "X ";

                } else if (board[i][j] == -1) {

                    line += "@ ";

                } else {

                    line += board[i][j] + " ";

                }

            }

            System.out.println(line);

        }

        System.out.println();

    }

    /**
     * User input choice on board given in instructions
     * contains simple error checks
     *
     * @param input Scanner object to process user input
     * @return int spot - spot on board selected
     */

    public int input_choice(Scanner input) {

        boolean valid_choice = false;

        while (!valid_choice) {

        System.out.println("Please Select Spot (1-9): ");
        // error check user input
        do {

            while (!input.hasNextInt()) {
                System.out.println("That is not a number! ");
                input.next();
            }

            spot = input.nextInt();

        } while (spot <= 0 || spot > 9);

        valid_choice = check_spot();

    }

        return spot;

    }

    /**
     * Check if spot if valid
     *
     * @return true if valid, false if taken
     */
    public boolean check_spot() {

        int col = ((spot - 1) / 3);
        int row = ((spot - 1) % 3);

        //formula to convert 1-9 spot on board to 2D array placement

        if (board[col][row] == 0) {

            return true;

        } //if spot is 0 == not taken
        System.out.println();
        System.out.println("Spot Taken. Please select an empty spot.");
        print_board();

        return false;

    }

    /**
     * Update board / 2D array with selection
     *
     * @return void
     */
    public void update_board() {

        switch (spot) { //switch statement for each 9 spaces on board
            case 1:

                board[0][0] = side; //side represents 1 or -1 which will later be changed for tile in print_board()
                break;

            case 2:
                board[0][1] = side;
                break;

            case 3:
                board[0][2] = side;
                break;

            case 4:
                board[1][0] = side;
                break;

            case 5:
                board[1][1] = side;
                break;

            case 6:
                board[1][2] = side;
                break;

            case 7:
                board[2][0] = side;
                break;

            case 8:
                board[2][1] = side;
                break;

            case 9:
                board[2][2] = side;
                break;

        }// end of switch statement

        print_board();
        side = side * -1; //swap sides for second player
        turn++; //add to turn count for check_board() function

    }// end of update board

    /**
     * Checks board for winning solutions to game
     *
     * @return true if three in a row, false otherwise
     */
    public boolean check_board() {

        if (turn >= 6) {

            // check horizontal
            int total;

            for (int i = 0; i < 3; i++) {

                total = 0;

                for (int j = 0; j < 3; j++) {

                    total += board[i][j]; //because -1 and 1 were used in 2D array, easy way to find winning solution is to check if row/col/diagonal is |3|

                }

                if (Math.abs(total) == 3) {

                    return true;

                }

            }

            // check vertical

            for (int i = 0; i < 3; i++) {

                total = 0;

                for (int j = 0; j < 3; j++) {

                    total += board[j][i];
                }

                if (Math.abs(total) == 3) {

                    return true;

                }

            }

            // check diagonal

            total = 0;
            total = board[0][0] + board[1][1] + board[2][2];

            if (Math.abs(total) == 3) {

                return true;

            }

            total = 0;
            total = board[0][2] + board[1][1] + board[2][0];

            if (Math.abs(total) == 3) {

                return true;

            }

        }

        return false;

    }

    /**
     * Simple robot opponent that selects spaces on board using Random()
     *
     * @return void
     */
    public void robot_opponent_select() {

        boolean status = false;
        Random rand = new Random();

        while (!status) {

            spot = rand.nextInt(9) + 1;

            int col = ((spot - 1) / 3);
            int row = ((spot - 1) % 3);

            if (board[col][row] == 0) {

                status = true;

            }

            

        }

        update_board();

    }

}
