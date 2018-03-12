package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ϲ������Ļ��̶�ö����
 * @author LXY
 *
 */
public enum WhcdEnum {
	WM("��ä"),
	XXWH("Сѧ�Ļ�"),
	CZWH("�����Ļ�"),
	ZZWH("��ר�Ļ�"),
	GZWH("�����Ļ�"),
	DZWH("��ר�Ļ�"),
	BKWH("�����Ļ�"),
	DXBKWH("��ѧ�����Ļ�"),
	DXWH("��ѧ�Ļ�");
	WhcdEnum(){
		
	}
	private WhcdEnum(String content){
		this.content = content;
	}
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static List<String> getWhcdList(){
		List<String> whcd = new ArrayList<String>();
		for(WhcdEnum whcdEnum : WhcdEnum.values()){
			whcd.add(whcdEnum.getContent());
		}
		return whcd;
	}
	
	public static boolean HasWhcd(String whcd){
		List<String> list = getWhcdList();
		for(int i=0;i<list.size();i++){
			if(whcd.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;
	}
	
	public static String getWhcd(String whcd){
		List<String> list = getWhcdList();
		for(int i=0;i<list.size();i++){
			if(whcd.indexOf(list.get(i))!=-1){
				return list.get(i);
			}
		}
		return null;
	}
}
