package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum MZEnum  {
	HZ("����"),
	MZ("����"),
	HUIZ("����"),
    MGZ("�ɹ���"),
    ZZ("����"),
    WWEZ("ά�����"),
    MIAOZ("����"),
    YZ("����"),
    BYZ("������"),
    ZHZ("׳��"),
    CXZ("������"),
    DZ("����"),
    YAOZ("����"),
    BZ("����"),
    TJZ("������"),
    HSKZ("��������"),
    DAIZ("����"),
    SSZ("������"),
    WZ("����"),
    SZ("���"),
    GSZ("��ɽ��"),
    QZ("Ǽ��"),
    TZ("����"),
    SHUIZ("ˮ��"),
    ELSZ("����˹��"),
    BAZ("������"),
    TJKZ("��������"),
    NZ("ŭ��"),
    HNZ("������"),
	LZ("����");
	//�����塢�����塢�����塢�����塢�¶������塢���Ӷ��塢�����塢�����塢�����塢
	//ë���塢�����塢�����塢�����塢�����塢���α���塢���¿��塢�°��塢
	//ԣ���塢���塢�������塢�����塢���״��塢�����塢�Ű��塢����塢��ŵ��
    
	MZEnum(){
		
	}
	private MZEnum(String content){
		this.content = content;
	}
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> getMZList() {
		List<String> mzList = new ArrayList<String>();
		for (MZEnum mzEnum : MZEnum.values()) {
			mzList.add(mzEnum.getContent());
		}
		return mzList;
	}
	
	public static boolean HasMZ(String mz){
		List<String> list=getMZList();
		for(int i=0;i<list.size();i++){
	    
			if(mz.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;
	}
	
	public static String getMZ(String mz){
		List<String> list=getMZList();
		for(int i=0;i<list.size();i++){
	    
			if(mz.indexOf(list.get(i))!=-1){
//				System.out.println(mz);
				return list.get(i);
			}
		}
		return null;
	}
}
