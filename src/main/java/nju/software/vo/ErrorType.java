package nju.software.vo;

/**
 *
 * - �ṹ����
     - �ṹȱʧ
        - ȱ�����ϼ�¼, �о��ṹ, ����
     - Ҫ��ȱʧ
        - ȱ�� "������", "��������" ��
   - ���ݴ���
     - Ҫ�ش���
        - "������" ӦΪ ԭ��...
     - �����Ĳ�ͳһ
        - ԭ�� xxx, ����д����
 * Created by away on 2018/3/28.
 */

public enum ErrorType {
    JGQS("�ṹȱʧ"), YSQS("Ҫ��ȱʧ"), YSCW("Ҫ�ش���"), SSWBTY("�����Ĳ�ͳһ");

    private String name;

    ErrorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
