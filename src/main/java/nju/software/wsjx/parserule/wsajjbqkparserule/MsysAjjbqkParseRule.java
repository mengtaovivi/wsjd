package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.impl.jtsg.CltbqkServiceImpl;
import nju.software.wsjx.service.impl.jtsg.CmssdpreServiceImpl;
import nju.software.wsjx.service.jtsg.CltbqkService;
import nju.software.wsjx.service.jtsg.CmssdpreService;
import nju.software.wsjx.util.FcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * ����һ�󰸼�������Ϣ����
 * @author wangzh
 *
 */
public class MsysAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{

	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		if(ajjbqk==null||ajjbqk.size()==0)
			return wsajjbqkModel;
	//	List<String> wssscyrModellist = wsAnalyse.getSscyr();
		
		List<String> cmsslist=new ArrayList<String>();
		List<String> bglist=new ArrayList<String>();
		for(int i=0;i<wssscyrModellist.size();i++){
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("����")){
				bglist.add(sscyr.getSscyr());
			}
		}
		if(ajjbqk.size()==1){
			cmsslist.add(ajjbqk.get(0));
			wsajjbqkModel.setCmssd(cmsslist);
		}else{

			List<Integer> exindex=new ArrayList<Integer>();
			int[] cmssin=new int[ajjbqk.size()];
			int cmsstotal=0;
			//���������������
			String dsryjd="";
			for(int i=0;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				if(ajnr.indexOf("������")==0){
					dsryjd=ajnr;
					exindex.add(i);
					break;
				}
			}
			if (dsryjd!="")
				wsajjbqkModel.setDsryjd(dsryjd);
			//���������߳ƶ�
			String fsscd="";
			for(int i=0;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				String ajnrfirst="";
				if(ajnr.length()<30)
					ajnrfirst=ajnr;
				else
					ajnrfirst=ajnr.substring(0,30);
				ajnrfirst=WsAnalyse.deBracket(ajnrfirst);
				int bgsc=worddis("����","�߳�",ajnrfirst);
				if(bgsc>-1&&bgsc<25){
					fsscd=ajnr;
					exindex.add(i);
					break;
				}
			}
			if (fsscd!="")
				wsajjbqkModel.setFsscd(fsscd);
			//�������߱�ƶ�
			String fsbcd="";
			for(int i=0;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				String ajnrfirst="";
				if(ajnr.length()<30)
					ajnrfirst=ajnr;
				else
					ajnrfirst=ajnr.substring(0,30);
				ajnrfirst=WsAnalyse.deBracket(ajnrfirst);
				int ygbc=worddis("ԭ��","���",ajnrfirst);
				if(ygbc>-1&&ygbc<25){
					fsbcd=ajnr;
					exindex.add(i);
					break;
				}
			}
			if (fsbcd!="")
				wsajjbqkModel.setFsbcd(fsbcd);
			//���������ƶ�
			String bgbcd="";
			int[] bgbcdindex=new int[ajjbqk.size()];
			for(int i=0;i<ajjbqk.size();i++){
				boolean b=true;
				for(int j=0;j<exindex.size();j++){
					if(i==exindex.get(j)){
						b=false;
						break;
					}
				}
				if(b){
					String ajnr=ajjbqk.get(i);
					String ajnrfirst="";
					if(ajnr.length()<50)
						ajnrfirst=ajnr;
					else
						ajnrfirst=ajnr.substring(0,50);
					List<String> nrtoken = FcUtil.getWholeToken(ajnr);
					int index=0;
					if(nrtoken.size()<30){
						index=nrtoken.size();
					}else{
						index=30;
					}
					boolean likezjd=likeZjd(ajnr);
					boolean iscmssd=isCmssd(ajnr);
					boolean iszjd=isZjd(ajnr);
					int bgdb=worddis("����","���",ajnr);
					int bgbc=worddis("����","���",ajnr);
					for(int j=0;j<index;j++){
						String content=nrtoken.get(j);
						if((content.equals("���")&&(likezjd==false)&&(iscmssd==false))||
								((bgbc>-1&&bgbc<10)&&(iszjd==false)&&(iscmssd==false))){
							bgbcd+=ajnr+" ";
							exindex.add(i);
							bgbcdindex[i]=1;
							break;
						}
						if((ajnrfirst.contains("���")&&(likezjd==false)&&(iscmssd==false))||
								((bgdb>-1&&bgdb<25)&&(iszjd==false)&&(iscmssd==false))){
							bgbcd+=ajnr+" ";
							exindex.add(i);
							bgbcdindex[i]=1;
							break;
						}
					}
				}
			}  

			//����ԭ���߳ƶ�
			String ygscd="";
			int[] ygscdindex=new int[ajjbqk.size()];
			int ygscpre=-5;
			for(int i=0;i<ajjbqk.size();i++){
				boolean b=true;
				for(int j=0;j<exindex.size();j++){
					if(i==exindex.get(j)){
						b=false;
						break;
					}
				}
				if(b){
					String ajnr=ajjbqk.get(i);
					List<String> nrtoken = FcUtil.getWholeToken(ajnr);
					int index=0;
					if(nrtoken.size()<30){
						index=nrtoken.size();
					}else{
						index=30;
					}
					boolean iscmss=isCmssd(ajnr);
					boolean iszjd=isZjd(ajnr);
					boolean likezjd=likeZjd(ajnr);
					int yg=ajnr.indexOf("ԭ��");
					int ch=ajnr.indexOf("��");
					int qqpl=worddis("����","����",ajnr);
					boolean isygsc=false;
					if(yg>-1&&yg<10&&ch>-1&&ch<15&&ch-yg>0)
						isygsc=true;
					for(int j=0;j<index;j++){
						String content=nrtoken.get(j);
						if((content.equals("�߳�")||content.equals("����")||
								isygsc||(qqpl>-1&&qqpl<10))
								&&(iscmss==false)&&(iszjd==false)){
							if(ygscpre>-5){
								if((i-ygscpre)==1){
									ygscd+=ajnr+" ";
									exindex.add(i);
									ygscpre=i;
									ygscdindex[i]=1;
									break;
								}
							}else{
								ygscd+=ajnr+" ";
								exindex.add(i);
								ygscpre=i;
								ygscdindex[i]=1;
								break;
							}

						}
						if((content.equals("��������")||content.equals("����")||(content.equals("����")))
								&&(likezjd==false)&&(iscmss==false)&&(!ajnr.contains("����"))){
							if(ygscpre>-5){
								if((i-ygscpre)==1){
									ygscd+=ajnr+" ";
									exindex.add(i);
									ygscpre=i;
									ygscdindex[i]=1;
									break;
								}
							}else{
								ygscd+=ajnr+" ";
								exindex.add(i);
								ygscpre=i;
								ygscdindex[i]=1;
								break;
							}
						}
					}
				}
			}  

			//����������ʵ��
			for(int i=0;i<ajjbqk.size();i++){
				boolean b=true;
				for(int j=0;j<exindex.size();j++){
					if(i==exindex.get(j)){
						b=false;
						break;
					}
				}
				if(b){
					String ajnr=ajjbqk.get(i);
					List<String> nrtoken = FcUtil.getWholeToken(ajnr);
					int index=0;
					if(nrtoken.size()<30){
						index=nrtoken.size();
					}else{
						index=30;
					}
					boolean iscmss=isCmssd(ajnr);
					boolean likezjd=likeZjd(ajnr);
					boolean iszjd=isZjd(ajnr);
					if(((ajnr.indexOf("���")>-1&&ajnr.indexOf("���")<30)||
							(ajnr.indexOf("������")>-1&&ajnr.indexOf("������")<30)
							||iscmss)&&(iszjd==false)){
						cmssin[i]=1;
						cmsstotal++;
						exindex.add(i);
					}else{
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if((content.equals("�϶�")||content.equals("��ʵ")||
									content.equals("ȷ��")||content.equals("����"))
									&&(likezjd==false)&&(iszjd==false)){
								cmssin[i]=1;
								cmsstotal++;
								exindex.add(i);
								break;
							}
						}	
					}

				}
			} 	
			//����֤�ݶ�
			int[] zjdindex=new int[ajjbqk.size()];
			int zjdtotal=0;
			for(int i=0;i<ajjbqk.size();i++){
				boolean b=true;
				for(int j=0;j<exindex.size();j++){
					if(i==exindex.get(j)){
						b=false;
						break;
					}
				}
				if(b){
					String ajnr=ajjbqk.get(i);
					if((likeZjd(ajnr)||isZjd(ajnr))&&(!issurplus(ajnr))){
						zjdindex[i]=1;
						zjdtotal++;
						exindex.add(i);
					}								
				}
			} 
			int [] exin=new int[ajjbqk.size()];
			for(int i=0;i<exindex.size();i++){
				exin[exindex.get(i)]=1;
			}
			//����ԭ���߳ƶ�
			boolean y=true;
			int ygscdpre=-1;
			for(int i=0;i<ajjbqk.size();i++){
				if(ygscdindex[i]==1){
					ygscdpre=i;
					break;
				}
			}
			if(ygscdpre>-1){
				while(y){
					y=false;
					for(int i=ygscdpre;i<ajjbqk.size()-1;i++){
						if(ygscdindex[i]==1){
							if(exin[i+1]==0){
								ygscdpre=i+1;
								ygscd+=ajjbqk.get(ygscdpre)+" ";
								ygscdindex[ygscdpre]=1;
								exin[ygscdpre]=1;
								exindex.add(ygscdpre);
								y=true;
							}
						}
					}
				}
			}
			if(ygscd!="")
				wsajjbqkModel.setYgscd(ygscd);
			//���뱻���ƶ�
			boolean bg=true;
			int bgbcdpre=-1;
			for(int i=0;i<ajjbqk.size();i++){
				if(bgbcdindex[i]==1){
					bgbcdpre=i;
					break;
				}
			}
			if(bgbcdpre>-1){
				while(bg){
					bg=false;
					for(int i=bgbcdpre;i<ajjbqk.size()-1;i++){
						if(bgbcdindex[i]==1){
							if(exin[i+1]==0){
								bgbcdpre=i+1;
								bgbcd+=ajjbqk.get(bgbcdpre)+" ";
								bgbcdindex[bgbcdpre]=1;
								exin[bgbcdpre]=1;
								exindex.add(bgbcdpre);
								bg=true;
							}
						}
					}
				}
			}
			if(bgbcd!="")
				wsajjbqkModel.setBgbcd(bgbcd);
