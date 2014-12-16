package ensisa.crypto.tcpclient;

import ensisa.crypto.tcpcommon.PROTOCOL;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    private Socket connection;
  
    /**
     * 
     */
    public TCPClient(String serverName) {
        try {
            this.connect(serverName);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * initialize the socket
     */
    public void connect(String serverName) throws IOException {
        connection = new Socket(serverName, PROTOCOL.PORT);
    }
    
    @Deprecated
    //should be in Session
    public void ftp() {
        try {
            ClientReader reader;
            reader = new ClientReader(connection.getInputStream());
            reader.receive();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param args les (éventuels) arguments passés en ligne de commande
     */
    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost");
        client.ftp();
    }
}