package fileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class FileCopyAndDown{
	
	/**
	 * 文件源source的内容复制到目标文件target
	 * @param s
	 * @param t
	 */
	public static void fileChannelCopy(File source, File target) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(source);
            fo = new FileOutputStream(target);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void main(String[] args) {
		File s = new File("s.png");
		File t = new File("t.png");
		fileChannelCopy(s,t);
	}
	
	/**
	 * 文件下载
	 * @param downloadFile
	 */
//	public void fileStreamByFile(File downloadFile,String fileName){
//
//		 BufferedInputStream bis = null;
//		  BufferedOutputStream bos = null;
//		  OutputStream os = null;
//		  InputStream is = null;
//		try {
//			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
//			final File file = downloadFile;
//			is = new FileInputStream(file);
//			bis = new BufferedInputStream(is,5*1024*1024);// 鐢?5M鐨勭紦鍐茶鍙栨枃浠? 
//			os = ServletActionContext.getResponse().getOutputStream();
//			bos = new BufferedOutputStream(os,5*1024*1024);// 鐢?5M鐨勭紦鍐茶鍙栨枃浠? 
//			ServletActionContext.getResponse().reset();
//			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
//			ServletActionContext.getResponse().setContentType(
//					"application/octet-stream");// 鏂囦欢绫诲瀷contenttype
//			ServletActionContext.getRequest().setCharacterEncoding("GB2312");
//			fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
//			ServletActionContext.getResponse().setHeader("Content-Disposition",
//					"attachment; filename=\"" + fileName + "\""); // 鍏抽敭閮ㄥ垎锛屾墦寮?涓?涓笅杞芥
//			int bytesRead = 0;
//	         byte[] buffer = new byte[8192];
//	         
//	         while((bytesRead = bis.read(buffer,0,8192)) != -1)
//	         {
//	          bos.write(buffer, 0, bytesRead);
//	         }
//	         bos.flush();   
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//		  try {
//			  if(null!=is){
//				  is.close();
//			  }
//			  if(null!=bis){
//				  bis.close();
//			  }
//			  if(null!=os){
//				  os.close();
//			  }
//			  if(null!=bos){
//				  bos.close();
//			  }
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		   System.gc();
//	        return;	
//	}
}
