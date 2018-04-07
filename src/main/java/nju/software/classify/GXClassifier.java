package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.util.List;

/**
 * Created by zhuding on 2018/4/4.
 */
public class GXClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel) {
        this.ssjl = wsModel.getWsssjlSegment();
        this.cpjg = wsModel.getWscpjgSegment();
        this.cpgc = wsModel.getWscpfxgcSegment();
        List<String> esList = DocType.getTypeList(BaseClassifier.GX_PREFIX);
        Class<? extends GXClassifier> clz = getClass();
        return getType(esList, clz, this);
    }

    @Override
    public String getParseRuleName() {
        return ParseMap.getInstance().getParseClassName("��Ͻ");
    }

    /**
     * ���²ö���(������Ͻ�ö����߰�����)
     * @return
     */
    private boolean isBFGXCDSS(){
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
    private boolean isGXQYY(){
        String pattern1 = "��ϽȨ"+CHINESE+"����";
        String pattern2 = "����"+CHINESE+"����";
        String pattern3 = "����"+CHINESE+pattern1;
        return matchSsjl(pattern1) && matchCpjg(pattern2) && matchCpjg(pattern3);
    }

    /**
     * ���²ö���(�ϼ���Ժ�ƽ��¼���Ժ������)
     * @return
     */
    private boolean isSJYJXJ(){
        String pattern1 = "����"+CHINESE+"��׼";
        String pattern2 = "��"+CHINESE+"����";
        return matchCpgc(pattern1) && matchCpjg(pattern2);
    }

    /**
     * ���²ö���(����������Ժ����ָ����Ͻ������)
     * @return
     */
    private boolean isSBQZDGX(){
        String pattern1 = "����"+CHINESE+"ָ����Ͻ";
        String pattern2 = "��"+CHINESE+"����";
        return matchSsjl(pattern1) && matchCpjg(pattern2);
    }

    /**
     * ���²ö���(С�����ϳ����ϽȨ������)
     * @return
     */
    private boolean isXSSSGXYY(){
        String pattern1 = "��ϽȨ"+CHINESE+"����";
        String pattern2 = "�������.*����"+CHINESE+"����";
        String pattern3 = "����"+CHINESE+"����";
        return matchSsjl(pattern1) && (matchCpjg(pattern2) || matchCpjg(pattern3));
    }

    /**
     * ���²ö���(�������ἶ��Ͻ��)
     * @return
     */
    private boolean isBQTJGX(){
        return (cpgc.contains("�ἶ��Ͻ") || ssjl.contains("�ἶ��Ͻ")) && cpjg.contains("�ɱ�Ժ����");
    }

    /**
     * ���²ö���(��ְȨ�ἶ��Ͻ��)
     * todo ����
     * @return
     */
    private boolean isZQTJGX(){
        return (cpgc.contains("�ἶ��Ͻ") || ssjl.contains("�ἶ��Ͻ")) && cpjg.contains("�ɱ�Ժ����");
    }

    /**
     * ���²ö���(��ְȨ���͹�Ͻ��)
     * @return
     */
    private boolean isZQYSGX(){
        String pattern2 = "����"+CHINESE+"����";
        return matchCpjg(pattern2);
    }

    /**
     * ���²ö���(���ϽȨ���鱨��ָ����Ͻ������)
     * @return
     */
    private boolean isBQZDGX(){
        String pattern1 = "��ϽȨ"+CHINESE+"����";
        String pattern2 = "ָ����Ͻ";
        return matchSsjl(pattern1) && matchSsjl(pattern2) && cpjg.contains("����");
    }

//
    /**
     * ���²ö���(�й�ϽȨ����Ժ����ָ����Ͻ������)
     * @return
     */
    private boolean isYGXQBQZD(){
        String pattern2 = "��"+CHINESE+"����";
        return matchCpjg(pattern2);
    }
}
