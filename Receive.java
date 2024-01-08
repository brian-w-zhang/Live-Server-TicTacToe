public class Receive {
    public static void main(String[] args) {
        System.out.println(NetIO.myIPAddress());
        do {
            String message = NetIO.receiveRequest();
            System.out.println(message);
        } while (true);   
    }
}
// client to server
^ full message one moment