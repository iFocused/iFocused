package application.gateways.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.gateways.ServerGateway;

public class DaemonServer implements Runnable, ServerGateway, ObserverNotifier {

	@Override
	public void run() {
		buildServer(5000);
	}

	public void buildServer(int port) {
		Socket socket = null;
		ServerSocket server = null;
		DataInputStream input = null;
		DataOutputStream out = null;

		try {
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			OWC("Hello from server");
			System.out.println("wrote to file");
			// takes input from terminal
			input = new DataInputStream(System.in);

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());

			String line = "";

			// keep reading until "Over" is input
			out.writeUTF("read signal");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// close the connection
			try {
				input.close();
				out.close();
				socket.close();
			} catch (IOException i) {
				System.out.println(i);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void OWC(String msg) throws IOException {
		FileWriter myFileWriter = new FileWriter("junk.txt", true);
		myFileWriter.write(msg);
		myFileWriter.close();
	}

	@Override
	public void update() {
		System.out.println("change has occurred!!");
	}
}
