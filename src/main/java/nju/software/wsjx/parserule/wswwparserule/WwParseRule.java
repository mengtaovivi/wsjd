package nju.software.wsjx.parserule.wswwparserule;

import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;

/**
 * ��β����
 * @author lr12
 *
 */
public interface WwParseRule {
	/**
	 * ����Ľ���������β�ӿ�
	 * @param wsww
	 * @return
	 */
	public WswwModel jxWswwModel(List<String> wsww) throws ParseException;
	
}