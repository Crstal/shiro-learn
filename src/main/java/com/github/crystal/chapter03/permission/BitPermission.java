package com.github.crystal.chapter03.permission;

import org.apache.shiro.authz.Permission;

import com.alibaba.druid.util.StringUtils;

public class BitPermission implements Permission {

	private String resourceIdentify;//资源  
    private int permissionBit;  //权限
    private String instanceId; //实例id
    
    public BitPermission(String permissionStr) {
    	String[] arr = permissionStr.split("\\+");
    	if (arr.length > 1) {
    		resourceIdentify = arr[1];
    	}
    	if(StringUtils.isEmpty(resourceIdentify)) {  
            resourceIdentify = "*";  
        }  
    	if (arr.length > 2) {
    		permissionBit = Integer.parseInt(arr[2]);
    	}
    	if(arr.length > 3) {  
            instanceId = arr[3];  
        }  
        if(StringUtils.isEmpty(instanceId)) {  
            instanceId = "*";  
        }  
	}
	    
	public boolean implies(Permission p) {
		if (!(p instanceof BitPermission))
			return false;
		BitPermission other = (BitPermission) p;  
		if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {  
            return false;  
        }  
		if(!(this.permissionBit ==0 || (this.permissionBit & other.permissionBit) != 0)) {  
            return false;  
        }  
		if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {  
            return false;  
        }  
		return true;
	}
}
