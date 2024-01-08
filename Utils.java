import javax.swing.JOptionPane;

public class Utils {
    public static void updateStatusLine(String message){
        Globals.status.setText(" Game Status: " + message);
    }
    
    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static int otherPlayer(int cp) {
        if (cp == Globals.PLAYER_ONE)
            return Globals.PLAYER_TWO;
        else if (cp == Globals.PLAYER_TWO)
            return Globals.PLAYER_ONE;
        else
            return Globals.NO_PLAYER;
    }

    public static String initialNetworkConnection() {
        String[] serversNames = {Globals.SERVER1_NAME,
                               Globals.SERVER2_NAME,
                               Globals.SERVER3_NAME,
                               Globals.SERVER4_NAME};         
        String[] serversAddresses  = {Globals.SERVER1_ADDRESS,
                               Globals.SERVER2_ADDRESS,
                               Globals.SERVER3_ADDRESS,
                               Globals.SERVER4_ADDRESS};
        String serverName = (String) JOptionPane.showInputDialog(null, 
                                                                 "Choose a Server",
                                                                 "The TTT Connection",
                                                                 Globals.OK_CANCEL,
                                                                 null,
                                                                 serversNames,
                                                                 Globals.SERVER1_NAME);
                                                                 
                                                                 
        if (serverName == null) {
            Globals.serverIPAddress = null;
        }
        else {
            int i = 0;
            for(; !serverName.equals(serversNames[i]); i++);
            Globals.serverIPAddress = serversAddresses[i];
            
            int errorCode = NetIO.sendRequest("" + Globals.REQUEST_TO_PLAY_GAME + 
                                              "00" + 
                                              leftPad(NetIO.myUserName(), Globals.CLIENT_ID_LENGTH, '0') + 
                                              leftPad(NetIO.myIPAddress(), Globals.MAX_IP_ADDRESS_LENGTH, '0') +
                                              Globals.NO_MESSAGE,
                                              Globals.serverIPAddress);
                                              
            if (errorCode != Globals.NET_OK) {
                JOptionPane.showMessageDialog(null,
                                              "Timed out. Server not found",
                                              "Tic Tac Toe Connection",
                                              JOptionPane.ERROR_MESSAGE);
                Globals.serverIPAddress = null;
            }                                  
                                              
            
        }
        
        return Globals.serverIPAddress;
    }
    
    public static String leftPad(String text, int desiredLen, char paddingItem) {
        String result = "";
        for (int i = 0; i < desiredLen - text.length(); i++) {
            result = paddingItem + result;
        }
        return result + text;
    }
} 
