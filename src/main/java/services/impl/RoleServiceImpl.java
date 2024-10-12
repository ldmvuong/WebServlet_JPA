package services.impl;

import dao.IRoleDao;
import dao.impl.RoleDaoImpl;
import entity.Role;
import services.IRoleService;

public class RoleServiceImpl implements IRoleService {
    private IRoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }
}
