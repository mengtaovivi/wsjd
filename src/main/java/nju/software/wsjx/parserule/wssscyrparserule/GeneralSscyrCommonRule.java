package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.Enum.TshyEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.StringUtil;
/**
 * ���ϲ����˵�ͨ�÷���
 * @author wangzh
 *
 */
public class GeneralSscyrCommonRule {
	public void setDsrgj(WssscyrModel wssscyrModel) {
		String dsrgj = null;
		String dsrmc = wssscyrModel.getSscyr();//����������
		String allinfo = wssscyrModel.getSscyrallinfo();
		if (allinfo.contains("����")&&allinfo.contains("����")){
			dsrgj="�й�����";
		}else if(allinfo.contains("���")&&allinfo.contains("����")){
			dsrgj="�й����";
		}else if (allinfo.contains("���ô�")&&allinfo.contains("����")){
			dsrgj="���ô�";
		}else if (allinfo.contains("��������ڹ�")&&allinfo.contains("����")||allinfo.contains("������")||allinfo.contains("������")&&allinfo.contains("����")){
			dsrgj="����";
		}else if (allinfo.contains("�����")&&allinfo.contains("����")){
			dsrgj="����";
		}else if (allinfo.contains("������")&&allinfo.contains("����")){
			dsrgj="������";
		}else if (allinfo.contains("�Ĵ�����")&&allinfo.contains("����")){
			dsrgj="�Ĵ�����";
		}else if (dsrmc!=null&&dsrmc.length()<5){
			dsrgj = "�й�";
		}
		wssscyrModel.setGj(dsrgj);
	}

}
