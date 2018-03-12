package nju.software.wsjx.parserule.wscpjgparserule;

import java.util.ArrayList;
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
import nju.software.wsjx.util.MoneyUtil;
import nju.software.wsjx.util.StringUtil;

/**
 * ����һ����н������
 * @author wangzh
 *
 */
public class XsysCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> cpjg = wsAnalyse.getCpjg();
		WscpjgModel cpjgModel = new WscpjgModel();
		String pjjgnr = "";
		for(int i=0;i<cpjg.size();i++){
			String jgnr = cpjg.get(i);
			pjjgnr=pjjgnr+jgnr;
//			������½��û�з��飬�ȷ���
			if(StringUtil.contains(jgnr, "һ��")&&StringUtil.contains(jgnr, "����")){
				String[] jgnrArrays = jgnr.split("һ��|����|����|�ġ�|�塢|����|�ߡ�|�ˡ�|�š�");
				for(String s:jgnrArrays){
					if(!StringUtil.isBlank(s)){
						cpjg.add(s);
					}
				}
			}else{
//				���½���Ѿ�����
//				�����о����
				if(StringUtil.contains(jgnr, "������")&&StringUtil.contains(jgnr, "����")){
					jxWzPf(cpjgModel, jgnr);//�����з������о��������
				}else if(StringUtil.contains(jgnr, "������")&&StringUtil.contains(jgnr, "������������")){
					jxBfxszrPf(cpjgModel, jgnr);//�������������о��������
				}else{
//					�������´������������´��������о��������
					String reg = "��[^x00-xff]+?��";
//					String reg = "��([\u4e00-\u9fa5]+?[��]*[\u4e00-\u9fa5]+?)*��";
					Pattern p = Pattern.compile(reg);
					Matcher m = p.matcher(jgnr);
					if(m.find()){
						String xqnr = "";
						if(i<cpjg.size()-1){
							xqnr = cpjg.get(i+1);
						}
						setXspf(cpjgModel,jgnr,xqnr);
					}
				}
			}
			if(StringUtil.contains(jgnr, "������������ԭ��")){
				setFdmspjjgfz(cpjgModel, jgnr, wssscyrModellist);
			}
		}

		if(!StringUtil.isBlank(pjjgnr)){
			setXsysjafs(cpjgModel, pjjgnr);//����һ��᰸��ʽ
			setYsxsbfcpjg(cpjgModel, pjjgnr, wsAnalyse);//һ�����²��ֲ��н��
			setTcgxyy(cpjgModel, pjjgnr);//�����Ͻ����
			if(pjjgnr.indexOf("�粻��")>-1){//�������
				setSsqk(cpjgModel, pjjgnr);
			}
			setDfdmspcdcl(cpjgModel, pjjgnr);//�Ը��������⳥�Ĵ���
			setFdmsbfcpjg(cpjgModel, pjjgnr);//�������²��ֲ��н��
		}
		return cpjgModel;
	}
	
	/**
	 * ����һ���������
	 * @param cpjgModel
	 * @param temp
	 */
	public static void setSsqk(WscpjgModel cpjgModel,String temp){
		String cpjg = temp.substring(temp.indexOf("�粻��"));
//		��������
		 int indexStart = cpjg.indexOf("����");
		 if(indexStart==-1){
			 indexStart = cpjg.indexOf("����");
		 }
	     int fyIndex =-1; 
	     if(indexStart>-1){
	    	 fyIndex = cpjg.indexOf("��");
	    	 if(fyIndex>-1 && fyIndex > indexStart){
	    		 cpjgModel.setSsqx(cpjg.substring(indexStart+2, fyIndex));
		     }
	     }
//	     ���߷�Ժ
	     String[] fyPrefix = {"ֱ����","������","ͨ����Ժ��"};
	     String fyprefix = getMin(fyPrefix, cpjg);
	     if(!StringUtil.isBlank(fyprefix)){
	    	 indexStart = cpjg.indexOf(fyprefix);
	    	 String[] ssSuffix ={"��������","�������","����"};
	    	 String sssuffix = getMin(ssSuffix, cpjg);
		     if(indexStart>-1 && !StringUtil.isBlank(sssuffix) && indexStart<cpjg.indexOf(sssuffix)){
		    	 cpjgModel.setKssz(cpjg.substring(indexStart+fyprefix.length(), cpjg.indexOf(sssuffix)));
		     }
	     }else{
	    	 indexStart = cpjg.indexOf("ֱ������");
	    	 fyIndex = cpjg.indexOf("��Ժ");
	    	 if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    		 cpjgModel.setKssz(cpjg.substring(indexStart+5, fyIndex+2));
	    	 }
	     }
