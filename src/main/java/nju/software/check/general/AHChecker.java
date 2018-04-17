package nju.software.check.general;

import nju.software.util.REUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;

import static nju.software.util.REUtil.CHINESE;
import static nju.software.util.REUtil.SINGLE_CHINESE;

/**
 * ��鰸��
 * Created by away on 2018/4/7.
 */
public class AHChecker {

    private static final String AH_FORMAT = "������+ �հ���� +������+ ��Ժ���� + ���ʹ��� + ������� + ���š���";

    private static final String SAND = "(\\(\\d{4}\\))"; // �հ����
    private static final String FYDD = "(" + SINGLE_CHINESE + "(\\d{0}|\\d{2}|\\d{4})" + ")"; // ��Ժ����
    private static final String LXDD = "(" + CHINESE + ")";
    private static final String AJBH = "(\\d+)";
    private static final String AH_PATTERN_NEW = SAND + FYDD + LXDD + AJBH + "��";

    public CheckInfoItemVO check(String ah) {
        boolean match = REUtil.match(ah, AH_PATTERN_NEW);
        if (!match) {
            return new CheckInfoItemVO(
                    ErrorType.YSCW, "���Žṹ����", "���Ÿ�ʽӦΪ: " + AH_FORMAT, ErrorLevelEnum.LV_3);
        } else {
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(REUtil.match("(2017)��24",SAND+FYDD));
//
//    }
}
