package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.MZEnum;
import nju.software.wsjx.model.Enum.WhcdEnum;
import nju.software.wsjx.model.Enum.ZWEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.StringUtil;

public class MszsSscyrParseRule extends GeneralSscyrCommonRule implements SscyrParseRule{
	public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse) {
		List<String> sscyr = wsAnalyse.getSscyr();//�õ������ϲ����˵��������ݡ�����������������ÿ��String��һ��
		String ssjl = wsAnalyse.getSsjl();
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
		
		for (int i = 0; i < sscyr.size(); i++) {
			WssscyrModel wssscyrModel = new WssscyrModel();
			ArrayList<String> contentlist = WsAnalyse.getWholeContent(sscyr
					.get(i));
			String sscyrallinfo="";
			for(String sscyrinfo:contentlist){
				sscyrallinfo+=sscyrinfo;
			}
			wssscyrModel.setSscyrallinfo(sscyrallinfo);
			// �������ϲ������������������
			//String content = WsAnalyse.deBracket(contentlist.get(0));
			String content=contentlist.get(0);
			
			String ysssdw=null;
			String esssdw=null;
			
			ysssdw=WsAnalyse.takeBracket(content);//ԭ�����ϵ�λ(ȡ�����������ֵ)
			if(ysssdw!=null){
				if(!(HeadEnum.HasHead(ysssdw))){
					ysssdw=null;
				}else if(ysssdw.contains("ϵ")){
					ysssdw=null;
				}else if(ysssdw.contains("Ů��")){
					ysssdw=null;
				}else if(ysssdw.contains("��")){
					ysssdw=null;
				}
			}  
			
			String bssssf=HeadEnum.getHead(WsAnalyse.deBracket(content));//�����������
			
			String sssf=bssssf;
			if(ysssdw!=null){
				sssf=bssssf+"��"+ysssdw+"��";
			}
			if (sssf != null) {
				wssscyrModel.setSssf(sssf);
				content=WsAnalyse.deBracket(content);
				int index = content.indexOf(bssssf);
				String ssmc = content.substring(index + bssssf.length(),
						content.length());
				
				//ȥ�������������ð��
				if(ssmc.contains("��")){
					ssmc=ssmc.replaceFirst("��", "");
				}
				wssscyrModel.setSscyr(ssmc);
				if (dt != "") {
					if (dt.contains(ssmc)) {
						wssscyrModel.setDtqk("��ͥ");
					}
				}
				if (wdt != "") {
					if (wdt.contains(ssmc)) {
						wssscyrModel.setDtqk("δ��ͥ");
					}
				}
				if (FcUtil.getWholeToken(ssmc).size() > 3) {
					wssscyrModel.setDsrlx("����");
				} else {
					wssscyrModel.setDsrlx("��Ȼ��");
				}
		    }
			//�������ϵ�λ��ԭ�����ϵ�λ
			String ssdw=bssssf;
//			if(ssdw.contains("������")||ssdw.contains("��������")){
//				wssscyrModel.setSsdw(ssdw);
//			}
			if(StringUtil.contains(ssdw, "����������")||StringUtil.contains(ssdw, "��������")){
				wssscyrModel.setSsdw(ssdw);
			}
			if(ysssdw!=null){
				String temp=ysssdw;
				if(ysssdw.contains("ԭ��")){
					ysssdw=ysssdw.substring(ysssdw.indexOf("ԭ��")+2,ysssdw.indexOf("ԭ��")+4 );
				}else if(ysssdw.contains("һ��")){
					ysssdw=ysssdw.substring(ysssdw.indexOf("һ��")+2, ysssdw.indexOf("һ��")+4);
				}
				if(ysssdw.contains("����")){
					ysssdw="������";
				}if(ysssdw.contains("����")){
					ysssdw="ԭ��";
				}if(ysssdw.contains("����")){
					ysssdw=temp.substring(temp.indexOf("����")+2,temp.indexOf("����")+4);
				}
				if(temp.contains("����")){
					esssdw=temp.substring(7);
				}
				wssscyrModel.setYsssdw(ysssdw);
				wssscyrModel.setEsssdw(esssdw);
			}
	
			//�������������
			String dsrlb=null;//���������
			String[] dlr={"����������","ί�д�����","����������","������","������"};//������
			if(bssssf!=null){
				if(bssssf.equals("����������")){
					dsrlb="����������";
				}else if(bssssf.equals("��������")){
					dsrlb="��������";
				}
				for(int j=0;j<dlr.length;j++){
					if(dsrlb!=null&&dsrlb.equals(dlr[j])){
						dsrlb="������";
					}
				}
			}
			wssscyrModel.setDsrlb(dsrlb);


			for (int j = 1; j < contentlist.size(); j++) {
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
				if (dsrzw != null) {
					wssscyrModel.setDsrzw(dsrzw);
				}
				if (year != null && month != null && day != null) {
					wssscyrModel.setYear(year);
					wssscyrModel.setMonth(month);
					wssscyrModel.setDay(day);

				}
			}
			//��������
			setDsrgj(wssscyrModel);
//			������λ����
			setDwxz(wssscyrModel);
			wssscyrModellist.add(wssscyrModel);
		}
		setFddbr(wssscyrModellist);
		return wssscyrModellist;
				
	}
		public static void setDwxz(WssscyrModel wssscyrModel){
			String name = wssscyrModel.getSscyr();
			if(name!=null && name.indexOf("�ɷ����޹�˾")>-1){
				wssscyrModel.setDwxz("�ɷ����޹�˾");
			}else if(name!=null && (name.indexOf("�������ι�˾")>-1||name.indexOf("���޹�˾")>-1)){
				wssscyrModel.setDwxz("�������ι�˾");
			}else if(name!=null && name.endsWith("��˾")){
				wssscyrModel.setDwxz("�������ι�˾");
			}else if(name!=null && (name.indexOf("��ʦ������")>-1||name.endsWith("ҽԺ"))){
				wssscyrModel.setDwxz("��ҵ��λ");
			}else if(name!=null && (name.endsWith("��")||name.endsWith("����")||name.endsWith("����������")||name.endsWith("�����"))){
				wssscyrModel.setDwxz("��ҵ");
			}
		}
		
		public static void setFddbr(List<WssscyrModel> wssscyrModellist ){
			int ssfIndex = -1;
			int ysfIndex = -1;
			boolean ssf = false;
			boolean ysf = false;
			for(int i=0;i<wssscyrModellist.size();i++){
				String ssdw = wssscyrModellist.get(i).getSssf();//�������
				if(!ssf && StringUtil.indexOf(ssdw, "����������")==0){
					ssfIndex = i;
					ssf = true;
				}
				if(!ysf && StringUtil.indexOf(ssdw, "��������")==0){
					ysfIndex = i;
					ysf = true;
				}
			}
			String ssfDbr = "";
			String ysfDbr = "";
			for(int i=0;i<wssscyrModellist.size();i++){
				//���߷�����������
				if(ssfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "����������") &&((i<ysfIndex)||ysfIndex==-1)){
					ssfDbr = wssscyrModellist.get(i).getSscyr();
//	        		������������˵Ĺ�����λ�������߷�
					wssscyrModellist.get(i).setGzdw(wssscyrModellist.get(ssfIndex).getSscyr());
					wssscyrModellist.get(i).setGzdwxz(wssscyrModellist.get(ssfIndex).getDwxz());
				}else if(ysfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "����������") && i>ysfIndex){
					ysfDbr = wssscyrModellist.get(i).getSscyr();
//	        		������������˵Ĺ�����λ����Ӧ�߷�
					wssscyrModellist.get(i).setGzdw(wssscyrModellist.get(ysfIndex).getSscyr());
					wssscyrModellist.get(i).setGzdwxz(wssscyrModellist.get(ysfIndex).getDwxz());
				}
			}
			if(ssfIndex!=-1 && !StringUtil.isBlank(ssfDbr))
				wssscyrModellist.get(ssfIndex).setFddbr(ssfDbr);
			if(ysfIndex!=-1 && !StringUtil.isBlank(ysfDbr))
				wssscyrModellist.get(ysfIndex).setFddbr(ysfDbr);
		}		

}	
