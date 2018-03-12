package nju.software.wsjx.parserule.msysFactorParseRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.formula.functions.Match;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.msysFactorModel.JdcjtsgModel;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.service.impl.jtsg.CmssdpreServiceImpl;

public class JdcjtsgParseRule {

	/**
	 * ��ȡ��������ͨ�¹ʵ�Ҫ�أ����ػ�������ͨ�¹�Ҫ��model
	 * @param wsAnalyse ��������������ֵķֶ���Ϣ���Լ�ÿ�εľ�������
	 * @param wsModel �Ѿ���ȡ��ɵ�����model
	 * @return jdcjtsgModel ��������ͨ�¹�ģ��
	 */
	public JdcjtsgModel getJdcjtsgModel(WsAnalyse wsAnalyse,WsModel wsModel){
		JdcjtsgModel jdcjtsgModel=new JdcjtsgModel();
		jdcjtsgModel=getCommonElement(wsModel, jdcjtsgModel);
		jdcjtsgModel=getCommonDamageElement(wsModel, jdcjtsgModel);
        jdcjtsgModel=getMaimElement(wsModel, jdcjtsgModel);
        jdcjtsgModel=getDeathElement(wsModel, jdcjtsgModel);
		jdcjtsgModel=getPropertyLossElement(wsModel, jdcjtsgModel);
		return jdcjtsgModel;
	}
	
	/**
	 * �Ӱ��������������ȡ����Ҫ��
	 * @param wsModel �Ѿ���ȡ��ɵ�����model
	 * @param jdcjtsgModel ��������ͨ�¹�ģ��
	 * @return
	 */
	public JdcjtsgModel getCommonElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		CmssdpreServiceImpl cmssPre = new CmssdpreServiceImpl();//����������ʵԤ������󣬵����䲿�ֺ���
		List<String> cmssIni = wsModel.getWsajjbqkModel().getCmssd();//������ʵ������
		List<String> cmssList = new ArrayList<String>();//��Ž�ȡ֮��Ĳ�����ʵ������
		

		String ajjbqksegement = wsModel.getWsajjbqSegment();
		String[] ajjbqksegements = ajjbqksegement.split("��");
		
		
		String sgsj=null;//�¹�ʱ��
		String sgdd=null;//�¹ʵص�
		String sgjg=null;//�¹ʾ���
		List<String> sgzr=new ArrayList<>();//�¹�����
		List<String> sgclph=new ArrayList<>();//�塢�¹ʳ����ƺ�
		List<String> sgclsyz=new ArrayList<>();//�����¹ʳ���������
		List<String> sgcljsy=new ArrayList<>();//�ߡ��¹ʳ�����ʻԱ
		List<String> sgcljqxbxgs=new ArrayList<>();//�ˡ��¹ʳ�����ǿ�ձ��չ�˾
		List<String> sgclsyszxbxgs=new ArrayList<>();//�š��¹ʳ�����ҵ�����ձ��չ�˾
		List<String> syszxpcxe=new ArrayList<>();//ʮ����ҵ�������⳥�޶�
		List<String> sgqtpczrzt=new ArrayList<>();//ʮһ���¹������⳥��������
		String ydfpckse=null;//ʮ�����ѵ渶�⳥������
		String dfr=null;//ʮ�����渶��

        String sglx="";//�¹�����
        String shlx="";//�����ͣ���Ϊ�����˲С���������
        String sfCcss="";//�Ƿ��вƲ���ʧ
        String sfsjjqx="";//�Ƿ��Ͻ���ǿ��
        String sfsjsyx="";//�Ƿ��Ͻ���ҵ��
        String hasZrrds="";//�Ƿ��н�ͨ�����϶���
        String tcmz="";//���չ�˾�Ƿ��������
		
		boolean contentFlag=false;//��ȡ��־
		
		for (String it : cmssIni) {//��ȡ�ʵ�������
			if (it.contains("��Ժ�϶���ʵ����")||it.contains("���������")||it.contains("��ʵ������")) {
				contentFlag=true;
			}
			
			if (contentFlag) {
				cmssList.add(it);
//				contentFlag=false;
			}
		}
		if (cmssList.isEmpty()) {
			cmssList=cmssIni;
		}
		
		sgjg=cmssPre.getSgjg(cmssPre.getSgxq(cmssList));//�¹ʾ���
		sgsj=getSgsj(sgjg);//�¹�ʱ��
		sgdd=getSgdd(sgjg);//�¹ʵص�
		sgqtpczrzt=getSgqtpczrzt(wsModel.getWssscyrModels());//�¹������⳥��������		
		
		for (String it : ajjbqksegements) {
//			if (sgsj==null) {
//				sgsj=getSgsj(it);//�¹�ʱ��
//			}
//			if (sgdd==null) {
//				sgdd=getSgdd(it);//�¹ʵص�
//			}
//			if (sgzr.isEmpty()) {
//				sgzr = getSgzr(it);//�¹�����
//			}
			if (sgclph.isEmpty()||sgclph==null) {
				sgclph=getSgclph(it);//�¹ʳ�������
			}
			if (sgclsyz.isEmpty()||sgclsyz==null) {
				sgclsyz=getSgclsyz(it, sgclph);//�¹ʳ���������
			}
//			if (sgcljsy.isEmpty()) {
//				sgcljsy=getSgcljsy(it, sgclph);//�¹ʳ�����ʻԱ
//			}
			if (sgcljqxbxgs.isEmpty()||sgcljqxbxgs==null) {
				sgcljqxbxgs=getJqxbxgs(it, sgclph);//�¹ʳ�����ǿ�ձ��չ�˾
			}
			if (sgclsyszxbxgs.isEmpty()||sgclsyszxbxgs==null) {
				sgclsyszxbxgs=getSzxbxgs(it, sgclph);//�¹ʳ�����ҵ�����ձ��չ�˾
			}
//			if (syszxpcxe.isEmpty()) {
//				syszxpcxe=getSzxpcxe(it, sgclph, sgclsyszxbxgs);//��ҵ�������⳥�޶�
//			}
//			if (ydfpckse==null||ydfpckse=="") {
//				ydfpckse=getYdfpckse(it);//�ѵ渶�⳥������
//			}
//			if (dfr==null||dfr=="") {
//				dfr=getDfr(it);//�渶��
//			}
		}
		for (String it_list : cmssList) {
			if (sgzr.isEmpty()||sgzr==null) {
				sgzr = getSgzr(it_list);//�¹�����
			}
			if (syszxpcxe.isEmpty()||syszxpcxe==null) {
				syszxpcxe=getSzxpcxe(it_list, sgclph, sgclsyszxbxgs);//��ҵ�������⳥�޶�
			}
			if (ydfpckse==null||ydfpckse=="") {
				ydfpckse=getYdfpckse(it_list);//�ѵ渶�⳥������
			}
			if (dfr==null||dfr=="") {
				dfr=getDfr(it_list);//�渶��
			}
            if (shlx == "" || shlx.isEmpty()) {
                if (it_list.contains("�м��⳥��")) {
                    shlx = "�����˲�";
                } else if (it_list.contains("ɥ���")||it_list.contains("�����⳥��")) {
                    shlx = "��������";
                }
            }
            if (sfCcss == "" || sfCcss.isEmpty()) {
                if (it_list.contains("����ά�޷�") || it_list.contains("������Ʒ��ʧ��") || it_list.contains("ʩ�ȷ�")
                        || it_list.contains("�������÷�") || it_list.contains("ͣ��") ||
                        it_list.contains("ͨ������Խ�ͨ���߷�")) {
                    sfCcss = "��";
                }
            }
            if (hasZrrds == "" || !hasZrrds.isEmpty()) {
                if (it_list.contains("�����϶���")) {
                    hasZrrds = "��";
                }
            }

		}
		sgcljsy=getSgcljsy(sgjg, sgclph);//�¹ʳ�����ʻԱ

        String cmssnr = wsModel.getWsajjbqSegment();
        String[] cmssnrs = cmssnr.split("\\n");
        for (String it :
                cmssnrs) {
            if (tcmz == "" || !tcmz.isEmpty()) {
                if (it.contains("����")) {
                    tcmz = "��";
                }
            }
            if (sglx == "" || !sglx.isEmpty()) {
                if (it.contains("������ײ")) {
                    sglx = "����������ײ";
                } else if (it.contains("������ײ")) {
                    sglx = "��������������ײ";
                } else if (it.contains("���г�") || it.contains("�綯��") || it.contains("�ǻ�����")) {
                    sglx = "��������ǻ�������ײ";
                }
            }
        }
        if (sgcljqxbxgs != null && !sgcljqxbxgs.isEmpty()) {
            sfsjjqx = "��";
        } else {
            sfsjjqx = "��";
        }

        if (sgclsyszxbxgs != null && !sgclsyszxbxgs.isEmpty()) {
            sfsjsyx = "��";
        } else {
            sfsjsyx = "��";
        }
        if (sfCcss == "" || sfCcss.isEmpty()) {
            sfCcss = "��";
        }
        if (hasZrrds == "" || !hasZrrds.isEmpty()) {
            hasZrrds = "��";
        }
        if (tcmz == "" || !tcmz.isEmpty()) {
            tcmz = "��";
        }

