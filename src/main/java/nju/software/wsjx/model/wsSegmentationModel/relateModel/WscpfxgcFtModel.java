package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.HashMap;

public class WscpfxgcFtModel {
	/**
	 * ���ɷ�������
	 */
	private String flftmc;
	/**
	 * ��������Map<��Ŀ����Ŀ>
	 */
	private HashMap<String,String> ftMap;
	private String sfcxf;//�Ƿ��ǳ��� ��Ͻ
	public String getFlftmc() {
		return flftmc;
	}
	public void setFlftmc(String flftmc) {
		this.flftmc = flftmc;
	}
	public HashMap<String,String> getFtMap() {
		return ftMap;
	}
	public void setFtMap(HashMap<String,String> ftMap) {
		this.ftMap = ftMap;
	}

	public String getSfcxf() {
		return sfcxf;
	}

	public void setSfcxf(String sfcxf) {
		this.sfcxf = sfcxf;
	}
}
