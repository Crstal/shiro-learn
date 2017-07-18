package com.github.crystal.chapter06.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.github.crystal.chapter06.JdbcTemplateUtils;
import com.github.crystal.chapter06.entity.Permission;

public class PermissionDaoImpl implements PermissionDao {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	
	public Permission createPermission(final Permission permission) {
		final String sql = "insert into sys_permissions(permission, description, available) values(?, ?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement stmt = con.prepareStatement(sql, new String[] {"id"});
				stmt.setString(1, permission.getPermission());
				stmt.setString(2, permission.getDescription());
				stmt.setBoolean(3, permission.getAvailable());
				return stmt;
			}
		}, keyHolder);
		permission.setId(keyHolder.getKey().longValue());
		return permission;
	}

	public void deletePermission(Long permissionId) {
		//首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, permissionId);

        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, permissionId);
	}
}
