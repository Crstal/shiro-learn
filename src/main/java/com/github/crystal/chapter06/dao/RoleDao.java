package com.github.crystal.chapter06.dao;

import com.github.crystal.chapter06.entity.Role;

/**
 * 
 * @author cye
 *
 */
public interface RoleDao {

	public Role createRole(Role role);

	public void deleteRole(Long roleId);

	public void correlationPermissions(Long roleId, Long... permissionIds);

	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
