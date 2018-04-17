package nju.software.wsjx.facade.impl;

import nju.software.classify.ParseMap;
import nju.software.wsjx.business.PreWsAnalyse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.facade.WsModelFacade;
import nju.software.wsjx.factory.ScannerFactory;
import nju.software.wsjx.factory.WsModelFactory;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsModelWrapper;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.scanner.WsParser;
import nju.software.wsjx.util.ExceptionUtil;
import nju.software.wsjx.util.FileUtil;
import org.jdom.JDOMException;

import javax.mail.internet.ParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * ����model�����ʵ����
 * @author lr12
 *
 */
public class WsModelFacadeImpl implements WsModelFacade{

	@Override
	public WsModel jxDocument(InputStream inputStream, String wswjm) {
		try{
//			��ȡ�����ĵ�������
			WsParser wsParser=ScannerFactory.getWsParserByWswjm(inputStream, wswjm);
//			���ĵ�������ת����String
			String content=wsParser.getContent();
			//Ԥ��������
			PreWsAnalyse preWsAnalyse=new PreWsAnalyse(wswjm, content);
			//��ȡ����Ľ�������
			WswsModel wswsModel=preWsAnalyse.handleWsws();
//			if(wswsModel==null||wswsModel.getParseName()==null)
//				throw new ParseException("δ������������ĵ�");
//			String parseName=wswsModel.getParseName();
			ParseMap parseMap = ParseMap.getInstance();
//			String parseName = parseMap.get("��һ����ͨ����");
			String parseName = parseMap.getParseName("��Ͻ");
//			ParseEnum parseEnum=ParseEnum.getParseEnumByParse(parseName);
//			if(parseEnum==null)
//				throw new ParseException("δ������������ĵ�");
			//�ֶ�
			WsAnalyse wsAnalyse=new WsAnalyse(wswjm,content);
			//���ݽ������ʹӹ�������ȡ����Model 
			WsModel wsModel=WsModelFactory.getWsModel(parseName,wsAnalyse);
			return wsModel;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public WsModel jxDocument(byte[] bytes, String wswjm) {
		try{
			//��ȡ�����ĵ�������
			WsParser wsParser=ScannerFactory.getWsParserByWswjm(bytes, wswjm);
			//���ĵ�������ת����String
			String content=wsParser.getContent();
			//Ԥ��������
			PreWsAnalyse preWsAnalyse=new PreWsAnalyse(wswjm, content);
			//��ȡ����Ľ�������
			WswsModel wswsModel=preWsAnalyse.handleWsws();
			if(wswsModel==null||wswsModel.getParseName()==null)
				throw new ParseException("δ������������ĵ�");
			String parseName=wswsModel.getParseName();
			
			ParseEnum parseEnum=ParseEnum.getParseEnumByParse(parseName);
			if(parseEnum==null)
				throw new ParseException("δ������������ĵ�");		
			//�ֶ�
			WsAnalyse wsAnalyse=new WsAnalyse(wswjm,content);
			
			//���ݽ������ʹӹ�������ȡ����Model 
			WsModel wsModel=WsModelFactory.getWsModel(parseName,wsAnalyse);			
			return wsModel;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public WsModel jxDocument(String wsnr, String wswjm) {
		try{			
			//Ԥ��������
			PreWsAnalyse preWsAnalyse=new PreWsAnalyse(wswjm, wsnr);
			//��ȡ����Ľ�������
			WswsModel wswsModel=preWsAnalyse.handleWsws();
			if(wswsModel==null||wswsModel.getParseName()==null)
				throw new ParseException("δ������������ĵ�");
			String parseName=wswsModel.getParseName();
			ParseEnum parseEnum=ParseEnum.getParseEnumByParse(parseName);
			if(parseEnum==null)
				throw new ParseException("δ������������ĵ�");			
			//�ֶ�
			WsAnalyse wsAnalyse=new WsAnalyse(wswjm,wsnr);
			//���ݽ������ʹӹ�������ȡ����Model 
			WsModel wsModel=WsModelFactory.getWsModel(parseName,wsAnalyse);			
			return wsModel;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws IOException, JDOMException{
		FileUtil fileUtil=new FileUtil();
		String wsmc = "������.doc";
		byte[] wsnr=fileUtil.getContent("F://Work//"+wsmc);
		InputStream is=new ByteArrayInputStream(wsnr);
		WsModelFacadeImpl wsModelFacadeImpl=new WsModelFacadeImpl();
		WsModel wsModel=wsModelFacadeImpl.jxDocument(wsnr, wsmc);
		wsModel.transformToXml("F://Work//Xml", wsmc);
		if(wsModel!=null){
        	println(wsModel.getWswsModel());
        	println(wsModel.getWsajjbqkModel());
        	println(wsModel.getWsssjlModel());
        	println(wsModel.getWscpjgModel());
        	println(wsModel.getWswwModel());
        }
	}
	public static void println(Object o){
		if(o==null)
			return;
		System.out.println(o);
	}
	
	@SuppressWarnings("finally")
	public WsModelWrapper jxDocumentWrapper(InputStream inputStream, String wswjm){
		WsModelWrapper wsModelWrapper = new WsModelWrapper();
		try{
			//��ȡ�����ĵ�������
			WsParser wsParser=ScannerFactory.getWsParserByWswjm(inputStream, wswjm);
			//���ĵ�������ת����String
			String content=wsParser.getContent();
			//Ԥ��������
			PreWsAnalyse preWsAnalyse=new PreWsAnalyse(wswjm, content);
			//��ȡ����Ľ�������
			WswsModel wswsModel=preWsAnalyse.handleWsws();
			if(wswsModel==null||wswsModel.getParseName()==null)
				throw new ParseException("δ������������ĵ�");
			String parseName=wswsModel.getParseName();
			ParseEnum parseEnum=ParseEnum.getParseEnumByParse(parseName);
			if(parseEnum==null)
				throw new ParseException("δ������������ĵ�");			
			//�ֶ�
			WsAnalyse wsAnalyse=new WsAnalyse(wswjm,content);
			//���ݽ������ʹӹ�������ȡ����Model 
			WsModel wsModel=WsModelFactory.getWsModel(parseName,wsAnalyse);	
			wsModelWrapper.setWsModel(wsModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			wsModelWrapper.setErrInfoList(ExceptionUtil.getExceptionStackTrace(e));
		}finally{
			wsModelWrapper.setWjm(wswjm);
			return wsModelWrapper;
		}
	}
}
