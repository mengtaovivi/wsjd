package nju.software.wsjx.service.jtsg;

import java.util.List;

/**
 * ����Ͷ�����
 * @author LXY
 *
 */
public interface CltbqkService {
	/**
	 * �Ƿ�Ͷ��
	 * @param cmss
	 * @return
	 */
	public boolean isToubao(List<String> cmsslist);
	/**
	 * Ͷ������
	 * @param cmss
	 * @return
	 */
	public String touBaoXZ(List<String> cmsslist);
	/**
	 * �Ƿ��ڱ�������
	 * @param cmss
	 * @return
	 */
	public boolean isValid(List<String> cmsslist);
	/**
	 * �Ƿ������⸶
	 * @param cmss
	 * @return
	 */
	public boolean isPay(List<String> cmsslist);
	/**
	 * �����Ƿ����
	 * @param cmss
	 * @return
	 */
	public boolean identifyIsTrue(List<String> cmsslist);
	/**
	 * �������
	 * @param cmsslist
	 * @return
	 */
	public String getShangQing(List<String> cmsslist);
	/**
	 * ʵ��֧�����
	 * @param cmsslist
	 * @return
	 */
	public String getRealPay(List<String> cmsslist);
	/**
	 * ��ؼ���
	 * @param cmsslist
	 * @return
	 */
	public String getIdentifyContent(List<String> cmsslist);
}
