package nju.software.wsjx.util;

import java.util.HashMap;

/**
 * Created by zhx on 2016/12/5.
 */
public class XzglfwSelector {
    public static String  selectXzglfw(String dwmc) {
        String xzglfw = null;
        HashMap<String, String[]> xzglfwmap = new HashMap();//����һ��HashMap����������Χ����������Χ��Ӧ�б������

        //������������(����)
        String[] czxzgl ={"����"};
        xzglfwmap.put("������������(����)", czxzgl);
        //��ԭ��������(��ԭ)
        String[] cyxzgl = {"��ԭ����վ"};
        xzglfwmap.put("��ԭ��������(��ԭ)",cyxzgl);
        //���й滮����(�滮)
        String[] csghgl={"�滮"};
        xzglfwmap.put("���й滮����(�滮)",csghgl);
        //�����ۺ���������(�ǹ�)
        String[] cszhxz = {"���й���","���ݹ���"};
        xzglfwmap.put("�����ۺ���������(�ǹ�)",cszhxz);
        //���罨����������
        String[] cxjsxz = {"���罨��","����"};
        xzglfwmap.put("���罨����������",cxjsxz);
        //������������(����)
        String[] xmxzgl={"ũ��","����"};
        xzglfwmap.put("������������(����)",xmxzgl);
        //��·��ͨ����(��·)
        String[] dljtgl={"����","��Ѳ��","��ͨ����","��ͨѲ��"};
        xzglfwmap.put("��·��ͨ����(��·)",dljtgl);
        //���ݵǼǹ���(���ݵǼ�)
        String[] fwdjgl={"��������","���ز�����","���ݹ���","����"};
        xzglfwmap.put("���ݵǼǹ���(���ݵǼ�)",fwdjgl);
        //���ʿ����������(�ؿ�)
        String[] dzkcxz={"����","���"};
        xzglfwmap.put("���ʿ����������(�ؿ�)",dzkcxz);
        //���ݲ�Ǩ����(��Ǩ)
        String[] fwcqgl={"��Ǩ"};
        xzglfwmap.put("���ݲ�Ǩ����(��Ǩ)",fwcqgl);
        //������������(����)
        String[] gsxzgl={"��������","����"};
        xzglfwmap.put("������������(����)",gsxzgl);
        //������������
        String[] gaxzgl={"����"};
        xzglfwmap.put("������������",gaxzgl);
        //��·��ͨ��������(��·)
        String[] gljtxz={"��������","���˹���","������ͨ","��·����","��·����"};
        xzglfwmap.put("��·��ͨ��������(��·)",gljtxz);
        //�㲥���ӵ�Ӱ��������(���)
        String[] gbdsdy={"���","�㲥","����","��Ӱ"};
        xzglfwmap.put("�㲥���ӵ�Ӱ��������(���)",gbdsdy);
        //�����ʲ���������(����)
        String[] gyzcgl={"�����ʲ�"};
        xzglfwmap.put("�����ʲ���������(����)",gyzcgl);
        //������������(����)
        String[] hgxzgl={"����"};
        xzglfwmap.put("������������(����)",hgxzgl);
        //����������������(����)
        String[] hjbhxz={"��������"};
        xzglfwmap.put("����������������(����)",hjbhxz);
        //�ƻ�������������(�ƻ�����)
        String[] jhsygl={"�ƻ�����"};
        xzglfwmap.put("�ƻ�������������(�ƻ�����)",jhsygl);
        //������������(����)
        String[] jyxzgl={"����"};
        xzglfwmap.put("������������(����)",jyxzgl);
        //��ͨ������������(��ͨ)
        String[] ytysgl={"��·����","��ͨ����","��ͨ����"};
        xzglfwmap.put("��ͨ������������(��ͨ)",ytysgl);
        //������������(����)
        String[] eduxzgl={"����","ѧУ","��ѧ","ѧԺ"};
        xzglfwmap.put("������������(����)",eduxzgl);
        //������������(����)
        String[] jrxzgl={"����","����","����"};
        xzglfwmap.put("������������(����)",jrxzgl);
        //��ó��������(��ó����ó)
        String[] jmxzgl={"ó��","����","���ù���","����ó��"};
        xzglfwmap.put("��ó��������(��ó����ó)",jmxzgl);
        //�Ͷ�����ᱣ����������(�Ͷ�����ᱣ��)
        String[] ldandsh={"������Դ����ᱣ��","�Ͷ�����","����","��ᱣ��","��ᱣ��","��ҵ����"};
        xzglfwmap.put("�Ͷ�����ᱣ����������(�Ͷ�����ᱣ��)",ldandsh);
        //��ҵ��������(��ҵ)
        String[] lyxzgl={"��ҵ"};
        xzglfwmap.put("��ҵ��������(��ҵ)",lyxzgl);
        //������������(����)
        String[] travelrxzgl={"����","����"};
        xzglfwmap.put("������������(����)",travelrxzgl);
        //������������(����)
        String[] mzxzgl={"������","����"};
        xzglfwmap.put("������������(����)",mzxzgl);
        //��Դ��������(��Դ����)
        String[] nyxzgl={"��Ȼ��","ʯ��","ú��"};
        xzglfwmap.put("��Դ��������(��Դ����)",nyxzgl);
        //ũҵ��������(ũҵ)
        String[] farmxz={"ũҵ"};
        xzglfwmap.put("ũҵ��������(ũҵ)",farmxz);
        //�̱���������(�̱�)
        String[] sbxzgl={"�̱�"};
        xzglfwmap.put("�̱���������(�̱�)",sbxzgl);
        //�����������(���)
        String[] sjxzgl={"���"};
        xzglfwmap.put("�����������(���)",sjxzgl);
        //ʳƷҩƷ��ȫ��������(ʳƷ��ҩƷ)
        String[] spypaq={"ʳƷҩƷ"};
        xzglfwmap.put("ʳƷҩƷ��ȫ��������(ʳƷ��ҩƷ)",spypaq);
        //ˮ����������(ˮ��)
        String[] slxzgl={"ˮ��","ˮ��"};
        xzglfwmap.put("ˮ����������(ˮ��)",slxzgl);
        //˰����������(˰��)
        String[] swxzgl={"˰��"};
        xzglfwmap.put("˰����������(˰��)",swxzgl);
        //˾����������(˾������)
        String[] sfxzgl={"˾��"};
        xzglfwmap.put("˾����������(˾������)",sfxzgl);
        //��·��������(��·)
        String[] tlxzgl={"��·"};
        xzglfwmap.put("��·��������(��·)",tlxzgl);
        //ͳ����������(ͳ��)
        String[] tjxzgl={"ͳ��"};
        xzglfwmap.put("ͳ����������(ͳ��)",tjxzgl);
        //������������(����)
        String[] tdxzgl={"������Դ","���ع���"};
        xzglfwmap.put("������������(����)",tdxzgl);
        //������������(����)�����ݵǼǹ���(���ݵǼ�)
        String[] tdfw={"������Դ�ͷ���"};
        xzglfwmap.put("������������(����)�����ݵǼǹ���(���ݵǼ�)",tdfw);
        //������������(����)
        String[] wsxzgl={"����","ҽ�Ʊ���","ҽ��"};
        xzglfwmap.put("������������(����)",wsxzgl);
        //�Ļ���������(�Ļ�)
        String[] whxzgl={"�Ļ�"};
        xzglfwmap.put("�Ļ���������(�Ļ�)",whxzgl);
        //�����������(���)
        String[] wjxzgl={"���"};
        xzglfwmap.put("�����������(���)",wjxzgl);
        //��������(����)
        String[] xfgl={"����"};
        xzglfwmap.put("��������(����)",xfgl);
        //���ų�����������(���š�����)
        String[] xwcbxz={"���ų���"};
        xzglfwmap.put("���ų�����������(���š�����)",xfgl);
        //��Ϣ��Ѷ��������(��Ϣ����Ѷ)
        String[] xxdxxz={"��Ϣ��"};
        xzglfwmap.put("��Ϣ��Ѷ��������(��Ϣ����Ѷ)",xxdxxz);
        //�������(���)
        String[] xzjc={"����ִ�����","���"};
        xzglfwmap.put("�������(���)",xzjc);
        //�̲�ר����������(�̲�ר��)
        String[] yczm={"�̲�ר��"};
        xzglfwmap.put("�̲�ר����������(�̲�ר��)",yczm);
        //��ҵ��������(��ҵ)
        String[] yyxzgl={"����"};
        xzglfwmap.put("��ҵ��������(��ҵ)",yyxzgl);
        //��ҵ��������(��ҵ)
        String[] fishxzgl={"��ҵ","����","ˮ��"};
        xzglfwmap.put("��ҵ��������(��ҵ)",fishxzgl);
        //����
        String[] zf={"����"};
        xzglfwmap.put("����",zf);
        //�ΰ�����(�ΰ�)
        String[] zagl={"�ɳ���"};
        xzglfwmap.put("�ΰ�����(�ΰ�)",zagl);
        //�����ල���������������
        String[] zljdyj={"�ල����"};
        xzglfwmap.put("�����ල���������������",zljdyj);
        //�����ල��������(�����ල)
        String[] zljdxz={"���������ල","�ල����"};
        xzglfwmap.put("�����ල��������(�����ල)",zljdxz);
        //ר����������(ר��)
        String[] zlxzgl={"֪ʶ��Ȩ"};
        xzglfwmap.put("ר����������(ר��)",zlxzgl);
        //��Դ��������
        String[] zyxzgl={"�����������Դ","������Դ����"};
        xzglfwmap.put("��Դ��������",zyxzgl);
        if (dwmc!=null){
            for (String key : xzglfwmap.keySet()) {
                for (String item : xzglfwmap.get(key)) {
                    if (dwmc.contains(item)) {
                        xzglfw = key;
                    }
                }
            }
            //�������
            if (dwmc.contains("����")) {
                for (String item:dljtgl) {
                    if (dwmc.contains(item)){
                        xzglfw="��·��ͨ����(��·)";
                    }
                }
                if(dwmc.contains("����")){
                    xzglfw="��������(����)";
                }
                if(dwmc.contains("�ɳ���")){
                    xzglfw="�ΰ�����(�ΰ�)";
                }
            }
            if (dwmc.contains("�滮")){
                for(String item:tdxzgl) {
                    if (dwmc.contains(item)){
                        xzglfw="������������(����)";
                    }
                }
                if(dwmc.contains("���")){
                    xzglfw="���й滮����(�滮)";
                }
            }
            if(dwmc.contains("�滮")&&dwmc.contains("����")){
                xzglfw="���й滮����(�滮)";
            }
            if(dwmc.contains("��������")&&dwmc.contains("�̱�")){
                xzglfw="�̱���������(�̱�)";
            }
            if (dwmc.contains("����")&&dwmc.contains("ҽ��")){
                xzglfw="������������(����)";
            }
            if (dwmc.contains("����")&&dwmc.contains("�ƻ�����")){
                xzglfw="�ƻ�������������(�ƻ�����)";
            }
        }

        return xzglfw;
    }
}
