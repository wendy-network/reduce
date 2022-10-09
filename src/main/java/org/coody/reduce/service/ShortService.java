package org.coody.reduce.service;

import java.text.MessageFormat;

import org.coody.framework.cache.annotation.CacheWipe;
import org.coody.framework.cache.annotation.CacheWrite;
import org.coody.framework.core.annotation.AutoBuild;
import org.coody.framework.core.util.CommonUtil;
import org.coody.framework.jdbc.JdbcProcessor;
import org.coody.framework.jdbc.entity.Pager;
import org.coody.framework.jdbc.util.JdbcUtil;
import org.coody.reduce.common.constants.CacheConstant;
import org.coody.reduce.domain.ShortInfo;

@AutoBuild
public class ShortService {

	@AutoBuild
	JdbcProcessor jdbcProcessor;

	@CacheWrite(key = CacheConstant.SHORT_INFO, fields = "id", expire = 60)
	public ShortInfo fromId(Long id) {
		return jdbcProcessor.findBeanFirst(ShortInfo.class, "id", id);
	}

	@CacheWipe(key = CacheConstant.SHORT_INFO, fields = "info.id")
	public Long save(ShortInfo info) {
		if (CommonUtil.isNullOrEmpty(info.getId())) {
			return jdbcProcessor.insert(info);
		}
		return jdbcProcessor.updateByPriKey(info, "id");
	}

	public Long insert(ShortInfo info) {
		return jdbcProcessor.insert(info);
	}

	public Pager findPager(Pager pager, ShortInfo shorter) {
		return jdbcProcessor.findPager(shorter, pager, "id", true);
	}

	public Long addFrequency(Long id) {
		String sql = String.format("update %s set frequency = frequency+1 where id=? limit 1",
				JdbcUtil.getTableName(ShortInfo.class));
		return jdbcProcessor.update(sql, id);
	}

	@CacheWipe(key = CacheConstant.SHORT_INFO, fields = "id")
	public Long del(Long id) {
		String sql = MessageFormat.format("delete from {0} where id=? limit 1", JdbcUtil.getTableName(ShortInfo.class));
		return jdbcProcessor.update(sql, id);
	}
}
