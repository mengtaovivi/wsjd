package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/19.
 */
public class ZYClassifier extends BaseClassifier  {

//    public static void main(String[] args) {
//        File file = new File("template/ִ������֮��");
//        File[] files = file.listFiles();
//
//        for(File f:files){
//            System.out.println("ZY_(\"" + f.getName().substring(0,f.getName().indexOf("."))+"\")");
//        }
//    }


//    public static void main(String[] args) {
//        String[] strings = ("ZY_AWRZY(\"�����о���(������ִ������֮����)\"),\n" +
//                "    ZY_SQZXRZY(\"�����о���(����ִ����ִ������֮����)\")").split(",");
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
        return getType(DocType.ZY_PREFIX, ah, wsModel);
    }

//    /**
//     * �����о���(������ִ������֮����)
//     * @return
//     */
//    private boolean isAWRZY(){
//
//    }
//
//    /**
//     * �����о���(����ִ����ִ������֮����)
//     * @return
//     */
//    private boolean isSQZXRZY(){
//
//    }
}
