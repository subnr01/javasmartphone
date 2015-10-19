/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit6.server;
import java.io.*;
import java.util.*;
import project1unit6.adapter.BuildAuto;


public class BuildCarModelOptions implements AutoServer{
	
	private static BuildAuto buildAuto;
	
	
	public BuildCarModelOptions(){
		buildAuto = new BuildAuto();
	}
	
	/*
	 * 	Build from properties 
	 */
	public void buildAutoFromProperty(Properties carProperties){
		
		buildAuto.buildAutoFromProperty(carProperties);
		
	}
	
	/*
	 * 	Build from text file 
	 */
	public void buildAutoFromTxt(String fileName) {
		buildAuto.buildAutoFromTxt(fileName);
		
	}

	public ArrayList<String> getAllModelList(){
		ArrayList<String> autoNameList = buildAuto.getAllModelList();
		return autoNameList;
	}
	
	
	/*
	 * 	Send auto details to client
	 */
	public void sendModelClient(ObjectOutputStream objectOutputStream, 
			String modelName) throws IOException{
		
		buildAuto.sendModelClient(objectOutputStream, modelName);
	}


	
	

}
