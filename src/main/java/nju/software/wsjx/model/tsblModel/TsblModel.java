package nju.software.wsjx.model.tsblModel;

import java.util.Map;
import nju.software.wsjx.model.ModelInterface;

/**
 * ͥ���¼model
 * @author lr12
 *
 */
public class TsblModel implements ModelInterface{

	protected String ktsj;//��ͥʱ��
	protected String ktdd;//��ͥ�ص�
	protected String wsmc;//��������
	public String getKtsj() {
		return ktsj;
	}
	public void setKtsj(String ktsj) {
		this.ktsj = ktsj;
	}
	public String getKtdd() {
		return ktdd;
	}
	public void setKtdd(String ktdd) {
		this.ktdd = ktdd;
	}
	public String getWsmc() {
		return wsmc;
	}
	public void setWsmc(String wsmc) {
		this.wsmc = wsmc;
	}
	@Override
	public Map<String, Object> TransformToDataMap() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
