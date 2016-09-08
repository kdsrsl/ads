/**  
 * Filename:    SocketUtils.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015-5-22 下午1:52:18  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015-5-22   senRsl      1.0            1.0 Version  
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
public class SocketSingle extends ChannelSocket {

	public SocketSingle(String host, int port, int timeout) {
		super(host, port, timeout);
	}

	/*
	 * (non-Javadoc)
	 * @see dc.common.trans.socket.BaseSocket#httpSocket(byte[], java.io.ByteArrayOutputStream)
	 */
	public int httpSocket(byte[] b, ByteArrayOutputStream baos) {
		long sum = 0;

		try {
			init();

			_FOR: for (;;) {
				// int shijian = selector.select(m_nTimeOut * 2 * 1000);
				if (selector.select(timeout * 2 * 1000) == 0) {
					// if( shijian == 0) {
					if (selector.isOpen()) {
						selector.close();
					}
					client.close();
//					Logger.w("信道未准备就绪");
					return BaseRetcode.ERROR_DE_CONNECTED_LOCAL_NOT;
				}
				// selector.select();
				Iterator<SelectionKey> iter = selector.selectedKeys()
						.iterator();
				while (iter.hasNext()) {
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
					if (key.isConnectable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						if (channel.isConnectionPending())
							channel.finishConnect();
						channel.write(ByteBuffer.wrap(b));
						channel.register(selector, SelectionKey.OP_READ);// 侦听文件描述符
					}
					int shijian = selector.select(timeout * 2 * 1000);
					if (shijian == 0) {
						if (selector.isOpen()) {
							selector.close();
						}
						client.close();
						return BaseRetcode.ERROR_DE_CONNECTED_LOCAL_NOT;
					}
//					Logger.w("分" + key.isReadable());
					if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						byte[] by = new byte[1024];
						ByteBuffer buffer = ByteBuffer.wrap(by);

						int resultLen = 0;
						while (true) {
							buffer.clear();
							int n = channel.read(buffer);
							if (n > 0) {
								buffer.flip();
								sum += n;
								baos.write(buffer.array(),0,n);
								buffer.clear();

								if (sum == resultLen) {
									selector.close();
									key.cancel();
									buffer.clear();
									client.close();
									break _FOR;
								}
//								Logger.w("has waiting...");
							} else if (n == 0) {
								if (sum == resultLen) {
									selector.close();
									key.cancel();
									buffer.clear();
									client.close();
									break _FOR;
								}
								continue;
							} else {
//								Logger.w("client close connect");
								selector.close();
								key.cancel();
								buffer.clear();
								client.close();
								break _FOR;

							}

						}
					}
				}
			}

		} catch (Exception e) {
			if (Global.isDebug)
				e.printStackTrace();
			close();
			return BaseRetcode.ERROR_DE_CONNECTED_HOST_NOT;
		}
		return BaseRetcode.SUSS;
	}

	@Override
	public int init() {

		try {
			if (null == client || !client.isOpen()) {
				client = SocketChannel.open();
				client.configureBlocking(false);// true阻塞,false非阻塞
			}

			if (null == ip)
				ip = new InetSocketAddress(host, port);
			if (ip.isUnresolved()) {
				client.close();
//				Logger.w("找不到地址");
				return BaseRetcode.ERROR_DE_CONNECTED_LOCAL_NOT;
			}

			if (!client.isConnected())
				client.connect(ip);

			if (null == selector || !selector.isOpen())
				selector = Selector.open();

			if (!client.isRegistered())
				client.register(selector, SelectionKey.OP_CONNECT);// 信道注册,持有锁
		} catch (ClosedChannelException e) {
			if (Global.isDebug)
				e.printStackTrace();
			return BaseRetcode.ERROR_DE_CONNECTED_LOCAL_NOT;
		} catch (IOException e) {
			if (Global.isDebug)
				e.printStackTrace();
			return BaseRetcode.ERROR_DE_CONNECTED_LOCAL_NOT;
		}

		return BaseRetcode.SUSS;
	}

	@Override
	public int close() {
		try {
			if (selector != null) {
				if (selector.isOpen()) {
					selector.close();
				}
				if (client.isOpen()) {
					client.close();
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			return BaseRetcode.ERROR_DE_CONNECTED_HOST_NOT;
		}
		return BaseRetcode.SUSS;
	}

	/**************** dc.vsf.utils.SocketClass End *********************************/

}
