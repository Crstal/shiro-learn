package com.github.crystal.chapter06.service;

import com.github.crystal.chapter06.dao.PermissionDao;
import com.github.crystal.chapter06.dao.PermissionDaoImpl;
import com.github.crystal.chapter06.entity.Permission;

/**
 * 权限管理
 * @author cye
 *
 */
public class PermisssionServiceImpl implements PermissionService {

	private PermissionDao permissionDao = new PermissionDaoImpl();
	
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
