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

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;
import nju.software.wsjx.service.model.WsssjlZkzmModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.FileUtil;
/**
 * �����������ϼ�¼��������
 * @author ningxuejiao
 *
 */
public class XszsSsjlParseRule extends GeneralSsjlCommonRule implements
		SsjlParseRule {
      public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) throws ParseException {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("\\.|��|��|;|��");
		String[] Arraycontent = wsssjl.split("\\.|��|��|;|��|,|��");
		// �������Ժ������������
				String jcyjyyqsl = "��";
				if (wsssjl.contains("���Ժ") && wsssjl.contains("����")
						&& wsssjl.contains("����")) {
					jcyjyyqsl = "��";
				}
				wsssjlModel.setJcyjyyqsl(jcyjyyqsl);
		// �������귨ͥ
				String snft = "��";
				if (wsssjl.contains("���귨ͥ")) {
					snft = "��";
				}
				wsssjlModel.setSnft(snft);
				//����ָ����Ϣ��ָ�ؼ�¼��ָ������������˵ȣ�
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
				//������ͥ������ͥ������Ϣ
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
				// ������ͥ����
			    ArrayList<String> ktrq = new ArrayList<String>();
				for(int i=0;i<contentArray.length;i++){
					if(contentArray[i].contains("��ͥ")){
						ktrq.add(getDate(contentArray[i]));
						}
					}
				if(ktrq!=null){
						wsssjlModel.setKtrq(ktrq);
					}
				//������ͥ���Ա��
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
				//�������������ɣ��������������ɴ��룩
				if (wsssjlModel.getWsssjlZkjl() != null) {
				System.out.println(wsssjlModel.getWsssjlZkjl().get(0)!=null);
				if (wsssjlModel.getWsssjlZkjl().get(0) != null) {
				if (wsssjlModel.getWsssjlZkjl().get(0).getZkzmModelist()
				.get(0).getZkzm() != null)
				qszay = wsssjlModel.getWsssjlZkjl().get(0)
				.getZkzmModelist().get(0).getZkzm();
				wsssjlModel.setQszay(qszay);
				}
				 }
				//�����������߰��ɣ�δ��ɣ�
				// ������������ǰ�󰸺�
				String qsah = null;
				String xszsqsah = null;
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
							xszsqsah = qsah;
							wsssjlModel.setXszsqsah(xszsqsah);
							break;
						}
						break;
					}
				
			}
				
				wsssjlModel.setXszsqsah(xszsqsah);
				// ����ǰ��Ժ��ƣ�ǰ�󰸺��������
				String qsfyjc = null;
				String qsahlasj = null;
				if (xszsqsah != null) {
					qsahlasj = xszsqsah.substring(1, 5);
					int end = 0;
					if (xszsqsah.contains("��")) {
						if (xszsqsah.indexOf("��") - 1 == xszsqsah.indexOf("֪")) {
							end = xszsqsah.indexOf("֪");
						} else if (xszsqsah.contains("��") && xszsqsah.contains("��")) {
							end = xszsqsah.indexOf("��");
						} else if (xszsqsah.indexOf("��") - 1 == xszsqsah.indexOf("��")) {
							end = xszsqsah.indexOf("��");
						} else if (xszsqsah.contains("��")) {
							end = xszsqsah.indexOf("��");
						} else {
							end = xszsqsah.indexOf("��");
						}
					} else if (xszsqsah.contains("��") && xszsqsah.contains("��")) {
						end = xszsqsah.indexOf("��");
					} else if (xszsqsah.contains("��")) {
						end = xszsqsah.indexOf("��");
					} else if (xszsqsah.contains("��")) {
						end = xszsqsah.indexOf("��");
					}
					if (end >= 6) {
						qsfyjc = xszsqsah.substring(6, end);
					}
					
				}
				wsssjlModel.setQsahlasj(qsahlasj);
				wsssjlModel.setQsfyjc(qsfyjc);
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
				} else if (xszsqsah != null) {
					if (xszsqsah.contains("����")) {
						qsjafs = "��������";
					} else if (xszsqsah.contains("����")) {
						qsjafs = "��������";
					}
				}
				wsssjlModel.setQswszl(qswszl);
				wsssjlModel.setQsjafs(qsjafs);
				wsssjlModel.setQssj(qssj);
				// �������󰸼���Դ
				String qsajyl = "���Ժ����";
				if (xszsqsah != null) {
					if (xszsqsah.contains("����")) {
						qsajyl = "�ϼ���Ժ��������";
					} else if (xszsqsah.contains("����")) {
						qsajyl = "����";
					}
				}
				if (wsssjl.contains("����")) {
					qsajyl = "����";
				}
				wsssjlModel.setQsajyl(qsajyl);
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
      }
				

					
	


