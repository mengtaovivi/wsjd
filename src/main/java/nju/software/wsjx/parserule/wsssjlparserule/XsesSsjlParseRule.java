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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;
import nju.software.wsjx.service.model.WsssjlZkzmModel;
import nju.software.wsjx.util.DateUtil;
/**
 * ���¶������ϼ�¼��������
 * @author wangzh
 *
 */
public class XsesSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		// System.out.println(wsssjl);
		WsssjlModel wsssjlModel = new WsssjlModel();
		ArrayList<WsssjlZkjlModel> zkjlModellist = new ArrayList<WsssjlZkjlModel>();
		wsssjlModel.setWsssjlZkjl(zkjlModellist);
		// String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		String[] content = wsssjl.split("\\.|��");
		String[] contentArray = wsssjl.split("\\.|��|��|;|��");
		String[] Arraycontent = wsssjl.split("\\.|��|��|;|��|,|��");
		String zkzm = null;
		String qszay = null;
		String wzzm = null;
		String zmdm = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			ArrayList<String> ayList = new ArrayList<String>();
			String wsssjlZkzm = wsssjl;
			if(wsssjl.contains("һ��")){
				wsssjlZkzm = wsssjl.substring(0,wsssjl.indexOf("һ��"));
			}
			String[] wsssjlBegin = wsssjlZkzm.substring(0,
					wsssjlZkzm.lastIndexOf("��") + 1).split("��");
			String str = "";
			is = getClass().getClassLoader().getResourceAsStream("enum/xszm_dm.txt");
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				ayList.add(str);
			}
			ArrayList<Integer> keylist = new ArrayList<Integer>();// ָ�ؼ�¼����λ
			for (int i = 0; i < wsssjlBegin.length; i++) {
				if (wsssjlBegin[i].contains("��")) {
					keylist.add(i);
				}
			}
			keylist.add(wsssjlBegin.length);
			if (keylist.size() == 2) {
				// ������ϼ�¼ָ����Ϣֻ��һ��"��"��˵��ֻ��һ��ָ�ؼ�¼
				WsssjlZkjlModel zkjlModel = new WsssjlZkjlModel();// �½�һ��ָ�ؼ�¼����
				ArrayList<WsssjlZkzmModel> zkzmModellist = new ArrayList<WsssjlZkzmModel>();// �½�һ��ָ�ؼ�¼list����
				zkjlModel.setZkzmModelist(zkzmModellist);
				// �������������
				ArrayList<String> xgr = new ArrayList<String>();
				for (WssscyrModel model : wssscyrModellist) {
					if (model.getSscyr() != null) {
						if (wsssjlBegin[0].contains(model.getSscyr())) {
							xgr.add(model.getSscyr());
						}
					}
				}
				zkjlModel.setXgr(xgr);
				//
				for (int j = 0; j < wsssjlBegin.length; j++) {
					if (wsssjlBegin[j].contains("��")) {
						// ����ַ�������"��"��˵���������ǳ�����"��"֮��
						// ����ָ������
						WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
						zkzm = wsssjlBegin[j].substring(
								wsssjlBegin[j].indexOf("��") + 1,
								wsssjlBegin[j].length())
								+ "��";
						wzzm = wsssjlBegin[j].substring(
								wsssjlBegin[j].indexOf("��") + 1,
								wsssjlBegin[j].length())
								+ "��";
						zkzmModel.setZkzm(zkzm);
						zkzmModel.setWzzm(wzzm);
						for (int i = 0; i < ayList.size(); i++) {
							// ��ȡ�İ����롮�������������롯�����ƥ�䣬�ҵ�������������������
							if (wsssjlBegin[j].contains(ayList.get(i)
									.substring(4, ayList.get(i).length()))) {
								// zkzmModel = new WsssjlZkzmModel();
								zkzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "��";
								wzzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "��";
								zmdm = ayList.get(i).substring(0, 4).trim();
								zkzmModel.setZkzm(zkzm);
								zkzmModel.setWzzm(wzzm);
								zkzmModel.setZmdm(zmdm);
								break;
							}
						}
						zkjlModel.getZkzmModelist().add(zkzmModel);
					}
					// �������ַ���û��"��",˵�����ַ������Ա����ſ�ͷ�ģ����ұ�����ľ�������
					else if (!wsssjlBegin[j].contains("��")) {
						WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
						zkzm = wsssjlBegin[j].substring(1,
								wsssjlBegin[j].length())
								+ "��";
						wzzm = wsssjlBegin[j].substring(1,
								wsssjlBegin[j].length())
								+ "��";
						zkzmModel.setZkzm(zkzm);
						zkzmModel.setWzzm(wzzm);
						for (int i = 0; i < ayList.size(); i++) {
							// ��ȡ�İ����롮�������������롯�����ƥ�䣬�ҵ�������������������
							if (wsssjlBegin[j].contains(ayList.get(i)
									.substring(4, ayList.get(i).length()))) {
								zkzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "��";
								wzzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "��";
								zmdm = ayList.get(i).substring(0, 4).trim();
								zkzmModel.setZkzm(zkzm);
								zkzmModel.setWzzm(wzzm);
								zkzmModel.setZmdm(zmdm);
								break;
							}
						}
						zkjlModel.getZkzmModelist().add(zkzmModel);
					}
				}
				if(!wsssjlModel.getWsssjlZkjl().contains(zkjlModel)){
					wsssjlModel.getWsssjlZkjl().add(zkjlModel);
				}
			} else if (keylist.size() > 2) {
				// ������ϼ�¼ָ����Ϣ�ж��"��"������"��"���ַ���ǰһ�������һ��ָ�ؼ�¼�Ľ���
				for (int k = 1; k < keylist.size(); k++) {
					WsssjlZkjlModel zkjlModel = new WsssjlZkjlModel();// �½�һ��ָ�ؼ�¼����
					ArrayList<WsssjlZkzmModel> zkzmModellist = new ArrayList<WsssjlZkzmModel>();// �½�һ��ָ�ؼ�¼list����
					zkjlModel.setZkzmModelist(zkzmModellist);
					// �������������
					ArrayList<String> xgr = new ArrayList<String>();
					for (WssscyrModel model : wssscyrModellist) {
						if (wsssjlBegin[keylist.get(k - 1)].contains(model
								.getSscyr())) {
							xgr.add(model.getSscyr());
						}
					}
					zkjlModel.setXgr(xgr);
					// ѭ��zkzm
					zkzm: for (int j = keylist.get(k - 1); j < keylist.get(k); j++) {
						if (wsssjlBegin[j].contains("��")) {
							// ����ַ�������"��"��˵���������ǳ�����"��"֮��
							// System.out.println(wsssjlBegin[j] + "--��һ����-" +
							// k);
							// ����ָ������
							WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
							zkzm = wsssjlBegin[j].substring(
									wsssjlBegin[j].indexOf("��") + 1,
									wsssjlBegin[j].length()) + "��";
							wzzm = wsssjlBegin[j].substring(
									wsssjlBegin[j].indexOf("��") + 1,
									wsssjlBegin[j].length()) + "��";
							zkzmModel.setZkzm(zkzm);
							zkzmModel.setWzzm(wzzm);
							for (int i = 0; i < ayList.size(); i++) {
								// ��ȡ�İ����롮�������������롯�����ƥ�䣬�ҵ�������������������
								if (wsssjlBegin[j].contains(ayList.get(i)
										.substring(4, ayList.get(i).length()))) {
									// zkzmModel = new WsssjlZkzmModel();
									zkzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "��";
									wzzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "��";
									zmdm = ayList.get(i).substring(0, 4).trim();
									zkzmModel.setZkzm(zkzm);
									zkzmModel.setWzzm(wzzm);
									zkzmModel.setZmdm(zmdm);
									break;
								}
							}
							zkjlModel.getZkzmModelist().add(zkzmModel);
						}
						// �������ַ���û��"��",˵�����ַ������Ա����ſ�ͷ�ģ����ұ�����ľ�������
						else if (!wsssjlBegin[j].contains("��")
								&& wsssjlBegin[j].length() != 0) {
							// System.out.println(wsssjlBegin[j] + "--��-" + k);
							WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
							// System.out.println(wsssjlBegin[j]);
							zkzm = wsssjlBegin[j].substring(1,
									wsssjlBegin[j].length())
									+ "��";
							wzzm = wsssjlBegin[j].substring(1,
									wsssjlBegin[j].length())
									+ "��";
							// System.out.println(zkzm);
							zkzmModel.setZkzm(zkzm);
							zkzmModel.setWzzm(wzzm);
							for (int i = 0; i < ayList.size(); i++) {
								// ��ȡ�İ����롮�������������롯�����ƥ�䣬�ҵ�������������������
								if (wsssjlBegin[j].contains(ayList.get(i)
										.substring(4, ayList.get(i).length()))) {
									zkzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "��";
									wzzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "��";
									zmdm = ayList.get(i).substring(0, 4).trim();
									zkzmModel.setZkzm(zkzm);
									zkzmModel.setWzzm(wzzm);
									zkzmModel.setZmdm(zmdm);
									break;
								}
							}
							zkjlModel.getZkzmModelist().add(zkzmModel);
						}
					}
					if(!wsssjlModel.getWsssjlZkjl().contains(zkjlModel)){
						wsssjlModel.getWsssjlZkjl().add(zkjlModel);
					}
				}
			}

			// ��������������
			// if (wsssjlModel.getWsssjlZkjl() != null) {
			// // System.out.println(wsssjlModel.getWsssjlZkjl().get(0)!=null);
			// if (wsssjlModel.getWsssjlZkjl().get(0) != null) {
			// if (wsssjlModel.getWsssjlZkjl().get(0).getZkzmModelist()
			// .get(0).getZkzm() != null)
			// qszay = wsssjlModel.getWsssjlZkjl().get(0)
			// .getZkzmModelist().get(0).getZkzm();
			// wsssjlModel.setQszay(qszay);
			// }
			// }
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

		// ����ǰ�����ʱ��
		String qscpsj = null;
		for (int i = 0; i < Arraycontent.length; i++) {
			if (Arraycontent[i].contains("����")
					&& Arraycontent[i].contains("��")
					&& Arraycontent[i].contains("��")
					&& (Arraycontent[i].contains("�о�") || Arraycontent[i]
							.contains("�ö�"))) {
				qscpsj = getDate(Arraycontent[i]);
				break;
			}
		}
		if (qscpsj != null) {
			if (qscpsj.contains("��") || qscpsj.contains("O")) {
				qscpsj = DateUtil.convertToCNDate(qscpsj);
			}
		}
		wsssjlModel.setQscpsj(qscpsj);

		// �������¶���ǰ�󰸺�
		String qsah = null;
		String xsesqsah = null;
		String reg1 = "[��\\(����]\\d{4}[��\\)����].+?[^��]�ֵ�?\\d+-?\\d+��";
		String reg2 = "\\W{1,30}";
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		for (int i = 0; i < Arraycontent.length; i++) {
			Matcher m1 = p1.matcher(Arraycontent[i]);
			while (m1.find()) {
				qsah = m1.group();
				Matcher m2 = p2.matcher(qsah);
				while (m2.find()) {
					xsesqsah = qsah;
					wsssjlModel.setXsesqsah(xsesqsah);
					break;
				}
				break;
			}
		}
		wsssjlModel.setXsesqsah(xsesqsah);
		// �������󰸼���Դ
		String qsajyl = "���Ժ����";
		if (xsesqsah != null) {
			if (xsesqsah.contains("����")) {
				qsajyl = "�ϼ���Ժ��������";
			} else if (xsesqsah.contains("����")) {
				qsajyl = "����";
			}
		}
		if (wsssjl.contains("����")) {
			qsajyl = "����";
		}
		wsssjlModel.setQsajyl(qsajyl);

		// ����ǰ��Ժ��ƣ�ǰ�󰸼�������ȣ�ǰ�󰸺�˳���
		String qsland = null;
		String qsfyjc = null;
		String qsahsxh = null;
		if (xsesqsah != null) {
			qsland = xsesqsah.substring(1, 5);
			int end = 0;
			if (xsesqsah.contains("��")) {
				if (xsesqsah.indexOf("��") - 1 == xsesqsah.indexOf("֪")) {
					end = xsesqsah.indexOf("֪");
				} else if (xsesqsah.contains("��") && xsesqsah.contains("��")) {
					end = xsesqsah.indexOf("��");
				} else if (xsesqsah.indexOf("��") - 1 == xsesqsah.indexOf("��")) {
					end = xsesqsah.indexOf("��");
				} else if (xsesqsah.contains("��")) {
					end = xsesqsah.indexOf("��");
				} else {
					end = xsesqsah.indexOf("��");
				}
			} else if (xsesqsah.contains("��") && xsesqsah.contains("��")) {
				end = xsesqsah.indexOf("��");
			} else if (xsesqsah.contains("��")) {
				end = xsesqsah.indexOf("��");
			} else if (xsesqsah.contains("��")) {
				end = xsesqsah.indexOf("��");
			}
			if (end >= 6) {
				qsfyjc = xsesqsah.substring(6, end);
			}
			if (xsesqsah.contains("��")) {
				qsahsxh = xsesqsah.substring(xsesqsah.lastIndexOf("��") + 1,
						xsesqsah.lastIndexOf("��"));
			} else {
				qsahsxh = xsesqsah.substring(xsesqsah.lastIndexOf("��") + 1,
						xsesqsah.lastIndexOf("��"));
			}
		}
		wsssjlModel.setQsland(qsland);
		wsssjlModel.setQsfyjc(qsfyjc);
		wsssjlModel.setQsahsxh(qsahsxh);

		// ���������漰
		String ajsj = null;
		if (wsssjl.contains("��Ʒ")) {
			ajsj = "�涾";
		} else if (wsssjl.contains("ǹ")) {
			ajsj = "��ǹ";

		} else if (wsssjl.contains("�����")) {
			ajsj = "���";

		} else if (wsssjl.contains("���Ұ�ȫ")) {
			ajsj = "����Ұ�ȫ";

		} else if (wsssjl.contains("���ֹ�")) {
			ajsj = "�淨�ֹ�";

		} else if (wsssjl.contains("֪ʶ��Ȩ")) {
			ajsj = "��֪ʶ��Ȩ";

		}
		wsssjlModel.setAjsj(ajsj);

		// ����ǰ��Ժ
		String qsfy = null;
		String fyjb = "����";
		for (int i = 0; i < Arraycontent.length; i++) {
			if (Arraycontent[i].contains("����Ժ")
					&& (Arraycontent[i].contains("����")
							|| Arraycontent[i].contains("����") || Arraycontent[i]
								.contains("��"))) {
				int key = Arraycontent[i].indexOf("����Ժ") + 4;
				if (key == Arraycontent[i].indexOf("����")
						|| key == Arraycontent[i].indexOf("����")
						|| key == Arraycontent[i].indexOf("��")) {
					qsfy = Arraycontent[i].substring(0,
							Arraycontent[i].lastIndexOf("����Ժ") + 4);
				}
				if (qsfy != null) {
					if (qsfy.contains("���")) {
						fyjb = "��߼�";
					} else if (qsfy.contains("�߼�")) {
						fyjb = "�߼�";
					} else if (qsfy.contains("�м�")) {
						fyjb = "�м�";
					}
				}
				break;
			}
		}
		wsssjlModel.setQsfy(qsfy);
		wsssjlModel.setFyjb(fyjb);

		// ������׼��Ժ����
		String bzfymc = null;
		if (qsfy != null) {
			if (qsfy.contains("��") && qsfy.contains("ʡ")) {
				if (qsfy.contains("��")) {
					bzfymc = qsfy.substring(qsfy.indexOf("��") + 1);
				} else {
					bzfymc = qsfy.substring(qsfy.indexOf("ʡ") + 1);
				}
			} else if (qsfy.contains("��") && qsfy.contains("��")) {
				bzfymc = qsfy.substring(qsfy.indexOf("��") + 1);
			} else if (qsfy.contains("��") && qsfy.contains("������")) {
				bzfymc = qsfy.substring(qsfy.indexOf("������") + 3);
			} else if (qsfy.contains("��") && qsfy.contains("������")) {
				bzfymc = qsfy.substring(qsfy.indexOf("������") + 3);
			} else if (qsfy.contains("��") && qsfy.contains("ʡ")) {
				bzfymc = qsfy.substring(qsfy.indexOf("ʡ") + 1);
			} else {
				bzfymc = qsfy;
			}
		}
		wsssjlModel.setBzfymc(bzfymc);

		// ����ԭ���߻���
		String qsgsjg = null;
		for (int i = 0; i < Arraycontent.length; i++) {
			if (Arraycontent[i].contains("���Ժ")
					&& Arraycontent[i].contains("����")) {
				if (Arraycontent[i].indexOf("����") < Arraycontent[i]
						.indexOf("���Ժ")) {
					if (Arraycontent[i].indexOf("����") + 2 == Arraycontent[i]
							.indexOf("��")
							|| Arraycontent[i].indexOf("����") + 2 == Arraycontent[i]
									.indexOf("��")) {
						if (Arraycontent[i].contains("��Ժ")
								&& Arraycontent[i].indexOf("���Ժ") < Arraycontent[i]
										.indexOf("��Ժ")) {
							qsgsjg = Arraycontent[i].substring(
									Arraycontent[i].indexOf("����") + 3,
									Arraycontent[i].indexOf("��Ժ") + 2);
							break;
						} else {
							qsgsjg = Arraycontent[i].substring(
									Arraycontent[i].indexOf("����") + 3,
									Arraycontent[i].indexOf("���Ժ") + 3);
							break;
						}
					} else if (Arraycontent[i].contains("��Ժ")
							&& Arraycontent[i].indexOf("���Ժ") < Arraycontent[i]
									.indexOf("��Ժ")) {
						qsgsjg = Arraycontent[i].substring(
								Arraycontent[i].indexOf("����") + 3,
								Arraycontent[i].indexOf("��Ժ") + 2);
						break;
					} else {
						qsgsjg = Arraycontent[i].substring(
								Arraycontent[i].indexOf("����") + 2,
								Arraycontent[i].indexOf("���Ժ") + 3);
						break;
					}
				}
			}
		}
		wsssjlModel.setQsgsjg(qsgsjg);

		// ����ǰ���������࣬ǰ��᰸��ʽ��ǰ����
		String qswszl = null;
		String qsjafs = null;
		String qssj = "һ��";
		if (wsssjl.contains("�����о�")) {
			qswszl = "�����о���";
			qsjafs = "�о�";
		} else if (wsssjl.contains("���²ö�")) {
			qswszl = "���²ö���";
			qsjafs = "�ö�";
		} else if (xsesqsah != null) {
			if (xsesqsah.contains("����")) {
				qsjafs = "��������";
			} else if (xsesqsah.contains("����")) {
				qsjafs = "��������";
			}
		}
		wsssjlModel.setQswszl(qswszl);
		wsssjlModel.setQsjafs(qsjafs);
		wsssjlModel.setQssj(qssj);

		// ����ǰ���о�
		String qspj = null;
		for (int i = 0; i < content.length; i++) {
			if(!content[i].contains("����")||!content[i].contains("����")){
				if (content[i].contains("�����о�")) {
					qspj = content[i].substring(content[i].indexOf("�����о�"));
				} else if (content[i].contains("���¸��������о�")) {
					qspj = content[i].substring(content[i].indexOf("���¸��������о�"));
				} else if (content[i].contains("�ݴ�")) {
					qspj = content[i].substring(content[i].indexOf("�ݴ�"));
				} else if (content[i].contains("����")) {
					qspj = content[i].substring(content[i].indexOf("����"));
				} else if (content[i].contains("Ϊ��")) {
					qspj = content[i].substring(content[i].indexOf("Ϊ��"));
				} else if (content[i].contains("����")) {
					qspj = content[i].substring(content[i].indexOf("����"));
				} else if (content[i].contains("����")) {
					qspj = content[i].substring(content[i].indexOf("����"));
				} else if (content[i].contains("����")) {
					qspj = content[i].substring(content[i].indexOf("����"));
				}
				else if (issurplus(content[i]) > -1) {
					qspj = qspj + "��" + content[i] + "��";
				} else if (isFzsurplus(content[i])) {
					qspj = qspj + "��" + content[i] + "��";
				} else if (content[i].contains("�϶�") || content[i].contains("ͽ��")
						|| content[i].contains("����") || content[i].contains("����")
						|| content[i].contains("û��") || content[i].contains("�������")
						|| content[i].contains("�������")|| content[i].contains("��������Ȩ��")
						|| content[i].contains("����")|| content[i].contains("����")
						|| content[i].contains("����")) {
					qspj = qspj + "��" + content[i] + "��";
				}
			}
		}
		if(qspj != null){
			if(qspj.length()<10){
				qspj = null;
			}else if (qspj.lastIndexOf("��")!= qspj.length() - 1) {
				qspj += "��";
			}
		}
		wsssjlModel.setQspj(qspj);

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

		// ������������
		String qsrq = null;
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("����")
					|| contentArray[i].contains("����")) {
				qsrq = getDate(contentArray[i]);
				break;
			}
		}
		wsssjlModel.setQsrq(qsrq);

		// ������������
		String ssxz = null;
		if (wsssjl.contains("����") || wsssjl.contains("���������������")
				|| wsssjl.contains("�̰�") || wsssjl.contains("Ű��")
				|| wsssjl.contains("��ռ")) {
			ssxz = "���߲Ŵ���İ���";
		} else if (wsssjl.contains("�����˺�") || wsssjl.contains("�Ƿ�����")
				|| wsssjl.contains("����ͨ������") || wsssjl.contains("�ػ�")
				|| wsssjl.contains("����") || wsssjl.contains("֪ʶ��Ȩ")
				|| wsssjl.contains("α����Ʒ")) {
			ssxz = "��������֤��֤������΢���°���";
		}
		if (wsssjl.contains("����") || wsssjl.contains("���Ժָ��")) {
			if (wsssjl.contains("����")) {
				if (wsssjl.contains("ת����")) {
					ssxz = "����ת���߰���";
				} else {
					ssxz = "���й�����������";
				}
			}
			ssxz = "����";
		}
		wsssjlModel.setSsxz(ssxz);

		// �������귨ͥ
		String snft = "��";
		if (wsssjl.contains("���귨ͥ")) {
			snft = "��";
		}
		wsssjlModel.setSnft(snft);

		// �������Ժ������������
		String jcyjyyqsl = "��";
		if (wsssjl.contains("���Ժ") && wsssjl.contains("����")
				&& wsssjl.contains("����")) {
			jcyjyyqsl = "��";
		}
		wsssjlModel.setJcyjyyqsl(jcyjyyqsl);

		// ���������𸽴���������
		String slztqfdmsss = "��";
		if (wsssjl.contains("��������")) {
			slztqfdmsss = "��";
			// �����������²��ּ�������
			String msbfjxsl = "��";
			if (wsssjl.contains("")) {
				msbfjxsl = "��";
			}
			wsssjlModel.setMsbfjxsl(msbfjxsl);
		}
		wsssjlModel.setSlztqfdmsss(slztqfdmsss);

		// ������ͥ���Ա���������Ա���
		String jcy = null;
		String js = null;
		if (wsssjl.contains("��֧ͥ��")) {
			if (wsssjl.contains("������Ա")) {
				jcy = wsssjl.substring(wsssjl.indexOf("������Ա") + 5,
						wsssjl.indexOf("��֧ͥ��"));
				js = "������Ա";
			} else if (wsssjl.contains("���Ա")) {
				jcy = wsssjl.substring(wsssjl.indexOf("���Ա") + 3,
						wsssjl.indexOf("��֧ͥ��"));
				js = "���Ա";
			}
		}
		wsssjlModel.setJcy(jcy);
		wsssjlModel.setJs(js);

		// �������߻��߷�Χ
		String sshksfw = null;
		if (wsssjl.contains("��������")) {
			sshksfw = "�����¡������о����ϣ�������";
		} else if (wsssjl.contains("������") && wsssjl.contains("�ö�")
				&& wsssjl.contains("����")) {
			sshksfw = "�Բ������߲ö�����";
		} else {
			sshksfw = "�������о��ϣ�������";
		}
		wsssjlModel.setSshksfw(sshksfw);

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
			int beginIndex = wsssjl.indexOf("��ͥ");
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
									if (model.getSscyr() != null) {
										if (wsssjl.substring(beginIndex, index)
												.contains(model.getSscyr())) {
											if (!ctrmap.containsKey((model
													.getSscyr()))) {
												ctrmap.put(model.getSscyr(),
														model.getSssf());
											}
										} else if (wsssjl.substring(index,
												wsssjl.lastIndexOf("��ͥ"))
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
		return wsssjlModel;
	}

	/**
	 * �ж��ַ����Ƿ�����������+��㿪ͷ
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isFzsurplus(String content) {
		boolean b = false;
		String rexNu = "[һ�����������߰˾�ʮ]";
		Pattern patternNu = Pattern.compile(rexNu);
		if (content.length() >= 3)
			content = content.substring(0, 3);
		Matcher matcherNu = patternNu.matcher(content);
		if (matcherNu.find()
				&& (content.contains(".") || content.contains("��")
						|| content.contains("��") || content.contains("("))) {
			b = true;
		}
		return b;
	}

	/**
	 * �ж��ַ����Ƿ��԰���������+��㿪ͷ
	 * 
	 * @param content
	 * @return
	 */
	public static int issurplus(String content) {
		int b = -1;
		String rexNu = "\\d{1,2}";
		Pattern patternNu = Pattern.compile(rexNu);
		if (content.length() >= 3)
			content = content.substring(0, 3);
		Matcher matcherNu = patternNu.matcher(content);
		if (matcherNu.find()
				&& (content.contains(".") || content.contains("��") || content
						.contains("��"))) {
			b = Integer.parseInt(matcherNu.group(0));
		}
		return b;
	}
}
