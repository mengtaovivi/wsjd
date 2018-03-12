package nju.software.wsjx.parserule.msysFactorParseRule;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.service.model.msysFactorModel.JdcjtsgModel;
import nju.software.wsjx.service.model.msysFactorModel.MjjdModel;

public class MjjdParseRule {
	/**
	 * ��ȡ�������Ҫ�أ����������Ҫ��model
	 * @param wsAnalyse ��������������ֵķֶ���Ϣ���Լ�ÿ�εľ�������
	 * @param wsModel �Ѿ���ȡ��ɵ�����model
	 * @return
	 */
	public MjjdModel getMjjdModel(WsAnalyse wsAnalyse,WsModel wsModel){
		
		MjjdModel mjjdModel=new MjjdModel();
		
		String ygscd=wsModel.getWsajjbqkModel().getYgscd();//����ԭ���߳ƶ�
		List<String> cmssd=wsModel.getWsajjbqkModel().getCmssd();//���ò�����ʵ��
		List<String> cpjgd=wsModel.getWscpjgModel().getCpjgnr();
		//List<String> cpjg=wsModel.getWscpjgModel().getPjjgList();
		//List<String> cpjgd=wsAnalyse.getCpjg();//���ò��н����
		
		String ygscdnr[]=ygscd.split("��");
		//List<String> ygscdlist=Arrays.asList(ygscds);//new ArrayList<String>();//��Ž�ȡ֮���ԭ���߳ƶ�
		//System.out.println(ygscdlist);
		
		String cmssds=cmssd.toString();
		String cmssdnr[]=cmssds.split("��");
		//List<String> cmssdnr=cmssd;//new ArrayList<String>();//��Ž�ȡ֮��Ĳ�����ʵ��
		
		String cpjgs=cpjgd.toString();
		String cpjgdnr[]=cpjgs.split("��|��");
		
		
		 String dsrzjdgx="";//1��������֮��Ĺ�ϵ���ݲ���ȡ��
		 String htclsj="";//2����ͬ����ʱ��
		 String jkje="";//3�������
		 String htkssj="";//4����ͬ��ʼʱ��
		 String htjssj="";//5����ͬ����ʱ��
		 List<String> bjhkqk;//6�����𻹿����
		 List<String> lxhkqk=new ArrayList<>();//7����Ϣ�������
		 List<String> dbr = new ArrayList<>();//8��������
		 String dbhtnr="";//9��������ͬ����
		 String dbje="";//10���������
		 String sfyfqgtzw="";//11���Ƿ��з��޹�ͬծ��
		 String myjkr="";//12����������(�ݲ���ȡ)
		 
		 
		 int index=0;
		
		/**
		 * 1��������֮��Ĺ�ϵ���ݲ���ȡ��
		 */
		
		 
		 /**
		 * 2����ͬ����ʱ��(��ʱ�����ǩ����ͬʱ��)
		 */
		 //ԭ���߳ƶ�
		 for(int i=0;i<ygscdnr.length;i++){
			 if(ygscdnr[i].contains("��")&&ygscdnr[i].contains("��")
			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("ǩ��")
			    &&ygscdnr[i].contains("���")){
				 index=i;
				 htclsj=ygscdnr[i].substring(ygscdnr[i].indexOf("2"),ygscdnr[i].indexOf("��")+1);
				 mjjdModel.setHtclsj("ԭ���߳ƶΣ�"+htclsj);
			 }
		 }
		 
		 //������ʵ��
		 for(int i=0;i<cmssdnr.length;i++){
			 if(cmssdnr[i].contains("��")&&cmssdnr[i].contains("��")
			    &&cmssdnr[i].contains("��")&&cmssdnr[i].contains("ǩ��")
			    &&cmssdnr[i].contains("���")){
				 index=i;
				 htclsj=ygscdnr[i].substring(ygscdnr[i].indexOf("2"),ygscdnr[i].indexOf("��")+1);
				 mjjdModel.setHtclsj("������ʵ�Σ�"+htclsj); 
			 } 
		 }
		 
		/**
		 * 3��������ʱ������о��������ı��𡣲���ԭ���߳ƶεĽ���
		 */
//		//ԭ���߳ƶ�
//		 for(int i=0;i<ygscdnr.length;i++){
//			 if(ygscdnr[i].contains("����")&&ygscdnr[i].contains("��ԭ��")
//			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("��Ԫ")){
//				 index=i;
//				 jkje=ygscdnr[i].substring(ygscdnr[i].indexOf("��")+1,ygscdnr[i].indexOf("Ԫ")+1);
//				 mjjdModel.setJkje("ԭ���߳ƶΣ�"+jkje);
//			 }
//		 }
//		 
//		//������ʵ��
//		 for(int i=0;i<ygscdnr.length;i++){
//			 if(ygscdnr[i].contains("����")&&ygscdnr[i].contains("ԭ��")
//			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("��Ԫ")){
//				 index=i;
//				 jkje=ygscdnr[i].substring(ygscdnr[i].indexOf("��")+1,ygscdnr[i].indexOf("Ԫ")+1);
//				 mjjdModel.setJkje("������ʵ�Σ�"+jkje);
//			 }
//		 }
		 //���н����
		 for(int i=0;i<cpjgdnr.length;i++){
			 if(cpjgdnr[i].contains("����")&&cpjgdnr[i].contains("ԭ��")
			    &&cpjgdnr[i].contains("��")&&cpjgdnr[i].contains("���")
			    &&cpjgdnr[i].contains("Ԫ")){
				 index=i;
				 jkje=cpjgdnr[i].substring(cpjgdnr[i].indexOf("��")+1,cpjgdnr[i].indexOf("Ԫ"));
				 mjjdModel.setJkje("���н����:"+jkje);
			 }
			 else 
				 if(cpjgdnr[i].contains("����")&&cpjgdnr[i].contains("ԭ��")
					    &&cpjgdnr[i].contains("��")&&cpjgdnr[i].contains("����")
					    &&cpjgdnr[i].contains("Ԫ")){
				      index=i;
				      jkje=cpjgdnr[i].substring(cpjgdnr[i].indexOf("��")+1,cpjgdnr[i].indexOf("Ԫ"));
				      mjjdModel.setJkje("���н����:"+jkje);
			 }
		 }
		 
		/**
		 * 4����ͬ��ʼʱ��
		 * 5����ͬ����ʱ��
		 */
		//ԭ���߳ƶ�
		 for(int i=0;i<ygscdnr.length;i++){
			 if(ygscdnr[i].contains("�������")&&ygscdnr[i].contains("��")
			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("��")
			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("��")){
				 index=i;
				 htkssj=ygscdnr[i].substring(ygscdnr[i].indexOf("��")+1,ygscdnr[i].indexOf("��")+1);
				 mjjdModel.setHtkssj("ԭ���߳ƶΣ�"+htkssj);
				 htjssj=ygscdnr[i].substring(ygscdnr[i].indexOf("��")+1,ygscdnr[i].lastIndexOf("��")+1);
				 mjjdModel.setHtjssj("ԭ���߳ƶ�"+htjssj);
			 }
		 }
		 
		//������ʵ��
		 for(int i=0;i<ygscdnr.length;i++){
			 if(ygscdnr[i].contains("�������")&&ygscdnr[i].contains("��")
			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("��")
			    &&ygscdnr[i].contains("��")&&ygscdnr[i].contains("��")){
				 index=i;
				 htkssj=ygscdnr[i].substring(ygscdnr[i].indexOf("��")+1,ygscdnr[i].indexOf("��")+1);
				 mjjdModel.setHtkssj("������ʵ�Σ�"+htkssj);
				 htjssj=ygscdnr[i].substring(ygscdnr[i].indexOf("��")+1,ygscdnr[i].lastIndexOf("��")+1);
				 mjjdModel.setHtjssj("������ʵ��"+htjssj);
			 }
		 }
		/**
		 * 6�����𻹿����
		 */
	   //�о������
//		 for(int i=0;i<cpjgdnr.length;i++){
//			 if(){
//				 
//			 }
//		 }
		 
		
		/**
		 * 7����Ϣ�������
		 */
	 //�о������
		 for(int i=0;i<cpjgdnr.length;i++){
			 if(cpjgdnr[i].contains("֧��")&&cpjgdnr[i].contains("��Ϣ")&&cpjgdnr[i].contains("Ԫ")){
				 if(cpjgdnr[i].contains("��ȥ"))
				 {
					 index=i;
					String lxhkqknr=cpjgdnr[i].substring(cpjgdnr[i].indexOf("ȥ")+1,cpjgdnr[i].indexOf("Ԫ")+1);
					 lxhkqk.add(lxhkqknr);
					 mjjdModel.setLxhkqk(lxhkqk);
				 }
				 if(cpjgdnr[i].contains("�۳�"))
				 {
					 index=i;
					String lxhkqknr=cpjgdnr[i].substring(cpjgdnr[i].indexOf("Ϣ")+1,cpjgdnr[i].indexOf("Ԫ")+1);
					 lxhkqk.add(lxhkqknr);
					 mjjdModel.setLxhkqk(lxhkqk);
				 }
			 }
		 }
		 
		/**
		 * 8��������
		 */
		//�о������
		 for(int i=0;i<cpjgdnr.length;i++){
			 if(cpjgdnr[i].contains("�е�")&&cpjgdnr[i].contains("����")&&cpjgdnr[i].contains("����")){
				 index=i;
				 String dbrnr=cpjgdnr[i].substring(cpjgdnr[i].indexOf("��")+1,cpjgdnr[i].indexOf("��"));
				 dbr.add(dbrnr);
				 mjjdModel.setDbr(dbr);
			 }
		 }
		/**
		 * 9����ͬ����
		 */
	  //ԭ���߳ƶ�
		 
	 //������ʵ��
		/**
		 * 10���������
		 */
	 //
		/**
		 * 11���Ƿ��з��޹�ͬծ��
		 */
	 //ԭ���߳ƶ�
		for(int i=0;i<ygscdnr.length;i++){
			if(ygscdnr[i].contains("���޹�ϵ")&&ygscdnr[i].contains("��ͬ")
			   &&ygscdnr[i].contains("ծ��")){
				index=i;
				sfyfqgtzw="��";
				mjjdModel.setSfyfqgtzw(sfyfqgtzw);			
			}
			else mjjdModel.setSfyfqgtzw("��");
		}
	//������ʵ��
		for(int i=0;i<cmssdnr.length;i++){
		if(cmssdnr[i].contains("���޹�ϵ")&&cmssdnr[i].contains("��ͬ")
				   &&cmssdnr[i].contains("ծ��")){
					index=i;
					sfyfqgtzw="��";
					mjjdModel.setSfyfqgtzw(sfyfqgtzw);			
				}
				else mjjdModel.setSfyfqgtzw("��");
		}
		
		
		return mjjdModel;
		
		
}
}
