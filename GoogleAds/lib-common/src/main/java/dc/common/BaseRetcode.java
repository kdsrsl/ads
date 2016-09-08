/**  
 * Filename:    VsfRetcode.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015-5-14 下午4:27:12  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015-5-14   senRsl      1.0            1.0 Version  
 */
package dc.common;

/**
 * 
 * 
 * VsfRetcode
 * 
 * @author senrsl
 * 
 *         android.vsf 2015-5-14 下午4:27:12
 */
public class BaseRetcode {

	/**
	 * 默认值/未实现
	 */
	public static final int DEFAULT = -1;

	/**
	 * 成功/通过
	 */
	public static final int SUSS = 2000;

	/**
	 * 拒绝
	 */
	public static final int REJECT = 3001;

	/**
	 * 拒绝,行为码不匹配
	 */
	public static final int REJECT_OPCODE = 3002;

	/**
	 * 失败
	 */
	public static final int FAIL = 3100;

	/**
	 * 参数错误
	 */
	public static final int FAIL_PARAMS = 3101;
	
	/**
	 * 本地持久化失败
	 */
	public static final int FAIL_STORAGE_LOCAL = 3300;


	/**
	 * 内部错误
	 */
	public static final int ERR = 5000;

	/**
	 * 不存在
	 */
	public static final int ERR_NOTFOUND = 5001;
	
	/**
	 * 缺少操作码
	 */
	public static final int ERR_NOTFOUND_OPCODE = 5002;
	
	/**
	 * 远端主机无法连通
	 */
	public static final int ERROR_DE_CONNECTED_HOST_NOT = 5010;
	
	/**
	 * 网络不通
	 */
	public static final int ERROR_DE_CONNECTED_LOCAL_NOT = 5011;

	/**
	 * 主机找不到
	 */
	public static final int ERROR_DE_HOST_UNRESOLVED = 5012;
	
	/**
	 * 信道已关闭
	 */
	public static final int ERROR_DE_CHANEL_CLOSED = 5013; 

	/**
	 * 初始化异常,IO错误
	 */
	public static final int ERROR_DE_INIT = 5014; 

	
	/**
	 * 关闭时IO异常
	 */
	public static final int ERROR_DE_CLOSE = 5015; 

	/**
	 * 连接超时
	 */
	public static final int ERROR_DE_CONNECTED_TIMEOUT = 5016;

	/**
	 * 要传的内容为空
	 */
	public static final int ERROR_DE_CONTEXT_EMPTY = 5017;
	
	/**
	 * 协议,解析时长度不足
	 */
	public static final int ERROR_DE_PROTOCOL_LENGTH_LESS = 5020;

	/**
	 * 协议，流长度不匹配
	 */
	public static final int ERROR_DE_PROTOCOL_LENGTH_SUM = 5021;

	/**
	 * 协议，无效的起止符
	 */
	public static final int ERROR_DE_PROTOCOL_FRONTIER = 5022;

	/**
	 * 协议编码,未预期类型,处理解析时
	 */
	public static final int ERROR_BE_PROTOCOL_TYPE_UNEXCEPTED = 5023;

	/**
	 * 协议编码,未预期的数据等级,数据准备时
	 */
	public static final int ERROR_BE_PROTOCOL_LEVEL_UNEXCEPTED = 5024;
	
	/**
	 * 协议解码,未预期类型,处理解析时
	 */
	public static final int ERROR_DE_PROTOCOL_TYPE_UNEXCEPTED = 5025;
	
	/**
	 * 远程调用provider出现异常
	 */
	public static final int ERROR_REMOTE_PROVIDER = 5030;
	
}
