package com.csuf.base;

import com.csuf.bean.Role;
import com.csuf.bean.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Divya on 10/2/2015.
 */
public abstract class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(BaseServlet.class);

    protected BaseServlet() {
    }

    protected final void redirect(String url, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(fixUrl(url));
    }

    protected final void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fixUrl(url));
        dispatcher.forward(request, response);
    }

    private String fixUrl(String url) {
        if (url != null && !url.startsWith("/"))
            url = "/" + url;
        return url;
    }

    protected String getHomePageUrl(HttpServletRequest request) {
        if (isLogedIn(request)) {
            if (Role.ADMIN == getCurrentUser(request).getRoleId()) {
                return "/AdminHome";
            } else {
                return "/DonorHome";
            }
        }
        return getLoginPageUrl();
    }

    protected String getLoginPageUrl() {
        return "/Login";
    }

    protected boolean isLogedIn(HttpServletRequest request) {
        return LoginManager.isLoggedIn(request);
    }

    protected User getCurrentUser(HttpServletRequest request) {
        return LoginManager.getCurrentUser(request);
    }
}
