import javax.swing.*;
import java.awt.*;

public class ClientTicTacToe extends JFrame
{
    public static void main (String[] args)
    {
        Globals.serverIPAddress = Utils.initialNetworkConnection();
        if (Globals.serverIPAddress != null)
        {
            JFrame mainWindow = new JFrame ();

            mainWindow.setTitle ("Bloor CI");
            mainWindow.setLocation(Globals.FRAME_X, Globals.FRAME_Y);
            mainWindow.addWindowListener(new WindowEventHandler());
            mainWindow.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
            mainWindow.setResizable (false);

            JPanel tttPanel = new JPanel ();
            tttPanel.setLayout (new GridLayout (Globals.ROWS, Globals.COLS));

            Color c = new Color (215, 171, 243);

            for (int row = 0 ; row < Globals.ROWS ; row++)
            {
                for (int col = 0 ; col < Globals.COLS ; col++)
                {
                    Globals.grid [row] [col] = new GridPanel (c, row, col, Globals.NO_PLAYER);
                    tttPanel.add (Globals.grid [row] [col]);
                }
            }

            GridBagLayout gridBag = new GridBagLayout ();
            mainWindow.getContentPane().setLayout(gridBag);

            // add the game board to the panel
            Globals.gridBagConstraints.gridheight = Globals.ROWS;
            mainWindow.getContentPane ().add (tttPanel, Globals.gridBagConstraints);

            // add the status line to the panel
            Globals.gridBagConstraints.gridx = 0;
            Globals.gridBagConstraints.gridy = Globals.ROWS;
            Globals.gridBagConstraints.anchor = GridBagConstraints.LINE_START;
            mainWindow.getContentPane().add(Globals.status, Globals.gridBagConstraints);

            mainWindow.pack();
            mainWindow.setVisible(true);


            do {
                System.out.println("Waiting...");
                String request = NetIO.receiveRequest();
                //System.out.println("Test " + request);
                NodeInfo commandFromServer = new NodeInfo(request);
                    
                switch (commandFromServer.getCommand()) {
                    case Globals.COMMAND_TO_WAIT:
                        Utils.updateStatusLine(commandFromServer.getMessage());
                        break;
                    case Globals.COMMAND_TO_START_GAME:
                        Globals.iAmPlayer = commandFromServer.getRowColPlayer().charAt(0) - 48;
                        Globals.currentPlayer = Globals.PLAYER_ONE;
                        Utils.updateStatusLine(commandFromServer.getMessage());
                        break;
                    case Globals.COMMAND_YOUR_TURN:
                        int row = (commandFromServer.getRowColPlayer()).charAt(0) - 48;
                        int col = (commandFromServer.getRowColPlayer()).charAt(1) - 48;
                        Globals.grid[row][col].setVal(Globals.currentPlayer);
                        Graphics2D g = (Graphics2D) Globals.grid[row][col].getGraphics();
                        Globals.grid[row][col].drawXorO(g);
                        Globals.currentPlayer = Utils.otherPlayer(Globals.currentPlayer);
                        Utils.updateStatusLine(commandFromServer.getMessage());
                        break;
                    case Globals.COMMAND_GAME_OVER:
                        Utils.updateStatusLine(commandFromServer.getMessage());
                    case Globals.COMMAND_GAME_TERMINATE:
                        Utils.updateStatusLine(commandFromServer.getMessage());
                        break;
                    case Globals.COMMAND_DISPLAY_MESSAGE:
                        break;
                    default:
                        Utils.updateStatusLine("ClientTicTacToe: Unknown Server Command");
                        break;
                }
            }
            while(!Globals.gameOver);


            
        }
        else
        {
            System.out.println ("No server selected");
        }

    }
}
