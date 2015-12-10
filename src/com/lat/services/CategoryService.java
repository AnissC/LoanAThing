package com.lat.services;

import com.lat.beans.Category;
import com.lat.dao.CategoryDao;
import com.lat.dao.DAOFactory;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CategoryService
{

        private static CategoryService CATEGORY_SERVICE = null;
        private HttpSession session;
        private CategoryDao categoryDao;

        private CategoryService()
        {
            this.categoryDao = DAOFactory.getInstance().getCategoryDao();
        }

        public static CategoryService getInstance()
        {
            if (CATEGORY_SERVICE == null) {
                CATEGORY_SERVICE = new CategoryService();
            }

            return CATEGORY_SERVICE;
        }

        public List<Category> getAllCategory()
        {
            return this.categoryDao.find();
        }
}
