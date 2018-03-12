package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.MZEnum;
import nju.software.wsjx.model.Enum.WhcdEnum;
import nju.software.wsjx.model.Enum.ZWEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QkqkModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.StringUtil;
/**
 * ����һ�����ϲ����˽���
 * @author wangzh
 *
 */
public class XsysSscyrParseRule extends GeneralSscyrCommonRule implements SscyrParseRule{

    public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse){
    	List<String> sscyr = wsAnalyse.getSscyr();
    	List<String> ajjbqk = wsAnalyse.getAjjbqk();
        String ssjl = wsAnalyse.getSsjl();
        String wsnr = wsAnalyse.getWsnr();
        List<WssscyrModel> wssscyrModellist = new ArrayList<WssscyrModel>();
        String dt = "";
        String wdt = "";
        if (ssjl != null) {
            ArrayList<String> ssjlfd = WsAnalyse.getWholeContent(ssjl);
            for (int i = 0; i < ssjlfd.size(); i++) {
                if (ssjlfd.get(i).contains("δ��ͥ")
                        || ssjlfd.get(i).contains("û�е�ͥ")) {
                    wdt = ssjlfd.get(i);
                    break;
                } else if (ssjlfd.get(i).contains("��ͥ")) {
                    dt = ssjlfd.get(i);
                    break;
                }
            }
        }
        for (int i = 0; i <sscyr.size() ; i++) {
            WssscyrModel wssscyrModel = new WssscyrModel();
            ArrayList<String> contentlist=WsAnalyse.getWholeContent(sscyr.get(i));
            String sscyrallinfo="";
            for(String sscyrinfo:contentlist){
                sscyrallinfo+=sscyrinfo;
            }
            wssscyrModel.setSscyrallinfo(sscyrallinfo);
            String content=contentlist.get(0);
            //����������ݡ���������
            String sssf = HeadEnum.getHead(WsAnalyse.deBracket(content));//�������
            if ((StringUtil.contains(content,"������������ԭ����")||StringUtil.contains(content,"�����������ϱ�����"))&&StringUtil.contains(content,"������")){
                if (StringUtil.contains(content,"���ϴ�����")){
                    sssf="���ϴ�����";
                }else if (StringUtil.contains(content,"ί�д�����")){
                    sssf="ί�д�����";
                }
            }
            if (sssf!=null){
                wssscyrModel.setSssf(sssf);
                content=WsAnalyse.deBracket(content);
                int index = content.indexOf(sssf);
                String ssmc = content.substring(index + sssf.length(),
                        content.length());
                //ȥ�������������ð��
                if(ssmc.contains("��")){
                    ssmc=ssmc.replaceFirst("��", "");
                }
                wssscyrModel.setSscyr(ssmc);
            }
            //�������ϵ�λ
            String ssdw=sssf;
            if (ssdw!=null){
                String[] ssdwlist={"���߻���","������","������","������������ԭ����","����������������ԭ����","�����������ϱ�����"};
                for (int j = 0; j <ssdwlist.length ; j++) {
                    if (ssdw.contains(ssdwlist[j])){
                        wssscyrModel.setSsdw(ssdw);
                    }
                }
            }
            //�������������
            String dsrlb=null;
            String[] dlr={"�绤��","����������","���ϴ�����","ί�д�����","ί�б绤��","ָ���绤��","������","�໤��"};
            if(StringUtil.equals(sssf,"���߻���")){
                dsrlb="���߷�";
            }else if(StringUtil.equals(sssf,"������")||StringUtil.equals(sssf,"����")||StringUtil.equals(sssf,"�����������ϱ�����")
                    ||StringUtil.equals(sssf,"���浥λ")||StringUtil.equals(sssf,"�����������ϱ���")){
                dsrlb="Ӧ�߷�";
            }else if (StringUtil.equals(sssf,"������������ԭ����")||StringUtil.equals(sssf,"������")||StringUtil.equals(sssf,"�������߸�����������ԭ����")
                    ||StringUtil.equals(sssf,"������")||StringUtil.equals(sssf,"������λ")){
                dsrlb="���߷�";
            }
            if (sssf!=null){
                for(int j=0;j<dlr.length;j++){
                    if(sssf!=null&&sssf.equals(dlr[j])){
                        dsrlb="������";
                    }
                }
            }
            wssscyrModel.setDsrlb(dsrlb);

            //���������˺������Ϣ
            for (int j = 0; j < contentlist.size(); j++) {
                String zjlx=null;//֤������
                String zjhm = null;
                // ����֤�����͡�֤����
                if (contentlist.get(j).indexOf("���") != -1) {
                    zjlx="���֤";
                    Pattern pattern = Pattern.compile("\\d{18}|\\d{17}(\\d|X|x)");
                    Matcher match = pattern.matcher(contentlist.get(j));
                    while (match.find()){
                        zjhm=match.group();
                    }
                }else if(contentlist.get(j).indexOf("ִҵ֤")!=-1){
                    zjlx="ִҵ֤";
                    ArrayList<String> zjxx = (ArrayList<String>) FcUtil
                            .getWholeToken(contentlist.get(j));
                    Pattern pattern = Pattern.compile("\\d");
                    for (int k = 0; k < zjxx.size(); k++) {
                        int count = 0;
                        Matcher match = pattern.matcher(zjxx.get(k));
                        while (match.find())
                            count++;
                        if (count >= 14)
                            zjhm = zjxx.get(k);
                    }
                }else if (contentlist.get(j).indexOf("����")!=-1){
                    zjlx="����";
                    Pattern pattern = Pattern.compile("[a-zA-Z0-9]{5,17}");
                    Matcher match = pattern.matcher(contentlist.get(j));
                    while (match.find()){
                        zjhm=match.group();
                    }

                }
                if (zjlx!=null){
                    wssscyrModel.setZjlx(zjlx);
                }
                // ������ַ
                String dsrdz = null;
                if (contentlist.get(j).indexOf("ס����") != -1
                        && contentlist.get(j).indexOf("ס����") < 3) {
                    dsrdz = contentlist.get(j).substring(
                            contentlist.get(j).indexOf("ס����") + 3,
                            contentlist.get(j).length());

                } else if (contentlist.get(j).indexOf("ס") != -1
                        && contentlist.get(j).indexOf("ס") < 3) {
                    dsrdz = contentlist.get(j).substring(
                            contentlist.get(j).indexOf("ס") + 1,
                            contentlist.get(j).length());

                }
                //������֯��������
                String zzjgdm=null;
                String contentinfo=contentlist.get(j);
                int zzindex=contentinfo.indexOf("��֯��������");
                if(zzindex!=-1){
                    zzjgdm=contentinfo.substring(zzindex, contentinfo.length());
                    String regex="[A-Za-z0-9]{8}-[A-Za-z0-9]{1}|[A-Za-z0-9]{9}|[A-Za-z0-9]{9}|[A-Za-z0-9]{8}��[A-Za-z0-9]{1}";
                    Pattern pattern=Pattern.compile(regex);
                    Matcher matcher=pattern.matcher(zzjgdm);
                    while(matcher.find()){
                        zzjgdm=matcher.group();
                    }
                }
                if(zzjgdm!=null){
                    wssscyrModel.setZzjgdm(zzjgdm);
                }
                // �����Ա�
                String xb = null;
                if (contentlist.get(j).indexOf("��") != -1
                        && contentlist.get(j).length() < 4) {
                    xb = "��";
                } else if (contentlist.get(j).indexOf("Ů") != -1
                        && contentlist.get(j).length() < 4) {
                    xb = "Ů";
                }
                // ������������
                String csrq = null;
                int rq = 0;
                if (contentlist.get(j).indexOf("��") != -1)
                    rq++;
                if (contentlist.get(j).indexOf("��") != -1)
                    rq++;
                if (contentlist.get(j).indexOf("��") != -1)
                    rq++;
                if (contentlist.get(j).indexOf("��") != -1)
                    rq++;
                if (rq == 4&&(contentlist.get(j).indexOf("��") - 3)>0) {
//					XX��XX��XX��
                    if(contentlist.get(j).indexOf("��")>3){
                        csrq = contentlist.get(j).substring(
                                contentlist.get(j).indexOf("��") - 4,
                                contentlist.get(j).indexOf("��") + 1);
                    }

                }
                // ������ϸ��������
                String year = null;
                String month = null;
                String day = null;
                if (csrq != null) {
                    year = csrq.substring(csrq.indexOf("��") - 4,
                            csrq.indexOf("��"));
                    month = csrq.substring(csrq.indexOf("��") + 1,
                            csrq.indexOf("��"));
                    day = csrq.substring(csrq.indexOf("��") + 1,
                            csrq.indexOf("��"));
                }
                // ��������
                String mz = null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    mz = MZEnum.getMZ(contentlist.get(j));
                }

                // ����ְ��
                String dsrzw = null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    dsrzw = ZWEnum.getZW(contentlist.get(j));
                } else {
                    dsrzw = ZWEnum.getZW(contentlist.get(j));
                }

                // �����Ļ��̶�
                String dsrwhcd = null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    dsrwhcd = WhcdEnum.getWhcd(contentlist.get(j));
                }
                //����ѧλ
                String dsrxw = null;
                if (dsrwhcd!=null){
                    if (StringUtil.contains(dsrwhcd,"��ѧ")){
                        dsrxw="ѧʿ";
                    }else if (StringUtil.contains(dsrwhcd,"˶ʿ")){
                        dsrxw="˶ʿ";
                    }else{
                        dsrxw="����";
                    }
                }
                if (dsrxw!=null){
                    wssscyrModel.setDsrxw(dsrxw);
                }
                //����������ò
                String zzmm=null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    if (StringUtil.equals(contentlist.get(j),"Ⱥ��")){
                        zzmm="Ⱥ��";
                    }else if (StringUtil.equals(contentlist.get(j),"�й���Ա")){
                        zzmm="�й���Ա";
                    }

                }
                //�����������ڵ�
                String hjd=null;
                if (StringUtil.contains(contentlist.get(j),"������")){
                    hjd=contentlist.get(j).substring(contentlist.get(j).indexOf("������")+3,contentlist.get(j).length());
                }else if (StringUtil.contains(contentlist.get(j),"�������ڵ�Ϊ")){
                    hjd=contentlist.get(j).substring(contentlist.get(j).indexOf("�������ڵ�Ϊ")+6,contentlist.get(j).length());
                }else if (StringUtil.contains(contentlist.get(j),"�������ڵ�")) {
                    hjd = contentlist.get(j).substring(contentlist.get(j).indexOf("�������ڵ�") + 5, contentlist.get(j).length());
                }else if (StringUtil.contains(contentlist.get(j),"������ַ��")) {
                    hjd = contentlist.get(j).substring(contentlist.get(j).indexOf("������ַ��") + 5, contentlist.get(j).length());
                }
                // �������������ݷ���model��
                if (dsrdz != null) {
                    //ȥ����ַ�е�ð��
                    if(dsrdz.contains("��")){
                        dsrdz=dsrdz.replaceFirst("��", "");
                    }
                    wssscyrModel.setDsrdz(dsrdz);
                }
                if (xb != null) {
                    wssscyrModel.setXb(xb);
                }
                if (csrq != null) {
                    wssscyrModel.setCsrq(csrq);
                }
                if (zjhm != null) {
                    wssscyrModel.setZjhm(zjhm);
                }
                if (mz != null) {
                    wssscyrModel.setMz(mz);
                }
                if (dsrzw != null&&(!dsrzw.equals("ũ��")&&!dsrzw.equals("��ҵ")&&!dsrzw.equals("�޹̶�ְҵ")&&!dsrzw.equals("��ְҵ")&&!dsrzw.equals("��")&&!dsrzw
                        .equals("����"))) {
                    wssscyrModel.setDsrzw(dsrzw);
                }
                if (year != null && month != null && day != null) {
                    wssscyrModel.setYear(year);
                    wssscyrModel.setMonth(month);
                    wssscyrModel.setDay(day);

                }
                if (dsrwhcd!=null){
                    wssscyrModel.setDsrwhcd(dsrwhcd);
                }
                if (zzmm!=null){
                    wssscyrModel.setZzmm(zzmm);
                }
                if (hjd!=null){
                    wssscyrModel.setHjd(hjd);
                }
            }
            //���������˹���
            setDsrgj(wssscyrModel);
            //������Ȼ�����
            setZrrsf(wssscyrModel,contentlist);
            //�����Ƿ񱻺���
            if (StringUtil.equals(wssscyrModel.getDsrlb(),"���߷�")||StringUtil.equals(wssscyrModel.getDsrlb(),"Ӧ�߷�")){
                setIsBhr(wssscyrModel,ajjbqk);
            }
            //����������������ԭ��������
            if (StringUtil.equals(wssscyrModel.getSssf(),"������������ԭ����")||StringUtil.equals(wssscyrModel.getSssf(),"����������������ԭ����")||StringUtil.equals(wssscyrModel.getSssf(),"�������߸�����������ԭ��")){
                setMsssygrlx(wssscyrModel);
            }
            //���������������������̿������ڷ�����Ϳ������ڷ���
            if (StringUtil.equals(wssscyrModel.getDsrlb(),"Ӧ�߷�")){
                setXszrablity(wssscyrModel,wsnr);
                setHxkyqfz(wssscyrModel,contentlist);
                setJskyqfz(wssscyrModel,contentlist);
            }

            //�����Ѻ����
            setJycs(wssscyrModel,contentlist);
            //����ǿ�ƴ�ʩ
            setQzcs(wssscyrModel,sscyr.get(i),wsnr);
            //����ǰ�����
            setQkqk(wssscyrModel,sscyr.get(i),wsnr);
            wssscyrModellist.add(wssscyrModel);
        }


        return  wssscyrModellist;
    }

    private void setQkqk(WssscyrModel wssscyrModel, String sscyrinfo,String wsnr) {
        List<QkqkModel> qkqkModelList = new ArrayList<QkqkModel>();
        ArrayList<String> contentlist=new ArrayList<String>();
        ArrayList<String> qkqk = new ArrayList<String>();//ǰ�����
        String[] jhsplit=sscyrinfo.split("��");
        for (int i = 0; i <jhsplit.length ; i++) {
            String fhcontent=jhsplit[i];
            String[] fhsplit=fhcontent.split("��");
            for(int k=0;k<fhsplit.length;k++){
                if(fhsplit[k].length()>0){
                    contentlist.add(fhsplit[k]);
                }
            }
        }
        if (contentlist.size()>1){
            for (int i = 0; i <contentlist.size() ; i++) {
                if (contentlist.get(i).contains("�д�")||contentlist.get(i).contains("��������")||contentlist.get(i).contains("�䶾")||contentlist.get(i).contains("�Ͷ�����")||
                        contentlist.get(i).contains("����")||contentlist.get(i).contains("����")||contentlist.get(i).contains("����")||contentlist.get(i).contains("����")
                        ||contentlist.get(i).contains("�ݿۼ�ʻ֤")){
                    qkqk.add(contentlist.get(i));
                }
            }
        }
        for (int i = 0; i <qkqk.size() ; i++) {
//            System.out.println(qkqk.get(i));
            QkqkModel qkqkModel = new QkqkModel();
            //��������
            String zm=null;
            Pattern pattern = Pattern.compile("��.+?��");
            Matcher matcher = pattern.matcher(qkqk.get(i));
            while (matcher.find()){
                zm=matcher.group();
                //System.out.println("ԭ��"+matcher.group());
            }

            //�����д���Ժ
            String pcfy=null;
            if (StringUtil.contains(qkqk.get(i),"Ժ")&&StringUtil.contains(qkqk.get(i),"��")) {
                if (qkqk.get(i).indexOf("��") < qkqk.get(i).indexOf("Ժ"))
                    pcfy = qkqk.get(i).substring(qkqk.get(i).indexOf("��") + 1, qkqk.get(i).indexOf("Ժ") + 1);
            }
            //����ǰ�����
            String[] xzqklblist={"��������","�䶾","�Ͷ�����","����"};
            boolean isXzqk=false;
            for (String qklb: xzqklblist) {
                if (qkqk.get(i).contains(qklb)){
                    isXzqk=true;
                }
            }
            if (isXzqk){
                qkqkModel.setQklb("����ǰ��");
            }else{
                qkqkModel.setQklb("����ǰ��");
            }
            //��������ʱ�䡢�����ͷ�����
            List<String> timelist=new ArrayList<String>();
            Pattern patterntime = Pattern.compile("\\d{4}��\\d{1,2}��\\d{1,2}��|\\d{4}��\\d{1,2}��|ͬ��\\d{1,2}��\\d{1,2}��");
            Matcher matchertime = patterntime.matcher(qkqk.get(i));
            while (matchertime.find()){
                timelist.add(matchertime.group());
            }
            for (int j=0;j<timelist.size();j++){
                int pcindex=qkqk.get(i).indexOf("�д�");
                int xmsfIndex=qkqk.get(i).indexOf("�ͷ�");
                int cfTimeIndex=qkqk.get(i).indexOf(timelist.get(j));
                if (pcindex!=-1&&pcindex>=cfTimeIndex){
                    qkqkModel.setCfTime(timelist.get(j));
                }
                for (String xzqklb:xzqklblist){
                    int xzqkindex=qkqk.get(i).indexOf(xzqklb);
                    if (xzqkindex!=-1&&xzqkindex>=cfTimeIndex){
                        qkqkModel.setCfTime(timelist.get(j));
                    }
                }
                if (xmsfIndex!=-1&&cfTimeIndex<=xmsfIndex&&cfTimeIndex>=pcindex){
                    qkqkModel.setXmsfTime(timelist.get(j));
                }
            }
            //��������ԭ��
            List<String> cfReason=new ArrayList<String>();
            Pattern patterncfReason = Pattern.compile("�����ӷ�.{1,8}����������|��.+?��");
            Matcher matchercfReason = patterncfReason.matcher(qkqk.get(i));
            while (matchercfReason.find()){
                String cfyy=matchercfReason.group();
                cfyy=cfyy.substring(1,cfyy.length());
                cfReason.add(cfyy);
            }
//            if (StringUtil.contains(qzcscontent,"�򱾰�")){
//                qzcsReason.add("����");
//            }
            String[] cfzmlist={"��֯����","�������","���䶾Ʒ��","������ľ","����������","�ش��Ͷ���ȫ�¹���","ƭȡ����","���ҹ�����������","��������Ʒ��","�Ƿ���ҽ��","���ÿ�թƭ��","������������","����","�Ƿ�������","����������������","�Ż���","�鿪��ֵ˰ר�÷�Ʊ����","���η���������","������Ʒ","�����д����ơ�����","�ƻ������豸","Ѱ������","�Ƿ��չ���������󡢱�ΣҰ��������Ʒ��","������","������ľ��","�ķ���ľ��","�����ж����к�ʳƷ��","���ÿ�թƭ","α֤��","�����ٴ�","�Ƿ�����ǹ֧","���ۼ�ҩ��","��ͨ������","���Ĳ���","̰����","���Ρ�������������","����ٻ�������","�Ƿ����ж�Ʒ��","�Ƿ��н�","����","�Ƿ�����ע���̱��ʶ����","�Ƿ����졢���۷Ƿ������ע���̱��ʶ����","�������˺���","���۷Ƿ������ע���̱��ʶ����","�Ƿ���Ӫ��","��֯���쵼�����","�Ƿ����졢���۷Ƿ�����ķ�Ʊ��","Σ�ռ�ʻ����","����ɱ����","��λ�л���","������ĳ���","��թ������","�ܻ�","��Σ�շ���Σ��������ȫ��","����������������","�Ƿ����졢���۷Ƿ������ע���̱��ʶ��","������Ʒ��","������","��������������","ְ����ռ��","���Ρ���������������","����α�Ӳ�Ʒ","��ͬթƭ","�ܲ�ִ���о����ö�","���ְ����","����������Ʒ","ʧ����","�Ƿ����չ��ڴ����","������ͯ","����ְȨ��","��ʧ����������","�ƻ����õ�����ʩ","���ְ�ط���","�Ƿ��н���","�����������ж����к�ʳƷ","���������ж��к�ʳƷ","�����������ж��к�ʳƷ","��������","�Ĳ�","����α�Ӳ�Ʒ��","����ĳ�","��������","թƭ","Ѱ��������","�ƻ���ȼ�ױ��豸","��Σ�ռ�ʻ��","Ź������","�ش������¹���","����ĳ���","����","α����һ���֤��","�������ѱ���ȫ�¹���","�������һ���֤��","�Ƿ�ռ��ũ�õ�","�Ƿ����챬ը����","�����������ж��к�ʳƷ��","�������һ���֤����","α�졢���졢�������һ��ع��ġ�֤����ӡ����","���ա�����������������","������","���������۲����ϰ�ȫ��׼��ʳƷ","ǿ�Ƚ���","���ԡ����Ρ���������������","�Ƿ����ж�Ʒ","��թ����","�ķ���ľ","����ɱ��","�����˺�����","��ͨ����","��λ�ܻ�","�����˺���","���ɷǷ���Ӫ","��ͨ���°�","�Ƿ��ɿ���","������Ʒ����","�����˺�","����","���������۲����ϰ�ȫ��׼��ʳƷ��","Σ�ռ�ʻ","�Ĳ���","����������������","���۷Ƿ�����ķ�Ʊ��","������������������","���ӵ�����","̰��","�ƻ����õ�����ʩ��","�����˺�����","�鿪��ֵ˰ר�÷�Ʊ","��������","�л���","��������","�ܻ�����","Σ�ռ�ʻ��","�Ƿ��ɷ������ص㱣��ֲ����","Υ���涨","���ۼ�ҩ","���Ρ������������á���������������","���١�Ѱ������","թƭ��","���Ρ������������÷���","�Ƿ�����ǹ֧��","�ķ���ľ��","ְ����ռ","����������α�Ӳ�Ʒ","������������","ʧ��","������ȫ�¹���","���Է���","��ʧ��������","����ٻ�����","��������������","�ַ�����Ȩ��","���䶾Ʒ","���Ƿ����ж�Ʒ��","���ԡ����Ρ������������÷���","���������۲����ϰ�ȫ��׼��ʳƷ����","��ðע���̱���"};
            for (String cfzm:cfzmlist){
                if (StringUtil.contains(qkqk.get(i),cfzm)&&cfReason.size()==0){
                    cfReason.add(cfzm);
                }
            }
            if (cfReason.size()>0){
                qkqkModel.setCfReason(cfReason);
            }
            for (String cfyyprint:cfReason){
                //System.out.println(cfyyprint);
            }
            //����������λ
            String cfdw=null;
            Pattern patterndw=Pattern.compile("[������].{1,13}?�־�|[������].{1,18}?�ɳ���|[������].{1,15}?��|[������].{1,18}?Ժ|��Ժ|����������|[������].{1,15}?������|��ָ������");
            Matcher matcherdw=patterndw.matcher(qkqk.get(i));
            while (matcherdw.find()){
                cfdw=matcherdw.group();
                if(!cfdw.equals("��Ժ"))
                    cfdw=cfdw.substring(1,cfdw.length());
            }
            if (StringUtil.equals(cfdw,"��Ժ")||StringUtil.equals(cfdw,"��Ժ")){
                String ws=wsnr.substring(0,30);
                ws=ws.replaceAll("\n","");
                if (ws.indexOf("��Ժ")!=-1){
                    cfdw=ws.substring(0,ws.indexOf("��Ժ")+2);
                }
            }
            if (cfdw!=null){
                qkqkModel.setCfdw(cfdw);
                //System.out.println("��λ��"+cfdw);
            }
            //����������ʽ
            String[] cfxslist={"û��Υ�����á�û�շǷ�����","�ݿۼ�ʻ֤","����ͽ��","��������","����","����","����ͽ��","ǿ�Ƹ���䶾","�����䶾","����","����","����","�Ͷ�����"};
            String cfxs=null;
            for (String cfxsitem:cfxslist){
                if (StringUtil.contains(qkqk.get(i),cfxsitem)){
                    cfxs=cfxsitem;
                }
                if (cfxs==null&&StringUtil.contains(qkqk.get(i),"����")){
                    cfxs="����������";
                }
            }
            if (cfxs!=null){
                qkqkModel.setCfxs(cfxs);
                //System.out.println(cfxs);
            }

            //��������
            List<String> xq=new ArrayList<String>();
            int pcindex =qkqk.get(i).indexOf("�д�");
            pcindex=pcindex==-1?0:pcindex;
            String timeinfo=qkqk.get(i).substring(pcindex,qkqk.get(i).length());
            Pattern  pattern1 = Pattern.compile("([һ�����������߰˾�ʮ]{1,2}��?��)|([һ�����������߰˾�ʮ]{1,2}��[һ�����������߰˾�ʮ]{1,2}��?��)|([һ�����������߰˾�ʮ]{1,2}��)|[һ�����������߰˾�ʮ]{1,2}��");
            Matcher matcher1 = pattern1.matcher(timeinfo);
            while (matcher1.find()){
                xq.add(matcher1.group());
                //System.out.println(matcher1.group());
            }
            if (xq.size()==2){
                if (StringUtil.contains(timeinfo,"����")&&StringUtil.contains(timeinfo,"����ͽ��")){
                    xq.set(0,"����ͽ������"+xq.get(0));
                    xq.set(1,"����"+xq.get(1));
                }else if (StringUtil.contains(timeinfo,"��������Ȩ��")&&StringUtil.contains(timeinfo,"����ͽ��")){
                    xq.set(0,"����ͽ������"+xq.get(0));
                    xq.set(1,"��������Ȩ��"+xq.get(1));
                }
            }
            if (xq.size()>0){
                qkqkModel.setCfxq(xq);
            }
            qkqkModelList.add(qkqkModel);
        }
        wssscyrModel.setQkqkList(qkqkModelList);
    }

    private void setQzcs(WssscyrModel wssscyrModel, String sscyrinfo,String wsnr) {
        List<QzcsModel> qzcsModellist = new ArrayList<QzcsModel>();
        String[] qzcslxlist={"����","���Ӿ�ס","ȡ������","ȡ������","�д�","���Ƴ���","����","ץ��","�Ѻ","׽��","����","�ͷ�"};
        ArrayList<String> contentlist=new ArrayList<String>();
        String[] jhsplit=sscyrinfo.split("��");
        for (int i = 0; i <jhsplit.length ; i++) {
            String fhcontent=jhsplit[i];
            String[] fhsplit=fhcontent.split("��");
            for(int k=0;k<fhsplit.length;k++){
                if(fhsplit[k].length()>0){
                    contentlist.add(fhsplit[k]);
                }
            }
        }
        ArrayList<String> qzcscontents=new ArrayList<String>();
        if (contentlist.size()>1){
            for (int i = 0; i <contentlist.size() ; i++) {
                //���ÿ�仰���й���ǿ�ƴ�ʩ�ʵ�λ��
                Set<Integer> qzcsindexSet=new TreeSet<>();
                for (int j = 0; j <qzcslxlist.length ; j++) {
                    if (contentlist.get(i).indexOf(qzcslxlist[j])!=-1){
                        int temp=0;
                        while(true){
                            int index=contentlist.get(i).indexOf(qzcslxlist[j],temp);
                            if (index==-1){
                                break;
                            }
                            qzcsindexSet.add(index+qzcslxlist[j].length());
                            temp=index+qzcslxlist[j].length();
                        }
                    }
                }
                Integer[] qzcsindexs=qzcsindexSet.toArray(new Integer[0]);
                if (qzcsindexs.length>=1){
                    qzcscontents.add(contentlist.get(i).substring(0,qzcsindexs[0]));
                    for (int j=0; j<qzcsindexSet.size()-1;j++){
                        qzcscontents.add(contentlist.get(i).substring(qzcsindexs[j],qzcsindexs[j+1]));
                    }
                }
            }
            for (int i = 0; i < qzcscontents.size(); i++) {
                if (StringUtil.contains(qzcscontents.get(i),"���Ѻ")||StringUtil.contains(qzcscontents.get(i),"�д�")||StringUtil.contains(qzcscontents.get(i),"�����ͷ�")||StringUtil.contains(qzcscontents.get(i),"��������")){
                    qzcscontents.remove(i);
                }
            }
            String qzcsTimeCompletion = null;
            String year = null;
            String month = null;
            String day = null;
            for (String qzcscontent:qzcscontents) {
                //System.out.println(qzcscontent);
                QzcsModel qzcsModel = new QzcsModel();
                //����ǿ�ƴ�ʩ����
                String qzcsCategory=null;
                for (String qzcxlx:qzcslxlist){
                    if (StringUtil.contains(qzcscontent,qzcxlx)){
                        qzcsCategory=qzcxlx;
                    }
                }
                if (StringUtil.equals(qzcsCategory,"ץ��")||StringUtil.equals(qzcsCategory,"׽��")){
                    qzcsCategory="�Ѻ";
                }
                if (StringUtil.contains(qzcscontent,"����׼����")){
                    qzcsCategory="����׼����";
                }
                if (StringUtil.contains(qzcscontent,"���ȡ������")){
                    qzcsCategory="���";
                }
                if (StringUtil.contains(qzcscontent,"����")){
                    qzcsCategory="����";
                }
                if (StringUtil.contains(qzcscontent,"��������")){
                    qzcsCategory="�Ѻ";
                }
                if (StringUtil.contains(qzcscontent,"�ͷ�")){
                    qzcsCategory="���";
                }
                if (StringUtil.contains(qzcscontent,"ȡ������")){
                    qzcsCategory="ȡ������";
                }
                //System.out.println("���ͣ�"+qzcsCategory);
                qzcsModel.setQzcsCategory(qzcsCategory);
                //����ǿ�ƴ�ʩִ��ʱ��
                String qzcsTime=null;
                ArrayList<String> qzcsTimelist = new ArrayList<String>();
                Pattern patterntime = Pattern.compile("\\d{4}ͬ��\\d{1,2}��\\d{1,2}��|\\d{4}��\\d{1,2}��\\d{1,2}��|ͬ��\\d{1,2}��\\d{1,2}��|ͬ��\\d{1,2}��|\\d{1,2}��\\d{1,2}��|\\d{4}��\\d{1,2}��\\d{1,2}");
                Matcher matchertime = patterntime.matcher(qzcscontent);
                while (matchertime.find()){
                    qzcsTimelist.add(matchertime.group());
                    //System.out.println(matchertime.group());
                }
                //����ÿ��ǿ�ƴ�ʩֻ����һ�����ڣ��ʹ��ڶ�����ڵ����
                if (qzcsTimelist.size()==1){
                    qzcsTime=qzcsTimelist.get(0);
                    if (!StringUtil.contains(qzcsTime,"��")){
                        qzcsTime=qzcsTime+"��";
                    }
                    if (Pattern.compile("\\d{4}ͬ��\\d{1,2}��\\d{1,2}��").matcher(qzcsTime).find()){
                        qzcsTime=qzcsTime.substring(0,4)+"��"+qzcsTime.substring(6,qzcsTime.length());
                    }
                    //�ж��Ƿ����������ڡ���ȡ��������
                    if (qzcsTime!=null){
                        Pattern patterntimecomple = Pattern.compile("\\d{4}��\\d{1,2}��\\d{1,2}��");
                        Matcher matchertimecomple = patterntimecomple.matcher(qzcsTime);
                        if (matchertimecomple.find()){
                            qzcsTimeCompletion = qzcsTime;
                            year=qzcsTimeCompletion.substring(0,4);
                            month=qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("��")+1,qzcsTimeCompletion.indexOf("��"));
                            day = qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("��")+1,qzcsTimeCompletion.indexOf("��"));
                        }
                    }
                    //�ж�ͬ������
                    if (StringUtil.contains(qzcsTime,"ͬ��")){
                        if (year!=null)
                            qzcsTime=year+qzcsTime.substring(1,qzcsTime.length());
                        if (StringUtil.contains(qzcsTime,"��")&&StringUtil.contains(qzcsTime,"��")){
                            month = qzcsTime.substring(qzcsTime.indexOf("��")+1,qzcsTime.indexOf("��"));
                            day = qzcsTime.substring(qzcsTime.indexOf("��")+1,qzcsTime.indexOf("��"));
                        }
                    }
                    //�ж�ͬ�µ����
                    if (StringUtil.contains(qzcsTime,"ͬ��")){
                        if (year!=null&&month!=null)
                            qzcsTime=year+"��"+month+"��"+qzcsTime.substring(qzcsTime.indexOf("��")+1,qzcsTime.length());
                        day=qzcsTime.substring(qzcsTime.indexOf("��")+1,qzcsTime.indexOf("��"));
                    }
                    //�ж�ֻ�����º��յ����
                    if (!StringUtil.contains(qzcsTime,"��")&&StringUtil.contains(qzcsTime,"��")&&StringUtil.contains(qzcsTime,"��")){
                        if (year!=null)
                            qzcsTime=year+"��"+qzcsTime;
                    }
                    //���ִ���
                    if (qzcsTime!=null&&qzcscontent.contains("����")){
                        int today = Integer.parseInt(qzcsTime.substring(qzcsTime.indexOf("��")+1,qzcsTime.indexOf("��")));
                        //��Ҫ�������ڽ�λ����
                        if (day!=null&&month!=null&&year!=null){
                            int tomorrow1 = today+1;
                            int[] bigMonths = {1,3,5,7,8,10,12};
                            int[] smallMonths = {4,6,9,11};
                            for (int bigMonth:bigMonths){
                                if (Integer.parseInt(month)==bigMonth&&tomorrow1>31){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow1=1;
                                    if (Integer.parseInt(month)>12){
                                        year=(Integer.parseInt(year)+1)+"";
                                        month="1";
                                        tomorrow1=1;
                                    }
                                }
                            }
                            for (int smallMonth:smallMonths){
                                if (Integer.parseInt(month)==smallMonth&&tomorrow1>30){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow1=1;
                                }
                            }
                            if (Integer.parseInt(month)==2){
                                if (Integer.parseInt(year)%4==0&&Integer.parseInt(year)%100!=0||
                                        Integer.parseInt(year)%400==0){
                                    if (tomorrow1>29){
                                        month=3+"";
                                        tomorrow1=1;
                                    }
                                }else {
                                    if (tomorrow1>28){
                                        month=3+"";
                                        tomorrow1=1;
                                    }
                                }
                            }
                            qzcsTime = year+"��"+month+"��"+tomorrow1+"��";
                        }
                    }

                }else if (qzcsTimelist.size()<1){
                    if (qzcsTime==null&&qzcscontent.contains("����")){
                        if (day!=null&&month!=null&&year!=null) {
                            int tomorrow2 = Integer.parseInt(day) + 1;
                            int[] bigMonths = {1,3,5,7,8,10,12};
                            int[] smallMonths = {4,6,9,11};
                            for (int bigMonth:bigMonths){
                                if (Integer.parseInt(month)==bigMonth&&tomorrow2>31){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow2=1;
                                    if (Integer.parseInt(month)>12){
                                        year=(Integer.parseInt(year)+1)+"";
                                        month="1";
                                        tomorrow2=1;
                                    }
                                }
                            }
                            for (int smallMonth:smallMonths){
                                if (Integer.parseInt(month)==smallMonth&&tomorrow2>30){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow2=1;
                                }
                            }
                            if (Integer.parseInt(month)==2){
                                if (Integer.parseInt(year)%4==0&&Integer.parseInt(year)%100!=0||
                                        Integer.parseInt(year)%400==0){
                                    if (tomorrow2>29){
                                        month=3+"";
                                        tomorrow2=1;
                                    }
                                }else {
                                    if (tomorrow2>28){
                                        month=3+"";
                                        tomorrow2=1;
                                    }
                                }
                            }
                            qzcsTime = year + "��" + month + "��" + tomorrow2 + "��";
                        }
                    }
                }else if (qzcsTimelist.size()>1){
                    for (int i = 0; i < qzcsTimelist.size(); i++) {
                        //�ж��Ƿ����������ڡ���ȡ��������
                        if (qzcsTimelist.get(i)!=null){
                            Pattern patterntimecomple = Pattern.compile("\\d{4}��\\d{1,2}��\\d{1,2}��");
                            Matcher matchertimecomple = patterntimecomple.matcher(qzcsTimelist.get(i));

                            if (matchertimecomple.find()){
                                qzcsTimeCompletion = qzcsTimelist.get(i);
                                year=qzcsTimeCompletion.substring(0,4);
                                month=qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("��")+1,qzcsTimeCompletion.indexOf("��"));
                                day = qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("��")+1,qzcsTimeCompletion.indexOf("��"));
                            }

                        }
                        //�ж�ͬ������
                        if (StringUtil.contains(qzcsTimelist.get(i),"ͬ��")){
                            qzcsTime=year+qzcsTimelist.get(i).substring(1,qzcsTimelist.get(i).length());
                        }
                        //�ж�ͬ�µ����
                        if (StringUtil.contains(qzcsTimelist.get(i),"ͬ��")){
                            qzcsTime=year+"��"+month+"��"+qzcsTimelist.get(i).substring(qzcsTimelist.get(i).indexOf("��")+1,qzcsTimelist.get(i).length());
                        }

                    }
                    if (qzcsTime==null){
                        qzcsTime=qzcsTimelist.get(qzcsTimelist.size()-1);
                    }
                }

                if (qzcsTime!=null){
                    qzcsModel.setQzcsTime(qzcsTime);
                    //System.out.println("ǿ�ƴ�ʩ���ڣ�"+qzcsTime);
                }
                //����ǿ�ƴ�ʩ��λ
                String qzcsdw=null;
                Pattern patterndw=Pattern.compile("[������].{1,13}?�־�|[������].{1,18}?�ɳ���|[������].{1,15}?��|[������].{1,18}?Ժ|��Ժ|����������|[������].{1,15}?������|��ָ������");
                Matcher matcherdw=patterndw.matcher(qzcscontent);
                while (matcherdw.find()){
                    qzcsdw=matcherdw.group();
                    if(!qzcsdw.equals("��Ժ"))
                        qzcsdw=qzcsdw.substring(1,qzcsdw.length());
                }
                if (StringUtil.equals(qzcsdw,"��Ժ")||StringUtil.equals(qzcsdw,"��Ժ")){
                    String ws=wsnr.substring(0,30);
                    ws=ws.replaceAll("\n","");
                    if (ws.indexOf("��Ժ")!=-1){
                        qzcsdw=ws.substring(0,ws.indexOf("��Ժ")+2);
                    }
                }
                if (qzcsdw!=null){
                    qzcsModel.setQzcsDw(qzcsdw);
                    //System.out.println("��λ��"+qzcsdw);
                }
                //����ǿ�ƴ�ʩԭ��
                List<String> qzcsReason=new ArrayList<String>();
                Pattern pattern = Pattern.compile("�����ӷ�.{1,8}����������|��.+?��");
                Matcher matcher = pattern.matcher(qzcscontent);
                while (matcher.find()){
                    String qzcsyy=matcher.group();
                    qzcsyy=qzcsyy.substring(1,qzcsyy.length());
                    qzcsReason.add(qzcsyy);
                    //System.out.println("ԭ��"+qzcsyy);
                }
                if (StringUtil.contains(qzcscontent,"�򱾰�")){
                    qzcsReason.add("����");
                }
                String[] qzcszmlist={"��֯����","���䶾Ʒ��","������ľ","����������","�ش��Ͷ���ȫ�¹���","ƭȡ����","���ҹ�����������","��������Ʒ��","�Ƿ���ҽ��","���ÿ�թƭ��","������������","����","�Ƿ�������","����������������","�Ż���","�鿪��ֵ˰ר�÷�Ʊ����","���η���������","������Ʒ","�����д����ơ�����","�ƻ������豸","Ѱ������","�Ƿ��չ���������󡢱�ΣҰ��������Ʒ��","������","������ľ��","�ķ���ľ��","�����ж����к�ʳƷ��","���ÿ�թƭ","α֤��","�����ٴ�","�Ƿ�����ǹ֧","���ۼ�ҩ��","��ͨ������","���Ĳ���","̰����","���Ρ�������������","����ٻ�������","�Ƿ����ж�Ʒ��","�Ƿ��н�","����","�Ƿ�����ע���̱��ʶ����","�Ƿ����졢���۷Ƿ������ע���̱��ʶ����","�������˺���","���۷Ƿ������ע���̱��ʶ����","�Ƿ���Ӫ��","��֯���쵼�����","�Ƿ����졢���۷Ƿ�����ķ�Ʊ��","Σ�ռ�ʻ����","����ɱ����","��λ�л���","������ĳ���","��թ������","�ܻ�","��Σ�շ���Σ��������ȫ��","����������������","�Ƿ����졢���۷Ƿ������ע���̱��ʶ��","������Ʒ��","������","��������������","ְ����ռ��","���Ρ���������������","����α�Ӳ�Ʒ","��ͬթƭ","�ܲ�ִ���о����ö�","���ְ����","����������Ʒ","ʧ����","�Ƿ����չ��ڴ����","������ͯ","����ְȨ��","��ʧ����������","�ƻ����õ�����ʩ","���ְ�ط���","�Ƿ��н���","�����������ж����к�ʳƷ","���������ж��к�ʳƷ","�����������ж��к�ʳƷ","��������","�Ĳ�","����α�Ӳ�Ʒ��","����ĳ�","��������","թƭ","Ѱ��������","�ƻ���ȼ�ױ��豸","��Σ�ռ�ʻ��","Ź������","�ش������¹���","����ĳ���","����","α����һ���֤��","�������ѱ���ȫ�¹���","�������һ���֤��","�Ƿ�ռ��ũ�õ�","�Ƿ����챬ը����","�����������ж��к�ʳƷ��","�������һ���֤����","α�졢���졢�������һ��ع��ġ�֤����ӡ����","���ա�����������������","������","���������۲����ϰ�ȫ��׼��ʳƷ","ǿ�Ƚ���","���ԡ����Ρ���������������","�Ƿ����ж�Ʒ","��թ����","�ķ���ľ","����ɱ��","�����˺�����","��ͨ����","��λ�ܻ�","�����˺���","���ɷǷ���Ӫ","��ͨ���°�","�Ƿ��ɿ���","������Ʒ����","�����˺�","����","���������۲����ϰ�ȫ��׼��ʳƷ��","Σ�ռ�ʻ","�Ĳ���","����������������","���۷Ƿ�����ķ�Ʊ��","������������������","���ӵ�����","̰��","�ƻ����õ�����ʩ��","�����˺�����","�鿪��ֵ˰ר�÷�Ʊ","��������","�л���","��������","�ܻ�����","Σ�ռ�ʻ��","�Ƿ��ɷ������ص㱣��ֲ����","Υ���涨","���ۼ�ҩ","���Ρ������������á���������������","���١�Ѱ������","թƭ��","���Ρ������������÷���","�Ƿ�����ǹ֧��","�ķ���ľ��","ְ����ռ","����������α�Ӳ�Ʒ","������������","ʧ��","������ȫ�¹���","���Է���","��ʧ��������","����ٻ�����","��������������","�ַ�����Ȩ��","���䶾Ʒ","���Ƿ����ж�Ʒ��","���ԡ����Ρ������������÷���","���������۲����ϰ�ȫ��׼��ʳƷ����","��ðע���̱���"};
                for (String qzcszm:qzcszmlist){
                    if (StringUtil.contains(qzcscontent,qzcszm)&&qzcsReason.size()==0){
                        qzcsReason.add(qzcszm);
                    }
                }
                if (qzcsReason.size()>0){
                    qzcsModel.setQscsReason(qzcsReason);
                }
                //�����Ƿ�����ʹ�������
                if (StringUtil.equals(qzcsCategory,"����")){
                    qzcsModel.setIsDB("��");
                    if (qzcsModel.getQzcsTime()!=null){
                        qzcsModel.setDBTime(qzcsModel.getQzcsTime());
                    }
                }else{
                    qzcsModel.setIsDB("��");
                }
                qzcsModellist.add(qzcsModel);
            }
        }



        wssscyrModel.setQzcsList(qzcsModellist);
    }

    private void setJycs(WssscyrModel wssscyrModel, ArrayList<String> contentlist) {
        String jycs=null;//�Ѻ����
        for (int i = 0; i <contentlist.size() ; i++) {
            String[] yjcswords={"�ֱ��Ѻ��","���Ѻ��","���Ѻ��","��Ѻ��","���Ѻ","��Ѻ"};
            for (String yjcsword:yjcswords){
                if (contentlist.get(i).indexOf(yjcsword)!=-1){
                    jycs=contentlist.get(i).substring(contentlist.get(i).indexOf(yjcsword)+yjcsword.length(),contentlist.get(i).length());
                    break;
                }
            }
            if (contentlist.get(i).indexOf("����")!=-1&&contentlist.get(i).indexOf("����")!=-1){
                jycs=contentlist.get(i).substring(contentlist.get(i).indexOf("����")+2,contentlist.get(i).indexOf("����"));
            }
        }
        if (jycs!=null){
            jycs=WsAnalyse.deBracket(jycs);
            wssscyrModel.setXjycs(jycs);
        }
    }

    private void setJskyqfz(WssscyrModel wssscyrModel, ArrayList<String> contentlist) {
        String jskyqfz="��";
        for (int i = 0; i <contentlist.size() ; i++) {
            if (contentlist.get(i).contains("����")){
                jskyqfz="��";
            }
        }
        wssscyrModel.setJskyqfz(jskyqfz);
    }

    private void setHxkyqfz(WssscyrModel wssscyrModel,ArrayList<String> contentlist) {
        String hxkyqfz="��";
        for (int i = 0; i <contentlist.size() ; i++) {
            if (contentlist.get(i).contains("����")||contentlist.get(i).contains("����")&&contentlist.get(i).contains("ִ��")){
                hxkyqfz="��";
            }
        }
        wssscyrModel.setHxkyqfz(hxkyqfz);
    }

    private void setXszrablity(WssscyrModel wssscyrModel, String wsnr) {
        String xszrablity="��ȫ������������";
        String name=wssscyrModel.getSscyr();
        String[] xzxszr={"����������������","�޶�������������","����������������","�޶���������","�޶������֣�������������"};
        ArrayList<String> wsnrlist = new ArrayList<String>();
        String xszrcontent;
        wsnrlist=WsAnalyse.getWholeContent(wsnr);
        for (int i = 0; i <wsnrlist.size() ; i++) {
            for (String xzxszritem:xzxszr) {
                if (StringUtil.contains(wsnrlist.get(i),xzxszritem)){
                    xszrcontent=wsnrlist.get(i-4)+wsnrlist.get(i-3)+wsnrlist.get(i-2)+wsnrlist.get(i-1)+wsnrlist.get(i);
                    if (StringUtil.contains(xszrcontent,name)){
                        xszrablity="����������������";
                    }
                }
            }
        }
//        if (StringUtil.contains(wsnr,"��������������")&&!StringUtil.contains(wsnr,"��ȫ������������")&&!StringUtil.equals(wsnr,"�޶�������������")){
//            xszrablity="��������������";
//        }
        wssscyrModel.setXszrablity(xszrablity);

    }

    private void setMsssygrlx(WssscyrModel wssscyrModel) {
        String msssygrlx=null;
        String allinfo=wssscyrModel.getSscyrallinfo();
        String[] namelist={"��","ĸ","��","Ů","��","��","��","��","��"};
        if (StringUtil.equals(wssscyrModel.getSssf(),"����������������ԭ����")){
            msssygrlx="������";
        }else if (StringUtil.contains(allinfo,"ϵ")||StringUtil.contains(allinfo,"֮")){
            for (String name:namelist){
                if (StringUtil.contains(allinfo,name)){
                    msssygrlx="����";
                }
            }
            if (msssygrlx==null){
                msssygrlx="���߰���������";
            }
        }else {
            msssygrlx="���߰���������";
        }
        if (msssygrlx!=null){
            wssscyrModel.setMsssygrlx(msssygrlx);
        }
    }

    private void setIsBhr(WssscyrModel wssscyrModel,List<String> ajjbqk) {
        String sssf=wssscyrModel.getSssf();//�������
        String name=wssscyrModel.getSscyr();//��������
        String allinfo=wssscyrModel.getSscyrallinfo();
        if (StringUtil.contains(sssf,"������")||StringUtil.equals(sssf,"������������ԭ����")||StringUtil.equals(sssf,"������")){
            wssscyrModel.setIsBhr("��");
        }else if (StringUtil.contains(allinfo,"ϵ����������")||StringUtil.contains(allinfo,"ϵ������")){
            wssscyrModel.setIsBhr("��");
        }
        if (ajjbqk!=null){
            for (String ajjbqkitem:ajjbqk){
                int bhrindex=ajjbqkitem.indexOf("������");
                int nameindex=ajjbqkitem.indexOf(name);
                if (bhrindex!=-1&&nameindex!=-1&&(nameindex-bhrindex)>0&&(nameindex-bhrindex)<4){
                    wssscyrModel.setIsBhr("��");
                }
            }
        }
        if (wssscyrModel.getIsBhr()==null) {
            wssscyrModel.setIsBhr("��");
        }
    }

    private void setZrrsf(WssscyrModel wssscyrModel,ArrayList<String> contentlist) {
        String zrrsf=null;
        String dsrzw=null;
        for (int i = 0; i <contentlist.size() ; i++) {
            if (FcUtil.getWholeToken(contentlist.get(i)).size() < 3) {
                dsrzw = ZWEnum.getZW(contentlist.get(i));
            } else {
                dsrzw = ZWEnum.getZW(contentlist.get(i));
            }
            if (dsrzw!=null){
                break;
            }
        }
        if(StringUtil.equals(dsrzw,"��ҵ")||StringUtil.equals(dsrzw,"�޹̶�ְҵ")||StringUtil.equals(dsrzw,"��ְҵ")){
            zrrsf="��ҵ��Ա";
        }else if (StringUtil.equals(dsrzw,"ũ��")){
            zrrsf="ũ��";
        }else if (StringUtil.contains(dsrzw,"��ʦ")){
            zrrsf="ְԱ";
        }else if (StringUtil.equals(dsrzw,"��")){
            zrrsf="����Ա";

        } else if (StringUtil.equals(dsrzw,"����")){
            zrrsf="˽Ӫ��ҵ���������Ͷ���";

        } else if (wssscyrModel.getSscyr()!=null&&wssscyrModel.getSscyr().length()<5){
            zrrsf = "����";
        }
        if (zrrsf!=null){
            wssscyrModel.setZrrsf(zrrsf);
        }
    }

}
