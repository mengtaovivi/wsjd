package nju.software.util;

import java.util.List;

/**
 * Created by away on 2018/4/3.
 */
public class Synonym {

    // TODO ͬ���
    private static String[] sscyr = {"���Ϸѳе���¼", "���Ϸѳе�"};
    private static String[] ssr = {"������(ԭ�����ϵ�λ)", "������", "������(ԭ��ԭ��)", "������(ԭ�󱻸�)"};
    private static String[] bssr = {"��������(ԭ�����ϵ�λ)", "��������", "��������(ԭ��ԭ��)", "��������(ԭ�󱻸�)"};

    public static boolean isEqual(String a, String b) {
        if (same(sscyr, a, b)) {
            return true;
        } else if (same(ssr, a, b)) {
            return true;
        } else if (same(bssr, a, b)) {
            return true;
        }
        return a.equals(b);
    }

    public static boolean isContains(List<String> list, String str) {
        for (String s : list) {
            if (isEqual(str, s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean same(String[] list, String a, String b) {
        return contains(list, a) && contains(list, b);
    }

    private static boolean contains(String[] list, String str) {
        for (String s : list) {
            if (s.contains(str)) {
                return true;
            }
        }
        return true;
    }
}
