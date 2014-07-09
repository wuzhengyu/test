package cn.aozhi.app.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {
	private static Properties props;
	static{
		 try {
			props = PropertiesLoaderUtils.loadAllProperties("app.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据key读取properties配置文件
	 * @param name
	 * @return
	 */
	public static String getProperty(String name) {
	   return props.getProperty(name);//根据name得到对应的value
	}
	
}
