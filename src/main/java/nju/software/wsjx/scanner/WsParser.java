package nju.software.wsjx.scanner;

import java.io.InputStream;

/**
 * �����ĳ�����
 * @author lr12
 *
 */
public abstract class WsParser {
	protected InputStream is;
	public WsParser(InputStream is){
		this.is=is;
	}
	
	public abstract String getContent();
}
