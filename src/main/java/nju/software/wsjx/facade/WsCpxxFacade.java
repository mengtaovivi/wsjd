package nju.software.wsjx.facade;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.facadeModel.WsxxModel;

/**
 * �ṩ���ⲿ����ȡ�������ɡ��������ݡ����н��
 * @author lr12
 *
 */
public interface WsCpxxFacade {
	/**
	 * ����wsnr�������ļ�����ȡ������ϢList
	 * �ֱ�Ϊ�������ɡ��������ݡ�������
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public String getCpxx(byte[] wsnr, String wswjm);
	
}
