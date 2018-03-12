package nju.software.wsjx.parserule.wscpjgparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.Enum.MsesFhcsyyEnum;
import nju.software.wsjx.model.Enum.SsfEnum;
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
 * ���¶�����н������
 * @author wangzh
 *
 */
public class MsesCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
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
//			�����Ƿ�֧����������
//			if (cpjg.get(i).contains("����")&& (cpjg.get(i).contains("Υ��") || cpjg.get(i).contains("����"))) {
//				wscpjgModel.setSfzcssqq("ȫ��֧��");
//			}
// 			һ�󰸼����������
//			if(StringUtil.contains(ajlxEnum.getAjlx(), "һ��")&&cpjg.get(i).indexOf("�粻����")>-1){
//				setSsqk(wscpjgModel, cpjg.get(i));
//			}
//			�����˼���
			if(pjjgnr.contains("׼")&&
					(pjjgnr.contains("��������")||pjjgnr.contains("��������")||pjjgnr.contains("����")
							||(pjjgnr.contains("����")&&(pjjgnr.contains("����")||pjjgnr.contains("����"))))){
				setCsrjh(wscpjgModel, wssscyrModellist, cpjg.get(i),wsAnalyse);
			}
		}
		
//		��һ�������Ҫ�������н������
//		if(StringUtil.contains(ajlxEnum.getAjlx(), "����һ��")||StringUtil.contains(ajlxEnum.getAjlx(), "���¶���")){
//	     	�����о��������
			for(PjjgnrModel pjjgnrmodel:pjjgList){
				setQlywr(pjjgnrmodel, wssscyrModellist);
				setPjzxqx(pjjgnrmodel);
				setPjje(pjjgnrmodel,pjjelx);
//				setPjzrcdfs(pjjgnrmodel);
			}
			wscpjgModel.setPjjgList(pjjgList);
//		}
//		�������������
		setSsfcd(wscpjgModel, wssscyrModellist);
//		�᰸��Ľ��
		wscpjgModel = setJabdje(wscpjgModel);
//		���¶���᰸��ʽ
		wscpjgModel = setMsesjafs(wscpjgModel, pjjgnr);
//		wscpjgModel = setJafsByAjlx(wscpjgModel, pjjgnr,allnr, ajlxEnum,wsAnalyse);
//		�Ƿ񷢻�����
		wscpjgModel.setSffhcs(StringUtil.equals(wscpjgModel.getSffhcs(), "��")?"��":"��");
//		��������ԭ��
		if(StringUtil.equals(wscpjgModel.getJafs(), "��������")){
			List<String> cpfxgc = wsAnalyse.getCpfxgc();
			if(cpfxgc!=null){
				for(String gc:cpfxgc){
					if(gc.contains("��ʵ����")){
						wscpjgModel.setFhcsyy("ԭ�о��϶�������ʵ����");
						break;
					}else if(MsesFhcsyyEnum.getMsesfhccyy(gc)!=null){
							wscpjgModel.setFhcsyy(MsesFhcsyyEnum.getMsesfhccyy(gc));
							break;
					}else{
						wscpjgModel.setFhcsyy("����");
					}
				}
			}
		}
//		����һ�������Ͻ����
//		if(StringUtil.contains(ajlxEnum.getAjlx(), "����һ��")||StringUtil.contains(ajlxEnum.getAjlx(), "����һ��")){
//			setTcgxyy(wscpjgModel, allnr);
//		}
		for (int j = 0; j < cpjgnrList.size(); j++) {
//			�����Ƿ�֧����������
			if (cpjgnrList.get(j).contains("����")
					&& cpjgnrList.get(j).contains("����")
					&& cpjgnrList.get(j).contains("����")) {
				wscpjgModel.setSfzcssqq("����֧�ֲ��ֲ�֧��");
			} else if (cpjgnrList.get(j).contains("��������")) {
				wscpjgModel.setSfzcssqq("ȫ������");
			} else if (cpjgnrList.get(j).contains("����")
					&& cpjgnrList.get(j).contains("Υ��")) {
				wscpjgModel.setSfzcssqq("ȫ��֧��");
			}
//			����ʤ���߷�
			if ((wscpjgModel.getSfzcssqq() == "����֧�ֲ��ֲ�֧��")
					|| (wscpjgModel.getSfzcssqq() == "ȫ��֧��")) {
				wscpjgModel.setSbsf("���߷�");
			} else if (wscpjgModel.getSfzcssqq() == "ȫ������") {
				wscpjgModel.setSbsf("Ӧ�߷�");
			}
		}
		
