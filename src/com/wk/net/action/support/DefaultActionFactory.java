package com.wk.net.action.support;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wk.net.AppContext;
import com.wk.net.action.Action;
import com.wk.net.action.ActionFactory;
/**
 * action 生成工厂
 * 参数：指令
 * @author wukai
 *
 */
public class DefaultActionFactory
  implements ActionFactory
{
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private Map<String, String> actionMap;
  private AppContext context;

  public DefaultActionFactory()
  {
    init();
  }
/**
 * 从action.xml中解析出业务action，存储到map表中
 */
  private void init() {
    this.actionMap = new HashMap<String, String>();
    this.context = AppContext.getInstance();
    InputStream input = null;
    try {
      ClassLoader loader = super.getClass().getClassLoader();
      URL url = loader.getResource(ResourceBundle.getBundle("config").getString("action"));
      input = url.openStream();
      SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
      parser.parse(input, new DefaultHandler() {
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
          if ((!qName.equals("action-map")) && (qName.equals("action"))) {
            try {
              String name = attributes.getValue("name");
              String beanName = attributes.getValue("bean");
              DefaultActionFactory.this.actionMap.put(name, beanName);
            } catch (Exception e) {
              DefaultActionFactory.this.logger.error("initialize action exception", e);
            }

          }

        }

      });
    }
    catch (Exception e)
    {
    	e.printStackTrace();
//      throw new InitializeException("Initialize ActionFactory Exception", e);
    } finally {
      try {
        input.close();
      } catch (IOException e) {
        this.logger.error("IOException while closing configuration input stream. Error was " + e.getMessage());
      }
    }
  }

  /**
   * 根据指令创建对应的action
   */
  public Action createAction(int commandId) throws Exception
  {
    String beanName = (String)this.actionMap.get(String.valueOf(commandId));
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("createAction:" + beanName);
    }
    return (Action)this.context.getApplicationContext().getBean(beanName);
  }
}