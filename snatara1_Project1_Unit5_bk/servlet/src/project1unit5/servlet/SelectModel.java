package project1unit5.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project1unit5.client.SocketClient;
import project1unit5.model.Automobile;
import project1unit5.util.*;

/**
 * Servlet implementation class 
 */
@WebServlet("/selectmodel")
public class SelectModel extends HttpServlet {
	
	
	private static final long serialVersionUID = 977129131725861763L;
	private SocketClient client;

	/**
	 * @see HttpServlet#HttpServlet() init the servlet
	 */
	@Override
	public void init(ServletConfig config) {
		String strLocalHost = "";
		
		try {
			strLocalHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println("Unable to find local host");
		}
		this.client = new SocketClient(strLocalHost, ConstantValues.PORT);
		client.start();
	}
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		client.closeSession();
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n Entering Get");
		while (!client.getOpenTag()) {}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		ObjectOutputStream objectOutputStream = client.getObjectOutputStream();
		ObjectInputStream objectInputStream = client.getObjectInputStream();

		objectOutputStream.writeObject("configure");
		objectOutputStream.flush();
		System.out.println("\n Server is connected ");
		ArrayList<String> modelList = null;
		try {
			Thread.sleep(200);
			String server = (String) objectInputStream.readObject();
			System.out.println("\n Server sending " + server);
			modelList = (ArrayList<String>) objectInputStream.readObject();
			server = (String) objectInputStream.readObject();
			System.out.println("\n Server sent " + server);
		} catch (ClassNotFoundException | IOException  |InterruptedException e) {
			e.printStackTrace();
		}

		while (modelList == null) {};

		String title = "Available Car Models";

		out.println(Helper.headWithTitle(title) + "<BODY BGCOLOR=\"#FFFFFF\">\n" + "<H1 ALIGN=\"CENTER\">"
				+ title + "</H1>\n");

		if (modelList.size() != 0) {
			out.println("<form ALIGN=\"CENTER\" action=\"ConfigurePage\" method=\"Get\">");
			out.println("<p>" + "Models:");

			out.println("<select name = \"model\">");
			for (int i = 0; i < modelList.size(); i++) {
				out.println("<option value=\"" + modelList.get(i) + "\">" + modelList.get(i) + "</option>");
			}
			out.println("</select>");
			out.println("<p>");
			out.println("<input type=\"submit\" value=\"Done\">");
			System.out.println(" DONE DONE DONE");
		} else {
			out.println("No Models Uplaoded to Server");
		}
		objectOutputStream.writeObject("none");
		out.println("</form ></BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in post");
		doGet(request, response);
	}

}