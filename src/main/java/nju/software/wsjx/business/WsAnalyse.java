package nju.software.wsjx.business;

import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.HeadExceptionEnum;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.POIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class WsAnalyse extends AbstractAnalyseWs {

	private String wswjm;//�����ļ���

	private String wsnr;

	List<String> wsnrList;

	/**
	 * ��ĳһ����Ϊ�գ�����Ϊ��
	 */
	private List<String> ws;

	private List<String> sscyr;
	
	private String ssjl;

	private List<String> ajjbqk;

	private List<String> cpfxgc;

	private List<String> cpjg;

	private List<String> ww;

	private List<String> fl;
	
	private boolean hasAjjbqk=true;

	/**
	 * endΪ�öν���,��һ�ν������������п���endΪ-1��������end+1
	 */
	private int end = -1;
	private int ajjbqkend=0;
	private int ajjbqkpre=0;

	public WsAnalyse(String wswjm, String wsnr) {
		super();
		this.wswjm = wswjm;
		this.wsnr = wsnr;
		csh();
	}

	public WsAnalyse(byte[] wsByte, String wswjm) {
		super();
		this.wsnr = POIUtil.getWqcGString(wsByte, wswjm);
		this.wswjm = wsnr;
		csh();
	}

	private void csh() {
		init();
		if(wsnrList==null||wsnrList.size()==0)
			return;
		hfWs();//��������
		hfSscyr();//�������ϲ�����
		hfSsjl();//�������ϼ�¼                              
		hfCpfxgc();//���ֲ��з�������
		hfCpjg();//���ֲ��н��
		hfAjjbqk();//���ְ���������Ϣ			
		hfWw();//������β
		hfFl();//���ָ�¼
	}

	@Override
	public void init() {
//		if (wsnr != null) {
//			//System.out.println(wsnr);
//			wsnr.replaceAll("\r", "");
//			/**
//			 * ȥ��ȫ�ǰ�ǿո�
//			 */
//			wsnr.replaceAll("��", "");
//			wsnr.replaceAll(" ", "");
//			String[] qfsj = wsnr.split("\n");
//			wsnrList = new ArrayList<String>();
//			// �������ݰѿո�һ��ȥ��
//			for (int i = 0; i < qfsj.length; i++) {
//				if (!qfsj[i].equals("")) {
//					String str = "";
//					for (int j = 0; j < qfsj[i].length(); j++) {
//						if (qfsj[i].charAt(j) != ' ')
//							str = str + qfsj[i].charAt(j);
//					}
//					wsnrList.add(str);
//				}
//			}
//		}
		if (wsnr != null) {
			//System.out.println(wsnr);
			wsnr=wsnr.replaceAll("[\\r]", "");
			wsnr=wsnr.replaceAll("[\\t]", "");
		    /**
			 * ȥ��ȫ�ǰ�ǿո�
			 */
			wsnr=wsnr.replaceAll("��", "");
			wsnr=wsnr.replaceAll(" ", "");
			String[] qfsj = wsnr.split("\n");
			
			wsnrList = new ArrayList<String>();
			
			String rexExp="[.��:��;��?��)��\"��]";
			String rexExpcom = "[,��]";
			Pattern pattern=Pattern.compile(rexExp);
			Pattern patternCom=Pattern.compile(rexExpcom);
			
			String headExp="[Ժ|��|��]";
			Pattern pattern_ws=Pattern.compile(headExp);
			StringBuffer paragraph=new StringBuffer();
			int wsEnd=0;
			
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					if (HeadEnum.HasHead(qfsj[i])) {
						wsEnd = i ;
						break;
					}
				}
					
			}
			/**
			 * �ҵ���һ����β���д�
			 */
			int index=0;
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					if(isCpjgEnd(qfsj[i])){
						index=i;
						break;
					}
				}
					
			}
			// �������ݰѿո�һ��ȥ�����ҽ����������һ���ֲ��Ǳ������Զ�Ĭ�ϸ���һ��Ϊһ��
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					String str = qfsj[i];

					paragraph.append(str);
					if(!str.equals("")){
	
						//��ȡ���һ���ַ��ж�
						String temp=str.substring(str.length()-1);
						Matcher matcher=pattern.matcher(temp);
						Matcher matcherCom=pattern.matcher(temp);
						/*Matcher wsMarcher=pattern_ws.matcher(temp);*/
						/**
						 * ��������һ���ַ�������������Ĭ���������ޱ�㻻��
						 * �������������β���д�������Ϊ��Ҳ�����ޱ�㻻��
						 */
						
						//������ھ�ŵȽ�β���ж���һ���Ƿ���exceptionEnum���еĻ��ӽ���
						if(matcher.find()||(i<wsEnd)||i>=index){
							if(i < qfsj.length-1 && HeadExceptionEnum.HasHead(qfsj[i + 1])){
								paragraph.append(qfsj[++i]);
								if(i < qfsj.length-1 && HeadExceptionEnum.HasHead(qfsj[i + 1])){
									paragraph.append(qfsj[++i]);
								}
							}
							//����μӽ����������paragraph
							wsnrList.add(paragraph.toString());
						    paragraph.delete(0, paragraph.length());
						}
						//���û�о�ŵȽ�β���ж���һ����û��headEnum��ͷ
						else if(!matcher.find()){
							//�����һ�η��Ͽ�ͷ����HeadEnum							
							if(i < qfsj.length-1 && HeadEnum.HasHead(qfsj[i + 1])){
								wsnrList.add(paragraph.toString());
							    paragraph.delete(0, paragraph.length());
							}
						}
					}
					
					
				}
			}
			wsnrList.add(paragraph.toString());
			/*for(String string:qfsj){
			System.out.println(string);
			System.out.println("=====");
			}*/
		}
	}

	@Override
	public void hfWs() {
		FcUtil fcUtil =new FcUtil();
		int index=0;
		if(wsnrList.size()<10)
			index=wsnrList.size();
		else
			index=10;
		for (int i = 0; i < index; i++) {
			if (HeadEnum.HasHead(wsnrList.get(i))) {
				end = i - 1;
				break;
			}
		}
		// ��end
		if (end >= 0) {
			ws = new ArrayList<String>();
		}
		String find = "��Ժ|��|��";
		Pattern p = Pattern.compile(find);
		for (int i = 0; i < wsnrList.size() && i < end + 1; i++) {
			Matcher matcher = p.matcher(wsnrList.get(i));
			if(matcher.find()&&fcUtil.getWholeToken(wsnrList.get(i)).size()<15){
				ws.add(wsnrList.get(i));
			}

		}
	}

	@Override
	public void hfSscyr() {
		int pre = ++end;
		for (int i = end; i < wsnrList.size(); i++) {
			if(wsnrList.get(i).contains("һ��")){
				end=i;
				break;
			}
		//	System.out.println(wsnrList.get(i));
			String sj = getFirstContent(wsnrList.get(i));
			
			if (isNotSscyr(sj)) {
				end = i;
				break;
			}
			if (likeSscyr(sj)) {
				continue;
			} else {
				if (i + 1 < wsnrList.size()) {
					sj = getFirstContent(wsnrList.get(i + 1));					
					if (isNotSscyr(sj)) {
						end = i;
						break;
					}
					if (!likeSscyr(sj)) {
						end = i;
						break;
					}
				}
			}
		}
		if (end > pre) {
			sscyr = new ArrayList<String>();

		}
		end--;
		for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
			sscyr.add(wsnrList.get(i));
		}
	//	System.out.println(sscyr);
	}

	/**
	 * ��ȡ�׾��ȡ������
	 * 
	 * @param str
	 * @return
	 */
	public static  String getContent(String str) {
		String temp=deBracket(str);
		int dh = (temp.indexOf("��")!=-1?temp.indexOf("��"):temp.indexOf(","));
		int jh = (temp.indexOf("��")!=-1?temp.indexOf("��"):temp.indexOf("."));
		int max = 0;
		if (dh == -1) {
			max = jh;
		} else if (jh == -1) {
			max = dh;
		} else {
			max = jh > dh ? dh : jh;
		}
		String content = "";
		try {
			content = temp.substring(0, max);
		} catch (Exception e) {
		}
		return content;
	}
	/**
	 * �������ϲ�����ʱ����ȡ�׾�����
	 * ���û�ж��Ż��߾���򷵻�ԭ�ַ���
	 * @param str
	 * @return
	 */
	public static  String getFirstContent(String str) {
		String temp=deBracket(str);
		int dh = (temp.indexOf("��")!=-1?temp.indexOf("��"):temp.indexOf(","));
		int jh = (temp.indexOf("��")!=-1?temp.indexOf("��"):temp.indexOf("."));
		int max = 0;
		if (dh == -1) {
			max = jh;
		} else if (jh == -1) {
			max = dh;
		} else {
			max = jh > dh ? dh : jh;
		}
		String content = "";
		try {
			content = temp.substring(0, max);
		} catch (Exception e) {
			return str;
		}
		return content;
	}
	
	/**
	 * ȥ�����ŵ�����
	 * 
	 * @param content
	 * @return
	 */
	public static  String  deBracket(String content) {
		int count=20;
		//�����������Ϊȫ���ұ�Ϊ������
		if(content.indexOf("��") != -1)
			content = content.replace('��','(');		
		if(content.indexOf("��") != -1)
			content = content.replace('��',')');
		while (((content.indexOf("��") != -1 && content.indexOf("��") != -1)
				|| (content.indexOf("(") != -1 && content.indexOf(")") != -1))&&count>0) {
			count--;
			int left = content.indexOf("��");
			int right = content.indexOf("��");
			if (left != -1) {
				if (right != -1&&left<right) {
					//System.out.println(content);
					content = content.substring(0, left)
							+ content
							.substring(right + 1, content.length());
				}
			}
			left = content.indexOf("(");
			right = content.indexOf(")");
			if (left != -1) {
				if (right != -1&&left<right) {
					content = content.substring(0, left)
							+ content
							.substring(right + 1, content.length());
				}
			}
		}
		return content;
	}
	/**
	 * ȡ�������е�����,�������򷵻�null
	 * @param content
	 * @return
	 */
	public static  String  takeBracket(String content) {
		if ((content.indexOf("��") != -1 && content.indexOf("��") != -1)
				|| (content.indexOf("(") != -1 && content.indexOf(")") != -1)) {
			int left = content.indexOf("��");
			int right = content.indexOf("��");
			if (left != -1) {
				if (right != -1) {
					if (left+1<right)
						content = content.substring(left+1,right);
				}
			}
			left = content.indexOf("(");
			right = content.indexOf(")");
			if (left != -1) {
				if (right != -1) {
					content = content.substring(left+1, right);
				}
			}
		}else{
			content=null;
		}
		return content;
	}
	/**
	 * ��ȡ��ȡ������
	 * 
	 * @param str
	 * @return
	 */
	public static  ArrayList<String> getWholeContent(String str) {
		ArrayList<String> contentlist= new ArrayList<String>();
		String[] jhsplit=str.split("[��.]");
		for(int i=0;i<jhsplit.length;i++){
			String content=jhsplit[i];
			String []dhsplit=content.split("[��,]");
			for(int j=0;j<dhsplit.length;j++){
				String dhcontent=dhsplit[j];
				String []fhsplit=dhcontent.split("[��;]");
				for(int k=0;k<fhsplit.length;k++){
					if(fhsplit[k].length()>0){
						contentlist.add(fhsplit[k]);
					}
				}
			}
		}
		return contentlist;
	}
	/**
	 * �����׾�ķִ����жϣ�С��10���ִʵ��п��������ϲ�����
	 * 
	 * @param words
	 * @return
	 */
	public boolean likeSscyr(String words) {
		List<String> list = FcUtil.getWholeToken(words);
		return list.size() >= 15 ? false : true;
	}

	/**
	 * �жϿ϶��������ϲ�����
	 * 
	 * @param words
	 * @return
	 */
	private boolean isNotSscyr(String words) {
		/**
		 * û�����ϵ�λ�����д� ���߳� ��� һ�� ���� ���� ����ͬʱ����ԭ���� �ִʴ���15 �����б�Ժ ����Ϊ ���϶� �Ķ������������ϲ�����
		 */
		if (words.contains("�߳�") || words.contains("���")
				|| words.contains("һ��") || words.contains("����")
				|| words.contains("����")||words.contains("������")
				|| (words.contains("ԭ��") && words.contains("����"))
				|| !HeadEnum.HasHead(words) 
				|| words.contains("��Ժ") || words.contains("��Ϊ")
				|| words.contains("�϶�"))

			return true;
		return false;
	}
	public void hfSsjl() {
		int pre=++end;
		ajjbqkpre=pre;
		ssjl=wsnrList.get(pre);
	//	System.out.println(ssjl);
	}
	@Override
	public void hfAjjbqk() {
		ajjbqkpre++;
		if (ajjbqkpre < ajjbqkend) {
			ajjbqk = new ArrayList<String>();
		}
		for (int i = ajjbqkpre; i <ajjbqkend && i < wsnrList.size(); i++) {
			ajjbqk.add(wsnrList.get(i));
		}
	}

	/**
	 * �жϿ϶������ڰ����������
	 * 
	 * @param word
	 * @return
	 */
	private boolean isNotAjjjbqk(String word) {//�������û�б��õ� 
		if (word.contains("�ö�����") || word.contains("�о�����")
				|| word.contains("��������") || word.contains("��Ժ��Ϊ")
				|| word.contains("��Ժ�������Ϊ")||word.contains("���ֵ���") )
//		if (word.contains(" ��Ժ��Ϊ") || word.contains(" ��Ժ�������Ϊ"))
			return true;
		if(word.contains("���Э������"))
			return true;
		return false;
	}
	private boolean isCpfxgc_1(String word) {
		if ( word.indexOf("��Ժ��Ϊ")==0|| word.indexOf("��Ժ��������Ϊ")==0
				|| word.indexOf("��Ժ�������Ϊ")==0 ||word.contains("���ֵ���")||word.indexOf("��Ժ������Ϊ")==0 )
			return true;
		if(word.contains("���Э������")||word.contentEquals("�������Э��")||word.contains("�������Э��"))
			return true;
		Pattern pattern = Pattern.compile("((����)|(����)|(����)).*((�ö�����)|(�о�����))");
		if(pattern.matcher(word).find())
		//	if(word.contains("����")&&word.contains("�ö�����"))
			return true;
			
		return false;
	}
	private boolean isCpfxgc_2(String word) {
		if ( word.contains("��Ժ��Ϊ")|| word.contains("��Ժ��������Ϊ")
				|| word.contains("��Ժ�������Ϊ")||word.contains("���ֵ���") ||word.indexOf("��Ժ������Ϊ")==0)
			return true;
		if(word.contains("���Э������")||word.contentEquals("�������Э��")||word.contains("�������Э��"))
			return true;
		if(word.contains("����")&&word.contains("�ö�����"))
			return true;
		return false;
	}
	@Override
	public void hfCpfxgc() {
		int pre = 0;
		boolean b=false;
		ajjbqkend=end;
		for(int i=wsnrList.size()-1;i>=end;i--){
	//		System.out.println(wsnrList.get(i));
			if(isCpfxgc_1(wsnrList.get(i))){				
				pre = i;
				ajjbqkend=i;
				b=true;				
				break;
			}
		}
		
		if(isCpfxgc_1(wsnrList.get(ajjbqkend-1))){
			pre = ajjbqkend -1;
			ajjbqkend = ajjbqkend - 1;
			b = true;
		}
		if(b==false){
			for(int i=wsnrList.size()-1;i>=end;i--){
				if(isCpfxgc_2(wsnrList.get(i))&&!isCpfxgc_2(wsnrList.get(i-1))){
					pre = i;
					ajjbqkend=i;
					b=true;
					break;
				}
			}
			if(isCpfxgc_2(wsnrList.get(ajjbqkend-1))){
				pre = ajjbqkend -1;
				ajjbqkend = ajjbqkend - 1;
				b = true;
			}
		}
		if(ajjbqkend!=0){
			for (int i = pre; i < wsnrList.size(); i++) {
				if (isCpfxgcEnd(wsnrList.get(i))) {
					end = i+1;
					break;
				}
			}
			if (end > pre) {
				cpfxgc=new ArrayList<String>();
			}
			end--;
			for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
				cpfxgc.add(wsnrList.get(i));
			}
		}
	//	System.out.println(cpfxgc);
	}

	private boolean isCpfxgcEnd(String word) {
		if (word.contains("�ö�����") || word.contains("�о�����")
				|| word.contains("��������")||word.contains("�������")||word.contains("�������")||word.contains("��Ըǩ��"))
			return true;
		return false;
	}

	@Override
	public void hfCpjg() {
		if(ajjbqkend!=0){
			int pre = ++end;
			for (int i = end; i < wsnrList.size(); i++) {
				if (isCpjgEnd(wsnrList.get(i))) {
					end = i;
					break;
				}
			}
			if (end > pre) {
				cpjg=new ArrayList<String>();
			}
			end--;
			for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
				cpjg.add(wsnrList.get(i));
			}
		}else{
			int pre=0;
			for(int i=wsnrList.size()-1;i>=end;i--){
				if(isCpfxgcEnd(wsnrList.get(i))){
					pre=i+1;
					ajjbqkend=i;
					break;
				}
			}
			for (int i = pre; i < wsnrList.size(); i++) {
				if (isCpjgEnd(wsnrList.get(i))) {
					end = i;
					break;
				}
			}
			if (end > pre) {
				cpjg=new ArrayList<String>();
			}
			end--;
			for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
				cpjg.add(wsnrList.get(i));
			}
		}
		
	}
	private boolean isCpjgEnd(String word) {
		if (word.contains("���г�") || word.contains("����Ա")|| word.contains("���Ա") 
				|| word.contains("��������Ա") || word.contains("��¼Ա") || word.contains("��������Ա")||(word.length()<14&&word.contains("��")&&word.contains("��")&&word.contains("��")))
			return true;
		return false;
	}
	
	@Override
	public void hfWw() {
		int pre = ++end;
		for (int i = end; i < wsnrList.size(); i++) {
			if (isWwEnd(wsnrList.get(i))) {
				end = i+1;
				break;
			}
		}
		if (end > pre) {
			ww=new ArrayList<String>();
		}
		end--;
		for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
			ww.add(wsnrList.get(i));
		}
	}
	private boolean isWwEnd(String word) {
		if (word.contains("��¼") && word.contains("���")){ 
			return true;
		}
		else if (word.indexOf("��¼")==-1 && word.contains("���")){
		return true;
		}
			return false;
	}
	
	public void hfFl() {
		// TODO Auto-generated method stub
		int pre = ++end;
		for (int i = end; i < wsnrList.size(); i++) {
			if (isFlEnd(wsnrList.get(i))) {
				end = i;
				break;
			}
		}
		if (end > pre) {
			fl=new ArrayList<String>();
		}
		end--;
		for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
			fl.add(wsnrList.get(i));
		}
	}
	private boolean isFlEnd(String word) {
		if (word.contains("PAGE"))
			return true;
		return false;
	}

	public String getWswjm() {
		return wswjm;
	}

	public void setWswjm(String wswjm) {
		this.wswjm = wswjm;
	}

	public String getWsnr() {
		return wsnr;
	}

	public void setWsnr(String wsnr) {
		this.wsnr = wsnr;
	}

	public List<String> getWs() {
		return ws;
	}

	public void setWs(List<String> ws) {
		this.ws = ws;
	}

	public List<String> getSscyr() {
		return sscyr;
	}

	public void setSscyr(List<String> sscyr) {
		this.sscyr = sscyr;
	}

	public List<String> getAjjbqk() {
		return ajjbqk;
	}

	public void setAjjbqk(List<String> ajjbqk) {
		this.ajjbqk = ajjbqk;
	}

	public List<String> getCpfxgc() {
		return cpfxgc;
	}

	public void setCpfxgc(List<String> cpfxgc) {
		this.cpfxgc = cpfxgc;
	}

	public List<String> getCpjg() {
		return cpjg;
	}

	public void setCpjg(List<String> cpjg) {
		this.cpjg = cpjg;
	}

	public List<String> getWw() {
		return ww;
	}

	public void setWw(List<String> ww) {
		this.ww = ww;
	}

	public List<String> getFl() {
		return fl;
	}

	public void setFl(List<String> fl) {
		this.fl = fl;
	}

	public String getSsjl() {
		return ssjl;
	}

	public void setSsjl(String ssjl) {
		this.ssjl = ssjl;
	}

	public void hfFj() {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args){
		String ssjl = "ԭ���������뱻�����£�����ܽ����޹�˾�Ͷ�����һ������Ժ��2015��9��6���������������ɴ�������Ա������ü��׳��򹫿���ͥ����������ԭ��֮ί�д��������ڣ�����֮ί�д����˸��⡢����溵�ͥ�μ����ϡ��������������սᡣ";
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(ssjl);
	//	System.out.println(contentlist.size());
	}

}
