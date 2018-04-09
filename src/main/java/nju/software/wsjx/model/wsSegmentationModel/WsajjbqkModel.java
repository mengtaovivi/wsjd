package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;

/**
 * ���鰸���������model
 * @author lr12
 *
 */
public class WsajjbqkModel {
	private String qyspjd;//ǰһ���о��� �������� ��������
	private String qysdl;//ǰһ����� �������� ��������
	private String qsdl;//ǰ����� ���¶��� ��������
	private String qsygscd;//ǰ��ԭ���߳� ���¶��� ��������
	private String qsbgbcd;//ǰ�󱻸��ƶ� ���¶��� ��������
	private String qsdsryjd;//ǰ������������ ��
	private List<String> qszjd;//ǰ��֤�ݶ� ��������
	private List<String> qssld;//ǰ������� ���¶��� ��������
	private String qscmd;//ǰ������� ���¶���

	public String getQscmd() {
		return qscmd;
	}

	public void setQscmd(String qscmd) {
		this.qscmd = qscmd;
	}

	private String qsfsscd;//ǰ�����߳ƶ� ���¶��� ��������
	private String qspjd;//ǰ���о��� ���¶��� ��������
	private String bsdl;//������� ���¶��� ��������
	private String ssrscd;//�������߳ƶ� ���¶��� ��������
	private List<String> bssrbcd;//�������˱�ƶ� ���¶��� ��������
	private String bsdsryjd;//������������ ��������
	private List<String> bssld;//��������� ���¶��� �������� ����һ��
	private List<String> bszjd;//����֤�ݶ� ��������
	private String ygscd;//ԭ���߳ƶ� ����һ�� ����һ��
	private String bgbcd;//�����ƶ� ����һ�� ����һ��
	private List<String> cmssd;//������ʵ�� ����һ�� ����һ��
	private List<String> zjd;//֤�ݶ� ����һ�� ����һ�� ��������
	private String dsryjd;//����������� ����һ�� ����һ��
	private String fsscd;//�����߳ƶ� ����һ��
	private String fsbcd;//���߱�ƶ� ����һ��
	private String xzsszyd;//������������� ����һ��
	private String zkdl;//ָ�ض��� ����һ��
	private String bhdl;//�绤���� ����һ��
	private String xsbssld;//���±�������� ���¶��� ����һ��
	private String xsqssld;//����ǰ������� ���¶���
	private String qscpyzypjjg;//ǰ�����Ҫּ���о���� ���¶���
	private String qscmssyzj;//ǰ�������ʵ��֤�� ���¶���
	private String ssssbhyj;//�������߱绤��� ���¶���
	private String jcjgyj;//��������� ���¶���
	private String fdmsssqqd;//����������������� ����һ��
	private String zkss;//ָ����ʵ ����һ�� 
	private String zkzj;//ָ��֤�� ����һ��
	private String zkyj;//ָ����� ����һ��
	private List<String> bgrbc;//�����˱�� ����һ��
	private List<String> bhrbh;//�绤�˱绤 ����һ��
	private List<PjjeModel> ssqqjeList;//����������
	
	private String ysfylyd;//���ͷ�Ժ���ɶ� ��Ͻ
	private String bqfylyd;//���뷨Ժ���ɶ� ��Ͻ
	public String getYsfylyd() {
		return ysfylyd;
	}
	public void setYsfylyd(String ysfylyd) {
		this.ysfylyd = ysfylyd;
	}
	public String getBqfylyd() {
		return bqfylyd;
	}
	public void setBqfylyd(String bqfylyd) {
		this.bqfylyd = bqfylyd;
	}
	public String gxString() {
		return "temo{" +
				"ysfylyd='" + ysfylyd + '\'' +
				", bqfylyd='" + bqfylyd + '\'' +
				'}';
	}

