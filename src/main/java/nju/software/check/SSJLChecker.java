package nju.software.check;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/12.
 */
@Component
public class SSJLChecker {

    public List<CheckInfoItemVO> check(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, List<WssscyrModel> sscyrList, WsssjlModel wsssjlModel) {
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        List<String> ssjlRequirements = jsonParserUtil.getSsjlRequirements();
        String tip = "���ϼ�¼Ӧ������: " + Arrays.toString(ssjlRequirements.toArray());
        System.out.println("ssjlRequirements = " + Arrays.toString(ssjlRequirements.toArray()));
        Map<String, String> ssjl = xmlParserUtil.getSsjl();
        System.out.println("ssjl = " + ssjl.keySet());
        for (String requirement : ssjlRequirements) {
            if(!Synonym.isContains(ssjl.keySet(), requirement)){
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ�� " + requirement, tip, ErrorLevelEnum.LV_2));
            }
        }
//        checkInfoItemVOS.addAll(checkYS("���ϼ�¼", ssjlRequirements, ssjl.keySet()));

        if (ssjlRequirements.contains("ԭ��")) {
            for (WssscyrModel wssscyrModel : sscyrList) {
                if (wssscyrModel.getDsrlb() == null) continue;
                if (wssscyrModel.getDsrlb().equals("ԭ��")) {
                    String trueName = wssscyrModel.getSscyr();
                    String falseName = wsssjlModel.getYg();
                    if (!trueName.equals(falseName)) {
                        checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSCW, "ԭ�����ƴ���", "����ӦΪ: " + trueName, ErrorLevelEnum.LV_2));
                    }
                }
            }
        }

        if (ssjlRequirements.contains("����")) {
            for (WssscyrModel wssscyrModel : sscyrList) {
                if (wssscyrModel.getDsrlb() == null) continue;
                if(wssscyrModel.getDsrlb().equals("����")) {
                    String trueName = wssscyrModel.getSscyr();
                    String falseName = wsssjlModel.getBg();
                    if (!trueName.equals(falseName)) {
                        checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSCW, "�������ƴ���", "����ӦΪ: " + trueName, ErrorLevelEnum.LV_2));
                    }
                }
            }
        }
        return checkInfoItemVOS;
    }
}
