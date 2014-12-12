package ensisa.crypto.tcpclient;

import ensisa.crypto.tcpcommon.FileHelper;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastien
 */
class Session {
    private Socket connection;

    public Session(Socket connection) 
    {
        this.connection = connection;
    }
    
    public List<String> getList()
    {
        try
        {
            ClientWriter writer = new ClientWriter(connection.getOutputStream());
            writer.createGetList();
            writer.send();
            Reader reader = new Reader(connection.getInputStream());
            reader.receive();
            return reader.getFilenames();
        }
        catch(IOException e)
        {
            return null;
        }
    }
    
    public FileHelper getFile(String filename)
    {
        ClientWriter writer;
        try {
            writer = new ClientWriter(connection.getOutputStream());
            writer.createGetFile(filename);
            writer.send();
            Reader reader = new Reader(connection.getInputStream());
            reader.receive();
            return reader.getFile();
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}