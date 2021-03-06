package com.github.crystal.chapter06.entity;

import java.io.Serializable;

/**
 * 角色权限关系
 * 
 * @author cye
 *
 */
public class RolePermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6721911722314140167L;
	private Long roleId;
	private Long permissionId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RolePermission that = (RolePermission) o;

		if (permissionId != null ? !permissionId.equals(that.permissionId)
				: that.permissionId != null)
			return false;
		if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = roleId != null ? roleId.hashCode() : 0;
		result = 31 * result
				+ (permissionId != null ? permissionId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "RolePermssion{" + "roleId=" + roleId + ", permissionId="
				+ permissionId + '}';
	}
}
