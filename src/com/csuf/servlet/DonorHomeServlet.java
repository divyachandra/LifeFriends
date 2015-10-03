package com.csuf.servlet;

import com.csuf.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DonorHomeServlet", urlPatterns = { "/DonorHome" })
public class DonorHomeServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!isLogedIn(request)) {
			redirect(getLoginPageUrl(), response);
			return;
		}
		forward("DonorHome.jsp", request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
