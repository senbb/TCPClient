package ensisa.crypto.tcpclient;

import java.net.Socket;

public class TCPClient 
{

    private Socket connection = null;
    
    public void connect(String serverName)
    {
        
    }
    
    public void ftp(String serverName)
    {
        connect(serverName);
        Session session = new Session(connection);
        while(true)
        {
            
        }
    }
    
    /**
     * 
     * @param args les (éventuels) arguments passés en ligne de commande
     */
    
    public static void main(String[] args)
    {
        
    }
}
