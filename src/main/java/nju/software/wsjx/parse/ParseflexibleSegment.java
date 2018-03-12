package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

/**
 * �������Ľ�������ӿڣ����ϲ�����,���ϼ�¼,����������������з������̣����н��
 * @author lr12
 *
 */
public interface ParseflexibleSegment {

	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist);
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse);
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist);
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse);
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl);
}
