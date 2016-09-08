/**  
 * Filename:    SocketStay.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015年7月7日 下午5:07:06  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015年7月7日   senRsl      1.0            1.0 Version  
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
import java.util.Iterator;

import dc.common.BaseRetcode;
import dc.common.Global;

/**
 *
 * @author senrsl
 *
 */
public class SocketStay extends ChannelSocket {

	public SocketStay(String host, int port, int timeout) {
		super(host, port, timeout);
	}

	/* (non-Javadoc)
	 * @see dc.common.trans.socket.BaseSocket#init()
	 */
	@Override
	public int init()  {
		try {
			// 判断所给的服务器地址是否正确
			if (null == ip)
				ip = new InetSocketAddress(host, port);
			if (ip.isUnresolved()) {
				client.close();
				return BaseRetcode.ERROR_DE_HOST_UNRESOLVED;
			}

			// 初始化channel
			if (null == client || !client.isOpen()) {
				client = SocketChannel.open();
				client.configureBlocking(false);// true阻塞,false非阻塞
			}

			if (!client.isConnected())
				client.connect(ip);
			// 初始化选择器
			if (null == selector || !selector.isOpen())
				selector = Selector.open();
			// 信道注册,持有锁
//			Logger.w("信道是否注册：" + client.isRegistered());
			if (!client.isRegistered())
				client.register(selector, SelectionKey.OP_CONNECT);
		} catch (ClosedChannelException e) {
			if(Global.isDebug)e.printStackTrace();
			return BaseRetcode.ERROR_DE_CHANEL_CLOSED;
		} catch (IOException e) {
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
			if (selector != null) 
				if (selector.isOpen()) selector.close();
			if(null != client)
				if (client.isOpen()) client.close();
		} catch (IOException e) {
			if(Global.isDebug)e.printStackTrace();
			return BaseRetcode.ERROR_DE_CLOSE;
		}
		return BaseRetcode.SUSS;
	}

	int tempRet;
	
	/* (non-Javadoc)
	 * @see dc.common.trans.socket.BaseSocket#httpSocket(byte[], java.io.ByteArrayOutputStream)
	 */
	@Override
	public int httpSocket(byte[] b, ByteArrayOutputStream baos) {

		try {
			// 初始化
			if(BaseRetcode.SUSS != (tempRet = init()))return tempRet;

			_FOR: for (;;) {
				
				if(BaseRetcode.SUSS != (tempRet = timeout()))return tempRet;

				// 有进行通信的channel
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {

					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
					if (key.isConnectable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						if (channel.isConnectionPending())//已发起,未完成
							channel.finishConnect();//完成套接字通道的连接过程
						channel.write(ByteBuffer.wrap(b));
						channel.register(selector, SelectionKey.OP_READ);
					}
					
					if(BaseRetcode.SUSS != (tempRet = timeout()))return tempRet;

//					Logger.w("写：" + key.isWritable());
					if (key.isWritable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						channel.write(ByteBuffer.wrap(b));
						channel.register(selector, SelectionKey.OP_READ);
					}

//					Logger.w("读：" + key.isReadable());
					if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						byte[] by = new byte[1024];
						ByteBuffer buffer = ByteBuffer.wrap(by);

						buffer.clear();
						int n = channel.read(buffer);
						if (n > 0) {
							buffer.flip();
							baos.write(buffer.array(), 0, n);
							buffer.clear();

							channel.register(selector, SelectionKey.OP_WRITE);
							break _FOR;
						}
					}
				}
			}

		} catch (Exception e) {
			if(Global.isDebug)e.printStackTrace();
			close();
			return BaseRetcode.FAIL;
		}
		return BaseRetcode.SUSS;
	}
	
	private int timeout() throws IOException{
		// 判断当前的channel中是否要进行读写
		if (selector.select(timeout * 2 * 1000) == 0) {
			close();
//			Logger.w("信道未准备就绪,连接超时");
			return BaseRetcode.ERROR_DE_CONNECTED_TIMEOUT;
		}
		return BaseRetcode.SUSS;
	}
	

}
