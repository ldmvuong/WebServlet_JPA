package dao.impl;

import configs.JPAConfig;
import dao.IVideoDao;
import entity.Category;
import entity.Video;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class VideoDaoImpl implements IVideoDao {

    @Override
    public void insert(Video video) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(video);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(video);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int videoId) throws Exception {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Video video = entityManager.find(Video.class, videoId);
            if (video != null) {
                entityManager.remove(video);
            } else {
                throw new Exception("Video not found");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Video findById(int videoId) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        return entityManager.find(Video.class, videoId);
    }

    @Override
    public List<Video> findAll() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        TypedQuery<Video> query = entityManager.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByTitle(String title) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
        TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
}

