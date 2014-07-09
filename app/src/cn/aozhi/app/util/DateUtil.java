package cn.aozhi.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;


public class DateUtil {  
  /** 
   * 将指定字符串转换成日期 
   *  
   * @param date 
   *            String 日期字符串 
   * @param datePattern 
   *            String 日期格式 
   * @return Date 
   */  
  public static java.util.Date getFormatDate(String date, String datePattern) {  
      SimpleDateFormat sd = new SimpleDateFormat(datePattern);  
      return sd.parse(date, new java.text.ParsePosition(0));  
  }  
    

  /** 
   * 将指定日期对象转换成格式化字符串 
   *  
   * @param date 
   *            Date XML日期对象 
   * @param datePattern 
   *            String 日期格式 
   * @return String 
   */  
  public static String getFormattedString(Date date, String datePattern) {  
      SimpleDateFormat sd = new SimpleDateFormat(datePattern);  

      return sd.format(date);  
  }  

  /** 
   * 将指定XML日期对象转换成格式化字符串 
   *  
   * @param xmlDate 
   *            Date XML日期对象 
   * @param datePattern 
   *            String 日期格式 
   * @return String 
   */  
  public static String getFormattedString(XMLGregorianCalendar xmlDate,  
          String datePattern) {  
      SimpleDateFormat sd = new SimpleDateFormat(datePattern);  

      Calendar calendar = xmlDate.toGregorianCalendar();  

      return sd.format(calendar.getTime());  
  }  

  /** 
   * 将指定XML日期对象转换成日期对象 
   *  
   * @param xmlDate 
   *            Date XML日期对象 
   * @param datePattern 
   *            String 日期格式 
   * @return Date 
   */  
  public static Date xmlGregorianCalendar2Date(XMLGregorianCalendar xmlDate) {  
      return xmlDate.toGregorianCalendar().getTime();  
  }  

  public static String getThisYear() {  
      // 获得当前日期  
      Calendar cldCurrent = Calendar.getInstance();  
      // 获得年月日  
      String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));  
      return strYear;  
  }  

  // 获取当天时间  
  public static java.sql.Timestamp getNowTime(String dateformat) {  
      Date now = new Date();  
      SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式  
      String dateString = dateFormat.format(now);  
      SimpleDateFormat sd = new SimpleDateFormat(dateformat);  
      Date dateFormt = sd.parse(dateString, new java.text.ParsePosition(0));  
      java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());  

      return dateTime;  
  }  

  // 获取指定时间  
  public static java.sql.Timestamp getNowNewTime(String date,String dateformat){      
      //Date   now   =   new   Date();         
      SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//可以方便地修改日期格式     
      dateFormat.parse(date, new java.text.ParsePosition(0));  
        
    //  String  dateString= dateFormat.format(date);   
     Date dateFormt= dateFormat.parse(date, new java.text.ParsePosition(0));  
     java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());  
     
       
     return dateTime;  
  }  
  /** 
   * @param 含有yyyy-MM-dd'T'hh:mm:ss.SSS格式的时间转换. 
   * @return 
   */  
  public static String getTFormatString(String tdate) {  
      SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");  
      String str ="";  
      try {  
          java.util.Date date = format1.parse(tdate);  
          SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
           str = format2.format(date);  
      } catch (ParseException e) {  
          e.printStackTrace();  
      }  
      return str;  
  }  
    
  //获取当前时间前2个小时的时间。  
  public static String getBefore2HourDate(){  
      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");    
       Calendar c = Calendar.getInstance();      
       c.add(Calendar.HOUR_OF_DAY, -2); // 目前時間加3小時      
      return df.format(c.getTime());  
        
  }  
    
  /** 
   *  
   * @param time1   当前时间   
   * @param time2  比较时间  
   * @return  如果time1比time2大gap分钟，则返回true; 
   */  
  public static boolean compareDateTime(Date time1, Date time2, int gap) {  
      return time1.getTime() - time2.getTime() > gap * 60 * 1000;  
  }
  
  /**
   * 获取当前年月日的文件路径:2014/05/23
   * @return
   */
  public static String getDateFilePath(){
	  String separator = "/";
	  DateTime dateTime = new DateTime();
	  String year = String.valueOf(dateTime.getYear());
	  String month = String.valueOf(dateTime.getMonthOfYear());
	  String day = String.valueOf(dateTime.getDayOfMonth());
	  String dateFilePath = year+separator+month+separator+day;
	  return dateFilePath;
  }
  
  /**
   * 获取时间毫秒值的字符串
   * @return
   */
  public static String getMillisOfSecondStr(){
	  DateTime dateTime = new DateTime();
	  String year = String.valueOf(dateTime.getYear());
	  String month = String.valueOf(dateTime.getMonthOfYear());
	  String day = String.valueOf(dateTime.getDayOfMonth());
	  String hour = String.valueOf(dateTime.getHourOfDay());
	  String minutes = String.valueOf(dateTime.getMinuteOfHour());
	  String second = String.valueOf(dateTime.getSecondOfMinute());
	  String millis = String.valueOf(dateTime.getMillisOfSecond());
	  return year+month+day+hour+minutes+second+millis;
  }
  
  /**
   * 获取当前日期减一天
   * @return
   */
  public static String getNowPerDay() {
	String separator = "/";
	DateTime dateTime = new DateTime();
	dateTime = dateTime.minusDays(1);
	String year = String.valueOf(dateTime.getYear());
	String month = String.valueOf(dateTime.getMonthOfYear());
	String day = String.valueOf(dateTime.getDayOfMonth());
	String dateFilePath = year+separator+month+separator+day;
	return dateFilePath;
 }
  
  /**
   * 获取当前日期减一天(返回yyyy-mm-dd格式字符串)
   * @return
   */
  public static String getNowMinusDay() {
	DateTime dateTime = new DateTime();
	dateTime = dateTime.minusDays(1);
	return dateTime.toString("YYYY-MM-dd");
 }
  
  
  
  /**
   * 获取当前日期的字符串类型(返回yyyy-mm-dd格式字符串)
   * @return
   */
  public static String getCurrentDay() {
	DateTime dateTime = new DateTime();
	return dateTime.toString("YYYY-MM-dd");
 }
  
  /**
   * 将Date类型转换为yyyy-mm-dd格式字符串
   * @param date
   * @return
   */
  public static String date2String(Date date){
	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  return simpleDateFormat.format(date);
  }
}  
