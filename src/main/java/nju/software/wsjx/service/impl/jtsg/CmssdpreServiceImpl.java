package nju.software.wsjx.service.impl.jtsg;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.jtsg.CmssdpreService;

import java.util.ArrayList;
import java.util.List;

public class CmssdpreServiceImpl implements CmssdpreService{

	@Override
	public String getSgxq(List<String> cmssd) {
		List<String> cmssdlist=new ArrayList<String>();
		boolean find=false;
		String sgxq="δ�ἰ";
		for(int i=0;i<cmssd.size();i++){
			String[] content=cmssd.get(i).split("��");
			for(int j=0;j<content.length;j++){
				cmssdlist.add(content[j]);
			}		
		}
		for(int i=0;i<cmssdlist.size();i++){
			String content=cmssdlist.get(i);
			if(isFwc(content)){
				find=true;
				sgxq=content+"��";
				break;
			}
		}
		if(find==false){
			for(int i=0;i<cmssdlist.size();i++){
				String content=cmssdlist.get(i);
				if(isSgxqgjc(content)){
					find=true;
					sgxq=content+"��";
					break;
				}
			}
		}
		if(find==false){
			for(int i=0;i<cmssdlist.size();i++){
				String content=cmssdlist.get(i);
				if(isSgsj(content)){
					find=true;
					sgxq=content+"��";
					break;
				}
			}
		}

		return sgxq;
	}
	//ͨ����λ���ж��Ƿ�Ϊ�¹����飬�硰�����򱱡���
	public static boolean isFwc(String content){
		int yx=worddis("��","��",content);
		int zx=worddis("��","��",content);
		if((yx>0&&yx<3)||(zx>0&&zx<3))
			return true;
		return false;
	}
	//ͨ���¹�����ؼ����жϣ��硰��ײ��������ͨ�¹ʡ���
	public static boolean isSgxqgjc(String content){
		int jsy=worddis("��ʻ","��",content);
		int jsz=worddis("��ʻ","ײ",content);
		if(content.contains("��ײ")&&content.contains("��ͨ�¹�"))
			return true;
		else if((jsy>0&&jsy<40)||(jsz>0&&jsz<100))
			return true;
		else if(content.contains("��ʻ")&&content.contains("��ͨ�¹�"))
			return true;
		return false;
	}
	//ͨ���¹�ʱ���ж�
	public static boolean isSgsj(String content){
		int sf=worddis("ʱ","��",content);
		int rs=worddis("��","ʱ",content);
		if((sf>0&&sf<3))
			return true;
		else if((rs>0&&rs<3))
			return true;
		return false;
	}
	@Override
	public String getSgsj(String sgxq) {
		String sgsj="δ�ἰ";
		String[] nr=sgxq.split("��");
		for(int i=0;i<nr.length;i++){
			if(nr[i].contains("��")&&nr[i].contains("��")&&nr[i].contains("��")){
				if(nr[i].contains("��"))
					sgsj=nr[i].substring(nr[i].indexOf("��")+1);
				else 
					sgsj=nr[i];
				break;
			}				
		}
		return sgsj;
	}

	@Override
	public String getSgdd(String sgxq) {
		String sgdd="δ�ἰ";
		String[] nr=sgxq.split("��");

		for(int i=0;i<nr.length;i++){
			int xss=worddis("��ʻ��","ʱ",nr[i]);
			int zs=worddis("��","ʱ",nr[i]);
			int yxs=worddis("��","��ʻ",nr[i]);
			if(xss>0&&xss<30){
				sgdd=nr[i].substring(nr[i].indexOf("��ʻ��")+3,nr[i].lastIndexOf("ʱ"));
				break;
			}else if(zs>0&&zs<30){
				sgdd=nr[i].substring(nr[i].lastIndexOf("��")+1,nr[i].lastIndexOf("ʱ"));
				break;
			}else if(nr[i].contains("��ʻ��")){
				sgdd=nr[i].substring(nr[i].indexOf("��ʻ��")+3);
				break;
			}else if(yxs>0&&yxs<30){
				sgdd=nr[i].substring(nr[i].indexOf("��")+1,nr[i].lastIndexOf("��ʻ"));
				break;
			}
		}
		return sgdd;
	}

	@Override
	public String getSgjg(String sgxq) {

		return sgxq;
	}

