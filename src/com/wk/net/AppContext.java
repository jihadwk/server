package com.wk.net;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/**
 * 单例类，管理applicationContext
 * 加载spring配置
 * @author wukai
 *
 */
public class AppContext
{
	//加载spring配置，搜索根目录
  private FileSystemXmlApplicationContext context;
  private AtomicBoolean started = new AtomicBoolean(false);
  private Logger logger = LoggerFactory.getLogger(getClass());
  private static AppContext instance;
//同步，同一时间只有一个线程可以执行，执行完之前不可中断
  public static synchronized AppContext getInstance()
  {
    if (instance == null) {
      instance = new AppContext();
      instance.init();
    }
    return instance;
  }

  public ApplicationContext getApplicationContext() {
    return this.context;
  }
     public Object getBean(String beanName) {
	  if (beanName.equals("minaServer")) {
	         //new Report();
	     }
	       return this.context.getBean(beanName);
	   }
  public void init() {
    try {
      if (this.started.compareAndSet(false, true)) {
        String contextFilePath = "serverCfg";
        try {
          contextFilePath = ResourceBundle.getBundle("config").getString("spring");
        } catch (Throwable e) {
          e.printStackTrace();
        }

        if (this.logger.isDebugEnabled()) {
          this.logger.debug(contextFilePath + "/spring-application.xml");
        }
        this.context = new FileSystemXmlApplicationContext(new StringBuilder(String.valueOf(contextFilePath)).append("/spring-application.xml").toString());
//          this.context = new FileSystemXmlApplicationContext("File:" + System.getProperty("user.dir") + new StringBuilder(String.valueOf(contextFilePath)).append("/*.xml").toString());
      }
    } catch (Exception e) {
    	e.printStackTrace();
//      throw new InitializeException(e);
    }
  }

  public void destroy() {
    if (this.started.compareAndSet(true, false)) {
      this.context.close();
      this.context.destroy();
    }
  }
}