package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcZdlxModel;
import nju.software.wsjx.parserule.wssscyrparserule.SscyrParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.XsesSscyrParseRule;
/**
 * ���¶�����з������̽���
 * @author wangzh
 *
 */
public class XsesCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		List<String> cpfxgc = wsAnalyse.getCpfxgc();
		XsesSscyrParseRule xsesSscyrParseRule = new XsesSscyrParseRule();
		List<WssscyrModel> wssscyrModellist = xsesSscyrParseRule.jxWssscyrModelList(wsAnalyse);
		
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
		// �������¶��󰸼���Դ
		String ysajly = null;
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("������")
					|| contentlist.get(i).contains("�ٴ�����")
					|| contentlist.get(i).contains("��������")
					|| contentlist.get(i).contains("�ٴ�����")
					|| contentlist.get(i).contains("����")) {
				ysajly = "��������";
			}
		}
		wscpfxgcModel.setYsajly(ysajly);
		// �����Ƿ�ͬ����
		String gtfz = "��";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("��ͬ����")
					|| contentlist.get(i).contains("����")
					|| contentlist.get(i).contains("�ŷ�")
					|| contentlist.get(i).contains("�໥���")
					|| contentlist.get(i).contains("�ӷ�")
					|| contentlist.get(i).contains("����")) {
				gtfz = "��";
			}
		}
		wscpfxgcModel.setGtfz(gtfz);
		// ������ͬ���������
		String bgrtyrzcx = "��";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("��ʵ����")
					|| contentlist.get(i).contains("�绤���������")) {
				bgrtyrzcx = "��";
			}
		}
		wscpfxgcModel.setBgrtyrzcx(bgrtyrzcx);

		// ������ͥǰ���볷������
		String ktqsqchss = "��";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("����")) {
				ktqsqchss = "��";
			}
		}
		wscpfxgcModel.setKtqsqchss(ktqsqchss);
		
		// �����������
		ArrayList<String> lxqk = new ArrayList<String>();
		for (int i = 0; i < cpfxgc.size(); i++) {
			String cpfxgcString = cpfxgc.get(i);
			String[] cpfxgcArray = cpfxgcString.split("\\.|��|;|��");
			for (int j = 0; j < cpfxgcArray.length; j++) {
				lxqk.add(cpfxgcArray[j]);
			}
		}
		ArrayList<WscpfxgcZdlxModel> zdlxModelist = new ArrayList<WscpfxgcZdlxModel>();
		ArrayList<WscpfxgcFdlxModel> fdlxModelist = new ArrayList<WscpfxgcFdlxModel>();
		wscpfxgcModel.setFdlxModel(fdlxModelist);
		wscpfxgcModel.setZdlxModel(zdlxModelist);
		for (int i = 0; i < lxqk.size(); i++) {
			WscpfxgcZdlxModel zdlxModel = new WscpfxgcZdlxModel();
			WscpfxgcFdlxModel fdlxModel = new WscpfxgcFdlxModel();
			ArrayList<String> lxqjlb = new ArrayList<String>();
			String qj = null;
			// ���������
			ArrayList<String> xgr = new ArrayList<String>();
			for (WssscyrModel model : wssscyrModellist) {
				if (model.getSscyr() != null) {
					if (lxqk.get(i).contains(model.getSscyr())) {
						xgr.add(model.getSscyr());
					}
				}
			}
			// if (lxqk.get(i).contains("��")) {
			if (xgr != null) {
				fdlxModel.setXgr(xgr);
				zdlxModel.setXgr(xgr);
			}
			// �����ö��������
			if (lxqk.get(i).contains("���ֺ�") || lxqk.get(i).contains("��������")) {
				qj = "һ����ֺ�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("��ʵ") || lxqk.get(i).contains("̹��")) {
				qj = "��ʵ����������ʵ��̹�ף�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����")) {
				qj = "����̬�Ⱥ�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("���С")) {
				qj = "���С";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("��ͥ") && lxqk.get(i).contains("����")) {
				qj = "��ͥ������";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����")) {
				qj = "����";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("ż��")) {
				qj = "ż��";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����") || lxqk.get(i).contains("����")
					|| lxqk.get(i).contains("�м�")) {
				qj = "�����ˡ������ˡ��м��˷�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("������")
					&& lxqk.get(i).contains("����")
					&& !lxqk.get(i).contains("�����϶�")) {
				qj = "�������й���";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("�������й���")) {
				qj = "�������й���";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����ȡ�ñ������½�")) {
				qj = "����ȡ�ñ������½�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����")) {
				if (lxqk.get(i).contains("����")
						|| lxqk.get(i).contains("����")
						|| (!lxqk.get(i).contains("�ܾ�����") && !lxqk.get(i)
								.contains("����������"))) {
					qj = "��������";
					lxqjlb.add("�ö��������");
					zdlxModel.setQj(qj);
					zdlxModel.setLxqjlb(lxqjlb);
				}
			} else if ((lxqk.get(i).contains("ȫ��") || lxqk.get(i).contains(
					"�󲿷�"))
					&& (lxqk.get(i).contains("�߿�") || lxqk.get(i)
							.contains("����")) && lxqk.get(i).contains("׷��")) {
				qj = "�߿�����ȫ�����ߴ󲿷ֱ�׷��";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("��ȡ����")) {
				qj = "������ȡ���ȴ�ʩ������ʧ";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			}

			// �����ö��������
			else if (lxqk.get(i).contains("�Ӽ�")) {
				qj = "����ǰ���Ӽ�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("ǰ��")) {
				qj = "����ǰ��ǰ��";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("�߷�")) {
				qj = "�߷�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����̬�Ȳ���")) {
				qj = "����̬�Ȳ���";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("��ߴ�")) {
				qj = "��ߴ�";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("�ֶζ���")
					|| lxqk.get(i).contains("��������")) {
				qj = "�ֶζ��ӣ���������";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����Σ�����")) {
				qj = "����Σ�����";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("�ܾ�����")) {
				qj = "�ܾ�����";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("�ܾ��⳥��ʧ")) {
				qj = "�ܾ��⳥��ʧ";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("����")) {
				qj = "���������";
				lxqjlb.add("�ö��������");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			}
			// wscpfxgcModellist.get(wscpfxgcModellist.size() - 1)
			// .getZdlxModel().add(zdlxModel);
			// }
			// else {
			// WscpfxgcFdlxModel fdlxModel = new WscpfxgcFdlxModel();
			// if (xgr.get(0) != null) {
			// fdlxModel.setXgr(xgr);
			// }

			// ���������������
			else if (lxqk.get(i).contains("�ӷ�")) {
				if (lxqk.get(i).contains("Э�ӷ�")) {
					qj = "Э�ӷ�";
					lxqjlb.add("����Ӧ���������");
				} else {
					qj = "�ӷ�";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("����Ӧ���������");
					}
				}
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("����Ӧ���������");
				}
				if (lxqk.get(i).contains("���")) {
					lxqjlb.add("����Ӧ��������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("δ����")) {
				qj = "δ����";
				if (lxqk.get(i).contains("�⴦����")) {
					lxqjlb.add("�⴦�������");
				}
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("����Ӧ���������");
				}
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("����Ӧ���������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("����")) {
				if (lxqk.get(i).contains("����")) {
					qj = "��������";
					if (lxqk.get(i).contains("���")) {
						lxqjlb.add("����Ӧ��������");
					}
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("����Ӧ���������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else if (lxqk.get(i).contains("����")) {
					qj = "���չ���";
					if (lxqk.get(i).contains("���")) {
						lxqjlb.add("����Ӧ��������");
					}
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("����Ӧ���������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("û������𺦵���ֹ��")) {
				qj = "û������𺦵���ֹ��";
				if (lxqk.get(i).contains("���")) {
					lxqjlb.add("����Ӧ��������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("����𺦵���ֹ��")) {
				qj = "����𺦵���ֹ��";
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("����Ӧ���������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("����") || lxqk.get(i).contains("Ͷ��")) {
				if (lxqk.get(i).contains("�ش�����")) {
					qj = "���ײ��ش�����";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("����Ӧ���������");
						lxqjlb.add("�������Լ������");
					}
					if (lxqk.get(i).contains("���")) {
						lxqjlb.add("����Ӧ��������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
				if (lxqk.get(i).contains("�ش�����")) {
					qj = "���ײ��ش�����";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("����Ӧ���������");
						lxqjlb.add("�������Լ������");
					}
					if (lxqk.get(i).contains("���")) {
						lxqjlb.add("����Ӧ��������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else if (lxqk.get(i).contains("���н���")) {
					qj = "���н���������";
					if (lxqk.get(i).contains("���")) {
						lxqjlb.add("����Ӧ��������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else {
					qj = "����";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("����Ӧ���������");
						lxqjlb.add("�������Լ������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("��") || lxqk.get(i).contains("��")
					|| lxqk.get(i).contains("ä��")) {
				qj = "�������ƻ�ä�˷���";
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�������Դ������");
				}
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�������Լ������");
				}
				if (lxqk.get(i).contains("���")) {
					lxqjlb.add("��������������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("Ԥ����")) {
				qj = "Ԥ����";
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�������Դ������");
				}
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�������Լ������");
				}
				if (lxqk.get(i).contains("���")) {
					lxqjlb.add("��������������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("δ��")) {
				qj = "δ�췸";
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�������Դ������");
				}
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�������Լ������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("����")) {
				if (lxqk.get(i).contains("û�з�")) {
					qj = "�������˷��ﱻ��������û�з�����������";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�������Դ������");
					}
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�������Լ������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else if (lxqk.get(i).contains("δ����")) {
					qj = "����δ�����˷���";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�����������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("����")) {
				if (lxqk.get(i).contains("�ش�����")) {
					qj = "�ش�����";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�������Դ������");
					}
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�������Լ������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else {
					qj = "����";
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�������Դ������");
					}
					if (lxqk.get(i).contains("����")) {
						lxqjlb.add("�������Լ������");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("�ڹ������ܹ��̷�����")) {
				qj = "�ڹ������ܹ��̷�����";
				if (lxqk.get(i).contains("���")) {
					lxqjlb.add("��������������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("�۷�")) {
				qj = "�۷�";
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�����������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("�ٷ�") && lxqk.get(i).contains("��Ʒ")) {
				qj = "��Ʒ�ٷ�";
				if (lxqk.get(i).contains("����")) {
					lxqjlb.add("�����������");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			}
			if (fdlxModel.getQj() != null) {
				wscpfxgcModel.getFdlxModel().add(fdlxModel);
			}
			if (zdlxModel.getQj() != null) {
				wscpfxgcModel.getZdlxModel().add(zdlxModel);
			}
			// System.out.println(lxqk.get(i));
		}

		return wscpfxgcModel;
	}
}
