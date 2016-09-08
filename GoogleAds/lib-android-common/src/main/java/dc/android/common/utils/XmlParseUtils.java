package dc.android.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.XmlResourceParser;
import dc.common.Global;
/**
 * 解析xml文件
 * @author panglijie
 *
 */
public class XmlParseUtils {
 
	public static void parseXml(Context ctx,int xmlId,String xmlHead,Map<String, String> map) throws XmlPullParserException, IOException {
		XmlResourceParser parser = ctx.getResources().getXml(xmlId);
		  //如果没有到文件尾继续执行   
        while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {    
            //如果是开始标签   
                 if (parser.getEventType() == XmlResourceParser.START_TAG ){
                	 String nodeName = parser.getName();
                	 if(!xmlHead.equals(nodeName))
                	 map.put(nodeName, parser.nextText());
                 }
                 //下一个标签   
                 parser.next();
            }
	}
	
	/**
	 *  pull
	 * @param inputstream 输入流
	 * @param fileHead 文件头
	 * @param dataHead 数据头
	 * @throws XmlPullParserException 
	 * @throws IOException 
	 */
	public static void parseXml(InputStream inputstream,String fileHead,String dataHead,Map<String, String> map) throws XmlPullParserException, IOException {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = xmlPullParserFactory.newPullParser();
			// 设置输入流已经编码方式
			parser.setInput(inputstream, Global.ENCODE_UTF_8);
			// 获取当前的事件类型
			int eventType = parser.getEventType();
			// Beauty beauty = null;
			while (XmlPullParser.END_DOCUMENT != eventType) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					String nodeName  = parser.getName();
					if (!(nodeName.equals(fileHead))) {
                         if(nodeName.equals(dataHead)){
                        		int count = parser.getAttributeCount();
      						  
        						for (int i = 0; i < count; i++) {
        							String key = parser.getAttributeName(i);
        							String value = parser.getAttributeValue(i);
//        							Logger.w("解析键值对："+key+"-----"+value);
        							map.put(key, value);
        						}
                         }else {
                        	 String value = parser.nextText();
//                        	 Logger.w("子参数："+parser.getName()+"-------------"+value);
                        	 map.put(parser.getName(), value);
                         }
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				default:
					break;
				}
				// 手动的触发下一个事件
				eventType = parser.next();
			}
	}


}
