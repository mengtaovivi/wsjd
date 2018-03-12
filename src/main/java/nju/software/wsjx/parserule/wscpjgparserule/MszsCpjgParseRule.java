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
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
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
public class MszsCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
		// TODO Auto-generated method stub
		List<String> cpjg = wsAnalyse.getCpjg();
		
//		��ȫ�Ƿֺż��������о��������
		List<String> cpjg_r=new ArrayList<String>();
		for(int k=0;k<cpjg.size();k++){
			String[] cpjg_r1 =cpjg.get(k).split("��");
			for(int j=0;j<cpjg_r1.length;j++){
				cpjg_r.add(cpjg_r1[j]);
			}
		}
		cpjg=cpjg_r;
		
		WscpjgModel wscpjgModel=new WscpjgModel();
		List<String> cpjgnrList=new ArrayList<String>();
		List<PjjgnrModel> pjjgList =new ArrayList<PjjgnrModel>();
		String pjjgnr="";
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

//			�����˼���
			if(pjjgnr.contains("׼")&&
					(pjjgnr.contains("��������")||pjjgnr.contains("��������")||pjjgnr.contains("����")
							||(pjjgnr.contains("����")&&(pjjgnr.contains("����")||pjjgnr.contains("����"))))){
				setCsrjh(wscpjgModel, wssscyrModellist, cpjg.get(i),wsAnalyse);
			}
			
			for(PjjgnrModel pm:pjjgList){//��д�о����ģ��
				setPjje(pm,pjjelx);
			}
			wscpjgModel.setPjjgList(pjjgList);
		}
		
//		������������ѣ�ÿ���˵����е��򼸸��˹�ͬ�е������Ϸ�
		setSsfcd(wscpjgModel, wssscyrModellist);
		
//		�᰸����ܶ�
		wscpjgModel = setJabdje(wscpjgModel,cpjg);
	
		
		
