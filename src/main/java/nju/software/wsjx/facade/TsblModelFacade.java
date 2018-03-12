package nju.software.wsjx.facade;

import java.io.InputStream;
import java.util.List;

import nju.software.wsjx.model.tsblModel.TsblModel;



/**
 * ͥ���¼facade
 * @author lr12
 *
 */
public interface TsblModelFacade {

	/**
	 * ͨ������������������ͥ���¼ģ��
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public TsblModel jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * ͨ������bytes������ͥ���¼ģ��
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public TsblModel jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * ͨ�������ĵ��ַ���������ͥ���¼ģ��
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public TsblModel jxDocument(String wsnr);
	
	/**
	 * ���ݷֺöεĻ�ȡ��Ӧmodel
	 * @param paragraps
	 * @return
	 */
	public TsblModel jxDocument(List<String> paragraps);
}