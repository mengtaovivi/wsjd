
package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;
import java.util.Map;

import nju.software.wsjd.model.msesWsModel.QscdModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.xs.FdmspjfzModel;

/**
 * ������н�� model
 * @author lr12
 *
 */
public class WscpjgModel {
	private String ajslf;// ��������ѣ�ָ������������ľ�
	
	List<String> cpjgnr;// ���н������
	
	private String jafs;//�᰸��ʽ
	
	private String sfzcssqq;//�Ƿ�֧����������
	
	private String sbsf;//ʤ���߷�
	/**
	 * �Ƿ�֧�������⳥��������������
	 */
	private String sfzcxzpc;
	/**
	 * �����⳥��������������
	 */
	private String xzpcje;
	private List<String> jabde;//�᰸��Ķ�
	private String jabdze;//�᰸����ܶ�
	
	private String sffhcs;//�Ƿ񷢻�����
	private String fhcsyy;//��������ԭ��
	private List<PjjgnrModel> pjjgList;//�о����
	private String kssz;//��������
	private String sstjcl;//�����ύ����
	private String ssqx;//��������
	private List<String> csrjh;//�����˼���
	private String cslx;//��������
//	private List<WsCpjgssfModel> ssfModelList;//���Ϸ�
	private  WsCpjgssfModel  ssfModel ;//���Ϸ�
	private String sftcgxyy;//�����Ͻ����
	private String pcsxsfzq;//��ͨ�¹��⳥˳���Ƿ���ȷ
	private String bsgssfbg;//�����˱��չ�˾�Ƿ���Ϊ����
	/**
	 * ���²��н��
	 */
	private String tcgxyy;//�����Ͻ����
	private List<String> gpyy;//����ԭ��
	private String ysxsbfpjjg;//һ�����²��ֲ��н��
	private String fdmscpjg;//�������²����о����
	private List<XspjjgfzModel> pjjgfzModels;//�����о��������
	private String dfdmspccl;//�Ը��������⳥����
	private FdmspjfzModel fdmspjfzModel;//���������о��������
	private String jtfz;//���ŷ���

	private String tdr;//����� ���ϲμ���
	private String btdr;//������� ���ϲμ���
	private String ssdw;//���ϵ�λ ���ϲμ���
	private String tcssr;//�˳������� ���ϲμ���

	private String ssr;//������ ���¶���
	private QscdModel qscdModel;//ǰ��ö��Ĵ��� ���¶���

	public String getSsr() {
		return ssr;
	}

	public void setSsr(String ssr) {
		this.ssr = ssr;
	}

	public QscdModel getQscdModel() {
		return qscdModel;
	}

	public void setQscdModel(QscdModel qscdModel) {
		this.qscdModel = qscdModel;
	}

	public String getTdr() {
		return tdr;
	}

	public void setTdr(String tdr) {
		this.tdr = tdr;
	}

	public String getBtdr() {
		return btdr;
	}

	public void setBtdr(String btdr) {
		this.btdr = btdr;
	}

