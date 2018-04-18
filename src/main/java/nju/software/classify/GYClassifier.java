package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/18.
 */
public class GYClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.GY_PREFIX, ah,  wsModel);
    }


    /**
     * ���²ö���(�������ϲ�׼����������)
     * @return
     */
    private boolean isBZXCH(){
        String p1 = "��������";
        String p2 = "���볷������";
        String p3 = "��׼��"+CHINESE+"��������";
        return matchSsjl(p1) && matchSsjl(p2) && matchCpjg(p3);
    }
    /**
     * ���²ö���(��ͬһ��Ȩ��Ϊ�������������ϲ���������)
     * @return
     */
    private boolean isLXTQBYSL(){
        String p1 = "�Ѿ���������Ч��";
        String p2 = "��������";
        String p3 = "��������";
        return matchCpgc(p1) && matchCpgc(p2) && matchCpjg(p3);

    }
    /**
     * ���²ö���(������Ⱦ������̬�ƻ���������׼����������)
     * @return
     */
    private boolean isHJWRZXCH(){
        String p1 = "��������";
        String p2 = "���볷������";
        String p3 = "׼��"+CHINESE+"��������";
        String p4 = "��׼��";
        return matchSsjl(p1) && matchSsjl(p2) && matchCpjg(p3) && !matchCpjg(p4);
    }
}
