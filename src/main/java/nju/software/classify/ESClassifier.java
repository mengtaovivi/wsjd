package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/4/2.
 */
public class ESClassifier extends BaseClassifier {
    @Override
    public DocType getType(WsModel wsModel) {
        this.ssjl = wsModel.getWsssjlSegment();
        this.cpjg = wsModel.getWscpjgSegment();
        this.cpgc = wsModel.getWscpfxgcSegment();
        List<String> esList = DocType.getES();
        Class<? extends ESClassifier> clz = getClass();
        return getType(esList, clz, this);
    }

    @Override
    public String getParseRuleName() {
        return ParseMap.getInstance().getParseClassName("�ڶ������");
    }


    private DocType getType(List<String> list, Class c, Object o){
        for (String es : list) {
            String methodName = getMethodName(es);
            try {
                Method method = c.getDeclaredMethod(methodName);
                Boolean isMatch = ((boolean) method.invoke(o));
                if (isMatch) {
                    DocType type = DocType.getType(es);
                    if (type != null) {
                        return type;
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * ���²ö���(���μӶ������ϰ��������ߴ�����)
     * @return
     */
    private boolean isBCJESSS(){
        String pattern1 = "�ܲ���ͥ";
        String pattern2 = "��" + CHINESE + "��������";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }

    /**
     * ���²ö���(���󲵻�������)
     * ���ء�����(д��һ��ԭ�������������)������
     * @return
     */
    private boolean isESBHQS(){
        String pattern1 = "����" +CHINESE+ "����";
        return matchCpjg(pattern1);
    }

    /**
     * ���²ö���(����׼����������)
     */
    private boolean isESBZXCS(){
        String pattern = "��׼��"+CHINESE+"��������";
        return matchCpjg(pattern) && (ssjl.contains("�����������") || cpgc.contains("�����������"));
    }

    /**
     * ���²ö���(���󷢻�������)
     * @return
     */
    private boolean isESFHCS(){
        String pattern = "����"+CHINESE+"����";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����ά�ֲ�������ö���)
     * @return
     */
    private boolean isWCBHQSCD(){
        return cpgc.contains("��������")&&cpjg.contains("��������")&&cpjg.contains("ά��ԭ�ö�");
    }

    /**
     * ���²ö���(����ά�ֲ������߲ö���)
     * @return
     */
    private boolean isWCBYSLCD(){
        return cpgc.contains("��������")&&cpjg.contains("��������")&&cpjg.contains("ά��ԭ�ö�");
    }

    /**
     * ���²ö���(����ָ������������)
     * @return
     */
    private boolean isZLLASL(){
        String pattern = "(ָ��|ָ��)"+CHINESE+"��������";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����ָ��������)
     * @return
     */
    private boolean isZLSL(){
        String pattern = "(ָ��|ָ��)"+CHINESE+"����";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����׼����������)
     * @return
     */
    private boolean isZXCHSS(){
        String pattern = "׼��"+CHINESE+"��������";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(����׼���׼����������)
     * @return
     */
    private boolean isZXHBZX(){
        String pattern = "׼��"+CHINESE+"��������";
        return matchCpjg(pattern);
    }

    /**
     * ���²ö���(δ�����󰸼�����Ѱ��������ߴ�����)
     * @return
     */
    private boolean isWSESSLF(){
        String pattern1 = "�������";
        String pattern2 = "��" + CHINESE + "��������";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }

    private String getMethodName(String ESName) {
        int index = ESName.indexOf("_");
        return "is" + ESName.substring(index+1);
    }

    private boolean match(String content, String pattern){
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        return matcher.find();
    }

    private boolean matchSsjl(String pattern) {
        return match(ssjl, pattern);
    }

    private boolean matchCpjg(String pattern){
        return match(cpjg, pattern);
    }

    private boolean matchCpgc(String pattern){
        return match(cpgc, pattern);
    }
}
