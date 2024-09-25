public class GameBoard {
    Node head;

    public GameBoard() {
        head = createBoard();
    }

    //Create TicTacToe game board that has 9 cells
    private Node createBoard() {
        Node head = new Node(1);
        Node curr = head;
        for (int i = 2; i <= 9; i++) {
            curr.next = new Node(i);
            curr = curr.next;
        }
        return head;
    }

    //Show the game board
    public void printBoard() {
        Node curr = head;
        for (int i = 1; i <= 9; i++) {
            System.out.print(" " + (curr.symbol == ' ' ? i : curr.symbol) + " ");
            if (i % 3 == 0) {
                System.out.println();
                if (i != 9) {
                    System.out.println("---|---|---");
                }
            }
            else {
                System.out.print("|");
            }
            curr = curr.next;
        }
    }

    //Add player move to the game board
    public boolean updateBoard(int position, char player) {
        Node curr = head;
        while (curr != null) {
            if (curr.position == position && curr.symbol == ' ') {
                curr.symbol = player;
                return true;
            }
            curr = curr.next;
        }
        return false; //Invalid if position is already taken
    }

    //Check for winning condition
    public boolean checkWin(char player) {
        return (checkCells(1, 2, 3, player) ||  // Row 1
                checkCells(4, 5, 6, player) ||  // Row 2
                checkCells(7, 8, 9, player) ||  // Row 3
                checkCells(1, 4, 7, player) ||  // Column 1
                checkCells(2, 5, 8, player) ||  // Column 2
                checkCells(3, 6, 9, player) ||  // Column 3
                checkCells(1, 5, 9, player) ||  // Diagonal 1
                checkCells(3, 5, 7, player));   // Diagonal 2
    }

    //Check for 3 consecutive cells for player to win
    private boolean checkCells(int pos1, int pos2, int pos3, char player) {
        return getNode(pos1).symbol == player && getNode(pos2).symbol == player && getNode(pos3).symbol == player;
    }

    //Get node symbol for position
    private Node getNode(int pos) {
        Node curr = head;
        while (curr != null) {
            if (curr.position == pos) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    //Draw condition used to see if game board is full
    public boolean isFull() {
        Node curr = head;
        while (curr != null) {
            if (curr.position == ' ') {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    //Reset to start new game
    public void resetBoard() {
        Node curr = head;
        while (curr != null) {
            curr.symbol = ' ';
            curr = curr.next;
        }
    }
}
