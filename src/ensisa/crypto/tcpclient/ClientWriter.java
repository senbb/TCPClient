package ensisa.crypto.tcpclient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import ensisa.crypto.tcpcommon.PROTOCOL;

public class ClientWriter {

	private OutputStream outputStream;
	private ByteArrayOutputStream baos = new ByteArrayOutputStream ();
	private DataOutputStream output = new DataOutputStream (baos);
	
	public ClientWriter(OutputStream outputStream) {
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
		writeInt(ensisa.crypto.tcpcommon.PROTOCOL.GET_FILE);
	}
	
	public void createGetFile(String fileName) {
		writeInt(ensisa.crypto.tcpcommon.PROTOCOL.GET_FILE);
		writeUTF(fileName);
	}

}