package nju.software.wsjx.service.jtsg;

import java.util.List;

public interface CmssdpreService {
	/**
	 * ��ȡ�¹�����
	 */
	public String getSgxq(List<String> cmssd);
	/**
	 * ��ȡ�¹�ʱ��
	 */
	public String getSgsj(String sgxq);
	/**
	 * ��ȡ�¹ʵص�
	 */
	public String getSgdd(String sgxq);
	/**
	 * ��ȡ�¹ʾ���
	 */
	public String getSgjg(String sgxq);
	/**
	 * ������������
	 */
	public String getJdcsyr(List<String> cmssd, String sgxq, List<String> bglist);
	/**
	 * ������������
	 */
	public String getJdcglr(List<String> cmssd, String sgxq, List<String> bglist);
	/**
	 * ���������϶����
	 */
	public String getGajgrdyj(List<String> cmssd);
	/**
	 * �ܺ��˾�ס��
	 */
	public String getShrjzd(List wssscyrModellist);
	/**
	 * �ܺ���ְҵ
	 */
	public String getShrzy(List wssscyrModellist);
}
