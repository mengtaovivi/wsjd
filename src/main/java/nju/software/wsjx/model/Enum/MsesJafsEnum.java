package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum MsesJafsEnum {
	WC("ά��"),
    JCXDSR("�����������˳���֮���о�"),
    GP("����"),
    ZXCHSS("׼�賷������"),
	TJ("����"),
	CXYCPBHQS("����ԭ�в���������"),
	FHCS("��������"),
	YSQTFY("����ԭ�в��ö�����������Ժ��Ͻ"),
	ACHSSCL("���������ߴ���"),
	ZYCHQSCXYSPJ("׼�賷�����߲�����һ���о�"),
	ZLSL("����ԭ�ö���ָ������"),
	CXZLSL("����ԭ�ö���ָ������");
	MsesJafsEnum(){
	}

	private MsesJafsEnum(String content) {
		this.content = content;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	//������н᰸��ʽ
	public static List<String> getAll() {
		List<String> jafsList = new ArrayList<String>();
		for (MsesJafsEnum jafs : MsesJafsEnum.values()) {
			jafsList.add(jafs.getContent());
		}
		return jafsList;
	}
	
	//�����о�����жϽ᰸��ʽ�������᰸��ʽ�ͷ���
	public static String getMsesjafs(String pjjg){
		List<String> jafsList = getAll();
		for(String jafs:jafsList){
			if(pjjg.indexOf(jafs)!=-1){
				return jafs;
			}
		}
		return null;
	}
}
