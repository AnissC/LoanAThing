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
    private static UserService userService = null;
    private HttpSession session;
    private UserDao userDao;
    private RegisterForm registerForm;
    private LoginForm loginForm;

    private UserService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.registerForm = new RegisterForm(this.userDao);
        this.loginForm = new LoginForm();
    }

    public static UserService getInstance()
    {
        if (userService == null)
        {
            userService = new UserService();
        }
        return userService;
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
        return this.registerForm.processUser(request);
    }

    public User connectUser(HttpServletRequest request)
    {
        this.session = request.getSession();
        User user = this.loginForm.connectUser(request);

        if (this.loginForm.getErrors().isEmpty()) {
            session.setAttribute("userSession", user);
        } else {
            session.setAttribute("userSession", null);
        }

        return user;
    }

    public void logout()
    {
        this.session.invalidate();
    }
}
