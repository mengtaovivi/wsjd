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

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;
import nju.software.wsjx.service.model.WsssjlZkzmModel;
import nju.software.wsjx.util.FileUtil;
/**
 * ����һ�����ϼ�¼��������
 * @author wangzh
 *
 */
public class XsysSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		// System.out.println(wsssjl);
		WsssjlModel wsssjlModel = new WsssjlModel();
		ArrayList<WsssjlZkjlModel> zkjlModellist = new ArrayList<WsssjlZkjlModel>();
		wsssjlModel.setWsssjlZkjl(zkjlModellist);
		// String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		String[] contentArray = wsssjl.split("\\.|��|��|;|��");
		ArrayList<String> zkjl = new ArrayList<String>();
		String zkzm = null;
		String qszay = null;
		String wzzm = null;
		String zmdm = null;
		FileUtil fileUtil = new FileUtil();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			ArrayList<String> ayList = new ArrayList<String>();
			String[] wsssjlBegin = wsssjl.substring(0,
					wsssjl.lastIndexOf("��") + 1).split("��");
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
				wsssjlModel.getWsssjlZkjl().add(zkjlModel);
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
						else if (!wsssjlBegin[j].contains("��")) {
							// System.out.println(wsssjlBegin[j] + "--��-" + k);
							WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
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
					wsssjlModel.getWsssjlZkjl().add(zkjlModel);
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

		// ����һ�󰸼����ó���
		String ysajsycx = "��ͨ����";
		String jyzpt = "��";
		String jysyjycx = "��";
		if (wsssjl.contains("���׳���") || wsssjl.contains("С�����ϳ���")) {
			if (wsssjl.contains("�������ü���")) {
				ysajsycx = "��ͨ����";
			} else if (wsssjl.contains("�������ü���")) {
				ysajsycx = "���׳���";
				jysyjycx = "��";
			} else {
				ysajsycx = "���׳���";
			}
		}
		if (wsssjl.contains("תΪ��ͨ") || wsssjl.contains("ת����ͨ")) {
			ysajsycx = "��ͨ����";
			jyzpt = "��";
		}
		wsssjlModel.setYsajsycx(ysajsycx);
		wsssjlModel.setJyzpt(jyzpt);
		wsssjlModel.setJysyjycx(jysyjycx);

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
		if (wsssjl.contains("����") || wsssjl.contains("������")) {
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
		// �������߻��أ����߰���
		// ������ֵ�д��������:XXX���Ժ��XX��������
		String gsjg = null;
		String gsah = null;
		if (wsssjl.contains("���Ժ") && wsssjl.contains("������")) {
			String str = wsssjl.substring(0, wsssjl.indexOf("������"));
			if (str.contains("��")) {
				gsjg = str.substring(str.indexOf("��") + 1,
						str.lastIndexOf("���Ժ") + 3);
			} else {
				gsjg = str.substring(0, str.lastIndexOf("���Ժ") + 3);
			}
			if (str.contains("��")) {
				gsah = str.substring(str.indexOf("��") + 1, str.length());
			}
			// else{
			// gsah = str.substring(str.indexOf("���Ժ")+1, str.length());
			// }
			// System.out.println(gsjg);
			// System.out.println(gsah);
		}
		wsssjlModel.setGsjg(gsjg);
		wsssjlModel.setGsah(gsah);

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
										if(!model.getSscyr().contains("���Ժ")){
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
							}
							// δ��ͥ����Ϣ��ǰ����ͥ����Ϣ�ں�
							else {
								for (WssscyrModel model : wssscyrModellist) {
									if (model.getSscyr() != null) {
										if(!model.getSscyr().contains("���Ժ")){
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
				}
				// ����ֻ�����ѳ�ͥ����Ϣ��û�г���δ��ͥ����Ϣ
				if (index == -1) {
					for (WssscyrModel model : wssscyrModellist) {
						if (model.getSscyr() != null) {
							if(!model.getSscyr().contains("���Ժ")){
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
				}
			} else if (beginIndex != -1) {
				// ����ֻ����δ��ͥ����Ϣ��û�г����ѳ�ͥ����Ϣ
				for (int i = 0; i < qxgjc.size(); i++) {
					if (wsssjl.contains(qxgjc.get(i))) {
						index = wsssjl.lastIndexOf(qxgjc.get(i));
						for (WssscyrModel model : wssscyrModellist) {
							if (model.getSscyr() != null) {
								if(!model.getSscyr().contains("���Ժ")){
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
		}
		wsssjlModel.setQxrxx(qxrmap);
		wsssjlModel.setCtrxx(ctrmap);
		return wsssjlModel;
	}
}
