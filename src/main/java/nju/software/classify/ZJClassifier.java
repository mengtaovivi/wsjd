package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class ZJClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.ZJ_PREFIX, ah, wsModel);
    }

    /**
     * ���²ö���(�ٲ�ǰ֤�ݱ�ȫ��)
     * @return
     */
    private boolean isZCQZJBQ(){
        String p1 = "�ٲ�ǰ��ȫ֤��";
        String p2 = "��.*�ṩ����";
        return matchSsjl(p1) && matchSsjl(p2);
    }

    /**
     * ���²ö���(������֤���������)
     * @return
     */
    private boolean isSQSZ(){
        String p1 = "�����������";
        String p2 = "����.*����.*�ύ";
        String p3 = "��.*�ύ";
        String p4 = "����.*����";
        return matchSsjl(p1) && matchSsjl(p2) && (matchCpjg(p3) || matchCpjg(p4));
    }


    /**
     * ���²ö���(���뷵������������)
     * @return
     */
    private boolean isSQFHJDF(){
        String p1 = "����.*��������";
        String p2 = "����.*����";
        return matchSsjl(p1) && (matchCpjg(p1) || matchCpjg(p2));
    }

    /**
     * ���²ö���(���֤�ݱ�ȫ��)
     * @return
     */
    private boolean isJCZJBQ(){
        String p1 = "�����.*��ȫ��ʩ";
        return matchCpjg(p1);
    }

    /**
     * ���²ö���(��ǰ֤�ݱ�ȫ��)
     * @return
     */
    private boolean isSQZJBQ(){
        String p1 = "��ǰ��ȫ֤��";
        String p2 = "��.*�ṩ����";
        return matchSsjl(p1) && matchSsjl(p2);
    }


    /**
     * ���²ö���(����֤�ݱ�ȫ��)
     * @return
     */
    private boolean isSSZJBQ(){
        String p1 = "���뱣ȫ֤��";
        String p2 = "��.*�ṩ����";
        return matchSsjl(p1) && matchSsjl(p2);
    }
}
