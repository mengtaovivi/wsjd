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
 *  һ��ö�
 * Created by away on 2018/4/18.
 */

public class YsssjlChecker extends SSJLChecker {

    public YsssjlChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
    }

    @Override
    public List<CheckInfoItemVO> check() {
        baseCheck();
        checkQsz(); // ����״
        checkFsz(); // ����״
        checkCs(); // ����
        checkFs(); // ����
        checkJjct(); // �ܾ���ͥ
        return checkInfoItemVOS;
    }

    /*
    ����״
        ����
        ������
     */
    private void checkQsz() {
        String tip = "��ʽӦ��Ϊ:  ��������������¡����գ���Ժ�յ�������������״��";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "����״")) {
            String name = wsssjlModel.getQsz().getName();
            String date = wsssjlModel.getQsz().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "ʱ��";
            } else if (name == null ) {
                wrongPart = "������";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ������״" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
    ����״
        ����
        ������
     */
    private void checkFsz() {
        String tip = "��ʽӦ��Ϊ:  ��������������¡����գ���Ժ�յ��������ķ���״��";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "����״")) {
            String name = wsssjlModel.getFsz().getName();
            String date = wsssjlModel.getFsz().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "ʱ��";
            } else if (name == null ) {
                wrongPart = "������";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ�ٷ���״" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
    ����
        ����
        ������
     */
    private void checkCs() {
        String tip = "��ʽӦ��Ϊ:  ԭ��������ڡ�������������¡�������Ժ����������롣";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "����")) {
            String name = wsssjlModel.getCs().getName();
            String date = wsssjlModel.getCs().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "����ʱ��";
            } else if (name == null ) {
                wrongPart = "������";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ��" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }



    /*
    ����
        ����
        ԭ��
        ����
     */
    private void checkFs() {
        String tip = "��ʽӦ��Ϊ:   ��������������¡����գ������������ԭ�������������ߡ�";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "����")) {
            String bg = wsssjlModel.getFs().getBg();
            String yg = wsssjlModel.getFs().getYg();
            String date = wsssjlModel.getFs().getFsrq();

            String wrongPart = "";
            if (bg == null && date == null && yg == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart += " ����ʱ�� ";
            } else if (bg == null ) {
                wrongPart = " ���� ";
            } else if (yg == null ) {
                wrongPart = " ԭ�� ";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ��" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
    �ܾ���ͥ
        ����
        ������
     */
    private void checkJjct() {
        String tip = "��ʽӦ��Ϊ:  ��������������¡����գ�����������Ʊ���������������ɾܲ���ͥ/δ����ͥ�����;��ͥ��";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "�ܾ���ͥ")) {
            String name = wsssjlModel.getJjct().getName();
            String date = wsssjlModel.getCs().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "�ܾ���ͥʱ��";
            } else if (name == null ) {
                wrongPart = "�ܾ���ͥ������";
            }
            if (!wrongPart.isEmpty()) { // ȱ��һ����
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ��" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

}
