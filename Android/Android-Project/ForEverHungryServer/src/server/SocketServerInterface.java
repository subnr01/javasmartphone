package server;

public interface SocketServerInterface {
	public void openConnection();
	public void handleSession();
	public void closeConnection();
}
