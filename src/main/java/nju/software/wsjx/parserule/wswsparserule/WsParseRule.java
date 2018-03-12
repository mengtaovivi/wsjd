package nju.software.wsjx.parserule.wswsparserule;

import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

/**
 * ���׽����Ĺ����壬Ϊ�˹淶�����ø���Ľӿڶ��ܳ��쳣
 * @author lr12
 *
 */
public interface WsParseRule {

	/**
	 * ����Ľ����������׽ӿ�
	 * @param wsws
	 * @return
	 */
	public WswsModel jxWswsModel(List<String> wsws) throws ParseException;
	
}
