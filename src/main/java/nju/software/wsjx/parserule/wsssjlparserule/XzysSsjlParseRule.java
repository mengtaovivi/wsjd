package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

/**
 * ����һ�����ϼ�¼����
 * @author wangzh
 *
 */
public class XzysSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

	/**
	 * hufk
	 * @param wssscyrModellist
	 * @param wsssjl �������ϼ�¼
	 * @return
	 */
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		String[] Arraycontent = wsssjl.split("\\.|��|;|��");
		// ������������
		String ay = null;
		if (wssscyrModellist != null && wsssjl != null) {
			// ����ssjl����һ�У�ȥ�����ţ����һ�������˵���󣨲�����һ����������
			String content = "";
			content = WsAnalyse.getContent(wsssjl);
		//	System.out.println(wsssjl);
			int prefix = 0;
			int suffix = content.indexOf("һ��");
		    suffix=suffix==-1?content.length():suffix;
			if (suffix != -1) {
				for (WssscyrModel model : wssscyrModellist) {
					if (model.getSscyr() != null) {
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
				}
				if (prefix < suffix) {
					ay = content.substring(prefix, suffix);
				}
				if (ay != null) {
					wsssjlModel.setAy(ay);
				}
			//	System.out.println(wsssjlModel.getAy());
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
		String qsrq = null;
		String slrq = null;
		String sqcsrq = null;
		ArrayList<String> ktrq = new ArrayList<String>();
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("����")
					|| contentArray[i].contains("����")
					|| contentArray[i].contains("����")) {
				qsrq = getDate(contentArray[i]);
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
		if (qsrq != null) {
			wsssjlModel.setQsrq(qsrq);
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

		// ����һ�󰸼����ó���
		// System.out.println(wsssjl);
		String ysajsycx = "��ͨ����";
		String jyzpt = "��";
		if (wsssjl.contains("���׳���") || wsssjl.contains("С�����ϳ���")) {
			if (wsssjl.contains("�������ü���")) {
				ysajsycx = "��ͨ����";
			} else {
				ysajsycx = "���׳���";
			}
		} else if (wsssjl.contains("תΪ��ͨ")) {
			ysajsycx = "��ͨ����";
			jyzpt = "��";
		}
		wsssjlModel.setYsajsycx(ysajsycx);
		wsssjlModel.setJyzpt(jyzpt);

		// ������ͥ��δ��ͥ��������Ϣ
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
									if (model.getSscyr() != null) {
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
														.lastIndexOf("��ͥ"),
												index).contains(
												model.getSscyr())) {
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
						if (model.getSscyr() != null) {
							if (wsssjl.substring(beginIndex,
									wsssjl.lastIndexOf("��ͥ")).contains(
									model.getSscyr())) {
								if (!ctrmap.containsKey((model.getSscyr()))) {
									ctrmap.put(model.getSscyr(),
											model.getSssf());
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
							if (model.getSscyr() != null) {
								if (wsssjl.substring(beginIndex, index)
										.contains(model.getSscyr())) {
									qxrmap.put(model.getSscyr(),
											model.getSssf());
								}
							}
						}
					}
				}
			}
		}
		wsssjlModel.setQxrxx(qxrmap);
		wsssjlModel.setCtrxx(ctrmap);

		// ��������һ��������Ϊ����
		String xzxwzl = null;
		if (wsssjl.contains("ȷ��") || wsssjl.contains("�϶�")) {
			xzxwzl = "����ȷ��";
		} else if (wsssjl.contains("��������")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("��������")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("����")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("����")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("����")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("�Ǽ�")) {
			xzxwzl = "�����Ǽ�";
		} else if (wsssjl.contains("�ල") || wsssjl.contains("���")) {
			xzxwzl = "�����ල";
		} else if (wsssjl.contains("����")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("�þ�")
				|| (wsssjl.contains("����") && wsssjl.contains("����"))) {
			xzxwzl = "�����þ�";
		} else if (wsssjl.contains("ǿ��") || wsssjl.contains("ǿ��")
				|| wsssjl.contains("���")) {
			xzxwzl = "����ǿ��";
		} else if (wsssjl.contains("���") || wsssjl.contains("��Ȩ֤")
				|| wsssjl.contains("��ʵ֤")) {
			xzxwzl = "�������";
		} else if (wsssjl.contains("��׼") || wsssjl.contains("����")) {
			xzxwzl = "������׼";
		} else if (wsssjl.contains("��������")) {
			xzxwzl = "��������";
		} else if (wsssjl.contains("����") || wsssjl.contains("���ϱ��մ���")) {
			xzxwzl = "��������";
		} else {
			xzxwzl = "����������Ϊ";
		}
		wsssjlModel.setXzxwzl(xzxwzl);

		String xzqqxwzl = null;
		if (wsssjl.contains("������") || wsssjl.contains("����Ϊ")) {
			xzqqxwzl = "��������Ϊ";
		} else {
			xzqqxwzl = "��������Ϊ";
		}
		wsssjlModel.setXzqqxwzl(xzqqxwzl);

		// ����������Ҫ�쵼��ͥ
		String bgzyldct = "��";
		for (int j = 0; j < Arraycontent.length; j++) {
			if (!Arraycontent[j].contains("δ��ͥ")
					&& !Arraycontent[j].contains("δ��ͥ")
					&& (Arraycontent[j].contains("��ͥ") || Arraycontent[j]
							.contains("��ͥ"))) {
				String[] content = Arraycontent[j].split(",|��|��");
				for (int i = 0; i < content.length; i++) {
					if (content[i].contains("����")
							&& (content[i].contains("��������")
									|| content[i].contains("վ��")
									|| content[i].contains("����")
									|| content[i].contains("�ֳ�")
									|| content[i].contains("�г�")
									|| content[i].contains("��")
									|| content[i].contains("�糤")
									|| content[i].contains("�س�")
									|| content[i].contains("�г�")
									|| content[i].contains("���")
									|| content[i].contains("�峤")
									|| content[i].contains("�ܾ���")
									|| content[i].contains("����")
									|| content[i].contains("������")
									|| content[i].contains("����")
									|| content[i].contains("����")
									|| content[i].contains("�ӳ�"))
							&& !content[i].contains("��ί��")) {
						bgzyldct = "��";
						break;
					}
				}
			}
		}
		wsssjlModel.setBgzyldct(bgzyldct);

		return wsssjlModel;
	}

}
