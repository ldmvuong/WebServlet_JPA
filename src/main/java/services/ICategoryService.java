package services;

import entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);
    void update(Category category);
    void delete(int cateid) throws Exception;
    Category findById(int cateid);
    List<Category> findAll();
    List<Category> findByCategoryname(String catname);
}
