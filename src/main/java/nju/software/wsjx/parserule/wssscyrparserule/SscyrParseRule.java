package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * ���ϲ����˽���
 * @author lr12
 *
 */
public interface SscyrParseRule {
	public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse) throws ParseException;
}