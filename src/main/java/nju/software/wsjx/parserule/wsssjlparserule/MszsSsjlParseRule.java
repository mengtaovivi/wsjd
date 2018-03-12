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
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.FileUtil;
/**
 * �����������ϼ�¼��������
 * @author ningxuejiao
 *
 */
public class MszsSsjlParseRule extends GeneralSsjlCommonRule implements
		SsjlParseRule {
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) throws ParseException {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
		String[] Arraycontent = wsssjl.split("\\.|��|��|;|��|,|��");
		// ������ͥ����,��ͥ������Ϣ
		String ktsl = "��";
		String ktslxx = null;
		if (wsssjl.contains("��ͥ")
				&& wsssjl.contains("����")&&!wsssjl.contains("����ͥ")) {
			ktsl = "��";
			if (wsssjl.contains("������")) {
				ktslxx = "����������";
				} else {
				ktslxx = "��������";
			}
		}
		wsssjlModel.setKtsl(ktsl);
		wsssjlModel.setKtslxx(ktslxx);
		// ��������,���ɴ��룬��������
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
							// System.out.println(wsssjlModel.getAy());
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
				//����������������������
				//����ǰ�󰸺�
				ArrayList<String> qsah = new ArrayList<String>();
				String reg1 = "[��\\(����]\\d{4}[��\\)����].+?[^��]�ֵ�?\\d+-?\\d+��";
				Pattern p1 = Pattern.compile(reg1);
				Matcher m1= p1.matcher(wsssjl);
				while (m1.find()) {
					qsah.add(m1.group());
				}
				wsssjlModel.setQsah(qsah);
				//����������鰸������
				ArrayList<String> zsscajah = new ArrayList<String>();
				String reg2 = "[��\\(����]\\d{4}[��\\)����].+?[^��]�ֵ�?\\d+-?\\d+��";
				Pattern p2 = Pattern.compile(reg2);
				Matcher m2 = p2.matcher(wsssjl);
				while (m2.find()) {
					qsah.add(m2.group());
				}
				wsssjlModel.setZsscajah(zsscajah);
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
				// ����ǰ���������࣬ǰ����
				String qswszl = null;
				String qssj = "����";
				if (wsssjl.contains("�����о�")) {
					qswszl = "�����о���";
					} else if (wsssjl.contains("���²ö�")) {
					qswszl = "���²ö���";
					
				} 
				wsssjlModel.setQswszl(qswszl);
				wsssjlModel.setQssj(qssj);
				// ����������Դ
				String ajly = null;
				if(wsssjl.contains("�¼�")){
					if(wsssjl.contains("����")) {
						ajly="�¼���Ժ����";
					}else if(wsssjl.contains("��Ч")){
						ajly="�����¼���Ժ��Ч���д���";
				}}else if(wsssjl.contains("����")) {
					if(wsssjl.contains("�о�")) {
						ajly="���о�����";
					}else if(wsssjl.contains("��������")){
					ajly="�Ծ�������";
				    }else if(wsssjl.contains("��Ͻ")) {
					ajly="�Թ�Ͻ����ö�����";
				    }else if(wsssjl.contains("����")) {
					ajly="�Բö�����������";
				    }else if(wsssjl.contains("����")) {
					ajly="�Բö���������ʱ�䣨���룩����";
				    }else if(wsssjl.contains("������")) {
					ajly="�Ծ����鲻��";
				}}else if(wsssjl.contains("�ϼ�")) {
					if(wsssjl.contains("ָ������")) {
					    ajly="�ϼ���Ժָ������";
					}else if(wsssjl.contains("ָ������")) {
					    ajly="�ϼ���Ժָ������";
					}else if(wsssjl.contains("�ƽ�")) {
						ajly="�ϼ���Ժ�ƽ���Ͻ";
					}else if(wsssjl.contains("ָ��")) {
						ajly="�ϼ���Ժָ����Ͻ";
					}else if(wsssjl.contains("����")) {
				        ajly="�ϼ���Ժָ������";
					}else if(wsssjl.contains("����")) {
						ajly="�ϼ���Ժָ������";
					}else if(wsssjl.contains("����")) {
						ajly="�ϼ���Ժ��������";
					}
				}else if(wsssjl.contains("����")) {
					if(wsssjl.contains("����")) {
						ajly="������ϵ������";
					}else if(wsssjl.contains("ԭ��")) {
						ajly="ԭ������������";
					}else if(wsssjl.contains("ָ��")) {
						ajly="ָ���໤������";
					}
				}else if(wsssjl.contains("����")) {
					ajly="����������";
				}else if(wsssjl.contains("����������")) {
					ajly="����������";
				}else if(wsssjl.contains("����������")) {
					ajly="����������";
				}else if(wsssjl.contains("����")) {
					ajly="����";
				}else if(wsssjl.contains("ְȨ")) {
					if(wsssjl.contains("�ල")) {
						ajly="��ְȨ���";
					}else {
						ajly="��ְȨ���";
					}
				}else if(wsssjl.contains("�������")) {
					ajly="�����������ߴ���ö����ָ��������";
				}else if(wsssjl.contains("֧����")) {
					ajly="��֧����ʧЧת��";
				}else if(wsssjl.contains("����")) {
					ajly="������Ժ�ö����͹�Ͻ";
				}else if(wsssjl.contains("������")){
					ajly="�����˳���֮�߰����������У�����������򲵻����߲ö���";
				}else if(wsssjl.contains("��λ")) {
					ajly="��ȷ�ϴ�λ�ܳ�Ȩ���򲵻�����ö�";
				}else if(wsssjl.contains("����")) {
					ajly="���󷢻�����";
				}else if(wsssjl.contains("���")) {
					ajly="���Ժ�������";
				}else if(wsssjl.contains("�ƽ�")) {
					ajly="������Ժ�ƽ�";
				}else if(wsssjl.contains("�Ʋ�")) {
					ajly="������ͽ����ö������Ʋ�";	
				}else if(wsssjl.contains("��Ժ")) {
					 if(wsssjl.contains("����")) {
						 ajly="��Ժ����";
					 }else if(wsssjl.contains("����")) {
					 ajly="��Ժ����";
		             }else if(wsssjl.contains("��Ч����")) {
					 ajly="���ֱ�Ժ��Ч���д���";
				    }else if(wsssjl.contains("�ἶ")){
					 ajly="��Ժ�ἶ��Ͻ";}
					}else if (wsssjl.contains("����")){
						if(wsssjl.contains("������")) {
							ajly="����������";
						}else if(wsssjl.contains("ָ��")) {
							ajly="ָ������������";
						}else if(wsssjl.contains("��������")) {
							ajly="��������";
						}
				}else {
					ajly="����";
				}
				wsssjlModel.setAjly(ajly);
				//����������֯
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
				//
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
