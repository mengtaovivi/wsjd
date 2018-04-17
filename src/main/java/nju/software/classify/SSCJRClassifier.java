package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class SSCJRClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.SSCJR_PREFIX, ah, wsModel);
    }

    /**
     * ���²ö���(�����������)
     * @return
     */
    private boolean isBGDSRY(){
        String pattern1 = "������������";
        String pattern2 = "׼��.*�μ�����.*�˳�����";
        return matchSsjl(pattern1) && matchCpjg(pattern2);
    }


    /**
     * ���²ö���(δ�μӵǼǵ�Ȩ����������Ч�о���ö���)
     * @return
     */
    private boolean isWCJDJ(){
        String pattern1 = "�����ڶ�.*������δȷ��";
        String pattern2 = "֪ͨȨ����.*�Ǽ�";
        return matchCpgc(pattern1) || matchCpgc(pattern2);
    }
}
