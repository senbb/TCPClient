package ensisa.crypto.tcpclient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Writer {

	private OutputStream outputStream;
	private ByteArrayOutputStream baos = new ByteArrayOutputStream ();
	private DataOutputStream output = new DataOutputStream (baos);
	
	public Writer(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	private void writeInt (int v) {
		try {
			output.writeInt(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	private void writeUTF (String v) {
		try {
			output.writeUTF(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void createText(String txt) {
		writeUTF (txt);
	}

	public void send() {
		byte [] message = baos.toByteArray();
		try {
			outputStream.write(message);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createGetList() {
		writeInt(Protocol.GET_FILE_LIST);
	}
	
	public void createGetFile(String fileName) {
		writeInt(Protocol.GET_FILE_LIST);
		writeUTF(fileName);
	}

}