package com.csuf.base;

import com.csuf.bean.User;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Divya on 10/2/2015.
 */
public class LoginManager {
    private static Logger logger = Logger.getLogger(LoginManager.class);

    private LoginManager() {
        // no-op
    }

    public static void login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("USER", user);
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        User user = getCurrentUser(request);
        return user != null;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            return (User) session.getAttribute("USER");
        }
        return null;
    }

    public static void logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    logger.info("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if(session != null) {
            User user = (User) session.getAttribute("USER");
            logger.info("User=" + user.getUserId());
            session.invalidate();
        }
    }
}
