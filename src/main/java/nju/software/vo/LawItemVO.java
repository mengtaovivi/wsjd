package nju.software.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/3/28.
 */
public class LawItemVO {

    // ��������, �� xxx ��
    private String name;

    // �����������Ƽ�������, �� map.put("�ڶ�����������һ��", ......);
    // ��֧�ֵ���, �г���ʽ�������Ҳ�г�
    private List<Map<String, String>> lawList;

    public LawItemVO(String name, List<Map<String, String>> lawList) {
        this.name = name;
        this.lawList = lawList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, String>> getLawList() {
        return lawList;
    }

    public void setLawList(List<Map<String, String>> lawList) {
        this.lawList = lawList;
    }
}
