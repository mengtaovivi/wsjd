package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum   ZWEnum {

	LS("��ʦ"), FWZY("����רԱ"), TXGR("���ݹ���"),GR("����"), NM("ũ��"), GCS("����ʦ"), MS("����"), FLGW("���ɹ���"),GW("����"), JS("��ʦ"),
	SJS("���ʦ"),SJ("˾��"),JSY("��ʻԱ"),JIS("��ʦ"),MDS("���ʦ"),CS("��ʦ"),CSZ("��ʦ��"),MJ("��"),FZZ("��վ��"),KJ("���"),CN("����"),
	ZZ("վ��"),DZ("�곤"),GS("���� "),FZR("������"),BGSZW("�칫������"),ZR("����"),FZJ("���ܼ�"),CWZJ("�����ܼ�"),ZJ("�ܼ�"),ZL("����"),ZC("�ܲ�"),ZXDS("ִ�ж���"),DSZ("���³�"),
	DS("����"),ZY("ְԱ"),YG("Ա��"),FBZ("������"),BZ("����"),FZJL("���ܾ���"),ZJL("�ܾ���"),FJL("������"),JL("����"),DWWW("��ίίԱ"),DWSJ("��ί���"),DWFSJ("��ί�����"),
	FCZ("������"),CZ("����"),FHZ("���г�"),HZ("�г�"),FZG("������"),ZG("����"),FDDBR("����������"),YTX("������"),ZYZY("����ְҵ"),
	WZY("��ְҵ"),WGDZY("�޹̶�ְҵ"),GZRY("������Ա"),WY("��ҵ"),NTZG("����ְ��"),TXZG("����ְ��"),ZHG("ְ��"),GTJYZ("���徭Ӫ��"),TXGB("���ݸɲ�"),
	JZ("�ֳ�"),GB("�ɲ�"),ZHZ("��"),WN("��ũ"),WG("��"),GT("����");

	ZWEnum(){

	}
	private String content;
	private ZWEnum(String content){
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static List<String> getZWList(){
		List<String> zwlist = new ArrayList<String>();
		for(ZWEnum zwEnum : ZWEnum.values()){
			zwlist.add(zwEnum.getContent());
		}
		return zwlist;
	}
	public static boolean HasZW(String zw){
		List<String> list = getZWList();
		for(int i=0;i<list.size();i++){
			if(zw.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;

	}
	public static String getZW(String zw){
		List<String> list = getZWList();
		for(int i=0;i<list.size();i++){
			if(zw.indexOf(list.get(i))!=-1){
				return list.get(i);
			}
		}
		return null;
	}
}
