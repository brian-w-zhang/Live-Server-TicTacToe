import javax.swing.*;
import java.awt.*;

public class Globals {
    // network constants
    public static final int NET_SEND_ERROR = -1;
    public static final int NET_OK = 0;
    public static final int SENDING_ATTEMPTS_LIMIT = 5;
    public static final int PORT_NUMBER = 5000;
    public static final int TIME_OUT = 10000; // milliseconds
    
    public static final int NET_RECEIVE_ERROR = -2;
    public static final int NET_REQUEST_ERROR = -3;
    public static final int NET_RECEIVE_REQUEST_ERROR = -4;
    public static final int NET_TIME_OUT_ERROR = -5;
    
    public static final int QUEUE_SIZE = 100;

    public static final int CLIENT_ID_LENGTH = 15;
    public static final int MAX_IP_ADDRESS_LENGTH = 15;
    
    // game of tic tac toe constants
    public static final int GAME_TIE = -1;
    public static final int GAME_STILL_ON = 0;
    public static final int NO_PLAYER = 0;
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;
    public static final int OK_CANCEL = 1;
    
    // graphics constants
    public static final int FRAME_X = 250;
    public static final int FRAME_Y = 100;
    public static final int ROW_HEIGHT = 175;
    public static final int COL_WIDTH = 175;
    public static final int ROWS = 3;
    public static final int COLS = 3; 
    public static final int OFFSET = 15;
    
    public static GridPanel[][] grid = new GridPanel[ROWS][COLS];
    public static GridBagConstraints gridBagConstraints = new GridBagConstraints();
    public static JLabel status = new JLabel("Game Status:");
    
    public static String clientIPAddress = "";
    public static String serverIPAddress = "";
    
    public static final String SERVER1_NAME = "Bob's Bar";
    public static final String SERVER2_NAME = "Daniel's Dungeon";
    public static final String SERVER3_NAME = "Prasun and Sons";
    public static final String SERVER4_NAME = "Tilted Towers";
    //public static final String SERVER5_NAME = "Gabe's Garage";
    //public static final String SERVER6_NAME = "Steven's Spa and Lotion";
    
    public static final String SERVER1_ADDRESS = "10.207.98.44";
    public static final String SERVER2_ADDRESS = "10.100.2.102";
    public static final String SERVER3_ADDRESS = "10.100.2.103";
    public static final String SERVER4_ADDRESS = "10.100.2.104";
    
    // constants for game commands (1st byte of transmitted message)
    public static final char REQUEST_UNKNOWN         = 255;
    public static final char REQUEST_TO_PLAY_GAME    = 245; // client to server
    public static final char REQUEST_TO_PROCESS_PLAY = 244; // client to server
    public static final char COMMAND_GAME_TERMINATE  = 243; // server to client
    public static final char COMMAND_TO_WAIT         = 242; // server to client
    public static final char COMMAND_TO_START_GAME   = 241; // server to client
    public static final char COMMAND_YOUR_TURN       = 240; // server to client
    public static final char COMMAND_GAME_OVER       = 239; // server to client
    public static final char COMMAND_DISPLAY_MESSAGE = 238; // server to client
    public static final char REQUEST_TO_DISCONNECT = COMMAND_GAME_TERMINATE; // client to server
    
    public static boolean gameOver = false;
    public static int iAmPlayer = NO_PLAYER;
    public static int currentPlayer = NO_PLAYER;

    
    // command + row + column + identification
    // 1 char  1 char  1 char   15 characters
    
    public static final String NO_MESSAGE = "No message";
    

    
}

