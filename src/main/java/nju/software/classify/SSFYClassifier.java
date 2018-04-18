package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by zhuding on 2018/4/18.
 */
public class SSFYClassifier extends BaseClassifier{

//    public static void main(String[] args) {
//        File file = new File("template/���Ϸ���");
//        File[] files = file.listFiles();
//
//        for(File f:files){
//            System.out.println("SSFY_(\"" + f.getName().substring(0,f.getName().indexOf("."))+"\")");
//        }
//    }

//    public static void main(String[] args) {
//        String[] strings = ("SSFY_WBJACH(\"���²ö���(δ������������Ѱ��������ߴ�����)\"),\n" +
//                "    SSFY_WYJACH(\"���²ö���(δԤ����������Ѱ��������ߴ�����)\")").split(",");
//        for (String s : strings) {
//            String s1 = s.substring(s.indexOf("_")+1, s.indexOf("("));
//            String s2 = s.substring(s.indexOf("\"")+1, s.lastIndexOf("\""));
//            System.out.println("    /**\n" +
//                    "     * "+ s2 +"\n" +
//                    "     * @return\n" +
//                    "     */\n" +
//                    "    private boolean is"+s1+"(){\n" +
//                    "\n" +
//                    "    }");
//        }
//    }

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.SSFY_PREFIX, ah, wsModel);
    }


    /**
     * ���²ö���(δ������������Ѱ��������ߴ�����)
     * @return
     */
    private boolean isWBJACH(){
        String p1 = "�ʹﲹ��.*֪ͨ";
        String p2 = "δ(����|���)*����";
        String p3 = "��.*��������";
        return (matchSsjl(p1) || matchCpgc(p1)) && (matchSsjl(p2) || matchCpgc(p2)) && matchCpjg(p3);
    }
    /**
     * ���²ö���(δԤ����������Ѱ��������ߴ�����)
     * @return
     */
    private boolean isWYJACH(){
        String p1 = "�ʹｻ��.*֪ͨ";
        String p2 = "(δ��.*Ԥ��)|(����(��|��|��).*δ����׼)";
        String p3 = "��.*��������";
        return (matchSsjl(p1) || matchCpgc(p1)) && (matchSsjl(p2) || matchCpgc(p2)) && matchCpjg(p3);
    }
}