//			for(int i=0;i<ajjbqk.size();i++){
//				System.out.println(i+" "+zjdindex[i]+"  "+exin[i]);
//			}
			//����֤�ݶ�
			for(int i=0;i<ajjbqk.size();i++){
				if(zjdindex[i]==1){
					for(int j=0;j<i;j++){
						if(zjdindex[j]==1){
							for(int k=j;k<i;k++){
								boolean b=true;
								for(int m=0;m<exindex.size();m++){
									if(k==exindex.get(m)){
										b=false;
										break;
									}
								}
								if(b){
									zjdindex[k]=1;
									zjdtotal++;
									exindex.add(k);
								}
							}
						}
					}
				}
			}
			//			for(int i=0;i<zjdindex.length;i++){
			//				if(zjdindex[i]==1)
			//					System.out.println(i);
			//			}
			//��ʣ����仮�ֵ�������ʵ��
			for(int i=0;i<ajjbqk.size();i++){
				boolean b=true;
				for(int j=0;j<exindex.size();j++){
					if(i==exindex.get(j)){
						b=false;
						break;
					}
				}
				if(b){
					cmssin[i]=1;
					cmsstotal++;
				}
			}
			//��֤�ݶ��������model��
			if(zjdtotal>0){
				if(zjdtotal==1){
					List<String> zjdlist=new ArrayList<String>();
					for(int i=0;i<zjdindex.length;i++){
						if(zjdindex[i]==1){
							zjdlist.add(ajjbqk.get(i));
						}
					}
					wsajjbqkModel.setZjd(zjdlist);
				}else{
					List<String> zjdlist=new ArrayList<String>();
					String zjd="";
					int pre=0;
					for(int i=0;i<zjdindex.length;i++){
						if(zjdindex[i]==1){
							zjd=ajjbqk.get(i);
							pre=i;
							break;
						}
					}
					for(int i=pre+1;i<ajjbqk.size();i++){
						if((zjdindex[i]==1)&&((i-pre)==1)){								
							zjd+=ajjbqk.get(i)+" ";
							pre=i;
						}else if(zjdindex[i]==1){
							zjdlist.add(zjd);
							zjd=ajjbqk.get(i)+" ";
							pre=i;
						}
					}
					zjdlist.add(zjd);
					wsajjbqkModel.setZjd(zjdlist);
				}

			}
			//��������ʵ���������model��
			if(cmsstotal>0){
				if(cmsstotal==1){
					for(int i=0;i<cmssin.length;i++){
						if(cmssin[i]==1){
							cmsslist.add(ajjbqk.get(i));
						}
					}
					wsajjbqkModel.setCmssd(cmsslist);
				}else{

					String cmss="";
					int pre=0;
					for(int i=0;i<cmssin.length;i++){
						if(cmssin[i]==1){
							cmss=ajjbqk.get(i);
							pre=i;
							break;
						}
					}
					for(int i=pre+1;i<ajjbqk.size();i++){
						if((cmssin[i]==1)&&((i-pre)==1)){								
							cmss+=ajjbqk.get(i)+" ";
							pre=i;
						}else if(cmssin[i]==1){
							cmsslist.add(cmss);
							cmss=ajjbqk.get(i)+" ";
							pre=i;
						}
					}
					cmsslist.add(cmss);
					wsajjbqkModel.setCmssd(cmsslist);
				}

			}
		}
		CltbqkService cs = new CltbqkServiceImpl();
		String sfzbxqn = "��";
		String sftb ="��";
		String sfxqpf = "��";
		String jdsfkk = "��";
		String shangQing = cs.getShangQing(cmsslist);
		String realPay = cs.getRealPay(cmsslist);
		String identifyContent = cs.getIdentifyContent(cmsslist);
		String touBaoXZ = cs.touBaoXZ(cmsslist);
		wsajjbqkModel.setTbxz(touBaoXZ);
		if(cs.isToubao(cmsslist)){
			sftb = "��";
			if(!cs.isValid(cmsslist)){
				sfzbxqn = "��";
			}
//			System.out.println("Ͷ�������ǣ�"+touBaoXZ);
		}
		wsajjbqkModel.setSftb(sftb);
		wsajjbqkModel.setSfzbxqn(sfzbxqn);
		if(cs.isPay(cmsslist)){
			sfxqpf = "��";
		}
		wsajjbqkModel.setSfxqpf(sfxqpf);
		if(!cs.identifyIsTrue(cmsslist)){
			jdsfkk = "��";
		}
		wsajjbqkModel.setJdsfkk(jdsfkk);
		wsajjbqkModel.setShangQing(shangQing);
		wsajjbqkModel.setRealPay(realPay);
		wsajjbqkModel.setIdentifyContent(identifyContent);
		
		
		CmssdpreService cps=new CmssdpreServiceImpl();
		String sgxq=cps.getSgxq(cmsslist);
		String sgsj=cps.getSgsj(sgxq);
		String sgdd=cps.getSgdd(sgxq);
		String jdcsyr=cps.getJdcsyr(cmsslist,sgxq,bglist);
		String jdcglr=cps.getJdcglr(cmsslist,sgxq,bglist);
