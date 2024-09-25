import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    GameBoard board;
    char currPlayer;

    //Constructor for game board
    public Game() {
        board = new GameBoard();
        currPlayer = 'X';
    }

    //Game loop
    public void startGame() {
        boolean runGame = true;
        Scanner scnr = new Scanner(System.in);

        while (runGame) {
            board.printBoard();
            System.out.print("Player " + currPlayer + "'s turn to move. Please enter a position between 1 and 9: ");
            int pos = -1;

            //Error handling for invalid inputs
            try {
                pos = scnr.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid! Please enter a positive integer");
                scnr.next();
                continue;
            }

            //Checking for a valid move
            if (pos < 1 || pos > 9 || !board.updateBoard(pos, currPlayer)) {
                System.out.println("Invalid position. Try again.");
                continue;
            }

            //Check if the current player has won the game
            if (board.checkWin(currPlayer)) {
                board.printBoard();
                System.out.println("Player " + currPlayer + " has won.");
                runGame = false;
            }

            //Check if game ends in draw
            else if (board.isFull()) {
                board.printBoard();
                System.out.println("Draw!");
                runGame = false;
            }

            //Change current player
            else {
                currPlayer = (currPlayer == 'X') ? 'O' : 'X';
            }
        }

        //Ask to play game again
        System.out.println("Do you want to play again? (y/n)");
        String answer = scnr.next();
        if (answer.equals("y")) {
            board.resetBoard();
            currPlayer = 'X';
            startGame();
        }
        else {
            System.out.println("Thanks for playing! Goodbye!");
        }
    }

}
