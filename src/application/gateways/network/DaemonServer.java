package application.gateways.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.gateways.ServerGateway;

public class DaemonServer implements Runnable, ServerGateway, ObserverNotifier {
	private volatile boolean changedOccured = false;

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

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());

			String line = "";

			// keep reading until "Over" is input
			while (true) {
//				System.out.println(this.changedOccured);
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				if (this.changedOccured) {
					System.out.println("YOOOO");
					out.writeUTF("change has occured in main app");
					OWC("hello from server");
					this.changedOccured = false;
				}
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
		System.out.println("change has occurred!");
		this.changedOccured = true;

	}
}