	public String getSsdw() {
		return ssdw;
	}

	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}

	public String getTcssr() {
		return tcssr;
	}

	public void setTcssr(String tcssr) {
		this.tcssr = tcssr;
	}

	public String getAjslf() {
		return ajslf;
	}
	public String getTcgxyy() {
		return tcgxyy;
	}
	public void setTcgxyy(String tcgxyy) {
		this.tcgxyy = tcgxyy;
	}
	public List<String> getGpyy() {
		return gpyy;
	}
	public void setGpyy(List<String> gpyy) {
		this.gpyy = gpyy;
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
	public String getDfdmspccl() {
		return dfdmspccl;
	}
	public void setDfdmspccl(String dfdmspccl) {
		this.dfdmspccl = dfdmspccl;
	}
	public FdmspjfzModel getFdmspjfzModel() {
		return fdmspjfzModel;
	}
	public void setFdmspjfzModel(FdmspjfzModel fdmspjfzModel) {
		this.fdmspjfzModel = fdmspjfzModel;
	}
	public String getJtfz() {
		return jtfz;
	}
	public void setJtfz(String jtfz) {
		this.jtfz = jtfz;
	}
	public void setAjslf(String ajslf) {
		this.ajslf = ajslf;
	}

	public List<String> getCpjgnr() {
		return cpjgnr;
	}

	public void setCpjgnr(List<String> cpjgnr) {
		this.cpjgnr = cpjgnr;
	}

	public String getSfzcssqq() {
		return sfzcssqq;
	}

	public void setSfzcssqq(String sfzcssqq) {
		this.sfzcssqq = sfzcssqq;
	}

	public String getSbsf() {
		return sbsf;
	}

	public void setSbsf(String sbsf) {
		this.sbsf = sbsf;
	}
	public String getSfzcxzpc() {
		return sfzcxzpc;
	}
	public void setSfzcxzpc(String sfzcxzpc) {
		this.sfzcxzpc = sfzcxzpc;
	}
	public String getXzpcje() {
		return xzpcje;
	}
	public void setXzpcje(String xzpcje) {
		this.xzpcje = xzpcje;
	}
	public String getSffhcs() {
		return sffhcs;
	}
	public void setSffhcs(String sffhcs) {
		this.sffhcs = sffhcs;
	}
	public String getFhcsyy() {
		return fhcsyy;
	}
	public void setFhcsyy(String fhcsyy) {
		this.fhcsyy = fhcsyy;
	}
	public List<String> getJabde() {
		return jabde;
	}
	public void setJabde(List<String> jabde) {
		this.jabde = jabde;
	}
	public String getJabdze() {
		return jabdze;
	}
	public void setJabdze(String jabdze) {
		this.jabdze = jabdze;
	}
	public List<PjjgnrModel> getPjjgList() {
		return pjjgList;
	}
	public void setPjjgList(List<PjjgnrModel> pjjgList) {
		this.pjjgList = pjjgList;
	}
	public String getKssz() {
		return kssz;
	}
	public void setKssz(String kssz) {
		this.kssz = kssz;
	}
	public String getSstjcl() {
		return sstjcl;
	}
	public void setSstjcl(String sstjcl) {
		this.sstjcl = sstjcl;
	}
	public String getSsqx() {
		return ssqx;
	}
	public void setSsqx(String ssqx) {
		this.ssqx = ssqx;
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
	public WsCpjgssfModel getSsfModel() {
		return ssfModel;
	}
	public void setSsfModel(WsCpjgssfModel ssfModel) {
		this.ssfModel = ssfModel;
	}
	public String getJafs() {
		return jafs;
	}
	public void setJafs(String jafs) {
		this.jafs = jafs;
	}
	public String getSftcgxyy() {
		return sftcgxyy;
	}
	public void setSftcgxyy(String sftcgxyy) {
		this.sftcgxyy = sftcgxyy;
	}
	public String getPcsxsfzq() {
		return pcsxsfzq;
	}
	public void setPcsxsfzq(String pcsxsfzq) {
		this.pcsxsfzq = pcsxsfzq;
	}
	public String getBsgssfbg() {
		return bsgssfbg;
	}
	public void setBsgssfbg(String bsgssfbg) {
		this.bsgssfbg = bsgssfbg;
	}
	@Override
	public String toString() {
		return "WscpjgModel [ajslf=" + ajslf + ", cpjgnr=" + cpjgnr + ", jafs="
				+ jafs + ", sfzcssqq=" + sfzcssqq + ", sbsf=" + sbsf
				+ ", sfzcxzpc=" + sfzcxzpc + ", xzpcje=" + xzpcje + ", jabde="
				+ jabde + ", jabdze=" + jabdze + ", sffhcs=" + sffhcs
				+ ", fhcsyy=" + fhcsyy + ", pjjgList=" + pjjgList + ", kssz="
				+ kssz + ", sstjcl=" + sstjcl + ", ssqx=" + ssqx + ", csrjh="
				+ csrjh + ", cslx=" + cslx + ", ssfModel=" + ssfModel
				+ ", sftcgxyy=" + sftcgxyy + ", pcsxsfzq=" + pcsxsfzq
				+ ", bsgssfbg=" + bsgssfbg + "]";
	}
	
}
