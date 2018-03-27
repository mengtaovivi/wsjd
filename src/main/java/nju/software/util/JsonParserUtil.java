package nju.software.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zhuding on 2018/3/27.
 */
public class JsonParserUtil {

    private String jsonString;

    private JsonObject jsonElement;

    public JsonParserUtil(String jsonString){
        this.setJsonString(jsonString);
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
        init();
    }

    private void init(){
        JsonParser parser=new JsonParser();
        JsonObject object=(JsonObject) parser.parse(jsonString);
        this.jsonElement = object.entrySet().iterator().next().getValue().getAsJsonObject();
    }

    public List<String> getSsjlRequirements(){
        return getListKeyPoints("���ϼ�¼");
    }

    public List<String> getPjjgRequirements(){
        return getListKeyPoints("�о����");
    }

    public List<String> getDsrRequirements(){
        return getListKeyPoints("������");
    }

    public List<String> getAjjbqkRequirements(){
        return getListKeyPoints("�����������");
    }

    public String getAhRequirements(){
        return jsonElement.get("����").getAsString();
    }

    public Map<String, List<String>> getCpfxgcRequirements(){
        Map<String, List<String>> map = new HashMap<>();
        JsonObject object = jsonElement.get("���з�������").getAsJsonObject();
        Iterator<Map.Entry<String, JsonElement>> iterator = object.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, JsonElement> next = iterator.next();
            List<String> strings = new ArrayList<>();
            for (JsonElement element : next.getValue().getAsJsonArray()) {
                strings.add(element.getAsString());
            }
            map.put(next.getKey(), strings);
        }
        return map;
    }

    private List<String> getListKeyPoints(String name){
        List<String> strings = new ArrayList<>();
        JsonArray array = jsonElement.get(name).getAsJsonArray();
        for (JsonElement element : array) {
            strings.add(element.getAsString());
        }
        return strings;
    }





//    public static void main(String[] args) {
//        String string = "{\n" +
//                "  \"���²ö���(��ְȨ�ἶ��Ͻ��)\": {\n" +
//                "    \"���ϼ�¼\": [\n" +
//                "      \"ԭ��\",\n" +
//                "      \"����\",\n" +
//                "      \"����\",\n" +
//                "      \"��������\",\n" +
//                "      \"������Ժ\"\n" +
//                "    ],\n" +
//                "    \"�о����\": [],\n" +
//                "    \"����\": \"��Ͻ\",\n" +
//                "    \"������\": [\n" +
//                "      \"ԭ��\",\n" +
//                "      \"����\"\n" +
//                "    ],\n" +
//                "    \"�����������\": [\n" +
//                "      \"ԭ���߳ƶ�\"\n" +
//                "    ],\n" +
//                "    \"���з�������\": {\n" +
//                "      \"���ɷ�������\": [\n" +
//                "        \"���л����񹲺͹��������Ϸ�������ʮ������һ��\"\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//        JsonParserUtil jsonParserUtil = new JsonParserUtil(string);
//
//
//    }


}
