import java.io.*;
import java.net.*;

public class MainServer {
	private static int port = 5000;
	private static String address;
	
	public MainServer ()
	{
		

		try {
			
			ServerSocket server = new ServerSocket(port);
			
			while(true) {

				Socket clientSocket = server.accept();
				
	            DataInputStream  dis = new DataInputStream (clientSocket.getInputStream());
	            //Get input from client
	            String input = dis.readUTF();

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		


	}
	
	public static void main (String [] args)
	{
		new MainServer();
	}

}
