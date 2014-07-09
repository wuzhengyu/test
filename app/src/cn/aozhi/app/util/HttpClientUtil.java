package cn.aozhi.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.CharsetUtils;
import org.apache.log4j.Logger;

/**
 * httpClient工具类
 * @author luxh
 *
 */
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	/**
	 * 文件上传(****该方法目前没有使用*****)
	 * @param filePath
	 * @param url
	 * @param params
	 * @return
	 */
	public static String fileUpload(File f, String url,Map<String, String> params) {
		CloseableHttpClient closeableHttpClient = null;
		String result = "";
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			closeableHttpClient = httpClientBuilder.build();
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
			
			multipartEntityBuilder.addBinaryBody("file", f);
			if (params != null && params.size() > 0  ) {
				for (Map.Entry<String, String> map : params.entrySet()) {
					multipartEntityBuilder.addTextBody(map.getKey(), map.getValue());
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httpPost.setEntity(reqEntity);
			HttpResponse response = closeableHttpClient.execute(httpPost);
			if(response!=null && response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					result = IOUtils.toString(responseEntity.getContent());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(closeableHttpClient!=null){
				try {
					closeableHttpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 上传录音文件到第三方服务器
	 * @param f
	 * @param url
	 * @param params
	 * @return
	 */
	public static String recordFileUpload(File f,String url,Map<String,ContentBody> params){
		  String result = "";
		  HttpClient httpclient = new DefaultHttpClient();
	      try {  
	            HttpPost httppost = new HttpPost(url);  
	          
	            FileBody file = new FileBody(f);  
	              
	            MultipartEntity reqEntity = new MultipartEntity();
	            
	            reqEntity.addPart("speechfile", file);
	             
	            for(Map.Entry<String,ContentBody> map:params.entrySet()){
	            	reqEntity.addPart(map.getKey(), map.getValue());
	            }
	            httppost.setEntity(reqEntity);
	          
	            HttpResponse response = httpclient.execute(httppost);
	            int statusCode=response.getStatusLine().getStatusCode();
	            logger.info("  ================>响应状态码:"+statusCode);
	            if(HttpStatus.SC_OK==statusCode){  
		            HttpEntity entity = response.getEntity();
		            if (entity != null) {  
		            	result = IOUtils.toString(entity.getContent());
		            }  
		        }
	              
	        }catch(IOException e){
	        	e.printStackTrace();
	        }finally{
	        	ClientConnectionManager connectionManager = httpclient.getConnectionManager();
	        	if(connectionManager!=null){
	        		connectionManager.shutdown();
	        	}
	        } 
	        return result;    
	}
	
	
	public static String post(String url,Map<String,ContentBody> params){
		  String result = "";
		  HttpClient httpclient = new DefaultHttpClient();  
	      try {  
	            HttpPost httppost = new HttpPost(url);
	            
	            MultipartEntity reqEntity = new MultipartEntity();  
	            
	            //设置参数
	            for(Map.Entry<String,ContentBody> map:params.entrySet()){
	            	reqEntity.addPart(map.getKey(), map.getValue());
	            }
	            httppost.setEntity(reqEntity);  
	          
	            HttpResponse response = httpclient.execute(httppost);
	            int statusCode=response.getStatusLine().getStatusCode();
				//logger.info("  ================>响应状态码: "+statusCode);
	            if(HttpStatus.SC_OK==statusCode){  
		            HttpEntity entity = response.getEntity();  
		            if (entity != null) {  
		            	result = IOUtils.toString(entity.getContent());
		            }  
		        }
	              
	        }catch(IOException e){
	        	e.printStackTrace();
	        }finally{
	        	ClientConnectionManager connectionManager = httpclient.getConnectionManager();
	        	if(connectionManager!=null){
	        		connectionManager.shutdown();
	        	}
	        } 
	        return result;    
	}
	
	/**  
	     * 保存指定URL的源文件到指定路径下  
	     *   
	     * @param srcUrl 要下载文件的绝对路径url  
	     * @param filePath 文件要保存的路径  
	     */  
	    public static synchronized String downloadFileByUrl(String srcUrl,String filePath) {   
	        String fileName = getFileName(srcUrl);
	        String newFileName = DateUtil.getMillisOfSecondStr();
	        String extendName = FileUtil.getExtension(fileName);
	        HttpClient httpclient = new DefaultHttpClient();
	        fileName = newFileName+extendName;
	        HttpGet httpget = new HttpGet(srcUrl);   
	        HttpResponse response;   
	        FileOutputStream out = null;   
	        try {   
	            
	            File f = new File(filePath);
	            if(!f.exists()){
	            	f.mkdirs();
	            }
	            
	            File wdFile = new File(f,fileName);   
	         
	            out = new FileOutputStream(wdFile);   
	            response = httpclient.execute(httpget);   
	            HttpEntity entity = response.getEntity();   
	            if (entity != null) {   
	                InputStream instream = entity.getContent();   
	                int l;   
	                byte[] tmp = new byte[2048];   
	                while ((l = instream.read(tmp)) != -1) {   
	                    out.write(tmp, 0, l);
	                }   
	            }   
	        } catch (ClientProtocolException e) {   
	            e.printStackTrace();   
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        }finally{   
	            if(out!=null){   
	                try {   
	                    out.close();   
	                } catch (IOException e) {   
	                    e.printStackTrace();   
	                }   
	            }   
	        }   

	        return fileName;
	    } 
	    
	    
	/**
	 * 获取文件名
	 * @param url
	 * @return
	 */
	public static String getFileName(String url){
    	String[] str = url.split("remixfile=");
    	return str[1];
	}
}
