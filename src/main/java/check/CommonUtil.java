package check;

import httl.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公共工具类
 * 
 * @author Zhijun Yuan
 * @version 1.0, 2013-10-30
 */
public class CommonUtil {
	// 日志信息
	final static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * 是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			if (((String) obj).length() == 0) {
				return true;
			}
		} else if (obj instanceof Collection) {
			if (((Collection) obj).size() == 0) {
				return true;
			}
		} else if (obj instanceof Map) {
			if (((Map) obj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否是数字类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 获取随机数
	 */
	public static String getRandomNumber(int count) {
		StringBuffer sb = new StringBuffer();
		String base = "0123456789";
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/*
	 * 获取随机字符
	 */
	public static String getRandomCode(int count) {
		StringBuffer sb = new StringBuffer();
		String base = "23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKMNPQRSTUVWXYZ0";
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 校验手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile)) {
			return false;
		}
		String regEx = "[1]{1}[3,4,5,6,7,8]{1}[0-9]{9}";
		return Pattern.compile(regEx).matcher(mobile).matches();
	}

	/** 获得IP地址 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getRemoteAddr();// IP
		try {
			if (ip.equals("127.0.0.1")) {
				InetAddress inet = InetAddress.getLocalHost();
				ip = inet.getHostAddress();
			}
		} catch (Exception e) {

			return "";
		}
		return ip;
	}

	/**
	 * 是否是EMAIL格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		/** EMAIL 格式正则 */
		String mailRegx = "[\\w\\.\\_\\-]+@(\\w+.)+[a-zA-Z]{2,3}";
		Pattern pattern = Pattern.compile(mailRegx);
		Matcher m = pattern.matcher(email.toLowerCase());
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 校验url是否符合规范
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		// 判断是否是带http，https、ftp请求的url地址
		Pattern pattern = Pattern
				.compile(
						"^(http|https|ftp|www|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$",
						Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(url);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 *获取页面json中的值
	 */
	public static String itemJson(String json, String keyastr) {
		String jsonValue = "";
		if (!isEmpty(json)) {
			// 转义模板字段
			net.sf.json.JSONObject js = net.sf.json.JSONObject.fromObject(json);
			// 判断json中是否存在
			if (js.containsKey(keyastr)) {
				jsonValue = js.getString(keyastr);
			}
		}
		return jsonValue;
	}


	/**
	 * 两个时间之间的天数 ,如果前者小于后者，那么返回0
	 * 
	 * @param date1
	 *            :开始时间
	 * @param date2
	 *            :结束时间
	 * @return
	 */
	public static long getComputeDays(Date date1, Date date2) {
		if (date1.compareTo(date2) >= 0) {
			return (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		}
		return 0;
	}

	
    /**
     * 删除Html标签
     * 
     * @param inputString
     * @return
     */
    public static String htmlRemoveTag(String inputString) {
        if (inputString == null)
            return null;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
            //定义style的正则表达式{或<style [^="">]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }
    
    /**
     * 保存文件
     * @param fileIs
     * @param savePath
     * @param fileName
     * @return
     * @throws IOException
     */
	public static boolean saveJPGFile(InputStream fileIs, String savePath, String fileName) throws IOException{
		boolean result = true;
	
		/**
		 * 存储至[本地指定目录]/[当天]目录下
		 */
		// 检查路径是否存在,如果不存在则创建之
		File file = new File(savePath);
		if (!file.exists()) {
			log.debug("创建本地目录" + savePath);
			file.mkdirs();
		}
		
		if(StringUtils.isEmpty(fileName)){
			fileName = new StringBuffer(UUID.randomUUID().toString().replace("-", "")).append(".jpg").toString();
		}
		
		File file2Local = new File(savePath, fileName);
		// 检查同名文件是否存在,不存在则将文件流写入文件磁盘系统
		log.info("###开始保存本地文件####");
		FileOutputStream os = new FileOutputStream(file2Local);
		
		byte[] data = new byte[4096];
	    int len = 0;
	    
	    while ((len = fileIs.read(data)) != -1) {
	    	os.write(data, 0, len);
        }
	    
		os.flush();
		os.close();
		log.info("本地文件" + savePath + fileName + "已保存");
		
		return result;
	}
	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(java.io.File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /**
     * 计算当前时间与历史时间之间的分钟数
     * @param now
     * @param old
     * @return
     */
    public static long minDiff(java.util.Date now, java.util.Date old){
    	// 系统当前时间
    	Calendar dateOne=Calendar.getInstance();
    	dateOne.setTime(now);	//设置为当前系统时间 
    	
    	// 历史时间
		Calendar dateTwo=Calendar.getInstance();
    	dateTwo.setTime(old);	//设置为2015年1月15日
    	
    	long timeOne=dateOne.getTimeInMillis();
    	long timeTwo=dateTwo.getTimeInMillis();
    	
    	return (timeOne-timeTwo)/(1000*60);
    }
	
	/**  
     * 下载远程文件并保存到本地  
     * @param remoteFilePath 远程文件路径   
     * @param localFilePath 本地文件路径  
     */
    public static void downloadFile(String remoteFilePath, String localFilePath)
    {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try
        {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static byte[] fileToBetyArray(File file)  
    {  
        FileInputStream fileInputStream = null;  
        byte[] bFile = null;  
        try {  
            bFile = new byte[(int) file.length()];  
            fileInputStream = new FileInputStream(file);  
            fileInputStream.read(bFile);  
            fileInputStream.close();  
            for (int i = 0; i < bFile.length; i++) {  
                System.out.print((char) bFile[i]);  
            }  
            System.out.println("Done");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                fileInputStream.close();  
                bFile.clone();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return bFile;  
    }  
    
    /**
	 * 字符串左补
     *    在指定字符串左边补充指定字符到指定长度
     * @param str String 需要补充的字符串
     * @param totalLength int 补充字符后的字符串长度
     * @param paddingStr String 指定的补充字符
     * @return String 补充后的字符串
     */
    public static String LPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // 获取待补充字符串长度
        int strLength = str.trim().length();
        // 计算与总长度的差值
        int dif_length = totalLength - strLength;
        
        // 1、添加补充字符
        while(dif_length > 0)
        {
            reStr.append(paddingStr);
            dif_length--;
        }
        // 2、添加原字符串
        reStr.append(str.trim());

        return reStr.toString();
    }
    
    /**
	 * 去掉多余的0和小数点
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) throws Exception{    
        if(s.indexOf(".") > 0){    
            s = s.replaceAll("0+?$", "");//去掉多余的0    
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉    
        }    
        return s;    
    }  
	
	
	public static String getPayKey(String StringKey){
		 String payString = "";
		 Properties prop = new Properties(); 
		 InputStream in;
		try {
			String path = CommonUtil.class.getClassLoader().getResource("").getPath();
			in = new BufferedInputStream (new FileInputStream(path+"/trig.properties"));
			prop.load(in);
			payString = prop.getProperty(StringKey);
        	in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payString;
	}
	
	/**
	 * 将数组按指定字符串 拼接成字符串
	 * 类似JavaScript中ArrayObject.join(keyStr)
	 * @param keyStr
	 * @param strAry
	 * @return
	 */
	public static String join(String keyStr, String[] strAry){
        StringBuffer sb = new StringBuffer();
        for(int i=0, len =strAry.length; i<len; i++){
            if(i == (len-1)){
                sb.append(strAry[i]);
            } else {
                sb.append(strAry[i]).append(keyStr);
            }
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		System.out.println(getPayKey("trig.pay.key"));
		System.out.println(getPayKey("trig.pay.shbh"));
		System.out.println(getPayKey("trig.pay.url"));
	}
}
