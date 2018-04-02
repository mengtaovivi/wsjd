package nju.software.classify;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/3/20.
 */
public class ParseMap {

    private ParseMap(){
        init();
    }

    private void init() {
        jxHashMap = new HashMap<>();
        jxHashMap.put("��Ͻ","ParseMsgxSegment");
        jxHashMap.put("��һ����ͨ����", "ParseYsptSegment");
        jxHashMap.put("���ϲμ���","ParseSscjrSegment");
        jxHashMap.put("�ڶ������","ParseMsesSegment");
        jxHashMap.put("֤��","ParseZjSegment");
        flHashMap = new HashMap<>();
        flHashMap.put("���","YSClassifier");
        flHashMap.put("����","ESClassifier");
        flHashMap.put("��Ͻ","");
    }

    private HashMap<String, String>  jxHashMap;

    private HashMap<String, String> flHashMap;

    private static ParseMap parseMap = new ParseMap();

    public static ParseMap getInstance(){
        return parseMap;
    }

    public String getParseClassName(String name){
        return jxHashMap.get(name);
    }

    public String getClassifierName(String name) {return flHashMap.get(name);}

    public static Iterable<String> parseClassNameKeys(){
        return parseMap.jxHashMap.keySet();
    }

    public static Iterable<String> classifierNameKeys(){return parseMap.flHashMap.keySet();}

}
