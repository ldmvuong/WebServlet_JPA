package dao.impl;

import configs.JPAConfig;
import dao.IUserDao;
import entity.User;
import jakarta.persistence.*;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "select u from User u where u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public void insert(User user) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "select u from User u where u.email = :email";

        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();

            return user != null;

        } catch (NoResultException e) {
            return false;

        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean checkExistUsername(String username) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "select u from User u where u.username = :username";

        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("username", username);

            User user = query.getSingleResult();
            return user != null;

        } catch (NoResultException e) {
            return false;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean checkExistPhone(String phone) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "select u from User u where u.phone = :phone";

        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("phone", phone);
            User user = query.getSingleResult();

            return user != null;

        } catch (NoResultException e) {
            return false;

        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean checkUserByUsernameAndEmail(String username, String email) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "select u from User u where u.email = :email and u.username = :username";

        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("email", email);
            query.setParameter("username", username);

            // Fetch the user if exists
            User user = query.getSingleResult();

            return user != null;

        } catch (NoResultException e) {
            return false;

        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean updatePassword(String username, String password) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String jpql = "update User set password = :password where username = :username";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("password", password);
            query.setParameter("username", username);

            int result = query.executeUpdate();
            transaction.commit();

            return result > 0;

        } catch (Exception e) {
            System.err.println("Error updating password for username: " + username);
            e.printStackTrace();

            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;

        } finally {
            entityManager.close();
        }
    }

}
