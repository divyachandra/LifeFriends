package com.csuf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.csuf.util.User;
//import com.mysql.jdbc.Blob;

//Added comment to test - 9/30/2015

@WebServlet(name = "UserLogin", urlPatterns = { "/UserLogin" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(UserLogin.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMsg = null;
		if(username == null || username.equals("")){
			errorMsg ="Fields can't be null or empty";
		}
		if(password == null || password.equals("")){
			errorMsg = "Fields can't be null or empty";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserLogin.html");
			//request.setAttribute("Message", "Fields Can't be Null or Empty");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from user where username=? and password=? limit 1");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs != null & rs.next()){
				User user = new User(rs.getString("username"),rs.getString("password"), rs.getString("uid"), rs.getString("role"));
				logger.info("User found with details="+username);
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				if(rs.getString("role").equals("Admin"))
				{
					response.sendRedirect("AdminHome.jsp");
				}
				else if(rs.getString("role").equals("Donor"))
				{
					response.sendRedirect("DonorHome.jsp");
				}
				else
				{
					response.sendRedirect("HospitalHome.jsp");
				}
				
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserLogin.html");
				PrintWriter out= response.getWriter();
				//request.setAttribute("Message", "No user found with given email id, please register first.");
				//response.sendRedirect("Patientlogin.jsp"); 
				out.println("<font color=red>No user found with given email id, please register first.</font>");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");;
			}
			
		}
		}
	}

}