	@Override
	public String getJdcsyr(List<String> cmssd, String sgxq, List<String> bglist) {
		String jdcsyr = null;
		boolean name=false;
		for(int i=0;i<cmssd.size();i++){
			String[] cmsslist=cmssd.get(i).split("��");
			for(int j=0;j<cmsslist.length;j++){
				int xsy=worddis("ϵ","����",cmsslist[j]);
				if(xsy>0&&xsy<20){			
					String content=cmsslist[j];  
					if(content.indexOf("ϵ")+1<content.indexOf("����"))
					{
						content=content.substring(content.indexOf("ϵ")+1,content.indexOf("����"));
						for(int k=0;k<bglist.size();k++){
							if(content.contains(bglist.get(k))){
								jdcsyr=bglist.get(k);
								name=true;
								break;
							}					
						}
					}

				}
				if(name==false){
					if(cmsslist[j].contains("����")){
						String content=cmsslist[j].substring(0,cmsslist[j].indexOf("����"));
						for(int k=0;k<bglist.size();k++){
							if(content.contains(bglist.get(k))){
								jdcsyr=bglist.get(k);
								name=true;
								break;
							}					
						}
					}



				}
			}

		}
		if(name==false){
			for(int i=0;i<bglist.size();i++){
				if(sgxq.contains(bglist.get(i))){
					jdcsyr=bglist.get(i);
					name=true;
					break;
				}
			}
		}
		if(jdcsyr==null&&bglist.size()>0){
			jdcsyr=bglist.get(0);
		}
		return jdcsyr;
	}
	@Override
	public String getJdcglr(List<String> cmssd, String sgxq, List<String> bglist) {
		String jdcglr=null;
		boolean name=false;
		for(int i=0;i<bglist.size();i++){
			if(sgxq.contains(bglist.get(i))){
				jdcglr=bglist.get(i);
				name=true;
				break;
			}
		}
		if(name==false){
			String[] sgxqlist=sgxq.split("��");
			for(int i=0;i<sgxqlist.length;i++){

				if(sgxqlist[i].contains("��ʻ")&&(!sgxqlist[i].contains("ԭ��"))){
					jdcglr=sgxqlist[i];
					jdcglr=jdcglr.substring(0,jdcglr.indexOf("��ʻ"));
					break;
				}
			}
		}
		if(jdcglr!=null){
			if(jdcglr.length()>3){
				jdcglr=jdcglr.substring(jdcglr.length()-3,jdcglr.length());
			}
		}
		return jdcglr;
	}

	@Override
	public String getGajgrdyj(List<String> cmssd) {
		String gajgrdyj=null;
		String []content=cmssd.get(0).split("��");
		for(int i=0;i<content.length;i++){
			if(content[i].contains("�¹��϶�")){
				gajgrdyj=content[i];
				break;
			}
		}
		if(gajgrdyj==null){
			for(int i=0;i<content.length;i++){
				if(content[i].contains("�϶�")){
					gajgrdyj=content[i];
					break;
				}
			}
		}
		if(gajgrdyj!=null){
			String[] contentlist=gajgrdyj.split("��");
			int index=0;
			String temp=null;
			for(int i=0;i<contentlist.length;i++){
				if(contentlist[i].contains("�϶�")){
					index=i;
					if(contentlist[i].contains("��")){
						temp=contentlist[i].substring(contentlist[i].indexOf("��")+1);
					}
					break;
				}

			}
			if(temp!=null){
				gajgrdyj=temp+"��";
			}else{
				gajgrdyj="";
			}
			for(int j=index+1;j<contentlist.length;j++){
				if(j<contentlist.length-1){
					gajgrdyj+=contentlist[j]+"��";
				}else{
					gajgrdyj+=contentlist[j]+"��";
				}

			}
		}
		return gajgrdyj;
	}
	public static int worddis(String word1,String word2,String content){
		int dis=-1;
		List<Integer> list1=new ArrayList<Integer>();
		List<Integer> list2=new ArrayList<Integer>();
		String word1content=content;
		String word2content=content;
		int word1index=0;
		int word2index=0;
		while(word1content.indexOf(word1)>-1){
			list1.add(word1content.indexOf(word1)+word1index);
			word1index+=word1content.indexOf(word1);
			word1content=word1content.substring(word1content.indexOf(word1)+1);
		}
		while(word2content.indexOf(word2)>-1){
			list2.add(word2content.indexOf(word2)+word2index);
			word2index+=word2content.indexOf(word2);
			word2content=word2content.substring(word2content.indexOf(word2)+1);

		}
		List<Integer> num=new ArrayList<Integer>();
		for(int i=0;i<list1.size();i++){
			for(int j=0;j<list2.size();j++){
				if((list2.get(j)-list1.get(i))>0){
					num.add(list2.get(j)-list1.get(i));
				}
			}
		}
		if(num.size()>0){
			dis=num.get(0);
			for(int i=1;i<num.size();i++){
				if(num.get(i)<dis)
					dis=num.get(i);
			}
		}

		return dis;
	}
	@Override
	public String getShrjzd(List wssscyrModellist) {
		String shrjzd=null;
		for(int i=0;i<wssscyrModellist.size();i++){
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("ԭ��")&&sscyr.getDsrlx().equals("��Ȼ��")){
				shrjzd=sscyr.getDsrdz();
				break;
			}

		}
		System.out.println(shrjzd);
		return shrjzd;
	}
	@Override
	public String getShrzy(List wssscyrModellist) {
		String shrzy=null;
		for(int i=0;i<wssscyrModellist.size();i++){
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("ԭ��")&&sscyr.getDsrlx().equals("��Ȼ��")){
				shrzy=sscyr.getDsrzw();
				break;
			}

		}
		System.out.println(shrzy);
		return shrzy;
	}



}
