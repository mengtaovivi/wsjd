package nju.software.wsjx.parserule.msysFactorParseRule;
import java.util.*;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.msysFactorModel.JrjkModel;
public class JrjkParseRule {
	/**
	 * ��ȡ���ڽ���Ҫ�أ����ؽ��ڽ��Ҫ��model
	 * @param wsAnalyse ��������������ֵķֶ���Ϣ���Լ�ÿ�εľ�������
	 * @param wsModel �Ѿ���ȡ��ɵ�����model
	 * @return
	 */
	public JrjkModel getJrjkModel(WsAnalyse wsAnalyse,WsModel wsModel){
		JrjkModel jrjkModel=new JrjkModel();
		//String ajjbqkseg = wsModel.getWsajjbqSegment();//��ȡ�¼������������
		//String[] ajjbqksegs = ajjbqkseg.split("��");//���¼������������ֶ�
		//String ygscd=wsModel.getWsajjbqkModel().getYgscd();//����ԭ���߳ƶ�
		List<String> zjd=wsModel.getWsajjbqkModel().getZjd();//����֤�ݶ�
		List<String> cpjgd=wsModel.getWscpjgModel().getCpjgnr();//���ò��н����
		List<String> cmsss = wsModel.getWsajjbqkModel().getCmssd();//��ȡ������ʵ������
		//String cpjgs=cpjgd.toString();
		//String cpjgdnr[]=cpjgs.split("��|��");
		String cmssList="";//ֻ��ȡ��ʵ��
		String [] cmssSplit;//��Ž�ȡ֮��Ĳ�����ʵ������
		String [] cmssSplit2;
		for (String cmss: cmsss) {
			if (cmss.contains("��Ժ�϶���ʵ����")||cmss.contains("���������")||cmss.contains("��Ժ����������ʵ")||cmss.contains("��ʵ������")) {
				cmssList=cmss;
					}
				}
		//System.out.println(cmssList);
         cmssSplit=cmssList.split("��|��");//��ȡ֮��Ĳ�����ʵ�����ݰ������ֶ�
		cmssSplit2=cmssList.split("��|��|��|��|��");
		//for(String list:cmssSplit2) {
			//System.out.println(list);
		//}
        /*
        *����Ҫ�ص���ȡ 
        */
		//��ͬ����
		/*int htjs=0;
		for(String list:zjd) {
		if(list.contains("����ͬ")||list.contains("�����ͬ")||list.contains("���ź�ͬ")||list.contains("����Э��")){
				htjs++;
			}
			}*/
		//System.out.println("��ͬ������"+htjs);
		/*����ǩԼʱ��
		 * cmssList�ο��ܺ��ж��ʱ��
		 */
		String sj="δ�ἰ";
		for(String list:cmssSplit){
			if(list.contains("��")&&list.contains("��")&&list.contains("��")){
		         sj=list.substring(list.indexOf("��")-4,list.indexOf("��")+1);
		       break;
		   }}
		//System.out.println("ǩԼʱ�䣺"+sj);
		jrjkModel.setSj(sj);
		
		//����ǩ����
		List<String> qzr=new ArrayList<String>();//ǩ����
		List<WssscyrModel> wssscyrModellist=wsModel.getWssscyrModels();
		for(int i=0;i<wssscyrModellist.size();i++) {
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("ԭ��")||sscyr.getSssf().equals("����")) {
				qzr.add(sscyr.getSscyr());
			}
		}
		if(qzr!=null) {
			jrjkModel.setQzr(qzr);
		}
		/*for(String list:qzr) {
			System.out.println("ǩ���ˣ�"+list);
		}*/
		
