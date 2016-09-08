package com.wk.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.wk.net.AppContext;

public class JdbcTemplateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppContext.getInstance().getBean("jdbcTemplate");
		String sql = "select * from bw_user_character";
		List list = jdbcTemplate.queryForList(sql);
		//jdbcTemplate配置成功
//		System.out.println(jdbcTemplate);
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Map map = (Map)iterator.next();
			System.out.println(map.get("id")+":"+map.get("mail_address"));
		}	
	}
}
