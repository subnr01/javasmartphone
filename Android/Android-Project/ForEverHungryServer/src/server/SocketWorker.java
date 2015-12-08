package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import db.DBOps;

public class SocketWorker extends Thread implements SocketServerInterface {
	private Socket socket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private boolean initialized = false;
	private DBOps dbOps = null;
	
	public SocketWorker(Socket socket) {
		this.socket = socket;
		dbOps = new DBOps();
	}
	
	public void openConnection() {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			if(!dbOps.connect()) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Error: I/O error with socket stream" + e.getMessage());
			return;
		}
		
		setInitialized(true);
	}
	
	public void run() {
		openConnection();
		if(!isInitialized()) {
			return;
		}
		
		handleSession();
		closeConnection();
	}
	
	@Override
	public void handleSession() {
		if(!isInitialized()) {
			return;
		}
		
		try {
			String action = (String)in.readObject();
			String username = (String)in.readObject();
			
			if(action.equals("newuser")) {
				String password = (String)in.readObject();
				if(dbOps.createUser(username, password)) {
					out.writeObject("SUCCESS");
					return;
				}
				out.writeObject("FAILED");
			} else if(action.equals("passwordcheck")) {
				String password = (String)in.readObject();
				if(dbOps.authenticate(username, password)) {
					out.writeObject("SUCCESS");
					return;
				}
				out.writeObject("FAILED");
			} else if(action.equals("lostpassword")) {
				StringBuilder oldpassword = new StringBuilder();
				if(dbOps.getpassword(username, oldpassword)) {
					out.writeObject("SUCCESS");
					out.writeObject(oldpassword.toString());
					return;
				}
				out.writeObject("FAILED");
			} else {
				out.writeObject("FAILED");
				return;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace(); // BUG!
		}
	}


	@Override
	public void closeConnection() {
		try {
			socket.close();
			dbOps.close();
			setInitialized(false);
		} catch (IOException e) {
			System.out.println("Error closing client socket");
		}
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}