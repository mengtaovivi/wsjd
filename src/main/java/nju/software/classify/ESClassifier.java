package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/4/2.
 */
public class ESClassifier extends BaseClassifier {

//    public static void main(String[] args) {
//        File file = new File("template/�ڶ������");
//        File[] files = file.listFiles();
//
//        for(File f:files){
//            System.out.println("ES_(\"" + f.getName().substring(0,f.getName().indexOf("."))+"\")");
//        }
//    }

//    public static void main(String[] args) {
//        String[] strings = ("ES_ESGP(\"�����о���(���������)\"),\n" +
//                "    ES_BFGP(\"�����о���(���ָ�����)\"),\n" +
//                "    ES_WCYP(\"�����о���(�������ߣ�ά��ԭ����)\")").split(",");
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

    /**
     * �����о���(���������)
     * @return
     */
    private boolean isESGP(){
        boolean isPJ = ws.contains("�о�");
        String p1 = "����";
        return matchCpjg(p1) && isPJ;
    }
    /**
     * �����о���(���ָ�����)
     * @return
     */
    private boolean isBFGP(){
        boolean isPJ = ws.contains("�о�");
        String p1 = "ά��.*��.*��";
        String p2 = "����.*��.*��";
        String p3 = "���.*��.*��";
        return matchCpjg(p1) && matchCpjg(p2) && matchCpjg(p3) && isPJ;
    }
    /**
     * �����о���(�������ߣ�ά��ԭ����)
     * @return
     */
    private boolean isWCYP(){
        boolean isPJ = ws.contains("�о�");
        String p1 = "ά��ԭ��";
        return isPJ && matchCpjg(p1);
    }

    @Override
    public DocType getType(WsModel wsModel,String ah) {
        return getType(DocType.ES_PREFIX, ah, wsModel);
    }

    /**
     * ���²ö���(���μӶ������ϰ��������ߴ�����)
     * @return
     */
    protected boolean isBCJESSS(){
        String pattern1 = "�ܲ���ͥ";
        String pattern2 = "��" + CHINESE + "��������";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }

    /**
     * ���²ö���(���󲵻�������)
     * ���ء�����(д��һ��ԭ�������������)������
     * @return
     */
    protected boolean isESBHQS(){
        String pattern1 = "����" +CHINESE+ "����";
        return matchCpjg(pattern1);
    }

    /**
     * ���²ö���(����׼����������)
     */
    protected boolean isESBZXCS(){
        String pattern = "��׼��"+CHINESE+"��������";
        return matchCpjg(pattern) && (ssjl.contains("�����������") || cpgc.contains("�����������"));
    }

    /**
     * ���²ö���(���󷢻�������)
     * @return
     */
    protected boolean isESFHCS(){
        String pattern = "����"+CHINESE+"����";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����ά�ֲ�������ö���)
     * @return
     */
    protected boolean isWCBHQSCD(){
        return cpgc.contains("��������")&&cpjg.contains("��������")&&cpjg.contains("ά��ԭ�ö�");
    }

    /**
     * ���²ö���(����ά�ֲ������߲ö���)
     * @return
     */
    protected boolean isWCBYSLCD(){
        return cpgc.contains("��������")&&cpjg.contains("��������")&&cpjg.contains("ά��ԭ�ö�");
    }

    /**
     * ���²ö���(����ָ������������)
     * @return
     */
    protected boolean isZLLASL(){
        String pattern = "(ָ��|ָ��)"+CHINESE+"��������";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����ָ��������)
     * @return
     */
    protected boolean isZLSL(){
        String pattern = "(ָ��|ָ��)"+CHINESE+"����";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����׼����������)
     * @return
     */
    protected boolean isZXCHSS(){
        String pattern = "׼��"+CHINESE+"��������";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����׼���׼����������)
     * @return
     */
    protected boolean isZXHBZX(){
        String pattern = "׼��"+CHINESE+"��������";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(δ�����󰸼�����Ѱ��������ߴ�����)
     * @return
     */
    protected boolean isWSESSLF(){
        String pattern1 = "�������";
        String pattern2 = "��" + CHINESE + "��������";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }



 }
