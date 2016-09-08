package com.wk.loginserver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wk.loginserver.entity.Service;
import com.wk.loginserver.entity.Test;
import com.wk.net.AppContext;
import com.wk.resourceManager.ResGlobal;
/**
 * 入口
 * @author wukai
 *
 */
public class LoginServer {
	
	public static void main(String[] args){
//		ApplicationContext context = new FileSystemXmlApplicationContext("resources/config/serverCfg/spring-application.xml");
////		Test test = (Test) context.getBean("test");
//		Service service = (Service) context.getBean("service");
//		service.print();
		
		AppContext appContext = AppContext.getInstance();
		MinaServer mina = (MinaServer) appContext.getBean("minaServer");
		//启动服务 监听端口
		mina.start();
//		配置文件加载到内存中，例如excel／csv／XML表等
		ResGlobal.getInstance();
	}
}
