package nju.software.wsjx.parserule.wswsparserule;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

/**
 * ��Ϊ���׼��������иĶ�������һ��ͨ�õĽ�������
 * @author lr12
 *
 */
public class GeneralWsParseRule implements WsParseRule{

	@Override
	public WswsModel jxWswsModel(List<String> wsws) throws ParseException {
		WswsModel wswsModel = new WswsModel();
		if(wsws == null)
			return wswsModel;
		String find1 = "��Ժ";
		String find2 = "��";
		String find3 = "��";
		//��������������λ
		String wszzdw="��Ժ";
		wswsModel.setWszzdw(wszzdw);
		Pattern p1 = Pattern.compile(find1);
		Pattern p2 = Pattern.compile(find2);
		Pattern p3 = Pattern.compile(find3);
		for (int i = 0; i < wsws.size(); i++) {
			Matcher matcher1 = p1.matcher(wsws.get(i));
			Matcher matcher2 = p2.matcher(wsws.get(i));
			Matcher matcher3 = p3.matcher(wsws.get(i));
			if (matcher1.find()){
				String jbfy=wsws.get(i);
				wswsModel.setJbfy(jbfy);
				//��Ժ�������
				if(jbfy.contains("�߼�")){
					wswsModel.setFyjb("�߼�");
				}else if(jbfy.contains("�м�")){
					wswsModel.setFyjb("�м�");
				}else if(jbfy.contains("���")){
					wswsModel.setFyjb("���");
				}else{
					wswsModel.setFyjb("����");
				}
				//����������ʡ������
				String[] zxsqj={"����","���","�Ϻ�","����"};//ֱϽ��ȫ��
				String[] zzqqj={"���ɹ�","����","����","����","�½�"};//������ȫ��
				String xzqhProv=null;
				boolean hasZzs=false;
				for(String zzx:zxsqj){
					if(jbfy.contains(zzx)){
						xzqhProv=zzx;
						hasZzs=true;
					}
				}
				for(String zzq:zzqqj){
					if(jbfy.contains(zzq)){
						xzqhProv=zzq;
					}
				}
				if(jbfy.indexOf("ʡ")!=-1){
					xzqhProv=jbfy.substring(0, jbfy.indexOf("ʡ"));
				}
				wswsModel.setXzqhProv(xzqhProv);
				
				//�����������У�����
				String xzqhCity=null;
				
				if(jbfy.indexOf("��")!=-1&&jbfy.indexOf("ʡ")!=-1){
					if (jbfy.indexOf("ʡ")<jbfy.indexOf("��"))
					xzqhCity=jbfy.substring(jbfy.indexOf("ʡ")+1, jbfy.indexOf("��")+1);
				}else if(jbfy.indexOf("��")!=-1&&jbfy.indexOf("������")!=-1){
					if (jbfy.indexOf("��")<jbfy.indexOf("��"))
					xzqhCity=jbfy.substring(jbfy.indexOf("��")+1, jbfy.indexOf("��")+1);
				}else if(jbfy.indexOf("��")!=-1&&!hasZzs){
					if (0<jbfy.indexOf("��"))
					xzqhCity=jbfy.substring(0, jbfy.indexOf("��")+1);
				}
				else if(jbfy.indexOf("��")==-1){
					xzqhCity=xzqhProv;
				}
				//����һ��Ժ����Ժ���
				for(String zzx:zxsqj){
					if(jbfy.contains(zzx)){
						if(jbfy.contains("��һ�м�")){
							xzqhCity="һ��Ժ";
						}else if(jbfy.contains("�ڶ��м�")){
							xzqhCity="����Ժ";
						}else if(jbfy.contains("�����м�")){
							xzqhCity="����Ժ";
						}
						else{
							xzqhCity=null;
						}
					}
						
				}
				wswsModel.setXzqhCity(xzqhCity);
				
				
				
			}
			
			if (matcher2.find()){
				String wsmc=wsws.get(i);
				wswsModel.setWsmc(wsmc);
				//������������
				String ajxz=null;//��������
				if(wsmc.length()>3)
					ajxz=wsmc.substring(0, 2);
				wswsModel.setAjxz(ajxz+"����");
				//������������
				String wszl=null;
				int index_shu=wsmc.indexOf("��");
				if(index_shu!=-1 && index_shu-2 < index_shu+1)
					if (index_shu-2<index_shu+1&&index_shu-2>0)

					wszl=wsmc.substring(index_shu-2, index_shu+1);
				wswsModel.setWszl(wszl);
						
				}
			
			if (matcher3.find() && (!wsws.get(i).contains(",")) && (!wsws.get(i).contains("��")) 
					&& (!wsws.get(i).contains("��")) && (!wsws.get(i).contains("��")) ) {				
				String ah=wsws.get(i);
				ah = delUrl(ah);//ɾ������
				ah = SBC2DBC(ah);//ȫ��ת���								
				wswsModel.setAh(ah);
				String year = "2016";
				String temp = wsws.get(i);
				
				
				String str = null;

//				if (temp.lastIndexOf("��") != -1) {
//					str = temp.substring(temp.lastIndexOf("��"),
//							temp.lastIndexOf("��"));
//					year = str.substring(1, str.length());
//				} else if (temp.lastIndexOf("(") != -1) {
//					str = temp.substring(temp.lastIndexOf("("),
//							temp.lastIndexOf(")"));
//					year = str.substring(1, str.length());
//				} else if (temp.lastIndexOf("��") != -1) {
//					str = temp.substring(temp.lastIndexOf("��"),
//							temp.lastIndexOf("��"));
//					year = str.substring(1, str.length());
//				}

				if (temp!=null){
					temp = temp.replace("��","(");
					temp = temp.replace("��",")");
				}

				if (temp.indexOf("��") != -1&&temp.indexOf("��")!=-1) {
					if (temp.lastIndexOf("��")<=temp.lastIndexOf("��")) {
						str = temp.substring(temp.lastIndexOf("��"),
								temp.lastIndexOf("��"));
						year = str.substring(1, str.length());						
					}
				} else if (temp.indexOf("(") != -1&&temp.indexOf(")")!=-1) {
					if (temp.lastIndexOf("(")<=temp.lastIndexOf(")")) {
						str = temp.substring(temp.lastIndexOf("("),
								temp.lastIndexOf(")"));
						try{
							int integer = Integer.parseInt(str);
						}
						catch(Exception e){
							str = temp.substring(temp.indexOf("("),
									temp.indexOf(")"));
						}
						year = str.substring(1, str.length());
					}
				} else if (temp.indexOf("��") != -1&&temp.indexOf("��")!=-1) {
					if (temp.lastIndexOf("��")<=temp.lastIndexOf("��")) {
						str = temp.substring(temp.lastIndexOf("��"),
								temp.lastIndexOf("��"));						
						try{
							int integer = Integer.parseInt(str);
						}
						catch(Exception e){
							str = temp.substring(temp.indexOf("��"),
									temp.indexOf("��"));
						}
						year = str.substring(1, str.length());
					}
				}else if (temp.indexOf("[") != -1&&temp.indexOf("]")!=-1) {
					if (temp.lastIndexOf("[")<=temp.lastIndexOf("]")) {
						str = temp.substring(temp.lastIndexOf("["),
								temp.lastIndexOf("]"));						
						try{
							int integer = Integer.parseInt(str);
						}
						catch(Exception e){
							str = temp.substring(temp.indexOf("["),
									temp.indexOf("]"));
						}
						year = str.substring(1, str.length());
					}
				}	
				
				//ȫ��ת���
				if (year != null) {
					String land = SBC2DBC(year);
			        if(land != null)
			        	wswsModel.setLand(land);
				}
				
				//�������г���Ŀǰֻ�ֳ�һ���������
				String spcx=null;//���г���
				String[] spcxqj={"һ��","����","����","�������","��Ͻ"};//���г���ȫ��
				String[] spcxjcqj={"��","��","��","��","Ͻ"};//���г�����ȫ��
				//Todo
				//���г�����
				for(int j=0;j<spcxqj.length;j++){
					if(temp.contains(spcxjcqj[j])){
						spcx=spcxqj[j];
						
					}
				}
				wswsModel.setSpcx(spcx+"����");
			}
			
			
			
		}
		//������������
		String ajlx=null;
		String ajxz=wswsModel.getAjxz();
		String spcx=wswsModel.getSpcx();
		if (ajxz!=null&&wswsModel.getSpcx()!=null&&ajxz.contains("����"))
			
		ajlx=ajxz.substring(0, ajxz.indexOf("����"))+wswsModel.getSpcx();
		
		String parseName=ajlx!=null?ajlx.replaceAll("����", ""):null;
		wswsModel.setParseName(parseName);
		wswsModel.setAjlx(ajlx);

		return wswsModel;
	}
	/**
	 * ȫ��ת���
	 * @param data
	 * @return
	 */
	public static String SBC2DBC(String data) {
		if(data == null)
			return data;
		char[] charArray = data.toCharArray();  
        for (int k = 0; k < charArray.length; k++) {  
            if (charArray[k] == '\u3000') {  
                charArray[k] =' ';  
            } else if (charArray[k] > '\uFF00' &&  
                    charArray[k]  < '\uFF5F') {  
                charArray[k] = (char) (charArray[k] - 65248);  
            } else {  	
            }  
        }
	    String result = new String(charArray);		
	    return result;		
	}
	/**
	 * ɾ����������
	 * @param data
	 * @return
	 */
	public static String delUrl(String data) {
		if(data == null)
			return null;
		//ɾ�����ɼ��ַ�\u200b
		String str = "";
		for (int i = 0; i < data.length(); i++) {
		      int ch = (int) data.charAt(i);
		      if (ch != '\u200b')
		    	  str += data.charAt(i);
		}		
		//ɾ�����ڵ���������
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
	
}
