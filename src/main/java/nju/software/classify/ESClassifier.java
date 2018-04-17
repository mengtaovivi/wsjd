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