//		�����Ƿ�֧�������⳥
		for (int j = 0; j < cpjgnrList.size(); j++) {
			if (cpjgnrList.get(j).contains("����")
					&& cpjgnrList.get(j).contains("�⳥")
					&& cpjgnrList.get(j).contains("����")) {
				wscpjgModel.setSfzcxzpc("��");
//				���������⳥���
				if(cpjgnrList.get(j).contains("Ԫ")){
					String xzpcje = "";
					String reg = "(\\d+(\\.\\d+)?)";//������С��ƥ������
					Pattern p = Pattern.compile(reg);
					Matcher m = p.matcher(cpjgnrList.get(j));
					if(m.find()){
						xzpcje = m.group() + "Ԫ";
						wscpjgModel.setXzpcje(xzpcje);
					}
				}
				break;
			}else if (cpjgnrList.get(j).contains("����")) {
				wscpjgModel.setSfzcxzpc("��");
			}
		}
//		��ͨ�¹��⳥˳���Ƿ���ȷ
		PjjgService pjjgservice = new PjjgServiceImpl();
		boolean sx = pjjgservice.isPeichangIndexCorrect(pjjgnr, wssscyrModellist);
		if(sx){
			wscpjgModel.setPcsxsfzq("��ȷ");
		}else{
			wscpjgModel.setPcsxsfzq("����");
		}
		DsrbxgsService dsrService = new DsrbxgsServiceImpl();
		if(dsrService.jqxBg(wssscyrModellist)){
			wscpjgModel.setBsgssfbg("��ȷ");
		}else{
			wscpjgModel.setBsgssfbg("����");
		}
		return wscpjgModel;
	}
	
