package org.coody.reduce.service;

import org.coody.framework.cache.annotation.CacheWipe;
import org.coody.framework.cache.annotation.CacheWrite;
import org.coody.framework.core.annotation.AutoBuild;
import org.coody.framework.core.util.reflex.PropertUtil;
import org.coody.framework.jdbc.JdbcProcessor;
import org.coody.framework.jdbc.util.JdbcUtil;
import org.coody.reduce.common.constants.CacheConstant;
import org.coody.reduce.domain.UserInfo;

@AutoBuild
public class UserService {

	@AutoBuild
	JdbcProcessor jdbcProcessor;

	public Long insert(UserInfo user) {
		return jdbcProcessor.insert(user);
	}

	@CacheWipe(key = CacheConstant.USER_INFO, fields = "user.email")
	@CacheWipe(key = CacheConstant.USER_INFO, fields = "user.id")
	public Long modify(UserInfo user, String field) {
		String sql = String.format("update %s set %s=? where email=? limit 1", JdbcUtil.getTableName(user.getClass()),
				field);
		String value = PropertUtil.getFieldValue(user, field);
		return jdbcProcessor.update(sql, value, user.getEmail());
	}

	@CacheWrite(key = CacheConstant.USER_INFO, fields = "email", expire = 72000)
	public UserInfo fromEmail(String email) {
		return jdbcProcessor.findBeanFirst(UserInfo.class, "email", email);
	}

	@CacheWrite(key = CacheConstant.USER_INFO, fields = "id", expire = 72000)
	public UserInfo fromId(Integer id) {
		return jdbcProcessor.findBeanFirst(UserInfo.class, "id", id);
	}
}
