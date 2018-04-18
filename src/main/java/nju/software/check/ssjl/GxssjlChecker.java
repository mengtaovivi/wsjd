package nju.software.check.ssjl;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.util.List;

/**
 * Created by away on 2018/4/17.
 */
public class GxssjlChecker extends SSJLChecker {

    public GxssjlChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
    }

    @Override
    public List<CheckInfoItemVO> check() {
        baseCheck();
        checkName("������", "getSsr");
        checkBqfy(); // ���뷨Ժ
        checkSscd(); // ���߲ö�
        return checkInfoItemVOS;
    }

    /*
        ���뷨Ժ
            ��Ժ����
            ����ʱ��
     */
    private void checkBqfy() {
        String tip = "��ʽӦ��Ϊ:  ��������������¡����գ�������������Ժ(д����������Ժ����)���뱾Ժָ����Ͻ";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "���뷨Ժ")) {
            String name = wsssjlModel.getBqfymc();
            String date = wsssjlModel.getBqsj();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "ʱ��";
            } else if (name == null ) {
                wrongPart = "��Ժ����";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ�ٱ���" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
        ���߲ö�
            ��Ժ����
            ����
     */
    private void checkSscd() {
        String tip = "����������������Ժ(��������)����������������²ö�����Ժ��������";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "���߲ö�")) {
            String name = wsssjlModel.getSscdfymc();
            String ah = wsssjlModel.getSscdah();
            String wrongPart = "";
            if (name == null && ah == null) {
                wrongPart = "";
            }
            if (name == null) {
                wrongPart = "��Ժ����";
            }
            if (ah == null) {
                wrongPart = "���� ";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ�����߲ö�" + wrongPart, tip, ErrorLevelEnum.LV_2));
            }
        }
    }

    // todo ����ö�
}
