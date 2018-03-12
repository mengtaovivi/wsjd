package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
/**
 * ���¶�����з������̽���
 * @author wangzh
 *
 */
public class MsesCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		List<String> cpfxgc = wsAnalyse.getCpfxgc();
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(cpfxgc
				.get(cpfxgc.size() - 1));
		int index = 0;
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("����")) {
				index = i;
				break;
			} else if (contentlist.get(i).contains("����")
					|| contentlist.get(i).contains("����")
					|| contentlist.get(i).contains("����")) {
				index = i;
			}
		}
		// ������������
		String ftString = "";// ����
		for (int j = index; j < contentlist.size(); j++) {
			ftString += contentlist.get(j);
		}
		ftString = delUrl(ftString);
		String[] ftfz = ftString.split("��");
		//ɾ���������һ������Ĺ涨���ַ�
		ftfz[ftfz.length-1] = delGd(ftfz[ftfz.length-1]);
		ArrayList<WscpfxgcFtModel> ftModellist = new ArrayList<WscpfxgcFtModel>();
		wscpfxgcModel.setFtModellist(ftModellist);
		for (int j = 0; j < ftfz.length; j++) {
			String content = ftfz[j];
			if (content.indexOf("��") != -1) {
				WscpfxgcFtModel ftModel = new WscpfxgcFtModel();
				String flftmc = content.substring(0, content.indexOf("��"));
				ftModel.setFlftmc(flftmc);
				//��ȡ��Ŀ��Ŀ
				ftModel.setFtMap(getTmkm(content));
				wscpfxgcModel.getFtModellist().add(ftModel);
			}
		}
		// �᰸��ʽ����
		String lastContent = contentlist.get(contentlist.size() - 1);
		if (lastContent != null) {
			int jaindex = lastContent.indexOf("����");
			if (jaindex != -1) {
				String jafslx = lastContent.substring(jaindex - 2, jaindex);
				wscpfxgcModel.setJafslx(jafslx);
			}
		}
		return wscpfxgcModel;
	}


}
