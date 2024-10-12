package dao.impl;

import configs.JPAConfig;
import dao.ICategoryDao;
import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transaction;

import java.util.List;


public class CategoryDaoImpl implements ICategoryDao {

    @Override
    public void insert(Category category) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(category);
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.merge(category);
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int cateid) throws Exception {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Category category = entityManager.find(Category.class, cateid);
            if(category != null){
                entityManager.remove(category);
            }
            else {
                throw new Exception("Không tìm thấy");
            }
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            throw e;
        }
        finally {
            entityManager.close();
        }
    }


    @Override
    public Category findById(int cateid) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        return entityManager.find(Category.class, cateid);
    }

    @Override
    public List<Category> findAll() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        TypedQuery<Category> query = entityManager.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findByCategoryname(String catename) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "select c from Category c where c.categoryname = :catname";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("catname", "%" + catename + "%");
        return query.getResultList();
    }
}
