package application;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream in = null;

	// constructor to put ip address and port
	public Client(String address, int port) {
		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from the server socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		}

		// string to read message from input
		String line = "";

		// reads message from server until "Over" is sent
		while (!line.equals("Over")) {
			try {
				line = in.readUTF();
				System.out.println(line);

			} catch (IOException i) {
				System.out.println(i);
			}
		}

		System.out.println("Closing connection");

		// close connection
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Client client = new Client("127.0.0.1", 5000);
	}
}
