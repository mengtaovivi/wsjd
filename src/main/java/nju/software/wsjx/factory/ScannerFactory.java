package nju.software.wsjx.factory;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.scanner.WsParser;
import nju.software.wsjx.scanner.impl.DocWsParser;
import nju.software.wsjx.scanner.impl.DocxWsParser;
import nju.software.wsjx.scanner.impl.RtfWsParser;
import nju.software.wsjx.scanner.impl.TxtWsParser;
import nju.software.wsjx.util.StringUtil;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * �������������
 * @author lr12
 *
 */
public class ScannerFactory {

	/**
	 * ��ȡ������������������ļ�����������
	 * @param inputStream
	 * @param wswjm
	 * @return
	 * @throws ParseException
	 */
	public static WsParser getWsParserByWswjm(InputStream inputStream,
			String wswjm) throws ParseException {
		if (wswjm != null) {
			wswjm = wswjm.trim();//ȥ�ո�
			wswjm= wswjm.toLowerCase();//ͳһ��Сд
		}
		int posb = wswjm.lastIndexOf('.');//ȡ�����һ�����λ��
		//��ȡ�����׺
		String suffix = wswjm.substring(posb + 1);
		WsParser wsParser = null;
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp = new String(bytes);
		InputStream is=new ByteArrayInputStream(bytes);

		if(StringUtil.equals(temp.substring(0, 5), "{\\rtf") && !StringUtil.equals("rtf", suffix)){
			//rtf ���⴦��
			wsParser=new RtfWsParser(is);
			return wsParser;
		}
		//������������ѡ����ʼ�����
		switch (suffix) {
		case "doc":
			wsParser = new DocWsParser(is);
			break;
		case "docx":
			wsParser = new DocxWsParser(is);
			break;
		case "rtf":
			wsParser = new RtfWsParser(is);
			break;
		default:
			throw new ParseException("δ��������ø�ʽ����");

		}


		return wsParser;
	}
	
	/**
	 * ��ȡ������������������ļ�����byte����
	 * @param bytes
	 * @param wswjm
	 * @return
	 * @throws ParseException
	 */
	public static WsParser getWsParserByWswjm(byte[] bytes,
			String wswjm) throws ParseException {
		if (wswjm != null) {
			wswjm = wswjm.trim();
			wswjm= wswjm.toLowerCase();
		}
		int posb = wswjm.lastIndexOf('.');
		//��ȡ�����׺
		String suffix = wswjm.substring(posb + 1);
		
		WsParser wsParser = null;	
		String temp = new String(bytes);
		InputStream is=new ByteArrayInputStream(bytes);
		
		try {
			if(StringUtil.equals(temp.substring(0, 5), "{\\rtf") && !StringUtil.equals("rtf", suffix)){
				//rtf ���⴦��
				wsParser=new RtfWsParser(is);
			}
			else if("doc".equals(suffix)){
				wsParser=new DocWsParser(is);
			}else if("docx".equals(suffix)){
				wsParser=new DocxWsParser(is);
			}else if("rtf".equals(suffix)){
				wsParser=new RtfWsParser(is);
			}
			else if("txt".equals(suffix)){
				wsParser=new TxtWsParser(is);
			}
			else throw new ParseException("δ��������ø�ʽ����");
			is.close();
		}catch (OfficeXmlFileException e) {
			
			/*wsParser=new DocxWsParser(new ByteArrayInputStream(bytes));*/
			return wsParser;
			//e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return wsParser;
	}

}
