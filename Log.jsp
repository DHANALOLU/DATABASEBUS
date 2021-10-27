<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%
String user = request.getParameter("reguid");
pageContext.setAttribute("userId", user);
String userid = (String) pageContext.getAttribute("userId");
//session.setAttribute("userid",user);
String pwd = request.getParameter("regpassword");
Class.forName("com.mysql.cj.jdbc.Driver");  
java.sql.Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/Users","root","root"); 
Statement st = con.createStatement();
ResultSet rs=st.executeQuery("select * from usersdata where userid='"+userid+"'");
boolean userfound = false;
if(rs.next())
{
	if(rs.getString(2).equals(pwd))
	{
		userfound=true;
	}
}
if(userfound)
{   session.setAttribute("userid",userid);
	 response.sendRedirect("BusBooking.jsp");
	 
}
else
{
	response.getWriter().println("Please Check Username and Password");
}
%>
</body>
</html>