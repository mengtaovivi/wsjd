package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class BQClassifier extends BaseClassifier {


//    public static void main(String[] args) {
//        File file = new File("template/��ȫ������ִ��");
//        File[] files = file.listFiles();
//
//        for(File f:files){
//            System.out.println("BQ_(\"" + f.getName().substring(0,f.getName().indexOf("."))+"\")");
//        }
//    }

    //"),
//    BQ_"),
//    BQ_CDFY("���²ö���(��ȫ��������ִ�вö�������)"),
//    BQ_XYZX("���²ö���(����ִ����)"),
//    BQ_BGBQ("���²ö���(�����ȫ��)"),
//    BQ_ZXQBQ("���²ö���(ִ��ǰ��ȫ��)"),
//    BQ_JCBQ("���²ö���(�����ȫ��)"),
//    BQ_SQXWBQ("���²ö���(��ǰ��Ϊ��ȫ��)"),
//    BQ_SQCCBQ("���²ö���(��ǰ�Ʋ���ȫ��)"),
//    BQ_SSXWBQ("���²ö���(������Ϊ��ȫ��)"),
//    BQ_SSCCBQ("���²ö���(���ϲƲ���ȫ��)"),
//    BQ_BHSQ("���²ö���(���ر�ȫ��������ִ��������)")
//    /**
//     *
//     * @return
//     */
//    private boolean is(){
//
//    }


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
     *
     * @return
     */
    private boolean is(){
        return false;
    }

}
