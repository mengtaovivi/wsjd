package nju.software.wsjx.service.model;

import java.util.List;


/**
 * �о���������Ϸ��࣬�������������Ϸ������Ϣ
 * @author ����
 *
 */
public class WsCpjgssfModel {
	private String ssfjl;//���ϷѼ�¼
	private List<WsCpjgssfjeModel> ssfjeModels;//���Ϸѽ��
	private List<WscpjgssfcdModel> ssfcdModels;//���Ϸѳе�
	private String jnqk;//�������
	private String zje;//���Ϸ��ܽ��
	
	public WsCpjgssfModel() {
		super();
	}
	public String getSsfjl() {
		return ssfjl;
	}
	public void setSsfjl(String ssfjl) {
		this.ssfjl = ssfjl;
	}
	public List<WsCpjgssfjeModel> getSsfjeModels() {
		return ssfjeModels;
	}
	public void setSsfjeModels(List<WsCpjgssfjeModel> ssfjeModels) {
		this.ssfjeModels = ssfjeModels;
	}
	public List<WscpjgssfcdModel> getSsfcdModels() {
		return ssfcdModels;
	}
	public void setSsfcdModels(List<WscpjgssfcdModel> ssfcdModels) {
		this.ssfcdModels = ssfcdModels;
	}
	public String getJnqk() {
		return jnqk;
	}
	public void setJnqk(String jnqk) {
		this.jnqk = jnqk;
	}
	public String getZje() {
		return zje;
	}
	public void setZje(String zje) {
		this.zje = zje;
	}
	

}
