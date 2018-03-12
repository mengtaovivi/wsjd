package nju.software.wsjx.facade;

import java.io.InputStream;

import nju.software.wsjx.model.caseinfo.BaseCaseInfo;

/**
 * ��¶���ⲿ��ͨ��������������byte��������ת�õ��ַ�����ȡ��Ӧ���ĵ�
 * ʹ������
 * @author lr12
 *
 */
public interface CaseInfoFacade {

	/**
	 * ͨ������������������һƪ�ĵ�
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public BaseCaseInfo jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * ͨ������bytes������һƪ�ĵ�
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public BaseCaseInfo jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * ͨ�������ĵ��ַ���������һƪ�ĵ�
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public BaseCaseInfo jxDocument(String wsnr, String wswjm);
}
