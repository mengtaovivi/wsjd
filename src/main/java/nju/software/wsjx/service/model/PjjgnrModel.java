package nju.software.wsjx.service.model;

import java.util.List;
import java.util.Map;

public class PjjgnrModel {
	String pjjgnr;
	Map<String,String> qlr;
	Map<String,String> ywr;
	private String pjzxqx;//�о�ִ������
	private String pjzrcdfs;//�о����γе���ʽ
//	private List<WsCpjgssfjeModel> pjjeList;
	private List<PjjeModel> pjjeList;//�о����
	
	public PjjgnrModel() {
		super();
	}
	public PjjgnrModel(String pjjgnr, Map<String, String> qlr,
			Map<String, String> ywr) {
		super();
		this.pjjgnr = pjjgnr;
		this.qlr = qlr;
		this.ywr = ywr;
	}
	public PjjgnrModel(String pjjgnr) {
		this.pjjgnr = pjjgnr;
	}
	public String getPjjgnr() {
		return pjjgnr;
	}
	public void setPjjgnr(String pjjgnr) {
		this.pjjgnr = pjjgnr;
	}
	public Map<String, String> getQlr() {
		return qlr;
	}
	public void setQlr(Map<String, String> qlr) {
		this.qlr = qlr;
	}
	public Map<String, String> getYwr() {
		return ywr;
	}
	public void setYwr(Map<String, String> ywr) {
		this.ywr = ywr;
	}
	public String getPjzxqx() {
		return pjzxqx;
	}
	public void setPjzxqx(String pjzxqx) {
		this.pjzxqx = pjzxqx;
	}
	public String getPjzrcdfs() {
		return pjzrcdfs;
	}
	public void setPjzrcdfs(String pjzrcdfs) {
		this.pjzrcdfs = pjzrcdfs;
	}
	public List<PjjeModel> getPjjeList() {
		return pjjeList;
	}
	public void setPjjeList(List<PjjeModel> pjjeList) {
		this.pjjeList = pjjeList;
	}

}
