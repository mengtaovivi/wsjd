package nju.software.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by away on 2018/3/27.
 */
public class Test {

    public static void main(String[] args) {
        String json = "{\"���²ö���(��ȫ��������ִ�вö�������)\":{\"���ϼ�¼\":[\"����\",\"��Ч�ö�\",\"������\",\"��������\"],\"�о����\":[],\"����\":\"\",\"������\":[\"����������\",\"��������\"],\"�����������\":[\"ԭ���߳ƶ�\"],\"���з�������\":{\"���ɷ�������\":[\"���л����񹲺͹��������Ϸ�����һ�������\",\"���������Ժ�������á��л����񹲺͹��������Ϸ����Ľ��͡���һ����ʮһ��\"]}}}";
        Gson gson = new Gson();
        Map<String, Map> val = gson.fromJson(json, new TypeToken<Map<String, Map>>() {}.getType());
        System.out.println(val.values());
    }
}
