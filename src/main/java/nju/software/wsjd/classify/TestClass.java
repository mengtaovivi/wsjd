package nju.software.wsjd.classify;

import nju.software.service.ErrorCheckService;
import nju.software.service.impl.ErrorCheckServiceImpl;
import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/20.
 */
public class TestClass {

    private static String jsonString(){
        return "{\n" +
                "  \"���²ö���(���󲵻�������)\": {\n" +
                "    \"���ϼ�¼\": [\n" +
                "      \"������\",\n" +
                "      \"����ö�\",\n" +
                "      \"����\"\n" +
                "    ],\n" +
                "    \"�о����\": [\n" +
                "      \"�о�\",\n" +
                "      \"������\"\n" +
                "    ],\n" +
                "    \"����\": \"����\",\n" +
                "    \"������\": [\n" +
                "      \"������\",\n" +
                "      \"��������\",\n" +
                "      \"ԭ��ԭ��\",\n" +
                "      \"����\",\n" +
                "      \"������\"\n" +
                "    ],\n" +
                "    \"�����������\": [\n" +
                "      \"�������߳ƶ�\",\n" +
                "      \"�������˱�ƶ�\",\n" +
                "      \"ǰһ��ԭ���߳ƶ�\",\n" +
                "      \"ǰһ�������ʵ��\",\n" +
                "      \"ǰһ�������\",\n" +
                "      \"ǰһ���о���\",\n" +
                "      \"���������\"\n" +
                "    ],\n" +
                "    \"���з�������\": {\n" +
                "      \"���ɷ�������\": [\n" +
                "        \"���������Ժ�������á��л����񹲺͹��������Ϸ����Ľ��͡���������ʮ��\"\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    public static void main(String[] args) {
        ErrorCheckService errorCheckService = new ErrorCheckServiceImpl();
        DocInfoVO docInfoVO = new DocInfoVO("xml/������������顢���ˡ��������׶������²ö���.xml",jsonString());
        System.out.println(errorCheckService.checkError(docInfoVO));
    }
}
