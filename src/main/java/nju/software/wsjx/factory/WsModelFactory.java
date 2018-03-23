package nju.software.wsjx.factory;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.caseinfo.BaseCaseInfo;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.parse.ParseSegment;

/**
 * ʹ�÷��䶯̬���ؽ���ʵ����ͽ������ɵľ����ĵ�
 * ������model����
 * @author lr12
 *
 */
public class WsModelFactory {
	public static WsModel getWsModel(String parseName,WsAnalyse wsAnalyse) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	
		StringBuilder pareseName=new StringBuilder();
		pareseName.append("nju.software.wsjx.parse.");
		pareseName.append(parseName);
//		ParseEnum parseEnum=ParseEnum.getParseEnumByParse(parseName);
//		if(parseEnum==null)
//			throw new ParseException("�޷���Ѱ�����࣬δ����ý�������");
//		pareseName.append(parseEnum.getParseDocumentName());
		Class<?> parseDocumentClass = Class.forName(pareseName.toString());
		//����������
		ParseSegment parseCaseinfo = (ParseSegment) parseDocumentClass.newInstance();
		parseCaseinfo.registerWsAnalyse(wsAnalyse);
		WsModel ws = parseCaseinfo.transformToWsModel();
		return ws;
	}
}
