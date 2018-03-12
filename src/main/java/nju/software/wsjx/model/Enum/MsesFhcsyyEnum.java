package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum MsesFhcsyyEnum {
	
	SPZZBHF("������֯����ɲ��Ϸ�"),
	WHB("Ӧ���رܵ�������Աδ�ر�"),
	WNL("��������Ϊ������δ�����������˴�Ϊ����"),
	WFBD("Υ�����ᵱ���˱���Ȩ��"),
	TJBC("��©һ�����������ҵ��ⲻ��"),
	YLDSR("��©����μ����ϵĵ�����"),
	WFQXPJ("Υ��ȱϯ�о�"),
	SSBQ("ԭ�о��϶�������ʵ����"),
	ESYPLH("������ΪӦ���о���飬�Ʋ�����Ů�������ⲻ�ܴ�ɵ���"),
	QT("����");
	
	String content;

	private MsesFhcsyyEnum(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public static String getMsesfhccyy(String content){
		List<String> allList = new ArrayList<String>();
		for (MsesJafsEnum jafs : MsesJafsEnum.values()) {
			allList.add(jafs.getContent());
		}
		for(String yy:allList){
			if(content.indexOf(yy)!=-1){
				return yy;
			}
		}
		return null;
	}
	
}
