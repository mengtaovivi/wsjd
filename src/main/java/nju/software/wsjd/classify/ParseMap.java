package nju.software.wsjd.classify;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/3/20.
 */
public class ParseMap {

    private ParseMap(){
        init();
    }

    private void init() {
        hashMap = new HashMap<>();
        hashMap.put("��Ͻ","ParseMsgxSegment");
        hashMap.put("��һ����ͨ����", "ParseYsptSegment");
    }

    private HashMap<String, String>  hashMap;

    private static ParseMap parseMap = new ParseMap();

    public static ParseMap getInstance(){
        return parseMap;
    }

    public String get(String name){
        return hashMap.get(name);
    }

    public Iterable<String> namelist(){
        return hashMap.keySet();
    }

}
