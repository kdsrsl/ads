/**  
 * Filename:    ChannelSocket.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015年7月8日 下午8:34:34  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015年7月8日   senRsl      1.0            1.0 Version  
 */ 
package dc.common.trans.socket;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import dc.common.trans.BaseTrans;



/**
 *
 * @author senrsl
 *
 */
public abstract class ChannelSocket extends BaseTrans {
	
	
	protected SocketChannel client = null;
	protected Selector selector = null;

	protected InetSocketAddress ip = null;


	public ChannelSocket(String host, int port, int timeout) {
		super(host, port, timeout);
	}
	
	
	


}