//		��������᰸��ʽ
		wscpjgModel = setMszsjafs(wscpjgModel, pjjgnr,cpjg);
		
		
		return wscpjgModel;
	}
		

	/**
	 * �������Ϸѳе�
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	public static void setSsfcd(WscpjgModel wscpjgModel,List<WssscyrModel> wssscyrModellist){
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
	 * ���ó����˼���
	 * ����һ��
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	private static void setCsrjh(WscpjgModel wscpjgModel, List<WssscyrModel> wssscyrModellist, String cpjg,
			WsAnalyse wsAnalyse) {
		// TODO Auto-generated method stub
		List<String> csrjh = new ArrayList<String>();
		for(WssscyrModel sscyr:wssscyrModellist){
			if(StringUtil.contains(cpjg, sscyr.getSscyr())){
				csrjh.add(sscyr.getSscyr());
			}
		}
		if(csrjh.size()>0){
			wscpjgModel.setCsrjh(csrjh);
			if(StringUtil.contains(wsAnalyse.getSsjl(), "�ͽ�")){
				wscpjgModel.setCslx("ԭ���뱻��ͥ��ͽ����");
			}else{
				wscpjgModel.setCslx("ԭ����������");
			}
		}
	}

	/**
	 * �����᰸��Ľ��᰸����ܶ�
	 * ����XXԪ���˴�XX�����ڽ᰸��Ľ��
	 * @param wscpjgModel
	 * @return
	 */
	private static WscpjgModel setJabdje(WscpjgModel wscpjgModel,List<String> cpjg) {
		// TODO Auto-generated method stub
//		List<String> pjjgList = wscpjgModel.getCpjgnr();
		List<String> pjjgList = new ArrayList<String>();
		for(int i=0;i<wscpjgModel.getPjjgList().size();i++){
			pjjgList.add(wscpjgModel.getPjjgList().get(i).getPjjgnr());
		}
		List<String> jes = new ArrayList<String>();
		if(pjjgList!=null){
			for(String jg :pjjgList){
			    getJe(jg,jes,true);
			}
		}
		if(jes.size()>0){
			wscpjgModel.setJabde(jes);//�᰸��Ķ�
		}
		Double zje = 0.0;
		try{
			for(String s:jes){
				zje = zje+Double.parseDouble(s);
			}
			if(zje!=0.0){
				wscpjgModel.setJabdze(zje+"Ԫ");//�᰸����ܶ�
			}
		}catch(Exception e){
			
		}
		return wscpjgModel;
	}

	/**
	 * ��ȡ�ַ���ĩβ������
	 * ����XXԪ���˴����Ʋ�����
	 * @param content
	 * @param moneyList
	 * @return
	 */
	private static List<String> getJe(String content, List<String> moneyList, boolean gjFlag) {
		// TODO Auto-generated method stub
		String reg = "Ԫ";
		String[] nums = content.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='.'||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='��')){
		    		index=j;
		    		break;
		    	}
		    }
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	�ų�����
    		if(nums[i].indexOf("����")!=index-1&& (!StringUtil.isBlank(je_toAdd))){
	        	if(je_toAdd.endsWith("��")){
		    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
		    		try{
		    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                        je_toAdd=je_addDou+"";
                    }catch(Exception e){
                    	je_toAdd="";
		    		}
	        	}
	        	if(!StringUtil.isBlank(je_toAdd)){
	        		 moneyList.add(je_toAdd);
	        	}
	        }
	    }
		return moneyList;
	}

	/**
	 * �Ƿ��ǰ��������
	 * @param content
	 * @return
	 */
	private static boolean isAjslf(String content) {
		// TODO Auto-generated method stub
		if (content.indexOf("���������") == 0 ||content.indexOf("���������") == 0 ||content.indexOf("�������������") == 0 ||content.indexOf("����һ�����Ϸ�") == 0 
				||(content.indexOf("���������")==2)||content.indexOf("������ȡ") >-1||content.indexOf("�������Ϸ�") == 0||content.indexOf("���Ϸ�") == 0||content.indexOf("�������Ϸ�") == 0||content.indexOf("���߰��������") == 0||content.indexOf("�����������") == 0
				||content.indexOf("һ�󰸼������") > -1||content.indexOf("����һ�������") > -1||content.indexOf("����һ�󰸼������") > -1||
				content.indexOf("һ�����󰸼������") > -1||content.indexOf("һ�󰸼����Ϸ�") > -1||content.indexOf("һ�����󰸼����Ϸ�") > -1
			     ||content.indexOf("���󰸼������") == 0 ||content.indexOf("�������������") == 0||content.indexOf("�������󰸼������") == 0
			    		 ||content.indexOf("�������������") == 0 ||content.indexOf("�����ڶ��󰸼������") == 0||content.indexOf("�ڶ��󰸼������") == 0
			 ||content.indexOf("���������") == 0||content.indexOf("���߰��������") == 0||content.indexOf("�������Ϸ�") >-1
			 ||content.indexOf("��ϵ�а������󰸼������") == 0 ||content.indexOf("�����") > -1||content.indexOf("�����հ���") > -1||content.indexOf("�����������հ���") > -1||content.indexOf("��ȫ��") == 0){
			return true;
		}else
			return false;
	}

	/**
	 * ������������Ľ᰸��ʽ
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @return
	 */
	private WscpjgModel setMszsjafs(WscpjgModel wscpjgModel, String pjjgnr,List<String> cpjg) {
		// TODO Auto-generated method stub
		
//		//�ڰ����������model��Ѱ�ҳ���ԭ�ö���������������
//		//WsajjbqkModel ajjbqk =new WsajjbqkModel();
//		WsModel wModel =new WsModel();
//		String ajjbqk=wModel.getWsajjbqSegment();
//		boolean convertFlag = false;//�жϳ���ԭ�ö������������־
//		if (ajjbqk.contains("����")&&ajjbqk.contains("����")) {
//			convertFlag=true;
//		}
		
		if (!StringUtil.equals(pjjgnr, "")) {
			if(!pjjgnr.contains("����")&&!pjjgnr.contains("���")&&pjjgnr.contains("ά��")){
//				 1.�������ߣ�ά��ԭ��
//				 2.ά��XX�����XX�����ڸ���
				 wscpjgModel.setJafs("ά��");
			 }else if((pjjgnr.contains("׼��")||pjjgnr.contains("׼��"))&&pjjgnr.contains("��������")&&pjjgnr.contains("����")&&(pjjgnr.contains("�ö�")||pjjgnr.contains("�о�"))){
				 wscpjgModel.setJafs("׼�賷�߲�����ԭ����");
			 }else if (pjjgnr.contains("���")&&pjjgnr.contains("���")) {
				wscpjgModel.setJafs("׼�賷�߲�����ԭ����");
			}else if (pjjgnr.contains("����")&&pjjgnr.contains("��Ӧ")) {
				wscpjgModel.setJafs("����ԭ�ö�����������");
			}else if(pjjgnr.contains("����")&&pjjgnr.contains("����")&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����ԭ�в���������");
			 }else if(pjjgnr.contains("����")&&(pjjgnr.contains("����")||pjjgnr.contains("��������"))){
				 wscpjgModel.setJafs("��������");
				 wscpjgModel.setSffhcs("��");
			 }else if(pjjgnr.contains("����")&&pjjgnr.contains("�ö�")&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����ԭ�ö���ָ������");
			 }else if(pjjgnr.contains("����")&&pjjgnr.contains("�ö�")&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����ԭ�ö���ָ������");
			 }else if(pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����");
			 }else if (pjjgnr.contains("����")&&pjjgnr.contains("��Ը���")) {
				wscpjgModel.setJafs("����");
			}else if((pjjgnr.contains("׼��")||pjjgnr.contains("׼��")||pjjgnr.contains("׼��"))&&(pjjgnr.contains("��������")||pjjgnr.contains("��������"))){
				 wscpjgModel.setJafs("׼�賷������");
			 }else if(pjjgnr.contains("��")&&(pjjgnr.contains("����")||pjjgnr.contains("��������"))&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("���������ߴ���");
			 }else if((pjjgnr.contains("ά��")&&(pjjgnr.contains("����")||pjjgnr.contains("���")))// 1.ά��XX������XX����ά��XX�����XX
					 ||(pjjgnr.contains("����")&&pjjgnr.contains("����")&&pjjgnr.contains("��������"))){// 2.����XX�о�,����XX��������
				 wscpjgModel.setJafs("����");
			 }else if(pjjgnr.contains("�ս�����")){
				 wscpjgModel.setJafs("�ս�");
			 } else if (pjjgnr.contains("����")&&pjjgnr.contains("�ö�")&&pjjgnr.contains("����")) {
				 wscpjgModel.setJafs("����ԭ�ö�");
			}else if(pjjgnr.contains("��ֹ����")){
				 wscpjgModel.setJafs("����");
			 }
			 if(StringUtil.isBlank(wscpjgModel.getJafs())){
					wscpjgModel.setJafs("����");
			 }
		}
		for (String it : cpjg) {
			if (it.contains("������")) {
				wscpjgModel.setJafs("����");
			}
		}
		return wscpjgModel ;
	}
	
	public  List<String> getPjjeLx(){
		String lxs = "[��, ��ǿ��, ���⳥��, �ۿ�, ���, ��Ϣ, ��Ϣ, ծȨ, ��ʦ��, ҽ�Ʒ�, Ӫ����, סԺ��ʳ������, �м��⳥��, ��ͨ��, "
				+ "������, ������, �󹤷�, ��ҽ��, �����˺�����, ����, �Ӱ๤��, ����, ���, ������, ����, ����Ʒ, ΥԼ��, ����, ��ʧ, �����⳥, "
				+ "�����, ʩ�ȷ�, ͸֧��, ����, �����, ���, ʹ�÷�, ����������, �����, ������, ������, ǲ����, �����, ������, �Ʋ���ȫ��, ��ȫ��,������,"
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
	 * �ҳ�һ�仰��Ľ��
	 * @param content
	 * @return ��������List,���硰����20Ԫ����Ϣ10Ԫ��������{��20Ԫ������10Ԫ��}
	 */
	public static  void setPjje(PjjgnrModel pjjgnrModel,List<String> jelxList){
		List<PjjeModel> pjjeModels = new ArrayList<PjjeModel>();
		String content = pjjgnrModel.getPjjgnr();
//		List<String> moneyList = new ArrayList<String>();
		String reg = "Ԫ";
		String[] nums = content.split(reg);//�ԡ�Ԫ���ָ�
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='.'||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='��'||nums[i].charAt(j)==',')){
		    		index=j;
		    		break;
		    	}
		    }
//	    	�ӽ�β�ҵ�����
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	
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
	        	if(!StringUtil.isBlank(je_toAdd)){
//	        		�ҵ���������
//	        		 moneyList.add(je_toAdd+"Ԫ");
        			 PjjeModel jeModel = new PjjeModel();
    				 jeModel.setValue(je_toAdd+"Ԫ");
//	        		 ���ҽ������
	        		 for(String s:jelxList){
	 					if(StringUtil.contains(nums[i], s)){
	 						if(jeModel.getCategorys()==null){
	 							jeModel.setCategorys(new ArrayList<String>());
	 						}
	 						jeModel.getCategorys().add(s);
	 					}
	 				}
	        		 pjjeModels.add(jeModel);
	        	}
	        }
	    }
	    if(pjjeModels.size()>0){
	    	pjjgnrModel.setPjjeList(pjjeModels);
	    }
	}
}
