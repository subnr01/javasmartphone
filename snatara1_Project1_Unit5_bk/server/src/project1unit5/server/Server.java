/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit5.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import project1unit5.util.*;


public class Server {
	private ServerSocket serverSocket = null;
	
	public Server() {
		try {
			
			serverSocket = new ServerSocket(ConstantValues.SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Listening on port " + ConstantValues.SERVER_PORT);
	}
	
	/*
	 * Start the server
	 */
	public void runServer() {
		ServerThread clientConnection = null;
		try {
			while(true) {
				Socket clientSocket = serverSocket.accept();
				clientConnection = new ServerThread(clientSocket);
				clientConnection.start();
			}
        } catch (IOException e) {
        		e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.runServer();
	}
}
