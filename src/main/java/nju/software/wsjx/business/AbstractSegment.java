package nju.software.wsjx.business;

import java.util.List;
import java.util.Map;

/**
 * ����ֶ�
 * @author lr12
 *
 */
public interface AbstractSegment {

	
	/**
	 * ��ʼ��
	 * @param wsnr
	 * @return
	 */
	public List<String> inint(String wsnr);
	
	/**
	 * �ֶ�
	 * @param paragraphs
	 * @return
	 */
	public Map<String, List<String>> segment(List<String> paragraphs);
}
