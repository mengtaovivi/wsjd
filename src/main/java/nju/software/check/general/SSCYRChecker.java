package nju.software.check.general;

import nju.software.util.JsonParserUtil;
import nju.software.util.StringUtil;
import nju.software.util.Synonym;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by away on 2018/4/11.
 */
public class SSCYRChecker {

    private JsonParserUtil jsonParserUtil;

    public SSCYRChecker(JsonParserUtil jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    public List<CheckInfoItemVO> check(List<WssscyrModel> sscyrList) {
        this.jsonParserUtil = jsonParserUtil;

        List<CheckInfoItemVO> SSCYRErrorList = new ArrayList<>();
        // ���ÿ�����ϲ�������Ϣ�Ƿ�����
        for (WssscyrModel wssscyrModel : sscyrList) {
            CheckInfoItemVO checkInfoItemVO = checkInfo(wssscyrModel);
            if (null != checkInfoItemVO) {
                SSCYRErrorList.add(checkInfoItemVO);
            }
        }

        // ������ϲ������Ƿ���ֲ�ǡ�������ϵ�λ, ��������д����������
        List<CheckInfoItemVO> checkSsdw = checkSsdw(sscyrList);

        SSCYRErrorList.addAll(checkSsdw);
        return SSCYRErrorList;
    }

    private List<CheckInfoItemVO> checkSsdw(List<WssscyrModel> wssscyrModels) {
        List<CheckInfoItemVO> checkInfoItemVOList = new ArrayList<>();

        List<String> dsrRequirements = jsonParserUtil.getDsrRequirements();
        String dsrRequirementsArray = Arrays.toString(dsrRequirements.toArray());
        //���������ϵ�λ�ȶ�
        List<String> dsrList = new ArrayList<>();
        for (WssscyrModel wssscyrModel : wssscyrModels) {
            String ssdw = wssscyrModel.getSsdw();
            if (null != ssdw) {
                dsrList.add(ssdw);
            }
        }

        for (String s : dsrList) {
//            System.out.println("s = " + s);
            if (!Synonym.isContains(dsrRequirements, s)) {
                String errMsg = "���ϲ����˵�λ��ӦΪ " + s;
                String tip = "���ϲ����˵�λӦ��Ϊ: " + dsrRequirementsArray + "�е�һ��";
                checkInfoItemVOList.add(new CheckInfoItemVO(ErrorType.YSCW, errMsg, tip, ErrorLevelEnum.LV_2));
            }
        }

        return checkInfoItemVOList;
    }

    /**
     * ������ϲ�������Ϣ�Ƿ�����, ����
     *
     * (��Ȼ��)
     * �������Ա𡢳��������ա����塢ס����
     * ������ְҵ���߹�����λ��ְ����ȷ�ģ����Բ�����
     *
     * (����)
     * д�����ƺ�ס����������һ��д�����������˵�������ְ��
     */
    private CheckInfoItemVO checkInfo(WssscyrModel wssscyrModel) {
        StringBuilder errorMsg = new StringBuilder();
        List<String> missPartList = new ArrayList<>();
        String name = wssscyrModel.getSscyr();
        boolean nameValid = checkName(wssscyrModel.getSscyr(), missPartList);
        if (nameValid) {
            errorMsg.append(name);
        }
        String tip = "";
        if (isNaturalDSR(wssscyrModel)) {
            tip = "����������Ȼ�˵ģ�Ӧ��д�����������Ա𡢳��������ա����塢ְҵ���߹�����λ��ְ��ס�����������Ա����������Ծ������֤������֤��Ϊ׼�� ������ְҵ���߹�����λ��ְ����ȷ�ģ����Բ�����";
            checkGender(wssscyrModel.getXb(), missPartList);
            checkBirthday(wssscyrModel.getCsrq(), missPartList);
            checkEthnicity(wssscyrModel.getMz(), missPartList);
            checkAddress(wssscyrModel.getDsrdz(), missPartList);
        } else if (isLegalDSR(wssscyrModel)) {
            tip = "�������Ƿ��˵ģ�д�����ƺ�ס����������һ��д�����������˵�������ְ��";
            checkAddress(wssscyrModel.getDsrdz(), missPartList);
            checkFDDBR(wssscyrModel.getFddbr(), missPartList);
        } else if (isFDDBR(wssscyrModel)) {
            tip = "�������Ƿ��˵ģ�д�����ƺ�ס����������һ��д�����������˵�������ְ��";
            checkZW(wssscyrModel.getDsrzw(), missPartList);
        }

        if (missPartList.isEmpty()) {
            return null;
        } else {
            errorMsg.append(" ȱ�� ");
            for (String s : missPartList) {
                errorMsg.append(" \"").append(s).append("\" ");
            }
            return new CheckInfoItemVO(ErrorType.YSQS, errorMsg.toString(), tip, ErrorLevelEnum.LV_2);
        }
    }

    // ������ȷ���� true, ���� false
    private boolean checkName(String name, List<String> missPartList) {
        // TODO �������ϲ����������Ʋ�?
        return isMissPart(name, "����", missPartList);
    }

    private void checkGender(String gender, List<String> missPartList) {
        isMissPart(gender, "�Ա�", missPartList);
    }

    private void checkBirthday(String birthday, List<String> missPartList) {
        isMissPart(birthday, "��������", missPartList);
    }

    private void checkEthnicity(String ethnicity, List<String> missPartList) {
        isMissPart(ethnicity, "����", missPartList);
    }

    private void checkAddress(String address, List<String> missPartList) {
        isMissPart(address, "ס��", missPartList);
    }

    private void checkFDDBR(String fddbr, List<String> missPartList) {
        isMissPart(fddbr, "����������", missPartList);
    }

    private void checkZW(String zw, List<String> missPartList) {
        isMissPart(zw, "����������ְ��", missPartList);
    }

    private boolean isMissPart(String part, String partName,  List<String> missPartList) {
        if (null == part || StringUtil.trim(part).equals("")) {
            missPartList.add(partName);
            return false;
        }
        return true;
    }

    // �Ƿ��ǵ�����������Ȼ��
    private boolean isNaturalDSR(WssscyrModel wssscyrModel) {
        String dsrlb = wssscyrModel.getDsrlb();
        String dsrlx = wssscyrModel.getDsrlx();
        return (null != dsrlb) && (null != dsrlx) && (dsrlx.equals("��Ȼ��"));
    }

    // �Ƿ��ǵ��������Ƿ���
    private boolean isLegalDSR(WssscyrModel wssscyrModel) {
        String dsrlb = wssscyrModel.getDsrlb();
        String dsrlx = wssscyrModel.getDsrlx();
        return (null != dsrlb) && (null != dsrlx) && (dsrlx.equals("����"));
    }

    // �Ƿ��Ƿ���������
    private boolean isFDDBR(WssscyrModel wssscyrModel) {
        String sssf = wssscyrModel.getSssf();
        return (null != sssf) && (sssf.equals("����������"));
    }
}
