
package nju.software.classify;

import nju.software.preProcess.PreClassifyAH;
import nju.software.preProcess.PreClassifyLaws;
import nju.software.vo.DocType;
import nju.software.vo.TemplateLawVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.util.List;

/**
 * Created by zhuding on 2018/4/4.
 */
public class GXClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.GX_PREFIX, ah, wsModel);
    }

    /**
     * ���²ö���(������Ͻ�ö����߰�����)
     * @return
     */
    protected boolean isBFGXCDSS(){
        boolean wccd = cpjg.contains("��������") && cpjg.contains("ά��ԭ�ö�");
        String pattern1 = "��"+CHINESE+"��Ͻ";
        String pattern2 = "����"+CHINESE+"����Ժ����";
        boolean cxcd = matchCpjg(pattern1) || matchCpjg(pattern2);
        return wccd || cxcd;
    }

    /**
     * ���²ö���(��ϽȨ������)
     * @return
     */
    protected boolean isGXQYY(){
        String pattern1 = "��ϽȨ"+CHINESE+"����";
        String pattern2 = "����"+CHINESE+"����";
        String pattern3 = "����"+CHINESE+pattern1;
        return matchSsjl(pattern1) && matchCpjg(pattern2) && matchCpjg(pattern3);
    }

    /**
     * ���²ö���(�ϼ���Ժ�ƽ��¼���Ժ������)
     * @return
     */
    protected boolean isSJYJXJ(){
        String pattern1 = "����"+CHINESE+"��׼";
        String pattern2 = "��"+CHINESE+"����";
        return matchCpgc(pattern1) && matchCpjg(pattern2);
    }

    /**
     * ���²ö���(����������Ժ����ָ����Ͻ������)
     * @return
     */
    protected boolean isSBQZDGX(){
        String pattern1 = "����"+CHINESE+"ָ����Ͻ";
        String pattern2 = "��"+CHINESE+"����";
        return matchSsjl(pattern1) && matchCpjg(pattern2) && ssjl.indexOf("һ��") == ssjl.lastIndexOf("һ��");
    }

    /**
     * ���²ö���(С�����ϳ����ϽȨ������)
     * @return
     */
    protected boolean isXSSSGXYY(){
        String pattern1 = "��ϽȨ"+CHINESE+"����";
        String pattern2 = "�������.*����"+CHINESE+"����";
        String pattern3 = "����"+CHINESE+"����";
        return matchSsjl(pattern1) && (matchCpjg(pattern2) || matchCpjg(pattern3));
    }

    /**
     * ���²ö���(�������ἶ��Ͻ��)
     * @return
     */
    protected boolean isBQTJGX(){
        return (cpgc.contains("�ἶ��Ͻ") || ssjl.contains("�ἶ��Ͻ")) && cpjg.contains("�ɱ�Ժ����");
    }

    /**
     * ���²ö���(��ְȨ�ἶ��Ͻ��)
     * todo ����
     * @return
     */
    protected boolean isZQTJGX(){
        return (cpgc.contains("�ἶ��Ͻ") || ssjl.contains("�ἶ��Ͻ")) && cpjg.contains("�ɱ�Ժ����");
    }

    /**
     * ���²ö���(��ְȨ���͹�Ͻ��)
     * @return
     */
    protected boolean isZQYSGX(){
        String pattern2 = "����"+CHINESE+"����";
        return matchCpjg(pattern2);
    }

    /**
     * ���²ö���(���ϽȨ���鱨��ָ����Ͻ������)
     * @return
     */
    protected boolean isBQZDGX(){
        String pattern1 = "��ϽȨ"+CHINESE+"����";
        String pattern2 = "ָ����Ͻ";
        return matchSsjl(pattern1) && matchSsjl(pattern2) && cpjg.contains("����") &&  ssjl.indexOf("һ��") != ssjl.lastIndexOf("һ��");
    }

//
    /**
     * ���²ö���(�й�ϽȨ����Ժ����ָ����Ͻ������)
     * @return
     */
    protected boolean isYGXQBQZD(){
        String pattern2 = "��"+CHINESE+"����";
        return matchCpjg(pattern2);
    }
}
