package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/18.
 */
public class DSRClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.DSR_PREFIX, ah, wsModel);
    }

    /**
     * ���²ö���(��ֹ���������)
     * @return
     */
    private boolean isZZZSCX(){
        String p1 = "��ɺ���ͥ.*������";
        String p2 = "����������";
        String p3 = "��ֹ����";
        String p4 = "��������";
        return matchSsjl(p1) && matchSsjl(p2) && matchCpjg(p3) && matchCpjg(p4);
    }
    /**
     * ���²ö���(�Ե����˳���֮�߲���������)
     * @return
     */
    private boolean isBYSL(){
        String p1 = "�����˳���֮��";
        String p2 = "��.*�����˳���֮��.*��������";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * ���²ö���(�����˳���֮�߲������������)
     * @return
     */
    private boolean isBRZSCX(){
        String p1 = "��δ���";
        String p2 = "��.*�ö�����";
        String p3 = "�����������";
        String p4 = "����.*����";
        return matchSsjl(p1) && matchCpgc(p2) && matchCpgc(p3) && matchCpjg(p4);
    }

}
