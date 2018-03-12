package nju.software.wsjx.parserule.wscpjgparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.MsesFhcsyyEnum;
import nju.software.wsjx.model.Enum.SsfEnum;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.service.impl.jtsg.DsrbxgsServiceImpl;
import nju.software.wsjx.service.impl.jtsg.PjjgServiceImpl;
import nju.software.wsjx.service.jtsg.DsrbxgsService;
import nju.software.wsjx.service.jtsg.PjjgService;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.util.NumberConverter;
import nju.software.wsjx.util.StringUtil;

/**
 * ����������н������
 * @author LiYX
 *
 */
public class XzzsCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
		// TODO Auto-generated method stub
		List<String> cpjg = wsAnalyse.getCpjg();
		// TODO Auto-generated method stub
		WscpjgModel wscpjgModel = new WscpjgModel();
		List<String> cpjgnrList = new ArrayList<String>();
		List<PjjgnrModel> pjjgList=new ArrayList<PjjgnrModel>();
		String pjjgnr ="";
		String allnr="";
		boolean cpjgnrFlag = true;
		List<String> pjjelx = getPjjeLx();
		for (int i = 0; i < cpjg.size(); i++) {
			allnr = allnr+cpjg.get(i);
//			�����֮ǰ�Ķ������н������
			if(cpjgnrFlag&&!isAjslf(cpjg.get(i))){
				cpjgnrList.add(cpjg.get(i));
				pjjgList.add(new PjjgnrModel(cpjg.get(i)));
				pjjgnr = pjjgnr+cpjg.get(i);
			}else{
				cpjgnrFlag = false;
			}
//			���������
			if (isAjslf(cpjg.get(i))) {
				if(StringUtil.isBlank(wscpjgModel.getAjslf())){
					wscpjgModel.setAjslf(cpjg.get(i));
				}else{
					wscpjgModel.setAjslf(wscpjgModel.getAjslf()+cpjg.get(i));
				}
				cpjgnrFlag = false;
			}

// 			�������
			
			if(cpjg.get(i).indexOf("�粻����")>-1){	
				setSsqk(wscpjgModel, cpjg.get(i));
			}
			

//		�������������
		setSsfcd(wscpjgModel, wssscyrModellist);
		
		
//		�᰸��ʽ
		wscpjgModel = setXzzsjafs(wscpjgModel, pjjgnr,allnr,wsAnalyse);
		}
		return wscpjgModel;
	}

	/**
	 * ������������᰸��ʽ
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @param allPjjg
	 * @param wsAnalyse
	 * @return
	 */
	private WscpjgModel setXzzsjafs(WscpjgModel wscpjgModel, String pjjgnr, String allPjjg, WsAnalyse wsAnalyse) {
		if(!StringUtil.equals(pjjgnr, "")){
			List<String> cpfxgc = wsAnalyse.getCpfxgc();
			String cpfxgcStr="";
			if(cpfxgc!=null){
				for(String s:cpfxgc){
					cpfxgcStr = cpfxgcStr+s;
				}
			}
			if(allPjjg.contains("�о�")||StringUtil.contains(cpfxgcStr, "�о�����")){
				wscpjgModel.setJafs("�о�");
			}else if(pjjgnr.contains("����")&&pjjgnr.contains("����")&&pjjgnr.contains("��ֹ")&&pjjgnr.contains("ִ��")){
				wscpjgModel.setJafs("��������");
			}else if(pjjgnr.contains("����")&&pjjgnr.contains("��")||pjjgnr.contains("ָ��")&&pjjgnr.contains("��������")||pjjgnr.contains("����")||pjjgnr.contains("����")&&pjjgnr.contains("����")){
				wscpjgModel.setJafs("����ԭ�ö���ָ������");
			}
			if(StringUtil.isBlank(wscpjgModel.getJafs())){
				wscpjgModel.setJafs("����");
			}
		}
		return wscpjgModel;
	}

	/**
	 * �������Ϸѳе�
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	private void setSsfcd(WscpjgModel wscpjgModel, List<WssscyrModel> wssscyrModellist) {
		String ssfcdjl = wscpjgModel.getAjslf();
		WsCpjgssfModel ssfModel = new WsCpjgssfModel();
		if(!StringUtil.isBlank(ssfcdjl)){
//			-----------���ϷѼ�¼--------------
			ssfModel.setSsfjl(ssfcdjl);
//			------�������Ϸ����࣬�������Ϸѽ��-----
			int indexOfSsf=-1,indexOfYuan=-1,lengthOfSsfName=-1;
			List<WsCpjgssfjeModel> jeModels = new ArrayList<WsCpjgssfjeModel>();
			int ssfzje=0;
			
			//����Ԫ����
			String[] ssfstrList = ssfcdjl.split("Ԫ");
//			for(String ssfstr:ssfstrList){
			for(int i=0;i<ssfstrList.length-1;i++){
				String ssfstr = ssfstrList[i];
//				����ÿһ�����Ϸ�
				for(SsfEnum ssfEnum:SsfEnum.values()){
					if(StringUtil.contains(ssfstr, ssfEnum.getSsfName())){
						ssfstr=StringUtil.trim(ssfstr);
						String je = ssfstr.substring(ssfEnum.getSsfName().length()+ssfstr.indexOf(ssfEnum.getSsfName()), ssfstr.length());//+"Ԫ";
						je = je.replace('��', '.');
						je=je.replaceAll("��", "");
						if(je.contains("������ȡ")){
							je = je.substring(je.indexOf("������ȡ")+4);
						}
						if(je.contains("��������ȡ��")){
							je = je.substring(6);
						}
						if(StringUtil.contains(je,"����")){
							je = je.substring(2);
						}else if(StringUtil.contains(je,"��")||StringUtil.contains(je,"��")||StringUtil.contains(je,"Ϊ")||StringUtil.contains(je,"��")){
							je = je.substring(1);
						}
//						je����������
						je = je.replaceAll("�����", "");
						if(!StringUtil.isNum(je)&&!StringUtil.contains(je, "�����")){
							int jeInt = NumberConverter.convertFromChinese(je);
							if((jeInt>10 && je.length()>1)||jeInt==10){
								je=jeInt+"";
							} 
						}
						if(ssfEnum.equals(SsfEnum.JBSQ)&&jeModels.size()>0){
							jeModels.get(jeModels.size()-1).setValue(je+"Ԫ");
						}else{
							WsCpjgssfjeModel jeModel = new WsCpjgssfjeModel(je+"Ԫ", ssfEnum.getSsfName());
							jeModels.add(jeModel);
							try{
								ssfzje+=Integer.parseInt(ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan));
							}catch(Exception e){
								
							}
						}
						break;
					}
					
				}
			}
			
			if(jeModels.size()>0){
				ssfModel.setSsfjeModels(jeModels);
				ssfModel.setZje(ssfzje+"Ԫ");
			}
//			--------�������Ϸѳе�-----------
			List<WscpjgssfcdModel> cdModels = new ArrayList<WscpjgssfcdModel>();
			HashMap<String, String> sscyrMap = new HashMap<String, String>();
			for(WssscyrModel sscyr:wssscyrModellist){
				sscyrMap.put(sscyr.getSscyr(), sscyr.getSsdw());
			}
		    String[] ssfs = ssfcdjl.split("��");
		    int count=0;
			for(String s:ssfs){
				String cdje = getNumberFromString(s);
				List<WscpjgssfcdModel> tempcdModels = new ArrayList<WscpjgssfcdModel>();
				if(StringUtil.contains(s, "����")||StringUtil.contains(s, "�е�")){
//					count++;
					for(Map.Entry<String, String> entry:sscyrMap.entrySet()){
						if(StringUtil.contains(s, entry.getKey())){
							count++;
							WscpjgssfcdModel cdmodel = new WscpjgssfcdModel();
							cdmodel.setCdr(entry.getKey());
							cdmodel.setCdrdw(entry.getValue());
							if(!StringUtil.equals(cdje, "0Ԫ")){
								cdmodel.setCdje(cdje);
							}
							cdmodel.setCdfs("�����˹�ͬ�е��ý��");
							tempcdModels.add(cdmodel);
						}
					}
//					���óе����ͳе���ʽ
					if(tempcdModels.size()==1){
						tempcdModels.get(0).setCdfs("���˶����е��ý��");
					}
					cdModels.addAll(tempcdModels);
				}
			}
//			ֻ��һ���е����е��ܽ��
//			if(count==1){
//				for(WscpjgssfcdModel cdmodel:cdModels){
//					cdmodel.setCdje(ssfModel.getZje());
//				}
//			}
			boolean jeflag = true;
			for(WscpjgssfcdModel cdmodel:cdModels){
				if(!StringUtil.isBlank(cdmodel.getCdje())){
					jeflag = false;
					break;
				}
			}
			if(jeflag){
				List<WsCpjgssfjeModel> mos = ssfModel.getSsfjeModels();
				if(mos!=null && mos.size()>0 && mos.get(0).getValue()!=null){
					String s = mos.get(0).getValue();
					for(WscpjgssfcdModel cdmodel:cdModels){
						cdmodel.setCdje(s);
					}
				}
				
			}
			if(cdModels.size()>0){
				ssfModel.setSsfcdModels(cdModels);
			}
			wscpjgModel.setSsfModel(ssfModel);
		}
	}
	
	public static String formatJe(String je){
		if(StringUtil.isBlank(je)){
			return null;
		}
//		�滻�������ķ���
		StringUtil.replace(je, "��", "");
		StringUtil.replace(je, "��", ".");
//		�����Ƿ�淶����
		try{
			Double jeTemp = Double.parseDouble(je);
		}catch(Exception e){
//			1.��������=
			je = NumberConverter.convertFromChinese(je)+"";
		}
		return je;
	}
	
	public static String getNumberFromString(String content){
		if(StringUtil.isBlank(content))
			return null;
		else{
			String reg1 = "[^0-9.,](\\d){1,3}(\\,\\d\\d\\d)*(\\d)*(\\.)?(\\d)*Ԫ";
			String reg = "(\\d+(\\.\\d+)?)Ԫ";//������С��ƥ������
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(content);
			if(m.find()){
				return m.group();
			}
			return null;
		}
	}

	/**
	 * ���ÿ������������߲��ϡ���������
	 * ��������
	 * @param wscpjgModel
	 */
	private void setSsqk(WscpjgModel wscpjgModel, String cpjg) {
//		 ����������������XX��Ժ
		 int indexStart = cpjg.indexOf("������");
		 if(indexStart==-1){
			 indexStart = cpjg.indexOf("������");
		 }
		 if (indexStart==-1) {
			indexStart = cpjg.indexOf("������");
		}
	     int fyIndex =-1; 
	     String temp="";
	     if(indexStart>-1){
	    	 temp = cpjg.substring(indexStart);
	    	 fyIndex = temp.indexOf("��Ժ");
	    	 if(fyIndex>-1){
		    	 wscpjgModel.setKssz(temp.substring(3, fyIndex+2));
		     }
	     }
//      ���߲���
	     indexStart = cpjg.indexOf("Ժ�ݽ�");
	     fyIndex  = getMinforSsqk(cpjg);
	     if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    	 String sscl = cpjg.substring(indexStart+3, fyIndex).trim();
	    	 if(sscl.endsWith("��")){
	    		 sscl=sscl.substring(0, sscl.length()-1);
	    	 }
	    	 wscpjgModel.setSstjcl(sscl);
	     }
	     //��������
	     indexStart = cpjg.indexOf("�ʹ�֮����");
	     fyIndex  = cpjg.indexOf("����");
	     if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    	 wscpjgModel.setSsqx(cpjg.substring(indexStart+5, fyIndex+1));
	     }
	     
	}
	public static int getMinforSsqk(String content){
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.add(content.indexOf("Ԥ��"));
		indexList.add(content.indexOf("������"));
		indexList.add(content.indexOf("��������"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("��ͬʱԤ��"));
		indexList.add(content.indexOf("������"));
		indexList.add(content.indexOf("������"));
		indexList.add(content.indexOf("���չ���Ժ"));
		indexList.add(content.indexOf("��ͬʱ����"));
		int result=content.length();
		for(Integer index:indexList){
			if(index!=-1 && index<result){
				result = index;
			}
		}
		return result;
	}
	
	/**
	 * ����о��������
	 * @return
	 */
	private List<String> getPjjeLx() {
		String lxs = "[��, ��ǿ��, ���⳥��, �ۿ�, ���, ��Ϣ, ��Ϣ, ծȨ, ��ʦ��, ҽ�Ʒ�, Ӫ����, סԺ��ʳ������, �м��⳥��, ��ͨ��, "
				+ "������, ������, �󹤷�, ��ҽ��, �����˺�����, ����, �Ӱ๤��, ����, ���, ������, ����, ����Ʒ, ΥԼ��, ����, ��ʧ, �����⳥, "
				+ "�����, ʩ�ȷ�, ͸֧��, ����, �����, ���, ʹ�÷�, ����������, �����, ������, ������, ǲ����, �����, ������, �Ʋ���ȫ��, ������,"
				+ " ����, �����, ���ò�����, ���Ϸ�, ���ս�, ���Ͽ�, ���̿�, �Ͷ�����, �����, ������ʧ, �ۼۿ�, ������, Ƿ��, ����, �м���������,"
				+ " ʳ�޷�, Ƹ��, ����Ͷ���ͬ�⳥��, ���ɽ�, ������, �ӹ��ۿ�, �������, �ֽ�, ά�޷�, ��ӡ��, ծ��, ʵ��ծȨ�ķ���, �������, �������,"
				+ " Ѻ��, �Ӱ��, �����, �����, ����, ���շ�, ���Ʒ�, �������, ס�޷�, �������������, ���, ���޷�, �渶��, �������˷�, ��ѵ��, "
				+ "����ο��, ��װ��, ��ҵ��, ������, Ͷ�ʿ�, ������Ϣ, ��ʳ������, �ʽ�, �Ų�, ҩ��, ���ò�����, ��֤��, �޸���, �������������, ��Ϣ,"
				+ " ����, װ�޷�, ͣ����, ������, ����, �����, ���޷�, ��ȫ�����, ˮ��, �����, ������ʧ, �ʼķ�, �Ͽ�, ���, ����, ��������, ú��,"
				+ " ת�÷�, �ϳ���, �˲о�ҵ������, �������, Ħ�г���, סԺ��, �˲в�����, ����ҽ�Ʋ�����, ��ʦ�����, ������, ת�ÿ�, ��Ȩת�ÿ�, "
				+ "�˲м�����, ��ҽ������, ש��, ��ѯ��, �а���, ��ۿ�, ��ͨ�¹���ʧ, �������, ���ʿ�, ������, �Ȼ�����, ���������, ���ð���, "
				+ "���ز�����, �����, �а���, �ͷ�, �н��, �豸��, ˰��, ʩ����, ����, �а���, �����, ��֤��, ���Ϻ���֧��, ��Ʒ�, ���, ����ϼ�,"
				+ " Ӷ��, ������, ������, Ԥ����, ����������, �ֲĿ�, ��Ѻ��, ������, ���ܷ�, ��ů��, ������, ��ʳ��, ������ʧ��, �����, �ƿ�, �˲з�, "
				+ "ԤԼ��, �ʱ���, ���سа���Ӫ��, ���·�, �����ۿ�, ʯ�ӿ�, ��֪ͨ��, �����, ���������, �����, ˮ���, ���η�, ������, �绰��, ���÷�,"
				+ " ȡ֤��, �·���ʧ��, ��ͬ��, ����ʹ�÷�, �鵵��, �˹���, ���������, �����, ģ�߿�, �����⳥��, ɥ���, ����ο��, ���ò���, �������⳥��, ���üۿ�, ͣ����]";
		String[] strs = lxs.split(", ");
		List<String> jelx = new ArrayList<String>();
		for(String s:strs){
			if(StringUtil.equals(s, "[��")){
				s="��";
			}else if(StringUtil.equals(s, " ͣ����]")){
				s="ͣ����";
			}
			jelx.add(s);
		}
		return jelx;
	}

	/**
	 * �Ƿ��ǰ��������
	 * @param content
	 * @return
	 */
	private static boolean isAjslf(String content) {
		if (content.indexOf("���������") == 0 ||content.indexOf("���������") == 0 ||content.indexOf("�������������") == 0 ||content.indexOf("����һ�����Ϸ�") == 0 
				||(content.indexOf("���������")==2)||content.indexOf("������ȡ") >-1||content.indexOf("�������Ϸ�") == 0||content.indexOf("���Ϸ�") == 0||content.indexOf("�������Ϸ�") == 0||content.indexOf("���߰��������") == 0||content.indexOf("�����������") == 0
				||content.indexOf("һ�󰸼������") == 0||content.indexOf("����һ�������") == 0||content.indexOf("����һ�󰸼������") == 0||
				content.indexOf("һ�����󰸼������") > -1||content.indexOf("һ�󰸼����Ϸ�") > -1||content.indexOf("һ�����󰸼����Ϸ�") > -1
			     ||content.indexOf("���󰸼������") == 0 ||content.indexOf("�������������") == 0||content.indexOf("�������󰸼������") == 0
			    		 ||content.indexOf("�������������") == 0 ||content.indexOf("�����ڶ��󰸼������") == 0||content.indexOf("�ڶ��󰸼������") == 0
			 ||content.indexOf("���������") == 0||content.indexOf("���߰��������") == 0||content.indexOf("�������Ϸ�") >-1
			 ||content.indexOf("��ϵ�а������󰸼������") == 0 ||content.indexOf("�����") > -1||content.indexOf("�����հ���") > -1||content.indexOf("�����������հ���") > -1){
			return true;
		}else
			return false;
	}
	public static boolean startWithNumber(String content){
		if(content.indexOf("һ") == 0 || content.indexOf("��") == 0
				|| content.indexOf("��") == 0 || content.indexOf("��") == 0
				|| content.indexOf("��") == 0 || content.indexOf("��") == 0
				|| content.indexOf("��") == 0 || content.indexOf("��") == 0
				|| content.indexOf("��") == 0 || content.indexOf("ʮ") == 0
				|| content.indexOf("1") == 0 || content.indexOf("2") == 0
				|| content.indexOf("3") == 0 || content.indexOf("4") == 0
				|| content.indexOf("5") == 0 || content.indexOf("6") == 0 || content.indexOf("7") == 0
				|| content.indexOf("8") == 0 || content.indexOf("9") == 0 || content.indexOf("10") == 0){
			return true;
			
		}else{
			return false;
		}
	}
}

