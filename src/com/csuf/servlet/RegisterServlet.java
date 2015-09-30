package com.csuf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


@WebServlet(name = "RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String email1 = request.getParameter("email1");
		String password = request.getParameter("password");
		String bloodtype=request.getParameter("bloodtype");
	    String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String errorMsg = null;
		if(firstname == null || firstname.equals("")){
			errorMsg = "Fields can't be null or empty.";
		}
		if(lastname == null || lastname.equals("")){
			errorMsg = "Fields can't be null or empty.";
		}
		if(email == null || email.equals("")){
			errorMsg = "Fields can't be null or empty.";
		}
		if(email1 == null || email1.equals("")){
			errorMsg = "Fields can't be null or empty.";
		}
		if(password == null || password.equals("")){
			errorMsg = "password can't be null or empty.";
		}
		/**if(gender == null || gender.equals("")){
			errorMsg = "Select your Gender";
		}**/
		if(bloodtype == null || bloodtype.equals("")){
			errorMsg = "Blood type cannot be empty";
		}
		if(!email.equals(email1))
		{
			errorMsg="Email does not match";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into users(firstname,lastname,password,email,dob,gender,bloodtype) values (?,?,?,?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, password);
			ps.setString(4, email);
			ps.setString(5, dob);
			ps.setString(6, gender);
			ps.setString(7, bloodtype);
			ps.execute();
			
			logger.info("User registered with email="+email);
			
			//forward to login page to login
			//PrintWriter out= response.getWriter();
			//out.println("<font color=green>Registration successful, please login below.</font>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserLogin.html");
			request.setAttribute("Message", "Register Successful, Please Login below");
			rd.include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}
		}
		}
		
	}
}


