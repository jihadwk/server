package com.wk.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.wk.loginserver.baseInfo.Dao.DBBaseInforDAO;
import com.wk.net.AppContext;

public class AnnotationTest {
	/**
	 * 通过context:component-scan 成功  @Autowired自动装配成功
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBBaseInforDAO dbBaseInforDAOImpl=(DBBaseInforDAO)AppContext.getInstance().getBean("DBBaseInforDAOImpl");
//		List<BwGameChannleVO> list = dbBaseInforDAOImpl.getGameServerChannleList();
//		System.out.println(list.get(0).getChannlename());
//		System.out.println(dbBaseInforDAOImpl);
		//使用的数据库是lootesmanager
		List<BwBuildingVO> list = dbBaseInforDAOImpl.queryBwBuildingVO();
		System.out.println(list.get(0).getBuildingName());
		//测试成功
		
	}

}