	private FsqqModel fsqqModel;//�������� ��һ����ͨ����
	private SsqqModel ssqqModel;//�������� ��һ����ͨ����
	private ZjdsrModel zjdsrModel;//׷�ӵ����˶� ��һ����ͨ����
	public FsqqModel getFsqqModel() {
		return fsqqModel;
	}
	public void setFsqqModel(FsqqModel fsqqModel) {
		this.fsqqModel = fsqqModel;
	}
	public SsqqModel getSsqqModel() {
		return ssqqModel;
	}
	public void setSsqqModel(SsqqModel ssqqModel) {
		this.ssqqModel = ssqqModel;
	}
	public ZjdsrModel getZjdsrModel() {
		return zjdsrModel;
	}
	public void setZjdsrModel(ZjdsrModel zjdsrModel) {
		this.zjdsrModel = zjdsrModel;
	}
	public String ysptString(){
		return "yspt{" +
				"fsqqModel=" + fsqqModel +
				", ssqqModel=" + ssqqModel +
				", zjdsrModel=" + zjdsrModel +
				'}';
	}

	private String sgxq;//�¹�����
	private String sgsj;//�¹�ʱ��
	private String sgdd;//�¹ʵص�
	private String jdcsyr;//������������
	private String jdcglr;//������������
	private String gajgrdyj;//���������϶����
	private String shrjzd;//�ܺ��˾�ס��
	private String shrzy;//�ܺ���ְҵ
	private String sftb;//�Ƿ�Ͷ��
	private String tbxz;//Ͷ������
	private String sfzbxqn;//�Ƿ��ڱ�������
	private String sfxqpf;//�Ƿ������⸶
	private String shangQing;//����
	private String realPay;//ʵ��֧�����
	private String identifyContent;//��ؼ���
	private String jdsfkk;//�����Ƿ�ɿ�
	public String getQyspjd() {
		return qyspjd;
	}
	public void setQyspjd(String qyspjd) {
		this.qyspjd = qyspjd;
	}
	public String getQysdl() {
		return qysdl;
	}
	public void setQysdl(String qysdl) {
		this.qysdl = qysdl;
	}
	public String getQsdl() {
		return qsdl;
	}
	public void setQsdl(String qsdl) {
		this.qsdl = qsdl;
	}
	public String getQsygscd() {
		return qsygscd;
	}
	public void setQsygscd(String qsygscd) {
		this.qsygscd = qsygscd;
	}
	public String getQsbgbcd() {
		return qsbgbcd;
	}
	public void setQsbgbcd(String qsbgbcd) {
		this.qsbgbcd = qsbgbcd;
	}
	public String getQsdsryjd() {
		return qsdsryjd;
	}
	public void setQsdsryjd(String qsdsryjd) {
		this.qsdsryjd = qsdsryjd;
	}
	public List<String> getQszjd() {
		return qszjd;
	}
	public void setQszjd(List<String> qszjd) {
		this.qszjd = qszjd;
	}

	public String getQsfsscd() {
		return qsfsscd;
	}
	public void setQsfsscd(String qsfsscd) {
		this.qsfsscd = qsfsscd;
	}
	public List<String> getQssld() {
		return qssld;
	}
	public void setQssld(List<String> qssld) {
		this.qssld = qssld;
	}
	public String getQspjd() {
		return qspjd;
	}
	public void setQspjd(String qspjd) {
		this.qspjd = qspjd;
	}
	public String getBsdl() {
		return bsdl;
	}
	public void setBsdl(String bsdl) {
		this.bsdl = bsdl;
	}

	public String getSsrscd() {
		return ssrscd;
	}
	public void setSsrscd(String ssrscd) {
		this.ssrscd = ssrscd;
	}


	public String getFdmsssqqd() {
		return fdmsssqqd;
	}
	public void setFdmsssqqd(String fdmsssqqd) {
		this.fdmsssqqd = fdmsssqqd;
	}
	public List<String> getBszjd() {
		return bszjd;
	}
	public void setBszjd(List<String> bszjd) {
		this.bszjd = bszjd;
	}
	public List<String> getBssrbcd() {
		return bssrbcd;
	}
	public void setBssrbcd(List<String> bssrbcd) {
		this.bssrbcd = bssrbcd;
	}
	public List<String> getBssld() {
		return bssld;
	}
	public void setBssld(List<String> bssld) {
		this.bssld = bssld;
	}

