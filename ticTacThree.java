import static java.lang.System.out;
import java.util.Scanner;

public class ticTacThree
{
    static char emptySpace = '.';
    static Scanner keyboard = new Scanner(System.in);
    
    public static void emptyBoard(char[][] board)
    {
        out.println();
        for(int row = 0; row < 3; row++) {
            out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
        }
        out.println();
    }
    
    public static char[][] createEmptyBoard()
    {
        char[][] gameBoard = new char[3][3];
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                gameBoard[row][col] = emptySpace;
            }
        }
        return gameBoard;
    }    
            
    public static boolean win(char[][] board)
    {
        return horizontalWin(board) || verticalWin(board) || diagonalWin(board);
    }
    
    public static boolean full(char[][] board)
    {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(board[row][col] == emptySpace) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean horizontalWin(char[][] board)
    {
        for(int row=0; row < 3; row++) {
            if(board[row][0] != emptySpace && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean verticalWin(char[][] board)
    {
        for(int col = 0; col < 3; col++) {
            if(board[0][col] != emptySpace && board[0][col] == board [1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean diagonalWin(char[][] board)
    {
        if(board[0][0] != emptySpace && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        
        if(board[0][2] != emptySpace && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
    
    public static void Welcome()
    {
        out.println("Welcome to Tic Tac Toe!");
        out.println("To play, enter a number for which box to play in.");
        out.println("1 2 3");
        out.println("4 5 6");
        out.println("7 8 9");
        out.println("You'll need a buddy to play with.  Ready to begin?  Here we go!");
    }
    
    public static boolean playAgain()
    {
        out.print("Would you like to play again?");
        keyboard.nextLine();
        String answer = keyboard.nextLine();
        return(answer.equalsIgnoreCase("y"));
    }
    
    public static char positionToken(int position, char[][] board)
    {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return board[row][col];
    }
    
    public static void placeToken(int position, char[][] board, boolean xTurn)
    {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        if(xTurn) {
            board[row][col] = 'x';
        } else {
            board[row][col] = 'o';
        }
    }
    
    public static void placePositionToken(char[][] board, boolean xTurn)
    {
        boolean invalidInput = true;
        boolean full = true;
        int position = 0;
        
        do {
            if(xTurn) {
                out.print("X where?");
            } else {
                out.print("O where?");
            }
            position = keyboard.nextInt();
            invalidInput = (position < 1 || position > 9);
            if(invalidInput) {
                out.println("Sorry, position " + position + " is not valid. try 1 to 9.");
            } else {
                full = (positionToken(position, board) != emptySpace);
                if(full) {
                    out.println("Sorry, position " + position + " is already full.  Please choose another position");
                }
            }
        } while (full || invalidInput);
        placeToken(position, board, xTurn);
    }
    
    public static void game()
    {
        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        boolean xStart = true;
        boolean xTurn = xStart;
        
        Welcome();
        
        do {
            char[][] gameBoard = createEmptyBoard();
            xTurn = xStart;
            xStart = ! xStart;
            boolean keepGoing = true;
            emptyBoard(gameBoard);
            
            do {
                placePositionToken(gameBoard, xTurn);
                emptyBoard(gameBoard);
                if(win(gameBoard)) {
                    keepGoing = false;
                    if(xTurn) {
                        xWins++;
                        out.println("X Wins!");
                    } else {
                        oWins++;
                        out.println("O Wins!");
                    }
                } else if(full(gameBoard)) {
                    keepGoing = false;
                    draws++;
                    out.println("The game is a draw.  Nobody wins:(");
                } else { 
                }
                xTurn = ! xTurn;
            } while (keepGoing);
            out.println("Score:  X =" + xWins + ", O =" + oWins + ", draws =" + draws);
        } while (playAgain());
    }
    
    public static void main(String[] args)
    {
        game();
        out.println("Goodbye!");
    }    
            
        
        
        
}