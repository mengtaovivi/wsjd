package nju.software.wsjx.service.model.xs;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;

/**
 * ����һ���о����
 * @author ����
 *
 */
public class XsPjjgModel {
	
	private String tcgxyy;//�����Ͻ����
	private  String jafs;//�᰸��ʽ
	private List<String> gpyy;//����ԭ��
	private String ysxsbfpjjg;//һ�����²��ֲ��н��
	private String fdmscpjg;//�������²����о����
	private List<XspjjgfzModel> pjjgfzModels;//�����о��������
	private String kssz;//��������
	private String ssqx;//��������
	private String sstjcl;//�����ύ����
	private List<String> csrjh;//�����˼���
	private String cslx;//��������
	private String dfdmspccl;//�Ը��������⳥����
	private FdmspjfzModel fdmspjfzModel;//���������о��������
	private String jtfz;//���ŷ���
	
	public XsPjjgModel() {
		super();
	}
	public String getTcgxyy() {
		return tcgxyy;
	}
	public void setTcgxyy(String tcgxyy) {
		this.tcgxyy = tcgxyy;
	}
	public String getJafs() {
		return jafs;
	}
	public void setJafs(String jafs) {
		this.jafs = jafs;
	}
	public String getYsxsbfpjjg() {
		return ysxsbfpjjg;
	}
	public void setYsxsbfpjjg(String ysxsbfpjjg) {
		this.ysxsbfpjjg = ysxsbfpjjg;
	}
	public String getFdmscpjg() {
		return fdmscpjg;
	}
	public void setFdmscpjg(String fdmscpjg) {
		this.fdmscpjg = fdmscpjg;
	}
	public List<XspjjgfzModel> getPjjgfzModels() {
		return pjjgfzModels;
	}
	public void setPjjgfzModels(List<XspjjgfzModel> pjjgfzModels) {
		this.pjjgfzModels = pjjgfzModels;
	}
	public String getKssz() {
		return kssz;
	}
	public void setKssz(String kssz) {
		this.kssz = kssz;
	}
	public String getSsqx() {
		return ssqx;
	}
	public void setSsqx(String ssqx) {
		this.ssqx = ssqx;
	}
	public String getSstjcl() {
		return sstjcl;
	}
	public void setSstjcl(String sstjcl) {
		this.sstjcl = sstjcl;
	}
	public List<String> getCsrjh() {
		return csrjh;
	}
	public void setCsrjh(List<String> csrjh) {
		this.csrjh = csrjh;
	}
	public String getCslx() {
		return cslx;
	}
	public void setCslx(String cslx) {
		this.cslx = cslx;
	}
	public FdmspjfzModel getFdmspjfzModel() {
		return fdmspjfzModel;
	}
	public void setFdmspjfzModel(FdmspjfzModel fdmspjfzModel) {
		this.fdmspjfzModel = fdmspjfzModel;
	}
	public String getDfdmspccl() {
		return dfdmspccl;
	}
	public void setDfdmspccl(String dfdmspccl) {
		this.dfdmspccl = dfdmspccl;
	}
	public List<String> getGpyy() {
		return gpyy;
	}
	public void setGpyy(List<String> gpyy) {
		this.gpyy = gpyy;
	}
	public String getJtfz() {
		return jtfz;
	}
	public void setJtfz(String jtfz) {
		this.jtfz = jtfz;
	}

}
