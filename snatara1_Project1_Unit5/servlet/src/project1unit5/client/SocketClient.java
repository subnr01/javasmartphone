/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit5.client;

import java.net.*;
import java.util.*;
import java.io.*;
import project1unit5.util.*;
import project1unit5.model.Automobile;

public class SocketClient extends Thread {

	private Socket clientSocket;
	private String server;
	private int port;
	private BufferedReader in;
	private boolean openTag = false;
	
	private ObjectOutputStream objectOutputStream = null; 
	private ObjectInputStream objectInputStream = null; 
	

	public SocketClient(String server, int port) {
		setPort(port);
		setHost(server);
	}

	public void setHost(String server) {
		this.server = server;
	}

	public void setPort(int port) {
		this.port = port;
	}

	
	public String getHost(){
		return this.server;
	}
	
	
	public ObjectOutputStream getObjectOutputStream(){
		return this.objectOutputStream;
	}
	public ObjectInputStream getObjectInputStream(){
		return this.objectInputStream;
	}
	// run
	public void run() {
		if (startClient()) {
			startSession();
			closeSession();
		}
	}
	
	
	/*
	 * startClient()
	 * 
	 * Setup socket and
	 * establish connection
	 * 
	 */
	public boolean startClient() {
		try {
			clientSocket = new Socket(server, port); 
		} catch (IOException ex) {
			System.err.println("Unable to connect to " + server);
			return false;
		}

		try {
			
			objectInputStream = new ObjectInputStream(
					clientSocket.getInputStream());
			objectOutputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());

		} catch (Exception ex) {
			System.err.println("Problem in stream to/from " + server);
			return false;
		}
		return true;
	}


	
	
	
	public void startSession() {
		String server = null;
		String userInput = null;
		in = new BufferedReader(new InputStreamReader(System.in));
		

		try {
			server = (String) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		while (true) {

			/*
			 * User interface
			 */
			printUserOptions();

			
			try {
				userInput = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				objectOutputStream.writeObject(userInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			if (userInput.equalsIgnoreCase("Quit")) {
				break;
			} else  if (userInput.equalsIgnoreCase("Upload")) { 
				processUpload();
			}else if (userInput.equalsIgnoreCase("Configure")) {  
				processConfigure();
			} else  {
				System.out.println("Invalid Input");
				continue;
			}
		}
	}
	
	/*
	 * print what you could do in client
	 * */
	public void printUserOptions() {
		System.out.println("------------");
		System.out.println("User Menu");
		System.out.println("------------");
		System.out.println("Upload Properties/text File");
		System.out.println("Configure a Car");
		System.out.println("Quit");
		System.out.print("Type (Upload/Configure/Quit):");
		
	}
	
	/*
	 * printFileList()
	 * print all available files in client
	 * 
	 */
	public String[] printFileList() {
		
		String[] list = new FileIO().getAutoFileList(ConstantValues.automobileFileList);
		for (int i = 0; i < list.length; i++) {
			System.out.println(i + " " + list[i]);
		}
		return list;
	}
	
	/*
	 * closeSession()
	 * 
	 * close input and output stream and client socket
	 * 
	 * */

	public void closeSession() {
		try {
			clientSocket.close();
			objectOutputStream.close();
			objectInputStream.close();
			System.out.println("closed!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String arg[]) {
		String local = null;
		
		try {
			local = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		
		SocketClient client = new SocketClient(local, ConstantValues.PORT);
		client.start();
	}
	
	/*
	 *  uploadPropertiesFile()
	 *   
	 * 
	 *  Upload Properties file to
	 *  server
	 * 
	 */
	public void uploadPropertiesFile(String fileName) {
		Properties prop = new Properties();

		try {
			FileInputStream fileInputStream = new FileInputStream(
					fileName);
			prop.load(fileInputStream);
			fileInputStream.close();
			
			objectOutputStream.writeObject(prop);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			server = (String) objectInputStream
					.readObject();
			System.out.println("Server: " + server);
			System.out.println("\n");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 *  uploadTxtFile()
	 *   
	 * 
	 *  Upload text file to
	 *  server
	 * 
	 */
	public void uploadTxtFile(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.err.println("File not found");
				return;
			}
				
			FileInputStream fileInputStream = new FileInputStream(new File(fileName));
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());
			byte[] buf = new byte[1024];
			int len = 0;
			
			while ((len = fileInputStream.read(buf, 0, 1024)) !=-1) {
				bufferedOutputStream.write(buf,0,len);
				bufferedOutputStream.flush();
			}
			fileInputStream.close();
			
		} catch (IOException e) {
			System.err.println("File not found");
			return;
		} 
		try {
			server = (String) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(server);
		
	}
	
	/*
	 *  processUpload()
	 *   
	 * 
	 *  Process the uplaod 
	 *  option
	 * 
	 */
	public void processUpload() {
		/* Start upload */
		try {
			
			server = (String) objectInputStream.readObject();
			System.out.println("Server: " + server+ "\n");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		/*
		 * Get filename and upload
		 */
		String fileName = "";
		while (true) {
		String[] autoFileList = printFileList();
		String userInput = null;
		try {
			userInput = in.readLine();
			while (!userInput.matches("[0-9]+")
					|| Integer.parseInt(userInput) < 0
					|| Integer.parseInt(userInput) > autoFileList.length - 1) {
				System.out.println("please input a valid number");
				userInput = in.readLine();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int fileIndex = Integer.parseInt(userInput);

		fileName = autoFileList[fileIndex];
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				//objectOutputStream.writeObject("Invalid");
				System.err.println("File not found");
				System.err.println("Input valid file number");
				continue;
			}
		
			/* send the name of the file to server */
			objectOutputStream.writeObject(fileName);
			/* Server responded */
			server = (String) objectInputStream.readObject();
			break;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		}

		/* Uploading properties file */
		if (fileName.matches("[a-zA-Z0-9]+.Properties")) {
			uploadPropertiesFile(fileName);
		} else {
			uploadTxtFile(fileName);
		}		
	}
	
	
	
	/*
	 * processConfigure 
	 * 
	 * Process the configure 
	 * option
	 */
	public Boolean processConfigure() {
		String userInput = null;
		/* Configure starts here */
		try {
			
			server = (String) objectInputStream.readObject();
			System.out.println("Server: " + server);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		ArrayList<String> autoNameList = null;
		try {
			autoNameList = (ArrayList<String>) objectInputStream
					.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		try {
			server = (String) objectInputStream.readObject();
			System.out.println("Server: " + server);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		if (autoNameList.size() == 0) {
			System.out.println("Server has empty list");
			System.out.println("please upload cars to use the configure option");
			return false;
		}
		System.out.println("-----------------------------");
		System.out.println("Printing all Automobile details");
		for (int i = 0; i < autoNameList.size(); i++) {
			System.out.println("Model " + i + " : "
					+ autoNameList.get(i));
		}
		System.out.println("-----------------------------");
		
		System.out.println("Select a number of model to configure");
		try {
			userInput = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (!userInput.matches("[0-9]+")
				|| Integer.parseInt(userInput) < 0
				|| Integer.parseInt(userInput) > autoNameList.size() - 1) {
			System.out.println("please input a legal number");
			try {
				userInput = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int userChoice = Integer.parseInt(userInput);
		String modelName = autoNameList.get(userChoice);

		try {
			objectOutputStream.writeObject(modelName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		startConfigure();
		return true;
	}
	
	/*
	 * startConfigure()
	 * 
	 * Let the user 
	 * configure for
	 * new options
	 * 
	 */
	
	public void startConfigure() {
		Automobile selectedAuto = null;
		try {
			selectedAuto = (Automobile) objectInputStream
					.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		try {
			server = (String) objectInputStream.readObject();
			System.out.println("Server: " + server);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------------");
		System.out.println("Start Choosing the options for Car configuration");
		new SelectCarOption().StartConfigureAction(selectedAuto);

		System.out.println("----------------------------");
		System.out.println("Configured Car of your Choice");
		System.out.println(selectedAuto.getName());
		selectedAuto.printChoice();
		System.out.println("----------------------------");
		System.out.println();
	}
	public boolean getOpenTag(){
		return this.openTag;
	}
	
}
