

package sbi.kiosk.swayam.common.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import sbi.kiosk.swayam.common.dto.AlertDto;
import sun.net.www.protocol.https.Handler;

@PropertySource({ "classpath:application.properties" })
public class SMSSender
{
	static Logger logger = LoggerFactory.getLogger(SMSSender.class);
   // @Value("${sms_url}")
  //  private static String smsUrl;
    
    static {
		// this part is needed cause Lebocoin has invalid SSL certificate, that cannot
		// be normally processed by Java
		TrustManager[] trustAllCertificates = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null; // Not relevant.
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
				// Do nothing. Just allow them all.
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
				// Do nothing. Just allow them all.
			}
		} };

		HostnameVerifier trustAllHostnames = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true; // Just allow them all.
			}
		};

		try {
			System.setProperty("jsse.enableSNIExtension", "false");
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCertificates, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
		} catch (GeneralSecurityException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
    
   /* public static String sendSms1(final AlertDto postData, final String contentType, final String mobile, final String senderId) {
    	
    	
    	
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        final List<CommonUrl> commonUrl = null;
        String smsUrl = null;
        String smsUserName = null;
        String smsUserPassword = null;
        String smsCertificatePath=null;
           
        try {
        	System.out.println("In side SMS");
        	SMSSender.logger.info("In side SMS-----");
        	       ClassLoader loader = Thread.currentThread().getContextClassLoader();
  			       Properties properties = new Properties();
  	        	 
  	                 Throwable t = null;
  	                 InputStream resourceStream = loader.getResourceAsStream("application.properties");
  	                     properties.load(resourceStream);
  	                
  	                 
  	                  // smsUrl ="https://smsapipprod.sbi.co.in:9445/bmg/sms/itspdominfo";
  	                  smsCertificatePath = properties.getProperty("sms.certificatePath");
  	                  SMSSender.logger.info("smsCertificatePath:::::" + smsCertificatePath);
  	                  System.setProperty("javax.net.ssl.trustStore",smsCertificatePath);
  	         		  // comment out below line
  	         		 // System.setProperty("javax.net.ssl.trustStore","trust_store/keystore.jks");
  	         		 System.setProperty("javax.net.ssl.trustStorePassword", "123456");
  	               smsUrl = properties.getProperty("sms.url");
  	               SMSSender.logger.info("smsUrlc:::::" + smsUrl);
  	               smsUserName = properties.getProperty("sms.username");
  	               SMSSender.logger.info("smsUserName:::::" + smsUserName);
  	               smsUserPassword = properties.getProperty("sms.password");
  	              SMSSender.logger.info("smsUserPassword:::::" + smsUserPassword);
        	
            SMSSender.logger.info("mobileNo :::::" + postData.getMobileNo());
            SMSSender.logger.info("smsUrl from db:::::" + commonUrl);
            final String finalTextMessage = "Call Id " + postData.getCallLogId() + " has been logged for Swayam Kiosk Sr. No " + postData.getKioskSrNo() + " in Branch " + postData.getBranchCode() + " on " + postData.getDateTime() + " for Issue:Test call log.";
            SMSSender.logger.info("finalTextMessage:::::" + finalTextMessage);
            postData.setContentType("text");
            postData.setSenderId("SBIBNK");
            postData.setMobileNo(postData.getMobileNo());
            postData.setMessage(finalTextMessage);
            postData.setIntflag("0");
            postData.setCharging("0");
            SMSSender.logger.info("finalMessagePostDate:::::" + postData);
            String jsonString = "content_type=text&sender_id=SBIBNK&mobile=" + postData.getMobileNo() + "&message=" + postData.getMessage() + "&intflag=0&charging=0";
            SMSSender.logger.info("jsonString:::============::" + jsonString);
            String credentials = String.valueOf(smsUserName) + ":" + smsUserPassword;
            SMSSender.logger.info("credentials:::::" + credentials);
            URL realUrl = new URL(smsUrl);
            URLConnection conn = realUrl.openConnection();
           // HttpsURLConnection conn1 = (HttpsURLConnection) realUrl.openConnection();
          //  SMSSender.logger.info("====conn1===1==" + conn1);
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String encoding = Base64.getEncoder().encodeToString(credentials.getBytes());
            SMSSender.logger.info("====encoding=====" + encoding);
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            SMSSender.logger.info("====jsonString===1==" + jsonString.toString());
            out.print(jsonString.toString());
            SMSSender.logger.info("====jsonString=2====" );
            out.flush();
            SMSSender.logger.info("====jsonString==3===" );
            try{
            SMSSender.logger.info("====jsonString==4==="+conn.getInputStream());
            SMSSender.logger.info("====jsonString==5==="+ new BufferedReader(new InputStreamReader(conn.getInputStream())));
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            SMSSender.logger.info("====jsonString==6===" );
            }catch (Exception e) {
            	//e.printStackTrace();
			}
            
            String line;
            while ((line = in.readLine()) != null) {
            	SMSSender.logger.info("====jsonString===7=="+line );
                result = String.valueOf(result) + "/n" + line;
                SMSSender.logger.info("====result=====" + result);
            }
        } catch (Exception e) {
		//	e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			//	ex.printStackTrace();
			}
		}
        SMSSender.logger.info("=final===result=====" + result);
		return result;
	}
    
    
    
 public static String sendSms(AlertDto postData,  String contentType, String mobile, String senderId) {
    	
    	
    	
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        final List<CommonUrl> commonUrl = null;
        String smsUrl = null;
        String smsUserName = null;
        String smsUserPassword = null;
        String smsCertificatePath=null;
        Gson gson = new Gson();  
        String error_code=null;
		String error_desc =null;
		String umid=null;
        try {
        	System.out.println("In side SMS");
        	SMSSender.logger.info("In side SMS--1111---");
        	       ClassLoader loader = Thread.currentThread().getContextClassLoader();
  			       Properties properties = new Properties();
  	        	 
  	                 Throwable t = null;
  	                 InputStream resourceStream = loader.getResourceAsStream("application.properties");
  	                     properties.load(resourceStream);
  	                
  	                 
  	                  // smsUrl ="https://smsapipprod.sbi.co.in:9445/bmg/sms/itspdominfo";
  	                  smsCertificatePath = properties.getProperty("sms.certificatePath");
  	                  SMSSender.logger.info("smsCertificatePath:::::" + smsCertificatePath);
  	                  System.setProperty("javax.net.ssl.trustStore",smsCertificatePath);
  	         		  // comment out below line
  	         		 // System.setProperty("javax.net.ssl.trustStore","trust_store/keystore.jks");
  	         		 System.setProperty("javax.net.ssl.trustStorePassword", "123456");
  	               smsUrl = properties.getProperty("sms.url");
  	               SMSSender.logger.info("smsUrlc:::::" + smsUrl);
  	               smsUserName = properties.getProperty("sms.username");
  	               SMSSender.logger.info("smsUserName:::::" + smsUserName);
  	               smsUserPassword = properties.getProperty("sms.password");
  	              SMSSender.logger.info("smsUserPassword:::::" + smsUserPassword);
        	
  	            logger.info("mobileNo :::::" + postData.getMobileNo());
            logger.info("smsUrl from db:::::" + commonUrl);
            final String finalTextMessage = "Call Id " + postData.getCallLogId() + " has been logged for Swayam Kiosk Sr. No " + postData.getKioskSrNo() + " in Branch " + postData.getBranchCode() + " on " + postData.getDateTime() + " for Issue:Test call log.";
            logger.info("finalTextMessage:::::" + finalTextMessage);
            postData.setContentType("text");
            postData.setSenderId("SBIBNK");
            postData.setMobileNo(postData.getMobileNo());
            postData.setMessage(finalTextMessage);
            postData.setIntflag("0");
            postData.setCharging("0");
            logger.info("finalMessagePostDate:::::" + postData);
            String jsonString = "content_type=text&sender_id=SBIBNK&mobile=" + postData.getMobileNo() + "&message=" + postData.getMessage() + "&intflag=0&charging=0";
            logger.info("jsonString:::============::" + jsonString);
            String credentials = String.valueOf(smsUserName) + ":" + smsUserPassword;
            logger.info("credentials:::::" + credentials);
            //URL realUrl = new URL(smsUrl);
            //URLConnection conn = realUrl.openConnection();
           // HttpsURLConnection conn1 = (HttpsURLConnection) realUrl.openConnection();
          //  SMSSender.logger.info("====conn1===1==" + conn1);
            
            HttpsURLConnection conn =null;
			try{	
				logger.info("before Inside connection url not stablish=");
				conn=  (HttpsURLConnection) new URL(null,smsUrl).openConnection();
				logger.info("Aftert connection url not stablish=");
				//conn=  (HttpURLConnection) new URL(dbOms_url).openConnection();
			}catch(Exception e){
				
				logger.info("Inside connection url not stablish="+e.getMessage());
			}
			conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String encoding = Base64.getEncoder().encodeToString(credentials.getBytes());
            SMSSender.logger.info("====encoding=====" + encoding);
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setDoOutput(true);
          //  conn.setDoInput(true);
            
            logger.info("====conn.getOutputStream()=====" + conn.getOutputStream());
			byte[] postDataBytes = jsonString.toString().getBytes("UTF-8");
			logger.info("postDataBytes="+postDataBytes);
			conn.getOutputStream().write(postDataBytes);
			logger.info("====conn.getOutputStream()=======222222: "+conn.getOutputStream());
			logger.info("====conn.getOutputStream()=======333333: "+new InputStreamReader(conn.getInputStream(), "UTF-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			logger.info("====conn.getOutputStream()=======444: "+ new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")));
			if ((result = br.readLine()) != null) {
				logger.info("====output=====" + result);
				Map map = gson.fromJson(result, Map.class);
				logger.info("=map===output=====" + map);
				error_code= (String) map.get("error_code");
				error_desc = (String) map.get("error_desc");
				umid=(String) map.get("umid");
				logger.info("Result error_desc::" + error_desc);
				logger.info("Result error_code::" + error_code);
				
			}
            
            
            out = new PrintWriter(conn.getOutputStream());
            SMSSender.logger.info("====jsonString===1==" + jsonString.toString());
            out.print(jsonString.toString());
            SMSSender.logger.info("====jsonString=2====" );
            out.flush();
            SMSSender.logger.info("====jsonString==3===" );
            try{
            SMSSender.logger.info("====jsonString==4==="+conn.getInputStream());
            SMSSender.logger.info("====jsonString==5==="+ new BufferedReader(new InputStreamReader(conn.getInputStream())));
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            SMSSender.logger.info("====jsonString==6===" );
            }catch (Exception e) {
            	//e.printStackTrace();
			}
            
            String line;
            while ((line = in.readLine()) != null) {
            	SMSSender.logger.info("====jsonString===7=="+line );
                result =line;
                SMSSender.logger.info("====result=====" + result);
            }
        } catch (Exception e) {
		//	e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			//	ex.printStackTrace();
			}
		}
        SMSSender.logger.info("=final===result=====" + error_code);
		return error_code;
	}
    
 */
 public static String sendSmsCall(AlertDto postData,  String contentType, String mobile, String senderId) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		String smsUrl=null;
		String smsUserName=null;
		String smsUserPassword=null;
		  String smsCertificatePath=null;
		
		try {
		//	logger.info("smsApi Start--------url--"+postData.getMobileNo());
		      ClassLoader loader = Thread.currentThread().getContextClassLoader();
			  Properties properties = new Properties();
	         try {
	        	 
	             Throwable t = null;
	                InputStream resourceStream = loader.getResourceAsStream("application.properties");
	                 try {
	                	 logger.info("smsApi Start--------resourceStream--");
	                   properties.load(resourceStream);
	                   logger.info("smsApi End--------resourceStream--");
	                 }
	                 finally {
	                     if (resourceStream != null) {
	                         resourceStream.close();
	                     }
	                 }
	            
	         }
	         catch (IOException e) {
	         //    e.printStackTrace();
	       //      logger.info("e.printStackTrace()--"+e.getMessage());
	         }
	         smsCertificatePath = properties.getProperty("sms.certificatePath");
	    //    logger.info("smsCertificatePath: "+smsCertificatePath);  
			  System.setProperty("javax.net.ssl.trustStore",smsCertificatePath);
   		  // comment out below line
   		  System.setProperty("javax.net.ssl.trustStore","trust_store/keystore.jks");
   		  System.setProperty("javax.net.ssl.trustStorePassword", "123456");
	          //address = inp.next();   
   		  
   		   smsUrl = properties.getProperty("sms.url");
           SMSSender.logger.info("smsUrlc:::::" + smsUrl);
           smsUserName = properties.getProperty("sms.username");
           SMSSender.logger.info("smsUserName:::::" + smsUserName);
           smsUserPassword = properties.getProperty("sms.password");
          SMSSender.logger.info("smsUserPassword:::::" + smsUserPassword);
	         logger.info("IP smsUrl: " + smsUrl);  
	         logger.info("username: " + smsUserName); 
	         logger.info("password: " + smsUserPassword); 
			
			//String postData = "content_type=text&sender_id=SBIBNK&mobile=9022795630&message=Call ID TEST001  has been logged for Swayam Kiosk Sr. No SRNOTEAT01 in Branch 04430 on 03.03.2020 at 16:00 for Issue:Test call log.&intflag=0&charging=0";
			
			
			  String finalTextMessage = "Call Id " + postData.getCallLogId() + " has been logged for SBI Swayam Kiosk Sr. No " + postData.getKioskSrNo() + " in Branch " + postData.getBranchCode() + " on " + postData.getDateTime() + " for Issue:Test call log.";
	            logger.info("finalTextMessage:::::" + finalTextMessage);
	            postData.setContentType("text");
	            postData.setSenderId("SBIBNK");
	            postData.setMobileNo(postData.getMobileNo());
	            postData.setMessage(finalTextMessage);
	            postData.setIntflag("0");
	            postData.setCharging("0");
	            logger.info("finalMessagePostDate:::::" + postData);
	            String jsonString = "content_type=text&sender_id=SBIBNK&mobile=" + postData.getMobileNo() + "&message=" + postData.getMessage() + "&intflag=0&charging=0";
	            logger.info("jsonString:::============::" + jsonString);
			
			
			logger.info("========1======333344444========"+jsonString);
			String credentials = smsUserName+":"+smsUserPassword;
			logger.info("====credentials====1======3333555========" + credentials);
			HttpsURLConnection conn = null ;
			try{
				URL realUrl = new URL(null, smsUrl, new Handler());
			//URL realUrl = new URL(url);
			// build connection
			 conn = (HttpsURLConnection) realUrl.openConnection();
			// set request properties
			} catch (Exception e) {
			//	e.printStackTrace();
				logger.info("===Exception=====" + e.getMessage());
			}
		conn.setRequestMethod("POST");
         conn.setRequestProperty("accept", "*/*");
         conn.setRequestProperty("connection", "Keep-Alive");
         conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			//conn.setRequestProperty("accept", "*/*");
			//conn.setRequestProperty("connection", "Keep-Alive");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			String encoding = Base64.getEncoder().encodeToString(credentials.getBytes());
			logger.info("encoding::::::::::::"+encoding);
			conn.setRequestProperty("Authorization","Basic "+encoding);// c3Azc21kb21pbmZvOnNwM3NtNjBAbjUw String.format("Basic " + encoding));
			// enable output and input
			conn.setDoOutput(true);
			conn.setDoInput(true);
			try{
				logger.info("====conn.getOutputStream()=====" + conn.getOutputStream());
			out = new PrintWriter(conn.getOutputStream());
			} catch (Exception e) {
			//	e.printStackTrace();
				logger.info("===Exception=====" + e.getMessage());
			}
			// send POST DATA
			try{
			logger.info("====postData=222====" + jsonString);
			out.print(jsonString);
			out.flush();
			logger.info("====new BufferedReader(new InputStreamReader(conn.getInputStream())=====" + new BufferedReader(new InputStreamReader(conn.getInputStream())));
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} catch (Exception e) {
			//	e.printStackTrace();
				logger.info("===Exception1111=====" + e.getMessage());
			}
			
			String line;
			while ((line = in.readLine()) != null) {
				result= line;
				logger.info("====result=====" + result);
			}
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.info("===Exception22=====" + e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			//	ex.printStackTrace();
			}
		}
		logger.info("===final=result=====" + result);
		return result;
	
	}
 
 
   
}

