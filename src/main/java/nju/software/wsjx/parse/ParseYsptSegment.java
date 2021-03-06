package nju.software.wsjx.parse;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.YsptAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.YsptCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.MsysCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MsysSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.YsptSsjlParseRule;

import java.util.List;

/**
 * 第一审普通程序（含简易程序转普通）裁定书解析逻辑
 * Created by zhuding on 2018/3/20.
 */
public class ParseYsptSegment extends DefaultParseSegment {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        ajjbqkParseRule = new YsptAjjbqkParseRule();
        return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
        cpfxgcParseRule = new YsptCpfxgcParseRule();
        return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
    }

    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        cpjgParseRule = new MsysCpjgParseRule();
        return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
        sscyrParseRule = new MsysSscyrParseRule();
        return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
    }

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) {
        ssjlParseRule = new YsptSsjlParseRule();
        return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
    }


}
