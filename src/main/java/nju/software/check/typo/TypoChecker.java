package nju.software.check.typo;

import nju.software.vo.SectionTypoCheckVO;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Chinese;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/4/4.
 */
@Component
public class TypoChecker {

    private JLanguageTool langTool;

    private String[] invalidRule = {"wb2", "wa5", "SHI_ADHECTIVE_ERROR", "wb4", "wa3"};
//    private String[] invalidRule = {};

    public TypoChecker() {
        langTool = new JLanguageTool(new Chinese());
    }

    public List<SectionTypoCheckVO> check(String text) {
        if (text == null) {
            return new ArrayList<>();
        }
//        System.out.println("text = " + text);
        List<SectionTypoCheckVO> sectionTypoCheckVOList = new ArrayList<>();
        List<RuleMatch> matches;
        try {
            matches = langTool.check(text);
            for (RuleMatch match : matches) {
                Rule rule = match.getRule();
//                System.out.println(match.getMessage());
                if (!isValidRule(rule)) continue;

                SectionTypoCheckVO sectionTypoCheckVO = new SectionTypoCheckVO();
                int start = match.getFromPos();
                int end = match.getToPos();
                String sentence = match.getSentence().getAnnotations();
                String word = text.substring(start, end);
                String message = getMessage(match.getMessage());

//                System.out.println("message = " + message);
//                System.out.println("id = " + rule.getId());
                sectionTypoCheckVO.setStart(start);
                sectionTypoCheckVO.setEnd(end);
                sectionTypoCheckVO.setSentence(sentence);
                sectionTypoCheckVO.setWord(word);
                sectionTypoCheckVO.setRuleId(rule.getId());
                sectionTypoCheckVO.setMessage(message);
                sectionTypoCheckVOList.add(sectionTypoCheckVO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sectionTypoCheckVOList;
    }

    private String getMessage(String message) {
        message = message.replace("<suggestion>", " ");
        message = message.replace("</suggestion>", " ");
        return message;
    }

    private boolean isValidRule(Rule rule) {
        String id = rule.getId();
        for (String s : invalidRule) {
            if (s.equals(id)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        JLanguageTool langTool = new JLanguageTool(new Chinese());
// comment in to use statistical ngram data:
//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        String text = "ԭ��Ժ�����������2013��7��8�գ�����顢���ˡ�����̫��ͬ���߽�����ݣ��ֱ������½��120000Ԫ��180000Ԫ��˫����ͷԼ��������2%��2014��8��19��֮ǰ����Ϣ�Ѿ����塣����������δ��������2014��9��24�������̫������顢���ˡΪ��������ԭ��Ժ��Ҫ��黹����Ϣ�������̫���ӷǷ����չ��ڴ��ԭ��Ժ��2014��12��2��������2014��������ֵ�02091��2�����²ö��飬���ض����µ����ߣ������������������ع�������鴦���������ع����������̫���ӷǷ����չ��ڴ����������ֶ�����������顢���ˡΪ��������ԭ��Ժ��Ҫ��֧�����������롣\n" +
                "���Ϲ����У���������Ը�����������Ϊ�����о�����顢���ˡ�黹�����½���300000Ԫ���������Ϸ�������顢���ˡ�е����Խ����Ϣ��ʾ������Ȩ����\n" +
                "ԭ��Ժ��Ϊ��2015��9��1����ʩ�еġ��������Ժ��������������������÷�����������Ĺ涨����ʮ������һ��涨������˻�����˵Ľ����Ϊ���ӷ�������Ѿ���Ч���о��϶����ɷ�������������������ϵģ��������ͬ������Ȼ��Ч������ԺӦ�����ݺ�ͬ������ʮ���������涨��ʮ����֮�涨���϶��������ͬ��Ч�������̫���ӷǷ����չ��ڴ������ӷ���ĵ����˵��������Ϊ�����ɷ��ֻ�дﵽһ������ŷ����ʱ䣬���ɷ����������Ϊ���ͬ��Ϊ���غϡ����̫������顢���ˡ��ͬ�����½��ʱ��������˼��ʾ��ʵ��������������顢���ˡ�����̫֮��Ľ����ϵ��Υ�����ɡ����������Ч����ǿ���Թ涨��δ�𺦹��ҡ����塢����������ߵ��������棬Ҳû�С��ԺϷ���ʽ�ڸǷǷ�Ŀ�ġ���Ӧ�϶��Ϸ���Ч��ԭ��Ժ����������ԡ������С�������顢���ˡΪ��ͬ����ˣ�����Ӧ���е���ͬ�������Ρ�����顢���ˡ�����Ϊ��֤�ˣ�δ�ṩ֤������֤�������š��ʶ�����Ҫ������顢���ˡ�黹�����������������ʵ�ͷ������ݣ�ԭ��Ժ����֧�֡��������ڱ����г��ض�֧�������Ϣ���������󣬱�ʾ������Ȩ����ϵ��������Ȩ���Ĵ��֣����Ϸ��ɹ涨��ԭ��Ժ����׼������顢���ˡ�ı������ʵ�ͷ������ݣ�ԭ��Ժ����֧�֡����ա��л����񹲺͹���ͬ�����ڶ��������������������Ժ��������������������÷�����������Ĺ涨����ʮ������һ����л����񹲺͹��������Ϸ�����һ����ʮ����֮�涨���о�������顢���ˡӦ���о���������Ч��֮����ʮ����������֧������300000Ԫ��\n" +
                "ԭ���о����к�����顢���ˡ��������Ժ�������ߣ��������Ժ�������в��ض�����ԭ���������������ߵ���Ҫ����Ϊ��1��ԭ��Ժ�϶�����顢���ˡ�����̫������Ϊ��ͬ������Ǵ���ġ���ʵ�ϣ�����顢���ˡ����Ϊ�永���Ľ����ˡ�֤�����ڽ����ǩ�ֵģ�ʵ�ʽ���������̫���ڣ�2014��������ֵ�2092�Ű���ͥ���¼�У����������������ɷ����̫���Ǯ������顢���ˡֻ�ǽ����ˡ�֤���ˡ����⣬�ӽ���ǩ����Ҳ���жϳ�����顢���ˡ���ǽ���ˣ�����˵�ǩ������ǩ�ڽ���˴�������ǩ�ڽ�����ڵĺ��棬���Ͻ����ˡ�֤����ǩ�ֵ�һ��ϰ�ߡ�2���������÷��ɴ��󡣼�ʹ����顢���ˡ�ǹ�ͬ����ˣ�����ҲӦ���������ߡ����������Ľ�������̫�������ӷǷ����չ��ڴ���ﱻ��������������飬���ݡ��������Ժ�����������Ժ�����������ڰ���Ƿ��������°������÷����������������������������������Ժ��������������������÷�����������Ĺ涨��������֮�涨������Ӧ���������ߡ�\n" +
                "�������˶����´��ƣ�ԭ���о��϶���ʵ��������÷�����ȷ��Ӧ�о��������ߣ�ά��ԭ�С������������£�1������顢���ˡ��Ƿ����ǩ��û���ر�ע��������ݣ�ǩ�����˾�Ӧ���϶�Ϊ����ˡ���Ϊ�����Ϊ���ˣ���дλ�ñ�Ȼ����һ�£�����Ӱ����Ϊ����˵��϶���2�����������²�����ʵ��������������Ϊ���°�������鵼�°����޷��϶��������Ǳ����������°����Ľ��۲������о���3������顢���ˡ�����������°�����鷶Χ����������һ������ǰҲ�������ع����ֱ�����Ҫ������顢���ˡ��Ϊ���������˽�����飬����������ȷ���Ծܾ������Ա���˫��������֮��Ľ����ϵ��ȷ�����Խ����о���\n" +
                "������������齹��Ϊ��1������顢���ˡ�Ƿ����永���Ĺ�ͬ����ˣ�2�������Ƿ�ΪӦ���������ߵİ�����\n" +
                "��Ժ��Ϊ�����ڵ�һ���齹�㣬��Ȼ����顢���ˡ�ڽ���������ˡ���ǩ�֣��������̫���ӷǷ����չ��ڴ���ﱻ������飬�ʱ�����ʵ�ʽ�����Ƿ��������顢���ˡ���辭�������о��󷽿��϶���\n" +
                "���ڵڶ����齹�㣬���������Ժ��������������������÷�����������Ĺ涨��������֮�涨������Ժ�����󣬷����������Ϊ�������ӷǷ����ʷ���ģ�Ӧ���ö��������ߣ��������ӷǷ����ʷ�����������������͹������ػ��߼����ء������̫���ӷǷ����չ��ڴ�����ѱ�������飬���������Ϊ�������ӷǷ����չ��ڴ���Ӧ�ö��������ߣ�ԭ��ԺӦ���������͹������ش���������������������������������߼��������������߾�������������Ժ��Ч�о��϶������ɷǷ����ʷ���ģ������¿��Ծ�ͬһ��ʵ�����ٴ�������Ժ�������ϡ�";

        List<RuleMatch> matches = langTool.check(text);
        for (RuleMatch match : matches) {
            System.out.println("Potential error at characters " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
            System.out.println("text:" + text.substring(match.getFromPos(), match.getToPos()));
            System.out.println("rule " + match.getRule().getCategory());
            System.out.println("rule id " + match.getRule().getId());
//            System.out.println("sentence " + match.getSentence().getAnnotations());
            System.out.println("sentence " + match.getSentence().getText());
            System.out.println("short" + match.getShortMessage());
            System.out.println("Suggested correction(s): " +
                    match.getSuggestedReplacements());
            System.out.println("_______________");
        }
    }
}

//        String url = "http://api.CuoBieZi.net/spellcheck/json_check/json_phrase";
//
//        String sentence = "Ժ�������Ϊ��������̩֤ȯ��˾�ṩ�ġ���ȯ�����ɽ��������������ˣ�Ӧ�ڰ��������в��������������Ժ���������ڶ���涨����ͬ�����еص�û��Լ������Լ������ȷ��������Ϊ�������ҵģ����ջ���һ�����ڵ�Ϊ��ͬ���еء�����ϵ֤ȯ���ף�������Ϊ�������ң���̩֤ȯ��˾��Ϊ���ܻ���һ������ס����Ϊ�����ĺ�ͬ���еء����л����񹲺͹��������Ϸ����ڶ�ʮ�����涨�����ͬ������������ϣ��ɱ���ס���ػ��ߺ�ͬ���е�����Ժ��Ͻ����̩֤ȯ��˾ס������һ��ԺϽ������һ��Ժ��Ϊ��ͬ���еط�Ժ�Ա����й�ϽȨ�����ϣ�����Ӣ����˾�������ɲ��ܳ�����������Ժ����֧��";
//
//        JSONObject json = new JSONObject();
//        json.put("content",sentence);
//        json.put("username","tester");
//        json.put("biz_type","show");
//        json.put("mode","advanced");
//        json.put("check_sensitive_word",true);
//
//        Gson gson = new Gson();
//
//        String str = HttpUtil.doPostJson(url, json.toString());
//        TypoMsg typoMsg = gson.fromJson(str, TypoMsg.class);
//        List<Case> cases = typoMsg.getCases();
//        for (Case aCase : cases) {
//            System.out.println(aCase);
//        }
