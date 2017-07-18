package com.github.crystal.chapter06.service;

import com.github.crystal.chapter06.dao.RoleDao;
import com.github.crystal.chapter06.dao.RoleDaoImpl;
import com.github.crystal.chapter06.entity.Role;

/**
 * 角色管理
 * @author cye
 *
 */
public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao = new RoleDaoImpl();

	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	/**
    * 添加角色-权限之间关系
    * @param roleId
    * @param permissionIds
    */
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	 /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}
}
