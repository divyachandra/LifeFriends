package com.csuf.servlet;

import com.csuf.base.BaseServlet;
import com.csuf.bean.Role;
import com.csuf.bean.User;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"})
public class RegisterServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLogedIn(request)) {
            redirect(getHomePageUrl(request), response);
            return;
        }
        forward("Register.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User newUser = new User();
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setFirstName(request.getParameter("firstname"));
        newUser.setMiddleName(request.getParameter("middlename"));
        newUser.setLastName(request.getParameter("lastname"));
        newUser.setDOB(getDOB(request.getParameter("dob")));
        newUser.setPhone(request.getParameter("phone"));
        newUser.setAddress(request.getParameter("address"));
        newUser.setCity(request.getParameter("city"));
        newUser.setState(request.getParameter("state"));
        newUser.setZip(request.getParameter("zip"));
        newUser.setCountry(request.getParameter("country"));
        newUser.setBloodGroup(request.getParameter("bloodtype"));
        newUser.setGender(request.getParameter("gender"));
        newUser.setDonor(isDonor(request.getParameter("donor")));

        String email1 = request.getParameter("email1");
        String errorMessage = validateRegisterParams(newUser, email1);

        if (errorMessage != null) {
            request.setAttribute("message", errorMessage);
        } else {
            try {
                createUser(newUser);
                request.setAttribute("message", "User " + newUser.getEmail() + " has been registered successfully.");
            } catch (SQLException e) {
                logger.error("Error creating user " + newUser.getEmail() + ". " + e.getMessage());
                request.setAttribute("message", "There was and error creating user. Please try again later ");
            }
        }
        forward("Register.jsp", request, response);
    }

    private Date getDOB(String dateString) {
        DateFormat format = new SimpleDateFormat("MM/DD/YYYY");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            logger.error("Error converting dateString " + dateString + " to Date");
        }
        return date;
    }

    private boolean isDonor(String donorString) {
        return Boolean.parseBoolean(donorString);
    }

    private void createUser(User user) throws SQLException {
        user.setRoleId(Role.USER);
        user.setActive(true);
        User.getDao().insert(user);
    }

    private String validateRegisterParams(User user, String email) {
        String errorMessage = null;

        if (StringUtils.isNullOrEmpty(user.getFirstName())) {
            errorMessage = "First Name can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getLastName())) {
            errorMessage = "Last Name can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getEmail())) {
            errorMessage = "Email can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(email)) {
            errorMessage = "Email can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getPassword())) {
            errorMessage = "Password can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getGender())) {
            errorMessage = "Select your Gender";
        }
        if (StringUtils.isNullOrEmpty(user.getBloodGroup())) {
            errorMessage = "Blood type cannot be empty";
        }
        if (StringUtils.isNullOrEmpty(user.getPhone())) {
            errorMessage = "Phone number can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getAddress())) {
            errorMessage = "Address can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getCity())) {
            errorMessage = "City can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getState())) {
            errorMessage = "State can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getZip())) {
            errorMessage = "Zip can't be null or empty.";
        }
        if (StringUtils.isNullOrEmpty(user.getCountry())) {
            errorMessage = "Country can't be null or empty.";
        }
        if (user.getDOB() == null) {
            errorMessage = "Date of Birth(DOB) can't be null or empty.";
        }

        if (!email.equals(user.getEmail())) {
            errorMessage = "Email and Confirmation Email does not match";
        }
        return errorMessage;
    }
}


