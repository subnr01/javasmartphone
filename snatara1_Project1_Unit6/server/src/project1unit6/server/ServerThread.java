/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit6.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import project1unit6.util.*;



public class ServerThread extends Thread {
		

	private int port;
	private Socket clientSocket = null;

	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;

	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.port = ConstantValues.SERVER_PORT;
	}


	public void run() {
	
		if (startServer()) {
			startSession();
			closeSession();
		}
	}
	
	/* start the server */
	public boolean startServer() {
		try {
			objectOutputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
		} catch (Exception e) {
				System.err.println("Unable to obtain stream to/from Echo Port"
						+ port);
				return false;
		}
		return true;
	}

	/* start the session */
	public void startSession() {
		String strInput = "";
		String strOutput = "";
		strOutput = "connect to " + port;
		try {
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		while(true){
			try {
				strInput = (String)objectInputStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				continue;
			}
			if (strInput == null) {
				break;
			}
			try {
			/* received a quit from client */
			if (strInput.equalsIgnoreCase("Quit")){
				System.out.println("Received an Exit from client");
				break;
			} else if(!strInput.equalsIgnoreCase("Upload") && !strInput.equalsIgnoreCase("Configure")
														&& !strInput.equalsIgnoreCase("getModel")){
				continue;
			}
			} catch (Exception e) {
			
			}
			
				/* received an upload from client*/
			if (strInput.equalsIgnoreCase("Upload")) {
				respondUpload();
			} else if (strInput.equalsIgnoreCase("Configure")) {
					System.out.println("Received configure");
					/* Configure request from client */
					respondConfigure();
					
			}else if (strInput.equalsIgnoreCase("getModel")) {
				System.out.println("Received getModel");
				/* Configure request from client */
				returnModelClient();
				
			}
				
			 
		}
	}

	public void closeSession() {
		try {
			clientSocket.close();
			
		} catch (IOException e) {
			System.err.println("Error closing socket");
		}
	}
	
	/* respond to the uplaod message
	 * from client
	 */
	public void respondUpload() {
		BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
		String strOutput;
		strOutput = "Request for upload,  Input "
				+ "the  file number";
		
		try {
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileName = null;
		try {
			fileName = (String)objectInputStream.readObject();
			String output = "Get File Name: " + fileName;
			objectOutputStream.writeObject(output);
		} catch (ClassNotFoundException | IOException e) {
			fileName = "null";
		}
		
		if (fileName.equals("Invalid")) {
			return;
		} 
		
		if (fileName.matches("[a-zA-Z0-9]+.Properties")) {
			Properties prop = null;
			try {
				prop = (Properties) objectInputStream.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			// add car in linked hash map
			buildCarModelOptions.buildAutoFromProperty(prop);
			
			try {
				strOutput = "build Auto successfully";
				objectOutputStream.writeObject(strOutput);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else { 
			/* Upload text file*/
			try {
				File file = new File(fileName);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());
				
				byte[] buf = new byte[10240];
				int len = 0;
				while ((len = bufferedInputStream.read(buf, 0, 10240)) > 0) {
					fileOutputStream.write(buf, 0, len);
					fileOutputStream.flush();
					break;
				}
				fileOutputStream.close();

				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			buildCarModelOptions.buildAutoFromTxt(fileName);
			
			
			File file = new File(fileName);
			file.delete();
			
			
			strOutput = "Build Car From Txt Successfuly";
			try {
				objectOutputStream.writeObject(strOutput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	/*
	 * respond to
	 * the configure message
	 */
	public Boolean respondConfigure() {
		BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
		String strOutput;
		strOutput = "Get configure request";
		try {
			System.out.println (" sending"+ strOutput);
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> autoNameList = buildCarModelOptions.getAllModelList();
		try {
			objectOutputStream.writeObject(autoNameList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		strOutput = "transfer AutoList successfully";
		try {
			System.out.println (" Sending"+ strOutput);
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (autoNameList.size() == 0) {
			return false;
		}

		
		String modelName = null;
		try {
			modelName = (String)objectInputStream.readObject();
			if (modelName == null) {
				return true;
			}
		} catch (ClassNotFoundException | IOException e) {
			
		}
		if ( modelName.equals("none")) {
			return true;
		}
		
		try {
			buildCarModelOptions.sendModelClient(objectOutputStream, modelName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		strOutput = "Automobile information transferred successfully";
		try {
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			
		}
		
		return true;
	}
	
	public void returnModelClient() {
		BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
		String modelName = null;
		try {
			modelName = (String)objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n the model name is "+modelName);
		
		try {
			buildCarModelOptions.sendModelClient(objectOutputStream, modelName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
