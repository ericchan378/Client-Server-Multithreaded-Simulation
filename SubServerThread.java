import java.io.*;
import java.net.*;

public class SubServerThread implements Runnable{
	private static final String GREEN = "Green";
	private static final String ORANGE = "Orange";
	private static final String STAFF = "Staff";
	private static final String CLOCK = "Clock";
	public static Object lock = new Object(); 
	private Socket client;
	private ServerSocket server;
	private String threadtype;
	private String name;
	private BufferedReader br;
	private PrintWriter pw;
	
	public SubServerThread (Socket client, ServerSocket server) {
		this.client = client;
		this.server = server;
		new Thread(this).start();
		
	}
	
	@Override
	public void run() {
		try {
			DataInputStream  dis = new DataInputStream (client.getInputStream());
			DataOutputStream dos = new DataOutputStream (client.getOutputStream());
			
			String input[] = (dis.readUTF()).split(" ");//input in format threadtype, name, method number
			
			this.threadtype = input[0];
			this.name = input[1];
			int methodNumber = Integer.parseInt(input[2]);
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
