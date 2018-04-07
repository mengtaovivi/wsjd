package nju.software.check;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import nju.software.util.HttpUtil;

import java.util.List;

/**
 * Created by away on 2018/4/4.
 */
public class TypoChecker {

    public static void main(String[] args) {
        String url = "http://api.CuoBieZi.net/spellcheck/json_check/json_phrase";

        String sentence = "Ժ�������Ϊ��������̩֤ȯ��˾�ṩ�ġ���ȯ�����ɽ��������������ˣ�Ӧ�ڰ��������в��������������Ժ���������ڶ���涨����ͬ�����еص�û��Լ������Լ������ȷ��������Ϊ�������ҵģ����ջ���һ�����ڵ�Ϊ��ͬ���еء�����ϵ֤ȯ���ף�������Ϊ�������ң���̩֤ȯ��˾��Ϊ���ܻ���һ������ס����Ϊ�����ĺ�ͬ���еء����л����񹲺͹��������Ϸ����ڶ�ʮ�����涨�����ͬ������������ϣ��ɱ���ס���ػ��ߺ�ͬ���е�����Ժ��Ͻ����̩֤ȯ��˾ס������һ��ԺϽ������һ��Ժ��Ϊ��ͬ���еط�Ժ�Ա����й�ϽȨ�����ϣ�����Ӣ����˾�������ɲ��ܳ�����������Ժ����֧��";

        JSONObject json = new JSONObject();
        json.put("content",sentence);
        json.put("username","tester");
        json.put("biz_type","show");
        json.put("mode","advanced");
        json.put("check_sensitive_word",true);

        Gson gson = new Gson();

        String str = HttpUtil.doPostJson(url, json.toString());
        TypoMsg typoMsg = gson.fromJson(str, TypoMsg.class);
        List<Case> cases = typoMsg.getCases();
        for (Case aCase : cases) {
            System.out.println(aCase);
        }
    }
}
