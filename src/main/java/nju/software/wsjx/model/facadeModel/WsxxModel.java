package nju.software.wsjx.model.facadeModel;

import java.io.Serializable;
import java.util.List;

/**
 * ������Ϣ
 * @author lr12
 *
 */
@SuppressWarnings("serial")
public class WsxxModel implements Serializable{

	
	/**
	 * ��������
	 */
	private String cply;
	
	/**
	 * ��������
	 */
	private String cpyj;
	
	/**
	 * ���н��
	 */
	private List<String> cpjg;

	public String getCply() {
		return cply;
	}

	public void setCply(String cply) {
		this.cply = cply;
	}

	public String getCpyj() {
		return cpyj;
	}

	public void setCpyj(String cpyj) {
		this.cpyj = cpyj;
	}

	public List<String> getCpjg() {
		return cpjg;
	}

	public void setCpjg(List<String> cpjg) {
		this.cpjg = cpjg;
	}

	@Override
	public String toString() {
		return "WsxxModel [cply=" + cply + ", cpyj=" + cpyj + ", cpjg=" + cpjg
				+ "]";
	}

	
	
}
