/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit4.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;


public interface AutoServer {
	/* Build from Properties */
	public void buildAutoFromProperty(Properties carProperties);
	/* build a automobile from .txt file */
	public void buildAutoFromTxt(String fileName);
	/* return all available automobile in LinkedHashMap */
	public ArrayList<String> getAllModelList();
	/* return selected model to client */
	public void sendModelClient(ObjectOutputStream objectOutputStream, 
			String modelName) throws IOException;
}
