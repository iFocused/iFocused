package application;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import application.gateways.GatewayPool;
import application.gateways.GatewayPoolFactory;
import application.usecases.BlockListRepository;
import application.usecases.UseCasePool;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream in = null;
	private UseCasePool useCasePool;
	private GatewayPool gatewayPool;
	private String address;
	private int port;
	private Timer timer;

	// constructor to put ip address and port
	public Client(String address, int port) {
		gatewayPool = new GatewayPoolFactory().getGatewayPool("ser");
		useCasePool = new UseCasePool(gatewayPool);
		this.address = address;
		this.port = port;
		this.timer = new Timer();
	}

	public void buildClient() {
		// establish a connection
		try {
			socket = new Socket(this.address, this.port);
			OAC("Connected");
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

				if (!line.isEmpty()) {
					this.timer.cancel();
					gatewayPool.refreshData(useCasePool);
					this.timer = new Timer();
					this.runTimer();
				}

			} catch (IOException i) {
				System.out.println(i);
			}
		}

		OAC("Closing Connection");
		//System.out.println("Closing connection");

		// close connection
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runTimer() {
		BlockListRepository blr = this.useCasePool.getBlockListRepository();
		OSFactory osFactory = new OSFactory();

		this.timer.schedule((TimerTask) osFactory.getKiller(blr), 0, 60000);
	}

	private static void OAC(String str) {
		try {
			FileWriter myWriter = new FileWriter("logsCSC392.txt", true);
			myWriter.write(str + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Client c = new Client("127.0.0.1", 5000);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				OAC("in runnable");
				System.out.println("in runnable");
				c.buildClient();
			}
		});

		t1.start();
		c.runTimer();

	}

}
