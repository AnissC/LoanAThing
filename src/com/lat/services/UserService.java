package com.lat.services;

import com.lat.beans.User;
import com.lat.dao.DAOFactory;
import com.lat.dao.UserDao;
import com.lat.forms.LoginForm;
import com.lat.forms.RegisterForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserService
{
    private static UserService USER_SERVICE = null;
    private HttpSession session;
    private UserDao userDao;
    private RegisterForm registerForm;
    private LoginForm loginForm;

    private UserService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.registerForm = new RegisterForm(this.userDao);
        this.loginForm = new LoginForm(this.userDao);
    }

    public static UserService getInstance()
    {
        if (USER_SERVICE == null) {
            USER_SERVICE = new UserService();
        }

        return USER_SERVICE;
    }

    public RegisterForm getRegisterForm()
    {
        return this.registerForm;
    }

    public LoginForm getLoginForm()
    {
        return this.loginForm;
    }

    public User processUser(HttpServletRequest request)
    {
        String email = getFieldValue(request, "email");
        String password = getFieldValue(request, "password");
        String valid = getFieldValue(request, "valid");
        String lastname = getFieldValue(request, "lastname");

        return this.registerForm.processUser(email, password, valid, lastname);
    }

    public User connectUser(HttpServletRequest request)
    {
        this.session = request.getSession();
        String email = getFieldValue(request, "email");
        String password = getFieldValue(request, "password");

        User user = this.loginForm.connectUser(email, password);

        if (this.loginForm.getErrors().isEmpty()) {
            session.setAttribute("userSession", user);
        } else {
            session.setAttribute("userSession", null);
        }

        this.loginForm.resetError();

        return user;
    }

    public void logout()
    {
        this.session.invalidate();
    }

    public User getUserById(Integer id)
    {
        return userDao.findOneById(id);
    }

    private static String getFieldValue(HttpServletRequest request, String fieldName)
    {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value.trim();
        }
    }
}