        if (sgclph.size() > 1) {
            if (!sglx.equals("����������ײ")) {
                sglx = "����������ײ";
            }
        }

		jdcjtsgModel.setSgfssj(sgsj);
		jdcjtsgModel.setSgfsdd(sgdd);
		jdcjtsgModel.setSgjg(sgjg);
		jdcjtsgModel.setSgzrrdqk(sgzr);
		jdcjtsgModel.setSgclph(sgclph);
		jdcjtsgModel.setSgclsyz(sgclsyz);
		jdcjtsgModel.setSgcljsy(sgcljsy);
		jdcjtsgModel.setSgcljqxbxgs(sgcljqxbxgs);
		jdcjtsgModel.setSgclsyszxbxgs(sgclsyszxbxgs);
		jdcjtsgModel.setSyszxpcxe(syszxpcxe);
		jdcjtsgModel.setSgqtpczrzt(sgqtpczrzt);
		jdcjtsgModel.setYdfpckse(ydfpckse);
		jdcjtsgModel.setDfr(dfr);

        jdcjtsgModel.setSglx(sglx);
        jdcjtsgModel.setShlx(shlx);
        jdcjtsgModel.setSfCcss(sfCcss);
        jdcjtsgModel.setSfsjjqx(sfsjjqx);
        jdcjtsgModel.setSfsjsyx(sfsjsyx);
        jdcjtsgModel.setHasZrrds(hasZrrds);
        jdcjtsgModel.setTcmz(tcmz);

		return jdcjtsgModel;
	}
	
	/**
	 * ��ȡһ���˺�����Ҫ��
	 * @param wsModel ����ģ��
	 * @param jdcjtsgModel ��������ͨ�¹�ģ��
	 * @return jdcjtsgModel ��������ͨ�¹�ģ��
	 */
	public JdcjtsgModel getCommonDamageElement(WsModel wsModel,JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("˫�����齹��")&&(cmssnr.contains("��Ժ�϶�����")||cmssnr.contains("���������"))
//				&&(cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("��Ժ�϶�����")||
//				cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("���������"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
//		}else if (cmssnr.contains("��Ժ�϶�����")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
//		}else if (cmssnr.contains("���������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
//		}else if (cmssnr.contains("��ʵ������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
//		}
        if (cmssnr.contains("˫�����齹��") && (cmssnr.contains("��Ժ�϶�����") ||
                cmssnr.contains("���������") )) {
            if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��Ժ�϶�����")) {
                if (cmssnr.indexOf("��Ժ�϶�����") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("���������")) {
                if (cmssnr.indexOf("���������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("���������"));
                }
            }
        } else if (cmssnr.contains("��Ժ�϶�����")) {
            cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
        } else if (cmssnr.contains("���������")) {
            cmssnr = cmssnr.substring(cmssnr.indexOf("���������"));
        } else if (cmssnr.contains("��Ժ�϶���ʵ����")) {
            cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
        }

		String ylfse="";//ʮ�ġ�ҽ�Ʒ�����
		String zysj="";//ʮ�塢סԺʱ��
		String zyhsbzfse="";//ʮ����סԺ��ʳ����������
		String hlq="";//ʮ�ߡ�������
		String hlfse="";//ʮ�ˡ����������
		String yyq="";//ʮ�š�Ӫ����
		String yyfse="";//��ʮ��Ӫ��������
		String wgq="";//��ʮһ������
		String wgfse="";//��ʮ�����󹤷�����
		String jtfse="";//��ʮ������ͨ������
		String zsfse="";//��ʮ�ġ�ס�޷�����

        boolean hlqSplitFlag = false;
		//�Ի��з��ֶ�
        String[] cmssnrs = cmssnr.split("\\n");
        for (String it :
                cmssnrs) {
            boolean twiceFlag = false;
            if (it.contains("�����") && it.contains("����") && it.contains("��Ժ")) {
                if (it.contains("������")) {
                    twiceFlag = true;
                }
                String[] hlfdl = it.split("��");
                for (int i=0;i<hlfdl.length;i++) {
                    if (hlfdl[i].contains("����ϼ�")&&twiceFlag) {
                        hlfse = getJe(hlfdl[i]).get(0)+"Ԫ";
                    }
                }
            }
        }
        
        //�Ծ�ŷֶ�
		cmssnrs=cmssnr.split("��");
		for (String it : cmssnrs) {
			if (ylfse==null||ylfse.isEmpty()) {
				if (it.contains("ҽ�Ʒ�")&&!it.contains("�޶�")) {
					ylfse=getIndemnity(it,"ҽ�Ʒ�");
                } else if (it.contains("ҽҩ��") && it.contains("֧��")) {
                    ylfse = getIndemnity(it, "ҽҩ��");
                }
			}
			if (zyhsbzfse==null||zyhsbzfse.isEmpty()) {
                if (it.contains("סԺ��ʳ������")) {
                    zyhsbzfse=getIndemnity(it, "סԺ��ʳ������");
                } else if (it.contains("��ʳ����") && it.contains("����ȷ��")) {
                    String[] states = it.split("��");
                    for (String its :
                            states) {
                        if (its.contains("��ʳ����") && !its.contains("��") && !its.contains("��")) {
                            zyhsbzfse = getIndemnity(its, "��ʳ����");
                        } else if (its.contains("����")) {
                            zyhsbzfse = getIndemnity(its, "����");
                        }
                    }
                }
            }
			if (hlfse==null||hlfse.isEmpty()) {
                hlfse=getIndemnity(it, "�����");
            }
			if (yyfse==null||yyfse.isEmpty()) {
                if (it.contains("Ӫ����")) {
                    String[] yyfs = it.split("��");
                    for (String its :
                            yyfs) {
                        if (its.contains("�ö�")) {
                            if (its.contains("��")) {
                                its = its.substring(0, its.indexOf("��"));
                                yyfse = getJe(its).get(0)+"Ԫ";
                            } else {
                                yyfse = getJe(its).get(0)+"Ԫ";
                            }
                        }
                    }
                }
                if (yyfse == null || yyfse.isEmpty()) {
                    yyfse = getIndemnity(it, "Ӫ����");
                }
			}
			if (wgfse==null||wgfse.isEmpty()) {
				wgfse=getIndemnity(it, "�󹤷�");
			}
			if (jtfse==null||jtfse.isEmpty()) {
                if (it.contains("��ͨ��")) {
                    String[] jtfs = it.split("��");
                    for (String its :
                            jtfs) {
                        if (its.contains("�ö�")) {
                            if (its.contains("��")) {
                                its = its.substring(0, its.indexOf("��"));
                                jtfse = getJe(its).get(0) + "Ԫ";
                            } else {
                                jtfse = getJe(its).get(0) + "Ԫ";
                            }
                        } else {
                            if (jtfse==null||jtfse.isEmpty()) {
                                jtfse = getIndemnity(its, "��ͨ��");
                            }
                        }
                    }
                }
			}
			if (zsfse==null||zsfse.isEmpty()) {
                if (!it.contains("����")) {
                    zsfse=getIndemnity(it, "ס�޷�");
                }
            }
			if (zysj==null||zysj.isEmpty()) {
				zysj=getZysj(it);
			}
			if (hlq==null||hlq.isEmpty()) {
				hlq=getHlq(it);
			}
            if (cmssnr.contains("��סԺ�ڼ�") || (cmssnr.contains("�����") && cmssnr.contains("������"))) {
			    hlqSplitFlag=true;
            }
			if (yyq==null||yyq.isEmpty()) {
				yyq=getYyq(it);
			}
			if (wgq==null||wgq.isEmpty()) {
				wgq=getWgq(it);
			}
		}

        //�ж�סԺʱ���Ƿ�ֶΣ����ֶν����ʱ�����
        if (hlqSplitFlag) {
            int hlqs = Integer.valueOf(getJe(hlq).get(0));
            int zysjs = 0;
            if (zysj!=null&&!zysj.isEmpty()) {
                zysjs = Integer.valueOf(getJe(zysj).get(0));
            }
            hlq = String.valueOf(zysjs + hlqs) + "��";
        }

		//���������ʵ��û��Ԥ�����ݣ��ٶ�����סԺʱ����ͬ
        if (hlq==null||hlq.isEmpty()) {
            hlq=zysj;
        }
		if (wgq==null||wgq.isEmpty()) {
			wgq=zysj;
		}



		jdcjtsgModel.setZysj(zysj);
		jdcjtsgModel.setYlfse(ylfse);
		jdcjtsgModel.setZyhsbzfse(zyhsbzfse);
		jdcjtsgModel.setHlq(hlq);
		jdcjtsgModel.setHlfse(hlfse);
		jdcjtsgModel.setYyq(yyq);
		jdcjtsgModel.setYyfse(yyfse);
		jdcjtsgModel.setWgq(wgq);
		jdcjtsgModel.setWgfse(wgfse);
		jdcjtsgModel.setJtfse(jtfse);
		jdcjtsgModel.setZsfse(zsfse);
		return jdcjtsgModel;
	}
	
	/**
	 * ��ȡ�����˲�����Ҫ��
	 * @param wsModel ����ģ��
	 * @param jdcjtsgModel ��������ͨ�¹�ģ��
	 * @return jdcjtsgModel ��������ͨ�¹�ģ��
	 */
	public JdcjtsgModel getMaimElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("˫�����齹��")&&(cmssnr.contains("��Ժ�϶�����")||cmssnr.contains("���������"))
//				&&(cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("��Ժ�϶�����")||
//				cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("���������"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
//		}else if (cmssnr.contains("��Ժ�϶�����")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
//		}else if (cmssnr.contains("���������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
//		}else if (cmssnr.contains("��ʵ������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
//		}

        if ((cmssnr.contains("˫�����齹��")||cmssnr.contains("��Ժ�϶�����") )&& (cmssnr.contains("��Ժ�϶���ʵ����") ||
                cmssnr.contains("���������") || cmssnr.contains("��ʵ������"))) {
            if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��Ժ�϶�����")) {
                if (cmssnr.indexOf("��Ժ�϶���ʵ����") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("���������")) {
                if (cmssnr.indexOf("���������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("���������"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��ʵ������")) {
                if (cmssnr.indexOf("��ʵ������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��ʵ������"));
                }
            } else if (cmssnr.contains("��Ժ�϶�����") && cmssnr.contains("��Ժ�϶���ʵ����")) {
                cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
            }
        }else if (cmssnr.contains("���������")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
        }else if (cmssnr.contains("��Ժ�϶���ʵ����")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
        }else if (cmssnr.contains("��ʵ������")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
        }

		String cjdjjdjlsj="";//��ʮ�塢�м��ȼ���������ʱ��
		String cjdjjdjl="";//��ʮ�����м��ȼ���������
		String bfyrshfse="";//��ʮ�ߡ������������������
		String cjpcjse="";//��ʮ�ˡ��м��⳥������
		String jsshfwj="";//��ʮ�š������𺦸�ο��
		String cjshfzjfse="";//��ʮ���м�������߷�����
        String dchhlylcdjdyj="";//��ʮһ�����к��������̶ȼ������
        String dchhlfse="";//��ʮ�������к��������

        String[] cmssnrs = cmssnr.split("��");
        for (String it : cmssnrs) {
            if (bfyrshfse==null||bfyrshfse.isEmpty()) {
                if (it.contains("�������������")&&!it.contains("����֧��")) {
                    bfyrshfse=getIndemnity(it, "�������������");
                }
            }
            if (cjpcjse==null||cjpcjse.isEmpty()) {
				cjpcjse=getIndemnity(it, "�м��⳥��");
			}
			if (jsshfwj==null||jsshfwj.isEmpty()) {
                if (it.contains("����ο��")&&!it.contains("����")) {
                    jsshfwj=getIndemnity(it, "����ο��");
                } else if (it.contains("�����𺦸�ο��")&&!it.contains("����")){
                    jsshfwj = getIndemnity(it, "�����𺦸�ο��");
                }
            }
			if (cjshfzjfse==null||cjshfzjfse.isEmpty()) {
				if (it.contains("�м�������߷�")) {
					cjshfzjfse=getIndemnity(it, "�м�������߷�");
				}else if (it.contains("�м������߷�")) {
					cjshfzjfse=getIndemnity(it, "�м������߷�");
                } else if (it.contains("�м��������߷�")) {
                    cjshfzjfse = getIndemnity(it, "�м��������߷�");
                }
			}
			if (cjdjjdjlsj==null||cjdjjdjlsj.isEmpty()) {
				cjdjjdjlsj=getCjdjjdjlsj(it);
			}
			if (cjdjjdjl==null||cjdjjdjl.isEmpty()) {
				cjdjjdjl=getCjdjjdjl(it);
			}
			if (dchhlylcdjdyj==null||dchhlylcdjdyj.isEmpty()) {
				dchhlylcdjdyj=getDchhlylcdjdyj(it);
			}
			if (dchhlfse==null||dchhlfse.isEmpty()) {
				dchhlfse=getDchhlfse(it);
			}
		}

        String cpjgnr = wsModel.getWscpjgSegment();
        String[] cpjgnrs = cpjgnr.split("��");
        for (String it :cpjgnrs) {
            if (jsshfwj==null||jsshfwj.isEmpty()) {
                if (it.contains("����ο��")&&!it.contains("����")) {
                    jsshfwj=getIndemnity(it, "����ο��");
                } else if (it.contains("�����𺦸�ο��")&&!it.contains("����")){
                    jsshfwj = getIndemnity(it, "�����𺦸�ο��");
                }
            }
        }
		jdcjtsgModel.setCjdjjdjlsj(cjdjjdjlsj);
		jdcjtsgModel.setCjdjjdjl(cjdjjdjl);
		jdcjtsgModel.setBfyrshfse_meim(bfyrshfse);
		jdcjtsgModel.setCjpcjse(cjpcjse);
		jdcjtsgModel.setJsshfwj_meim(jsshfwj);
		jdcjtsgModel.setCjshfzjfse(cjshfzjfse);
		jdcjtsgModel.setDchhlylcdjdyj(dchhlylcdjdyj);
		jdcjtsgModel.setDchhlfse(dchhlfse);

		return jdcjtsgModel;
	}
	
	/**
	 * ��ȡ������������Ҫ��
	 * @param wsModel ����ģ��
	 * @param jdcjtsgModel ��������ͨ�¹�ģ��
	 * @return jdcjtsgModel��������ͨ�¹�ģ��
	 */
	public JdcjtsgModel getDeathElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("˫�����齹��")&&(cmssnr.contains("��Ժ�϶�����")||cmssnr.contains("���������"))
