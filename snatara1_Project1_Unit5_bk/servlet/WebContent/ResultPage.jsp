<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	double totalCost = 0;
	String model = ""; 
	String basePrice = ""; 

	/*
		OptionSets 
	*/
	String color = "";
	String transmission = ""; 
	String abs = "";
	String airbags = "";
	String moonroof = ""; 
	String colorPrice = "";
	String transmissionPrice = "";
	String absPrice = "";
	String airbagsPrice = "";
	String moonroofPrice = "";

	
	/*
	 * Get the model and base price
	 */
	String[] OptionSetValues = null; 
	String modelandbaseprice = (String) session.getAttribute("modelbaseprice");
	if (modelandbaseprice != null) {
		OptionSetValues = modelandbaseprice.split("=");
		model = OptionSetValues[0];
		basePrice = OptionSetValues[1];

		totalCost = Float.parseFloat(basePrice);
	} else {
		model = "";
		basePrice = "";
		totalCost = Float.parseFloat(basePrice);
	}
	
	
	/*
	 * Get the color and price
	 */
	String colorPair = request.getParameter("color");
	OptionSetValues = colorPair.split("=");
	color = OptionSetValues[0];
	colorPrice = OptionSetValues[1];
	totalCost += Float.parseFloat(colorPrice);
	
	/*
	 * Get the transmission
	 * and values of transmission values
	 */
	String transmissionPair = request.getParameter("transmission");
	OptionSetValues = transmissionPair.split("=");
	transmission = OptionSetValues[0];
	transmissionPrice = OptionSetValues[1];
	totalCost += Float.parseFloat(transmissionPrice);
	
	/*
	 * Get the Brake values
	 */
	String absPair = request.getParameter("abs");
	OptionSetValues = absPair.split("=");
	abs = OptionSetValues[0];
	absPrice = OptionSetValues[1];
	totalCost += Float.parseFloat(absPrice);

	/*
	 * Get the airbag values
	 */
	String airbagPair = request.getParameter("airbag");
	OptionSetValues = airbagPair.split("=");
	airbags = OptionSetValues[0];
	airbagsPrice = OptionSetValues[1];
	totalCost += Float.parseFloat(airbagsPrice);
	
	/*
	 * Get the moonroof values
	 */
	String moonroofPair = request.getParameter("moonroof");
	OptionSetValues = moonroofPair.split("=");
	moonroof = OptionSetValues[0];
	moonroofPrice = OptionSetValues[1];
	totalCost += Float.parseFloat(moonroofPrice);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get Price</title>
</head>
<body BGCOLOR="#FFFFFF">
	<CENTER>
		<h3>Car has been Successfully Configured</h3>
		<table border="1" cellpadding="10" width="400">
			<tr>
				<td><%=model%></td>
				<td>Base Price</td>
				<td><%=basePrice%></td>
			</tr>
			<tr>
				<td>Color</td>
				<td><%=color%></td>
				<td><%=colorPrice%></td>
			</tr>
			<tr>
				<td>Transmission</td>
				<td><%=transmission%></td>
				<td><%=transmissionPrice%></td>
			</tr>
			<tr>
				<td>Brake/Traction Control</td>
				<td><%=abs%></td>
				<td><%=absPrice%></td>
			</tr>
			<tr>
				<td>Side Impact Air Bags</td>
				<td><%=airbags%></td>
				<td><%=airbagsPrice%></td>
			</tr>
			<tr>
				<td>Power Moonroof</td>
				<td><%=moonroof%></td>
				<td><%=moonroofPrice%></td>
			</tr>
			<tr>
				<td><b>Total Price</b></td>
				<td></td>
				<td><%=totalCost%></td>
			</tr>
		</table>
	</CENTER>
</body>
</html>