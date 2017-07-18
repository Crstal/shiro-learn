package com.github.crystal.chapter06.dao;

import com.github.crystal.chapter06.entity.Permission;

/**
 * 
 * @author cye
 *
 */
public interface PermissionDao {

	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);
}
