/**  
 * Filename:    SocketKeep.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015年7月8日 下午8:24:46  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015年7月8日   senRsl      1.0            1.0 Version  
 */ 
package dc.common.trans.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import dc.common.BaseRetcode;
import dc.common.Global;


/**
 *
 * @author senrsl
 * http://docs.oracle.com/javase/7/docs/api/index.html?overview-summary.html
 */
public class SocketKeep extends ChannelSocket {

	public SocketKeep(String host, int port, int timeout) {
		super(host, port, timeout);
	}

	/* (non-Javadoc)
	 * @see dc.common.trans.socket.BaseSocket#init()
	 */
	@Override
	public int init() {
		try {
			if (client == null || !client.isOpen()) {
				client = SocketChannel.open();
				client.configureBlocking(false);
				selector = Selector.open();// 打开选择器
				client.register(selector, SelectionKey.OP_CONNECT);
			}
			if (!client.isConnected()) {
				if (!client.connect(new InetSocketAddress(host, port))) {
					int i = 0;
					while (!client.finishConnect()) {
						// 连接服务器
						i++;
						if (i >= 100) {
							client.close();
							client = null;
							return BaseRetcode.ERROR_DE_CONNECTED_TIMEOUT;
						}
						Thread.sleep(10);
					}
				}
			}
		} catch (ClosedChannelException e) {
			if(Global.isDebug)e.printStackTrace();
			return BaseRetcode.ERROR_DE_CHANEL_CLOSED;
		} catch (IOException e) {
			if(Global.isDebug)e.printStackTrace();
			return BaseRetcode.ERROR_DE_INIT;
		} catch (InterruptedException e) {
			if(Global.isDebug)e.printStackTrace();
			return BaseRetcode.ERROR_DE_INIT;
		}
		return BaseRetcode.SUSS;
	}

	/* (non-Javadoc)
	 * @see dc.common.trans.socket.BaseSocket#close()
	 */
	@Override
	public int close() {
		try {
			client.close();
			client = null;
		} catch (IOException e) {
			if(Global.isDebug)e.printStackTrace();
			return BaseRetcode.ERROR_DE_CLOSE;
		}
		return BaseRetcode.SUSS;
	}

	/* (non-Javadoc)
	 * @see dc.common.trans.socket.BaseSocket#httpSocket(byte[], java.io.ByteArrayOutputStream)
	 */
	@Override
	public int httpSocket(byte[] b, ByteArrayOutputStream baos) {

		if (b == null || b.length <= 0) {
			return BaseRetcode.ERROR_DE_CONTEXT_EMPTY;
		}
		try {
			init();
			try {
				ByteBuffer readBuf = ByteBuffer.allocate(1024);
				if (client.read(readBuf) <= -1) {
					close();
					return httpSocket(b, baos);
				}
			} catch (ClosedChannelException e) {
				close();
				return BaseRetcode.ERROR_DE_CHANEL_CLOSED;
			}

			ByteBuffer writeBuf = ByteBuffer.wrap(b);
			client.write(writeBuf);

			// 接收服务端返回数据
			ByteBuffer readBuf = ByteBuffer.allocate(1024);
			for (int i = 0; i <= 50; i++) {
				if (i >= 20) {
					return BaseRetcode.ERROR_DE_CONNECTED_HOST_NOT;
				}
				int readSize = client.read(readBuf);
				if (readSize > 0) {
					baos.write(readBuf.array(),0,readSize);
					break;
				}

				Thread.sleep(10);
			}
		} catch (Exception e) {
			client = null;
			return BaseRetcode.FAIL;
		}
		return BaseRetcode.SUSS;
	}

}
