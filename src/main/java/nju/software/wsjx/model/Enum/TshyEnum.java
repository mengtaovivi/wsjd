package nju.software.wsjx.model.Enum;

import java.util.HashMap;

public class TshyEnum {
	public static String selectTshy(String dwmc){
		String tshy=null;
		HashMap<String,String[]> tshymap=new HashMap();//����һ��HashMap��������ҵ��������ҵ��Ӧ�б������
		//����
		String[] bx={"����"};
		tshymap.put("����",bx);
		//����
		String[] cz={"����","������"};
		tshymap.put("����", cz);
		//�ǹ�
		String[] cg={"�ǹ�","����"};
		tshymap.put("�ǹ�", cz);
		//��ί
		String[] dw={"��ί"};
		tshymap.put("��ί", dw);
		//�ؿ�
		String[] dk={"����","����"};
		tshymap.put("�ؿ�",dk);
		//����
		String[] dl={"����","����","ˮ��վ","�ҵ�վ"};
		tshymap.put("����", dl);
		//����
		String[] dx={"����","��ͨ","��ͨ","�ƶ�","��ͨ"};
		tshymap.put("����", dx);
		//��Ժ
		String[] fy={"��Ժ"};
		tshymap.put("��Ժ", fy);
		//����
		String[] gh={"����","�����Ļ���"};
		tshymap.put("����", gh);
		//����
		String[] gs={"���̾�","������������"};
		tshymap.put("����", gs);
		//����
		String[] ga={"����","����֧��","������","����","�ɳ���","����"};
		tshymap.put("����", ga);
		//����
		String[] gt={"����"};
		tshymap.put("����", gt);
		//����
		String[] gz={"�����ʲ�","��Ȩ����"};
		tshymap.put("����", gz);
		//����
		String[] hg={"����"};
		tshymap.put("����", hg);
		//����
		String[] hb={"����","��������","��Ȼ������"};
		tshymap.put("����", hb);
		//���
		String[] kj={"���","����","����"};
		tshymap.put("���", kj);
		//����
		String[] js={"������","�ƻ�����"};
		tshymap.put("����", js);
		//�����ල
		String[] jsjd={"�����ල"};
		tshymap.put("�����ල",jsjd);
		//���Ժ
		String[] jcy={"���Ժ"};
		tshymap.put("���Ժ", jcy);
		//��ͨ����
		String[] jtjc={"��ͨ��"};
		tshymap.put("��ͨ����", jtjc);
		//����
		String[] jy={"����","��ѧ","Сѧ","����","��ѧ","ѧԺ","ѧУ","��ʦ"};
		tshymap.put("����", jy);
		//����
		String[] jr={"����","����","����","���ú�����"};
		tshymap.put("����", jr);
		//�Ͷ��籣
		String[] ldsb={"������Դ����ᱣ��","�Ͷ����","�Ͷ�����ᱣ��","��ᱣ��","������Դ������Ͷ�����"};
		tshymap.put("�Ͷ��籣", ldsb);
		//��ó
		String[] jm={"��ó","���õ���","����"};
		tshymap.put("��ó", jm);
		//��ҵ
		String[] ly={"��ҵ��","��ҵ��"};
		tshymap.put("��ҵ", ly);
		//��
		String[] mh={"��","����","����","���ú���"};
		tshymap.put("��", mh);
		//����
		String[] mz={"����","��������","��ҵ","��Ǩ����","������Ĺ����","������","�����˻����"};
		tshymap.put("����", mz);
		//ũˮ
		String[] ns={"ũˮ","ˮ��ˮ��","ˮ��"};
		tshymap.put("ũˮ",ns);
		//�˴�
		String[] rd={"�˴�"};
		tshymap.put("�˴�", rd);
		//�̼�
		String[] sj={"���뾳������߾�"};
		tshymap.put("�̼�", sj);
		//���
		String[] sji={"���"};
		tshymap.put("���", sji);
		//˰��
		String[] sw={"˰��","��˰","˰"};
		tshymap.put("˰��", sw);
		//˾������
		String[] sfxz={"˾��","��֤��"};
		tshymap.put("˾������", sfxz);
		//����
		String[] ty={"����"};
		tshymap.put("����", ty);
		//��·
		String[] tl={"��·","�г�"};
		tshymap.put("��·", tl);
		//ͳ��
		String[] tj={"ͳ��"};
		tshymap.put("ͳ��", tj);
		//��ó
		String[] wm={"����ó��","��ó"};
		tshymap.put("��ó", tj);
		//����
		String[] ws={"������"};
		tshymap.put("����", ws);
		//�Ļ�
		String[] wh={"ͼ��","�Ļ�","��������","�������"};
		tshymap.put("�Ļ�", wh);
		//���
		String[] wj={"���"};
		tshymap.put("���", wj);
		//�̲�ר��
		String[] yc={"�̲�ר��"};
		tshymap.put("�̲�ר��", yc);
		//ҩƷ����
		String[] ypgl={"ҩƷ�ල"};
		tshymap.put("ҩƷ����", ypgl);
		//ҽԺ
		String[] yy={"ҽԺ","��������"};
		tshymap.put("ҽԺ", yy);
		//�ʵ�
		String[] yd={"�ʵ�","������"};
		tshymap.put("�ʵ�", yd);
		//����
		String[] zf={"����","����ίԱ��"};
		tshymap.put("����", zf);
		//��Э
		String[] zx={"��Э"};
		tshymap.put("��Э", zx);
		for(String key:tshymap.keySet()){
			for(String item:tshymap.get(key)){
				if(dwmc.contains(item)){
					tshy=key;
				}	
			}
		}
		return tshy;
		
		
	}
}
