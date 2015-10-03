package com.csuf.servlet;

import com.csuf.base.BaseServlet;
import com.csuf.base.LoginManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = { "/Logout" })
public class LogoutServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginManager.logout(request);
		redirect(getHomePageUrl(request), response);
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginManager.logout(request);
		redirect(getHomePageUrl(request), response);
    }
}
