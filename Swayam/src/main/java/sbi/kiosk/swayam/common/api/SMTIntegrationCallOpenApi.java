package sbi.kiosk.swayam.common.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;



public class SMTIntegrationCallOpenApi {
	
	static Logger logger = LoggerFactory.getLogger(SMTIntegrationCallOpenApi.class);
	
	static SecureRandom rnd = new SecureRandom();
	static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
	
	
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
	
	
	/*public static void main(String[] args) {
		ManualCallLogApiRequest dataRequest=new ManualCallLogApiRequest();
	makeWebServiceCall(dataRequest);
	}*/
	
	public static String  makeWebServiceCall(ManualCallLogApiRequest dataRequest) {

		ResponseEntity<String> response = null;
		String output="";
		try {
			
			 ClassLoader loader = Thread.currentThread().getContextClassLoader();
		     Properties properties = new Properties();
             Throwable t = null;
             InputStream resourceStream = loader.getResourceAsStream("application.properties");
             properties.load(resourceStream);
          // smsUrl ="https://smsapipprod.sbi.co.in:9445/bmg/sms/itspdominfo";
             String pathCertificate = properties.getProperty("sms.certificatePath");
			logger.info("pathCertificate:::::" + pathCertificate);
			System.setProperty("javax.net.ssl.trustStore", pathCertificate);
			System.setProperty("javax.net.ssl.trustStorePassword", "123456");
			String apiUrl = properties.getProperty("cms.url");
			String crmUserName=properties.getProperty("crmapi.username");
			String crmPassword=properties.getProperty("crmapi.password");
			
			logger.info("makeWebServiceCall:::::crmUserName:::::" + crmUserName);
			logger.info("makeWebServiceCall:::::crmPassword:::::" + crmPassword);
			
			String credentials = crmUserName+":"+crmPassword;
			logger.info("credentials=====CRM======makeWebServiceCall:::::::" + credentials);
			
	         		
			DateFormat sdf1 = new SimpleDateFormat("d-MMM-yyyy HH:mm:ss");
			String dateTime1 = sdf1.format(new Date());
			ManualCallLogApiRequest data1=new ManualCallLogApiRequest();
		    data1.setRequestId(dataRequest.getRequestId());
			data1.setReqType(dataRequest.getReqType());
			data1.setReqTicketNumber(dataRequest.getReqTicketNumber());
			data1.setReqDatetime(dateTime1);
			data1.setBrCode(dataRequest.getBrCode());
			data1.setSrc(dataRequest.getSrc());
			data1.setKioskProvider(dataRequest.getKioskProvider());
			data1.setKioskId(dataRequest.getKioskId());
			data1.setKioskSrno(dataRequest.getKioskSrno());
			data1.setIssueCategory(dataRequest.getIssueCategory());
			data1.setIssueSubcategory(dataRequest.getIssueSubcategory());
			data1.setIssueDescription(dataRequest.getIssueDescription());
			data1.setContactName(dataRequest.getContactName());
			data1.setContactNumber(dataRequest.getContactNumber());
			data1.setContactEmailId(dataRequest.getContactEmailId());
			data1.setStatus(dataRequest.getStatus());
			Gson gson=new Gson();
			String gsonData=gson.toJson(data1);
			logger.info("gsonData::="+gsonData);
			String dyanamicKey= GenerateRandomKeyString(32);
	       logger.info("dyanamicKey=="+dyanamicKey);
		    String encryptData=EncryptWithAES(gsonData, dyanamicKey);
		   logger.info("encryptData=="+encryptData);
		   String decryptData= DecryptWithAES(encryptData,dyanamicKey);
		  logger.info("decryptData=="+decryptData);
		    Cipher cipher1 = Cipher.getInstance("RSA");   
	        logger.info("KEY-RSA--"+ encryptTextRSA(dyanamicKey,publicKeyC()));
	        String encryKey=encryptTextRSA(dyanamicKey,publicKeyC());
			BufferedReader in = null ;
			
			try {
				    JSONObject inputJson = new JSONObject();
					inputJson.put("DATA", encryptData);  
					inputJson.put("KEY", encryKey);
		           
		            HttpURLConnection urlConnection = null;
			        
			            try {
			            	 URL url = new URL(apiUrl);
			            	 logger.info("inputJson::--before conn---1-- "+inputJson);
			                 urlConnection = (HttpURLConnection) url.openConnection();
			                  logger.info("inputJson::-After conn--2---- "+inputJson);
			            } catch (Exception e) {
						//	e.printStackTrace();
						}
			            //set up some things on the connection
			            urlConnection.setRequestMethod("POST");
			            urlConnection.setRequestProperty("Content-Type","application/json");
			            String encoding = Base64.getEncoder().encodeToString(credentials.getBytes());
						logger.info("makeWebServiceCall::::::encoding::::::::::::"+encoding);
						urlConnection.setRequestProperty("Authorization","Basic "+encoding);// c3Azc21kb21pbmZvOnNwM3NtNjBAbjUw String.format("Basic " + encoding));
						// enable output and input
			            urlConnection.setDoOutput(true); 
			             //and connect!
			            try {
			            urlConnection.connect();
			            } catch (Exception e) {
						//	e.printStackTrace();
						}
			             
			            PrintWriter pw =null;
			            try {
			            	 logger.info("pw::--start----- ");
			            	 pw = new PrintWriter(urlConnection.getOutputStream());
			                 logger.info("pw::---end---- "+pw);
			                 pw.write(inputJson.toString());
			                 logger.info("pw::---write---- "+pw);
			             
			            } catch (Exception e) {
						//	e.printStackTrace();
						}
			            logger.info("response::---1---- ");
			            pw.close();
			            
			            
			            try {
			            	logger.info("response::---3333---- ");
			            	InputStream inputStream;
			            	int status = urlConnection.getResponseCode();
			            	 logger.info("response::---status---"+status);
			            	if (status != HttpURLConnection.HTTP_OK)  {
			            		  logger.info("response::---IF-1---");
			            	      inputStream = urlConnection.getErrorStream();
			            	      logger.info("response::---IF----"+inputStream);
			            	}
			            	else  {
			            		 logger.info("response::---ELSE--2--");
			            	    inputStream = urlConnection.getInputStream();
			            	    logger.info("response::---ELSE----"+inputStream);
			            	}
	
			            } catch (Exception e) {
						//	e.printStackTrace();
						}
			            logger.info("response::----2------");
			            try{
			            	 logger.info("BufferedReader-----3----");
			            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			            logger.info("BufferedReader---4---");
			            }catch (Exception e) {
				         //    e.printStackTrace();
				          }
			            
			            if ((output = in.readLine()) != null) {
						//	logger.info("Result output::" + output);
						}
	
						   in.close();	
			            
			       } catch (Exception e) {
			       //      e.printStackTrace();
			          }
			
			    }catch (Exception e) {
	           //    e.printStackTrace();
	             }
			logger.info("final response::------- "+output);
			return output;
		
		
	}
	
