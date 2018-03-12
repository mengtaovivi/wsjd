package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * ����һ�󰸼�������Ϣ����
 * @author wangzh
 *
 */
public class XsysAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();
		if(ajjbqk.size()==1){
			String xsbssld=ajjbqk.get(0);
			wsajjbqkModel.setXsbssld(xsbssld);
		}else{
			int zkdlindex=-1;
			int index=0;
			//����ָ�ض���
			for(int i=0;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				if(ajnr.length()>25)
					ajnr=ajnr.substring(0,25);
				if(ajnr.contains("ָ��")){
					zkdlindex=i;
					break;
				}
			}
			//�������������
			int bssldindex=-1;
			index=zkdlindex+1;
			for(int i=index;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				if(issurplus(ajnr))
					continue;
				if(ajnr.length()>10)
					ajnr=ajnr.substring(0,10);
				if((ajnr.indexOf("����")>0&&ajnr.indexOf("����")<8)||ajnr.contains("��Ժ��Ϊ")
						||ajnr.contains("ͥ����֤"))	{
					bssldindex=i;
					break;
				}
			}	
			if(bssldindex==-1){
				for(int i=ajjbqk.size()-1;i>index-1;i--){
					String ajnr=ajjbqk.get(i);
					if(ajnr.length()>25)
						ajnr=ajnr.substring(0,25);
					if((ajnr.indexOf("����")>0&&ajnr.indexOf("����")<8)||
							(ajnr.indexOf("������ʵ")==0)||(ajnr.indexOf("������ʵ")==0)||
							ajnr.contains("�϶���ʵ")||ajnr.contains("��Ժ��Ϊ"))	{
						bssldindex=i;
						break;
					}
				}	
			}
			int end=-1;
			if(bssldindex==-1)
				end=ajjbqk.size();
			else
				end=bssldindex;
			int bhdlindex=-1;
			//�����绤����
			for(int i=index;i<end;i++){
				String ajnr=ajjbqk.get(i);
				if(isBhdl(ajnr)&&(!isZjd(ajnr))){
					bhdlindex=i;	
					break;
				}			
			}
			//���������������������
			int fdmsssqqdindex=-1;
			for(int i=index;i<end;i++){
				String ajnr=ajjbqk.get(i);
				if(ajnr.length()>20)
					ajnr=ajnr.substring(0,20);
				if(ajnr.contains("��������")){
					fdmsssqqdindex=i;	
					break;
				}			
			}
			String zkdl="";
			String bhdl="";
			String bssld="";
			List<String>bssldlist=new ArrayList<String>();
			String fdmsssqqd="";
			int zkdlend=-1;
			int bhdlend=-1;
			if(zkdlindex>-1&&bhdlindex>-1&&bssldindex>-1&&fdmsssqqdindex>-1&&fdmsssqqdindex>bhdlindex){
				zkdlend=bhdlindex;
				bhdlend=fdmsssqqdindex;
				for(int i=zkdlindex;i<bhdlindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bhdlindex;i<fdmsssqqdindex;i++)
					bhdl+=ajjbqk.get(i);
				for(int i=fdmsssqqdindex;i<bssldindex;i++)
					fdmsssqqd+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++){
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bhdlindex>-1&&bssldindex>-1&&fdmsssqqdindex>-1&&fdmsssqqdindex<bhdlindex){
				zkdlend=fdmsssqqdindex;
				bhdlend=bssldindex;
				for(int i=zkdlindex;i<fdmsssqqdindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=fdmsssqqdindex;i<bhdlindex;i++)
					fdmsssqqd+=ajjbqk.get(i);
				for(int i=bhdlindex;i<bssldindex;i++)
					bhdl+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}
			else if(zkdlindex>-1&&bhdlindex>-1&&bssldindex>-1&&fdmsssqqdindex==-1){
				zkdlend=bhdlindex;
				bhdlend=bssldindex;
				for(int i=zkdlindex;i<bhdlindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bhdlindex;i<bssldindex;i++)
					bhdl+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bssldindex>-1&&fdmsssqqdindex>-1&&bhdlindex==-1){
				zkdlend=fdmsssqqdindex;
				for(int i=zkdlindex;i<fdmsssqqdindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=fdmsssqqdindex;i<bssldindex;i++)
					fdmsssqqd+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bssldindex>-1&&fdmsssqqdindex==-1&&bhdlindex==-1){
				zkdlend=bssldindex;
				for(int i=zkdlindex;i<bssldindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex==-1&&bssldindex>-1&&fdmsssqqdindex==-1&&bhdlindex==-1){
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bssldindex==-1&&fdmsssqqdindex==-1&&bhdlindex>-1){
				zkdlend=bhdlindex;
				bhdlend=ajjbqk.size();
				for(int i=zkdlindex;i<bhdlindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bhdlindex;i<ajjbqk.size();i++)
					bhdl+=ajjbqk.get(i);
			}
			//����ָ����ʵ��ָ��֤�ݡ�ָ�����
			if(zkdl!=""){
				wsajjbqkModel.setZkdl(zkdl);
				String zkss="";
				String zkzj="";
				String zkyj="";
				int zkzjindex=-1;
				int zkyjindex=-1;
				//��һ�β���ָ��֤��
				for(int i=zkdlindex+1;i<zkdlend;i++){
					String ajnr=ajjbqk.get(i);
					if(isZjd(ajnr)||likeZjd(ajnr)){
						zkzjindex=i;
						break;
					}
				}
				//��һ�β���ָ�����
				for(int i=zkdlend-1;i>zkdlindex-1;i--){
					String ajnr=ajjbqk.get(i);
					int zjxszr=worddis("׷��","��������",ajnr);
					if(ajnr.contains("��")||ajnr.contains("�����д�")||ajnr.contains("���뱾Ժ")
							||ajnr.contains("���߻�����Ϊ")||ajnr.contains("����")||ajnr.contains("�ݴ���Ϊ")
							||(zjxszr>-1&&zjxszr<10)||ajnr.contains("ָ����Ϊ")){
						zkyjindex=i;
						break;
					}
				}
				int zkzjindex_2=-1;
				//�ڶ��β���ָ��֤��
				if(zkzjindex==-1){
					String[] zksslist=ajjbqk.get(zkdlindex).split("��");
					for(int i=0;i<zksslist.length;i++){
						String ajnr=zksslist[i];
						if(isZjd(ajnr)||likeZjd(ajnr)){
							zkzjindex_2=i;
							break;
						}
					}
					if(zkzjindex_2>-1&&zkyjindex>zkdlindex){
						for(int i=0;i<zkzjindex_2;i++)
							zkss+=zksslist[i]+"��";
						for(int i=zkzjindex_2;i<zksslist.length;i++)
							zkzj+=zksslist[i]+"��";
						for(int i=zkyjindex;i<zkdlend;i++)
							zkyj+=ajjbqk.get(i);
					}else if(zkzjindex_2>-1&&zkyjindex==zkdlindex){
						String zkzjyj="";
						for(int i=0;i<zkzjindex_2;i++)
							zkss+=zksslist[i]+"��";
						for(int i=zkzjindex_2;i<zksslist.length;i++)
							zkzjyj+=zksslist[i]+"��";
						int rw=zkzjyj.indexOf("��Ϊ");
						if(rw==-1)
							rw=zkzjyj.indexOf("�϶�");
						if(rw==-1)
							rw=zkzjyj.indexOf("����");
						if(rw>-1){
							String temp=zkzjyj.substring(0,rw);
							int jh=temp.lastIndexOf("��");
							int dh=temp.lastIndexOf("��");
							if(jh>dh&&jh>0)
								rw=jh+1;
							else if(jh<dh&&dh>0)
								rw=dh+1;
							zkzj=zkzjyj.substring(0,rw);
							zkyj=zkzjyj.substring(rw,zkzjyj.length());
						}else{
							zkyj=zkzjyj;
						}
					}
					else if(zkzjindex_2==-1&&zkyjindex>zkdlindex){
						for(int i=zkdlindex;i<zkyjindex;i++)
							zkss+=ajjbqk.get(i);
						for(int i=zkyjindex;i<zkdlend;i++)
							zkyj+=ajjbqk.get(i);
					}else if(zkzjindex_2==-1&&zkyjindex==zkdlindex){
						String zkssyj=ajjbqk.get(zkdlindex);
						int rw=ajjbqk.get(zkdlindex).lastIndexOf("��Ϊ");
						if(rw==-1)
							rw=zkssyj.indexOf("�϶�");
						if(rw==-1)
							rw=zkssyj.indexOf("����");
						if(rw>-1){
							String temp=zkssyj.substring(0,rw);
							int jh=temp.lastIndexOf("��");
							int dh=temp.lastIndexOf("��");
							if(jh>dh&&jh>0)
								rw=jh+1;
							else if(jh<dh&&dh>0)
								rw=dh+1;
							zkss=zkssyj.substring(0,rw);
							zkyj=zkssyj.substring(rw,zkssyj.length());	
						}else{
							zkss=ajjbqk.get(zkdlindex);
						}
					}
					else{
						for(int i=zkdlindex;i<zkdlend;i++)
							zkss+=ajjbqk.get(i);
					}
				}else{
					if(zkyjindex>-1){
						if(zkyjindex>zkzjindex){
							for(int i=0;i<zkzjindex;i++)
								zkss+=ajjbqk.get(i);
							for(int i=zkzjindex;i<zkyjindex;i++)
								zkzj+=ajjbqk.get(i);
							for(int i=zkyjindex;i<zkdlend;i++)
								zkyj+=ajjbqk.get(i);
						}else if(zkyjindex==zkzjindex){
							for(int i=zkdlindex;i<zkzjindex;i++)
								zkss+=ajjbqk.get(i);
							String zkzjyj=ajjbqk.get(zkyjindex);
							int rw=zkzjyj.indexOf("��Ϊ");
							if(rw==-1)
								rw=zkzjyj.indexOf("�϶�");
							if(rw==-1)
								rw=zkzjyj.indexOf("����");
							if(rw>-1){
								String temp=zkzjyj.substring(0,rw);
								int jh=temp.lastIndexOf("��");
								int dh=temp.lastIndexOf("��");
								if(jh>dh&&jh>0)
									rw=jh+1;
								else if(jh<dh&&dh>0)
									rw=dh+1;
								zkzj=zkzjyj.substring(0,rw);
								zkyj=zkzjyj.substring(rw,zkzjyj.length());
							}else{
								zkyj=ajjbqk.get(zkyjindex);
							}

						}else if(zkyjindex<zkzjindex){
							for(int i=0;i<zkyjindex;i++)
								zkss+=ajjbqk.get(i);
							for(int i=zkyjindex;i<zkzjindex;i++)
								zkyj+=ajjbqk.get(i);
							for(int i=zkzjindex;i<zkdlend;i++)
								zkzj+=ajjbqk.get(i);
						}
					}else{
						for(int i=0;i<zkzjindex;i++)
							zkss+=ajjbqk.get(i);
						for(int i=zkzjindex;i<zkdlend;i++)
							zkzj+=ajjbqk.get(i);
					}
				}
				if(zkss!="")
					wsajjbqkModel.setZkss(zkss);
				if(zkzj!="")
					wsajjbqkModel.setZkzj(zkzj);
				if(zkyj!="")
					wsajjbqkModel.setZkyj(zkyj);
			}
			//���������˱�ƺͱ绤�˱绤
			if(bhdl!=""){
				wsajjbqkModel.setBhdl(bhdl);
				List<String>bgrbclist=new ArrayList<String>();
				List<String>bhrbhlist=new ArrayList<String>();
				for(int i=bhdlindex;i<bhdlend;i++){
					String ajnr=ajjbqk.get(i);
					if(ajnr.contains("�绤")){
						bhrbhlist.add(ajnr);
					}else{
						bgrbclist.add(ajnr);
					}
				}
				if(bgrbclist!=null)
					wsajjbqkModel.setBgrbc(bgrbclist);
				if(bhrbhlist!=null)
					wsajjbqkModel.setBhrbh(bhrbhlist);
			}
			if(fdmsssqqd!="")
				wsajjbqkModel.setFdmsssqqd(fdmsssqqd);
			if(bssld!=""){
				wsajjbqkModel.setXsbssld(bssld);
				wsajjbqkModel.setBssld(bssldlist);
			}
		}
		return wsajjbqkModel;
	}
	public boolean likeZjd(String content){
		boolean b=false;
		if(content.length()>=100)
			content=content.substring(0,100);
		if(content.contains("֤��")||content.contains("֤��")||content.contains("��֤")
				||content.contains("֤��")||content.contains("֤ʵ")
				||content.contains("��֤")||content.contains("��֤")){
			b=true;
		}
		return b;
	}
	public boolean isZjd(String content){
		boolean b=false;
		if(content.contains("��ͥ����֤")||content.contains("�������")||content.contains("���Բ���")
				||content.contains("����֤��")||content.contains("��ͥ��֤")||content.contains("���Բ���")
				||content.contains("�ھ�Ϊƾ")||content.contains("������֤"))
			b=true;
		if(content.length()>=50)
			content=content.substring(0,50);
		if(content.contains("֤��:")||content.contains("֤�ݣ�")||content.contains("֤�����£�")
				||content.contains("֤������:")||content.contains("֤���У�")
				){
			b=true;
		}
		return b;
	}
	public boolean isCmssd(String content){
		boolean b=false;
		int qrss=worddis("ȷ��","��ʵ",content);
		int rdss=worddis("�϶�","��ʵ",content);
		int cmss=worddis("����","��ʵ",content);
		if((qrss>-1&&qrss<10)||(rdss>-1&&rdss<10)||(cmss>-1&&cmss<10)
				||content.contains("������ʵ")||content.contains("������ʵ")||content.contains("��ʵ����"))
			b=true;
		return b;
	}
	public static boolean isBhdl(String content){
		boolean b=false;
		if(issurplus(content))
			return false;
		int bgryy=worddis("����","����",content);		
		int bgrgr=worddis("����","����",content);
		int bgrrk=worddis("����","�Ͽ�",content);		
		int bgrfr=worddis("����","����",content);
		int bgryj=worddis("����","���",content);
		if(content.length()>70)
			content=content.substring(0,70);
		boolean isbh=true;
		if(content.contains("���߻���")){
			isbh=false;
			if(content.indexOf("���߻���")>-1&&content.indexOf("����")>-1){
				if(content.indexOf("���߻���")>content.indexOf("����"))
					isbh=true;
			}
		}
		if(((bgryy>-1&&bgryy<60)||(bgryj>-1&&bgryj<50)||(bgrrk>-1&&bgrrk<60)||(bgrfr>-1&&bgrfr<60)||
				(bgrgr>-1&&bgrgr<60)
				||content.contains("���")||content.contains("�绤")||content.contains("���"))
				&&(isbh==true)
				)
			b=true;

		return b;
	}
	public static boolean issurplus(String content){
		boolean b=false;
		String rexCh="[һ�����������߰˾�ʮ]";
		String rexNu="\\d{1}";
		Pattern patternCh=Pattern.compile(rexCh);
		Pattern patternNu=Pattern.compile(rexNu);

		if(content.length()>=3)
			content=content.substring(0,3);
		Matcher matcherCh=patternCh.matcher(content);
		Matcher matcherNu=patternNu.matcher(content);
		if(matcherCh.find()||matcherNu.find())
			b=true;
		return b;
	}

}
