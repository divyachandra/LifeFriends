package com.csuf.servlet;

import com.csuf.base.BaseServlet;
import com.csuf.bean.User;
import com.csuf.base.LoginManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLogedIn(request)) {
            redirect(getHomePageUrl(request), response);
            return;
        }
        forwardToLogin(null, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMessage = validateLoginParam(email, password);

        if (errorMessage != null) {
            forwardToLogin(errorMessage, request, response);
        } else {
            User user = null;
            try {
                user = User.getDao().findByEmailAndPassword(email, password);
                if (user == null)
                    errorMessage = "Invalid Email or Password";
            } catch (SQLException e) {
                logger.error(e.getMessage());
                errorMessage = "Error logging in, please trying again later";
            }

            if (errorMessage != null) {
                forwardToLogin(errorMessage, request, response);
            } else {
                LoginManager.login(user, request);
                redirect(getHomePageUrl(request), response);
            }
        }
    }

    private String validateLoginParam(String username, String password) {
        if (username == null || username.equals(""))
            return "Email can't be null or empty";
        if (password == null || password.equals(""))
            return "Password can't be null or empty";
        return null;
    }

    private void forwardToLogin(String errorMessage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        forward("Login.jsp", request, response);
    }
}
