package nju.software.wsjx.service.model.xs;

import nju.software.wsjx.util.StringUtil;

/**
 * ���¸�����
 * @author ����
 *
 */
public enum XsFjxEnum {
	
	BDZZQL("��������Ȩ��"),//lb,qx
	FJ("����"),
	QZCJ("�������"),//lb
	MSGRBFCC("û�ո��˲��ֲƲ�"),
	MSGRQBCC("û�ո���ȫ���Ʋ�"),
	BDJX("�������");//lb
	String fjx;

	private XsFjxEnum(String fjx) {
		this.fjx = fjx;
	}

	public String getFjx() {
		return fjx;
	}

	public void setFjx(String fjx) {
		this.fjx = fjx;
	}
	
	public static XsFjxEnum getFjx(String content){
		for(XsFjxEnum fjx:XsFjxEnum.values()){
			if(StringUtil.contains(content, fjx.getFjx())){
				return fjx;
			}
		}
		if(StringUtil.contains(content, "û�ղƲ�")){
			return MSGRBFCC;
		}
		return null;
	}
}
