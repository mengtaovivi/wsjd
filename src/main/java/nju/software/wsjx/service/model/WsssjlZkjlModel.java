package nju.software.wsjx.service.model;

import java.util.ArrayList;
/**
 * ���ϼ�¼�ε�ָ�ؼ�¼Model,���°�������
 * @author DTLXY
 *
 */
public class WsssjlZkjlModel {
	private ArrayList<String> xgr;//�����
	private ArrayList<WsssjlZkzmModel> zkzmModelist;
	public ArrayList<String> getXgr() {
		return xgr;
	}

	public void setXgr(ArrayList<String> xgr) {
		this.xgr = xgr;
	}

	public ArrayList<WsssjlZkzmModel> getZkzmModelist() {
		return zkzmModelist;
	}

	public void setZkzmModelist(ArrayList<WsssjlZkzmModel> zkzmModelist) {
		this.zkzmModelist = zkzmModelist;
	}

	
}
