package nju.software.check.pjjg;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/12.
 */
public class PJJGChecker {

    private JsonParserUtil jsonParserUtil;
    private XmlParserUtil xmlParserUtil;
    private WsModel wsModel;

    public PJJGChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        this.jsonParserUtil = jsonParserUtil;
        this.xmlParserUtil = xmlParserUtil;
        this.wsModel = wsModel;
    }

    public List<CheckInfoItemVO> check() {
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

        WscpjgModel wscpjgModel = wsModel.getWscpjgModel();
        if (wscpjgModel != null) {
            WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
            if (ssfModel != null) {
                List<WsCpjgssfjeModel> ssfjeModels = ssfModel.getSsfjeModels();
                BigDecimal je = new BigDecimal("0");
                for (WsCpjgssfjeModel ssfjeModel : ssfjeModels) {
                    String value = ssfjeModel.getValue();
                    if (value == null) continue;
                    String price = value.substring(0, value.indexOf("Ԫ"));
                    je = je.add(new BigDecimal(price));
                }
                BigDecimal cd = new BigDecimal("0");
                List<WscpjgssfcdModel> ssfcdModels = ssfModel.getSsfcdModels();
                for (WscpjgssfcdModel ssfcdModel : ssfcdModels) {
                    String value = ssfcdModel.getCdje();
                    if (value == null) continue;
                    String price = value.substring(0, value.indexOf("Ԫ"));
                    cd = cd.add(new BigDecimal(price));
                }

                if (!je.equals(cd)) {
                    checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.SSWBTY, "��������Ѽ������", "��������ѳе����ֽ���ܺ�Ӧ����ǰ�ĵĽ���ܺ���ͬ", ErrorLevelEnum.LV_2));
                }
            }
        }
        return checkInfoItemVOS;
    }

//    public static void main(String[] args) {
//        System.out.println(new BigDecimal(1));
//        System.out.println(new BigDecimal("1.23"));
//    }
}
