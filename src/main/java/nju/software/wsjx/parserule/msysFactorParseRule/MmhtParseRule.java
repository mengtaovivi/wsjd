package nju.software.wsjx.parserule.msysFactorParseRule;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.service.model.msysFactorModel.MmhtModel;

public class MmhtParseRule {
	public MmhtModel getMmhtModel(WsAnalyse wsAnalyse,WsModel wsModel){
		MmhtModel mmhtModel = new MmhtModel();
		
		List<String> cmssd = wsModel.getWsajjbqkModel().getCmssd();
		List<String> zjd = wsModel.getWsajjbqkModel().getZjd();
		String ygscd = wsModel.getWsajjbqkModel().getYgscd();
		String cpfxgc = wsModel.getWscpfxgcSegment();
		String cmssnr = "";
		String zjnr = "";
		String[] cmssList;
		String[] cmssList2;
		String[] cmssList3;
		String[] cpfxgcList;
		String[] zjdList;
        for(String cmss:cmssd){
        	if(cmss.contains("���������")||cmss.contains("��Ժ�϶���ʵ����")
        			||cmss.contains("��Ժ����������ʵ")||cmss.contains("��Ժ����������֤����")){
        		cmssnr = cmss;
        	}
        }
        if(zjd!=null){
        	 for(String zj:zjd){
             	if(zj.contains("��ͬǩ����")||zj.contains("��ͬ������")){
             		zjnr = zj;
             	}
             }
        }    
		cmssList = cmssnr.split("��|��");
		cmssList2 = cmssnr.split("��|��|��");
		cmssList3 = cmssnr.split("��|��|��|��");
		cpfxgcList = cpfxgc.split("��");
		zjdList = zjnr.split("��|��");
		
		//����ǩ��ʱ��
		String qdsj = "";
		for(String list:cmssList){
			if(list.contains("��")&&list.contains("��ͬ")&&list.contains("��")
					&&list.contains("��")&&list.contains("��")){
				qdsj = list.substring(list.indexOf("��")-4,list.indexOf("��")+1);
				if(qdsj!=""){
					mmhtModel.setHtqdsj(qdsj);
					break;
				}
			}
		}
				
		//������Ʒ���ơ�����
		String wmc = "";
		String sl ="";
		for(String list:cmssList2){
			if(list.contains("��")&&list.contains("��")&&list.contains("ǩ��")&&list.contains("��ͬ")
					&&(list.indexOf("��ͬ")-list.indexOf("ǩ��")!=3)){
				wmc = list.substring(list.indexOf("��")+1,list.indexOf("��")-4);
			}
			if(list.contains("ԭ��")&&list.contains("����")&&list.contains("��Ӧ")&&list.contains("��")){
				if(isNumeric(list)!=0){
					sl = list.substring(isNumeric(list));
                    wmc = list.substring(list.indexOf("��Ӧ")+2,isNumeric(list));
				}else{
					wmc = list.substring(list.indexOf("��Ӧ")+2);
					}	
			}
			if(list.contains("ԭ��")&&list.contains("����")&&list.contains("��")||
					list.contains("����")&&list.contains("����")&&list.contains("��")){
				wmc = list.substring(list.indexOf("����")+2);
				if(wmc.contains("�ܽ��")&&isNumeric(wmc)>0){
					if(wmc.indexOf("Ԫ")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("�ܽ��"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("Ԫ")+1);
					}								
				}
				if(wmc.contains("��ֵ")&&isNumeric(wmc)>0){
					if(wmc.indexOf("Ԫ")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("�ܽ��"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("Ԫ")+1);
					}	
				}
			}
			if(list.contains("ԭ��")&&list.contains("����")&&list.contains("��")||
					list.contains("����")&&list.contains("����")&&list.contains("��")){
				wmc = list.substring(list.indexOf("����")+2);
				if(wmc.contains("�ܽ��")&&isNumeric(wmc)>0){
					if(wmc.indexOf("Ԫ")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("�ܽ��"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("Ԫ")+1);
					}								
				}
				if(wmc.contains("��ֵ")&&isNumeric(wmc)>0){
					if(wmc.indexOf("Ԫ")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("�ܽ��"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("Ԫ")+1);
					}	
				}
			}
			if(list.contains("����")&&isNumeric(list)>0){
				sl = list.substring(list.indexOf("����")+2);
			}
		}
		if(wmc!=""){
			mmhtModel.setWmc(wmc);
		}
		if(wmc==""){
		    String[]  ygscList = ygscd.split("��|��");
			for(String list:ygscList){
				if(list.contains("ԭ��")&&list.contains("�ṩ")&&list.contains("����")){
					wmc = list.substring(list.indexOf("�ṩ")+2);
					if(wmc!=""){
						mmhtModel.setWmc(wmc);
					}
				}else if(list.contains("ԭ��")&&list.contains("��Ӧ")&&list.contains("����")){
					wmc = list.substring(list.indexOf("��Ӧ")+2);
					if(wmc!=""){
						mmhtModel.setWmc(wmc);
					}
				}
			}
		}
		if(sl!=""){
			mmhtModel.setYdsl(sl);
		}else{
			mmhtModel.setYdsl("δ�ἰ");
		}
		
		//���ṩ����Ʒ����һ������ʱ�ϲ����ƺ�����
		List<String> wmcsl = new ArrayList<String>(); 
		for(String list:cmssList3){
			if(list.indexOf("��")==-1&&list.indexOf("��")==-1&&list.indexOf("��")==-1&&list.indexOf("Ԫ")==-1
					&&isNumeric(list)>0&&list.indexOf("%")==-1&&list.indexOf("��")==-1&&list.indexOf("��")==-1){
				wmcsl.add(list);
			}
		}
		if(wmcsl.size()!=0){
			mmhtModel.setWmcsl(StringUtils.strip(wmcsl.toString(),"[]"));
		}else{
			mmhtModel.setWmcsl("��");
		}
			
		//������ͬǩ���ص�
		String qddd = "";
		for(String list:cmssList2){
			if(list.contains("ǩ���ص�")){
				qddd = list.substring(list.indexOf("ǩ���ص�")+4);
				if(qddd.contains("��")){
					qddd=qddd.replaceFirst("��", "");
					mmhtModel.setHtqddd(qddd);
					break;
				}else{
					mmhtModel.setHtqddd(qddd);
					break;
				}
			}
		}
		if(qddd==""){
			mmhtModel.setHtqddd("δ�ἰ");
		}
		
		//������ͬԼ���ܼۿ�(�������)
		String zje = "";
		for(String list:cmssList2){
			if((list.contains("�ܽ��")||list.contains("�ܼ�")||list.contains("�ϼ�")||list.contains("�����"))
					&&list.indexOf("%")==-1&&isNumeric(list)>0){
				zje = list.substring(isNumeric(list));
					mmhtModel.setYdjk(zje);
			}
			if(list.contains("���")&&list.contains("�ܽ��")){
				zje = list.substring(isNumeric(list));
				mmhtModel.setYdjk(zje);
			}
		}
		if(zje==""){
			mmhtModel.setYdjk("δ�ἰ");
		}
		
		//��ͬԼ���ۿ��ʱ��
		String ydjfsj = "";
		for(String list:cmssList2){
			if(list.contains("Ӧ��ʱ��")&&list.contains("��")&&list.contains("��")&&list.contains("��")){
				ydjfsj = list.substring(list.indexOf("Ӧ��ʱ��Ϊ")+5,list.indexOf("��")+1);
			}else if(list.contains("Ӧ��ʱ��")&&list.contains("��")&&list.contains("��")){
				ydjfsj = list.substring(list.indexOf("Ӧ��ʱ��Ϊ")+5,list.indexOf("��")+1);
			}
		}
		if(ydjfsj!=""){
			mmhtModel.setYdjkjfdd(ydjfsj);
		}else{
			mmhtModel.setYdjkjfdd("δ�ἰ");
		}
		
		//��ͬԼ���ۿ������ʽ
		String jkjffs = "";
		for(String list:cmssList2){
			if(list.contains("ԭ��")&&list.contains("����")&&list.contains("ָ��")&&list.contains("��ʽ")){
				jkjffs = list;
			}
		}
		if(jkjffs!=""){
			mmhtModel.setYdjkjffs(jkjffs);
		}else{
			mmhtModel.setYdjkjffs("δ�ἰ");
		}
		
		//������ͬԼ���ｻ��ʱ��(���ж��ֻ����ҽ���ʱ�䲻һ�£�ֻ����ȡ�������ᵽ�Ľ���ʱ��)
		String jhsj = "";
		for(String list:cmssList2){
			if(list.contains("�����ᣩ��ʱ��")){
				jhsj = list.substring(list.indexOf("�����ᣩ��ʱ��Ϊ")+8);
			}else if(list.contains("����ʱ��")){
				jhsj = list.substring(list.indexOf("����ʱ��Ϊ")+5);
			}
			if(list.contains("����")&&list.contains("����")&&list.contains("��")){
				jhsj = list;
			}
		}
		if(jhsj!=""){
			mmhtModel.setYdwjfsj(jhsj);
		}else{
			mmhtModel.setYdwjfsj("δ�ἰ");
		}
		
		//��ͬԼ���ｻ���ص㡢ʵ�ʽ����ص�
		String wydjfdd = "";
		String wsjjfdd = "";
		for(String list:cmssList2){
			if(list.contains("ԭ��")&&list.contains("����")&&list.contains("����")&&list.contains("�ص�")){
				wsjjfdd = list.substring(list.indexOf("����")+2);
				mmhtModel.setWsjjfdd(wsjjfdd);
			}else if(list.contains("ԭ��")&&list.contains("����")&&list.contains("�͵�")){
				wsjjfdd = list.substring(list.indexOf("�͵�")+2);
				mmhtModel.setWsjjfdd(wsjjfdd);
			}else if(list.contains("�����ص�Ϊ")){
				wydjfdd = list.substring(list.indexOf("�����ص�Ϊ")+5);
				mmhtModel.setWydjfdd(wydjfdd);
			}else if(list.contains("Ӧ")&&list.contains("����")&&list.contains("ָ��")){
				wydjfdd = list.substring(list.indexOf("����")+2);
				mmhtModel.setWydjfdd(wydjfdd);
			}else if(list.contains("����")&&list.contains("�ص�")){
				wydjfdd = list.substring(list.indexOf("�ص�"));
				mmhtModel.setWydjfdd(wydjfdd);
			}else if(list.contains("����")){
				wydjfdd = list.substring(list.indexOf("����")+2);
				mmhtModel.setWydjfdd(wydjfdd);
			}
		}
		if(wydjfdd==""){
			mmhtModel.setWydjfdd("δ�ἰ");
		}
		if(wsjjfdd==""&&zjdList.length!=0){
			for(String list:zjdList){
				if(list.contains("����")){
					wsjjfdd = list.substring(list.indexOf("����")+2);
					mmhtModel.setWsjjfdd(wsjjfdd);
				}
			}
		}else{
			mmhtModel.setWsjjfdd("δ�ἰ");
		}
		
		//��ͬԼ����ʵ�ʽ���ʱ��
		String wsjjfsj = "";
		for(String list:cmssList){
			if(list.contains("ԭ��")&&list.contains("��")&&list.contains("��")&&list.contains("��")&&list.contains("��")
					||list.contains("����")&&list.contains("��")&&list.contains("��")&&list.contains("��")&&list.contains("��")
					||list.contains("��")&&list.contains("����")&&list.contains("��")&&list.contains("��")&&list.contains("��")&&list.contains("��")){
				if(list.contains("��")&&list.contains("��")){
					if((list.indexOf("��", list.indexOf("��")+1)-list.indexOf("��"))>0){
						wsjjfsj = list.substring(list.indexOf("��")+1,list.indexOf("��", list.indexOf("��")+1)+1);
					}else if((list.indexOf("��", list.indexOf("��")+1)-list.indexOf("��"))>0){
						wsjjfsj = list.substring(list.indexOf("��")+1,list.indexOf("��", list.indexOf("��")+1)+1);
					}					
				}else{
					wsjjfsj = list.substring(list.indexOf("��")-4,list.indexOf("��")+1);
				} 
			}
		}
		if(wsjjfsj ==""&&zjdList.length!=0){
			for(String list:zjdList){
				if(list.contains("����")&&list.contains("��")&&list.contains("��")&&list.contains("��")){
					wsjjfsj = list.substring(list.indexOf("��")-4,list.indexOf("��")+1);
					mmhtModel.setWsjjfsj(wsjjfsj);
				}
			}
		}
		if(wsjjfsj!=""){
			mmhtModel.setWsjjfsj(wsjjfsj);
		}else{
			mmhtModel.setWsjjfsj("δ�ἰ");
		}
				
		//������ͬԼ���ｻ����ʽ
		String jhfs = "";
		for(String list:cmssList2){
			if(list.contains("�����ᣩ����ʽ")){
				jhfs = list.substring(list.indexOf("�����ᣩ����ʽΪ")+8);
			}else if(list.contains("������ʽ")){
				jhfs = list.substring(list.indexOf("������ʽ"));
			}
		}
		if(jhfs!=""){
			mmhtModel.setYdwjffs(jhfs);
		}else{
			mmhtModel.setYdwjffs("δ�ἰ");
		}
		
		//������ͬԼ�����㷽ʽ
		List<String> jsfs = new ArrayList<String>();
		for(String list:cmssList3){
			if(list.contains("���㷽ʽ")||list.contains("���ʽ")){
				 jsfs.add(list);
			}
			if(list.indexOf("��")==-1&&list.contains("��")&&list.contains("��")&&list.indexOf("ΥԼ��")==-1||
					list.indexOf("��")==-1&&list.contains("%")&&list.contains("��")&&list.indexOf("ΥԼ��")==-1){
				if(list.contains("����")){}
				else{
					 jsfs.add(list);
				}
			}if(list.contains("����")){
				jsfs.add(list);
			}else if(list.contains("��")&&list.contains("����")){
				jsfs.add(list);
			}			
		}
		if(jsfs.size()!=0){			
			mmhtModel.setYdjkjsfs(StringUtils.strip(jsfs.toString(),"[]"));
		}else{
			mmhtModel.setYdjkjsfs("δ�ἰ");
		}
		
		//����Լ��������ʱ�䣨Լ������ʵ�ʣ���
		String yssj = "";
		for(String list:cmssList){
			if(list.contains("��")&&list.contains("��")&&list.contains("��")&&list.contains("���߰�װ���յ�")){
				yssj = list.substring(list.indexOf("��")-4,list.indexOf("��")+1);
				if(yssj!=""){
					mmhtModel.setYssj(yssj);
					break;
				}
			}
		}
		if(yssj==""){
			mmhtModel.setYssj("δ�ἰ");
		}
				
		//������ͬԼ��ΥԼ���μ��е���ʽ
		List<String> wyzr = new ArrayList<String>();
		for(String list:cmssList){
			if(list.contains("�е�ΥԼ����")||list.contains("ΥԼ����Լ��")){
				wyzr.add(list);
			}
		}
		if(wyzr.size()!=0){
			mmhtModel.setYdwyzr(wyzr);
		}else{
			wyzr.add("δ�ἰ");
			mmhtModel.setYdwyzr(wyzr);
		}
			
		//����ΥԼ����㹫ʽ��ԭ���߳ƺͲ��н����һ����ȡ��һ������
		String cpjgd = wsModel.getWscpjgSegment();
		String[] cpjgnr = cpjgd.split("��");
		String wyjjs = "";
		for(String list:cpjgnr){
			if(list.contains("ΥԼ��")&&(list.indexOf("��")-list.indexOf("��"))==1){
				wyjjs = list.substring(list.indexOf("��"),list.indexOf("��"));
			}else if(list.contains("ΥԼ��")&&(list.indexOf("[")-list.indexOf("��"))==1){
				wyjjs = list.substring(list.indexOf("["),list.indexOf("]"));
			}else if(list.contains("ΥԼ��")&&isNumeric(list)>0&&list.contains("Ԫ")){
				wyjjs = list.substring(list.indexOf("ΥԼ��")+3,list.indexOf("Ԫ")+1);
			}
		}
		if(wyjjs!=""){
			mmhtModel.setWyjjsgs(wyjjs);
		}else{
			mmhtModel.setWyjjsgs("δ�ἰ");
		}
		
		//������ͬԼ����ʵ�ʽ�������
		String wsjjfsl = "";
		for(String list: cmssList){
			String[] str = list.split("��|��");
			if(list.contains("��")&&list.contains("ԭ��")&&list.contains("�ṩ")
					||list.contains("��")&&list.contains("����")&&list.contains("�ṩ")){				
				for(String list2:str){
					if(list2.contains("�ṩ")&&isNumeric(list2)>0){
						wsjjfsl = list2.substring(isNumeric(list2));
					}
				}				
			}else if(list.contains("��")&&list.contains("ԭ��")&&list.contains("����")
					||list.contains("��")&&list.contains("����")&&list.contains("����")){
				for(String list2:str){
					if(list2.contains("����")&&isNumeric(list2)>0){
						wsjjfsl = list2.substring(isNumeric(list2));
					}
				}
			}else if(list.contains("��")&&list.contains("ԭ��")&&list.contains("�ṩ")
					||list.contains("��")&&list.contains("����")&&list.contains("�ṩ")){
				for(String list2:str){
					if(list2.contains("�ṩ")&&isNumeric(list2)>0){
						wsjjfsl = list2.substring(isNumeric(list2));
					}
				}
			}
		}
		if(wsjjfsl!=""){
			mmhtModel.setWsjjfsl(wsjjfsl);
		}else{
			mmhtModel.setWsjjfsl("δ�ἰ");
		}
		
		//������ͬ�ۿ�ʵ��֧�����
		List<String> sjzf = new ArrayList<String>();
		for(String list:cmssList){
			if(list.contains("��")&&list.contains("ԭ��")&&list.contains("֧��")&&isNumeric(list)>0
					&&list.indexOf("%")==-1&&list.indexOf("��")==-1
					||list.contains("��")&&list.contains("����")&&list.contains("֧��")&&isNumeric(list)>0
					&&list.indexOf("%")==-1&&list.indexOf("��")==-1){
				sjzf.add(list);
			}
			if(list.contains("ԭ��")&&list.contains("��Ƿ")&&isNumeric(list)>0
				||list.contains("����")&&list.contains("��Ƿ")&&isNumeric(list)>0){
					sjzf.add(list);
			}
			if(list.contains("�Ѿ�֧��")||list.contains("ʣ��δ��")){
				sjzf.add(list);
			}
		}
		if(sjzf.size()!=0){			
			mmhtModel.setJksjzf(StringUtils.strip(sjzf.toString(),"[]"));
		}
		if(sjzf.size()==0&&zjdList.length!=0){
			for(String list:zjdList){
				if(list.contains("ԭ��")&&list.contains("��")&&isNumeric(list)>0
						||list.contains("����")&&list.contains("��")&&isNumeric(list)>0){
					sjzf.add(list);
				}
				if(list.contains("��Ƿ")&&isNumeric(list)>0||list.contains("����δ��")){
					sjzf.add(list);
				}
				if(sjzf.size()!=0){			
					mmhtModel.setJksjzf(StringUtils.strip(sjzf.toString(),"[]"));
				}
			}
		}else{
			mmhtModel.setJksjzf("δ�ἰ");
		}
		
		//�Ժ�ͬԼ��ΥԼ���׼��������
		String ywyy = "";
		for(String list:cpfxgcList){
			if(list.contains("���")&&list.contains("ΥԼ��")&&list.contains("����Ժ���Ե���")){
				ywyy = "��";
				mmhtModel.setDwyjbzywyy(ywyy);
				break;
			}else if(list.contains("ԭ��")&&list.contains("��Ϊ")&&list.contains("��")
					||list.contains("����")&&list.contains("��Ϊ")&&list.contains("��")){
				ywyy = "��";
				mmhtModel.setDwyjbzywyy(ywyy);
				break;
			}
		}
        if(ywyy!=""){}
        else{
        	mmhtModel.setDwyjbzywyy("��");
        }
		
		//�����ʱ���
        String zbj = "";
        for(String list:cmssList2){
        	if(list.contains("Ϊ�ʱ���")){
        		zbj= list;
        	}
        	if(zbj!=""){
        		mmhtModel.setYdzbj(zbj);
        		break;
        	}
        }
        if(zbj!=""){}
        else{
        	mmhtModel.setYdzbj("δ�ἰ");
        }
        
        //�����ʱ���
        String zbq = "";
        for(String list:cmssList2){
        	if(list.contains("�ʱ���")&&list.contains("��")){
        		zbq = list;
        		if(zbq!=""){
        			mmhtModel.setYdzbq(zbq);
        			break;
        		}
        	}
        }
        if(zbq==""){
        	mmhtModel.setYdzbq("δ�ἰ");
        }
        
        //�����Ƿ������ʧ����ʧ��������
        boolean flag = false;
        String ss = "";
        String ssse = "";
	    String[]  ygscList = ygscd.split("��");
        if(ygscd.contains("�⳥")&&ygscd.contains("��ʧ")){
        	for(String list:cpfxgcList){
        		if(list.contains("��ʧ")&&list.contains("�Դ˱�Ժ������׼")){
        			flag = true;
        			ss = "��";
        			mmhtModel.setSfzcss(ss);
        			break;
        		}
        	}
            for(String list:ygscList){
            	if(list.contains("�⳥")&&list.contains("��ʧ")&&isNumeric(list)>0){
            		ssse = list.substring(list.indexOf("��ʧ")+2);
            		mmhtModel.setSsse(ssse);
            		break;
            	}
            }
        }
        if(ss==""){
        	mmhtModel.setSfzcss("��");
        	mmhtModel.setSsse("��");
        }
        
        //������ͬ��������������
        String ywzlzy = "";
        for(String list:cmssd){
        	if(list.contains("ԭ��")&&list.contains("����")&&list.contains("��������")
        			||list.contains("����")&&list.contains("����")&&list.contains("��������")){
        		ywzlzy = "��";
        		mmhtModel.setWywzlzy(ywzlzy);
        		break;
        	}else if(list.contains("��������ʹ��")||list.contains("�޷�����ʹ��")){
        		ywzlzy = "��";
        		mmhtModel.setWywzlzy(ywzlzy);
        		break;
        	}
        }
        if(ywzlzy==""){
        	mmhtModel.setWywzlzy("��");
        }
        
        //����ԭ�����ϼ�֤�ݶΡ������Ƽ�֤�ݶ�
        List<String> ygsszj = new ArrayList<String>();
        List<String> bgbczj = new ArrayList<String>();
        ygsszj.add(wsModel.getWsajjbqkModel().getYgscd());
        if(wsModel.getWsajjbqkModel().getBgbcd()!=""){
        	bgbczj.add(wsModel.getWsajjbqkModel().getBgbcd());
        }else{
        	bgbczj.add("�ޱ�����");
        }
        if(zjd!=null){
        	for(String list:zjd){
            	if(list.contains("ԭ���ύ��֤��Ϊ")||list.contains("ԭ����Ժ�ύ����֤��")){
            		ygsszj.add(list);
            	}else if(list.contains("�����ύ��֤��Ϊ")||list.contains("������Ժ�ύ����֤��")){
            		bgbczj.add(list);
            	}else{
            		ygsszj.add(list);
            		bgbczj.add(list);
            	}
            }
        }       
        mmhtModel.setYgssqqjzj(ygsszj);
        mmhtModel.setBgkblyjzj(bgbczj);
		
		return mmhtModel;
	}
	
	//�����ַ����г��ֵĵ�һ�����ֵ�λ��
    public int isNumeric(String str) {
        for (int i = 0;i<str.length();i++) {
            if (Character.isDigit(str.charAt(i))) {
                return i;
            }
        }
		return 0;
    }
         
}
