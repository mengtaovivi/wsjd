package nju.software.wsjx.parse.tsbl;

import java.util.List;
import java.util.Map;

/**
 * ����ͥ���¼�̶��߼�����
 * @author lr12
 *
 */
public interface ParseTsblFixedSegment {

	//�����̶����߼�
	public Map<String, String> parseFixedSegment(List<String> paragraphs);
	
}
