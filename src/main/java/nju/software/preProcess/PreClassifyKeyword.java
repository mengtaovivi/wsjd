package nju.software.preProcess;

import nju.software.util.IOHelper;
import nju.software.wsjx.util.ListToString;

import java.io.File;
import java.util.*;

/**
 * Created by zhuding on 2018/4/10.
 */
public class PreClassifyKeyword {

    private static final String KEYWORDS_PATH = "src/main/resources/templatekeywords.txt";

    private PreClassifyKeyword(){}

    private static HashMap<String,String[]> map = new HashMap<>();

    static {
        List<String> strings = IOHelper.read(KEYWORDS_PATH);
        for (String string : strings) {
            String name = string.substring(0, string.indexOf(":"));
            String[] keywords = string.substring(string.indexOf("[")+1, string.indexOf("]")).split(",");
            map.put(name, keywords);
        }
    }

    public static String getPossibleType(String content){
        String result = "����ȷ��";
        double maxRate = 0;
        for (String s : map.keySet()) {
            String[] strings = map.get(s);
            double count = 0;
            for (String string : strings) {
                if(content.contains(string)){
                    count++;
                }
            }
            if(count / strings.length > maxRate){
                maxRate = count / strings.length;
                result = s;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getPossibleType("������ʡ���������м�����Ժ\n" +
                "�� �� �� �� ��\n" +
                "��2018����01��Ͻ4��\n" +
                "ԭ�棺�����䣬�У�1963��8��25�ճ��������壬ס����ʡТ����Т������\n" +
                "���棺ʱ�������У�1957��10��15�ճ��������壬ס������ʡ���������ϸ�����\n" +
                "ԭ���������뱻��ʱ���������ͬ����һ�������������ϸ�������Ժ��2017��7��21��������\n" +
                "�������߳ƣ�2010��6��5�գ�����ʱ����ǩ������ְ���ͬ�顣���̵ص��ں�����ʡ�������С���������壬�������ƣ�˽��סլ�����̷�Χ���ڡ���ǽĨ�ҡ���ǽճ���塢��ǽճש�����ڵ��棬�������������ΧǽĨ�ҡ�ճש����ˮ�¡�̨��Ĩ�ң����̼ۿ��60��Ԫ����ʩ�������м�ʩ��������˲����˹��ѡ�������Ϊʱ����ƽ��9#¥����Ĩ��ʩ����2012��6��12�գ���˫������ȷ��ʱ����Ƿ���˹���31��Ԫ�����˺�ʱ�����Ⱥ�����˹���1.8��Ԫ���ó��ָ�7��Ԫ����Ƿ22.2��Ԫδ�������侭�����Ҫ���������ܾ���������ԭ��Ժ������һ������ʱ����������Ƿ���񱨳�22.2��Ԫ����Ϣ���������Ϸ�����ʱ�����е���\n" +
                "���������ϸ�������Ժ��Ϊ������ϵ���蹤��ʩ����ͬ���ף������������ף�Ӧ����ר����Ͻ�Ĺ涨��˫���ķְ�Э����ȷ�ϵĹ��̵�ַλ�ڹ������С�������Ǵ壬�ʸ�Ժ�Ա����޹�ϽȨ����2017��10��18�ղö����������͹������е�������Ժ����\n" +
                "2018��1��3�գ��������е���������Ժ�Ա���Ϊ�����ͬ���ף����ǽ��蹤��ʩ����ͬ���ף�������ר����Ͻ������ʱ������ס����λ�ں�����ʡ�����ϸ��������������ϸ�������Ժ�Ա����й�ϽȨ�����������ϸ�������Ժ��������������Ժ�������Ϊ�ɣ����뱾Ժָ����Ͻ��\n" +
                "��Ժ�������Ϊ������ʱ������������ǩ���ġ���ͬ�顷�������Լ���������ԭ��Ժ����ʱ�ύ������״�����������������󣬱���Ӧϵ�����ͬ���ס�����˫�����������ڡ���ͬ�顷��Լ���������������ͬ���ף���ѡ���ͬ�����Ͷ��ٲò��Ż�������Ժ���ߡ�������Լ����Լ������������������Ϊȷ��������Ͻ��Ժ�����ݡ����������Ժ�������á��л����񹲺͹��������Ϸ����Ľ��͡���ʮ�����ڶ���涨������ͬ�����еص�û��Լ������Լ������ȷ��������Ϊ�������ҵģ����ջ���һ�����ڵ�Ϊ��ͬ���еأ������������ģ����������ڵ�Ϊ��ͬ���еأ�������ģ���������һ�����ڵ�Ϊ��ͬ���еء���ʱ����ĺ�ͬ��������Ϊ��Ϊ��ͬ���еء�������˫�������˶Ժ�ͬ���е�δ����ȷԼ������������Ϊ�������ң��������������ɹ涨��������Ϊ���ջ���һ�����������ס����ӦΪ�����ĺ�ͬ���еء��������ס����λ�ں���ʡ�����������ʱ����ĺ�ͬ���е�ӦΪ����ʡ�������������л����񹲺͹��������Ϸ����ڶ�ʮ�����涨�������ͬ������������ϣ��ɱ���ס���ػ��ߺ�ͬ���е�����Ժ��Ͻ��������ʱ������ס����λ�ں�����ʡ�����ϸ�������ͬ���е�λ�ں���ʡ�����������������������ɹ涨�����������ϸ�������Ժ��Т����Т��������Ժ�Ա��������й�ϽȨ��������ѡ����ʱ������ס���ع��������ϸ�������Ժ�������Ϸ��Ϸ��ɹ涨�����������ϸ�������Ժ�Ա���Ӧ�����������������������ϸ�������Ժ�Ա���ϵ��������ʩ����ͬ���ף�Ӧ����ר����ϽΪ�ɽ��������͹������е���������Ժ�����϶���ʵ���壬���÷��ɴ��󣬱�Ժ���Ծ������������е���������Ժ����ָ����Ͻ�������������Ժ����֧�֡�\n" +
                "���ա��л����񹲺͹��������Ϸ�������ʮ�����涨���ö����£�\n" +
                "�����ɹ��������ϸ�������Ժ����\n" +
                "���ö�һ����������Ч��\n" +
                "���г����� �� ��\n" +
                "����Ա���� ־ ��\n" +
                "����Ա����ľ����\n" +
                "\n" +
                "����һ����һ��ʮ����\n" +
                "���Ա���� �� ��\n" +
                "\n"));
    }

//    public static void main(String[] args) {
//        File file = new File("template");
//        File[] files = file.listFiles();
//        KeywordsMaker keywordsMaker = new KeywordsMaker();
//        for (File f : files) {
//            if(f.isDirectory()){
//                String classname = f.getName();
//                System.out.println(classname);
//
//                List<String> list = new ArrayList<>();
//                File[] files1 = f.listFiles();
//                for (File file1 : files1) {
//                    String name = "template/" + classname+"/" +file1.getName();
//                    if(file1.getName().contains("�ö�")){
//                        List<String> read = IOHelper.read(name);
//                        list.add(ListToString.List2String(read));
//                    }
//
//                }
//
//
//                TemplatePrePro templatePrePro = new TemplatePrePro(list);
//                keywordsMaker.addData(classname, templatePrePro.getWordNums());
//            }
//        }
//        HashMap<String, HashMap<String, Integer>> keywords = keywordsMaker.getKeywords();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : keywords.keySet()) {
//            System.out.println(s+":"+keywords.get(s).keySet());
//            stringBuilder.append(s+":"+keywords.get(s).keySet()+"\n");
//        }
//        IOHelper.write("src/main/resources/templatekeywords.txt",stringBuilder.toString());
//    }

}
