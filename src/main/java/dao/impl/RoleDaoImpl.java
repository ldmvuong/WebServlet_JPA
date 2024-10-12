package dao.impl;

import configs.JPAConfig;
import dao.IRoleDao;
import entity.Role;
import jakarta.persistence.EntityManager;

public class RoleDaoImpl implements IRoleDao {

    @Override
    public Role findById(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        Role role = entityManager.find(Role.class, id);
        return role;
    }
}