		//���������
	    String jkje="δ�ἰ";
	    for (String list : cmssSplit2) {
				if ((list.contains("���")||list.contains("����"))&&list.contains("Ԫ")) {
					jkje=list.substring(list.indexOf("��")+1, list.length());
				}else if(list.contains("����")&&list.contains("Ԫ")) {
					jkje=list.substring(list.indexOf("��")+1,list.length());
				}
			}
		jrjkModel.setJkje(jkje);
		//System.out.println("����"+jkje);
		/*���н���γ�ȡ*/
		/*for(String it:cpjgdnr){
		 if(it.contains("����")&&it.contains("ԭ��")
				    &&it.contains("��")&&it.contains("���")
				    &&it.contains("Ԫ")){
					 jkje=it.substring(it.indexOf("��")+1,it.indexOf("Ԫ"));
				 }
				 else if(it.contains("����")&&it.contains("ԭ��")
						    &&it.contains("��")&&it.contains("����")
						    &&it.contains("Ԫ")){
					      jkje=it.substring(it.indexOf("��")+1,it.indexOf("Ԫ"));
					      }break;
			 }
		jrjkModel.setJkje(jkje);*/
		//System.out.println("���н���ν���"+jkje);
		//�����������
		String jkqx="δ�ἰ";
		for(String list:cmssSplit2) {
			if(list.contains("�������")||list.contains("��������")||list.contains("��������")) {
				jkqx=list.substring(list.indexOf("Ϊ")+1,list.length());
				break;
			}
		}
		jrjkModel.setJkqx(jkqx);
		//System.out.println("������ޣ�"+jkqx);
		//������ʼʱ��
	    	String jkkssj="δ�ἰ";
	    for(String list:cmssSplit2){
	    		if(list.contains("����ͬ")||list.contains("�����ͬ")) {
			  if(list.contains("��")&&list.contains("��")){   
			   jkkssj=list.substring(list.indexOf("��")+1,list.indexOf("��"));
			   }else if(list.contains("��")&&list.contains("��")) {
				   jkkssj=list.substring(list.indexOf("��")+1,list.indexOf("��"));
			   }
			    break;
			   }}
	   jrjkModel.setJkkssj(jkkssj);
	   //System.out.println("��ʼʱ�䣺"+jkkssj);
	 
	    	//����������ʱ��
		String jkjssj="δ�ἰ";
		for(String list:cmssSplit2){
			if(list.contains("�������")||list.contains("��������")||list.contains("��������")) {
				jkjssj="δ�ἰ";
			}else if(list.contains("����ͬ")||list.contains("�����ͬ")) {
			  if(list.contains("��")&&list.contains("ֹ")){   
			   jkjssj=list.substring(list.indexOf("��")+1,list.indexOf("ֹ"));
			   }else if(list.contains("��")) {
				   jkjssj=list.substring(list.indexOf("��")+1,list.lastIndexOf("��")+1);
			   }
			    break;
			   }}
		jrjkModel.setJkjssj(jkjssj);
		//System.out.println("������ʱ�䣺"+jkjssj);
		//���������Ϣ
		String jklx="δ�ἰ";
		for(String list:cmssSplit2) {
			if(list.contains("�������")||list.contains("��������")){
				jklx=list.substring(list.indexOf("Ϊ")+1,list.length());
			}
		}
		jrjkModel.setJklx(jklx);
		//System.out.println("�����Ϣ��"+jklx);
		
