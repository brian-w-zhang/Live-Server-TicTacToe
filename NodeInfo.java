public class NodeInfo {
    char command = 0;
    String rowColPlayer = "";
    String userName = "";
    String ipAddress = "";
    String message = "";


    public NodeInfo() {
        command = 0;
        rowColPlayer = "";
        userName = "";
        ipAddress = "";
        message = "";
    }    
    
    public NodeInfo(String request) {
        System.out.println(request);
        command = request.charAt(0);
        rowColPlayer = request.substring(1, 3);
        userName = request.substring(3, 18);
        ipAddress = request.substring(18, 33);
        message = request.substring(33);
    }
    
    public char getCommand() {
        return command;
    }
    
    public String getRowColPlayer() {
        return rowColPlayer;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String toString() {
        return "Command              : " + (int) getCommand() + "\n" +
               "RowColPlayer         : " + getRowColPlayer() + "\n" + 
               "User Name            : " + userName + "\n" + 
               "Sourceip-Address     : " + ipAddress + "\n" +
               "Message              : " + getMessage();
    
    }
    
    
    public static void main(String[] args) {
        NodeInfo info = new NodeInfo((char) 245 + "12" + "000000123456789" + "00010.178.3.100" + "Your opponent is Dan...wait your turn.");
        System.out.println(info);
    }
    

}