//		String gajgrdyj=cps.getGajgrdyj(cmsslist);
		String shrjzd=cps.getShrjzd(wssscyrModellist);
		String shrzy=cps.getShrzy(wssscyrModellist);
		wsajjbqkModel.setSgxq(sgxq);
		wsajjbqkModel.setSgsj(sgsj);
		wsajjbqkModel.setSgdd(sgdd);
		wsajjbqkModel.setJdcglr(jdcglr);
		wsajjbqkModel.setJdcsyr(jdcsyr);
//		wsajjbqkModel.setGajgrdyj(gajgrdyj);
		wsajjbqkModel.setShrjzd(shrjzd);
		wsajjbqkModel.setShrzy(shrzy);
//		List<PjjeModel> jeModels = MoneyUtil.getJeModels(wsajjbqkModel.getYgscd(), true);
//		wsajjbqkModel.setSsqqjeList(jeModels);
		return wsajjbqkModel;
	}
	public boolean likeZjd(String content){
		boolean b=false;
		if(content.length()>=100)
			content=content.substring(0,100);
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
				||content.contains("֤������:")
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
				||content.contains("������ʵ")||content.contains("������ʵ")||content.contains("��ʵ����")||
				content.contains("���������"))
			b=true;
		return b;
	}
	public boolean issurplus(String content){
		boolean b=false;
		String rexCh="[һ�����������߰˾�ʮ]";
		String rexNu="\\d{1}";
		Pattern patternCh=Pattern.compile(rexCh);
		Pattern patternNu=Pattern.compile(rexNu);

		if(content.length()>=5)
			content=content.substring(0,5);
		Matcher matcherCh=patternCh.matcher(content);
		Matcher matcherNu=patternNu.matcher(content);
		if(matcherCh.find()||matcherNu.find())
			b=true;
		return b;
	}

}
