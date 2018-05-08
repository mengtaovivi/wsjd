package nju.software.classify;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/3/20.
 */
public class ParseMap {

    public static final String NOT_DETERMINED = "����ȷ��";

    public static final String DEFAULT_PARSECLASS = "��һ����ͨ����";

    private ParseMap(){
        init();
    }

    private void init() {
        map = new HashMap<>();
        map.put("��Ͻ",new String[]{"ParseMsgxSegment", "GXClassifier", "GXChecker"});
        map.put("��һ����ͨ����", new String[]{"ParseYsptSegment","YSClassifier", "YSChecker"});
        map.put("�ڶ������", new String[]{"ParseMsesSegment", "ESClassifier", ""});
        map.put("���ϲμ���", new String[]{"ParseSscjrSegment","SSCJRClassifier", ""});
        map.put("֤��", new String[]{"ParseZjSegment","ZJClassifier", ""});
        map.put("��ȫ������ִ��", new String[]{"","BQClassifier", ""});
        map.put("��������", new String[]{"","GYClassifier", ""});
        map.put("��ʾ�߸����", new String[]{"","", ""});
        map.put("ִ������֮��", new String[]{"","ZYClassifier", ""});
        map.put("�����˳���֮��", new String[]{"","DSRClassifier", ""});
        map.put("���׳���", new String[]{"ParseYsptSegment","YSClassifier", ""});
        map.put("���׳����е�С������", new String[]{"ParseYsptSegment","YSClassifier",""});
        map.put("���Ϸ���", new String[]{"","SSFYClassifier", ""});
    }

    private HashMap<String, String[]> map;

    private static ParseMap parseMap = new ParseMap();

    public static ParseMap getInstance(){
        return parseMap;
    }

    public static Iterable<String> classnameKeys(){return parseMap.map.keySet();}

    public String getParseName(String classname){
        return map.get(classname)[0];
    }

    public String getClassifyName(String classname){
        return map.get(classname)[1];
    }

    public String getCheckerName(String classname){
        return map.get(classname)[2];
    }

    public static void main(String[] args) {
        System.out.println(classnameKeys());
    }
}
