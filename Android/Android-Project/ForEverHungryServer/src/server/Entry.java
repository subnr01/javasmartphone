package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import db.DBOps;

public class Entry {
	private static final int SERVER_PORT = 6000;
	
	public static boolean dbinit() {
		DBOps db = new DBOps();
		if(!db.connectPrimitive()) {
			return false;
		}
		
		if(!db.createDB()) {
			return false;
		}
		
		db.close();
		
		if(!db.connect()) {
			return false;
		}
		if(!db.createTable()) {
			return false;
		}
		
		db.close();
		return true;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket socket = null;
		
		if(!dbinit()) {
			System.out.println("ERROR: Unable to initialize server database. Exiting");
			return;
		}
		System.out.println("setting up server socket");
		try {
			socket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			System.out.println("Failed to create server socket Abort!");
			e.printStackTrace();
		}
		System.out.println("Starting to accept requests from client");
		
		while(true) {
			Socket clientSocket;
			try {
				clientSocket = socket.accept();
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
				continue;
			}
			
			
			SocketWorker sw = new SocketWorker(clientSocket);
			sw.start();	
		}
	}
}
