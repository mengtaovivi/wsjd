package nju.software.wsjx.parserule.wscpfxgcparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.util.ArrayList;

/**
 * Created by zhuding
 */
public class MsgxCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule {

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) throws ParseException {
        MsysCpfxgcParseRule msysCpfxgcParseRule = new MsysCpfxgcParseRule();
        WscpfxgcModel wscpfxgcModel = msysCpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
        ArrayList<WscpfxgcFtModel> ftModellist = wscpfxgcModel.getFtModellist();
        for (WscpfxgcFtModel wscpfxgcFtModel : ftModellist) {
            if (wscpfxgcFtModel.getFlftmc().contains("�л����񹲺͹��������Ϸ�")){
                wscpfxgcFtModel.setSfcxf("�ǳ���");
            }else {
                wscpfxgcFtModel.setSfcxf("�ǳ���");
            }
        }
        return wscpfxgcModel;
    }
}