//				&&(cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("��Ժ�϶�����")||
//				cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("���������"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
//		}else if (cmssnr.contains("��Ժ�϶�����")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
//		}else if (cmssnr.contains("���������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
//		}else if (cmssnr.contains("��ʵ������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
//		}

        if ((cmssnr.contains("˫�����齹��")||cmssnr.contains("��Ժ�϶�����") )&& (cmssnr.contains("��Ժ�϶���ʵ����") ||
                cmssnr.contains("���������") || cmssnr.contains("��ʵ������"))) {
            if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��Ժ�϶�����")) {
                if (cmssnr.indexOf("��Ժ�϶���ʵ����") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("���������")) {
                if (cmssnr.indexOf("���������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("���������"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��ʵ������")) {
                if (cmssnr.indexOf("��ʵ������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��ʵ������"));
                }
            } else if (cmssnr.contains("��Ժ�϶�����") && cmssnr.contains("��Ժ�϶���ʵ����")) {
                cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
            }
        }else if (cmssnr.contains("���������")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
        }else if (cmssnr.contains("��Ժ�϶���ʵ����")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
        }else if (cmssnr.contains("��ʵ������")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
        }

		
		String swpcjse="";//��ʮ�塢�����⳥������
		String bfyrshfse="";//��ʮ�ġ������������������
		String jsshfwjse="";//��ʮ�ߡ������𺦸�ο������
		String szfse="";//��ʮ�ˡ�ɥ�������
		String shrqsjtfse="";//��ʮ�š��ܺ�����������ɥ������֧���Ľ�ͨ������
		String shrqszsfse="";//��ʮ���ܺ�����������ɥ������֧����ס�޷�����
		String shrqswgfse="";//��ʮһ���ܺ�����������ɥ������֧�����󹤷�����
		
		String[] cmssnrs = cmssnr.split("��");
		for (String it : cmssnrs) {
            String bfyrshfse2 = "";
			if (swpcjse==null||swpcjse.isEmpty()) {
				swpcjse=getIndemnity(it, "�����⳥��");
			}
            if (bfyrshfse==null||bfyrshfse.isEmpty()) {
                if (it.contains("�������������")&&!it.contains("����֧��")) {
                    if (!it.contains("��")) {
                        bfyrshfse=getIndemnity(it, "�������������");
                    } else {
                        String[] shfs = it.split("��");
                        List<String> bfyrshfs = new ArrayList<>();
                        for (String its : shfs) {
                            String temp = getIndemnity(its, "�������������");
                            if (temp != null && !temp.isEmpty()) {
                                bfyrshfs.add(temp);
                            }
                        }
                        if (bfyrshfs.size() == 1) {
                            bfyrshfse = bfyrshfs.get(0);
                        } else if (bfyrshfs.size() == 2) {
                            bfyrshfse = jePlus(bfyrshfs.get(0), bfyrshfs.get(1));
                        } else if (bfyrshfs.size() >= 3) {
                            bfyrshfse = jePlus(bfyrshfs.get(0), bfyrshfs.get(1));
                            for (int i=2;i<bfyrshfs.size();i++) {
                                if (bfyrshfs.get(i) != null && !bfyrshfs.isEmpty()) {
                                    bfyrshfse = jePlus(bfyrshfse, bfyrshfs.get(i));
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (jsshfwjse==null||jsshfwjse.isEmpty()) {
                if (it.contains("����ο��")&&!it.contains("����")) {
                    jsshfwjse=getIndemnity(it, "����ο��");
                } else if (it.contains("�����𺦸�ο��")&&!it.contains("����")){
                    jsshfwjse = getIndemnity(it, "�����𺦸�ο��");
                }
            }
			if (szfse==null||szfse.isEmpty()) {
				szfse=getIndemnity(it, "ɥ���");
			}
			if (it.contains("ɥ��")) {
				if (shrqsjtfse==null||shrqsjtfse.isEmpty()) {
					shrqsjtfse=getIndemnity(it, "��ͨ��");
				}
				if (shrqszsfse==null||shrqszsfse.isEmpty()) {
					shrqszsfse=getIndemnity(it, "ס�޷�");
				}
				if (shrqswgfse==null||shrqswgfse.isEmpty()) {
					shrqswgfse=getIndemnity(it, "�󹤷�");
				}
			}
		}

		jdcjtsgModel.setSwpcjse(swpcjse);
		jdcjtsgModel.setJsshfwjse_death(jsshfwjse);
		jdcjtsgModel.setBfyrshfse_death(bfyrshfse);
		jdcjtsgModel.setSzfse(szfse);
		jdcjtsgModel.setShrqsblszsyzcdwgfse(shrqswgfse);
		jdcjtsgModel.setShrqsjtfse(shrqsjtfse);
		jdcjtsgModel.setShrqszsfse(shrqszsfse);

		return jdcjtsgModel;
	}
	
	/**
	 * ��ȡ�Ʋ���ʧ����Ҫ��
	 * @param wsModel ����ģ��
	 * @param jdcjtsgModel ��������ͨ�¹�ģ��
	 * @return jdcjtsgModel ��������ͨ�¹�ģ��
	 */
	public JdcjtsgModel getPropertyLossElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("˫�����齹��")&&(cmssnr.contains("��Ժ�϶�����")||cmssnr.contains("���������"))
//				&&(cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("��Ժ�϶�����")||
//				cmssnr.indexOf("˫�����齹��")>cmssnr.indexOf("���������"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
//		}else if (cmssnr.contains("��Ժ�϶�����")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
//		}else if (cmssnr.contains("���������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
//		}else if (cmssnr.contains("��ʵ������")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
//		}

        if ((cmssnr.contains("˫�����齹��")||cmssnr.contains("��Ժ�϶�����") )&& (cmssnr.contains("��Ժ�϶���ʵ����") ||
                cmssnr.contains("���������") || cmssnr.contains("��ʵ������"))) {
            if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��Ժ�϶�����")) {
                if (cmssnr.indexOf("��Ժ�϶���ʵ����") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("���������")) {
                if (cmssnr.indexOf("���������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("���������"));
                }
            } else if (cmssnr.contains("˫�����齹��") && cmssnr.contains("��ʵ������")) {
                if (cmssnr.indexOf("��ʵ������") > cmssnr.indexOf("˫�����齹��")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("˫�����齹��"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("��ʵ������"));
                }
            } else if (cmssnr.contains("��Ժ�϶�����") && cmssnr.contains("��Ժ�϶���ʵ����")) {
                cmssnr = cmssnr.substring(cmssnr.indexOf("��Ժ�϶�����"));
            }
        }else if (cmssnr.contains("���������")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("���������"));
        }else if (cmssnr.contains("��Ժ�϶���ʵ����")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("��Ժ�϶���ʵ����"));
        }else if (cmssnr.contains("��ʵ������")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("��ʵ������"));
        }

		String clwxfse="";//ʮ�ġ�����ά�޷�����
		String czwpssfse="";//ʮ�塢������Ʒ��ʧ������
		String sjfse="";//ʮ����ʩ�ȷ�����
		String clczfse="";//ʮ�ߡ��������÷�����
		String tyqj="";//ʮ�ˡ�ͣ���ڼ�
		String tyssse="";//ʮ�š�ͣ����ʧ����
		String tctdxjtgjfyse="";//��ʮ��ͨ������Խ�ͨ���߷�������
		
		String[] cmssnrs=cmssnr.split("��");
		for (String it : cmssnrs) {
			if (clwxfse==null||clwxfse.isEmpty()) {
				clwxfse=getIndemnity(it, "ά�޷�");
			}
			if (sjfse==null||sjfse.isEmpty()) {
				if (it.contains("�ϳ���")) {
					sjfse=getIndemnity(it, "�ϳ���");
				}else if (it.contains("ʩ�ȷ�")) {
					sjfse=getIndemnity(it, "ʩ�ȷ�");
				}
			}
			if (czwpssfse==null||czwpssfse.isEmpty()) {
				czwpssfse=getIndemnity(it, "������Ʒ��ʧ��");
			}
			if (clczfse==null||clczfse.isEmpty()) {
				clczfse=getIndemnity(it, "�������÷�");
			}
			if (tctdxjtgjfyse==null||tctdxjtgjfyse.isEmpty()) {
				tctdxjtgjfyse=getIndemnity(it, "ͨ������Խ�ͨ���߷�");
			}
			if (tyssse==null||tyssse.isEmpty()) {
				if (!(it.contains("��ͬ��")||it.contains("��֧��"))) {
					if (it.contains("ͣ����ʧ")) {
						tyssse=getIndemnity(it, "ͣ����ʧ");
					}else if (it.contains("ͣ����ʧ��")&&cmssnr.contains("��Ժ����")&&
							cmssnr.contains("ȷ��")) {
						tyssse=getIndemnity(it, "ͣ����ʧ��");
					}else if (it.contains("ͣ����ʧ��")) {
						tyssse=getIndemnity(it, "ͣ����ʧ��");
					}else if (it.contains("ͣ��")) {
						tyssse=getIndemnity(it, "ͣ��");
					}
				}
			}
			if (tyqj==null||tyqj.isEmpty()) {
				tyqj=getTysj(it);
			}
		}
		//��֤�ݶ���ȡͣ����Ϣ
        List<String> zjd = wsModel.getWsajjbqkModel().getZjd();
        if (zjd!=null&&!zjd.isEmpty()) {
            for (String it : zjd) {
                String[] its = it.split("��");
                for (String itss : its) {
                    if (tyqj == null || tyqj.isEmpty()) {
                        tyqj = getTysj(itss);
                    }
                }
            }
        }
        //���з���������ȡδ��ȡ��Ϣ
		String cpfxgc=wsModel.getWscpfxgcSegment();
		String[] cpfxgcs=cpfxgc.split("��");
		for (String it : cpfxgcs) {
			if (tyqj==null||tyqj.isEmpty()) {
				tyqj=getTysj(it);
			}
			if (tyssse==null||tyssse.isEmpty()) {
				if (!(it.contains("��ͬ��")||it.contains("��֧��"))) {
					if (it.contains("ͣ����ʧ")) {
						tyssse=getIndemnity(it, "ͣ����ʧ");
					}else if (it.contains("ͣ����ʧ��")&&(cmssnr.contains("��Ժ����")&&
							cmssnr.contains("ȷ��")||cmssnr.contains("��Ժ����ȷ��"))) {
						tyssse=getIndemnity(it, "ͣ����ʧ��");
					}else if (it.contains("ͣ����ʧ��")) {
						tyssse=getIndemnity(it, "ͣ����ʧ��");
					}else if (it.contains("ͣ��")) {
						tyssse=getIndemnity(it, "ͣ��");
					}
				}
			}
		}
				
		//�������������������ȡ��Ϣ���ʹӲ��н������ȡ
		String cpjgnr=wsModel.getWscpjgSegment();
		String[] cpjgnrs=cpjgnr.split("��");
		for (String it : cpjgnrs) {
			if (tyssse==null||tyssse.isEmpty()) {
				if (!(it.contains("��ͬ��")||it.contains("��֧��"))) {
					if (it.contains("ͣ����ʧ")) {
						tyssse=getIndemnity(it, "ͣ����ʧ");
					}else if (it.contains("ͣ����ʧ��")&&cmssnr.contains("��Ժ����")&&
							cmssnr.contains("ȷ��")) {
						tyssse=getIndemnity(it, "ͣ����ʧ��");
					}else if (it.contains("ͣ����ʧ��")) {
						tyssse=getIndemnity(it, "ͣ����ʧ��");
					}else if (it.contains("ͣ��")) {
						tyssse=getIndemnity(it, "ͣ��");
					}
				}
			}
		}

		jdcjtsgModel.setClwxfse(clwxfse);
		jdcjtsgModel.setCzwpssfse(czwpssfse);
		jdcjtsgModel.setSjfse(sjfse);
		jdcjtsgModel.setClczfse(clczfse);
		jdcjtsgModel.setTyqj(tyqj);
		jdcjtsgModel.setTyssse(tyssse);
		jdcjtsgModel.setTctdxjtgjfyse(tctdxjtgjfyse);

		return jdcjtsgModel;
	}
	
	/**
	 * ��ȡͣ��ʱ��
	 * @param cmssnr ������ʵ������
	 * @return ͣ��ʱ��
	 */
	public String getTysj(String cmssnr) {
		String tysj = null;
		if (cmssnr.contains("��")&&cmssnr.contains("��")&&cmssnr.contains("ά��")) {
			tysj=cmssnr.substring(cmssnr.indexOf("��")+1,cmssnr.indexOf("ά��"));
		}else if (cmssnr.contains("ͣ����ʧ��")&&cmssnr.contains("��")&&cmssnr.contains("��")) {
			tysj=cmssnr.substring(cmssnr.indexOf("��")+1,cmssnr.indexOf("��"));
			if (tysj.contains("*")) {
				tysj.replaceAll("\\*", "*");
			}
			tysj=tysj.substring(tysj.indexOf("*")+1,tysj.indexOf("��"));
			tysj=tysj+"��";
		}else if (cmssnr.contains("ͣ������")&&cmssnr.contains("������")) {
			tysj=cmssnr.substring(cmssnr.indexOf("��"));
		}
		return tysj;
	}

    /**
     * ��������XXXԪ���ַ�����ֵ�������
     * @param money1 ��������
     * @param money2 ��������
     * @return ��ֵ������Ӻ���ַ���
     */
    public String jePlus(String money1, String money2) {
        String money1_t = getJe(money1).get(0);
        String money2_t = getJe(money2).get(0);
        int money1_i = Integer.valueOf(money1_t);
        int money2_i = Integer.valueOf(money2_t);
        int money_i = money1_i + money2_i;
        String money = String.valueOf(money_i)+"Ԫ";
        return money;
    }

	/**
	 * ��ȡ���к��������
	 * @param cmssnr ������ʵ������
	 * @return ���к��������
	 */
	public String getDchhlfse(String cmssnr) {
		// TODO �ҵ����ж��������ȡ�߼�
		return null;
	}
	
	/**
	 * ��ȡ���к��������̶����,����Ϊ�����ܷ���������������
	 * @param cmssnr ������ʵ������
	 * @return ���к��������̶����
	 */
	public String getDchhlylcdjdyj(String cmssnr) {
		// TODO �ҵ����ж��������ȡ�߼�
		return null;
	}
	
	/**
	 * ��òм��ȼ���������ʱ��
	 * @param cmssnr ������ʵ����
	 * @return �м��ȼ���������ʱ��
	 */
	public String getCjdjjdjlsj(String cmssnr) {
		String cjdjjdjlsj="";
		if (cmssnr.contains("���������")&&cmssnr.contains("��")&&
				cmssnr.contains("��")&&cmssnr.contains("��")||(cmssnr.contains("��") && cmssnr.contains("��"))) {
			cjdjjdjlsj=cmssnr.substring(0, cmssnr.indexOf("��")+1);
//			cjdjjdjlsj=getDate(cjdjjdjlsj);

            if (cjdjjdjlsj == null || cjdjjdjlsj.isEmpty()) {
                if (cmssnr.contains("��") && cmssnr.contains("��") &&
                        cmssnr.contains("��") && cmssnr.contains("��") || cmssnr.contains("��") && cmssnr.contains("��") ||
                        cmssnr.contains("[") && cmssnr.contains("]")) {
                    if (cmssnr.contains("��")) {
                        cjdjjdjlsj = cmssnr.substring(cmssnr.indexOf("��") + 1, cmssnr.indexOf("��"));
                    }
                }
            } else if (cjdjjdjlsj.contains("��")){
                cjdjjdjlsj = cjdjjdjlsj.substring(cjdjjdjlsj.lastIndexOf("��") + 1);
            }
		}

		return cjdjjdjlsj;
	}
	
	/**
	 * ��þ����е�ʱ��
	 * @param cmssnr ������ʱ������
	 * @return �����е�ʱ��
	 */
	public String getDate(String cmssnr) {
		String number = "";
		int index = -1;
		char[] chars=cmssnr.toCharArray();
		for (int j=chars.length-1;j>=0;j--){
			if(!(Character.isDigit(cmssnr.charAt(j)) || (cmssnr.charAt(j) == '��') ||
					(cmssnr.charAt(j) == '��') || (cmssnr.charAt(j) == '��'))){
				index = j;
			}

//            if (!(Character.isDigit(cmssnr.charAt(j)) && cmssnr.charAt(j) == '��' &&
//                    cmssnr.charAt(j) == '��' && cmssnr.charAt(j) == '��')) {
//                index = j;
//            }
			
			String je_toAdd = StringUtil.ToDBC(cmssnr.substring(index+1, cmssnr.length()));
		    je_toAdd = je_toAdd.replaceAll(",", "");
		    	
		    if(!StringUtil.isBlank(je_toAdd)){
		    	number=je_toAdd.toString();
		    	break;
		    }
		}
		return number;
	}
	
	/**
	 * ��òм��ȼ���������
	 * @param cmssnr ������ʵ��
	 * @return �м��ȼ���������
	 */
	public String getCjdjjdjl(String cmssnr) {
		String cjdjjdjl = "";
		if (cmssnr.contains("���������")&&cmssnr.contains("��")) {
			if (cmssnr.contains("����") && cmssnr.contains("�˲�")) {
				cjdjjdjl = cmssnr.substring(cmssnr.lastIndexOf("����")+2, cmssnr.lastIndexOf("�˲�")+2);
			} else if (cmssnr.contains("�˲�")) {
                cjdjjdjl=cmssnr.substring(cmssnr.lastIndexOf("Ϊ")+1);
                if (cjdjjdjl.contains("��")) {
                    cjdjjdjl=cjdjjdjl.substring(0,cjdjjdjl.indexOf("��"));
                }
            }
        }else if (cmssnr.contains("�˲�")&&cmssnr.contains("��")) {
			if (cmssnr.contains("�ȼ�")) {
				cjdjjdjl=cmssnr.substring(cmssnr.lastIndexOf("Ϊ")+1);
			}
        }
		return cjdjjdjl;
	}
	
	/**
	 * �������
	 * @param cmssnr ������ʵ����
	 * @return ����
	 */
	public String getWgq(String cmssnr) {
		String wgq="";
        if (cmssnr.contains("ɥ���")||cmssnr.contains("�����⳥��")) {
            wgq="�����������������ڡ�";
        }else if (cmssnr.contains("��")) {
			if (cmssnr.contains("ʱ��")&&cmssnr.contains("����")) {
				wgq=cmssnr.substring(cmssnr.indexOf("����")+2, cmssnr.indexOf("��"));
			}else if (cmssnr.contains("ʱ��")&&cmssnr.contains("��")) {
				wgq=cmssnr.substring(cmssnr.lastIndexOf("��ʱ��")+4, cmssnr.indexOf("��"))+"��";
			}else if (cmssnr.contains("ʱ��")&&cmssnr.contains("��")) {
				wgq=cmssnr.substring(cmssnr.lastIndexOf("��ʱ��")+4, cmssnr.indexOf("��"))+"��";
			}
		}
		return wgq;
	}
	
	/**
	 * ���Ӫ����
	 * @param cmssnr ������ʵ����
     * @return Ӫ����
	 */
	public String getYyq(String cmssnr) {
		String yyq="";
		if (cmssnr.contains("ɥ���")||cmssnr.contains("�����⳥��")) {
			yyq="��������������Ӫ���ڡ�";
		}else if (cmssnr.contains("Ӫ����")&&cmssnr.contains("��ع涨")) {
            String[] yyqs = cmssnr.split("��");
            for (String it :
                    yyqs) {
                if (!it.contains("����")) {
                    if (it.contains("Ӫ����") && it.contains("��")) {
                        yyq = getDate(it)+"��";
                    }
                }
            }
        }
		return yyq;
	}
	
	/**
	 * ��û�����
	 * @param cmssnr ������ʵ������
	 * @return ������
	 */
	public String getHlq(String cmssnr) {
		String hlq="";
		if (cmssnr.contains("ɥ���")||cmssnr.contains("�����⳥��")) {
			hlq="�������������޻����ڡ�";
        } else if (!cmssnr.contains("���")&&!cmssnr.contains("ί��")) {
            if (cmssnr.contains("֤��")&&cmssnr.contains("��")&&cmssnr.contains("��")) {
                hlq=cmssnr.substring(cmssnr.indexOf("��")+1,cmssnr.indexOf("��"));
            }else if (cmssnr.contains("����")&&cmssnr.contains("����")&&cmssnr.contains("��")) {
                hlq=cmssnr.substring(cmssnr.indexOf("��������")+4, cmssnr.lastIndexOf("��"))+"��";
            }else if (cmssnr.contains("����") && cmssnr.contains("��") && cmssnr.contains("��") && cmssnr.contains("��") && cmssnr.contains("+")) {
                hlq = cmssnr.substring(cmssnr.indexOf("��"), cmssnr.lastIndexOf("��"));
                List<String> days = getDay(hlq);
                int day = 0;
                for (String it : days) {
                    if (it.contains(" ")) {
                        it.replaceAll(" ", "");
                    }
                    if (Double.valueOf(it) != 365 && days.size() > 1) {
                        day += Double.valueOf(it);
                    } else if (days.size() == 1) {
                        day += Double.valueOf(it);
                    }
                }
                hlq = String.valueOf(day) + "��";
            } else if (cmssnr.contains("����") && cmssnr.contains("�ڼ�")&&!cmssnr.contains("����")&&!cmssnr.contains("��")) {
                hlq = getJe(cmssnr).get(0)+"��";
            }
        }
        return hlq;
	}
	
	/**
	 * ���סԺʱ��
	 * @param cmssnr ������ʵ������
	 * @return סԺʱ��
	 */
	public String getZysj(String cmssnr) {
		String zysj="";
		if (cmssnr.contains("ɥ���")||cmssnr.contains("�����⳥��")) {
			zysj="��������������סԺʱ�䡣";
        } else if (!cmssnr.contains("���")) {
            if (cmssnr.contains("����")&&cmssnr.contains("סԺ")&&cmssnr.contains("��")) {
                if (cmssnr.contains("סԺ����")) {
//				zysj=cmssnr.substring(cmssnr.indexOf("סԺ����")+4,cmssnr.indexOf("��"));
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("סԺ����")+4,cmssnr.lastIndexOf("��"))+"��";
                }else {
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("סԺ")+2,cmssnr.indexOf("��"))+"��";
                }
            }else if (cmssnr.contains("��ʳ����")&&cmssnr.contains("סԺ")&&cmssnr.contains("��")&&
                    ((!cmssnr.contains("����")&&!cmssnr.contains("����"))||(cmssnr.contains("����")&&(cmssnr.contains("�Ͽ�")||cmssnr.contains("֧��"))))) {
                if (cmssnr.contains("סԺ����")) {
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("סԺ����")+4,cmssnr.lastIndexOf("��"))+"��";
                }else if (cmssnr.contains("����")) {
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("����")+2, cmssnr.indexOf("��"))+"��";
                }else {
//                    zysj=cmssnr.substring(cmssnr.lastIndexOf("סԺ")+2,cmssnr.indexOf("��"))+"��";
                    cmssnr = cmssnr.substring(cmssnr.indexOf("סԺ") + 2);
                    zysj = cmssnr.substring(0, cmssnr.indexOf("��")) + "��";
                }
            } else if (cmssnr.contains("סԺ") && cmssnr.contains("֧��")&&!cmssnr.contains("�����")) {
                if (cmssnr.contains("סԺ�ڼ�")&&cmssnr.contains("��")) {
                    zysj = cmssnr.substring(cmssnr.lastIndexOf("סԺ�ڼ�") + 4, cmssnr.indexOf("��")) + "��";
                }
            } else if (cmssnr.contains("סԺ") && cmssnr.contains("֧��")&&cmssnr.contains("����ѷ�Ʊ")) {
                if (cmssnr.contains("סԺ�ڼ�")&&cmssnr.contains("��")) {
                    zysj = cmssnr.substring(cmssnr.lastIndexOf("סԺ�ڼ�") + 4, cmssnr.indexOf("��")) + "��";
                }
            }
        }
		return zysj;
	}
	
	/**
	 * ��ø����⳥���
	 * @param cmssnr ������ʵ������
	 * @param moneyType ��Ҫ��ȡ���⳥������
	 * @return �⳥���
	 */
	private String getIndemnity(String cmssnr, String moneyType) {
		String money=null;
		String[] cmssnrs = cmssnr.split("��|��");
		for (String it : cmssnrs) {
			if (it.contains(moneyType)) {
				List<String> moneys = getJe(it);
				if (!moneys.isEmpty()) {
					money=moneys.get(0)+"Ԫ";
				}
			}
		}
		return money;
	}

	/**
	 * ��ȡ�¹�ʱ��		
	 * @param it Ŀ�����
	 * @return �¹�ʱ��
     */
	private String getSgsj(String it) {
		String sgsj="δ�ἰ";
		String[] nr=it.split("��|��");
		for(int i=0;i<nr.length;i++){
			if(nr[i].contains("��")&&nr[i].contains("��")&&nr[i].contains("��")&&nr[i].contains("ʱ")&&nr[i].contains("��")){
				if(nr[i].contains("��"))
					sgsj=nr[i].substring(nr[i].indexOf("��")+1);
				else 
					sgsj=nr[i];
				break;
			}				
		}
		return sgsj;
	}

	/**
	 * ��ȡ�¹��⳥��������
	 * @param wssscyrModellist �������ϲ�����ģ��
	 * @return �¹��⳥��������
	 */
	public List<String> getSgqtpczrzt(List<WssscyrModel> wssscyrModellist) {
		List<String> sgpczrzt=new ArrayList<>();
		for(int i=0;i<wssscyrModellist.size();i++){
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("����")){
				if (!sscyr.getSscyr().contains("��˾")) {
					sgpczrzt.add(sscyr.getSscyr());
				}
			}
		}
		return sgpczrzt;
	}
	
	/**
	 * ��ȡ�渶��	
	 * @param cmssnr ������ʵ������
	 * @return �渶��
     */
	public String getDfr(String cmssnr) {
		String dfr="";
		String[] cmssnrs=cmssnr.split("��");
		for (String it : cmssnrs) {
			if (it.contains("�¹ʷ�����")) {
				if (it.contains("��")&&it.contains("֧��")) {
					dfr=it.substring(it.lastIndexOf("��")+1, it.indexOf("��"));
				}else if (it.contains("��ȡ")&&it.contains("ԭ��")&&it.contains("����")) {
					dfr=it.substring(it.indexOf("����")+2);
				}else if (it.contains("ϵ")&&it.contains("֧��")) {
					dfr=it.substring(it.indexOf("ϵ")+1,it.lastIndexOf("֧��"));
				}
			}
		}
		dfr=removeLastNum(dfr);
		return dfr;
	}
	
	/**
	 * ȥ����������������Ϣ
	 * @param target ��ȥ��������Ϣ�����
	 * @return ����
	 */
	public String removeLastNum(String target) {
		String[] targets=target.split("Ԫ|��Ԫ|����");
		char[] target2char = targets[0].toCharArray();
		int index=-1;
		for (int i = target2char.length-1; i > 0 ; i--) {
			if(!(Character.isDigit(target2char[i]))){
	    		index=i;
	    		break;
			}
		}
		return target=target.substring(0,index+1);
	}
	
	/**
	 * ��ȡ�ѵ渶�⳥������
	 * @param cmssnr ������ʵ������
	 * @return �ѵ渶�⳥������
	 */
	public String getYdfpckse(String cmssnr) {
		String ydfpck="";
		String[] cmssnrs=cmssnr.split("��");
		for (String it : cmssnrs) {
			if (it.contains("���")&&it.contains("�¹ʷ�����")&&
					(it.contains("֧��")||it.contains("��ȡ"))) {
				List<String> moneyList = getJe(it);
				if (moneyList.size()>0) {
					ydfpck=moneyList.get(0)+"Ԫ";
				}
			}else if (it.contains("�¹ʷ�����")&&
					(it.contains("֧��")||it.contains("��ȡ"))) {
				List<String> moneyList = getJe(it);
				if (moneyList.size()>0) {
					ydfpck=moneyList.get(0)+"Ԫ";
				}
			}
		}
		return ydfpck;
	}

	/**
	 * ��ȡ��ҵ�����ձ����޶�
	 * @param cmssnr ������ʵ������
	 * @param sgclph �¹ʳ�������
	 * @param sgclszxbxgs �¹ʳ��������ձ��չ�˾
	 * @return ��ҵ�����ձ����޶�
	 */
	public List<String> getSzxpcxe(String cmssnr, List<String> sgclph, List<String> sgclszxbxgs) {
		List<String> szxpcxe = new ArrayList<>();
		String[] cmssnrs = cmssnr.split("��");
		
		for (int i=0;i<cmssnrs.length;i++) {
			String it_nr = cmssnrs[i];
			int jqxFlag=0;
			int szxFlag=0;
			if (it_nr.contains("���ս��")) {
				List<String> moneyList=getJe(it_nr);
//				���һ�������д��ڽ�ǿ�պ���ҵ����������������ж��������ֵ�˳��
				if ((it_nr.contains("����������ǿ�Ʊ���")||it_nr.contains("��ͨ�¹�����ǿ�Ʊ���")||it_nr.contains("��ǿ��"))&&
						(it_nr.contains("��ҵ����")||it_nr.contains("��ҵ������")||it_nr.contains("��������ҵ���α���"))) {
					
					if (it_nr.contains("����������ǿ�Ʊ���")) {
						jqxFlag=it_nr.indexOf("����������ǿ�Ʊ���");
					}else if (it_nr.contains("��ͨ�¹�����ǿ�Ʊ���")) {
						jqxFlag=it_nr.indexOf("��ͨ�¹�����ǿ�Ʊ���");
					}else if (it_nr.contains("��ǿ��")) {
						jqxFlag=it_nr.indexOf("��ǿ��");
					}
					
					if (it_nr.contains("��ҵ����")) {
						szxFlag=it_nr.indexOf("��ҵ����");
					}else if (it_nr.contains("��ҵ������")) {
						szxFlag=it_nr.indexOf("��ҵ������");
					}else if (it_nr.contains("��������ҵ���α���")) {
						szxFlag=it_nr.indexOf("��������ҵ���α���");
					}
					
//					�жϽ�ǿ�պ���ҵ������˳�򣬰���洢����
					for (int j = 0; j < sgclszxbxgs.size(); j++) {
						for (String it_cp : sgclph) {
							if (sgclszxbxgs.get(j).contains(it_cp)) {
								if (jqxFlag<szxFlag) {
//									szxpcxe.add(jeFilter(moneyList.get(1))+"Ԫ");
									szxpcxe.add(it_cp+"�������⳥��ȣ�"+moneyList.get(1)+"Ԫ");
								}else {
//									szxpcxe.add(jeFilter(moneyList.get(0))+"Ԫ");
									szxpcxe.add(it_cp+"�������⳥��ȣ�"+moneyList.get(0)+"Ԫ");
								}
							}
						}
					}
				}else {
//					û�г�����������
					for (String it : moneyList) {
						szxpcxe.add(jeFilter(it)+"Ԫ");
					}
					for (int j = 0; j <sgclszxbxgs.size(); j++) {
						for (String it_cp : sgclph) {
							if (sgclszxbxgs.get(j).contains(it_cp)) {
								szxpcxe.set(j, it_cp+"�������⳥��ȣ�"+szxpcxe.get(j));
							}
						}
					}
				}
			}
		}
		return szxpcxe;
	}
	
	/**
	 * ����ȡ�Ľ����н�һ���ᴿ	
	 * @param jeString ���ᴿ�Ľ��
	 * @return ��ȡ�Ľ��
	 */
	public String jeFilter(String jeString) {
		String reg="([\u4e00-\u9fa5]?)([0-9]+\\.[0-9]+||[0-9]+)";
		Pattern pattern=Pattern.compile(reg);
		Matcher matcher=pattern.matcher(jeString);
		if (matcher.find()) {
			jeString=matcher.group(2);
		}
		return jeString;
	}

    /**
     * ��ȡ�����е�����
     * @param cmssnr ����������
     * @return ����
     */
    public List<String> getDay(String cmssnr) {
        List<String> dayList = new ArrayList<>();
        String reg = "��";
        String[] cmssnrs = cmssnr.split(reg);
        for (int i=0;i<cmssnrs.length;i++) {
            int index=-1;
            char[] chars=cmssnrs[i].toCharArray();
            for (int j=chars.length-1;j>=0;j--){
                if(!(Character.isDigit(cmssnrs[i].charAt(j))||cmssnrs[i].charAt(j)=='��'||
                        cmssnrs[i].charAt(j)=='.'||cmssnrs[i].charAt(j)=='��'||
                        cmssnrs[i].charAt(j)=='��'||cmssnrs[i].charAt(j)==',')||
                        cmssnrs[i].charAt(j)=='��'){
                    index=j;
                    break;
                }
            }
            String je_toAdd = StringUtil.ToDBC(cmssnrs[i].substring(index+1, cmssnrs[i].length()));

            if (!StringUtil.isBlank(je_toAdd)) {
                boolean flag = true;
                for (String it :
                        dayList) {
                    if (it.equals(je_toAdd)) {
                        flag=false;
                        break;
                    }
                }
                if (flag) {
                    dayList.add(je_toAdd);
                }
            }
        }
        return dayList;
    }

	/**
	 * ��ȡ�����е�����
	 * @param cmssnr ������ʵ������
	 * @return ����
	 */
	public List<String> getJe(String cmssnr) {
		List<String> moneyList = new ArrayList<>();
		String reg = "Ԫ|��";
		String[] cmssnrs=cmssnr.split(reg);
		for (int i = 0; i < cmssnrs.length; i++) {
			int index=-1;
			char[] chars=cmssnrs[i].toCharArray();
			for (int j=chars.length-1;j>=0;j--){
				if(!(Character.isDigit(cmssnrs[i].charAt(j))||cmssnrs[i].charAt(j)=='��'||
						cmssnrs[i].charAt(j)=='.'||cmssnrs[i].charAt(j)=='��'||
						cmssnrs[i].charAt(j)=='��'||cmssnrs[i].charAt(j)==',')||
						cmssnrs[i].charAt(j)=='��'){
		    		index=j;
		    		break;
				}
			}
			
			String je_toAdd = StringUtil.ToDBC(cmssnrs[i].substring(index+1, cmssnrs[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
	    	
	    	if(!StringUtil.isBlank(je_toAdd)){
	        	if(je_toAdd.endsWith("��")){
		    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
		    		try{
		    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                        je_toAdd=je_addDou+"";
                    }catch(Exception e){
                    	je_toAdd="";
		    		}
	        	}
	        	if (!StringUtil.isBlank(je_toAdd)) {
	        		moneyList.add(je_toAdd.toString());
				}
	    	}
	    }
		return moneyList;
	}
	
	/**
	 * ��ȡ�����ձ��չ�˾
	 * @param cmssnr ������ʵ������
	 * @param sgclph �¹ʳ�������
	 * @return �����ձ��չ�˾
	 */
	public List<String> getSzxbxgs(String cmssnr, List<String> sgclph) {
		List<String> szxbxgs= new ArrayList<>();
		String[] cmssnrs = cmssnr.split("��");
		for (String its : cmssnrs) {
			for (String it : sgclph) {
				if (!(its.contains("δ") || its.contains("û��"))) {
					if (its.contains(it)&&(its.contains("��ҵ����")||its.contains("��ҵ������")||its.contains("��������ҵ���α���"))) {
						szxbxgs.add(it+"�����ձ��չ�˾��"+its.substring(its.indexOf("����")+2,its.indexOf("Ͷ��")));
					}
				}
			}
		}
		return szxbxgs;
	}
	
	/**
	 * ��ȡ��ǿ�ձ��չ�˾
	 * @param cmssnr ������ʵ������
	 * @param sgclph �¹ʳ�������
	 * @return ��ǿ�ձ��չ�˾
	 */
	public List<String> getJqxbxgs(String cmssnr, List<String> sgclph) {
		List<String> jqxbxgs = new ArrayList<>();
		String[] cmssnrs= cmssnr.split("��");
		for (String its : cmssnrs) {
			for (String it : sgclph) {
				if (!(its.contains("δ") || its.contains("û��"))) {
					if (its.contains(it)&&(its.contains("��ǿ��")||its.contains("��ͨ�¹�����ǿ�Ʊ���")||its.contains("����������ǿ�Ʊ���"))) {
						if (its.contains("��")&&its.contains("Ͷ��")) {
							jqxbxgs.add(it+"��ǿ�ձ��չ�˾��"+its.substring(its.indexOf("��")+1,its.indexOf("Ͷ��")));
						}else if (its.contains("��")&&its.contains("Ͷ��")) {
							jqxbxgs.add(it+"��ǿ�ձ��չ�˾��"+its.substring(its.indexOf("��")+1,its.indexOf("Ͷ��")));
						}else if (its.contains("����")&&its.contains("Ͷ��")) {
							jqxbxgs.add(it+"��ǿ�ձ��չ�˾��"+its.substring(its.indexOf("����")+2,its.indexOf("Ͷ��")));
						}
					}else{
//						jqxbxgs.add(it+"δͶ����ǿ��");
					}
				}
			}
		}
		return jqxbxgs;
	}
	
	/**
	 * ��ȡ�¹ʳ�����ʻԱ
	 * @param sjjg �¹ʾ���
	 * @param sgclph �¹ʳ�������
	 * @return �¹ʳ�����ʻԱ
	 */
	public List<String> getSgcljsy(String sjjg, List<String> sgclph) {
		List<String> sgcljsy = new ArrayList<>();
		String[] sjjgs= sjjg.split("��|��");
		for (String it_jsynr : sjjgs) {
			for (String it_ph : sgclph) {
				if (it_jsynr.contains(it_ph)&&it_jsynr.contains("��ʻ")) {
					String jsyName = it_jsynr.substring(0, it_jsynr.indexOf("��ʻ"));
					String[] jsyName2split = jsyName.split("��|��|��|��|��|ԭ��|����");
					if (jsyName2split.length>0) {
						if (!sgcljsy.contains(it_ph+"��ʻԱ��"+jsyName2split[jsyName2split.length-1])) {
							sgcljsy.add(it_ph+"��ʻԱ��"+jsyName2split[jsyName2split.length-1]);
						}	
					}
//					for (String it_jsy : sgcljsy) {
//						if (it_jsy.equals(it_ph+"��ʻԱ��"+jsyName2split[jsyName2split.length-1])) {
//							break;
//						}
//						sgcljsy.add(it_ph+"��ʻԱ��"+jsyName2split[jsyName2split.length-1]);
//					}
				}
			}
		}
//		String[] cmssnrs =cmssnr.split("��");
//		for (String its : cmssnrs) {
//			for (String it : sgclph) {
//				if (its.contains(it)&&(its.contains("��ʻ��")||its.contains("��ʻ"))&&its.contains("Ϊ")) {
//					if (its.indexOf("Ϊ")>its.indexOf("��ʻ")) {
//						String jsy2split = its.substring(0,its.indexOf("��ʻ"));
//						String[] jsy2splits = jsy2split.split("��");
//						for (String it_jsy : sgcljsy) {
//							if (it_jsy.equals(it+"��ʻԱ:"+jsy2splits[jsy2splits.length-1])) {
//								break;
//							}
//						}
//						sgcljsy.add(it+"��ʻԱ:"+jsy2splits[jsy2splits.length-1]);
//					}else if (its.indexOf("Ϊ")<its.indexOf("��ʻ")) {
//						if (its.contains("ϵΪ")) {
//							String jsy2split = its.substring(0,its.indexOf("ϵΪ"));
//							String[] jsy2splits = jsy2split.split("��");
//							for (String it_jsy : sgcljsy) {
//								if (it_jsy.equals(it+"��ʻԱ:"+jsy2splits[jsy2splits.length-1])) {
//									break;
//								}
//							}
//							sgcljsy.add(it+"��ʻԱ:"+its.substring(0, its.indexOf("ϵΪ")));
//						}else {
//							String jsy2split = its.substring(0,its.indexOf("Ϊ"));
//							String[] jsy2splits = jsy2split.split("��");
//							for (String it_jsy : sgcljsy) {
//								if (it_jsy.equals(it+"��ʻԱ:"+jsy2splits[jsy2splits.length-1])) {
//									break;
//								}
//							}
//							sgcljsy.add(its.substring(0,its.indexOf("Ϊ")));
//						}
//					}
//				}
//			}
//		}
		return sgcljsy;
	}

	/**
	 * ��ȡ�¹ʳ���������
	 * @param cmssnr ������ʵ������
	 * @param sgclph �¹ʳ����ƺ�
	 * @return �¹ʳ���������
	 */
	public List<String> getSgclsyz(String cmssnr, List<String> sgclph) {
		List<String> sgclsyz = new ArrayList<>();
		String[] cmssnrs =cmssnr.split("��");
		for (String its : cmssnrs) {
			for (String it : sgclph) {
				if (its.contains(it)&&its.contains("ʵ��")&&its.contains("����")) {
					if (its.contains("ʵ��������")) {
						String subcmssr=its.substring(its.indexOf("ʵ��������Ϊ")+6);
						String[] subcmssrs=subcmssr.split("��");
						sgclsyz.add(it+"������:"+subcmssrs[0]);
					}else if (its.contains("ʵ��Ϊ")&&its.contains("����")) {
						sgclsyz.add(it+"������:"+its.substring(its.indexOf("ʵ��Ϊ")+3, its.lastIndexOf("����")));
					}
				}
			}
		}
		return sgclsyz;
	}
	
	/**
	 * ��ȡ�¹ʳ�������
	 * @param cmssnr ������ʵ������
	 * @return �¹ʳ�������
	 */
	public List<String> getSgclph(String cmssnr) {
		String[] cmssnrs=cmssnr.split("��|��");
		List<String> sgclph=new ArrayList<>();
		String provinceName="[\u4EAC]?[\u6D25]?[\u5180]?[\u664B]?[\u8499]?[\u8FBD]?[\u5409]?"
				+ "[\u9ED1]?[\u6CAA]?[\u82CF]?[\u6D59]?"
				+ "[\u7696]?[\u95FD]?[\u8D63]?[\u9C81]?[\u8C6B]?[\u9102]?[\u6E58]?[\u7CA4]?[\u6842]?"
				+ "[\u743C]?[\u5DDD]?[\u8D35]?[\u4E91]?[\u6E1D]?[\u85CF]?[\u9655]?[\u7518]?[\u9752]?"
				+ "[\u5B81]?[\u65B0]?[\u6E2F]?[\u6FB3]?[\u53F0]?";
//		String reg="([\u4e00-\u9fa5]||��)("+provinceName+"[0-9a-zA-Z]{5,6}[\u6302]?)([\u4e00-\u9fa5]?)";
		String reg="([\u4e00-\u9fa5]||��)([\u4e00-\u9fa5]{1}[A-Z]{1}[0-9a-zA-Z]{4,5}[\u6302]?)([\u4e00-\u9fa5]?)";
//		String reg2=provinceName+"[A-Z]{1}[0-9a-zA-Z]{4,5}[\u6302]?";
		String[] province={"��","��","��","��","��","��","��","��","��","��","��","��","��","��","³","ԥ","��","��","��","��"
		                   ,"��","��","��","��","��","��","��","��","��","��","��","��","��","̨"};
		Pattern pattern=Pattern.compile(reg);
//		Pattern pattern2=pattern.compile(reg2);
		
		for (String it : cmssnrs) {
			Matcher matcher=pattern.matcher(it);
			while(matcher.find()) {
				String	sgclph_temp=matcher.group(2);
//				Matcher matcher2 = pattern2.matcher(sgclph_temp);
				boolean flag_repeat = false;
				boolean flag_province = false;
				if (sgclph.contains(sgclph_temp)) {
					flag_repeat=true;
				}
				if (!flag_repeat) {
//					if (matcher2.find()) {
//						sgclph.add(sgclph_temp);
//					}
					for (String it_p : province) {
						if (sgclph_temp.contains(it_p)) {
							flag_province=true;
							break;
						}
					}
					if (flag_province) {
						sgclph.add(sgclph_temp);
					}
				}
			}
		}
		return sgclph;
	}
	
	/**
	 * ����¹������϶�
	 * @param cmssnr ������ʵ������
	 * @return �¹������϶�
	 */
	public List<String> getSgzr(String cmssnr) {
		List<String>sgzr=new ArrayList<>();
		String[] sgzrnrSplit=cmssnr.split("��|,|��|��");
		
		for (String it : sgzrnrSplit) {
			if ((it.contains("�е�")||it.contains("��"))&&(it.contains("��Ҫ����")||it.contains("��Ҫ����")||
					it.contains("ȫ������")||it.contains("�¹�����"))) {
				sgzr.add(it);
			}
		}
		return sgzr;
	}
	
	/**
	 * ��ȡ�¹ʵص�
	 * @param cmssNr ������ʵ������
	 * @return �¹ʵص�
	 */
	public String getSgdd(String cmssNr) {
		String sgdd="δ�ἰ";

			int xzc=CmssdpreServiceImpl.worddis("����","��", cmssNr);
			int xss=CmssdpreServiceImpl.worddis("��ʻ��","ʱ",cmssNr);
			int zs=CmssdpreServiceImpl.worddis("��","ʱ",cmssNr);
			int zy=CmssdpreServiceImpl.worddis("��","��",cmssNr);
			int yxs=CmssdpreServiceImpl.worddis("��","��ʻ",cmssNr);
			if (xzc>5&&xzc<30) {
				sgdd=cmssNr.substring(cmssNr.indexOf("����")+2, cmssNr.lastIndexOf("��"));
			}else if(xss>6&&xss<30){
				sgdd=cmssNr.substring(cmssNr.indexOf("��ʻ��")+3,cmssNr.lastIndexOf("ʱ"));
			}else if(zs>5&&zs<40){
				sgdd=cmssNr.substring(cmssNr.lastIndexOf("��")+1,cmssNr.lastIndexOf("ʱ"));
			}else if(zy>5&&zy<30){
				sgdd=cmssNr.substring(cmssNr.indexOf("��")+1,cmssNr.indexOf("��"));
			}else if(yxs>5&&yxs<30){
				sgdd=cmssNr.substring(cmssNr.indexOf("��")+1,cmssNr.lastIndexOf("��ʻ"));
			}
//		String[] nr=cmssNr.split("��");
//
//		for(int i=0;i<nr.length;i++){
//			int xzc=CmssdpreServiceImpl.worddis("����","��", nr[i]);
//			int xss=CmssdpreServiceImpl.worddis("��ʻ��","ʱ",nr[i]);
//			int zs=CmssdpreServiceImpl.worddis("��","ʱ",nr[i]);
//			int yxs=CmssdpreServiceImpl.worddis("��","��ʻ",nr[i]);
//			if (xzc>0&&xzc<30) {
//				sgdd=nr[i].substring(nr[i].indexOf("����")+2, nr[i].lastIndexOf("��"));
//			}else if(xss>0&&xss<30){
//				sgdd=nr[i].substring(nr[i].indexOf("��ʻ��")+3,nr[i].lastIndexOf("ʱ"));
//				break;
//			}else if(zs>0&&zs<30){
//				sgdd=nr[i].substring(nr[i].lastIndexOf("��")+1,nr[i].lastIndexOf("ʱ"));
//				break;
//			}else if(nr[i].contains("��ʻ��")){
//				sgdd=nr[i].substring(nr[i].indexOf("��ʻ��")+3);
//				break;
//			}else if(yxs>0&&yxs<30){
//				sgdd=nr[i].substring(nr[i].indexOf("��")+1,nr[i].lastIndexOf("��ʻ"));
//				break;
//			}
//		}
		return sgdd;
	}
}
