import java.util.Scanner;


/**
 * Main class to run game
 *
 * @author Brandon Dang
 * @version 2024-03-18
 */
public class Main {

    public static void main(final String args[]) {

        Game game = new Game();

        Scanner input = new Scanner(System.in);

        boolean status = false;
        

        instructions(); //instruction set

        game.setPlayers(players(input)); //choose between 1 or 2 players

        while (!status) {

            game.input_choice(input);

            game.update_board();

            status = game.check_board();

            if (status) {

                int num = game.turn % 2 + 1; //for winning message to say Player 1 or Player 2

                if (game.players == 2) {

                    System.out.println("Player " + num + " has won!");

                } else {

                    if (num == 1) {

                        System.out.println("You won!");

                    } else {

                        System.out.println("The robot has won!");

                    }

                }

            }

            if (game.players == 1 && !status) { //for single-player option

                game.robot_opponent_select();

                status = game.check_board();

                if (status) {

                    int num = game.turn % 2 + 1;

                    if (game.players == 2) {

                        System.out.println("Player " + num + " has won!");

                    } else {

                        if (num == 1) {

                            System.out.println("You won!");

                        } else {

                            System.out.println("The robot has won!");

                        }

                    }

                }

            }


        }

        input.close();

    }

    /**
     * Prints instruction message as well as tic tac toe board with number indicators at start of game
     *
     * @return void
     */
    public static void instructions() {

        System.out.println("Welcome to Tic Tac Toe! The spot selection is determined in this order:");

        for (int i = 1; i < 9;) {

            for (int j = 0; j < 3; j++) {

                System.out.print(i + " ");
                i++;

            }

            System.out.println();

        }

        System.out.println("The two different tiles are X and @. A blank spot is indicated through a 0.");

        System.out.println();

    }

    /**
     * Allow user to select between single-player and multi-player (local)
     *
     * @param input Scanner to process user input
     * @return int 1 or 2 depending on selection (single or multi)
     */
    public static int players(Scanner input) {

        int players;

        System.out.println("Are you playing single-player or multi-player? (1 or 2) ");

        do {

            while (!input.hasNextInt()) {
                System.out.println("That is not a choice. ");
                input.next();
            }

            players = input.nextInt();

        } while (players < 1 || players > 2);

        return players;

    }

}