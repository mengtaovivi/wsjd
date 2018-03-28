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
    private List<Map> lawList;

    public LawItemVO(String name, List<Map> lawList) {
        this.name = name;
        this.lawList = lawList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map> getLawList() {
        return lawList;
    }

    public void setLawList(List<Map> lawList) {
        this.lawList = lawList;
    }
}
