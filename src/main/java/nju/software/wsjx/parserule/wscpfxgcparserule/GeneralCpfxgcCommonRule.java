package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.HashMap;
/**
 * ���з������̹��õķ���
 * @author wangzh
 */
public class GeneralCpfxgcCommonRule {
	/**
	 * ɾ�������е���������
	 * @param fltw
	 * @return
	 */
	public String delUrl(String fltw) {
		//ɾ�������в��ɼ��ַ�\u200b
		String str = "";
		for (int i = 0; i < fltw.length(); i++) {
		      int ch = (int) fltw.charAt(i);
		      if (ch != '\u200b')
		    	  str += fltw.charAt(i);
		}		
		//ɾ�������д��ڵ���������
		while(str.contains("(http") || str.contains("(javascript")){			
			int zkh = str.indexOf("(http");
			if(zkh == -1)
				zkh = str.indexOf("(javascript");	
			int khNum = 1;
			int ykh = zkh;
			while(khNum != 0){
				ykh++;
				if(str.charAt(ykh) == '(')
					khNum++;
				if(str.charAt(ykh) == ')')
					khNum--;					
			}
			str = str.substring(0, zkh) + str.substring(ykh + 1);					
		}
		return str;
	}
	/**
	 * ����content��ȡ��Ŀ��Ŀ��������Ŀ��Ŀ��map
	 * @param content
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getTmkm(String content){
		content = content.substring(content.indexOf("��") + 1);
		String tmString[] = content.split("��");
		//����*��*�����ֶ�
		if(content.lastIndexOf("��")==content.length()-1){
			String[] copy=new String[tmString.length+1];
			for(int i=0;i<tmString.length;i++){
				copy[i]=tmString[i];
			}
			copy[copy.length-1]=tmString[tmString.length-1].lastIndexOf("��")==-1?"":tmString[tmString.length-1].substring(tmString[tmString.length-1].lastIndexOf("��"));
			tmString=copy;
		}
		HashMap<String, String> ftMap = new HashMap<String, String>();// ����map<��Ŀ����Ŀ>																				
		if((tmString.length == 1)){			
			if(!tmString[0].contains("��")){
				if(!tmString[0].contains("��") && !tmString[0].contains("��")){
					//����ʮ��֮�涨
					if(tmString[0].contains("��")){												
						ftMap.put(tmString[0].substring(tmString[0].indexOf("��") + 1,tmString[0].length()), "û�п�Ŀ");																	
					}
					//��ʮ����֮�涨
					else{
						ftMap.put(tmString[0], "û�п�Ŀ");
					}
				}
				else{
					//����ʮ��������֮�涨
					if(tmString[0].contains("��")){
						if(tmString[0].indexOf("��") != tmString[0].lastIndexOf("��")){
							int d1 = tmString[0].indexOf("��");								
							String qtmString = tmString[0].substring(tmString[0].indexOf("��") + 1);
							int d2 = qtmString.indexOf("��")+tmString[0].indexOf(qtmString);
							String tm = tmString[0].substring(d1 + 1,d2);
							String km = tmString[0].substring(d2,tmString[0].length());
							ftMap.put(tm, km);
						}
					}														
				}
			}
			else {
				//����ʮ�š�����ʮ����֮�涨
				if(tmString[0].indexOf("��") < tmString[0].indexOf("��")){
					String temp = tmString[0].substring(0,tmString[0].indexOf("��"));
					
					if(!temp.contains("��") && !temp.contains("��")){
						ftMap.put(tmString[0].substring(
								tmString[0].indexOf("��") + 1,
								tmString[0].indexOf("��")), "û�п�Ŀ");
					}
					String temp2 = tmString[0].substring(tmString[0].indexOf("��") + 1);							
					if(!temp2.contains("��") && !temp2.contains("��") && temp2.contains("��")){
						ftMap.put(temp2.substring(temp2.indexOf("��") + 1), "û�п�Ŀ");
					}
				}
			}
		}
		
		for (int i = 0; i < tmString.length - 1; i++) {											
			if (!tmString[i + 1].contains("��")
					&& !tmString[i + 1].contains("��")
					&& tmString[i].contains("��")) {								
				ftMap.put(tmString[i].substring(
						tmString[i].lastIndexOf("��") + 1,
						tmString[i].length()), "û�п�Ŀ");	
				//����ʮ����������ʮ֮�涨
				if((i == tmString.length - 2) && tmString[i + 1].contains("��")){
					
					ftMap.put(tmString[i+1].substring(
							tmString[i+1].lastIndexOf("��") + 1,
							tmString[i+1].length()), "û�п�Ŀ");
				}
			} else if (tmString[i].indexOf("��") > -1) {
				String tm = tmString[i].substring(
						tmString[i].lastIndexOf("��") + 1,
						tmString[i].length());
				
				String km = "";	
				if (i < tmString.length - 1
						&& tmString[i + 1].contains("��")) {
					km = tmString[i + 1].substring(0,
							tmString[i + 1].lastIndexOf("��") + 1);							
					ftMap.put(tm, km);
				}
				if (i < tmString.length - 1
						&& tmString[i + 1].contains("��")) {
					km = tmString[i + 1].substring(0,
							tmString[i + 1].lastIndexOf("��") + 1);
					if(ftMap.get(tm) != null){
						if(!ftMap.get(tm).contains(km))
							ftMap.put(tm, km);
					}
					else {
						ftMap.put(tm, km);
					}
				}
				
			}
		}
		return ftMap;
	}
	/**
	 * ɾ�����涨�����ַ���
	 * @param zh
	 * @return
	 */
	public String delGd(String zh){
		String[] gdList = {"�Ĺ涨","֮�涨","�涨"};
		for(String gd:gdList){
			if (zh.indexOf("��") != -1) {
				String shString = zh.substring(zh.indexOf("��"));
				if(shString.contains(gd)){				
					return zh.substring(0,zh.indexOf(shString) + shString.lastIndexOf(gd));						
				}	
			}
		}
		return zh;
	}
}
