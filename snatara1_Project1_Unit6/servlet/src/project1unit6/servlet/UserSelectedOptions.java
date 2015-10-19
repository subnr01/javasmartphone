package project1unit6.servlet;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import project1unit6.client.SocketClient;
import project1unit6.model.Automobile;
import project1unit6.util.*;

@WebServlet("/ConfigurePage")
public class UserSelectedOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String modelName;
								
	private SocketClient client;

	/**
	 * @see HttpServlet#HttpServlet() init the servlet
	 */
	@Override
	public void init(ServletConfig config) {
		System.out.println("SUBBU IN INIT");
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		modelName = request.getParameter("model");
		Automobile auto = null;
		response.setContentType("text/html");
		
		while (!client.getOpenTag()) {

		}
		ObjectOutputStream objectOutputStream = client.getObjectOutputStream();
		ObjectInputStream objectInputStream = client.getObjectInputStream();

		objectOutputStream.writeObject("getModel");
		objectOutputStream.flush();

		objectOutputStream.writeObject(modelName);
		objectOutputStream.flush();

		
		try {
			auto = (Automobile) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		while (auto == null) {};
		
		System.out.println(auto.getName());
		
		
		configure(auto, request, response);
	}
	
	
	void configure( Automobile auto, HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException { 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		while (session == null) {};
		session.setAttribute("modelbaseprice", modelName + "=" + auto.getBasePrice());
		LinkedHashMap<String, Float> colorSet = auto.getOptionSetMap("Color");
		LinkedHashMap<String, Float> tranmmissionSet = auto.getOptionSetMap("Transmission");
		LinkedHashMap<String, Float> absSet = auto.getOptionSetMap("Brakes/Traction Control");
		LinkedHashMap<String, Float> airbagSet = auto.getOptionSetMap("Side Impact Airbags");
		LinkedHashMap<String, Float> roofSet = auto.getOptionSetMap("Power Moonroof");

		String colorStr = "<select name=color>" + "<optgroup label=\"select color\">";
		for (String s : colorSet.keySet()) {
			colorStr += "<option value=\"" + s + "=" + colorSet.get(s) + "\">" + s + "</option>";
		}
		colorStr += "</optgroup>";

		String transStr = "<select name=transmission>" + "<optgroup label=\"select transmission\">";
		for (String s : tranmmissionSet.keySet()) {
			transStr += "<option value=\"" + s + "=" + tranmmissionSet.get(s) + "\">" + s + "</option>";
		}
		transStr += "</optgroup>";

		String absStr = "<select name=abs>" + "<optgroup label=\"select abs\">";
		for (String s : absSet.keySet()) {
			absStr += "<option value=\"" + s + "=" + absSet.get(s) + "\">" + s + "</option>";
		}
		absStr += "</optgroup>";

		String airbagStr = "<select name=airbag>" + "<optgroup label=\"select airbag\">";
		for (String s : airbagSet.keySet()) {
			airbagStr += "<option value=\"" + s + "=" + airbagSet.get(s) + "\">" + s + "</option>";
		}
		airbagStr += "</optgroup>";

		String moonroofStr = "<select name=moonroof>" + "<optgroup label=\"select moonroof\">";
		for (String s : roofSet.keySet()) {
			moonroofStr += "<option value=\"" + s + "=" + roofSet.get(s) + "\">" + s + "</option>";
		}
		moonroofStr += "</optgroup>";

		String[][] variables = { { "Make/Model:", modelName }, { "Color:", colorStr }, { "Transmission:", transStr },
				{ "ABS/Traction Control:", absStr }, { "Side Impact Air Bags:", airbagStr },
				{ "Power Moonroof", moonroofStr } };

		String title = "Basic Car Choice";
		out.println(Helper.headWithTitle(title) + "<BODY BGCOLOR=\"#FFFFFF\">\n" + "<H1 ALIGN=\"CENTER\">"
				+ title + "</H1>\n" + "<form action=\"ResultPage.jsp\" method=\"Get\">"
				+ "<TABLE BORDER=1 ALIGN=\"CENTER\">\n");
		
		for (int i = 0; i < variables.length; i++) {
			String varName = variables[i][0];
			String varValue = variables[i][1];
			if (varValue == null)
				varValue = "<I>Not specified</I>";
			out.println("<TR><TD>" + varName + "<TD>" + varValue);
		}
		out.println("<TR><TD> <TD> <input type=\"submit\" value=\"Done\">");
		out.println("</TABLE>");
		out.println("</form ></BODY></HTML>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		//client.closeSession();
		
	}

}