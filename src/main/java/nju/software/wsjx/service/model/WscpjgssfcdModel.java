package nju.software.wsjx.service.model;

/**
 * �о�������Ϸѳе���ģ�ͣ������˵����˳е����Ϸ������Ϣ
 * @author ����
 *
 */
public class WscpjgssfcdModel {
	private String cdr;//�е���
	private String cdrdw;//�е���˼ά
	private String cdje;//�е����
	private String cdfs;//�е���ʽ
	
	public WscpjgssfcdModel() {
		super();
	}
	public WscpjgssfcdModel(String cdr, String cdrdw, String cdje, String cdfs) {
		super();
		this.cdr = cdr;
		this.cdrdw = cdrdw;
		this.cdje = cdje;
		this.cdfs = cdfs;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public String getCdrdw() {
		return cdrdw;
	}
	public void setCdrdw(String cdrdw) {
		this.cdrdw = cdrdw;
	}
	public String getCdje() {
		return cdje;
	}
	public void setCdje(String cdje) {
		this.cdje = cdje;
	}
	public String getCdfs() {
		return cdfs;
	}
	public void setCdfs(String cdfs) {
		this.cdfs = cdfs;
	}
	

}
