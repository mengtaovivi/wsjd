package nju.software.wsjx.service.model.xs;

/**
 * ���� ����
 * @author ����
 *
 */
public class ZmModel {
	private String zm;//����
	private String zmdm;//��������
	private String wzzm;//��������
	
	
	public ZmModel() {
		super();
	}
	
	public ZmModel(String zm) {
		super();
		this.zm = zm;
	}


	public String getZmdm() {
		return zmdm;
	}
	public void setZmdm(String zmdm) {
		this.zmdm = zmdm;
	}
	public String getWzzm() {
		return wzzm;
	}
	public void setWzzm(String wzzm) {
		this.wzzm = wzzm;
	}

	public String getZm() {
		return zm;
	}

	public void setZm(String zm) {
		this.zm = zm;
	}
	
	

}
