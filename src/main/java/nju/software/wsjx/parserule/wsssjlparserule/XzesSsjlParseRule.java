package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

/**
 * �����������ϼ�¼����
 * @author wangzh
 *
 */
public class XzesSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		// ������������
		String ay = null;
		if (wssscyrModellist != null && wsssjl != null) {
			// ����ssjl����һ�У�ȥ�����ţ����һ�������˵���󣨲�����һ����������
			String content = "";
			content = WsAnalyse.getContent(wsssjl);
			int prefix = 0;
			int suffix = content.indexOf("һ��");
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
					ay = content.substring(prefix, suffix);
					if (ay != null) {
						wsssjlModel.setAy(ay);
					}
				}
			}
		}
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

		// ������������������Ϊ����
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
		} else if (wsssjl.contains("��������")) {
			xzxwzl = "��������";
		} else {
			xzxwzl = "����������Ϊ";
		}
		wsssjlModel.setXzxwzl(xzxwzl);

		// ����������Ȩ��Ϊ����
		String xzqqxwzl = null;
		if (wsssjl.contains("������") || wsssjl.contains("����Ϊ")) {
			xzqqxwzl = "��������Ϊ";
		} else {
			xzqqxwzl = "��������Ϊ";
		}
		wsssjlModel.setXzqqxwzl(xzqqxwzl);

		// ����ǰ�����ʱ��
		String qscpsj = null;
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("����Ժ")
					&& contentArray[i].contains("��")
					&& contentArray[i].contains("��")) {
				qscpsj = getDate(contentArray[i]);
				wsssjlModel.setQscpsj(qscpsj);
				break;
			}
		}

		// ������������ǰ�󰸺�
		String qsah = null;
		String xzesqsah = null;
		String reg1 = "[��\\(����]\\d{4}[��\\)����].+?[^��]��?��?\\d+-?\\d+��";
		String reg2 = "\\W{1,30}";
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		for (int i = 0; i < contentArray.length; i++) {
			Matcher m1 = p1.matcher(contentArray[i]);
			while (m1.find()) {
				qsah = m1.group();
				Matcher m2 = p2.matcher(qsah);
				while (m2.find()) {
					xzesqsah = qsah;
					if(xzesqsah!= null){
						xzesqsah = xzesqsah.replace("��", "(");
						xzesqsah = xzesqsah.replace("��", ")");
					}					
					wsssjlModel.setXzesqsah(xzesqsah);
					// System.out.println(xzesqsah);
					break;
				}
				break;
			}
		}
		if(xzesqsah!= null){ 
			xzesqsah = xzesqsah.replace("��", "(");
			xzesqsah = xzesqsah.replace("��", ")");
		}	
		wsssjlModel.setXzesqsah(xzesqsah);
		// �������󰸼���Դ
		String qsajyl = "����";
		if(xzesqsah!=null){
			if (xzesqsah.contains("����")) {
				qsajyl = "��������";
			} else if (xzesqsah.contains("����")) {
				qsajyl = "����";
			}
		}
		wsssjlModel.setQsajyl(qsajyl);

		// ����ǰ��Ժ��ƣ�ǰ�󰸼�������ȣ�ǰ�󰸺�˳���
		String qsland = null;
		String qsfyjc = null;
		String qsahsxh = null;
		if (xzesqsah != null) {
			qsland = xzesqsah.substring(1, 5);
			// System.out.println(qsland);
			int end = 0;
			if (xzesqsah.contains("��")) {
				if (xzesqsah.indexOf("��") - 1 == xzesqsah.indexOf("֪")) {
					end = xzesqsah.indexOf("֪");
				} else if (xzesqsah.contains("��") && xzesqsah.contains("��")) {
					end = xzesqsah.indexOf("��");
				} else if (xzesqsah.indexOf("��") - 1 == xzesqsah.indexOf("��")) {
					end = xzesqsah.indexOf("��");
				} else if (xzesqsah.contains("��")) {
					end = xzesqsah.indexOf("��");
				} else {
					end = xzesqsah.indexOf("��");
				}
			} else if (xzesqsah.contains("��") && xzesqsah.contains("��")) {
				end = xzesqsah.indexOf("��");
			} else if (xzesqsah.contains("��")) {
				end = xzesqsah.indexOf("��");
			} else if (xzesqsah.contains("��")) {
				end = xzesqsah.indexOf("��");
			}
			if (end >= 6) {
				qsfyjc = xzesqsah.substring(6, end);
			}
			// System.out.println(qsfyjc);
			if (xzesqsah.contains("��")) {
				qsahsxh = xzesqsah.substring(xzesqsah.lastIndexOf("��") + 1,
						xzesqsah.lastIndexOf("��"));
			} else {
				qsahsxh = xzesqsah.substring(xzesqsah.lastIndexOf("��") + 1,
						xzesqsah.lastIndexOf("��"));
			}
			// System.out.println(qsahsxh);
		}
		wsssjlModel.setQsland(qsland);
		wsssjlModel.setQsfyjc(qsfyjc);
		wsssjlModel.setQsahsxh(qsahsxh);

		// ����ǰ��Ժ
		String qsfy = null;
		String fyjb = "����";
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("����Ժ")
					&& contentArray[i].contains("����")
					&& (contentArray[i].lastIndexOf("����") < contentArray[i]
							.lastIndexOf("����Ժ"))) {
				qsfy = contentArray[i].substring(
						contentArray[i].lastIndexOf("����") + 2,
						contentArray[i].lastIndexOf("����Ժ") + 4);
				if (qsfy.contains("���")) {
					fyjb = "��߼�";
				} else if (qsfy.contains("�߼�")) {
					fyjb = "�߼�";
				} else if (qsfy.contains("�м�")) {
					fyjb = "�м�";
				}
				break;
			}
		}
		wsssjlModel.setQsfy(qsfy);
		wsssjlModel.setFyjb(fyjb);

		// ����ǰ���������࣬ǰ��᰸��ʽ��ǰ����
		String qswszl = null;
		String qsjafs = null;
		String qssj = "һ��";
		if (wsssjl.contains("�����о�")) {
			qswszl = "�����о���";
			qsjafs = "�о�";
		} else if (wsssjl.contains("�����ö�")) {
			qswszl = "�����ö���";
			qsjafs = "�ö�";
		} else if (xzesqsah!=null) {
			if(xzesqsah.contains("����")){
				qsjafs = "��������";
			}else if(xzesqsah.contains("����")){
				qsjafs = "��������";
			}
		} 
		wsssjlModel.setQswszl(qswszl);
		wsssjlModel.setQsjafs(qsjafs);
		wsssjlModel.setQssj(qssj);

		// ����������֯
		String spzz = null;
		if (wsssjl.contains("����ͥ")) {
			spzz = "����ͥ";
		} else if (wsssjl.contains("����Ա")) {
			if (wsssjl.contains("��������Ա") || wsssjl.contains("����Ա")) {
				// spzz = "����ͥ";
			}
		} else {
			spzz = "����ͥ";
		}
		wsssjlModel.setSpzz(spzz);

		// ����������Ҫ�쵼��ͥ
		String bgzyldct = "��";
		for (int i = 0; i < contentArray.length; i++) {
			if (!contentArray[i].contains("δ��ͥ")
					&& (contentArray[i].contains("��ͥ"))
					|| contentArray[i].contains("��ͥ")) {
				if (contentArray[i].contains("����")
						&& contentArray[i].contains("��������")) {
					bgzyldct = "��";
				}
			}
		}
		wsssjlModel.setBgzyldct(bgzyldct);

		return wsssjlModel;
	}

}
