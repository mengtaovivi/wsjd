package nju.software.wsjx.facade;

import java.io.InputStream;

import nju.software.wsjx.model.ModelInterface;


/**
 * modelInterfaceFacade
 * @author lr12
 *
 */
public interface ModelInterfaceFacade {



	/**
	 * ͨ������������������modelInterface
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public ModelInterface jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * ͨ������bytes������modelInterface
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public ModelInterface jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * ͨ�������ĵ��ַ���������modelInterface
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public ModelInterface jxDocument(String wsnr);
	
}
