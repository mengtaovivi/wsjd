package nju.software.vo;

import java.util.Map;

/**
 * Created by away on 2018/3/28.
 */
public class LawItemVO {

    // ��������, �� xxx ��
    private String name;

    // �����������Ƽ�������, �� map.put("�ڶ�����������һ��", ......);
    // ��֧�ֵ���, �г���ʽ�������Ҳ�г�
    private Map<String, String> lawMap;

    public LawItemVO(String name, Map<String, String> lawMap) {
        this.name = name;
        this.lawMap = lawMap;
    }

    public LawItemVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getLawMap() {
        return lawMap;
    }

    public void setLawMap(Map<String, String> lawMap) {
        this.lawMap = lawMap;
    }
}
