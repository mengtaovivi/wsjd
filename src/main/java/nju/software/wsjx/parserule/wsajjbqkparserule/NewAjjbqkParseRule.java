package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuding on 2018/3/31.
 */
public class NewAjjbqkParseRule {

    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist, WsajjbqkModel wsajjbqkModel)  {
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        List<String> tempAjjbqk = new ArrayList<>();
        for (String s : ajjbqk) {
            for (WssscyrModel wssscyrModel : wssscyrModellist) {
                if(s.contains(wssscyrModel.getSscyr()))
                    s = s.replaceAll(wssscyrModel.getSscyr(), wssscyrModel.getSssf());
            }
            tempAjjbqk.add(s);
        }

        if(ajjbqk.size()==0){
            return wsajjbqkModel;
        }

        List<List<String>> keySentence = new ArrayList<>();
        for (String s : tempAjjbqk) {
            String[] strings = s.split("\\.|��");
            List<String> list = new ArrayList<>();
            for (String string : strings) {

                if(string.contains("��")){
                    string = string.substring(0, string.indexOf("��"));
                }
                if(string.contains(":")){
                    string = string.substring(0, string.indexOf(":"));
                }
                boolean isContainsFY = string.contains("��Ժ")||string.contains("��Ժ");
                boolean isContainsRD = string.contains("��Ϊ")||string.contains("����")||string.contains("�϶�")||string.contains("����")||string.contains("�о�");
                boolean isContainsSSCYR = string.contains("������")||string.contains("ԭ��")||string.contains("����");
                boolean isContainsSS = string.contains("���")||string.contains("�߳�")||string.contains("��������")||string.contains("����");
                boolean isContainsFL = string.contains("��");
                if(((isContainsFY && isContainsRD) || (isContainsSSCYR && isContainsSS)) && !isContainsFL ){
                    list.add(string);
                }
            }
            keySentence.add(list);
        }


        int index = -1;//ǰ��������
        String[] types = new String[keySentence.size()];
        for (int i = 0; i < keySentence.size(); i++) {
            List<String> strings = keySentence.get(i);
            System.out.println(strings);
            String type = getQsType(strings);
            if(type != null){
                index = i;
                types[i] = type;
            }
        }
        for (int i = 0; i <= index; i++) {
            if(types[i] == null){
                List<String> strings = keySentence.get(i);
                for (String string : strings) {
                    if(isQSYGSC(string)){
                        types[i] = "ǰ��ԭ���߳ƶ�";
                    }
                    if(isQSBGBC(string)){
                        types[i] = "ǰ�󱻸��ƶ�";
                    }
                }
            }
        }
        for (int i = index == -1 ? 0 : index + 1; i < keySentence.size(); i++) {
            if(types[i] == null){
                List<String> strings = keySentence.get(i);
                for (String string : strings) {
                    if(isBSYGSC(string)){
                        types[i] = "�������߳ƶ�";
                    }
                    if(isBSBGBC(string)){
                        types[i] = "�������˱�ƶ�";
                    }
                    if(isBSSLD(string)){
                        types[i] = "���������";
                    }
                }
            }
        }
        types = removeNull(types);
        for (String type : types) {
            System.out.print(type);
        }
        System.out.println();

        StringBuilder qsdl = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            qsdl.append(ajjbqk.get(i));
        }
        wsajjbqkModel.setQsdl(qsdl.toString());
        StringBuilder bsdl = new StringBuilder();
        for (int i = index == -1 ? 0 : index + 1; i < keySentence.size(); i++) {
            bsdl.append(ajjbqk.get(i));
        }
        wsajjbqkModel.setBsdl(bsdl.toString());
        wsajjbqkModel.setQsygscd(getStringData(ajjbqk,types,"ǰ��ԭ���߳ƶ�"));
        wsajjbqkModel.setQsbgbcd(getStringData(ajjbqk,types,"ǰ�󱻸��ƶ�"));
        wsajjbqkModel.setQssld(getListData(ajjbqk,types,"ǰ�������"));
        wsajjbqkModel.setQscmd(getStringData(ajjbqk,types,"ǰ�������ʵ��"));
        wsajjbqkModel.setQspjd(getStringData(ajjbqk,types,"ǰ���о���"));
        wsajjbqkModel.setSsrscd(getStringData(ajjbqk,types,"�������߳ƶ�"));
        wsajjbqkModel.setBssrbcd(getListData(ajjbqk,types,"�������˱�ƶ�"));
        wsajjbqkModel.setBssld(getListData(ajjbqk,types,"���������"));
        return wsajjbqkModel;
    }

    private String getStringData(List<String> ajjbqk,String[] types, String name){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : getListData(ajjbqk, types, name)) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    private List<String> getListData(List<String> ajjbqk,String[] types, String name){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            if(types[i].equals(name)){
                strings.add(ajjbqk.get(i));
            }
        }
        return strings;
    }

    private static final String[] QS_TYPES = {"ǰ���о���","ǰ�������","ǰ�������ʵ��","ǰ��ԭ���߳ƶ�","ǰ�󱻸��ƶ�"};

    private static final String[] BS_TYPES = {"�������߳ƶ�","�������˱�ƶ�","���������"};

    private static String[] removeNull(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            if(strings[i] == null){
                removeNull(strings, i);
            }
        }
        return strings;
    }

    private static String removeNull(String[] strings, int i){
        int preIndex = i - 1;
        if(preIndex >= 0 && preIndex < strings.length){
            if(strings[preIndex] != null){
                strings[i] = strings[preIndex];
            }else{
                strings[i] = removeNull(strings, preIndex);
            }
        }
        return strings[i];
    }

    private static boolean existNull(String[] strings){
        for (Object o : strings) {
            if(o == null){
                return true;
            }
        }
        return false;
    }
    
    protected String getQsType(List<String> strings){
        String type = null;
        for (String string : strings) {
            if(isQSCMD(string)){
                type = "ǰ�������ʵ��";
            }
            if (isQSSLD(string)){
                type = "ǰ�������";
            }
            if(isQSPJD(string)){
                type = "ǰ���о���";
            }
        }
        return type;
    }

    protected boolean isQSCMD(String string){
        boolean contQSFY = string.contains("ԭ��Ժ")||string.contains("ǰ��Ժ")||string.contains("һ��Ժ");
        boolean contCM = string.contains("����");
        return contQSFY && contCM;
    }

    protected boolean isQSSLD(String string){
        boolean contQSFY = string.contains("ԭ��Ժ")||string.contains("ǰ��Ժ")||string.contains("һ��Ժ");
        boolean contSL = string.contains("��Ϊ")||string.contains("�϶�");
        return contQSFY && contSL;
    }

    //ԭ��������һ��
    protected boolean isQSPJD(String string){
        boolean contQSFY = string.contains("ԭ��Ժ")||string.contains("ǰ��Ժ")||string.contains("һ��Ժ");
        boolean contPJ = string.contains("�о�");
        return contQSFY && contPJ;
    }

    protected boolean isBSSLD(String string){
        boolean contQSFY = string.contains("��Ժ");
        boolean contSL = string.contains("��Ϊ")||string.contains("����")||string.contains("�϶�");
        return contQSFY && contSL;
    }

    protected boolean isQSYGSC(String string){
        boolean contQSYG = string.contains("ԭ��");
        boolean contQQ = string.contains("����")||string.contains("�߳�");
        return contQSYG && contQQ;
    }

    protected boolean isQSBGBC(String string){
        boolean contQSYG = string.contains("����");
        boolean contQQ = string.contains("���")||string.contains("���");
        return contQSYG && contQQ;
    }

    protected boolean isBSYGSC(String string){
        boolean contQSYG = string.contains("������");
        boolean contQQ = string.contains("����")||string.contains("�߳�");
        return contQSYG && contQQ;
    }

    protected boolean isBSBGBC(String string){
        boolean contQSYG = string.contains("��������");
        boolean contQQ = string.contains("���")||string.contains("���");
        return contQSYG && contQQ;
    }
}
