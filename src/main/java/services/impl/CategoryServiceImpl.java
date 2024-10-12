package services.impl;

import dao.ICategoryDao;
import dao.impl.CategoryDaoImpl;
import entity.Category;
import services.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(int cateid) throws Exception {
        categoryDao.delete(cateid);
    }

    @Override
    public Category findById(int cateid) {
        return categoryDao.findById(cateid);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findByCategoryname(String catname) {
        return categoryDao.findByCategoryname(catname);
    }
}
