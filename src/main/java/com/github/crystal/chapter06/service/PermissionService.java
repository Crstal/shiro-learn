package com.github.crystal.chapter06.service;

import com.github.crystal.chapter06.entity.Permission;

public interface PermissionService {
	public Permission createPermission(Permission permission);  
    public void deletePermission(Long permissionId); 
}
