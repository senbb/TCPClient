package ensisa.crypto.tcpclient;

import ensisa.crypto.tcpcommon.FileHelper;
import java.net.Socket;
import java.util.List;

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
            Writer writer = new Writer(connection.getOutputStream());
            writer.getList();
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
        Writer writer = new Writer(connection.getOutputStream());
        writer.getFile(filename);
        writer.send();
        Reader reader = new Reader(connection.getInputStream());
        reader.receive();
        return reader.getFile();
    }
}