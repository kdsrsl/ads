/**  
 * Filename:    BaseSocket.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015年7月7日 下午4:59:07  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015年7月7日   senRsl      1.0            1.0 Version  
 */
package dc.common.trans;

import java.io.ByteArrayOutputStream;

/**
 *
 * @author senrsl
 *
 */
public abstract class BaseTrans {

	protected String host;
	protected int port;
	protected int timeout;

	public BaseTrans(String host, int port, int timeout) {
		super();
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}

	/**************** dc.vsf.utils.SocketClass Start *********************************/


	public abstract int init();

	public abstract int close();

	/**
	 * 
	 * @param b
	 *            传入的字节流
	 * @param baos
	 *            返回的字节流承载
	 * @return 错误码
	 */
	public abstract int httpSocket(byte[] b, ByteArrayOutputStream baos);

	/**************** dc.vsf.utils.SocketClass End *********************************/

}
