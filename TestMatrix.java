/*****************************************************************************************************************/
/* To run this program: Place it in the same folder that contains the Globals class and the Matrix with         */ 
/* checkWinner() method in it and run                                                                                   */
/*                                                                                                                      */
/* May 25 2023                                                                                                          */
/* This program generates 19,693 TicTacToe configuration boards and checks the correctness of the                       */
/* method Matrix.checkWinner() written by students from the course slides of Culminating Activity (slide 12)    */
/* Boards that contain configurations with 2 winners are skipped                                                        */
/* Program ends immediately when an error is found                                                                      */
/*                                                                                                                      */
/* Nothing needs to be done to this code in order to run it.                                                    */
/* All necessary calls are made from within here (see line labelled CALL TO STUDENT PROGRAM)                    */
/*****************************************************************************************************************/
import hsa.*;
public class TestMatrix {
    public static int checkBoardNumber = 0;
    public static int checkBoardTested = 0;
    public static int invalidBoards    = 0;

    private static int checkWinner (int[] [] board)
    {
        int result = Globals.GAME_TIE;
        String boardStr = "";

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                boardStr = boardStr + board[row][col];

        if (boardStr.substring(0, 3).equals("111") ||
                boardStr.substring(3, 6).equals("111") ||
                boardStr.substring(6, 9).equals("111") ||

                boardStr.charAt(0) == '1' && boardStr.charAt(3) == '1' && boardStr.charAt(6) == '1' ||
                boardStr.charAt(1) == '1' && boardStr.charAt(4) == '1' && boardStr.charAt(7) == '1' ||
                boardStr.charAt(2) == '1' && boardStr.charAt(5) == '1' && boardStr.charAt(8) == '1' ||

                boardStr.charAt(0) == '1' && boardStr.charAt(4) == '1' && boardStr.charAt(8) == '1' ||
                boardStr.charAt(2) == '1' && boardStr.charAt(4) == '1' && boardStr.charAt(6) == '1') {

            result = Globals.PLAYER_ONE;
        }
        else if (boardStr.substring(0, 3).equals("222") ||
                boardStr.substring(3, 6).equals("222") ||
                boardStr.substring(6, 9).equals("222") ||

                boardStr.charAt(0) == '2' && boardStr.charAt(3) == '2' && boardStr.charAt(6) == '2' ||
                boardStr.charAt(1) == '2' && boardStr.charAt(4) == '2' && boardStr.charAt(7) == '2' ||
                boardStr.charAt(2) == '2' && boardStr.charAt(5) == '2' && boardStr.charAt(8) == '2' ||

                boardStr.charAt(0) == '2' && boardStr.charAt(4) == '2' && boardStr.charAt(8) == '2' ||
                boardStr.charAt(2) == '2' && boardStr.charAt(4) == '2' && boardStr.charAt(6) == '2') {

            result = Globals.PLAYER_TWO;

        }
        else if (boardStr.indexOf("" + Globals.NO_PLAYER) != -1) {
            result = Globals.GAME_STILL_ON;
        }

        else {
            result = Globals.GAME_TIE;
        }

        return result;
    }

    public static boolean boardFilled(int[][] board) {
        boolean filled = true;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                filled = filled && board[row][col] != -1;
            }
        }
        return filled;
    }

    // in a valid ttt game, the number of player 1s is always equal to number of player 2s or
    // the number of player 1s is one more than the number of player 2s
    private static boolean validTicTacToeGame(int[][] matrix) {
        boolean result = false;
        int onesCount = 0;
        int twosCount = 0;

        String board = "";
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board = board + matrix[row][col];
            }
        }

        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == '1')
                onesCount++;
            if (board.charAt(i) == '2')
                twosCount++;            
        }
        result = onesCount == twosCount || onesCount == twosCount + 1;
        result = result && !((board.substring(0, 3).equals("111") || board.substring(3, 6).equals("111") || board.substring(6, 9).equals("111")) &&
                             (board.substring(0, 3).equals("222") || board.substring(3, 6).equals("222") || board.substring(6, 9).equals("222")));

        String tr = "";
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tr = tr + matrix[col][row];
            }
        }
        result = result && !((tr.substring(0, 3).equals("111") || tr.substring(3, 6).equals("111") || tr.substring(6, 9).equals("111")) &&
                             (tr.substring(0, 3).equals("222") || tr.substring(3, 6).equals("222") || tr.substring(6, 9).equals("222")));
        return result;
    }
    
    public static void generateBoard(String board) {
        int[][] matrix = new int[3][3];
        if (board.length() == 9) {
            // System.out.println(board);
            checkBoardNumber++;
            
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    matrix[row][col] = Integer.parseInt("" + board.charAt(3 * row + col));
                }
            }

            if (validTicTacToeGame(matrix)) {
//printBoard(matrix);
//System.out.println();
                checkBoardTested++;
                if (checkWinner(matrix) != Matrix.checkWinner(matrix)) {
                        /*********     CALL TO STUDENT PROGRAM ********/
                        System.out.println("Tester checkWinner(): " + checkWinner(matrix) + " Student checkWinner(): " + Matrix.checkWinner(matrix));
                        System.out.println("***error in board");
                        System.out.println(board);
                        printBoard(matrix);
                        int k = Stdin.readInt();
                        System.exit(0);
                }
                else if (checkBoardNumber % 50 == 0) {
                        System.out.println("Testing board: " + checkBoardNumber);
                }
            }
            else {
                invalidBoards++;
            }
        }
        else {
            for (int player = 0; player < 3; player++) {
                generateBoard(board + player);
            }
        }
    }
    
    public static void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        String board = "";    
        generateBoard("");
        
        System.out.println("==============================");
        System.out.println("Games generated : " + checkBoardNumber);
        System.out.println("Invalid boards  : " + invalidBoards);
        System.out.println("Games tested    : " + checkBoardTested);
        System.out.println("Tests passed    : " + checkBoardTested);
        System.out.println();

        if (checkBoardTested + invalidBoards == (int) Math.pow(3, 9))
            System.out.println("#### All tests passed ####");
        else
            System.out.println("***Some tests failed");
        System.out.println("==============================");
    }
}
