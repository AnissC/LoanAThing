package com.lat.services;

import com.lat.beans.Advert;
import com.lat.beans.User;
import com.lat.dao.DAOFactory;
import com.lat.dao.GroupDAO;
import com.lat.dao.UserDao;
import com.lat.forms.UserForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService
{
    private static UserService USER_SERVICE = null;
    private HttpSession session;
    private UserDao userDao;
    private GroupDAO groupDAO;
    private UserForm userForm;

    private UserService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.groupDAO = DAOFactory.getInstance().getGroupDAO();
        this.userForm = new UserForm(this.userDao);
    }

    public static UserService getInstance()
    {
        if (USER_SERVICE == null) {
            USER_SERVICE = new UserService();
        }

        return USER_SERVICE;
    }

    public User getUserInSession()
    {
        return ((User) this.session.getAttribute("userSession"));
    }

    public void setUserInSession(User user)
    {
        this.session.setAttribute("userSession", user);
    }

    public UserForm getUserForm()
    {
        return this.userForm;
    }

    public User processUser(HttpServletRequest request)
    {
        String email = getFieldValue(request, "email");
        String password = getFieldValue(request, "password");
        String valid = getFieldValue(request, "valid");
        String nickname = getFieldValue(request, "nickname");

        return this.userForm.processUser(email, password, valid, nickname);
    }

    public void updateUser(HttpServletRequest request)
    {
        Integer id = Integer.parseInt(getFieldValue(request, "id"));
        User user = this.userDao.findOneById(id);

        String lastname = getFieldValue(request, "lastname");
        String firstname = getFieldValue(request, "firstname");
        String nickname = getFieldValue(request, "nickname");
        String email = getFieldValue(request, "email");
        String address = getFieldValue(request, "address");
        String city = getFieldValue(request, "city");
        Integer zipcode = Integer.parseInt(getFieldValue(request, "zipcode"));
        String birthday = getFieldValue(request, "birthday");

        this.userForm.updateUser(user, lastname, firstname, nickname, email, address, city, zipcode, birthday);

        setUserInSession(this.userDao.findOneById(id));
    }

    public User connectUser(HttpServletRequest request)
    {
        this.session = request.getSession();
        String email = getFieldValue(request, "email");
        String password = getFieldValue(request, "password");

        User user = this.userForm.connectUser(email, password);

        if (this.userForm.getErrors().isEmpty()) {
            session.setAttribute("userSession", user);
        } else {
            session.setAttribute("userSession", null);
        }

        this.userForm.resetError();

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

    public void ban(User user) { userDao.suspend(user); }

    public void reauthorize(User user) { userDao.reauthorize(user); }

    public List<User> getAllSuspendedUsers(){ return userDao.findAllSuspended(); }

}
