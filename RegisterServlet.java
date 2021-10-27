package pack;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 response.sendRedirect("/Reg.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
		String user = request.getParameter("uid");
		String pwd= request.getParameter("password");
		String fname = request.getParameter("fname");
		String lname= request.getParameter("lname");
		String email= request.getParameter("email");
		Class.forName("com.mysql.cj.jdbc.Driver");  
		java.sql.Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/users","root","root"); 
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery("select * from usersdata where userid='"+user+"'");
		boolean userfound = false;
		if(rs.next())
		{
			if(rs.getString(1).equals(user))
			{
				userfound=true;
			}
		}
		if(userfound)
		{   response.getWriter().println("User Already Exists!!!!");
			// response.sendRedirect("BusBooking.jsp");
			 
		}
		else
		{
			//response.getWriter().println("Please Check Username and Password");
		
		String insert ="insert into usersdata values('"+user+"','"+pwd+"','"+fname+"','"+lname+"','"+email+"')";
		PreparedStatement insertrow1 = con.prepareStatement(insert);
		insertrow1.execute();
		  HttpSession session=request.getSession();
		 session.setAttribute("user",user);
		 response.sendRedirect("/RegisterServlet");
			
		
		}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		 	response.getWriter().println("Please Check MAil Format");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
