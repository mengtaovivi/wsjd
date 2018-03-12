package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum HeadExceptionEnum {	
	JGDM("��������"),
	ZZJGDM("��֯��������"),
	GMSFHM("������ݺ���"),
	GMSFZH("�������֤��"),
	GMSFH("������ݺ�"),
	TYSHXYDM("ͳһ������ô���"),
	SFHM("��ݺ���"),
	SFZH("���֤��"),
	XZ("��ס"),
	DLQX("����Ȩ��");
	HeadExceptionEnum() {
	}
	private HeadExceptionEnum(String content) {
		this.content = content;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> getHeadList() {
		List<String> headList = new ArrayList<String>();
		for (HeadExceptionEnum headExceptionEnum : HeadExceptionEnum.values()) {
			headList.add(headExceptionEnum.getContent());
		}
		return headList;
	}
	public static boolean HasHead(String head){
		List<String> list=getHeadList();
		for(int i=0;i<list.size();i++){
		//	if(head.indexOf(list.get(i))>-1&&head.indexOf(list.get(i))<10){
/*				System.out.println(head);*/
			if(head.startsWith(list.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public static String getHead(String head){
		List<String> list=getHeadList();
		for(int i=0;i<list.size();i++){
	    
			if(head.indexOf(list.get(i))>-1){
/*				System.out.println(head);*/
				return list.get(i);
			}
		}
		return null;
	}
}
