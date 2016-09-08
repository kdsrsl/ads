/** 
 * Filename:    FileUtils.java 
 * Description:  
 * Copyright:   Copyright (c)2016 
 * Company:     VCYBER 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2016年3月21日 下午1:47:17 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年3月21日   senRsl      1.0         1.0 Version 
 */
package dc.common.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 文件操作
 * 
 * @author senrsl
 * <br/>finally改设2016年04月20日14:32:51
 *
 */
public class FileUtils {

	/**
	 * 存储文本到本地文件
	 * 
	 * @param context
	 *            待存储文本
	 * @param path
	 *            路径
	 * @return null/file
	 */
	public File writeStr2File(String context, String path) throws FileNotFoundException,IOException {
		OutputStream out = null;
		File file = null;
		if (null != context && null != path) {
			OutputStreamWriter osw = null;
			try {
				file = new File(path);
				if (!file.getParentFile().exists())file.mkdirs();
				out = new FileOutputStream(path);
				osw = new OutputStreamWriter(out);
				osw.write(context);
			}finally{
				if(null != osw)	osw.close();
				if(null != out)out.close();
			}
		}
		return file;
	}

	public static byte[] file2byte(String filePath) throws FileNotFoundException,IOException {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
		}finally{
			if(null != fis)fis.close();
			if(null != bos)bos.close();
		}
		buffer = bos.toByteArray();
		return buffer;
	}

	public static void byte2File(byte[] buf,  int offset, int length,String filePath, String fileName) throws FileNotFoundException,IOException {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory())dir.mkdirs();
			file = new File(new StringBuffer(filePath).append(File.separator).append(fileName).toString());//filePath + File.separator + fileName
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf,offset,length);
		}finally{
			if (null != bos) bos.close();
			if (null != fos) fos.close();
		}
	}
	
	public static void  is2File(InputStream is,String filepath) throws FileNotFoundException,IOException {
    	File file = new File(filepath);
    	// 输出的文件流   
		OutputStream os = null;
		try {
			if(file.exists())file.delete();
			else if (!file.getParentFile().exists())file.mkdirs();
			 
			// 1K的数据缓冲   
			 byte[] bs = new byte[1024];   
			 // 读取到的数据长度   
			 int len;   
			 os = new FileOutputStream(filepath);   
			 // 开始读取   
			 while ((len = is.read(bs)) != -1) {   
			     os.write(bs, 0, len);   
			 }
		}finally{
			// 完毕，关闭所有链接   
			if(null != os)os.close();  
			if(null != is)is.close();
		}
	}
}
