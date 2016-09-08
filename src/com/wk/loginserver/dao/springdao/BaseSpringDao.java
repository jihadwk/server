package com.wk.loginserver.dao.springdao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @creator:赵清有 2010-7-19下午06:26:59 dao层 数据库操作基础类
 */
public abstract class BaseSpringDao {
	@Autowired
	protected JdbcTemplate jdbcTemplateManager;

	protected Logger log = LoggerFactory.getLogger(BaseSpringDao.class);

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplateManager = jdbcTemplate;
	}

	/**
	 * @param seqName
	 * @return 获取每个表的id
	 */
	public long getId(String seqName) {
		String sql = "select NextVal('" + seqName + "')";
		return jdbcTemplateManager.queryForObject(sql,Long.class);
	}

	
}
