package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
/**
 * ��Ž����̶����������߼����������ף���β,��¼��
 * @author lr12
 *
 */
public interface ParseFixedSegment {

	//�����̶��Ķ����߼�
	public WswsModel jxWswsModel(List<String> wsws);
	
	//�����̶���������β
	public WswwModel jxWswwModel(List<String> wsww);
	
}
