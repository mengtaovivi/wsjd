package nju.software.wsjx.model.caseinfo;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

import nju.software.wsjx.parserule.wswsparserule.WsParseRule;

import java.sql.Timestamp;

/**
 * �ĵ��ĸ���
 * @author lr12
 *
 */
public abstract class BaseCaseInfo {

	 String wdbs;//�ĵ���ʶ
	 String ah;//����
	 String fy;//��Ժ
	 String ajxz;//��������
	 String spcx;//���г���
	 String zay;//������
	 String ajly;//������Դ
	 String ktsl;//��ͥ����
	 String dtxp;//��ͥ����
	 String jafs;//�᰸��ʽ
	 Timestamp larq;//��������
	 Timestamp jarq;//�᰸����
	 String spy;//����Ա
	 String sjy;//���Ա
	
	public String getWdbs() {
		return wdbs;
	}

	public void setWdbs(String wdbs) {
		this.wdbs = wdbs;
	}

	public String getAh() {
		return ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	public String getFy() {
		return fy;
	}

	public void setFy(String fy) {
		this.fy = fy;
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

	public String getZay() {
		return zay;
	}

	public void setZay(String zay) {
		this.zay = zay;
	}

	public String getAjly() {
		return ajly;
	}

	public void setAjly(String ajly) {
		this.ajly = ajly;
	}

	public String getJafs() {
		return jafs;
	}

	public void setJafs(String jafs) {
		this.jafs = jafs;
	}

	//���� �������Լ��ķ���ί�и��Լ������ݸ��������model����Լ�����
	public abstract void generate(WsModel wsModel);
	
}
