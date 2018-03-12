package nju.software.wsjx.service.impl.jtsg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.jtsg.PjjgService;
import nju.software.wsjx.util.StringUtil;

public class PjjgServiceImpl implements PjjgService{

	@Override
	public boolean isPeichangIndexCorrect(String content, List<WssscyrModel> models) {
		// TODO Auto-generated method stub
		Map<String,Integer> indexMap = new HashMap<String,Integer>();
		for(WssscyrModel model:models){
			int index = StringUtil.indexOf(content, model.getSscyr());
			model.setPeichangIndex(index);
			if(StringUtil.contains(model.getSscyr(), "����") 
					&& indexMap.get("����")==null && index !=-1){
				indexMap.put("����", index);
			}else if(StringUtil.contains(model.getSsdw(), "����") && !StringUtil.contains(model.getSsdw(), "����")
					&& (indexMap.get("����")==null || indexMap.get("����")>index)&& index !=-1){
				indexMap.put("����", index);
			}
			
		}
		if(indexMap.get("����")!=null && indexMap.get("����")!=null && indexMap.get("����") >indexMap.get("����")  ){
			return false;
		}
		return true;
	}

}
