package com.csuf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.csuf.util.User;

@WebServlet(name = "DonorHomeServlet", urlPatterns = { "/DonorHomeServlet" })
public class DonorHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(DonorHomeServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("User");
		//String blood=user.getBloodtype();
		
		String errorMsg = null;
		if(search == null || search.equals("")){
			errorMsg ="Enter a search term";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/DonorHome.jsp");
			//request.setAttribute("Message", "Fields Can't be Null or Empty");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ArrayList<String> al = null;
        
			ArrayList<ArrayList<String>> blood_list = new ArrayList<ArrayList<String>>();
			ps = con.prepareStatement("select * from users where bloodtype= ? ");
			ps.setString(1, search);
			rs = ps.executeQuery();
			
			while (rs.next()) {
                al = new ArrayList<String>();
 
//                out.println(rs.getString(1));
//                out.println(rs.getString(2));
//                out.println(rs.getString(3));
//                out.println(rs.getString(4));
                al.add(rs.getString(1));
                al.add(rs.getString(2));
                al.add(rs.getString(3));
                al.add(rs.getString(4));
                al.add(rs.getString(5));
                al.add(rs.getString(6));
                al.add(rs.getString(7));
                al.add(rs.getString(8));
 
                System.out.println("al :: " + al);
                blood_list.add(al);
            }
			request.setAttribute("BloodList", blood_list);
            RequestDispatcher view = request.getRequestDispatcher("/DonorHome.jsp");
            view.forward(request, response);
            con.close();
            System.out.println("Disconnected!");
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
