package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by zhuding on 2018/4/18.
 */
public class SSFYClassifier extends BaseClassifier{

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.SSFY_PREFIX, ah, wsModel);
    }


    /**
     * ���²ö���(δ�������������Ѱ��������ߴ�����)
     * @return
     */
    private boolean isWBJACH(){
        String p1 = "�ʹﲹ��.*֪ͨ";
        String p2 = "δ(����|���)*����";
        String p3 = "��.*��������";
        return (matchSsjl(p1) || matchCpgc(p1)) && (matchSsjl(p2) || matchCpgc(p2)) && matchCpjg(p3);
    }
    /**
     * ���²ö���(δԤ�����������Ѱ��������ߴ�����)
     * @return
     */
    private boolean isWYJACH(){
        String p1 = "�ʹｻ��.*֪ͨ";
        String p2 = "(δ��.*Ԥ��)|(����(��|��|��).*δ����׼)";
        String p3 = "��.*��������";
        return (matchSsjl(p1) || matchCpgc(p1)) && (matchSsjl(p2) || matchCpgc(p2)) && matchCpjg(p3);
    }
}