package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;


public class MoneyUtil {
	public static List<String> getJe(String content){
		List<String> moneyList = new ArrayList<String>();
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
    		if(!StringUtil.isBlank(je_toAdd)){
	        	if(je_toAdd.endsWith("��")){
		    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
		    		try{
		    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                        je_toAdd=je_addDou+"";
                    }catch(Exception e){
                    	//����Ǻ���
                    	int intvalue = NumberConverter.convertFromChinese(je_toAdd)*10000;
                    	if (intvalue!=1 && !je_toAdd.equals("һ��")) {
                    		je_toAdd = intvalue+"";
						}
		    		}
	        	}
	        	if(!StringUtil.isBlank(je_toAdd)){
	        		 moneyList.add(je_toAdd+"Ԫ");
	        	}
	        }
	    }
		return moneyList;
	}
	public static String getJestr(String content){
		String reg = "Ԫ";
    	 int index=-1;
    	 char[] chars = content.toCharArray();
    	 for(int j=chars.length-1;j>-1;j--){
    		 if(!(Character.isDigit(content.charAt(j))||content.charAt(j)=='��'||content.charAt(j)=='��'||content.charAt(j)=='��')){
	    		index=j;
	    		break;
	    	}
	    }
    	String je_toAdd = StringUtil.ToDBC(content.substring(index+1, content.length()));
    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	�ų�����
		if(!StringUtil.isBlank(je_toAdd)){
        	if(je_toAdd.endsWith("��")){
	    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
	    		try{
	    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                    je_toAdd=je_addDou+"";
                }catch(Exception e){
                	//����Ǻ���
                	int intvalue = NumberConverter.convertFromChinese(je_toAdd)*10000;
                	if (intvalue!=1 && !je_toAdd.equals("һ��")) {
                		je_toAdd = intvalue+"";
					}
	    		}
        	}
        	return je_toAdd+"Ԫ";
        }
		return null;
	}
	
	/**
	 * ��ȡ�ı�����Ԫ��β�Ľ��ԭ��
	 * @param content
	 * @return
	 */
	public static List<String> getMoeny(String content){
		
		List<String> moneyList = new ArrayList<String>();
		String reg = "Ԫ";
		String[] nums = content.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))
	    				 ||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='.'
	    				 ||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='ǧ'
	    				 ||nums[i].charAt(j)=='��'||nums[i].charAt(j)==',' || charIsNum(nums[i].charAt(j)))){
		    		index=j;
		    		break;
		    	}
		    }
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	�ų�����
    		if(!StringUtil.isBlank(je_toAdd)){
    			moneyList.add(je_toAdd+"Ԫ");
	        }
	    }
		return moneyList;
	}
	/**
	 * ��ȡ�ı�����Ԫ��β�Ľ��ԭ���Լ���������
	 * @param content
	 * @param flag �Ƿ���Ҫ��ȡ���㷽��
	 * @return
	 */
	public static List<PjjeModel> getJeModels(String content,boolean flag){
		List<PjjeModel> moneyList = new ArrayList<PjjeModel>();
		List<String> jelsx = getPjjeLx();
		String reg = "Ԫ";
		String temp = StringUtil.removeBreak(content);
		String[] nums = temp.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))
	    				 ||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='.'
	    				 ||nums[i].charAt(j)=='��'||nums[i].charAt(j)=='ǧ'
	    				 ||nums[i].charAt(j)=='��'||nums[i].charAt(j)==',' || charIsNum(nums[i].charAt(j)))){
		    		index=j;
		    		break;
		    	}
		    }
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	�ų�����
    		if(!StringUtil.isBlank(je_toAdd)){
    			PjjeModel model = new PjjeModel();
    			model.setValue(je_toAdd+"Ԫ");
    			int indexOfCat = -1;
    			 for(String s:jelsx){
	 					if(StringUtil.contains(nums[i], s) && nums[i].indexOf(s)+s.length() == nums[i].indexOf(je_toAdd)){
	 						if(model.getCategorys()==null){
	 							model.setCategorys(new ArrayList<String>());
	 						}
	 						model.getCategorys().add(s);
	 					}
	 				}
//    			 �������㷽������ʼ��������
    			 if(flag){
    				 if(i<nums.length-1 && content.indexOf(nums[i])>-1 && content.indexOf(nums[i])<content.indexOf(nums[i+1]))  {
    					 setJsfsAndKsjsrq(content.substring(content.indexOf(nums[i]), content.indexOf(nums[i+1])), je_toAdd+"Ԫ", model); 
    				 }else if(i==nums.length-1 && content.indexOf(nums[i])>-1){
    					 setJsfsAndKsjsrq(content.substring(content.indexOf(nums[i])), je_toAdd+"Ԫ", model); 
    				 }
    			 }
    			 
    			 moneyList.add(model);
	        }
	    }
		return moneyList;
	}
	
	public static void setJsfsAndKsjsrq(String content,String je,PjjeModel model){
		int indexOfJe = content.indexOf(je)+je.length();
		int indexRight = content.indexOf("(")!=-1 ?content.indexOf("(") :content.indexOf("��") ;
		if(indexRight==indexOfJe){
			int indexLeft = content.indexOf(")")!=-1 ?content.indexOf(")") :content.indexOf("��") ;
			if(indexLeft>-1){
//				ȡ�����ڵ�����
				String fs = content.substring(indexRight+1, indexLeft-1);
				int indexOfYear = content.indexOf("��");
				int indexOfDay = content.indexOf("����")!=-1?content.indexOf("����"):content.indexOf("��-");
				if(indexOfYear>-1 && indexOfDay>-1 && indexOfDay>indexOfYear){
					String ksrq = content.substring(indexOfYear-4,indexOfDay+1);
					String temp = content.substring(indexOfDay+1);
					indexOfYear = temp.indexOf("��");
					indexOfDay = temp.indexOf("��");
					model.setKssj(ksrq);
//					System.out.println("start"+ksrq);
					if(indexOfYear>-1 && indexOfDay>-1 && indexOfDay>indexOfYear){
						ksrq = temp.substring(indexOfYear-4,indexOfDay+1);
					    model.setJssj(ksrq);
//						System.out.println("end"+ksrq);
					}
				}else{
					model.setJsfs(fs);
//					System.out.println("all"+fs);
				}
			}
		}
	}
	public static boolean charIsNum(char content){
		if(content=='һ' || content=='��' ||content=='��' ||content=='��' ||content=='��' ||content=='��' ||
				content=='��' ||content=='��' ||content=='��' ||content=='ʮ' ){
			return true;
		}
		return false;
	}
	
	public static List<String> getPjjeLx(){
		String lxs = "[��, �泵��, ��ǿ��, ���⳥��, �ۿ�, ���, ��Ϣ, ��Ϣ, ծȨ, ��ʦ��, ҽ�Ʒ�, Ӫ����, סԺ��ʳ������, �м��⳥��, ��ͨ��, "
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
				+ " ȡ֤��, �·���ʧ��, ��ͬ��, ����ʹ�÷�, �鵵��, �˹���, ���������, �����, ģ�߿�, �����⳥��, ɥ���, ����ο��, ���ò���, �������⳥��, ���üۿ�, ͣ����, ͷ��, �綯����]";
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
	
}