	static PublicKey   publicKeyC() throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeySpecException{
		  
	     ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    java.util.Properties properties = new java.util.Properties();
	    try(InputStream resourceStream = loader.getResourceAsStream("application.properties")){
	        properties.load(resourceStream);
	    }catch(IOException e){
	     //   e.printStackTrace();
	    }
	 
	    String filepath=properties.getProperty("pathCertificate");
	   logger.info("-----filepath-----"+filepath);
	    File file=new File(filepath); 
	   // List<String> list=new ArrayList<>();
	    StringBuilder sb=null;
	    try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String sr=null;
			
			while((sr=br.readLine())!=null){
				sb= new StringBuilder();
				sb.append(sr);
			}
			///logger.info("sb::::::::::::::"+sb.toString());
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
			    FileInputStream fis = new FileInputStream(filepath);
		        CertificateFactory cf = CertificateFactory.getInstance("X.509");
		        Certificate cert1 = cf.generateCertificate(fis);
		       //logger.info("----cert1:::"+cert1.getPublicKey());
		        PublicKey publicKey = cert1.getPublicKey();
		       ///logger.info("PublicKey========= : \n" + publicKey.getAlgorithm());
		     return   publicKey;    
	   
  }
  
	
	
	private static String GenerateRandomKeyString(int len){
		   StringBuilder sb = new StringBuilder(len);
		   for(int i = 0; i < len; i++)
		      sb.append(AB.charAt(rnd.nextInt(AB.length())));
		   return sb.toString();
		}
	
	
	public static String EncryptWithAES(String message,String key)
    {
 
		   try {  
		  byte [] kb = key.getBytes("UTF-8");
		  byte [] ivb = Arrays.copyOf(kb,16);
		   
		  IvParameterSpec iv = new IvParameterSpec(ivb);
		  SecretKeySpec seckey= new SecretKeySpec(kb, "AES");
		  Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		  c.init(Cipher.ENCRYPT_MODE, seckey,iv);
		  byte[] encStr=c.doFinal(message.getBytes("UTF-8"));
		     String base64encStr =Base64.getEncoder().encodeToString(encStr).replaceAll("\r\n", "");
		  return base64encStr;
		 
		  } catch (Exception e) {
		 
		  return ""+e.toString();
           }
     
    }
	
	   public static String DecryptWithAES(String message, String key)
	    {
	   
	   try {
	   byte [] kb = key.getBytes("UTF-8");
	   byte [] ivb = Arrays.copyOf(kb,16);
	 
	  IvParameterSpec iv = new IvParameterSpec(ivb);
	  byte[] encStr = Base64.getDecoder().decode(message);
	  SecretKeySpec seckey= new SecretKeySpec(kb, "AES");
	  Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	  c.init(2,seckey,iv);
	  byte[] decStr=c.doFinal(encStr);
	  String base64decStr = new String(decStr);
	  return base64decStr;
	 
	 
	  } catch (Exception e) {
	 
	  return e.toString();
	  }
	   
	    }
	   
	   
	   public static String encryptTextRSA(String plainText,PublicKey publicKey){
	    	try {
	    	Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
	    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    	return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("utf-8")));
	    	} catch (Exception e) {
	   // 	    e.printStackTrace();
	    	}
	    	return null;
	    	}

}
