package nju.software.wsjx.model.caseinfo;

import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.parse.ParseXzysSegment;


/**
 * ����һ���ĵ�
 * @author lr12
 *
 */
public class XzysCaseInfo extends BaseCaseInfo{

	public XzysCaseInfo(){
		
	}
	//���Ի������Ժͷ���
	
	@Override
	public void generate(WsModel wsModel) {
		// TODO Auto-generated method stub
		this.wdbs="xzys";
	}	
	
}
