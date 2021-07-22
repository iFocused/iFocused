package application;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
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

//	public Client() {
//		gatewayPool = new GatewayPoolFactory().getGatewayPool("ser");
//		useCasePool = new UseCasePool(gatewayPool);
//	}

	// constructor to put ip address and port
	public Client(String address, int port) {
		gatewayPool = new GatewayPoolFactory().getGatewayPool("ser");
		useCasePool = new UseCasePool(gatewayPool);
		this.address = address;
		this.port = port;
	}

	public void buildClient() {

		// establish a connection
		try {
			socket = new Socket(this.address, this.port);
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
		Client c = new Client("127.0.0.1", 5000);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("in runnable");
				c.buildClient();
			}
		});
		
		t1.start();

//		Client c = new Client();
		BlockListRepository blr = c.useCasePool.getBlockListRepository();
		Timer timer = new Timer();
		OSFactory osFactory = new OSFactory();
		
		timer.schedule( (TimerTask) osFactory.getKiller(blr), 0, 60000);
//		for (int key : blr.getBlockLists().keySet()) {
//			System.out.println(blr.getBlockLists().get(key).getBlockedProcesses().get(0).getProcessName());
//			System.out.println(blr.getBlockLists().get(key).getBlockedWebsites().get(0).getWebsiteName());
//		}
//		Client client = new Client("127.0.0.1", 5000);

	}
}
