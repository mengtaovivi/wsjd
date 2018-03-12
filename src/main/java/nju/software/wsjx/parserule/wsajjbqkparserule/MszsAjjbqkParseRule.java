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
 * @author zhouwei
 *
 */
public class MszsAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		//����ǰһ����䡢ǰ�����ͱ������
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

		if(ajjbqk==null || ajjbqk.size()==0){
			return wsajjbqkModel;
		}
		if(ajjbqk.size()==1){
			wsajjbqkModel.setBsdl(ajjbqk.get(0));
			List<String> bssldlist=new ArrayList<String>();
			bssldlist.add(ajjbqk.get(0));
			wsajjbqkModel.setBssld(bssldlist);
		}else{
			String ajjbqk_1=ajjbqk.get(0);
			int qsindex=0;
			List<String> tokens = FcUtil.getWholeToken(ajjbqk_1);
			int pre=-1;
			//ǰһ�����
			int end=-1;
			//ǰ�����
			int end1=-1;
			//�������
			int end2=-1;
			int index1=0;
			if(tokens.size()<20){
				index1=tokens.size();
			}else{
				index1=20;
			}
			for(int i=0;i<index1;i++){
				String content=tokens.get(i);
				if(content.equals("ԭ��")||content.equals("ǰ��")||content.equals("����")||content.equals("һ��")
						||content.equals("�߳�")||content.equals("ԭ��")||content.equals("����")||content.equals("����")){				  
					pre=0;
					break;
				}
			}
			if(pre==0){  
				for(int i=1;i<ajjbqk.size();i++){
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
						if(content.equals("����")){
							end=i;
							break;
						}
						if(content.equals("����")||content.equals("����")||content.equals("������")||
								(ajnr.indexOf("���߳�")>-1&&ajnr.indexOf("���߳�")<30)){
							end1=i;
							break;
						}
						if((content.equals("����"))){
							if(end2==-1||end>end2||end1>end2){
								end2=i;
								break;
							}
						}
					}
				}  
				if(end==-1) end=0;
				if(end1==-1) end1=0;
				if(end2==-1) end2=ajjbqk.size()-1;
				if(end>end1){ 
					end1=end;
				}
				//����ǰһ�����
				String qysdl="";
				for (int i = end; i <end1; i++) {
					if(i!=end1-1)			
						qysdl += ajjbqk.get(i) + " ";
					else
						qysdl += ajjbqk.get(i);
				}
				if(qysdl!=""){
					wsajjbqkModel.setQysdl(qysdl);	
				}
				//����ǰ�����
				String qsdl="";
				for (int i = end1; i <end2; i++) {
					if(i!=end2-1)			
						qsdl += ajjbqk.get(i) + " ";
					else
						qsdl += ajjbqk.get(i);
				}
				if(qsdl!=""){
					wsajjbqkModel.setQsdl(qsdl);
				}
				//�����������
				String bsdl = "";
				for (int i = 0; i < end ; i++) {
					bsdl += ajjbqk.get(i) + " ";
				}
				for (int i = end2; i < ajjbqk.size() ; i++) {
					if(i!=ajjbqk.size()-1)
						bsdl += ajjbqk.get(i) + " ";
					else
						bsdl += ajjbqk.get(i) ;
				}
				if(bsdl!=""){
					wsajjbqkModel.setBsdl(bsdl);
				}
				//�������������˱�ƶ�
				List<Integer> bsindex=new ArrayList<Integer>();
				List<String> bssrbcdlist=new ArrayList<String>();
				for(int i=0;i<end;i++){
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
						if(bcajnr.contains("���")||bcajnr.contains("���")){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);					
						}
					}
				}  
				for(int i=end2;i<ajjbqk.size();i++){
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
						if(bcajnr.contains("���")||bcajnr.contains("���")){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);					
						}
					}
				} 
				if(bssrbcdlist.size()>0)
					wsajjbqkModel.setBssrbcd(bssrbcdlist);
				//�����������߶�
				String ssrscd="";
				for(int i=0;i<end;i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
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
						boolean ssr=true;
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("����")||content.equals("����")){
								ssr=false;
								break;
							}
						}
						if(ssr){
							for(int j=0;j<index;j++){
								String content=nrtoken.get(j);
								if(content.equals("��")||content.equals("�߳�")||content.equals("����")){
									ssrscd+=ajnr+" ";
									bsindex.add(i);
									break;
								}
							}
						}

					}

				}
				for(int i=end2;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
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
						boolean ssr=true;
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("����")||content.equals("����")){
								ssr=false;
								break;
							}
						}
						if(ssr){
							for(int j=0;j<index;j++){
								String content=nrtoken.get(j);
								if(content.equals("��")||content.equals("�߳�")||content.equals("����")){
									ssrscd+=ajnr+" ";
									bsindex.add(i);
									break;
								}
							}
						}

					}

				}
				if(ssrscd!="")
				wsajjbqkModel.setSsrscd(ssrscd);
				//�������������
				String bssld="";
				List<String> bssldlist=new ArrayList<String>();
				for(int i=0;i<end;i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						bssld=ajjbqk.get(i);
						if(!bssld.equals("")){
							bssldlist.add(bssld);
							bssld="";
						}
					}
				}
				for(int i=end2;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						bssld=ajjbqk.get(i);
						if(!bssld.equals("")){
							bssldlist.add(bssld);
							bssld="";
						}
					}
				}
				wsajjbqkModel.setBssld(bssldlist);
				//����ǰ���о���
				String []qsdllast=ajjbqk.get(end2-1).split("��");
				int qspjindex=-1;
				for(int i=0;i<qsdllast.length;i++){
					if(qsdllast[i].contains("����")||qsdllast[i].contains("����")||qsdllast[i].contains("����")){
						int zs=qsdllast[i].indexOf("����");
						int yz=qsdllast[i].indexOf("����");
						int yj=qsdllast[i].indexOf("����");
						int min=0;
						if(zs!=-1) min=zs;
						if(yz!=-1) min=min<yz?min:yz;
						if(yj!=-1) min=min<yj?min:yj;
						if(qsdllast[i].indexOf("�о�")>min||qsdllast[i].indexOf("�ö�")>min){	
							qspjindex=i;
							break;
						}
					}
				}
				if(qspjindex==-1){
					for(int i=0;i<qsdllast.length;i++){
						if((qsdllast[i].contains("�о�")||qsdllast[i].contains("�ö�"))){
							qspjindex=i;
							break;
						}
					}
				}
				int qspjdpre=end2;
				if(qspjindex>-1){
					String qspjd="";
					for(int j=qspjindex;j<qsdllast.length;j++){
						qspjd+=qsdllast[j]+"��";
					}
					qspjdpre=end2-1;
					wsajjbqkModel.setQspjd(qspjd);
				}
				//����ǰ�󱻸��ƶΡ�ԭ���߳ƶ�
				List<Integer> exindex=new ArrayList<Integer>();
				List<Integer> qsdlindex=new ArrayList<Integer>();
				String qsbgbcd="";
				for(int i=end1;i<qspjdpre;i++){
					String ajnr=ajjbqk.get(i);
						if(ajnr.indexOf("���")>-1&&ajnr.indexOf("���")<30){
							qsbgbcd+=ajnr+" ";
							exindex.add(i);					
						}
				}
				for(int i=0;i<qspjindex;i++){
					if(qsdllast[i].indexOf("���")>-1&&qsdllast[i].indexOf("���")<30){
						qsbgbcd+=qsdllast[i]+"��";
						qsdlindex.add(i);	
					}
				}
				if(qsbgbcd!=""){
					wsajjbqkModel.setQsbgbcd(qsbgbcd);
				}
				String qsygscd="";
				for(int i=end1;i<qspjdpre;i++){
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
						if(content.equals("��������")||content.equals("����")||content.equals("�߳�")||content.equals("����")||
								content.equals("��������")||content.equals("����")){
							qsygscd+=ajnr+" ";
							exindex.add(i);
							break;
						}
					}
				}  
				for(int i=0;i<qspjindex;i++){
					if(qsdllast[i].contains("��������")||qsdllast[i].contains("����")||qsdllast[i].contains("�߳�")||qsdllast[i].contains("����")||
							qsdllast[i].contains("��������")||qsdllast[i].contains("����")){
						qsygscd+=qsdllast[i]+"��";
						qsdlindex.add(i);
					}
				}
				if(qsygscd!=""){
					wsajjbqkModel.setQsygscd(qsygscd);
				}
				//����ǰ�������
				String qssld="";
				List<String> qssldlist=new ArrayList<String>();
				for(int i=end1;i<qspjdpre;i++){
					boolean b=true;
					for(int j=0;j<exindex.size();j++){
						if(i==exindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						qssld=ajjbqk.get(i);
						if(!qssld.equals("")){
							qssldlist.add(qssld);
							qssld="";
						}
					}
				}
				for(int i=0;i<qspjindex;i++){
					boolean b=true;
					for(int j=0;j<qsdlindex.size();j++){
						if(i==qsdlindex.get(j)){
							b=false;
							break;
						}
					}
					if(b){
						qssld+=qsdllast[i]+"��";
					}
				}
				if(!qssld.equals("")){
					qssldlist.add(qssld);
				}
				wsajjbqkModel.setQssld(qssldlist);
				//����ǰһ���о���
				if(end1>0){
					String []qysdllast=ajjbqk.get(end1-1).split("��");
					int qyspjindex=-1;
					for(int i=0;i<qysdllast.length;i++){
						if(qysdllast[i].contains("����")||qysdllast[i].contains("����")||qysdllast[i].contains("����")){
							int zs=qysdllast[i].indexOf("����");
							int yz=qysdllast[i].indexOf("����");
							int yj=qysdllast[i].indexOf("����");
							int min=0;
							if(zs!=-1) min=zs;
							if(yz!=-1) min=min<yz?min:yz;
							if(yj!=-1) min=min<yj?min:yj;
							if(qysdllast[i].indexOf("�о�")>min||qysdllast[i].indexOf("�ö�")>min){	
								qyspjindex=i;
								break;
							}
						}
					}
					if(qyspjindex==-1){
						for(int i=0;i<qysdllast.length;i++){
							if((qysdllast[i].contains("�о�")||qysdllast[i].contains("�ö�"))){
								qyspjindex=i;
								break;
							}
						}
					}
					if(qyspjindex>-1){
						String qyspjd="";
						for(int j=qyspjindex;j<qysdllast.length;j++){
							qyspjd+=qysdllast[j]+"��";
						}
						wsajjbqkModel.setQyspjd(qyspjd);
					}	
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
}
