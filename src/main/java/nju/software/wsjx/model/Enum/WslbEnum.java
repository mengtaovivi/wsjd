package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * �������ö��
 * 
 * @author lr12
 *
 */
public enum WslbEnum {
	
	TSBL("ͥ���¼"), 
	PJS("�о���"), 
	CDS("�ö���"), 
	TJS("������"), 
	QT("����");

	private String content;

	private WslbEnum(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	//��ȡȫ�����������
	public static List<String> getWslbList() {
		List<String> wslbs = new ArrayList<String>();
		for (WslbEnum wslbEnum : WslbEnum.values()) {
			wslbs.add(wslbEnum.getContent());
		}
		return wslbs;
	}
	
}
