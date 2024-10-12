package configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Test {
    public static void main(String[] args) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}
