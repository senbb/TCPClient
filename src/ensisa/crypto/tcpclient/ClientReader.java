package ensisa.crypto.tcpclient;

import ensisa.crypto.tcpcommon.FileHelper;
import ensisa.crypto.tcpcommon.PROTOCOL;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastien
 */
public class ClientReader {
    
    private DataInputStream inputStream;
    private List<String> filenames;
    private long size;
    private byte[] content;
    private String text;
    private int type;
    private FileHelper file;
    
    public ClientReader(InputStream inputStream)
    {
        this.inputStream = new DataInputStream(inputStream);
    }

    private int readInt()
    {
        try {
            return inputStream.readInt();
        } catch (IOException ex) {
            Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    private long readLong()
    {
        try {
            return inputStream.readLong();
        } catch (IOException ex) {
            Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    /**
     * @return string in the inputStream
     */
    private String readUTF()
    {
        try {
            return inputStream.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    /**
     * Get all filenames
     */
    private void fillFilenames(int count)
    {
        filenames = new ArrayList<String>();
        for(int i=0;i<count;i++)
        {
            filenames.add(readUTF());
        }
    }
    
    private byte readByte()
    {
        try {
            return inputStream.readByte();
        } catch (IOException ex) {
            Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    private byte[] readContent(int count)
    {
        for(int i=0; i<count; i++)
        {
            content[i] = readByte();
        }
        
        return content;
    }
    
    private void readFile()
    {
        int length = readInt();
        file = new FileHelper(readContent(length), readUTF());
    }
    
    private void fillContent(int count)
    {
        content = new byte[count];
        for(int i=0; i<count; i++)
        {
            content[i] = readByte();
        }
    }
    
    public void receive()
    {
        System.out.println(readUTF());
        /*type = readInt();
        /*switch(type)
        {
            case PROTOCOL.SEND_FILE_LIST:
                fillFilenames(readInt());
                break;
            case PROTOCOL.SEND_FILE:
                readFile();
                break;
        }*/
    }
    
    public String getText()
    {
        return text;
    }
    
    public int getType()
    {
        return type;
    }
    
    public List<String> getFilenames()
    {
        return filenames;
    }
    
    public long getSize()
    {
        return size;
    }
    
    public byte[] getContent()
    {
        return content;
    }

    public FileHelper getFile() 
    {
        return file;
    }
}