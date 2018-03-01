package fileUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;


public class DownFiles {
	/**
	 * 下载文件
	 * @throws IOException
	 */
	public void downFiles(String fileNamee,String filePathh,HttpServletResponse responsee) throws IOException{
		String fileName = fileNamee;
		String filePath = filePathh;
		String fileType = filePath.substring(filePath.lastIndexOf(".")+1, filePath.length());
		
		OutputStream os = null;
		InputStream is = null;
		BufferedOutputStream bos = null;
		try {
			
			HttpServletResponse response = responsee;
			os = response.getOutputStream();
			response.reset();// 清空输出流
			File file = new File(filePath);
			
			response.setContentType(fileType);
	        response.setCharacterEncoding("UTF-8");
	        fileName = URLEncoder.encode(fileName, "UTF-8");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName.replace("+", "%20") );
	        is = new FileInputStream(file);
	        bos = new BufferedOutputStream(os);
	        byte[] data = new byte[4096];
	        int size=0;
	        size=is.read(data);
	        // 开始循环写出数据
	        while (size!=-1) {
	         bos.write(data,0,size);
	         size=is.read(data);
	        }
	        bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bos!=null){
				bos.close();
			}
			if(is != null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
		}
	}
}
