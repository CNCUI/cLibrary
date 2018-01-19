package fileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Pasier
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class IoZipFileUtil {
	/** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径 
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @return 
     */  
    public static boolean fileToZip( File[] sourceFiles ,File zipFile){  
        boolean flag = false;  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
          
            try {  
                if(zipFile.exists()){  
                	zipFile.delete();
                }
                if(null == sourceFiles || sourceFiles.length<1){  
                    System.out.println("待压缩的文件目录里面不存在文件，无需压缩.");  
                }else{  
                    fos = new FileOutputStream(zipFile);  
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                    zos.setEncoding("GB2312");
                    byte[] bufs = new byte[1024*10];  
                    for(int i=0;i<sourceFiles.length;i++){ 
                    	try {
	                    	File file = sourceFiles[i] ;
	                		//文件存在服务器
	                    	if(file.exists()){
		                        //创建ZIP实体，并添加进压缩包  
		                        ZipEntry zipEntry = new ZipEntry(file.getName());  
		                        zos.putNextEntry(zipEntry);  
		                        //读取待压缩的文件并写进压缩包里  
		                        fis = new FileInputStream(file);  
		                        bis = new BufferedInputStream(fis, 1024*10);  
		                        int read = 0;  
		                        while((read=bis.read(bufs, 0, 1024*10)) != -1){  
		                            zos.write(bufs,0,read);  
		                        }  
	                    	}
						} catch (Exception e) {
							e.printStackTrace();  
						}finally{  
			                //关闭流  
			                try {  
			                    if(null != fis) fis.close();  
			                    if(null != bis) bis.close();  
			                } catch (IOException e) {  
			                    e.printStackTrace();  
			                    throw new RuntimeException(e);  
			                }  
				        }
                    }  
                    flag = true;  
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                //throw new RuntimeException(e);  
            } catch (IOException e) {  
                e.printStackTrace();  
                //throw new RuntimeException(e);  
            } finally{  
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    //throw new RuntimeException(e);  
                }  
                try {
                	 if(null != zos) zos.close();  
				} catch (Exception e2) {
					
				}
               try {
            	   if(null!=fos){
                   	fos.close();
                   }
				} catch (Exception e2) {
					// TODO: handle exception
				}
                
               try {
            	   if(null!=fis){
                   	fis.close();
                   }
				} catch (Exception e2) {
					// TODO: handle exception
				}
                
                
        }  
        return flag;  
    }  
      
}