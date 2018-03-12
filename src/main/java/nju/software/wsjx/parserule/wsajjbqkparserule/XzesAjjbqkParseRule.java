package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;

/**
 * �������󰸼�������Ϣ����
 * @author wangzh
 *
 */
public class XzesAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{

	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		//����ǰ�����ͱ������
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

		if(ajjbqk==null)
			return wsajjbqkModel;
		if(ajjbqk.size()==1){
			wsajjbqkModel.setBsdl(ajjbqk.get(0));
			List<String> bssldlist=new ArrayList<String>();
			bssldlist.add(ajjbqk.get(0));
			wsajjbqkModel.setBssld(bssldlist);
		}else{
			int pre=-1;
			int end=-1;
			//			int[] exindex=new int[ajjbqk.size()];
			for(int j=0;j<ajjbqk.size();j++){
				String ajnr=ajjbqk.get(j);
				if(ajnr.length()>50)
					ajnr=ajnr.substring(0,50);
				int ssrsc=worddis("������","�߳�",ajnr);
				int ssrss=worddis("������","����",ajnr);
				int bfsc=worddis("����","�߳�",ajnr);
				int bfss=worddis("����","����",ajnr);
				boolean isQsdl=isQsdl(ajnr);
				if(((ssrsc>0&&ssrsc<30)||(bfsc>0&&bfsc<15)||(ssrss>0&&ssrss<30)
						||(bfss>0&&bfss<30))&&(isQsdl==false)){
					end=j;
					break; 
				}
			}
			if(end==-1){
				for(int j=0;j<ajjbqk.size();j++){
					String ajnr=ajjbqk.get(j);
					if(ajnr.length()>50)
						ajnr=ajnr.substring(0,50);
					boolean likeQsdl=false;
					if(ajnr.contains("���߳�")){
						ajnr=ajnr.substring(0,ajnr.indexOf("���߳�"));
						likeQsdl=likeQsdl(ajnr);
						if(!likeQsdl){
							end=j;
							break;
						}

					}

				}
			}

			pre=0;

			if(pre<5&&pre>-1){  
				if(end==-1) end=ajjbqk.size()-1;
				//����ǰ����� 
				String qsdl="";
				for (int i = 0; i <end; i++) {
					if(i!=end-1)			
						qsdl += ajjbqk.get(i) + " ";
					else
						qsdl += ajjbqk.get(i);
				}
				wsajjbqkModel.setQsdl(qsdl);
				//�����������
				String bsdl = "";
				for (int i = end; i < ajjbqk.size() ; i++) {
					if(i!=ajjbqk.size()-1)
						bsdl += ajjbqk.get(i) + " ";
					else
						bsdl += ajjbqk.get(i) ;
				}
				wsajjbqkModel.setBsdl(bsdl);
				//������������������
				List<Integer> bsindex=new ArrayList<Integer>();
				int [] bsin=new int[ajjbqk.size()];
				String bsdsryjd="";
				for(int i=end;i<ajjbqk.size();i++){
					String ajnr=ajjbqk.get(i);
					String dsrajnr=ajnr;
					if(ajnr.length()>10)
						dsrajnr=ajnr.substring(0,10);
					if(dsrajnr.contains("������")){
						bsdsryjd+=ajnr+" ";
						bsindex.add(i);	
						bsin[i]=1;
					}
				}  
				if(bsdsryjd!="")
					wsajjbqkModel.setBsdsryjd(bsdsryjd);
				//�������������˱�ƶ�
				List<String> bssrbcdlist=new ArrayList<String>();
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						String ajnr=ajjbqk.get(i);
						String bcajnr=ajnr;
						if(ajnr.length()>40)
							bcajnr=ajnr.substring(0,40);
						int bssr=worddis("��������","��",bcajnr);
						if(bcajnr.contains("���")||bcajnr.contains("���")||(bssr>-1&&bssr<20)){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);	
							bsin[i]=1;
						}
					}
				}  
				if(bssrbcdlist.size()>0)
					wsajjbqkModel.setBssrbcd(bssrbcdlist);
				//���������������߳ƶ�
				String ssrscd="";
				int ssindex=ajjbqk.size();
				if(end+2<=ajjbqk.size())
					ssindex=end+2;
				for(int i=end;i<ssindex;i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						String ajnr=ajjbqk.get(i);
						String ssajnr=ajnr;
						if(ajnr.length()>40)
							ssajnr=ajnr.substring(0,40);
						int ssrc=worddis("������","��",ssajnr);
						int ssrsc=worddis("������","�߳�",ajnr);
						int ssrss=worddis("������","����",ajnr);
						int bfsc=worddis("����","�߳�",ajnr);
						int bfss=worddis("����","����",ajnr);
						if(ssajnr.contains("�߳�")||(ssrc>-1&&ssrc<20)||(ssrsc>0&&ssrsc<30)||(bfsc>0&&bfsc<15)||(ssrss>0&&ssrss<30)
								||(bfss>0&&bfss<30)){
							ssrscd+=ajnr+" ";
							bsindex.add(i);		
							bsin[i]=1;
						}
					}

				}  
				if(ssrscd!="")
					wsajjbqkModel.setSsrscd(ssrscd);


				//�������������
				int bssltotal=0;
				int [] bsslindex=new int[ajjbqk.size()];
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						boolean isBssld=isBssld(ajjbqk.get(i));
						if(isBssld){
							bsindex.add(i);
							bsslindex[i]=1;
							bssltotal++;
							bsin[i]=1;
						}
					}
				}
				//��������֤�ݶ�
				int[] zjdindex=new int[ajjbqk.size()];
				int zjdtotal=0;
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						boolean iszjd=isZjd(ajjbqk.get(i));
						if(iszjd){
							zjdindex[i]=1;
							zjdtotal++;
							bsin[i]=1;
							bsindex.add(i);
						}

					}
				}
				//���뱾�������
				for(int i=end;i<ajjbqk.size();i++){
					if(bsslindex[i]==1){
						for(int j=end;j<i;j++){
							if(bsslindex[j]==1){
								for(int k=j;k<i;k++){
									boolean b=true;
									for(int m=0;m<bsindex.size();m++){
										if(k==bsindex.get(m)){
											b=false;
											break;
										}
									}
									if(b){
										bsslindex[k]=1;
										bssltotal++;
										bsindex.add(k);
										bsin[k]=1;
									}
								}
							}
						}
					}
				}
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}
					}
					if(b){
						zjdindex[i]=1;
						zjdtotal++;
						bsin[i]=1;
						bsindex.add(i);
					}
				}
				if(bssltotal>0){
					if(bssltotal==1){
						List<String> bssldlist=new ArrayList<String>();
						for(int i=end;i<bsslindex.length;i++){
							if(bsslindex[i]==1){
								bssldlist.add(ajjbqk.get(i));
							}
						}
						wsajjbqkModel.setBssld(bssldlist);
					}else{
						List<String> bssldlist=new ArrayList<String>();
						String bssld="";
						int prez=0;
						for(int i=end;i<bsslindex.length;i++){
							if(bsslindex[i]==1){
								bssld=ajjbqk.get(i);
								prez=i;
								break;
							}
						}
						for(int i=prez+1;i<ajjbqk.size();i++){
							if((bsslindex[i]==1)&&((i-prez)==1)){								
								bssld+=ajjbqk.get(i)+" ";
								prez=i;
							}else if(bsslindex[i]==1){
								bssldlist.add(bssld);
								bssld=ajjbqk.get(i)+" ";
								prez=i;
							}
						}
						bssldlist.add(bssld);
						wsajjbqkModel.setBssld(bssldlist);
					}

				}
				if(zjdtotal>0){
					if(zjdtotal==1){
						List<String> zjdlist=new ArrayList<String>();
						for(int i=end;i<zjdindex.length;i++){
							if(zjdindex[i]==1){
								zjdlist.add(ajjbqk.get(i));
							}
						}
						wsajjbqkModel.setZjd(zjdlist);
					}else{
						List<String> zjdlist=new ArrayList<String>();
						String zjd="";
						int prez=0;
						for(int i=end;i<zjdindex.length;i++){
							if(zjdindex[i]==1){
								zjd=ajjbqk.get(i);
								prez=i;
								break;
							}
						}
						for(int i=prez+1;i<ajjbqk.size();i++){
							if((zjdindex[i]==1)&&((i-prez)==1)){								
								zjd+=ajjbqk.get(i)+" ";
								prez=i;
							}else if(zjdindex[i]==1){
								zjdlist.add(zjd);
								zjd=ajjbqk.get(i)+" ";
								prez=i;
							}
						}
						zjdlist.add(zjd);
						wsajjbqkModel.setBszjd(zjdlist);
					}

				}
				//����ǰ���о���
				if(end>0){
					String []qsdllast=ajjbqk.get(end-1).split("��");
					int qspjindex=-1;
					for(int i=qsdllast.length-1;i>=0;i--){
						if(qsdllast[i].contains("�ݴ��о�")||qsdllast[i].contains("�ݴ˲ö�"))
						{	
							qspjindex=i;
							break;
						}
						if(qsdllast[i].contains("����")||qsdllast[i].contains("����")||qsdllast[i].contains("����")||qsdllast[i].contains("����")){
							int zs=qsdllast[i].indexOf("����");
							int yz=qsdllast[i].indexOf("����");
							int yj=qsdllast[i].indexOf("����");
							int gj=qsdllast[i].indexOf("����");
							int min=0;
							if(zs!=-1) min=zs;
							if(yz!=-1) min=min<yz?min:yz;
							if(yj!=-1) min=min<yj?min:yj;
							if(gj!=-1) min=min<gj?min:gj;
							if(qsdllast[i].indexOf("�о�")>min||qsdllast[i].indexOf("�ö�")>min
									){	
								qspjindex=i;
								break;
							}
						}
					}
					if(qspjindex==-1){
						for(int i=qsdllast.length-1;i>=0;i--){
							if((qsdllast[i].contains("�о�")||qsdllast[i].contains("�ö�"))){
								qspjindex=i;
								break;
							}
						}


					}
					int qspjdpre=end;
					if(qspjindex>-1){
						String qspjd="";
						for(int j=qspjindex;j<qsdllast.length;j++){
							qspjd+=qsdllast[j]+"��";
						}
						qspjdpre=end-1;
						wsajjbqkModel.setQspjd(qspjd);
					}
					//����ǰ�󱻸��ƶΡ�ԭ���߳ƶΡ�ǰ�����߳ƶ�
					List<Integer> exindex=new ArrayList<Integer>();
					String qsbgbcd="";
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						if(ajnr.indexOf("���")>-1&&ajnr.indexOf("���")<30){
							qsbgbcd+=ajnr+" ";
							exindex.add(i);					
						}
					}  
					if(qsbgbcd!="")
						wsajjbqkModel.setQsbgbcd(qsbgbcd);
					String qsygscd="";
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						List<String> nrtoken = FcUtil.getWholeToken(ajnr);
						int index=0;
						if(nrtoken.size()<20){
							index=nrtoken.size();
						}else{
							index=20;
						}
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("����")||content.equals("�߳�")||content.equals("����")||
									content.equals("����")||
									content.equals("��������")){
								qsygscd+=ajnr+" ";
								exindex.add(i);
								break;
							}
						}
					}  
					if(qsygscd!="")
						wsajjbqkModel.setQsygscd(qsygscd);
					String qsfsscd="";
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						List<String> nrtoken = FcUtil.getWholeToken(ajnr);
						int index=0;
						if(nrtoken.size()<30){
							index=nrtoken.size();
						}else{
							index=30;
						}
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("����")){
								qsfsscd+=ajnr+" ";
								exindex.add(i);
								break;
							}
						}
					} 
					if(qsfsscd!="") 					
						wsajjbqkModel.setQsfsscd(qsfsscd);
					//����ǰ��֤�ݶ�
					List<String> qszjdlist=new ArrayList<String>();
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						boolean isZjd=isZjd(ajnr);
						if(isZjd){
							qszjdlist.add(ajnr);
							exindex.add(i);
							break;
						}

					} 
					if(qszjdlist.size()>0) 					
						wsajjbqkModel.setQszjd(qszjdlist);
					//����ǰ�������
					String qssld="";
					List<String> qssldlist=new ArrayList<String>();
					for(int i=0;i<qspjdpre;i++){
						boolean b=true;
						for(int j=0;j<exindex.size();j++){
							if(i==exindex.get(j)){
								b=false;
								break;
							}

						}
						if(b){
							qssld+=ajjbqk.get(i)+" ";
						}else{
							if(!qssld.equals("")){
								qssldlist.add(qssld);
								qssld="";
							}
						}
					}
					for(int j=0;j<qspjindex;j++){
						qssld+=qsdllast[j]+"��";
					}
					if(!qssld.equals("")){
						qssldlist.add(qssld);
					}
					wsajjbqkModel.setQssld(qssldlist);
				}


			}else{
				String bsdl = "";
				for (int i = 0; i < ajjbqk.size() - 1; i++) {
					if(i!=ajjbqk.size()-1)
						bsdl += ajjbqk.get(i) + " ";
					else
						bsdl+=ajjbqk.get(i);
				}
				wsajjbqkModel.setBsdl(bsdl);

			}
		}
		return wsajjbqkModel;
	}

	public static boolean likeQsdl(String ajnr){
		if(ajnr.length()>20)
			ajnr=ajnr.substring(0,20);
		if(ajnr.contains("ԭ��")||ajnr.contains("һ��")||ajnr.contains("����")||
				ajnr.contains("ǰ��")||ajnr.contains("ԭ��"))
			return true;
		return false;
	}
	public static boolean isQsdl(String ajnr){
		if(ajnr.length()>20)
			ajnr=ajnr.substring(0,20);
		if(ajnr.contains("��ԭ��")||ajnr.contains("��һ��")||
				ajnr.contains("��ǰ��")||ajnr.contains("��ԭ��"))
			return true;
		return false;
	}
	public boolean likeZjd(String content){
		boolean b=false;
		if(content.length()>=50)
			content=content.substring(content.length()-50,content.length());
		if(content.contains("֤��")||content.contains("֤��")||content.contains("��֤")
				||content.contains("֤��")||content.contains("֤ʵ")||content.contains("֤��")
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
				||content.contains("֤������:")||content.contains("��֤��")||content.contains("����֤��")
				){
			b=true;
		}
		return b;
	}
	public boolean isBssld(String result){
		if(result.length()>30)
			result=result.substring(0,30);
		int byrd=worddis("��Ժ","�϶�",result);
		int byqr=worddis("��Ժ","ȷ��",result);
		if(result.contains("����")||result.contains("����")||result.contains("���")
				||(byrd>0&&byrd<10)||(byrd>0&&byrd<10))
			return true;
		return false;
	}


}
