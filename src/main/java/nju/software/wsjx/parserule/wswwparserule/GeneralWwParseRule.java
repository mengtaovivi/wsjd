package nju.software.wsjx.parserule.wswwparserule;







import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.WwEnum;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.StringUtil;

public class GeneralWwParseRule implements WwParseRule{

	public WswwModel jxWswwModel(List<String> wsww) throws ParseException{
		// TODO Auto-generated method stub
		WswwModel wswwModel = new WswwModel();
		if(wsww == null)
			return wswwModel;
		HashMap<String, String> spzzcyMap = new HashMap<String, String>();//������֯��ԱMap
	
		for (int i = 0; i < wsww.size(); i++) {
			String content = wsww.get(i);
			String wwsf = WwEnum.getWw(content);
			// ������ݺ�����
			if(wwsf!=null){
				
				int index = content.indexOf(wwsf);
				String wwmc = content.substring(index + wwsf.length(),
						content.length());
				String js = WwEnum.getWw(wwmc);
				//������Ա֮��û�пո�
				if(js!=null){
					content = WwEnum.addBlank(content);
					String[] contentWithBlank = content.split(" ");
					for(String s:contentWithBlank){
						wsww.add(s);
					}
				}else{
//					���Աxxx���棩
					if(StringUtil.equals(wwsf, "���Ա")&&StringUtil.contains(wwmc, "���棩")){
						wwsf = WwEnum.SJYJ.getContent();
						wwmc = wwmc.substring(0,wwmc.indexOf("���棩"));
					}else if(StringUtil.equals(wwsf, "���Ա")&&StringUtil.contains(wwmc, "������")){
						wwsf = WwEnum.SJYD.getContent();
						wwmc = wwmc.substring(0,wwmc.indexOf("������"));
					}
					spzzcyMap.put(wwmc, wwsf);
				}
			}else if (content.contains("��") || content.contains("��")
					|| content.contains("��")) {
				try{
					String wsrq;
					boolean hasWsrq = content.length() > 8;// content�ĳ��ȴ���"xxxx��x��x��"�ĳ����������������
					if (!hasWsrq) {
						wsrq = null;
					} else {
						wsrq = DateUtil.convertToCNDate(content);
					}
					if (wsrq != null) {
//						����������ת��Ϊ��������������
						wsrq = DateUtil.convertToCNDate(wsrq);
						if((wsrq.indexOf("��") - 4)>-1){
							String year = wsrq.substring(wsrq.indexOf("��") - 4,
									wsrq.indexOf("��"));
							String month = wsrq.substring(wsrq.indexOf("��") + 1,
									wsrq.indexOf("��"));
							String day = wsrq.substring(wsrq.indexOf("��") + 1,
									wsrq.indexOf("��"));
							String yearMonth = year+"-"+month;
							wsrq=wsrq==null?null:wsrq.replace("������ԭ���˶�����", "").replace("null��", "");
							wswwModel.setWsrq(wsrq);
							wswwModel.setYear(year);
							wswwModel.setMonth(month);
							wswwModel.setDay(day);
							wswwModel.setYearAndMonth(yearMonth);
						}
					}
				}catch(Exception e){
					
				}
				
			}

		}
		wswwModel.setSpzzcyMap(spzzcyMap);

		return wswwModel;
	}
	


}
