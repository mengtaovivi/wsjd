package nju.software.wsjx.model.wsSegmentationModel;

/**
 * ��������model
 * @author lr12
 *
 */
public class WswsModel {
	
	private String parseName;//��������
	private String jbfy;//���취Ժ
	private String wsmc;//��������
	private String ah;//����
	private String land;//�������
	
	private String wszzdw;//����������λ
	private String fyjb;//��Ժ����
	private String xzqhProv;//����������ʡ��
	private String xzqhCity;//�����������У�
	
	private String wszl;//��������
	private String ajxz;//��������
	private String spcx;//���г���
	private String ajlx;//��������
	
	
	
	

	public String getWszzdw() {
		return wszzdw;
	}
	public void setWszzdw(String wszzdw) {
		this.wszzdw = wszzdw;
	}
	public String getFyjb() {
		return fyjb;
	}
	public void setFyjb(String fyjb) {
		this.fyjb = fyjb;
	}
	public String getXzqhProv() {
		return xzqhProv;
	}
	public void setXzqhProv(String xzqhProv) {
		this.xzqhProv = xzqhProv;
	}
	public String getXzqhCity() {
		return xzqhCity;
	}
	public void setXzqhCity(String xzqhCity) {
		this.xzqhCity = xzqhCity;
	}
	public String getWszl() {
		return wszl;
	}
	public void setWszl(String wszl) {
		this.wszl = wszl;
	}
	public String getAjxz() {
		return ajxz;
	}
	public void setAjxz(String ajxz) {
		this.ajxz = ajxz;
	}
	public String getSpcx() {
		return spcx;
	}
	public void setSpcx(String spcx) {
		this.spcx = spcx;
	}
	public String getAjlx() {
		return ajlx;
	}
	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
	}
	public String getJbfy() {
		return jbfy;
	}
	public void setJbfy(String jbfy) {
		this.jbfy = jbfy;
	}
	public String getWsmc() {
		return wsmc;
	}
	public void setWsmc(String wsmc) {
		this.wsmc = wsmc;
	}
	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = ah;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	
	public String getParseName() {
		return parseName;
	}
	public void setParseName(String parseName) {
		this.parseName = parseName;
	}
	@Override
	public String toString() {
		return "WswsModel [parseName=" + parseName + ", jbfy=" + jbfy
				+ ", wsmc=" + wsmc + ", ah=" + ah + ", land=" + land
				+ ", wszzdw=" + wszzdw + ", fyjb=" + fyjb + ", xzqhProv="
				+ xzqhProv + ", xzqhCity=" + xzqhCity + ", wszl=" + wszl
				+ ", ajxz=" + ajxz + ", spcx=" + spcx + ", ajlx=" + ajlx + "]";
	}
	

}