/*	*//**
	 * �����᰸��ʽ
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @param ajlxEnum
	 * @return
	 *//*
	public static WscpjgModel setJafsByAjlx(WscpjgModel wscpjgModel,String pjjgnr,String allPjjg,AjlxEnum ajlxEnum,WsAnalyse wsAnalyse){

			return setMsesjafs(wscpjgModel, pjjgnr);
	}
*/	
	/**
	 * �������¶���Ľ᰸��ʽ
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @return
	 */
	public static WscpjgModel setMsesjafs(WscpjgModel wscpjgModel,String pjjgnr){
		if(!StringUtil.equals(pjjgnr, "")){
			 if(!pjjgnr.contains("����")&&!pjjgnr.contains("���")&&pjjgnr.contains("ά��")){
//				 1.�������ߣ�ά��ԭ��
//				 2.ά��XX�����XX�����ڸ���
				 wscpjgModel.setJafs("ά��");
			 }
			 else if(pjjgnr.contains("����")&&pjjgnr.contains("����")&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����ԭ�в���������");
			 } 
			 else if(pjjgnr.contains("����")&&(pjjgnr.contains("����")||pjjgnr.contains("��������"))){
				 wscpjgModel.setJafs("��������");
				 wscpjgModel.setSffhcs("��");
			 }else if(pjjgnr.contains("����")&&pjjgnr.contains("�ö�")&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����ԭ�ö���ָ������");
			 }else if(pjjgnr.contains("����")&&pjjgnr.contains("�ö�")&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����ԭ�ö���ָ������");
			 }else if(pjjgnr.contains("����")){
				 wscpjgModel.setJafs("����");
			 }else if(pjjgnr.contains("����")&&pjjgnr.contains("�о�")&&(pjjgnr.contains("׼��")||pjjgnr.contains("׼��"))&&pjjgnr.contains("��������")){
				 wscpjgModel.setJafs("׼�賷�����߲�����һ���о�");
			 }else if((pjjgnr.contains("׼��")||pjjgnr.contains("׼��")||pjjgnr.contains("׼��"))&&(pjjgnr.contains("��������")||pjjgnr.contains("��������"))){
				 wscpjgModel.setJafs("׼�賷������");
			 }else if(pjjgnr.contains("��")&&(pjjgnr.contains("����")||pjjgnr.contains("��������"))&&pjjgnr.contains("����")){
				 wscpjgModel.setJafs("���������ߴ���");
			 }else if((pjjgnr.contains("ά��")&&(pjjgnr.contains("����")||pjjgnr.contains("���")))// 1.ά��XX������XX����ά��XX�����XX
					 ||(pjjgnr.contains("����")&&pjjgnr.contains("����")&&pjjgnr.contains("��������"))){// 2.����XX�о�,����XX��������
				 wscpjgModel.setJafs("����");
			 }else if(pjjgnr.contains("�ս�����")){
				 wscpjgModel.setJafs("�ս�");
			 } else if(pjjgnr.contains("��ֹ����")){
				 wscpjgModel.setJafs("����");
			 }
			 if(StringUtil.isBlank(wscpjgModel.getJafs())){
					wscpjgModel.setJafs("����");
			 }
		}
		return wscpjgModel;
	}
	/**
	 * �����᰸��Ľ��᰸����ܶ�
	 * ����XXԪ���˴�XX�����ڽ᰸��Ľ��
	 * @param wscpjgModel
	 * @return
	 */
	public static WscpjgModel setJabdje(WscpjgModel wscpjgModel){
		List<String> pjjgList = wscpjgModel.getCpjgnr();
		List<String> jes = new ArrayList<String>();
		if(pjjgList!=null){
			for(String jg :pjjgList){
			    getJe(jg,jes,true);
			}
		}
		if(jes.size()>0){
			wscpjgModel.setJabde(jes);
		}
		Double zje = 0.0;
		try{
			for(String s:jes){
				zje = zje+Double.parseDouble(s);
			}
			if(zje!=0.0){
				wscpjgModel.setJabdze(zje+"Ԫ");
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
	public static List<String> getJe(String content,List<String> moneyList,boolean gjFlag){
		String reg = "Ԫ";
		String[] nums = content.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='��')){
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
	public static boolean isAjslf(String content){
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
	
	/**
	 * ����Ȩ���ˡ�������
	 * @param model
	 */
	public static void setQlywr(PjjgnrModel pjjgnrModel,List<WssscyrModel> wssscyrModellist){
		String pjjg = pjjgnrModel.getPjjgnr();

		HashMap<String,String> qlr = new HashMap<String, String>();
		HashMap<String,String> ywr = new HashMap<String, String>();
//		����������index
		int firstIndex = getMin(pjjg);//��ǰ����
		
		HashMap<String, Integer> nameIndexMap = new HashMap<String, Integer>();
		for(WssscyrModel cyr:wssscyrModellist){
			if(!StringUtil.isBlank(cyr.getSscyr())){
				String name  = cyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				nameIndexMap.put(name, nameIndex);
			}
		}
//		������������ڶ���֮��ģ�������ǰ�������flag=false
		boolean flag=true;
		if(firstIndex>-1&&firstIndex!=pjjg.length()){
			for(Map.Entry<String, Integer> entry:nameIndexMap.entrySet()){
				if(entry.getValue()>firstIndex){
					flag=false;
					break;
				}
			}
		}
		
//		��һ������֧���Ķ��ʣ�֮ǰΪ�����ˣ�֮��ΪȨ����
//		������е��������ڶ���֮ǰ������ʹ��ǰ�󷽷�
		if(!flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				if(nameIndex>-1 &&nameIndex<firstIndex ){
					//������������
					ywr.put(name, sscyr.getSssf());
				}else if(nameIndex>-1 &&nameIndex>firstIndex){
					qlr.put(name, sscyr.getSssf());
				}
			}
			pjjgnrModel.setQlr(qlr);
			pjjgnrModel.setYwr(ywr);
		}else if(flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
//			���������ڶ���֮ǰ
			int qlIndex=-1;
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				if(!StringUtil.isBlank(name)){
					String ssdwname = sscyr.getSssf()+sscyr.getSscyr(); 
//					��������˴��ڣ���ô����������ǵ�һ��Ȩ����
					if(nameIndexMap.get(name)>-1 &&(pjjg.indexOf("��"+name)>0||(pjjg.indexOf("��"+ssdwname)>0))){
						qlr.put(name, sscyr.getSssf());
						qlIndex=nameIndexMap.get(name);
						break;
					}
				}
			}
			if(qlIndex!=-1){
				for( WssscyrModel  sscyr:wssscyrModellist){
					String name = sscyr.getSscyr(); 
					if(!StringUtil.isBlank(name)){
						if(nameIndexMap.get(name)>-1 && nameIndexMap.get(name)<qlIndex){
							ywr.put(name, sscyr.getSssf());
						}
					}
					
				}
			}
			pjjgnrModel.setQlr(qlr);
			pjjgnrModel.setYwr(ywr);
		}
	}
	
	/**
	 * ���Ȩ�����������жϵ��н�㣬����һ������֧���Ķ���λ��
	 * @param content
	 * @return
	 */
	public static int getMin(String content){
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.add(content.indexOf("�⳥"));
		indexList.add(content.indexOf("֧��"));
		indexList.add(content.indexOf("�е�"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("�⸶"));
		indexList.add(content.indexOf("����"));
		int result=content.length();
		for(Integer index:indexList){
			if(index!=-1 && index<result){
				result = index;
			}
		}
		return result;
	}
	
	/**
	 * �����о�ִ������
	 * @param pjjgnrModel
	 */
	public static void setPjzxqx(PjjgnrModel pjjgnrModel){
		if(pjjgnrModel!=null && !StringUtil.isBlank(pjjgnrModel.getPjjgnr())){
			String pjjgnr = pjjgnrModel.getPjjgnr();
			int indexOfStart=-1;
			String start="";
			String[] starts={"���о���Ч֮����","���о���Ч��","���о���Ч��","���о���������Ч����","���о���������Ч��֮����","���о���Ч֮����","���о���Ч֮����","���о���Ч��"};
			for(int i=0;i<starts.length;i++){
				indexOfStart = pjjgnr.indexOf(starts[i]);
				if(indexOfStart>-1){
					start = starts[i];
					break;
				}
			}
			if(indexOfStart>-1){
				try{
					int timeStart = indexOfStart+start.length();
					start = pjjgnr.substring(timeStart, pjjgnr.length());
					int endIndex = start.indexOf("��");
					start = start.substring(0, endIndex);
					pjjgnrModel.setPjzxqx(start);
				}catch(Exception e){
				}
			}
		}
	}
	
	public static void setPjzrcdfs(PjjgnrModel pjjgnrModel){
		if(pjjgnrModel!=null && !StringUtil.isBlank(pjjgnrModel.getPjjgnr())){
			pjjgnrModel.setPjzrcdfs("��������");
		}
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
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='��')){
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
	/**
	 * ���ÿ������������߲��ϡ���������
	 * ����һ��
	 * @param wscpjgModel
	 */
	public static void setSsqk(WscpjgModel wscpjgModel,String cpjg){
//		 ����������������XX��Ժ
		 int indexStart = cpjg.indexOf("������");
		 if(indexStart==-1){
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
//       ��Ժ�ݽ�
	     indexStart = cpjg.indexOf("Ժ�ݽ�");
	     fyIndex  = getMinforSsqk(cpjg);
	     if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    	 String sscl = cpjg.substring(indexStart+3, fyIndex).trim();
	    	 if(sscl.endsWith("��")){
	    		 sscl=sscl.substring(0, sscl.length()-1);
	    	 }
	    	 wscpjgModel.setSstjcl(sscl);
	     }
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
	 * ���ó����˼���
	 * ����һ��
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	public static void setCsrjh(WscpjgModel wscpjgModel,List<WssscyrModel> wssscyrModellist,String cpjg,WsAnalyse wsAnalyse){
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
//		String ssfcdjl = wscpjgModel.getAjslf();
//		WsCpjgssfModel ssfModel = new WsCpjgssfModel();
//		if(!StringUtil.isBlank(ssfcdjl)){
////			-----------���ϷѼ�¼--------------
//			ssfModel.setSsfjl(ssfcdjl);
////			------�������Ϸ����࣬�������Ϸѽ��-----
//			char[] chars = ssfcdjl.toCharArray();
//			String ssfzl,ssfje;
//			int indexOfSsf=-1,indexOfYuan=-1,lengthOfSsfName=-1;
//			List<WsCpjgssfjeModel> jeModels = new ArrayList<WsCpjgssfjeModel>();
//			int ssfzje=0;
//			
//			//����Ԫ����
//			String[] ssfstrList = ssfcdjl.split("Ԫ");
////			for(String ssfstr:ssfstrList){
//			for(int i=0;i<ssfstrList.length-1;i++){
//				String ssfstr = ssfstrList[i];
////				����ÿһ�����Ϸ�
//				for(SsfEnum ssfEnum:SsfEnum.values()){
//					if(StringUtil.contains(ssfstr, ssfEnum.getSsfName())){
//						ssfstr=StringUtil.trim(ssfstr);
//						String je = ssfstr.substring(ssfEnum.getSsfName().length()+ssfstr.indexOf(ssfEnum.getSsfName()), ssfstr.length());//+"Ԫ";
//						je = je.replace('��', '.');
//						je=je.replaceAll("��", "");
//						if(je.contains("������ȡ")){
//							je = je.substring(je.indexOf("������ȡ")+4);
//						}
//						if(je.contains("��������ȡ��")){
//							je = je.substring(6);
//						}
//						if(StringUtil.contains(je,"����")){
//							je = je.substring(2);
//						}else if(StringUtil.contains(je,"��")||StringUtil.contains(je,"��")||StringUtil.contains(je,"Ϊ")||StringUtil.contains(je,"��")){
//							je = je.substring(1);
//						}
////						je����������
//						if(!StringUtil.isNum(je)&&!StringUtil.contains(je, "�����")){
//							int jeInt = NumberConverter.convertFromChinese(je);
//							if((jeInt>10 && je.length()>1)||jeInt==10){
//								je=jeInt+"";
//							} 
//						}
//						if(ssfEnum.equals(SsfEnum.JBSQ)&&jeModels.size()>0){
//							jeModels.get(jeModels.size()-1).setValue(je+"Ԫ");
//						}else{
//							WsCpjgssfjeModel jeModel = new WsCpjgssfjeModel(je+"Ԫ", ssfEnum.getSsfName());
//							jeModels.add(jeModel);
//							try{
//								ssfzje+=Integer.parseInt(ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan));
//							}catch(Exception e){
//								
//							}
//						}
//						break;
//					}
//					
//				}
//			}
////			�������Ϸ�����
////			for(SsfEnum ssfEnum:SsfEnum.values()){
////				ssfzl = ssfEnum.getSsfName();//���Ϸ���������
////				lengthOfSsfName = ssfzl.length();//���Ϸ��������Ƴ���
////				indexOfSsf = ssfcdjl.indexOf(ssfzl);//���Ϸ�λ��
//////					�����Ϸ�λ�ÿ�ʼ�ҵ���һ��Ԫ������ ����������100Ԫ��
////				for(int j=indexOfSsf+lengthOfSsfName;j<chars.length;j++){
////		   	    	 if(chars[j] == 'Ԫ'){
////		   	    		indexOfYuan = j;
////		   	    		 break;
////		   	    	 }
////			    }
////				if(indexOfSsf!=-1 &&indexOfYuan!=-1 ){
//////						�������Ϸ��ܽ��
////					try{	
////						ssfje = ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan+1);
////						if(ssfEnum.equals(SsfEnum.JBSQ)){
////							if(jeModels.get(jeModels.size()-1)!=null){
////								jeModels.get(jeModels.size()-1).setValue(ssfje);
////							}
////						}else{
////							WsCpjgssfjeModel jeModel = new WsCpjgssfjeModel(ssfje, ssfzl);
////							jeModels.add(jeModel);
////							ssfzje+=Integer.parseInt(ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan));
////						}
////					}catch(Exception e){
////						ssfzje=0;
////					}
////				}
////			}
//			
//			if(jeModels.size()>0){
//				ssfModel.setSsfjeModels(jeModels);
//				ssfModel.setZje(ssfzje+"Ԫ");
//			}
////			--------�������Ϸѳе�-----------
//			List<WscpjgssfcdModel> cdModels = new ArrayList<WscpjgssfcdModel>();
//			HashMap<String, String> sscyrMap = new HashMap<String, String>();
//			for(WssscyrModel sscyr:wssscyrModellist){
//				sscyrMap.put(sscyr.getSscyr(), sscyr.getSsdw());
//			}
//		    String[] ssfs = ssfcdjl.split("��");
//		    int count=0;
//			for(String s:ssfs){
//				String cdje = getNumberFromString(s);
//				List<WscpjgssfcdModel> tempcdModels = new ArrayList<WscpjgssfcdModel>();
//				if(StringUtil.contains(s, "����")||StringUtil.contains(s, "�е�")){
//					count++;
//					for(Map.Entry<String, String> entry:sscyrMap.entrySet()){
//						if(StringUtil.contains(s, entry.getKey())){
//							WscpjgssfcdModel cdmodel = new WscpjgssfcdModel();
//							cdmodel.setCdr(entry.getKey());
//							cdmodel.setCdrdw(entry.getValue());
////							if(!StringUtil.equals(cdje, "0Ԫ")){
////								cdmodel.setCdje(cdje);
////							}
//							cdmodel.setCdfs("�����˹�ͬ�е��ý��");
//							tempcdModels.add(cdmodel);
//						}
//					}
////					���óе����ͳе���ʽ
//					if(tempcdModels.size()==1){
//						tempcdModels.get(0).setCdfs("���˶����е��ý��");
//					}
//					cdModels.addAll(tempcdModels);
//				}
//			}
////			ֻ��һ���е����е��ܽ��
//			if(count==1){
//				for(WscpjgssfcdModel cdmodel:cdModels){
//					cdmodel.setCdje(ssfModel.getZje());
//				}
//			}
//			if(cdModels.size()>0){
//				ssfModel.setSsfcdModels(cdModels);
//			}
//			wscpjgModel.setSsfModel(ssfModel);
//		}
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
	
	public  List<String> getPjjeLx(){
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
	 * �����Ƿ������ϽȨ����
	 * @param wscpjgModel
	 */
	public static void setTcgxyy(WscpjgModel wscpjgModel,String allNr){
		if(StringUtil.contains(allNr, "��Ͻ����")){
			wscpjgModel.setSftcgxyy("��");
		}else{
			wscpjgModel.setSftcgxyy("��");
		}
	}
}