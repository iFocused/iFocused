package application.gateways.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

			//runDaemonClient();
			socket = server.accept();
			System.out.println("Client accepted");

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());

			String line = "";

			// keep reading until "Over" is input
			while (true) {
				if (this.changedOccured) {
					out.writeUTF("change has occured in main app");
					this.changedOccured = false;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void runDaemonClient() {
		try {
//			Runtime.getRuntime().exec(
//					"java -jar \"C:\\Users\\Abwatts\\Desktop\\School\\University Courses\\Second Year\\Summer\\CSC392\\Project\\iFocused\\DaemonBlocker\\DaemonBlocker.jar\" ");
			Runtime.getRuntime().exec(
					"java -jar DaemonBlocker.jar");
			System.out.println("jar file exectued");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		System.out.println("change has occurred!");
		this.changedOccured = true;

	}
}
