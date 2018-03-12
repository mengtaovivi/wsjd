package nju.software.wsjx.service;

import java.util.List;




import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.service.model.WsfdModel;


/**
 * �����ȡ�İ�����Ϣģ��
 * @author lr12
 *
 */
public interface CaseModelByWs {

	
	
	/**
	 * �Ƿ���������
	 * @return
	 */
	public boolean isWzws();

	/**
	 * ��������ֶ�����
	 * @param wsnr
	 * @return
	 */
	public WsfdModel jxWsfdModel(String wsnr, List<String> ws, List<String> sscyr,
                                 String ssjl, List<String> ajjbqk, List<String> cpfxgc
            , List<String> cpjg, List<String> ww, List<String> fl);
	
	/**
	 * ����������������
	 * @param ws
	 * @return
	 */
	public WswsModel jxWswsModel(List<String> ws);
	
	/**
	 * �����������ϲ���������
	 * @param sscyr
	 * @return
	 */
	public List<WssscyrModel> jxWssscyrModelList(List<String> sscyr, List<String> ajjbqk);
	
	/**
	 * �������鰸�������������
	 * @param ajjbqk
	 * @return
	 */
	public List<WsajjbqkModel> jxWsajjbqkModel(List<String> ajjbqk);
	
	/**
	 * �������з�����������
	 * @param cpfxgc
	 * @return
	 */
	public WscpfxgcModel jxWscpfxgcModel(List<String> cpfxgc);
	
	/**
	 * �������н������
	 * @param cpjg
	 * @return
	 */
	public WscpjgModel jxWscpjgModel(List<String> cpjg);
	
	/**
	 * ����������β����
	 * @param wsww
	 * @return
	 */
	public WswwModel jxWswwModel(List<String> wsww);
	
}
