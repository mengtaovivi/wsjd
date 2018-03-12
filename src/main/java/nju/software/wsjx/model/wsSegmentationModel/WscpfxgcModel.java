package nju.software.wsjx.model.wsSegmentationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcZdlxModel;

/**
 * ������з�������model
 * @author LXY
 *
 */
public class WscpfxgcModel {
	/**
	 * ����Model
	 */
	private ArrayList<WscpfxgcFtModel> ftModellist;
	/**
	 * �᰸��ʽ����
	 */
	private String Jafslx;
	/**
	 * �����⳥��������������
	 */
	private String xzpc;
	/**
	 * һ�󰸼���Դ��һ�󰸼�����
	 */
	private String ysajly;
	/**
	 * �Ƿ񾭹���������
	 */
	private String sfjgxzfy;
	/**
	 * ������ΪΥ������
	 */
	private String xzxwwfbj;
	/**
	 * ��ͥǰ���볷��
	 */
	private String ktqsqchss;
	
	//������أ�
	/**
	 * ��������
	 */
	private ArrayList<WscpfxgcFdlxModel> fdlxModel; 
	/**
	 * �ö�����
	 */
	private ArrayList<WscpfxgcZdlxModel> zdlxModel; 
	/**
	 * ��ͬ����
	 */
	private String gtfz;
	/**
	 * ������ͬ���������
	 */
	private String bgrtyrzcx;
	
	public String getJafslx() {
		return Jafslx;
	}
	public void setJafslx(String jafslx) {
		Jafslx = jafslx;
	}
	public String getXzpc() {
		return xzpc;
	}
	public void setXzpc(String xzpc) {
		this.xzpc = xzpc;
	}
	public String getYsajly() {
		return ysajly;
	}
	public void setYsajly(String ysajly) {
		this.ysajly = ysajly;
	}
	public String getSfjgxzfy() {
		return sfjgxzfy;
	}
	public void setSfjgxzfy(String sfjgxzfy) {
		this.sfjgxzfy = sfjgxzfy;
	}
	public String getXzxwwfbj() {
		return xzxwwfbj;
	}
	public void setXzxwwfbj(String xzxwwfbj) {
		this.xzxwwfbj = xzxwwfbj;
	}
	public String getKtqsqchss() {
		return ktqsqchss;
	}
	public void setKtqsqchss(String ktqsqchss) {
		this.ktqsqchss = ktqsqchss;
	}
	public ArrayList<WscpfxgcFdlxModel> getFdlxModel() {
		return fdlxModel;
	}
	public void setFdlxModel(ArrayList<WscpfxgcFdlxModel> fdlxModel) {
		this.fdlxModel = fdlxModel;
	}
	public ArrayList<WscpfxgcZdlxModel> getZdlxModel() {
		return zdlxModel;
	}
	public void setZdlxModel(ArrayList<WscpfxgcZdlxModel> zdlxModel) {
		this.zdlxModel = zdlxModel;
	}
	public ArrayList<WscpfxgcFtModel> getFtModellist() {
		return ftModellist;
	}
	public void setFtModellist(ArrayList<WscpfxgcFtModel> ftModellist) {
		this.ftModellist = ftModellist;
	}
	public String getGtfz() {
		return gtfz;
	}
	public void setGtfz(String gtfz) {
		this.gtfz = gtfz;
	}
	public String getBgrtyrzcx() {
		return bgrtyrzcx;
	}
	public void setBgrtyrzcx(String bgrtyrzcx) {
		this.bgrtyrzcx = bgrtyrzcx;
	}
}
