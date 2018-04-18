package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;

/**
 * ���¶��󰸼�������Ϣ����
 * @author wangzh
 *
 */
public class MsesAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
//        for (String s : ajjbqk) {
//            System.out.println(s);
//        }
        //����ǰ�����ͱ������
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

		if(ajjbqk==null || ajjbqk.size()==0){
			return wsajjbqkModel;
		}

		List<String> tempAjjbqk = new ArrayList<>();
		for (String s : ajjbqk) {
			for (WssscyrModel wssscyrModel : wssscyrModellist) {
				if(s.contains(wssscyrModel.getSscyr()))
					s = s.replaceAll(wssscyrModel.getSscyr(), wssscyrModel.getSssf());
			}
            tempAjjbqk.add(s);
		}

		if(ajjbqk.size()==0){
			return wsajjbqkModel;
		}

		List<List<String>> keySentence = new ArrayList<>();
		for (String s : tempAjjbqk) {
			String[] strings = s.split("\\.|��");
			List<String> list = new ArrayList<>();
			for (String string : strings) {

                if(string.contains("��")){
                    string = string.substring(0, string.indexOf("��"));
                }
                if(string.contains(":")){
                    string = string.substring(0, string.indexOf(":"));
                }
				boolean isContainsFY = string.contains("��Ժ")||string.contains("��Ժ");
				boolean isContainsRD = string.contains("��Ϊ")||string.contains("����")||string.contains("�϶�")||string.contains("����")||string.contains("�о�");
				boolean isContainsSSCYR = string.contains("������")||string.contains("ԭ��")||string.contains("����");
				boolean isContainsSS = string.contains("���")||string.contains("�߳�")||string.contains("��������")||string.contains("����");
				boolean isContainsFL = string.contains("��");
				if(((isContainsFY && isContainsRD) || (isContainsSSCYR && isContainsSS)) && !isContainsFL ){
					list.add(string);
				}
			}
			keySentence.add(list);
		}

		for (List<String> strings : keySentence) {
			System.out.println(strings);
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
			int end=-1;
			int index1=0;
			if(tokens.size()<20){
				index1=tokens.size();
			}else{
				index1=20;
			}
			for(int i=0;i<index1;i++){
				String content=tokens.get(i);
				if(content.equals("ԭ��")||content.equals("ǰ��")||content.equals("����")||content.equals("һ��")
						||content.equals("�߳�")||content.equals("ԭ��")){				  
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
						if(content.equals("����")||content.equals("����")||content.equals("������")||
								(ajnr.indexOf("���߳�")>-1&&ajnr.indexOf("���߳�")<30)){
							end=i;
							break;
						}
					}
					if(end>-1) break;
				}  
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
				//�������������˱�ƶ�
				List<Integer> bsindex=new ArrayList<Integer>();
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
						if(bcajnr.contains("���")||bcajnr.contains("���")){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);					
						}
					}
				}  
				if(bssrbcdlist.size()>0)
					wsajjbqkModel.setBssrbcd(bssrbcdlist);
				//���������������߳ƶ�
				String ssrscd="";
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
								if((content.equals("����")||content.equals("����")||content.equals("������")||
										(ajnr.indexOf("���߳�")>-1&&ajnr.indexOf("���߳�")<30))){
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
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						bssld+=ajjbqk.get(i)+" ";
					}else{
						if(!bssld.equals("")){
							bssldlist.add(bssld);
							bssld="";
						}
					}
				}
				wsajjbqkModel.setBssld(bssldlist);
				//����ǰ���о���
				String []qsdllast=ajjbqk.get(end-1).split("��");
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
		NewAjjbqkParseRule esAjjbqkParseRule = new NewAjjbqkParseRule();
		esAjjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist, wsajjbqkModel);
		return wsajjbqkModel;
	}

}
