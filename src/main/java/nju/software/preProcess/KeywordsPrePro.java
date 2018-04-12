package nju.software.preProcess;

import nju.software.util.IOHelper;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/4/10.
 */
public class KeywordsPrePro {

    private static final String STOPWORDDIC = "src/main/resources/stopword.txt";

    private static final String CHINESE = "[\\u4e00-\\u9fa5]+";

    private List<String> contents;

    private static List<String> stopwordList = IOHelper.read(STOPWORDDIC);

    public KeywordsPrePro(List<String> contents) {
        init(contents);
    }

    private void init(List<String> contents){
        this.contents = new ArrayList<>();
        Pattern pattern = Pattern.compile(CHINESE);
        for (String content : contents) {
            content = content.substring(0, content.indexOf("��˵����"));
            Matcher matcher = pattern.matcher(content);
            StringBuilder stringBuilder = new StringBuilder();
            while (matcher.find()){
                stringBuilder.append(matcher.group());
            }
            this.contents.add(stringBuilder.toString());
        }
    }


    protected List<List<String>> getFCResult(){
        List<List<String>> result = new ArrayList<>();
        for (String content : contents) {
            List<String> strings = new ArrayList<>();
            IKAnalyzer analyzer = new IKAnalyzer();
            analyzer.setUseSmart(true);
            try {
                StringReader stringReader = new StringReader(content);
                TokenStream tokenStream = analyzer.tokenStream("", stringReader);
                tokenStream.reset();
                CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
                while (tokenStream.incrementToken()) {
                    String string = term.toString();
                    if(!isStopWord(string))
                        strings.add(string);
                }
                result.add(strings);
                analyzer.close();
                stringReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public HashMap<String, Integer> getWordNums(){
        HashMap<String, Integer> map = new HashMap<>();
        for (List<String> strings : getFCResult()) {
            for (String string : strings) {
                if(map.keySet().contains(string)){
                    map.put(string, map.get(string) + 1);
                }else{
                    map.put(string, 1);
                }
            }
        }
        return map;
    }

    private boolean isStopWord(String s){
        for (String s1 : stopwordList) {
            if(s.contains(s1) || s.length() == 1){
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        List<String> strings = new ArrayList<>();
//        strings.add("ԭ��������뱻�����������(д������)һ������Ժ�ڡ�������������¡�����������������������\n");
//        strings.add("��������Ժ�����������1.������2.����(��ȷԭ�����������)����ʵ�����ɣ�����(����ԭ�����ŵ���ʵ������)��");
//        KeywordsPrePro templatePrePro = new KeywordsPrePro(strings);
//        HashMap<String, Integer> wordNums = templatePrePro.getWordNums();
//        for (String s : wordNums.keySet()) {
//            System.out.println(s + ":" +wordNums.get(s));
//        }
//    }

}
