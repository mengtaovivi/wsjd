package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.QkqkModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;

/**
 * �������ϲ�����model
 * @author lr12
 *
 */
public class WssscyrModel {
	private String sscyrallinfo;//���ϲ�����������Ϣ
	private String sscyr;//���ϲ���������
	private String sssf;//�������
	private String dsrlx;//����������
	private String mz;//����
	private String csrq;//��������
	private String zjlx;//֤������
	private String zjhm;//֤������
	private String dsrdz;//�����˵�ַ
	private String dsrzw;//������ְ��
	private String zrrsf;//��Ȼ�����
	private String dsrwhcd;//�������Ļ��̶�
	private String dsrxw;//������ѧλ
	private String xb;//�Ա�
	private String dtqk;//��ͥ���
	private String gj;//����
	private String year;
	private String month;
	private String day;
	private String ssdw;//���ϵ�λ
	private String ysssdw;//���ϵ�λ
	private String esssdw;//�������ϵ�λ
	private String dsrlb;//���������
	private String dwxz;//��λ����
	private String fddbr;//��λ����������
	private String gzdw;//������λ
	private String gzdwxz;//������λ����
	private String dsrsfzh;//�������Ƿ��ٻ�
	private String tshy;//������ҵ
	private String xzfagxzt;//�������ɹ�ϵ����
	private String bglx;//��������
	private String zzjgdm;//��֯��������
	private String xzglfw;//��������Χ
	private String zzmm;//������ò
	private String hjd;//������
	private String isBhr;//�Ƿ񱻺���
	private String msssygrlx;//��������ԭ��������
	private String xszrablity;//������������
	private String hxkyqfz;//���̿������ڷ���
	private String jskyqfz;//���Ϳ������ڷ���
	private String xjycs;//���Ѻ����
	private List<QzcsModel> qzcsList;//ǿ�ƴ�ʩModel
	private List<QkqkModel> qkqkList;//ǰ�����Model
//	��ͨ�¹�
//	private boolean jqxgsSfbg;//��ǿ�չ�˾�Ƿ���Ϊ����
//	private boolean sysxgsSfg;//��ҵ���չ�˾�Ƿ���Ϊ����
	private int peichangIndex;//�⳥˳��
	private String jtsgzr;//��ͨ�¹�����
	private String sfcdpczr;//�Ƿ�е��⳥����

	private String xgsscyr;//������ϲ�����
	public List<QkqkModel> getQkqkList() {
		return qkqkList;
	}

	public void setQkqkList(List<QkqkModel> qkqkList) {
		this.qkqkList = qkqkList;
	}

	public List<QzcsModel> getQzcsList() {
		return qzcsList;
	}

	public void setQzcsList(List<QzcsModel> qzcsList) {
		this.qzcsList = qzcsList;
	}

	public String getXjycs() {
		return xjycs;
	}

	public void setXjycs(String xjycs) {
		this.xjycs = xjycs;
	}

	public String getJskyqfz() {
		return jskyqfz;
	}

	public void setJskyqfz(String jskyqfz) {
		this.jskyqfz = jskyqfz;
	}

	public String getHxkyqfz() {
		return hxkyqfz;
	}

	public void setHxkyqfz(String hxkyqfz) {
		this.hxkyqfz = hxkyqfz;
	}

	public String getXszrablity() {
		return xszrablity;
	}

	public void setXszrablity(String xszrablity) {
		this.xszrablity = xszrablity;
	}

	public String getMsssygrlx() {
		return msssygrlx;
	}

	public void setMsssygrlx(String msssygrlx) {
		this.msssygrlx = msssygrlx;
	}

	public String getIsBhr() {
		return isBhr;
	}

	public void setIsBhr(String isBhr) {
		this.isBhr = isBhr;
	}

	public String getHjd() {
		return hjd;
	}

	public void setHjd(String hjd) {
		this.hjd = hjd;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getZrrsf() {
		return zrrsf;
	}

	public void setZrrsf(String zrrsf) {
		this.zrrsf = zrrsf;
	}

	public String getDsrxw() {
		return dsrxw;
	}

	public void setDsrxw(String dsrxw) {
		this.dsrxw = dsrxw;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getXzglfw() {
		return xzglfw;
	}

	public void setXzglfw(String xzglfw) {
		this.xzglfw = xzglfw;
	}

	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getBglx() {
		return bglx;
	}
	public void setBglx(String bglx) {
		this.bglx = bglx;
	}
	public String getXzfagxzt() {
		return xzfagxzt;
	}
	public void setXzfagxzt(String xzfagxzt) {
		this.xzfagxzt = xzfagxzt;
	}
	public String getSscyrallinfo() {
		return sscyrallinfo;
	}
	public void setSscyrallinfo(String sscyrallinfo) {
		this.sscyrallinfo = sscyrallinfo;
	}
	public String getTshy() {
		return tshy;
	}
	public void setTshy(String tshy) {
		this.tshy = tshy;
	}
	public String getDsrsfzh() {
		return dsrsfzh;
	}
	public void setDsrsfzh(String dsrsfzh) {
		this.dsrsfzh = dsrsfzh;
	}
	public String getSsdw() {
		return ssdw;
	}
	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}
	public String getYsssdw() {
		return ysssdw;
	}
	public void setYsssdw(String ysssdw) {
		this.ysssdw = ysssdw;
	}
	public String getEsssdw(){
		return esssdw;
	}
	public void setEsssdw(String esssdw){
		this.esssdw = esssdw;
	}
	public String getDsrlb() {
		return dsrlb;
	}
	public void setDsrlb(String dsrlb) {
		this.dsrlb = dsrlb;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}


	public String getDtqk() {
		return dtqk;
	}
	public void setDtqk(String dtqk) {
		this.dtqk = dtqk;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getSscyr() {
		return sscyr;
	}
	public void setSscyr(String sscyr) {
		this.sscyr = sscyr;
	}
	public String getSssf() {
		return sssf;
	}
	public void setSssf(String sssf) {
		this.sssf = sssf;
	}
	public String getDsrlx() {
		return dsrlx;
	}
	public void setDsrlx(String dsrlx) {
		this.dsrlx = dsrlx;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getDsrdz() {
		return dsrdz;
	}
	public void setDsrdz(String dsrdz) {
		this.dsrdz = dsrdz;
	}
	public String getDsrzw() {
		return dsrzw;
	}
	public void setDsrzw(String dsrzw) {
		this.dsrzw = dsrzw;
	}
	public String getDsrwhcd() {
		return dsrwhcd;
	}
	public void setDsrwhcd(String dsrwhcd) {
		this.dsrwhcd = dsrwhcd;
	}
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getGzdw() {
		return gzdw;
	}
	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}
	public String getGzdwxz() {
		return gzdwxz;
	}
	public void setGzdwxz(String gzdwxz) {
		this.gzdwxz = gzdwxz;
	}


	public int getPeichangIndex() {
		return peichangIndex;
	}

	public void setPeichangIndex(int peichangIndex) {
		this.peichangIndex = peichangIndex;
	}

	public String getJtsgzr() {
		return jtsgzr;
	}

	public void setJtsgzr(String jtsgzr) {
		this.jtsgzr = jtsgzr;
	}

	public String getSfcdpczr() {
		return sfcdpczr;
	}

	public void setSfcdpczr(String sfcdpczr) {
		this.sfcdpczr = sfcdpczr;
	}

	public String getXgsscyr() {
		return xgsscyr;
	}

	public void setXgsscyr(String xgsscyr) {
		this.xgsscyr = xgsscyr;
	}
	
}
