package nju.software.wsjx.service.model.xs;

import nju.software.wsjx.util.StringUtil;

public enum XszxEnum {
	SX("����"),
	SH("����"),
	WQTX("����ͽ��"),
	YQTX("����ͽ��"),
	JY("����"),
	GZ("����");
	String xz;

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	private XszxEnum(String xz) {
		this.xz = xz;
	}
	
	public static XszxEnum getXszx(String conten){
		for(XszxEnum xz:XszxEnum.values()){
			if(StringUtil.contains(conten, xz.getXz())){
				return xz;
			}
		}
		return null;
	}

}
