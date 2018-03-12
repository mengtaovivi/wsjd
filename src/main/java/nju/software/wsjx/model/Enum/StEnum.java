package nju.software.wsjx.model.Enum;

/**
 *  ʵ������enum
 * @author lr12
 *
 */
public enum StEnum {

	YG("ԭ��"),
	BG("����"),
	SSR("������"),
	BSSR("��������"),
	SQR("������"),
	BSQR("��������"),
	DSR("������");
	
	private String stzt;

	private StEnum() {
	}

	private StEnum(String stzt) {
		this.stzt = stzt;
	}
	
	public String getStzt() {
		return stzt;
	}

	public void setStzt(String stzt) {
		this.stzt = stzt;
	}

	public static  boolean hasStzt(String content){
		
		for(StEnum stEnum:StEnum.values()){
			if(stEnum.getStzt().equals(content))
				return true;
		}
		return false;
	}
	
}
