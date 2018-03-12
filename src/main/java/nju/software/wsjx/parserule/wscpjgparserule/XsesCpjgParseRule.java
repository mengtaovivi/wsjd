package nju.software.wsjx.parserule.wscpjgparserule;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.xs.FdmspjfzModel;
import nju.software.wsjx.service.model.xs.FjxModel;
import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.XsFjxEnum;
import nju.software.wsjx.service.model.xs.Xszx;
import nju.software.wsjx.service.model.xs.XszxEnum;
import nju.software.wsjx.service.model.xs.ZmModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.MoneyUtil;
import nju.software.wsjx.util.StringUtil;

/**
 * ���¶�����н������
 * @author wangzh
 *
 */
public class XsesCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	private List<String> ayList;
	
	public XsesCpjgParseRule(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			is = getClass().getClassLoader().getResourceAsStream("enum/xszm_dm.txt");
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			String str = "";
			ayList = new ArrayList<String>();
			while ((str = br.readLine()) != null) {
				ayList.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> cpjg = wsAnalyse.getCpjg();
		WscpjgModel cpjgModel = new WscpjgModel();
		String pjjgnr = "";
		int size = cpjg.size();
		for(int i=0;i<cpjg.size();i++){
			String jgnr = cpjg.get(i);
			pjjgnr=pjjgnr+jgnr;
			if(((StringUtil.contains(jgnr, "һ��")&&StringUtil.contains(jgnr, "����"))||(StringUtil.contains(jgnr, "һ��")&&StringUtil.contains(jgnr, "����"))) 
					&& i<size){
				String splitExpression = buildSplitExpression();
				String[] jgnrArrays = jgnr.split(splitExpression);
				if(jgnrArrays.length>1){
					for(String s:jgnrArrays){
						if(!StringUtil.isBlank(s)){
//							�ж�s��ͷ����ʲô
							int indexTemp = jgnr.indexOf(s);
							String xq = "";
							if(indexTemp-3>-1){
								String prefix = jgnr.substring(indexTemp-4, indexTemp);
								if(prefix.contains("ά��")||prefix.contains("����")){
									s = prefix+s;
								}else if(i<cpjg.size()-1 && StringUtil.contains(cpjg.get(i+1), "����") && !StringUtil.contains(cpjg.get(i+1), "��")){
									//������ά�ֳ�����˵���Ǳ����о������ڿ��ܳ�������һ��
									xq = cpjg.get(i+1);
								}
							}
							cpjg.add(s);
							if(!StringUtil.isBlank(xq)){
								cpjg.add(xq);
							}
						}
					}
				}
				
			}else{
//					�����о����
				if(!StringUtil.contains(jgnr, "����")&&!StringUtil.contains(jgnr, "ά��")&&!StringUtil.contains(jgnr, "��׼")){
					if((StringUtil.contains(jgnr, "������")||StringUtil.contains(jgnr, "������"))&&StringUtil.contains(jgnr, "����")){
						jxWzPf(cpjgModel, jgnr);//�����з������о��������
					}else if((StringUtil.contains(jgnr, "������")||StringUtil.contains(jgnr, "������"))&&StringUtil.contains(jgnr, "������������")){
						jxBfxszrPf(cpjgModel, jgnr);//�������������о��������
					}else{
//						String reg = "��[\u4e00-\u9fa5]+?��";
						String reg = "��([\u4e00-\u9fa5]+?[��]*[\u4e00-\u9fa5]+?)*��";
						Pattern p = Pattern.compile(reg);
						Matcher m = p.matcher(jgnr);
						if(m.find()){//���¶�����г���Ϊǰ������
							String xqnr = "";
							if(i<cpjg.size()-1){
								xqnr = cpjg.get(i+1);
							}
							setXspf(cpjgModel,jgnr,xqnr);
						}
					}
				}else if(StringUtil.contains(jgnr, "����")||StringUtil.contains(jgnr, "ά��")){
					setFz(cpjgModel, jgnr, wssscyrModellist);
				}
			}
			if(StringUtil.contains(jgnr, "������������ԭ��")){
				setFdmspjjgfz(cpjgModel, jgnr, wssscyrModellist);
			}
		}

		if(!StringUtil.isBlank(pjjgnr)){
		//	if(ajlxEnum == AjlxEnum.XSYS){
		//		setXsysjafs(cpjgModel, pjjgnr);//����һ��᰸��ʽ
		//	}else if(ajlxEnum == AjlxEnum.XSES){
				setXsesjafs(cpjgModel, pjjgnr);//���¶���᰸��ʽ
				if(StringUtil.equals(cpjgModel.getJafs(), "����")){
					setGpyy(cpjgModel, wsAnalyse);//����ԭ��
				}
		//	}else{
		//		setXsysjafs(cpjgModel, pjjgnr);//�������°����᰸��ʽ
		//	}
			
			setJtfz(cpjgModel, pjjgnr);//�Ƿ��ŷ���
		}
		return cpjgModel;
	}
	
	public void setFz(WscpjgModel cpjgModel,String pjjg,List<WssscyrModel> sscyrList){
		if(sscyrList==null){
			return;
		}
		for(WssscyrModel sscyr:sscyrList){
			String name = sscyr.getSscyr();
			if(!StringUtil.isBlank(name) && StringUtil.contains(pjjg, name)){
				XspjjgfzModel fzModel = new XspjjgfzModel(name);
				if(StringUtil.contains(pjjg, "ά��")){
					fzModel.setEssljg("ά��ԭ��");
					fzModel.setEslxjg("ά��ԭ��");
				}
				if(cpjgModel.getPjjgfzModels()==null){
					List<XspjjgfzModel> fzs = new ArrayList<XspjjgfzModel>();
					fzs.add(fzModel);
					cpjgModel.setPjjgfzModels(fzs);
				}else{
					cpjgModel.getPjjgfzModels().add(fzModel);
				}
			}
		}
	}
	
	public String buildSplitExpression(){
		String expression = "";
		String[] nums = {"һ","��","��","��","��","��","��","��","��","ʮ","ʮһ","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","��ʮ",};
		for(String num:nums){
			expression = expression+"|"+num+"��"+"ά��"+"|"+num+"��"+"����"+"|"+num+"��"+"������"+"|"+num+"��"+"ԭ�󱻸���"+"|"+num+"��"+"������";
			expression = expression+"|"+num+"��"+"ά��"+"|"+num+"��"+"����"+"|"+num+"��"+"������"+"|"+num+"��"+"ԭ�󱻸���"+"|"+num+"��"+"������";
		}
		if(expression.startsWith("|")){
			expression = expression.substring(1);
		}
		return expression;
	}
	
	
	public static String getMin(String[] words,String content){
		int index = -1;
		for(String s:words){
			index = content.indexOf(s);
			if(index>-1){
				return s;
			}
		}
		return null;
	}
	/**
	 * ����������ֹ����
	 * @param fzModel �����о��������
	 * @param pjjg
	 * @return
	 */
	public void setXqqzrq(XspjjgfzModel fzModel,String pjjg){
		String[] qzrq = new String[2];
		String reg = "[��]?[�Դ�][\\dһ�����������߰˾��㩖��]*��[\\dһ�����������߰˾���ʮ����]*��[\\dһ�����������߰˾���ʮ����]*������[\\dһ�����������߰˾���ʮ����]*��[\\dһ�����������߰˾���ʮ����]*��[\\dһ�����������߰˾���ʮ����]*��";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(pjjg);
		
		String reg1 = "[��]?[�Դ�][\\dһ�����������߰˾��㩖��]*��[\\dһ�����������߰˾���ʮ����]*��[\\dһ�����������߰˾���ʮ����]*����[\\dһ�����������߰˾���ʮ����]*��[\\dһ�����������߰˾���ʮ����]*��[\\dһ�����������߰˾���ʮ����]*��";
		Pattern p1 = Pattern.compile(reg1);
		Matcher m1 = p1.matcher(pjjg);
		
		String xqcontent = "";
		if(m.find()){
			xqcontent = m.group();
		}else if(m1.find()){
			xqcontent = m1.group();
		}
		
		if(!StringUtil.isBlank(xqcontent)){
			
			int middleIndex = xqcontent.indexOf("����");
			int middleIndex1 = xqcontent.indexOf("��");
			int index = -1;
			if(middleIndex==-1){
				index = middleIndex1+1;
				middleIndex = middleIndex1;
			}else{
				index = middleIndex+2;
			}
			
			if(middleIndex>2){
				String ksrq = xqcontent.substring(2, middleIndex);
				if(!xqcontent.startsWith("��")){
					 ksrq = xqcontent.substring(1, middleIndex);
				}
				qzrq[0] = DateUtil.convertToCNDate(ksrq);
				
				fzModel.setXqksrq(qzrq[0]);
//				String jsrq = xqcontent.substring(middleIndex+2, xqcontent.length());
				String jsrq = xqcontent.substring(index, xqcontent.length());
				qzrq[1] = DateUtil.convertToCNDate(jsrq);
				fzModel.setXqjsrq(qzrq[1]);
			}
		}
//			���������۵ְ취
		reg = "�Ѻһ��[��]*�۵�����[^x00-xff]��";
		p = Pattern.compile(reg);
		m = p.matcher(pjjg);
		
		reg1 = "�Ѻһ��[��]*������[^x00-xff]��";
		p1 = Pattern.compile(reg1);
		m1 = p1.matcher(pjjg);
		if(m.find()){
			String zdbfcontent = m.group();
			fzModel.setXqzdbf(zdbfcontent);
		}else if(m1.find()){
			String zdbfcontent = m1.group();
			fzModel.setXqzdbf(zdbfcontent);
		}
	}
	
	
	/**
	 * �����о��������
	 * @param fzModel �˷����������������о��������
	 * @param pjjg �о����ԭʼ�ı���
	 * @param xqnr �������ڶ�
	 */
	public void setXspf(WscpjgModel cpjgModel,String pjjg,String xqnr){
//			������
		String brgName = "";
		XspjjgfzModel fzModel ;
		
		int nextIndex = pjjg.indexOf("��");
		
		brgName = pjjg.substring(0, nextIndex);//����������
		brgName = brgName.replaceAll("��ԭ�󱻸��ˣ�", "");
		brgName = brgName.replaceAll("������", "");
		brgName = brgName.replaceAll("������", "");
		if(StringUtil.contains(brgName, "��")){
			brgName = brgName.substring(brgName.indexOf("��")+1);
		}
		
		if(StringUtil.isBlank(brgName)){
			return;
		}else{
			fzModel = new XspjjgfzModel(brgName);
		}
		
		if (StringUtil.contains(pjjg, "�����ͷ�")||StringUtil.contains(pjjg, "�������´���")) {
			fzModel.setMzhwzsf("��");
		}else{
			fzModel.setMzhwzsf("��");
		}
		
		String reg = "��[^x00-xff]+?��";
//		String reg = "��([\u4e00-\u9fa5]+?[��]*[\u4e00-\u9fa5]+?)*��";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(pjjg);
		List<String> zmList = new ArrayList<String>();//��������
		while(m.find()){
			zmList.add(m.group());
		}
		List<PfModel> dzpfList = new ArrayList<PfModel>();
		List<PfModel> yzpfList = new ArrayList<PfModel>();
		String zxPfnr = "";
//			ÿ������������һ�������з�
		for(int i = 0;i<zmList.size();i++){
			String pfContent = "";
			if(i<zmList.size()-1){
//					A�B�A��
				if(pjjg.indexOf(zmList.get(i))<pjjg.indexOf(zmList.get(i+1))){
					pfContent =  pjjg.substring(pjjg.indexOf(zmList.get(i)), pjjg.indexOf(zmList.get(i+1)));
				}else{
					String pjjgTemp = pjjg.substring(pjjg.indexOf(zmList.get(i)));
					pfContent =  pjjgTemp.substring(pjjgTemp.indexOf(zmList.get(i)), pjjgTemp.indexOf(zmList.get(i+1)));
				}
				
			}else{
				pfContent =  pjjg.substring(pjjg.indexOf(zmList.get(i)));
			}
			
			if(zmList.size()>1 && i==zmList.size()-1 && StringUtil.contains(pfContent, "ִ��")){
				zxPfnr = pfContent.substring(pfContent.indexOf("ִ��"));
				pfContent = pfContent.substring(0, pfContent.indexOf("ִ��"));
			} 
			PfModel pfModel = jxPfModelFromPfnr(zmList.get(i), pfContent);
			if(pfModel!=null ){
				dzpfList.add(pfModel);
			}
		}
//			�з�
		if(dzpfList.size()>0){
			fzModel.setDzpf(dzpfList);
//				ִ���з�
			if(!StringUtil.isBlank(zxPfnr)){
				PfModel zxpf = jxPfModelFromPfnr(zmList.get(0), zxPfnr);
				fzModel.setZxpf(zxpf);
			}else if (dzpfList.size()==1){
				fzModel.setZxpf(dzpfList.get(0));
			}
			if(dzpfList.get(0)!=null && dzpfList.get(0).getZm()!=null){
				fzModel.setPjzzm(dzpfList.get(0).getZm());//�о�������
			}
		}
		if(yzpfList.size()>0){
			fzModel.setYzpf(yzpfList);
		}
		
//			�ϲ�����
		if(dzpfList.size()>1){
			fzModel.setHblx("��");
			fzModel.setSzbf("��");
		}else{
			fzModel.setHblx("��");
			fzModel.setSzbf("��");
		}
		
//			���ڣ����������������һ�������˵��з������ж������ǲ���������о������
		if(StringUtil.contains(pjjg, "����")&&StringUtil.contains(pjjg, "����")){
			setXqqzrq(fzModel, pjjg);
		}else if(StringUtil.contains(xqnr, "����")&&StringUtil.contains(xqnr, "��")&&!StringUtil.contains(xqnr, "������")){
			setXqqzrq(fzModel, xqnr);
		}

		if(cpjgModel.getPjjgfzModels()==null){
			List<XspjjgfzModel> xspjjgfzModels = new ArrayList<XspjjgfzModel>();
			xspjjgfzModels.add(fzModel);
			cpjgModel.setPjjgfzModels(xspjjgfzModels);
		}else{
//			���з���
			List<XspjjgfzModel> xspjjgfzModels = cpjgModel.getPjjgfzModels();
//			for(XspjjgfzModel fz:xspjjgfzModels){
//				if(StringUtil.equals(fz.getSscyr(), fzModel.getSscyr()) && (fz.getEslxjg()!=null ||fz.getEssljg()!=null)){
//					xspjjgfzModels.remove(fz);
//				}
//			}
			for(Iterator it = xspjjgfzModels.iterator();it.hasNext();){
				XspjjgfzModel fz = (XspjjgfzModel) it.next();
				if(StringUtil.equals(fz.getSscyr(), fzModel.getSscyr()) && (fz.getEslxjg()!=null ||fz.getEssljg()!=null)){
					it.remove();
				}
			}
			xspjjgfzModels.add(fzModel);
//			cpjgModel.setFdmspjfzModel(fdmspjfzModel);
			cpjgModel.setPjjgfzModels(xspjjgfzModels);
//			cpjgModel.getPjjgfzModels().add(fzModel);
		}
	}
	
	public int getBgrIndex(String pjjgnr){
		int index1 = pjjgnr.indexOf("��ԭ�󱻸��ˣ�");
		if(index1<0){
			index1 = pjjgnr.indexOf("������");
			if(index1<0){
				index1 =pjjgnr.indexOf("������");
				if(index1>-1){
					index1 = index1+3;
				}
			}else{
				index1 = index1+3;
			}
		}else{
			index1 = index1+"��ԭ�󱻸��ˣ�".length();
		}
		if(index1==-1){
			index1=0;
		}
		return index1;
	}
	
	/**
	 * ���������з�����
	 * @param zm
	 * @param pfContent
	 * @return
	 */
	public PfModel jxPfModelFromPfnr(String zm,String pfContent){
		if(StringUtil.isBlank(pfContent)){
			return null;
		}
		PfModel pfModel = new PfModel(pfContent);
//			����
		String zmName = zm.substring(1);
		if(zmName.endsWith("��")){
			zmName = zmName.substring(0, zmName.length()-1);
		}
		ZmModel zmModel = new ZmModel(zmName);

		/*
		 * �������ӽ����������롢��������
		 */
		setZm(zmModel, zmName);
		
		pfModel.setZm(zmModel);

		if(StringUtil.contains(pfContent, "�������´���")){
			pfModel.setPjjglx("�������´���");
		}else{
			pfModel.setPjjglx("�������´���");
		}
		
		String[] pfArrays = pfContent.split("��|,|\\.|��|��|;|��|��|��");
		List<FjxModel> fjxList = new ArrayList<FjxModel>();
		boolean zx = true;
		boolean hxflag = true;
		boolean fjflag = true;
		for(String pf:pfArrays){
			if(StringUtil.contains(pf, "�ϲ�")){
				break;
			}else if(XszxEnum.getXszx(pf)!=null && zx){
				String zxName = XszxEnum.getXszx(pf).getXz();
				Xszx xzModel = new Xszx(zxName);
				if(StringUtil.contains(pf, "����")){
					xzModel.setZxxq(pf.substring(pf.indexOf(zxName)+zxName.length(),pf.indexOf("����")));
				}else{
					xzModel.setZxxq(pf.substring(pf.indexOf(zxName)+zxName.length()));
				}
				if(StringUtil.equals(zxName, "����")&&StringUtil.contains(pfContent, "����")){
					xzModel.setZxlb(XszxEnum.SH.getXz());
				}
				pfModel.setZx(xzModel);
				zx = false;
			}
			if((StringUtil.contains(pf, "����") ||StringUtil.contains(pf, "����"))&& hxflag){
				Xszx hx =  new Xszx();
				if(StringUtil.contains(pf, "սʱ")){
					hx.setZxlb("սʱ����");
				}else{
					hx.setZxlb("��ͨ����");
				}
				String hxxq = "";
				if(StringUtil.contains(pf, "����")){
					hxxq = pf.substring(pf.indexOf("����")+2);
				}else if(StringUtil.contains(pf, "����") && pf.indexOf("ִ��")>pf.indexOf("����")+2){
					hxxq= pf.substring(pf.indexOf("����")+2,pf.indexOf("ִ��"));
				}else if(StringUtil.contains(pf, "����")){
					hxxq= pf.substring(pf.indexOf("����")+2);
				}
				hx.setZxxq(hxxq);
				if(!hxxq.contains("��������")){
					pfModel.setHx(hx);
					hxflag = false;
				}
			}
			if(XsFjxEnum.getFjx(pf)!=null){
				FjxModel fjx = new FjxModel(XsFjxEnum.getFjx(pf),pf);
				if(XsFjxEnum.getFjx(pf)==XsFjxEnum.FJ && fjflag){
					fjxList.add(fjx);
					fjflag = false;
				}
			}
		}
		if(fjxList.size()>0){
			pfModel.setFjxList(fjxList);
		}
//			ԭ���з�-��ǰ�ﲢ��
		if(StringUtil.contains(pfContent, "ǰ�ﲢ��")||StringUtil.contains(pfContent, "ԭ�ﲢ��")){
			pfModel.setYzszbf("��");
		}else if(StringUtil.contains(pfContent, "ǰ��")||StringUtil.contains(pfContent, "���о�")||StringUtil.contains(pfContent, "�о���")){
			pfModel.setYzszbf("��");
		}
		return pfModel;
	}
	 
	public void setZm(ZmModel zmModel,String zm){
		String wzzm = "";
		String zmdm = "";
		if(zm.endsWith("��")){
			zm = zm.substring(0, zm.length()-1);
		}
		List<String> zmList = FcUtil.getWholeToken(zm);
		for (int i = 0; i < ayList.size(); i++) {
			String ay = ayList.get(i).substring(4, ayList.get(i).length());
			boolean flag = true;
			for(String s :zmList){
				if(!StringUtil.contains(ay, s)){
					flag = false;
				}
			}
			if (flag) {
				wzzm = ay+ "��";
				zmdm = ayList.get(i).substring(0, 4).trim();
				zmModel.setWzzm(wzzm);
				zmModel.setZmdm(zmdm);
				break;
			}
		}
	}
	/**
	 * ������������о��������
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void jxWzPf(WscpjgModel cpjgModel,String jgnr){
		String bgrName = "";
		if(jgnr.indexOf("������")<jgnr.indexOf("����")){
			bgrName = jgnr.substring(jgnr.indexOf("������")+3, jgnr.indexOf("����"));
		}
		if(!StringUtil.isBlank(bgrName)){
			XspjjgfzModel fzModel = new XspjjgfzModel(bgrName);
			PfModel dzpfModel = new PfModel(jgnr.substring(jgnr.indexOf(bgrName)+bgrName.length()));
			dzpfModel.setPjjglx("��������");
			List<PfModel> dzpfList = new ArrayList<PfModel>();
			dzpfList.add(dzpfModel);
			fzModel.setDzpf(dzpfList);
			fzModel.setZxpf(dzpfModel);
			fzModel.setHblx("��");
			fzModel.setSzbf("��");
			if (StringUtil.contains(jgnr, "�����ͷ�")||StringUtil.contains(jgnr, "�������´���")) {
				fzModel.setMzhwzsf("��");
			}else{
				fzModel.setMzhwzsf("��");
			}
		}
	}
	
	/**
	 * ������������ �����о��������
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void jxBfxszrPf(WscpjgModel cpjgModel,String jgnr){
		String bgrName = "";
		if(jgnr.indexOf("������")<jgnr.indexOf("������������")){
			bgrName = jgnr.substring(jgnr.indexOf("������")+3, jgnr.indexOf("������������"));
		}
		if(!StringUtil.isBlank(bgrName)){
			XspjjgfzModel fzModel = new XspjjgfzModel(bgrName);
			PfModel dzpfModel = new PfModel(jgnr.substring(jgnr.indexOf(bgrName)+bgrName.length()));
			dzpfModel.setPjjglx("������������");
			List<PfModel> dzpfList = new ArrayList<PfModel>();
			dzpfList.add(dzpfModel);
			fzModel.setDzpf(dzpfList);
			fzModel.setZxpf(dzpfModel);
			fzModel.setHblx("��");
			fzModel.setSzbf("��");
			if (StringUtil.contains(jgnr, "�����ͷ�")||StringUtil.contains(jgnr, "�������´���")) {
				fzModel.setMzhwzsf("��");
			}else{
				fzModel.setMzhwzsf("��");
			}
		}
	}
	
	/**
	 * ���¶���᰸��ʽ
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void setXsesjafs(WscpjgModel cpjgModel,String jgnr){
//			��������
		String zycsReg = "����[\u4e00-\u9fa5]*��Ժ��[��]?��";
		Pattern zycsPattern = Pattern.compile(zycsReg);
		Matcher zycsMatcher = zycsPattern.matcher(jgnr);
		if(jgnr.contains("��")&&(jgnr.contains("����")||jgnr.contains("��������"))&&jgnr.contains("����")){
			cpjgModel.setJafs("���������ߴ���");
		}else if(jgnr.contains("��")&&jgnr.contains("���ؿ���")&&jgnr.contains("����")){
			cpjgModel.setJafs("�����ؿ��ߴ���");
		}else if((!jgnr.contains("����")&&!jgnr.contains("���")&&jgnr.contains("ά��"))||jgnr.contains("ά��ԭ��")){
			cpjgModel.setJafs("ά��");
		}else if(StringUtil.contains(jgnr, "��ֹ����")||StringUtil.contains(jgnr, "��ֹ����")){
			cpjgModel.setJafs("��ֹ����");
		}else if(StringUtil.contains(jgnr, "����")){
			cpjgModel.setJafs("����");
		}else if(zycsMatcher.find()){
			cpjgModel.setJafs("��������");
		}else if((jgnr.contains("ά��")&&(jgnr.contains("����")||jgnr.contains("���")))// 1.ά��XX������XX����ά��XX�����XX
				 ||(jgnr.contains("����")&&jgnr.contains("�о�")&&jgnr.contains("�д�"))
				 ||jgnr.contains("������")){// 2.����XX�о�,��XX��д�XX
			 cpjgModel.setJafs("����");
		 }else if(StringUtil.contains(jgnr, "����ԭ�ö�")&&StringUtil.contains(jgnr, "����û��Υ����������")){
			cpjgModel.setJafs("����ԭ�ö�������û��Υ����������");
		}else if(StringUtil.contains(jgnr, "����ԭ�ö�")&&StringUtil.contains(jgnr, "û��Υ������")){
			cpjgModel.setJafs("����ԭ�ö����ö�û��Υ������");
		}else if(StringUtil.contains(jgnr, "���û��Υ�����òö�")){
			cpjgModel.setJafs("���û��Υ�����òö�");
		}else if((jgnr.contains("׼��")||jgnr.contains("׼��")) && jgnr.contains("����") &&(jgnr.contains("����")||jgnr.contains("����"))){
			cpjgModel.setJafs("׼�賷������");
		}else if((jgnr.contains("׼��")||jgnr.contains("׼��")) && jgnr.contains("����") &&jgnr.contains("����")){
			cpjgModel.setJafs("׼�賷�ؿ���");
		}else if((jgnr.contains("׼��")||jgnr.contains("׼��")) && jgnr.contains("����") &&jgnr.contains("����")&&jgnr.contains("����һ�����")){
			cpjgModel.setJafs("׼�������߲�����һ�����");
		}else if(jgnr.contains("����")&&jgnr.contains("�ö�")&&jgnr.contains("����")){
			cpjgModel.setJafs("����ԭ�ö���ָ������");
		 }else if(jgnr.contains("����")&&jgnr.contains("�ö�")&&jgnr.contains("����")){
			 cpjgModel.setJafs("����ԭ�ö���ָ������");
		 }else if(jgnr.contains("�����²��ֲ���һ����������")){
			 cpjgModel.setJafs("�����²��ֲ���һ����������");
		 }else{
			 cpjgModel.setJafs("����");
		 }
	}
	
	public void setGpyy(WscpjgModel cpjgModel,WsAnalyse wsAnalyse){
		List<String> cpfxgcList = wsAnalyse.getCpfxgc();
		if(cpfxgcList!=null && cpfxgcList.size()>0){
			String cpfxgc = "";
			List<String> gpyy = new ArrayList<String>();
			for(String s:cpfxgcList){
				cpfxgc +=s;
			}
			
			if(StringUtil.contains(cpfxgc, "��ʵ����")||StringUtil.contains(cpfxgc, "֤�ݲ���")){
				gpyy.add("��ʵ�������֤�ݲ���");
			}
			if(rdssbd(cpfxgc)){
				gpyy.add("�϶���ʵ����");
			}
			if(StringUtil.contains(cpfxgc, "�ش���������")){
				gpyy.add("���������ش���������");
			}
			if(StringUtil.contains(cpfxgc, "��֤��")){
				gpyy.add("��������֤��");
			}
			if(StringUtil.contains(cpfxgc, "���÷��ɴ���")||StringUtil.contains(cpfxgc, "���÷����д���")||StringUtil.contains(cpfxgc, "���÷��ɲ���")
					||StringUtil.contains(cpfxgc, "���÷��ɼ����̲���")){
				gpyy.add("���÷��ɴ���");
			}
			if(StringUtil.contains(cpfxgc, "���̹���")||StringUtil.contains(cpfxgc, "����ƫ��")||StringUtil.contains(cpfxgc, "���̻���")){
				gpyy.add("���̹���");
			}
			if(StringUtil.contains(cpfxgc, "���̹���")||StringUtil.contains(cpfxgc, "����ƫ��")){
				gpyy.add("����ƫ��");
			}
			if(StringUtil.contains(cpfxgc, "���̲���")){
				gpyy.add("����ƽ��");
			}
			if(StringUtil.contains(cpfxgc, "����")){
				gpyy.add("���У����ˣ�ʱ����");
			}
			if(gpyy.size()==0){
				gpyy.add("����");
			}
			if(gpyy.size()>0){
				cpjgModel.setGpyy(gpyy);
			}
		}
	}
	
	/**
	 * ����ԭ�� �϶���ʵ����
	 * @param cpfxgc
	 * @return
	 */
	public boolean rdssbd(String cpfxgc){
		String[] cpfxgcArrays = cpfxgc.split("��|��|��|��|��");
		for(String s :cpfxgcArrays){
			if(StringUtil.contains(s, "�϶�")&&StringUtil.contains(s, "��ʵ")
					&&(StringUtil.contains(s, "����")||StringUtil.contains(s, "����")||StringUtil.contains(s, "����"))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �����Ƿ������ϽȨ����
	 * @param wscpjgModel
	 */
	public void setTcgxyy(WscpjgModel cpjgModel,String allNr){
		if(StringUtil.contains(allNr, "��Ͻ����")){
			cpjgModel.setTcgxyy("��");
		}else{
			cpjgModel.setTcgxyy("��");
		}
	}
	
	public void setFdmspjjgfz(WscpjgModel cpjgModel,String pjjg,List<WssscyrModel> wssscyrModellist){
		List<String> pcr = new ArrayList<String>();
		List<String> bpcr = new ArrayList<String>();
		int indexOfms = pjjg.indexOf("������������ԭ����");
		for(WssscyrModel sscyr:wssscyrModellist){
			String sscyrName = sscyr.getSscyr();
			if(pjjg.indexOf(sscyrName)>indexOfms){
				bpcr.add(sscyrName);
			}else if(pjjg.indexOf(sscyrName)>indexOfms && pjjg.indexOf(sscyrName)>-1){
				pcr.add(sscyrName);
			}
		}
		List<String> moneyList = MoneyUtil.getJe(pjjg);
		if(pcr.size()>0 || bpcr.size()>0 || moneyList.size()>0){
			FdmspjfzModel fzModel = new FdmspjfzModel();
			if(pcr.size()>0){
				fzModel.setPcr(pcr);
			}
			if(bpcr.size()>0){
				fzModel.setBpcr(bpcr);
			}
			if(moneyList.size()>0){
				fzModel.setBcje(moneyList);
			}
			fzModel.setNr(pjjg);
			cpjgModel.setFdmspjfzModel(fzModel);
		}
	}
	/**
	 * �Ը��������⳥�Ĵ���
	 * @param cpjgModel
	 * @param pjjg
	 */
	public void setDfdmspcdcl(WscpjgModel cpjgModel,String pjjg){
		//���ظ�����������ԭ����  ����
		if(StringUtil.contains(pjjg, "���ظ�����������ԭ����")&&(!StringUtil.contains(pjjg, "������������")||!StringUtil.contains(pjjg, "������������"))){
			cpjgModel.setDfdmspccl("����");
		}else if(StringUtil.contains(pjjg, "������������ԭ����")&&StringUtil.contains(pjjg, "��")){
			//������������ԭ������
			cpjgModel.setDfdmspccl("�⳥");
		}
	}
	
	public void setFdmsbfcpjg(WscpjgModel cpjgModel,String pjjg){
		if(StringUtil.contains(cpjgModel.getJafs(), "����") && pjjg.contains("������������ԭ����")){
			cpjgModel.setFdmscpjg("������������ԭ�泷��");
		}else if(StringUtil.contains(pjjg, "������������ԭ��")){
			cpjgModel.setFdmscpjg("�о�");
		}
	}

	/**
	 * ���ü��ŷ���
	 * @param cpjgModel
	 * @param pjjg
	 */
	public void setJtfz(WscpjgModel cpjgModel,String pjjg){
		if(StringUtil.contains(pjjg, "���ŷ���")){
			cpjgModel.setJtfz("��");
		}else{
			cpjgModel.setJtfz("��");
		}
	}

}