	public String getBsdsryjd() {
		return bsdsryjd;
	}
	public void setBsdsryjd(String bsdsryjd) {
		this.bsdsryjd = bsdsryjd;
	}
	public String getYgscd() {
		return ygscd;
	}
	public void setYgscd(String ygscd) {
		this.ygscd = ygscd;
	}
	public String getBgbcd() {
		return bgbcd;
	}
	public void setBgbcd(String bgbcd) {
		this.bgbcd = bgbcd;
	}
	public List<String> getCmssd() {
		return cmssd;
	}
	public void setCmssd(List<String> cmssd) {
		this.cmssd = cmssd;
	}
	public List<String> getZjd() {
		return zjd;
	}
	public void setZjd(List<String> zjd) {
		this.zjd = zjd;
	}
	public String getDsryjd() {
		return dsryjd;
	}
	public void setDsryjd(String dsryjd) {
		this.dsryjd = dsryjd;
	}
	public String getFsscd() {
		return fsscd;
	}
	public void setFsscd(String fsscd) {
		this.fsscd = fsscd;
	}
	public String getFsbcd() {
		return fsbcd;
	}
	public void setFsbcd(String fsbcd) {
		this.fsbcd = fsbcd;
	}
	public String getXzsszyd() {
		return xzsszyd;
	}
	public void setXzsszyd(String xzsszyd) {
		this.xzsszyd = xzsszyd;
	}
	public String getZkdl() {
		return zkdl;
	}
	public void setZkdl(String zkdl) {
		this.zkdl = zkdl;
	}
	public String getBhdl() {
		return bhdl;
	}
	public void setBhdl(String bhdl) {
		this.bhdl = bhdl;
	}
	public String getXsbssld() {
		return xsbssld;
	}
	public void setXsbssld(String xsbssld) {
		this.xsbssld = xsbssld;
	}
	public String getZkss() {
		return zkss;
	}
	public void setZkss(String zkss) {
		this.zkss = zkss;
	}
	public String getZkzj() {
		return zkzj;
	}
	public void setZkzj(String zkzj) {
		this.zkzj = zkzj;
	}
	public String getZkyj() {
		return zkyj;
	}
	public void setZkyj(String zkyj) {
		this.zkyj = zkyj;
	}
	public List<String> getBgrbc() {
		return bgrbc;
	}
	public void setBgrbc(List<String> bgrbc) {
		this.bgrbc = bgrbc;
	}
	public List<String> getBhrbh() {
		return bhrbh;
	}
	public void setBhrbh(List<String> bhrbh) {
		this.bhrbh = bhrbh;
	}
	public String getXsqssld() {
		return xsqssld;
	}
	public void setXsqssld(String xsqssld) {
		this.xsqssld = xsqssld;
	}
	public String getQscpyzypjjg() {
		return qscpyzypjjg;
	}
	public void setQscpyzypjjg(String qscpyzypjjg) {
		this.qscpyzypjjg = qscpyzypjjg;
	}
	public String getQscmssyzj() {
		return qscmssyzj;
	}
	public void setQscmssyzj(String qscmssyzj) {
		this.qscmssyzj = qscmssyzj;
	}
	public String getSsssbhyj() {
		return ssssbhyj;
	}
	public void setSsssbhyj(String ssssbhyj) {
		this.ssssbhyj = ssssbhyj;
	}
	public String getGsjgctyj() {
		return jcjgyj;
	}
	public void setGsjgctyj(String gsjgctyj) {
		this.jcjgyj = gsjgctyj;
	}
	public List<PjjeModel> getSsqqjeList() {
		return ssqqjeList;
	}
	public void setSsqqjeList(List<PjjeModel> ssqqjeList) {
		this.ssqqjeList = ssqqjeList;
	}
	public String getJcjgyj() {
		return jcjgyj;
	}
	public void setJcjgyj(String jcjgyj) {
		this.jcjgyj = jcjgyj;
	}
	public String getSgxq() {
		return sgxq;
	}
	public void setSgxq(String sgxq) {
		this.sgxq = sgxq;
	}
	public String getSgsj() {
		return sgsj;
	}
	public void setSgsj(String sgsj) {
		this.sgsj = sgsj;
	}
	public String getSgdd() {
		return sgdd;
	}
	public void setSgdd(String sgdd) {
		this.sgdd = sgdd;
	}
	public String getJdcsyr() {
		return jdcsyr;
	}
	public void setJdcsyr(String jdcsyr) {
		this.jdcsyr = jdcsyr;
	}
	public String getJdcglr() {
		return jdcglr;
	}
	public void setJdcglr(String jdcglr) {
		this.jdcglr = jdcglr;
	}
	public String getGajgrdyj() {
		return gajgrdyj;
	}
	public void setGajgrdyj(String gajgrdyj) {
		this.gajgrdyj = gajgrdyj;
	}
	public String getShrjzd() {
		return shrjzd;
	}
	public void setShrjzd(String shrjzd) {
		this.shrjzd = shrjzd;
	}
	public String getShrzy() {
		return shrzy;
	}
	public void setShrzy(String shrzy) {
		this.shrzy = shrzy;
	}
	public String getSftb() {
		return sftb;
	}
	public void setSftb(String sftb) {
		this.sftb = sftb;
	}
	public String getTbxz() {
		return tbxz;
	}
	public void setTbxz(String tbxz) {
		this.tbxz = tbxz;
	}
	public String getSfzbxqn() {
		return sfzbxqn;
	}
	public void setSfzbxqn(String sfzbxqn) {
		this.sfzbxqn = sfzbxqn;
	}
	public String getSfxqpf() {
		return sfxqpf;
	}
	public void setSfxqpf(String sfxqpf) {
		this.sfxqpf = sfxqpf;
	}
	public String getJdsfkk() {
		return jdsfkk;
	}
	public void setJdsfkk(String jdsfkk) {
		this.jdsfkk = jdsfkk;
	}
	public String getShangQing() {
		return shangQing;
	}
	public void setShangQing(String shangQing) {
		this.shangQing = shangQing;
	}
	public String getRealPay() {
		return realPay;
	}
	public void setRealPay(String realPay) {
		this.realPay = realPay;
	}
	public String getIdentifyContent() {
		return identifyContent;
	}
	public void setIdentifyContent(String identifyContent) {
		this.identifyContent = identifyContent;
	}
	@Override
	public String toString() {
		return "WsajjbqkModel [qsdl=" + qsdl + ", qsygscd=" + qsygscd
				+ ", qsbgbcd=" + qsbgbcd + ", qsdsryjd=" + qsdsryjd
				+ ", qszjd=" + qszjd + ", qssld=" + qssld + ", qsfsscd="
				+ qsfsscd + ", qspjd=" + qspjd + ", bsdl=" + bsdl + ", ssrscd="
				+ ssrscd + ", bssrbcd=" + bssrbcd + ", bsdsryjd=" + bsdsryjd
				+ ", bssld=" + bssld + ", bszjd=" + bszjd + ", ygscd=" + ygscd
				+ ", bgbcd=" + bgbcd + ", cmssd=" + cmssd + ", zjd=" + zjd
				+ ", dsryjd=" + dsryjd + ", fsscd=" + fsscd + ", fsbcd="
				+ fsbcd + ", xzsszyd=" + xzsszyd + ", zkdl=" + zkdl + ", bhdl="
				+ bhdl + ", xsbssld=" + xsbssld + ", xsqssld=" + xsqssld
				+ ", qscpyzypjjg=" + qscpyzypjjg + ", qscmssyzj=" + qscmssyzj
				+ ", ssssbhyj=" + ssssbhyj + ", jcjgyj=" + jcjgyj
				+ ", fdmsssqqd=" + fdmsssqqd + ", zkss=" + zkss + ", zkzj="
				+ zkzj + ", zkyj=" + zkyj + ", bgrbc=" + bgrbc + ", bhrbh="
				+ bhrbh + ", ssqqjeList=" + ssqqjeList + ", sgxq=" + sgxq
				+ ", sgsj=" + sgsj + ", sgdd=" + sgdd + ", jdcsyr=" + jdcsyr
				+ ", jdcglr=" + jdcglr + ", gajgrdyj=" + gajgrdyj + ", shrjzd="
				+ shrjzd + ", shrzy=" + shrzy + ", sftb=" + sftb + ", tbxz="
				+ tbxz + ", sfzbxqn=" + sfzbxqn + ", sfxqpf=" + sfxqpf
				+ ", shangQing=" + shangQing + ", realPay=" + realPay
				+ ", identifyContent=" + identifyContent + ", jdsfkk=" + jdsfkk
				+ "]";
	}
	
	
}
