package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.List;

import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.ZmModel;

/**
 * �����о��������
 * @author ����
 *
 */
public class XspjjgfzModel {
	
	private String sscyr;//���ϲ�����
	private List<PfModel> dzpf;//�����з�(�����о����
	private PfModel zxpf;//ִ���з�(�����о����
	private ZmModel pjzzm;//�о�������(�����о����
	private String xqksrq;//���ڿ�ʼ����
	private String xqjsrq;//���ڽ�������
	private String xqzdbf;//�����۵ְ취
	private String mzhwzsf;//����������ͷ�
	private String szbf;//���ﲢ������/��
	private String hblx;//�ϲ����̣���/��
	private List<PfModel> yzpf;//ԭ���з�(ԭ���о����
	private String essljg;
	private String eslxjg;
	
	public XspjjgfzModel() {
		super();
	}
	
	public XspjjgfzModel(String sscyr) {
		super();
		this.sscyr = sscyr;
	}

	public String getSscyr() {
		return sscyr;
	}
	public void setSscyr(String sscyr) {
		this.sscyr = sscyr;
	}
	
	
   public List<PfModel> getDzpf() {
		return dzpf;
	}
	public void setDzpf(List<PfModel> dzpf) {
		this.dzpf = dzpf;
	}
	//	public PfModel getDzpf() {
//		return dzpf;
//	}
//	public void setDzpf(PfModel dzpf) {
//		this.dzpf = dzpf;
//	}
	public PfModel getZxpf() {
		return zxpf;
	}
	public void setZxpf(PfModel zxpf) {
		this.zxpf = zxpf;
	}
	public String getXqksrq() {
		return xqksrq;
	}
	public void setXqksrq(String xqksrq) {
		this.xqksrq = xqksrq;
	}
	public String getXqjsrq() {
		return xqjsrq;
	}
	public void setXqjsrq(String xqjsrq) {
		this.xqjsrq = xqjsrq;
	}
	public ZmModel getPjzzm() {
		return pjzzm;
	}
	public void setPjzzm(ZmModel pjzzm) {
		this.pjzzm = pjzzm;
	}
	public String getXqzdbf() {
		return xqzdbf;
	}
	public void setXqzdbf(String xqzdbf) {
		this.xqzdbf = xqzdbf;
	}

	public String getSzbf() {
		return szbf;
	}

	public void setSzbf(String szbf) {
		this.szbf = szbf;
	}

	public String getHblx() {
		return hblx;
	}

	public void setHblx(String hblx) {
		this.hblx = hblx;
	}

	public String getMzhwzsf() {
		return mzhwzsf;
	}

	public void setMzhwzsf(String mzhwzsf) {
		this.mzhwzsf = mzhwzsf;
	}

	public List<PfModel> getYzpf() {
		return yzpf;
	}

	public void setYzpf(List<PfModel> yzpf) {
		this.yzpf = yzpf;
	}

	public String getEssljg() {
		return essljg;
	}

	public void setEssljg(String essljg) {
		this.essljg = essljg;
	}

	public String getEslxjg() {
		return eslxjg;
	}

	public void setEslxjg(String eslxjg) {
		this.eslxjg = eslxjg;
	}
	

}