		//�����Ƿ�Լ�����ڻ�����Ϣ
	    String sfydyqhklx="��";
		for(String list:cmssSplit2) {
			if(list.contains("����")) {
				if(list.contains("��Ϣ")) {
					sfydyqhklx="��";
					}
			}
		}
		jrjkModel.setSfydyqhklx(sfydyqhklx);
		//System.out.println("�Ƿ�Լ�����ڻ�����Ϣ��"+sfydyqhklx);
		//�����Ƿ�Լ�����ڻ����
		String sfydyqhkfl="��";
		for(String list:cmssSplit2) {
			if(list.contains("����")) {
				if(list.contains("����")) {
				    sfydyqhkfl="��";
				}
			}
		}
		jrjkModel.setSfydyqhkfl(sfydyqhkfl);
		//System.out.println("�Ƿ�Լ�����ڻ������"+sfydyqhkfl);
		//�����Ƿ�Լ�����ڻ���ΥԼ��
		String sfydyqhkwyj="��";
		for(String list:cmssSplit2) {
			if(list.contains("����")) {
				if(list.contains("ΥԼ��")) {
				    sfydyqhkwyj="��";
				}
			}
		}
		jrjkModel.setSfydyqhkwyj(sfydyqhkwyj);
		//System.out.println("�Ƿ�Լ�����ڻ���ΥԼ��"+sfydyqhkwyj);
		//���������Ƿ񳥻�����
		String jkhsfchbj="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("����"))
			if((list.contains("δ")&&list.contains("����"))||list.contains("δ��֧��")) {
				jkhsfchbj="��";
			}else {
				jkhsfchbj="��";
			}
		}
		jrjkModel.setJkhsfchbj(jkhsfchbj);
		//System.out.println("�����Ƿ񳥻�����"+jkhsfchbj);
		//�����Ƿ񳥻���Ϣ
		String jkhsfchlx="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("��Ϣ"))
			if(list.contains("δ")&&(list.contains("����")||list.contains("����"))||list.contains("δ��֧��")) {
				jkhsfchbj="��";
			}else {
				jkhsfchbj="��";
			}
		}
		jrjkModel.setJkhsfchlx(jkhsfchlx);
		//System.out.println("�����Ƿ񳥻���Ϣ��"+jkhsfchlx);
		//���������Ƿ񳥻���Ϣ
		String jkhsfchfx="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("��Ϣ"))
			if((list.contains("δ")&&list.contains("����"))||list.contains("δ��֧��")) {
				jkhsfchfx="��";
			}else {
				jkhsfchfx="��";
			}
		}
		jrjkModel.setJkhsfchfx(jkhsfchfx);
		//System.out.println("�����Ƿ񳥻���Ϣ��"+jkhsfchfx);
		//���������Ƿ񳥻�����
				String jkhsfchfl="δ�ἰ";
				for(String list:cmssSplit) {
					if(list.contains("����"))
					if((list.contains("δ")&&list.contains("����"))||list.contains("δ��֧��")) {
						jkhsfchfl="��";
					}else {
						jkhsfchfl="��";
					}
				}
				jrjkModel.setJkhsfchfl(jkhsfchfl);
				//System.out.println("�����Ƿ񳥻�������"+jkhsfchfl);
		//�����Ƿ���ڵ���
		String sfczdb="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("��֤")||list.contains("��Ѻ")||list.contains("��Ѻ")){
			sfczdb="��";
		}else {
			sfczdb="��";
		}
			}
		jrjkModel.setSfczdb(sfczdb);
		//System.out.println("�Ƿ���ڵ�����"+sfczdb);
		/*
		 *���ݵ�����ʽ����
		 */
		//��֤
		//������֤��ͬ����
		String bzhtlx="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("��֤��ͬ")) {
				if(list.contains("��߶�")) {
					bzhtlx="��߶֤��ͬ";
				}else {
					bzhtlx="��֤��ͬ";
				}
			}
		}
		jrjkModel.setBzhtlx(bzhtlx);
		//System.out.println("��֤��ͬ���ͣ�"+bzhtlx);
		//������֤��ʽ
		String bzfs="δԼ����Լ������";
		//�����жϵ�����ʽ���б�֤
		if(bzhtlx.contains("��֤��ͬ")) {
		for(String list:cmssSplit2) {
			if(list.contains("�������α�֤")||list.contains("������֤����")) {
			     bzfs="�������α�֤";
			}else {
				bzfs="һ�㱣֤";
			}	
			}
		}
		jrjkModel.setBzfs(bzfs);
		//System.out.println("��֤��ʽ��"+bzfs);
		//������֤��Χ
		String bzfw="δ�ἰ";
		if(bzhtlx.contains("��֤��ͬ")) {
			for(String list:cmssSplit2) {
				if(list.contains("��֤��Χ")) {
					bzfw=list.substring(list.indexOf("Χ")+1,list.length());	
				}
			}
		}
		jrjkModel.setBzfw(bzfw);
		//System.out.println("��֤��Χ��"+bzfw);
		//������֤�ڼ�
		String bzqj="δ�ἰ";
		for(String list:cmssSplit2) {
			if(list.contains("��֤�ڼ�")) {
				if(list.contains("Ϊ")) {
					bzqj=list.substring(list.indexOf("Ϊ"),list.length());
				}else if(list.contains("��")) {
					bzqj=list.substring(list.indexOf("��"),list.length());
				}
			}break;
		}
		jrjkModel.setBzqj(bzqj);
		//System.out.println("��֤�ڼ䣺"+bzqj);
		//������֤��������������
		//������Ѻ��ͬ����
		String dyhtlx="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("��Ѻ��ͬ")) {
				if(list.contains("��߶�")) {
					dyhtlx="��߶��Ѻ��ͬ";
				}else {
					dyhtlx="��Ѻ��ͬ";
				}
			}
		}
		jrjkModel.setDyhtlx(dyhtlx);
		//System.out.println("��Ѻ��ͬ���ͣ�"+dyhtlx);
		//������Ѻ������
		String dywlx="δ�ἰ";
		if(dyhtlx.contains("��Ѻ��ͬ")) {
		for(String list:cmssSplit) {
			if(list.contains("����")||list.contains("����")) {
				if(list.contains("�ڽ�")) {
					dywlx="�ڽ�������";
				}else {
					dywlx="������";
				}
			}else {
				dywlx="����";
			}
		}}
		jrjkModel.setDywlx(dywlx);
		//System.out.println("��Ѻ�����ͣ�"+dywlx);
		//������Ѻ�����Ƿ�Ǽ�
		//������Ѻ����״
		//������Ѻ����
		String zylx="δ�ἰ";
		for(String list:cmssSplit) {
			if(list.contains("��Ѻ")) {
				if(list.contains("������Ѻ")) {
					zylx="������Ѻ";
				}else if(list.contains("Ȩ��")||list.contains("��Ȩ")) {
					zylx="Ȩ����Ѻ";
				}
			}		
				}
		jrjkModel.setZylx(zylx);
		//System.out.println("��Ѻ���ͣ�"+zylx);
		//���������Ƿ񽻸���Ȩ��
		//������Ȩ���Ƿ�����ʹȨ����Ȩ
		//����Լ����ͬ���е�
		//����Э���ϽԼ������
		//�Ƿ�Լ���ٲ�
		//�Ƿ�Լ��׷��ծȨ���õĳе�
		//�Ƿ���������ط��õ�Լ��
		String sfyqtxgfydyd="��";
		for(String list:cmssSplit2) {
			if(list.contains("����")&&list.contains("����")){
				sfyqtxgfydyd="��";
			}break;
		}
		jrjkModel.setSfyqtxgfydyd(sfyqtxgfydyd);
		//System.out.println("�Ƿ���������ط��õ�Լ����"+sfyqtxgfydyd);
		//�Ƿ�ǩ����չ�ں�ͬ
		String sfqdgzqht="��";
		for(String list:cmssSplit2) {
			if(list.contains("չ��")&&(list.contains("��ͬ")||list.contains("Э��"))) {
				sfqdgzqht="��";
			}break;
		}
		jrjkModel.setSfqdgzqht(sfqdgzqht);
		//System.out.println("�Ƿ�ǩ����չ�ں�ͬ��"+sfqdgzqht);
		//չ�ں�ͬ����Լ������Ŀ
		//�Ƿ�ͬʱԼ���˱����ﱣ
		//���չ�ں�ͬԼ�������Ƿ�õ���֤�˵�ͬ��
		//����ծ���Ƿ����ծ����ת��ծ������
		//ת����Ϊ�Ƿ����ñ�֤��ͬ��
		//Ҫ����ż�е����ε�����
		//�����Ŀǰ�Ļ���״��
		//ԭ������ǰ�Ƿ��д���
		String ygqsqsfyct="δ�ἰ";
		for(String list:cmssSplit2) {
			if(list.contains("����")) {
				if(list.contains("�绰")) {
					ygqsqsfyct="�绰����";
				}else if(list.contains("����")) {
					ygqsqsfyct="�������";
				}else if(list.contains("����")) {
					ygqsqsfyct="�������ر���";
				}else if(list.contains("����")) {
					ygqsqsfyct="��������";
				}else {
					ygqsqsfyct="����";
				}
			}break;
		}
		jrjkModel.setYgqsqsfyct(ygqsqsfyct);
		//System.out.println("ԭ������ǰ�Ƿ��д��֣�"+ygqsqsfyct);
		//Ŀǰ�Ƿ������ϵ������
		
		return jrjkModel;
	}
}
