package nju.software.wsjx.facade;

import java.io.InputStream;

import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsModelWrapper;

/**
 * ��¶���ⲿ��ͨ��������������byte��������ת�õ��ַ�����ȡ��Ӧ������model
 * ʹ������
 * @author lr12
 *
 */
public interface WsModelFacade {

	/**
	 * ͨ����������������������ģ��
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public WsModel jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * ͨ������bytes����������ģ��
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public WsModel jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * ͨ�������ĵ��ַ�������������ģ��
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public WsModel jxDocument(String wsnr, String wswjm);
	
	/**
	 * ͨ����������������������ģ�Ͱ�װ�࣬���������������������Ϣ
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public WsModelWrapper jxDocumentWrapper(InputStream inputStream, String wswjm);
}
