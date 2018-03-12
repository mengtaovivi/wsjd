package nju.software.wsjx.parserule.tsblparserule.commonparserule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonParseRule {

	public static Map<String, String> parseCommon(List<String> paragraphs){
		Map<String, String> map=new HashMap<String, String>();
		boolean fy=false;
		for(String string:paragraphs){
			String split[]=string.split("[:��]");
			if(split[0].contains("ʱ��")&&!map.containsKey("ktsj")){
			    //������ͥʱ��
				map.put("ktsj", split[1]);
			}
			else if(split[0].contains("�ص�")&&!map.containsKey("ktdd")){
				//������ͥ�ص�
				map.put("ktdd", split[1]);
			}
			else if(split[0].contains("��Ժ")&&split[0].contains("fy")){
				//������Ժ��
				map.put("fy", split[0]);
				fy=true;
			}
			else if(fy){
				//������������
				map.put("wsmc", split[0]);
				fy=false;
			}
		}
		return map;
	}
}
