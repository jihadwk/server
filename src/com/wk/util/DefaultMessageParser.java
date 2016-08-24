package com.wk.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.Adler32;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 单例类，管理指令表
 * @author wukai
 *
 */
public class DefaultMessageParser
{
  private final Logger logger = LoggerFactory.getLogger(DefaultMessageParser.class);
  private static DefaultMessageParser instance;
  private Map<String, Integer> commandMap;
  private Set<Class<? extends Object>> messageTypes;
  protected PnpcAdler32 adler32;
  
  private DefaultMessageParser()
  {
    this.commandMap = new HashMap();
    this.messageTypes = new HashSet();
    this.adler32 = new PnpcAdler32();
  }

  public static DefaultMessageParser getInstance() throws Exception {
    if (instance == null) {
      instance = new DefaultMessageParser();
      instance.init();
    }
    return instance;
  }

  private void init() throws Exception {
    InputStream input = null;
    ClassLoader loader = super.getClass().getClassLoader();
    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
    try {
      URL url = loader.getResource("command.xml");
      input = url.openStream();
      parser.parse(input, new MyDefaultHandler());
    } catch (Exception e) {
      throw e;
    } finally {
      try {
//        if (input == null) {
//          parser.parse(input, new MyDefaultHandler());
//        }
        input.close();
      } catch (IOException e) {
        this.logger.error("IOException while closing configuration input stream. Error was " + e.getMessage());
      }
    }
  }

//  public long checkAdler32(byte[] b)
//    throws Exception
//  {
//    if ((b == null) || (b.length == 0)) {
//      throw new Exception("data null or size is zero!");
//    }
//    long value = 0L;
//    this.adler32.update(b);
//    value = this.adler32.getValue();
//    this.adler32.reset();
//    return value;
//  }

  public Set<Class<? extends Object>> getMessageTypes() {
    return this.messageTypes;
  }

  public Map<String, Integer> getCommandMap() {
    return this.commandMap;
  }

  public void setCommandMap(Map<String, Integer> commandMap) {
    this.commandMap = commandMap;
  }

  public Logger getLogger() {
    return this.logger;
  }
  //test
  public static void main(String[] args){
	  try {
		Map<String, Integer> map = DefaultMessageParser.getInstance().getCommandMap();
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}