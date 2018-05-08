package nju.software.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/3/31.
 */
public enum DocType {
    YS_YSPT("�����о���(��һ����ͨ������)"),
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
    ES_ESGP("�����о���(���������)"),
    ES_BFGP("�����о���(���ָ�����)"),
    ES_WCYP("�����о���(�������ߣ�ά��ԭ����)"),
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
    ES_WSESSLF("���²ö���(δ�����󰸼�����Ѱ��������ߴ�����)"),
    GX_BFGXCDSS("���²ö���(������Ͻ�ö����߰�����)"),
    GX_GXQYY("���²ö���(��ϽȨ������)"),
    GX_SJYJXJ("���²ö���(�ϼ���Ժ�ƽ��¼���Ժ������)"),
    GX_SBQZDGX("���²ö���(����������Ժ����ָ����Ͻ������)"),
    GX_XSSSGXYY("���²ö���(С�����ϳ����ϽȨ������)"),
    GX_BQTJGX("���²ö���(�������ἶ��Ͻ��)"),
    GX_ZQTJGX("���²ö���(��ְȨ�ἶ��Ͻ��)"),
    GX_ZQYSGX("���²ö���(��ְȨ���͹�Ͻ��)"),
    GX_BQZDGX("���²ö���(���ϽȨ���鱨��ָ����Ͻ������)"),
    GX_YGXQBQZD("���²ö���(�й�ϽȨ����Ժ����ָ����Ͻ������)"),
    SSCJR_BGDSRY("���²ö���(�����������)"),
    SSCJR_WCJDJ("���²ö���(δ�μӵǼǵ�Ȩ����������Ч�о���ö���)"),
    ZJ_ZCQZJBQ("���²ö���(�ٲ�ǰ֤�ݱ�ȫ��)"),
    ZJ_SQSZ("���²ö���(������֤���������)"),
    ZJ_SQFHJDF("���²ö���(���뷵������������)"),
    ZJ_JCZJBQ("���²ö���(���֤�ݱ�ȫ��)"),
    ZJ_SQZJBQ("���²ö���(��ǰ֤�ݱ�ȫ��)"),
    ZJ_SSZJBQ("���²ö���(����֤�ݱ�ȫ��)"),
    BQ_ZCZCCBQ("���²ö���(�ٲ��вƲ���ȫ��)"),
    BQ_YZQSSBQ("���²ö���(��ְȨ���ϱ�ȫ��)"),
    BQ_CDFY("���²ö���(��ȫ��������ִ�вö�������)"),
    BQ_XYZX("���²ö���(����ִ����)"),
    BQ_BGBQ("���²ö���(�����ȫ��)"),
    BQ_ZXQBQ("���²ö���(ִ��ǰ��ȫ��)"),
    BQ_JCBQ("���²ö���(�����ȫ��)"),
    BQ_SQXWBQ("���²ö���(��ǰ��Ϊ��ȫ��)"),
    BQ_SQCCBQ("���²ö���(��ǰ�Ʋ���ȫ��)"),
    BQ_SSXWBQ("���²ö���(������Ϊ��ȫ��)"),
    BQ_SSCCBQ("���²ö���(���ϲƲ���ȫ��)"),
    BQ_BHSQ("���²ö���(���ر�ȫ��������ִ��������)"),
//    GY_QFXFZQY("�����о���(�ֺ�������Ȩ�湫��������)"),
//    GY_HJWR("�����о���(������Ⱦ������̬�ƻ�����������"),
    GY_BZXCH("���²ö���(�������ϲ�׼����������)"),
    GY_LXTQBYSL("���²ö���(��ͬһ��Ȩ��Ϊ�������������ϲ���������)"),
    GY_HJWRZXCH("���²ö���(������Ⱦ������̬�ƻ���������׼����������)"),
//    DSR_DSRCXZS("�����о���(�����˳���֮����)"),
    DSR_ZZZSCX("���²ö���(��ֹ���������)"),
    DSR_BYSL("���²ö���(�Ե����˳���֮�߲���������)"),
    DSR_BRZSCX("���²ö���(�����˳���֮�߲������������)"),
    SSFY_WBJACH("���²ö���(δ������������Ѱ��������ߴ�����)"),
    SSFY_WYJACH("���²ö���(δԤ����������Ѱ��������ߴ�����)"),
//    ZY_AWRZY("�����о���(������ִ������֮����)"),
//    ZY_SQZXRZY("�����о���(����ִ����ִ������֮����)")
    ;

    public static final String YS_PREFIX = "YS";

    public static final String ES_PREFIX = "ES";

    public static final String GX_PREFIX = "GX";

    public static final String SSCJR_PREFIX = "SSCJR";

    public static final String ZJ_PREFIX = "ZJ";

    public static final String BQ_PREFIX = "BQ";

    public static final String GY_PREFIX = "GY";

    public static final String DSR_PREFIX = "DSR";

    public static final String SSFY_PREFIX = "SSFY";

    public static final String ZY_PREFIX = "ZY";

    String fileName;

    DocType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public static List<String> getTypeList(String typeString) {
        List<String> ys = new ArrayList<>();
        DocType[] values = values();
        for (DocType value : values) {
            String str = value.name();
            if (str.startsWith(typeString)) {
                ys.add(str);
            }
        }
        return ys;
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
