package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
/**
 * �����������ϼ�¼��������
 * @author ningxuejiao
 *
 */
public class XzzsSsjlParseRule extends GeneralSsjlCommonRule implements
		SsjlParseRule {
public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) throws ParseException {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		//������ͥ������ͥ������Ϣ
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
		// ������ͥ����,��������
	    ArrayList<String> ktrq = new ArrayList<String>();
		String slrq = null;
		for(int i=0;i<contentArray.length;i++){
			if(contentArray[i].contains("����")){
				slrq=getDate(contentArray[i]);
			}else if(contentArray[i].contains("��ͥ")){
				ktrq.add(getDate(contentArray[i]));
				}
			}
		if(ktrq!=null){
				wsssjlModel.setKtrq(ktrq);
			}
		if (slrq != null) {
				wsssjlModel.setSlrq(slrq);
		//����������Ȩ��Ϊ����
		String xzqqxwzl = null;
		if (wsssjl.contains("������") || wsssjl.contains("����Ϊ")) {
			xzqqxwzl = "��������Ϊ";
			} else {
			xzqqxwzl = "��������Ϊ";
			}
			wsssjlModel.setXzqqxwzl(xzqqxwzl);
		//����������Ϊ����
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
			// ����ǰ�󰸺�
			String qsah = null;
			String xzzsqsah = null;
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
						xzzsqsah = qsah;
						if(xzzsqsah!= null){
							xzzsqsah = xzzsqsah.replace("��", "(");
							xzzsqsah = xzzsqsah.replace("��", ")");
						}					
						wsssjlModel.setXzesqsah(xzzsqsah);
						// System.out.println(xzesqsah);
						break;
					}
					break;
				}
			}
			if(xzzsqsah!= null){
				xzzsqsah = xzzsqsah.replace("��", "(");
				xzzsqsah = xzzsqsah.replace("��", ")");
			}	
			wsssjlModel.setXzesqsah(xzzsqsah);
			// ����ǰ��Ժ��ƣ�ǰ�󰸺�����ʱ��
			String qsahlasj = null;
			String qsfyjc = null;
			if (xzzsqsah!= null) {
							qsahlasj= xzzsqsah.substring(1, 5);
							int end = 0;
							if (xzzsqsah.contains("��")) {
								if (xzzsqsah.indexOf("��") - 1 == xzzsqsah.indexOf("֪")) {
									end = xzzsqsah.indexOf("֪");
								} else if (xzzsqsah.contains("��") && xzzsqsah.contains("��")) {
									end = xzzsqsah.indexOf("��");
								} else if (xzzsqsah.indexOf("��") - 1 == xzzsqsah.indexOf("��")) {
									end = xzzsqsah.indexOf("��");
								} else if (xzzsqsah.contains("��")) {
									end = xzzsqsah.indexOf("��");
								} else {
									end = xzzsqsah.indexOf("��");
								}
							} else if (xzzsqsah.contains("��") && xzzsqsah.contains("��")) {
								end = xzzsqsah.indexOf("��");
							} else if (xzzsqsah.contains("��")) {
								end = xzzsqsah.indexOf("��");
							} else if (xzzsqsah.contains("��")) {
								end = xzzsqsah.indexOf("��");
							}
							if (end >= 6) {
								qsfyjc = xzzsqsah.substring(6, end);
							}
							
						}
						wsssjlModel.setQsahlasj(qsahlasj);
						wsssjlModel.setQsfyjc(qsfyjc);
			
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
			
			// �������󰸼���Դ
			String qsajyl = "����";
			if(xzzsqsah!=null){
				if (xzzsqsah.contains("����")) {
					qsajyl = "��������";
				} else if (xzzsqsah.contains("����")) {
					qsajyl = "����";
				}
			}
			wsssjlModel.setQsajyl(qsajyl);
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
            // ����ǰ���������࣬ǰ����
			/*String qswszl = null;
			String qssj = "����";
			if (wsssjl.contains("�����о�")) {
				qswszl = "�����о���";
			   } else if (wsssjl.contains("�����ö�")) {
				qswszl = "�����ö���";
				} else if (xzzsqsah!=null) {
				}
			wsssjlModel.setQswszl(qswszl);
			wsssjlModel.setQssj(qssj);*/
			//����������֯
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
        }
		return wsssjlModel;
		}
}

