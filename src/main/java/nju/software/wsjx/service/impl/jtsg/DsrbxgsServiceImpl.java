package nju.software.wsjx.service.impl.jtsg;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.jtsg.DsrbxgsService;
import nju.software.wsjx.util.StringUtil;

public class DsrbxgsServiceImpl implements DsrbxgsService{

	@Override
	public boolean jqxBg(List<WssscyrModel> sscyrModels) {
		// TODO Auto-generated method stub
		boolean jqxBg =  false;
		for(WssscyrModel model:sscyrModels){
			if(StringUtil.contains(model.getSscyr(), "����") && 
					StringUtil.contains(model.getSssf(), "����")){
				jqxBg = true;
				break;
			}
		}
		return jqxBg;
	}

}
