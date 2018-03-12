package nju.software.wsjx.parserule.wsssjlparserule;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.util.FileUtil;
/**
 * ����һ�����ϼ�¼��������
 * @author wangzh
 *
 */
public class MsysSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		// ��������,�������ɣ����ɴ���
		String ay = null;
		String aydm = null;
		FileUtil fileUtil = new FileUtil();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			ArrayList<String> ayList = new ArrayList<String>();
			String str = "";
			is = getClass().getClassLoader().getResourceAsStream("msaymc_aydm.txt");
			isr = new InputStreamReader(is,"utf-8");			
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				ayList.add(str);
			}
			for (int i = 0; i < ayList.size(); i++) {
				String wzay = ayList.get(i)
						.substring(5, ayList.get(i).length());
				if (wsssjl.contains(wzay)) {
					ay = wzay;
					aydm = ayList.get(i).substring(0, 4);
				}
			}
			if (ay != null && aydm != null) {
				wsssjlModel.setAy(ay);
				wsssjlModel.setWzay(ay);
				wsssjlModel.setAydm(aydm);
			} else if (wssscyrModellist != null && wsssjl != null) {
				// ����ajjbqk����һ�У�ȥ�����ţ����һ�������˵���󣨲�����һ����������
				String content = "";
				content = WsAnalyse.getContent(wsssjl);
				int prefix = 0;
				int suffix = content.indexOf("һ��");
				if (suffix != -1) {
					for (WssscyrModel model : wssscyrModellist) {
						int temp = content.indexOf(model.getSscyr());
						if (temp != -1
								&& (temp + model.getSscyr().length()) > prefix) {
							if (content.indexOf("��") == (temp + 1)) {
								prefix = temp + model.getSscyr().length() + 1;
							} else {
								prefix = temp + model.getSscyr().length();
							}
						}
					}
					ay = content.substring(prefix, suffix);
					if (ay != null) {
						wsssjlModel.setAy(ay);
						wsssjlModel.setWzay(ay);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ���ָ���ļ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// ��������һ�󰸼���Դ
		String ysajly = null;
		if (wsssjl.contains("������") || wsssjl.contains("�ٴ�����")
				|| wsssjl.contains("��������") || wsssjl.contains("�ٴ�����")
				|| wsssjl.contains("����")) {
			ysajly = "��������";
		} else if (wsssjl.contains("����") || wsssjl.contains("��������")) {
			ysajly = "�ϼ���Ժ����һ���о���������";
		} else if (wsssjl.contains("���ͱ�Ժ") || wsssjl.contains("��������Ժ")) {
			if (wsssjl.contains("�ϼ�")) {
				ysajly = "�ϼ���Ժ����";
			} else if (wsssjl.contains("�¼�")) {
				ysajly = "�¼���Ժ��������";
			} else {
				ysajly = "ͬ����Ժ����";
			}
		} else if (wsssjl.contains("�ἶ��Ͻ")) {
			ysajly = "�ἶ��Ͻ";
		} else if (wsssjl.contains("ָ����Ͻ")) {
			ysajly = "�ϼ���Ժָ����Ͻ";
		} else if (wsssjl.contains("��������") && wsssjl.contains("ָ��")) {
			ysajly = "�ϼ���Ժ����һ��������ö�ָ����������";
		} else if (wsssjl.contains("��Ͻ����") && wsssjl.contains("ָ��")) {
			ysajly = "�ϼ���Ժ����һ���Ͻ����ö�ָ���Ͻ";
		} else {
			ysajly = "����";
		}
		wsssjlModel.setYsajly(ysajly);
		// ����������֯
		// ������������
		String spzz = null;
		String drsp = null;
		if (wsssjl.contains("����ͥ")) {
			spzz = "����ͥ";
			drsp = "��";
		} else if (wsssjl.contains("����Ա")) {
			if (wsssjl.contains("��������Ա") || wsssjl.contains("����Ա")) {
				// spzz = "����ͥ";
				// drsp = "��";
			}
		} else {
			spzz = "����ͥ";
			drsp = "��";
		}
		wsssjlModel.setSpzz(spzz);
		wsssjlModel.setDrsp(drsp);
		// �����Ƿ�ͥ����
		String ktsl = "��";
		String ktslxx = null;
		String bgkslyy = "����";
		if ((wsssjl.contains("��ͥ") || wsssjl.contains("����ͥ"))
				&& wsssjl.contains("����")&&!wsssjl.contains("����ͥ")) {
			ktsl = "��";
			if (wsssjl.contains("������")) {
				ktslxx = "����������";
				if (wsssjl.contains("���")) {
					bgkslyy = "���������벻�����������鰸��";
				} else if (wsssjl.contains("δ����")) {
					bgkslyy = "���������벻����������漰δ���갸��";
				} else if (wsssjl.contains("��ҵ����")) {
					bgkslyy = "���������벻����������漰��ҵ���ܰ���";
				} else if (wsssjl.contains("��˽")) {
					bgkslyy = "���������벻����������漰������˽�İ���";
				} else if (wsssjl.contains("��������")) {
					bgkslyy = "�漰�������ܰ���";
				}
			} else {
				ktslxx = "��������";
			}
		}
		wsssjlModel.setKtsl(ktsl);
		wsssjlModel.setKtslxx(ktslxx);
		wsssjlModel.setBgkslyy(bgkslyy);

		// ������ͥ����,��������,��������,���볷������
		String larq = null;
		String slrq = null;
		String sqcsrq = null;
		ArrayList<String> ktrq = new ArrayList<String>();
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("����")
					|| contentArray[i].contains("����")) {
				larq = getDate(contentArray[i]);
				if (contentArray[i].contains("����")) {
					slrq = getDate(contentArray[i]);
				}
			} else if (contentArray[i].contains("��ͥ")) {
				ktrq.add(getDate(contentArray[i]));
			} else if (contentArray[i].contains("���볷")
					|| contentArray[i].contains("��������")
					|| contentArray[i].contains("�����")
					|| contentArray[i].contains("�������")) {
				sqcsrq = getDate(contentArray[i]);
			} else {
				ktrq.add(getDate(contentArray[i]));
			}
		}
		if (larq != null) {
			wsssjlModel.setLarq(larq);
		}
		if (ktrq != null) {
			wsssjlModel.setKtrq(ktrq);
		}
		if (slrq != null) {
			wsssjlModel.setSlrq(slrq);
		}
		if (sqcsrq != null) {
			wsssjlModel.setSqcsrq(sqcsrq);
		}
		// ���������漰
		String ajsj = null;
		if (wsssjl.contains("ũ�񹤹���")) {
			ajsj = "��ũ�񹤹���";
		} else if (wsssjl.contains("���ز���")) {
			ajsj = "�����ز���";

		} else if (wsssjl.contains("��Ǩ���ò���")) {
			ajsj = "���Ǩ���ò���";

		} else if (wsssjl.contains("��Ʒ����")) {
			ajsj = "���Ʒ����";

		} else if (wsssjl.contains("������Ⱦ")) {
			ajsj = "�滷����Ⱦ";

		} else if (wsssjl.contains("ҽԺ")) {
			ajsj = "��ҽ�ƾ���";

		} else if (wsssjl.contains("��ͬ��թ")) {
			ajsj = "���ͬ��թ";

		} else if (wsssjl.contains("������թ")) {
			ajsj = "�������թ";

		} else if (wsssjl.contains("������թ")) {
			ajsj = "�汣����թ";

		} else if (wsssjl.contains("������թ")) {
			ajsj = "�������թ";

		} else if (wsssjl.contains("������")) {
			ajsj = "�������";

		} else if (wsssjl.contains("֪ʶ��Ȩ")) {
			ajsj = "��֪ʶ��Ȩ";

		} else if (wsssjl.contains("���º���")) {
			ajsj = "�溣�º���";

		} else if (wsssjl.contains("WTO����")) {
			ajsj = "��WTO����";

		} else if (wsssjl.contains("����")) {
			ajsj = "���";

		}
		wsssjlModel.setAjsj(ajsj);
		// ����һ�󰸼����ó���
		String ysajsycx = "��ͨ����";
		String jyzpt = "��";
		if (wsssjl.contains("���׳���") || wsssjl.contains("С�����ϳ���")) {
			if (wsssjl.contains("�������ü���")) {
				ysajsycx = "��ͨ����";
			} else {
				ysajsycx = "���׳���";
			}
		} else if (wsssjl.contains("תΪ��ͨ") || wsssjl.contains("ת����ͨ")) {
			ysajsycx = "��ͨ����";
			jyzpt = "��";
		}
		wsssjlModel.setYsajsycx(ysajsycx);
		wsssjlModel.setJyzpt(jyzpt);
		// ������ͥ��δ��ͥ��������Ϣ
		String[] content = wsssjl.split("\\.|��|;|��");
		// ȱϯ����Ϣ����ͥ����Ϣmap<�������������>
		HashMap<String, String> qxrmap = new HashMap<String, String>();
		HashMap<String, String> ctrmap = new HashMap<String, String>();
		ArrayList<String> qxgjc = new ArrayList<String>();// ȱϯ�ؼ���
		qxgjc.add("δ��");
		qxgjc.add("û�е�");
		qxgjc.add("�ܲ���");
		int index = -1;
		if (wsssjlModel.getKtsl() == "��") {
			int beginIndex = wsssjl.indexOf("һ��");
			if (wsssjl.contains("��ͥ") && beginIndex != -1) {
				for (int i = 0; i < qxgjc.size(); i++) {
					if (wsssjl.contains(qxgjc.get(i))) {
						index = wsssjl.lastIndexOf(qxgjc.get(i));
						// ���мȳ����ѳ�ͥ����Ϣ��Ҳ����δ��ͥ����Ϣ
						if (index != -1) {
							// ��ͥ����Ϣ��ǰ��δ��ͥ����Ϣ�ں�
							if (wsssjl.indexOf("��ͥ") < index) {
								for (WssscyrModel model : wssscyrModellist) {
									if(model.getSscyr()!=null){
										if (wsssjl.substring(beginIndex,
												wsssjl.indexOf("��ͥ")).contains(
														model.getSscyr())) {
											if (!ctrmap.containsKey((model
													.getSscyr()))) {
												ctrmap.put(model.getSscyr(),
														model.getSssf());
											}
										} else if (wsssjl.substring(
												wsssjl.substring(0, index)
												.lastIndexOf("��ͥ"), index)
												.contains(model.getSscyr())) {
											qxrmap.put(model.getSscyr(),
													model.getSssf());
										}
									}
								}
							}
							// δ��ͥ����Ϣ��ǰ����ͥ����Ϣ�ں�
							else {
								for (WssscyrModel model : wssscyrModellist) {
									if(model.getSscyr()!=null){
										if (wsssjl.substring(beginIndex, index)
												.contains(model.getSscyr())) {
											if (!ctrmap.containsKey((model
													.getSscyr()))) {
												ctrmap.put(model.getSscyr(),
														model.getSssf());
											}
										} else if (wsssjl.substring(index,
												wsssjl.lastIndexOf("��ͥ")).contains(
														model.getSscyr())) {
											qxrmap.put(model.getSscyr(),
													model.getSssf());
										}
									}
								}
							}
						}
					}
				}
				// ����ֻ�����ѳ�ͥ����Ϣ��û�г���δ��ͥ����Ϣ
				if (index == -1) {
					for (WssscyrModel model : wssscyrModellist) {
						if(model.getSscyr()!=null){
							if (wsssjl.substring(beginIndex,
									wsssjl.lastIndexOf("��ͥ")).contains(
									model.getSscyr())) {
								if (!ctrmap.containsKey((model.getSscyr()))) {
									ctrmap.put(model.getSscyr(), model.getSssf());
								}
							}
						}
					}
				}
			} else if (beginIndex != -1) {
				// ����ֻ����δ��ͥ����Ϣ��û�г����ѳ�ͥ����Ϣ
				for (int i = 0; i < qxgjc.size(); i++) {
					if (wsssjl.contains(qxgjc.get(i))) {
						index = wsssjl.lastIndexOf(qxgjc.get(i));
						for (WssscyrModel model : wssscyrModellist) {
							if(model.getSscyr()!=null){
								if (wsssjl.substring(beginIndex, index).contains(
										model.getSscyr())) {
									qxrmap.put(model.getSscyr(), model.getSssf());
								}
							}
						}
					}
				}
			}
		}
		wsssjlModel.setQxrxx(qxrmap);
		wsssjlModel.setCtrxx(ctrmap);

		return wsssjlModel;
	}

}
