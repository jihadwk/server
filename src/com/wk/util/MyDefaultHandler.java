package com.wk.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//SAX解析XML文件，command.xml 配置 指令
public class MyDefaultHandler extends DefaultHandler
{
  public void startElement(String uri, String localName, String qName, Attributes attributes)
    throws SAXException
  {
    if ((!qName.equals("message")) && (qName.equals("entry")))
      try {
        int id = Integer.valueOf(attributes.getValue("id")).intValue();
        String messageName = attributes.getValue("messageName");
        //解析的指令放入指令表commandMap中，方便查找，解码时根据不同的指令分发不同的业务处理
        DefaultMessageParser.getInstance().getCommandMap().put(messageName, Integer.valueOf(id));
      } catch (Exception e) {
        try {//打印抛错
          DefaultMessageParser.getInstance().getLogger().error("initialize command parser exception", e);
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
  }
}