//	     �ύ����
	     String[] tjPrefix = {"�ύ","Ӧ��","Ӧ����","Ӧ�ݽ�","Ӧ���ݽ�","Ӧ���ύ"};
	     String tjprefix = getMin(tjPrefix, cpjg);
	     if(!StringUtil.isBlank(tjprefix)){
	    	 indexStart = cpjg.indexOf(tjprefix);
	    	 String tjcl = cpjg.substring(indexStart+tjprefix.length());
	    	 if(tjcl.endsWith("��")){
	    		 tjcl = tjcl.substring(0, tjcl.length()-1);
	    	 }
	    	 cpjgModel.setSstjcl(tjcl);
	     }
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
	 * ����һ�����˼���
	 * @param cpjgModel
	 * @param wssscyrModellist
	 * @param cpjg
	 * @param wsAnalyse
	 */
	public static void setCsrjh(WscpjgModel cpjgModel,List<WssscyrModel> wssscyrModellist,String cpjg,WsAnalyse wsAnalyse){
		List<String> csrjh = new ArrayList<String>();
		String csrStr = "";
		for(WssscyrModel sscyr:wssscyrModellist){
			if(StringUtil.contains(cpjg, sscyr.getSscyr())){
				csrjh.add(sscyr.getSscyr());
				csrStr += sscyr.getSscyr();
			}
		}
		if(csrjh.size()>0){
			cpjgModel.setCsrjh(csrjh);
//			�������뱻�������кͽ���� ���������ϼ�¼��
			String ssjl = wsAnalyse.getSsjl();
//			��ɺͽ� ����Ը�ͽ⣻ ���Э�飻��ɺͽ�Э�飻
			if((StringUtil.contains(ssjl, "�ͽ�")||StringUtil.contains(ssjl, "���"))&&StringUtil.contains(ssjl, "����")){
				cpjgModel.setCslx("�������뱻�������кͽ����");
				return;
			}
			if(StringUtil.contains(csrStr, "���Ժ")){
				cpjgModel.setCslx("���Ժ����");
			}else{
				cpjgModel.setCslx("�����˳���");
			}
		}
	}
	
	/**
	 * ����������ֹ����
	 * @param fzModel �����о��������
	 * @param pjjg
	 * @return
	 */
	public void setXqqzrq(XspjjgfzModel fzModel,String pjjg){
		String[] qzrq = new String[2];
		String reg = "����[\\dһ�����������߰˾��㩖]*��[\\dһ�����������߰˾��㩖]*��[\\dһ�����������߰˾��㩖]*������[\\dһ�����������߰˾��㩖]*��[\\dһ�����������߰˾��㩖]*��[\\dһ�����������߰˾��㩖]*��ֹ";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(pjjg);
		if(m.find()){
			String xqcontent = m.group();
			int middleIndex = xqcontent.indexOf("����");
			if(middleIndex>2){
				String ksrq = xqcontent.substring(2, middleIndex);
				qzrq[0] = ksrq;
				fzModel.setXqksrq(ksrq);
				String jsrq = xqcontent.substring(middleIndex+2, xqcontent.length()-1);
				qzrq[1] = jsrq;
				fzModel.setXqjsrq(jsrq);
			}
		}
//		���������۵ְ취
		reg = "�Ѻһ���۵�����[^x00-xff]��";
		p = Pattern.compile(reg);
		m = p.matcher(pjjg);
		if(m.find()){
			String zdbfcontent = m.group();
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
//		������
		String brgName = "";
		XspjjgfzModel fzModel ;
		int firstIndex = pjjg.indexOf("������");
		int nextIndex = pjjg.indexOf("��");
		if(firstIndex>-1 && firstIndex<nextIndex){
			brgName = pjjg.substring(firstIndex+3, nextIndex);//����������
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
//		ÿ������������һ�������з�
		for(int i = 0;i<zmList.size();i++){
			String pfContent = "";
			if(i<zmList.size()-1){
//				A�B�A��
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
//		�з�
		if(dzpfList.size()>0){
			fzModel.setDzpf(dzpfList);
//			ִ���з�
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
		
//		�ϲ�����
		if(dzpfList.size()>1){
			fzModel.setHblx("��");
			fzModel.setSzbf("��");
		}else{
			fzModel.setHblx("��");
			fzModel.setSzbf("��");
		}
		
//		���ڣ����������������һ�������˵��з������ж������ǲ���������о������
		if(StringUtil.contains(pjjg, "����")&&StringUtil.contains(pjjg, "����")){
			setXqqzrq(fzModel, pjjg);
		}else if(StringUtil.contains(xqnr, "����")&&StringUtil.contains(xqnr, "����")&&!StringUtil.contains(xqnr, "������")){
			setXqqzrq(fzModel, xqnr);
		}

		if(cpjgModel.getPjjgfzModels()==null){
			List<XspjjgfzModel> xspjjgfzModels = new ArrayList<XspjjgfzModel>();
			xspjjgfzModels.add(fzModel);
			cpjgModel.setPjjgfzModels(xspjjgfzModels);
		}else{
			cpjgModel.getPjjgfzModels().add(fzModel);
		}
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
//		����
		String zmName = zm.substring(1);
		if(zmName.endsWith("��")){
			zmName = zmName.substring(0, zmName.length()-1);
		}
		ZmModel zmModel = new ZmModel(zmName);
		/*
		 * �������ӽ����������롢��������
		 */
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
				pfModel.setZx(xzModel);
				zx = false;
			}
			if(StringUtil.contains(pf, "����") && hxflag){
				Xszx hx =  new Xszx();
				if(StringUtil.contains(pf, "սʱ")){
					hx.setZxlb("սʱ����");
				}else{
					hx.setZxlb("��ͨ����");
				}
				hx.setZxxq(pf.substring(pf.indexOf("����")+2));
				pfModel.setHx(hx);
				hxflag = false;
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
//		ԭ���з�-��ǰ�ﲢ��
		if(StringUtil.contains(pfContent, "ǰ�ﲢ��")||StringUtil.contains(pfContent, "ԭ�ﲢ��")){
			pfModel.setYzszbf("��");
		}else if(StringUtil.contains(pfContent, "ǰ��")||StringUtil.contains(pfContent, "���о�")||StringUtil.contains(pfContent, "�о���")){
			pfModel.setYzszbf("��");
		}
		return pfModel;
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
	 * ����һ��᰸��ʽ
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void setXsysjafs(WscpjgModel cpjgModel,String jgnr){
//		��������
		String bhqsReg = "����[\u4e00-\u9fa5]*��[\u4e00-\u9fa5]*��[\u4e00-\u9fa5]*����";
		Pattern bhqsPattern = Pattern.compile(bhqsReg);
		Matcher bhqsMatcher = bhqsPattern.matcher(jgnr);
//		׼�ʳ���
		String zycsReg = "׼[����][\u4e00-\u9fa5]*����";
		Pattern zycsPattern = Pattern.compile(zycsReg);
		Matcher zycsMatcher = bhqsPattern.matcher(jgnr);
		String reg = "��([\u4e00-\u9fa5]+?[��]*[\u4e00-\u9fa5]+?)*��";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(jgnr);
		
		if(StringUtil.contains(jgnr,"��" )&&StringUtil.contains(jgnr, "���ߴ���")){
			cpjgModel.setJafs("�����ߴ���");
		}else if(StringUtil.contains(jgnr, "��������")){
			cpjgModel.setJafs("��������");
		}else if(StringUtil.contains(jgnr, "��������")){
			cpjgModel.setJafs("��������");
		}else if(StringUtil.contains(jgnr, "����Ǽ�����")){
			cpjgModel.setJafs("����Ǽ�����");
		}else if(bhqsMatcher.find()){
			cpjgModel.setJafs("��������");
		}else if(zycsMatcher.find()){
			cpjgModel.setJafs("׼�ʳ���");
		}else if(StringUtil.contains(jgnr, "��ֹ����")||StringUtil.contains(jgnr, "��ֹ����")){
			cpjgModel.setJafs("��ֹ����");
		}else if(StringUtil.contains(jgnr, "����")){
			cpjgModel.setJafs("����");
		}else if(m.find()){
			cpjgModel.setJafs("�о�");
		}
	}
	/**
	 * һ�����²��ֲ��в��н��
	 * @param wscpjgModel
	 * @param allNr
	 * @param wsAnalyse
	 */
	public void setYsxsbfcpjg(WscpjgModel cpjgModel,String allNr,WsAnalyse wsAnalyse){
		String cpfxgc="";
		if(wsAnalyse.getCpfxgc()!=null && wsAnalyse.getCpfxgc().size()>0){
			for(String cpfx:wsAnalyse.getCpfxgc()){
				cpfxgc = cpfxgc+cpfx;
			}
		}
		String ssjl = wsAnalyse.getSsjl();
		
		String reg = "׼[����][\u4e00-\u9fa5]*���Ժ����[^x00-xff]*����";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(allNr);
		
		if(cpjgModel.getJafs()!=null && cpjgModel.getJafs().contains("����") && 
				(ssjl.contains("�ͽ�")||cpfxgc.contains("�ͽ�"))){
			cpjgModel.setYsxsbfpjjg("�������뱻�������кͽ����");
		}else if(m.find()){
			cpjgModel.setYsxsbfpjjg("���Ժ����");
		}else if(StringUtil.equals("��������", cpjgModel.getJafs())){
			cpjgModel.setYsxsbfpjjg("��������");
		}else if(StringUtil.equals("��ֹ����", cpjgModel.getJafs())){
			cpjgModel.setYsxsbfpjjg("��ֹ");
		}else if(StringUtil.equals("�о�", cpjgModel.getJafs())){
			cpjgModel.setYsxsbfpjjg("�о�");
		}else {
			cpjgModel.setYsxsbfpjjg("��");
		}
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

}
