package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.facade.impl.WsModelFacadeImpl;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.util.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/3/31.
 */
public class YSClassifier implements BaseClassifier {

    private String ssjl;
    private String cpjg;

    @Override
    public DocType getType(WsModel wsModel) {
        this.ssjl = wsModel.getWsssjlSegment();
        this.cpjg = wsModel.getWscpjgSegment();
        List<String> ysList = DocType.getYS();
        Class<? extends YSClassifier> clz = getClass();
        for (String ys : ysList) {
            String methodName = getMethodName(ys);
            try {
                Method method = clz.getDeclaredMethod(methodName);
                Boolean isMatch = ((boolean) method.invoke(this));
                if (isMatch) {
                    DocType type = DocType.getType(ys);
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

    // ���²ö���(����׷�ӹ�ͬ���ϵ�����������)
    private boolean isBHZJ() {
        String BHZJ = "����" + CHINESE + "׷��" + CHINESE;
        return isMatch(cpjg, BHZJ);
    }

    // ���²ö���(����������)
    private boolean isBHQS() {
        String BHQS = "����" + CHINESE + "������";
        return isMatch(cpjg, BHQS);
    }

    // ���²ö���(���μ����ϰ����ߴ�����)
    private boolean isBCJSS() {
        String BCJSS_1 = "���������ɾܲ���ͥ";
        String BCJSS_2 = "δ����ͥ�����;��ͥ";
        String BCJSS_3 = "��" + CHINESE + "���ߴ���";
        return ssjl.contains(BCJSS_1) || ssjl.contains(BCJSS_2) ||
                isMatch(cpjg, BCJSS_3);
    }

    // ���²ö���(��׼������)
    private boolean isBZXCS() {
        String BZXCS_1 = "��׼��" + CHINESE + "����";
        String BZXCS_2 = "��׼��" + CHINESE + "��������";
        return isMatch(cpjg, BZXCS_1) || isMatch(cpjg, BZXCS_2);
    }

    // ���²ö���(�Է��߲���������)
    private boolean isDFSBYSL() {
        String DFSBYSL = CHINESE + "�ķ��ߣ���Ժ��������";
        return isMatch(cpjg, DFSBYSL);
    }

    // ���²ö���(�����߲���������)
    private boolean isDQSBYSL() {
        String DQSBYSL = CHINESE + "�����ߣ���Ժ��������";
        return isMatch(cpjg, DQSBYSL);
    }

    // ���²ö���(�ϲ�������)
    private boolean isHBSL() {
        String HBSL = CHINESE + "����" + CHINESE + "����";
        return isMatch(cpjg, HBSL);
    }

    // ���²ö���(���׳���תΪ��ͨ������)
    private boolean isJYZPT() {
        String JYZPT = CHINESE + "ת" + CHINESE + "��ͨ����";
        return isMatch(cpjg, JYZPT) && ssjl.contains("���׳���");
    }

    // ���²ö���(С�����ϳ��򲵻�������)
    private boolean isXESS() {
        String JYZPT_1 = CHINESE + "����С�����ϳ���" + CHINESE;
        String JYZPT_2 = "����" + CHINESE + "������";
        return isMatch(cpjg, JYZPT_1) && isMatch(cpjg, JYZPT_2);
    }

    // ���²ö���(��ֹ������)
    private boolean isZZSS() {
        String ZZSS = CHINESE + "��ֹ����";
        return isMatch(cpjg, ZZSS);
    }

    // ���²ö���(�ս�������)
    private boolean isZJSS() {
        String ZJSS = CHINESE + "�ս�����";
        return isMatch(cpjg, ZJSS);
    }

    // ���²ö���(׼���ط�����)
    private boolean isZXCHFS() {
        String TCFS = CHINESE + "�������";
        String ZXCHFS = "׼��" + CHINESE + "���ط���";
        return isMatch(ssjl, TCFS) && isMatch(cpjg, ZXCHFS);
    }

    // ���²ö���(׼������)
    private boolean isZZCS() {
        String CSSQ_1 = CHINESE + "��������";
        String CSSQ_2 = CHINESE + "��������";
        String ZZCS_1 = "׼��" + CHINESE + "����";
        String ZZCS_2 = "׼��" + CHINESE + "��������";
//        System.out.println(ssjl);
//        System.out.println(cpjg);
//        System.out.println((isMatch(ssjl, CSSQ_1) || isMatch(ssjl, CSSQ_2)));
//        System.out.println(isMatch(cpjg, ZZCS_1) || isMatch(cpjg, ZZCS_2));
        return (isMatch(ssjl, CSSQ_1) || isMatch(ssjl, CSSQ_2)) && (isMatch(cpjg, ZZCS_1) || isMatch(cpjg, ZZCS_2));
    }

    private String getMethodName(String YSName) {
        int index = YSName.indexOf("_");
        return "is" + YSName.substring(index+1);
    }

    private boolean isMatch(String content, String pattern) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        return matcher.find();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("file");
        File[] files = file.listFiles();
        BaseClassifier classifier = new YSClassifier();

        for(File f:files) {
            String name = f.getAbsolutePath();
            byte[] wsnr = FileUtil.getContent(name);
            InputStream is = new ByteArrayInputStream(wsnr);
            WsModelFacadeImpl wsModelFacadeImpl = new WsModelFacadeImpl();
            WsModel wsModel = wsModelFacadeImpl.jxDocument(is, name);

            System.out.println(name);
            System.out.println(classifier.getType(wsModel).getFileName());
        }
    }
}
