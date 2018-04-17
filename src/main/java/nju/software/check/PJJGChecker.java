package nju.software.check;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/12.
 */
@Component
public class PJJGChecker {

    public List<CheckInfoItemVO> check(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil) {
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        List<String> pjjgRequirements = jsonParserUtil.getPjjgRequirements();
        String tip = "�о����Ӧ������: " + Arrays.toString(pjjgRequirements.toArray());
        System.out.println("pjjgRequirements = " + Arrays.toString(pjjgRequirements.toArray()));
        Map<String, String> pjjg = xmlParserUtil.getPjjg();
        System.out.println("pjjg = " + pjjg.keySet());
        for (String requirement : pjjgRequirements) {
            if(!Synonym.isContains(pjjg.keySet(), requirement)){
                if (requirement.equals("��������Ȩ")) {
                    tip = "��������Ȩ���ʽ����:  �粻�����ö��������ڲö����ʹ�֮����ʮ���ڣ���Ժ�ݽ�����״�������նԷ������˻��ߴ����˵�������������������ڡ�����������Ժ";
                }
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ�� " + requirement, tip, ErrorLevelEnum.LV_2));
            } else {
                System.out.println(requirement + " in " + Arrays.toString(pjjg.keySet().toArray()));
            }
        }
        return checkInfoItemVOS;
    }
}
