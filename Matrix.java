public class Matrix {
    
/* 
0 1 2
3 4 5
6 7 8

a b c
d e f
g h i

[0][0] [0][1] [0][2]

[1][0] [1][1] [1][2]

[2][0] [2][1] [2][2]

winning combinations:
(0, 1, 2)  (board[0][0], board[0][1], board[0][2])  (a, b, c) a !
(3, 4, 5)  (board[1][0], board[1][1], board[1][2])  (d, e, f) e !
(6, 7, 8)  (board[2][0], board[2][1], board[2][2])  (g, h, i) i !

(0, 3, 6)  (board[0][0], board[1][0], board[2][0])  (a, d, g) a !
(1, 4, 7)  (board[0][1], board[1][1], board[2][1])  (b, e, h) e !
(2, 5, 8)  (board[0][2], board[1][2], board[2][2])  (c, f, i) i !

(0, 4, 8)  (board[0][0], board[1][1], board[2][2])  (a, e, i) a !
(2, 4, 6)  (board[2][0], board[1][1], board[0][2])  (g, e, c) e !
*/

    public static int checkWinner(int[][] board) {
        boolean isZero = false;
        int winner = -1;
        int gameState = -2;
        
        int a = board[0][0];
        int b = board[0][1];
        int c = board[0][2];
        int d = board[1][0];
        int e = board[1][1];
        int f = board[1][2];
        int g = board[2][0];
        int h = board[2][1];
        int i = board[2][2];
        
          
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == 0) {
                    isZero = true;
                } 
                if ((a != 0) && ((a == b && a == c) || (a == d && a == g) || (a == e && a == i))) {
                    winner = a;
                } 
                else if ((e != 0) && ((e == d && e == f) || (e == b && e == h) || (e == g && e == c))) {
                    winner = e;
                }
                else if ((i != 0) && ((i == g && i == h) || (i == c && i == f))) {
                    winner = i;
                }
            }
        }
        
        if (winner == -1 && isZero == true) {
            gameState = Globals.GAME_STILL_ON;
        } 
        else if (winner == -1 && isZero == false) {
            gameState = Globals.GAME_TIE;
        }
        else if (winner == 1) {
            gameState = Globals.PLAYER_ONE;
        }
        else if (winner == 2) {
            gameState = Globals.PLAYER_TWO;
        }
        return gameState;
    }
    
    
}
