package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class BQClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.BQ_PREFIX, ah, wsModel);
    }


    /**
     * ���²ö���(�ٲ��вƲ���ȫ��)
     * @return
     */
    private boolean isZCZCCBQ(){
        String p1 = "��"+CHINESE+"�ٲ�ίԱ������Ʋ���ȫ";
        String p2 = "(���|��Ѻ|����)"+CHINESE+"��";
        return matchSsjl(p1) && matchCpjg(p2);
    }

    /**
     * ���²ö���(��ְȨ���ϱ�ȫ��)
     * @return
     */
    private boolean isYZQSSBQ(){
        String p1 = "��ְȨ";
        String p2 = "���ϱ�ȫ";
        return matchCpgc(p1) && matchCpgc(p2) || matchCpjg(p1) && matchCpjg(p2);
    }

    /**
     * ���²ö���(��ȫ��������ִ�вö�������)
     * @return
     */
    private boolean isCDFY(){
        String p1 = "����";
        String p2 = "�Ʋ���ȫ|��Ϊ��ȫ|����ִ��";
        String p3 = "����"+CHINESE+"��������";
        String p4 = "����.*(��ȫ|����ִ��)";
        return matchSsjl(p1) && matchSsjl(p2) && (matchCpjg(p3) || matchCpjg(p4));
    }


     /**
     * ���²ö���(����ִ����)
     * @return
     */
    private boolean isXYZX(){
        String p1 = "��������ִ��";
        return matchSsjl(p1);
    }

    /**
     * ���²ö���(�����ȫ��)
     * @return
     */
    private boolean isBGBQ(){
        String p1 = "��ֵ�����Ʋ�";
        String p2 = "�����ȫ�����";
        String p3 = "���.*(���|��Ѻ|����)";
        String p4 = "����";
        return matchCpjg(p3) && matchCpjg(p4) && matchSsjl(p1) && matchSsjl(p2);
    }

    /**
     * ���²ö���(ִ��ǰ��ȫ��)
     * @return
     */
    private boolean isZXQBQ(){
        String p1 = "�Ѿ���Ч.*���뱣ȫ";
        return matchSsjl(p1);
    }
    /**
     * ���²ö���(�����ȫ��)
     * @return
     */
    private boolean isJCBQ(){
        String p1 = "���������ȫ";
        String p2 = "�����.*��.*";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * ���²ö���(��ǰ��Ϊ��ȫ��)
     * @return
     */
    private boolean isSQXWBQ(){
        String p1 = "������ǰ��Ϊ��ȫ";
        return matchSsjl(p1);
    }
    /**
     * ���²ö���(��ǰ�Ʋ���ȫ��)
     * @return
     */
    private boolean isSQCCBQ(){
        String p1 = "������ǰ�Ʋ���ȫ";
        String p2 = "(���|��Ѻ|����)";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * ���²ö���(������Ϊ��ȫ��)
     * @return
     */
    private boolean isSSXWBQ(){
        String p1 = "������Ϊ��ȫ";
        return matchSsjl(p1);
    }
    /**
     * ���²ö���(���ϲƲ���ȫ��)
     * @return
     */
    private boolean isSSCCBQ(){
        String p1 = "����Ʋ���ȫ";
        String p2 = "(���|��Ѻ|����)";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * ���²ö���(���ر�ȫ��������ִ��������)
     * @return
     */
    private boolean isBHSQ(){
        String p1 = "����(�Ʋ���ȫ|��Ϊ��ȫ|����ִ��)";
        String p2 = "����"+CHINESE+"����";
        return matchSsjl(p1) && matchCpjg(p2);
    }
}
