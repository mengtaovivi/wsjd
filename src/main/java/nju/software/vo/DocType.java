package nju.software.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/3/31.
 */
public enum DocType {
    YS_BHQS("���²ö���(����������)"),
    YS_BHZJ("���²ö���(����׷�ӹ�ͬ���ϵ�����������)"),
    YS_BCJSS("���²ö���(���μ����ϰ����ߴ�����)"),
    YS_BZXCS("���²ö���(��׼������)"),
    YS_DFSBYSL("���²ö���(�Է��߲���������)"),
    YS_DQSBYSL("���²ö���(�����߲���������)"),
    YS_HBSL("���²ö���(�ϲ�������)"),
    YS_JYZPT("���²ö���(���׳���תΪ��ͨ������)"),
    YS_XESS("���²ö���(С�����ϳ��򲵻�������)"),
    YS_ZZSS("���²ö���(��ֹ������)"),
    YS_ZJSS("���²ö���(�ս�������)"),
    YS_ZXCHFS("���²ö���(׼���ط�����)"),
    YS_ZZCS("���²ö���(׼������)"),
    ES_BCJESSS("���²ö���(���μӶ������ϰ��������ߴ�����)"),
    ES_ESBHQS("���²ö���(���󲵻�������)"),
    ES_ESBZXCS("���²ö���(����׼����������)"),
    ES_ESFHCS("���²ö���(���󷢻�������)"),
    ES_WCBHQSCD("���²ö���(����ά�ֲ������߲ö���)"),
    ES_WCBYSLCD("���²ö���(����ά�ֲ�������ö���)"),
    ES_ZLLASL("���²ö���(����ָ������������)"),
    ES_ZLSL("���²ö���(����ָ��������)"),
    ES_ZXCHSS("���²ö���(����׼����������)"),
    ES_ZXHBZX("���²ö���(����׼���׼����������)"),
    ES_WSESSLF("���²ö���(δ�����󰸼�����Ѱ��������ߴ�����)")
    ;

    String fileName;

    DocType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public static List<String> getYS() {
        List<String> ys = new ArrayList<>();
        DocType[] values = values();
        for (DocType value : values) {
            String str = value.name();
            if (str.startsWith("YS")) {
                ys.add(str);
            }
        }
        return ys;
    }

    public static List<String> getES() {
        List<String> es = new ArrayList<>();
        DocType[] values = values();
        for (DocType value : values) {
            String str = value.name();
            if (str.startsWith("ES")) {
                es.add(str);
            }
        }
        return es;
    }


    public static DocType getType(String name) {
        for (DocType docType : values()) {
            if (docType.toString().equals(name)) {
                return docType;
            }
        }
        return null;
    }
}
