package nju.software.wsjx.service.model.xs;

import java.util.List;

/**
 * �����з���ִ���з�
 * @author ����
 *
 */
public class PfModel {
	private String pfnr;//�з�����
	private ZmModel zm;//����
	private Xszx zx;//����
	private Xszx hx;//����
	private List<FjxModel> fjxList;//������
	private String pjjglx;//�о��������
	private String yzszbf;//ԭ�����ﲢ��
	
	public PfModel(String pfnr) {
		super();
		this.pfnr = pfnr;
	}
	public PfModel() {
		super();
	}
	public ZmModel getZm() {
		return zm;
	}
	public void setZm(ZmModel zm) {
		this.zm = zm;
	}
	public Xszx getZx() {
		return zx;
	}
	public void setZx(Xszx zx) {
		this.zx = zx;
	}
	public String getPjjglx() {
		return pjjglx;
	}
	public void setPjjglx(String pjjglx) {
		this.pjjglx = pjjglx;
	}
	public List<FjxModel> getFjxList() {
		return fjxList;
	}
	public void setFjxList(List<FjxModel> fjxList) {
		this.fjxList = fjxList;
	}
	public Xszx getHx() {
		return hx;
	}
	public void setHx(Xszx hx) {
		this.hx = hx;
	}
	public String getPfnr() {
		return pfnr;
	}
	public void setPfnr(String pfnr) {
		this.pfnr = pfnr;
	}
	public String getYzszbf() {
		return yzszbf;
	}
	public void setYzszbf(String yzszbf) {
		this.yzszbf = yzszbf;
	}
	

}
