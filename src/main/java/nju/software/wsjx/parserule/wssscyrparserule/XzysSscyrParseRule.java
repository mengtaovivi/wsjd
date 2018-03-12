package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.MZEnum;
import nju.software.wsjx.model.Enum.StEnum;
import nju.software.wsjx.model.Enum.TshyEnum;
import nju.software.wsjx.model.Enum.WhcdEnum;
import nju.software.wsjx.model.Enum.ZWEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XzglfwSelector;
/**
 * ����һ�����ϲ����˽���
 * @author wangzh
 *
 */
public class XzysSscyrParseRule extends GeneralSscyrCommonRule implements SscyrParseRule{

	public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse) {
		List<String> wssscyr = wsAnalyse.getSscyr();
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        String ssjl = wsAnalyse.getSsjl();
		List<WssscyrModel> wssscyrModellist = new ArrayList<WssscyrModel>();
		if(wssscyr == null || wssscyr.size() == 0)
			return wssscyrModellist;
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
		String qz="";
		for (int i = 0; i < wssscyr.size(); i++) {
			WssscyrModel wssscyrModel = new WssscyrModel();
			ArrayList<String> contentlist = WsAnalyse.getWholeContent(wssscyr
					.get(i));
			String sscyrallinfo="";
			for(String sscyrinfo:contentlist){
				sscyrallinfo+=sscyrinfo; 

			}
			
			wssscyrModel.setSscyrallinfo(sscyrallinfo);
			// �������ϲ������������������
			//String content = WsAnalyse.deBracket(contentlist.get(0));
			String content=contentlist.get(0);
			
			String bssssf = HeadEnum.getHead(WsAnalyse.deBracket(content));//�������

			String sssf=bssssf;
			if (sssf != null) {
				wssscyrModel.setSssf(sssf);
				//ɾ�����ź��������
				if(content.indexOf(")") != -1)
					content = content.substring(0,content.indexOf(")")+1);
				if(content.indexOf("��") != -1)
					content = content.substring(0,content.indexOf("��")+1);
				content=WsAnalyse.deBracket(content);
				int index = content.indexOf(bssssf);
				
				String ssmc = content.substring(index + bssssf.length(),
						content.length());
				
				if((ssmc.equals("")||ssmc == null)&&contentlist.size()>=2){
					ssmc = contentlist.get(1);
				}
				//ȥ�������������ð��
				if(ssmc.contains("��")){
					ssmc=ssmc.replaceFirst("��", "");
				}
				
				if(ssmc!=null){
					ssmc=ssmc.replaceAll("[��,.��()����:��]", "");
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
				if (FcUtil.getWholeToken(ssmc).size() > 3||ssmc.length()>7) {
					wssscyrModel.setDsrlx("����");
				} else {
					wssscyrModel.setDsrlx("��Ȼ��");
				}
			}
			//�������ϵ�λ
			String ssdw=bssssf;
			if(ssdw!=null){
				if(ssdw.contains("ԭ��")||ssdw.contains("����")){
					wssscyrModel.setSsdw(ssdw);
				}
				//�����������ɹ�ϵ����
				if(ssdw.equals("ԭ��")){
					wssscyrModel.setXzfagxzt("������Է�");
				}else if(ssdw.equals("����")){
					wssscyrModel.setXzfagxzt("��������");
					//������������
					setBglx(wssscyrModel,ssjl,ajjbqk);
				}
			}

			//�������������
			String dsrlb=null;//���������
			String[] dlr={"����������","ί�д�����","ί�����ϴ�����","����������","������","������"};//������
			if(bssssf!=null){
				if(bssssf.equals("ԭ��")){
					dsrlb="ԭ��";
				}else if(bssssf.equals("����")){
					dsrlb="����";
				}
				for(int j=0;j<dlr.length;j++){
					if(bssssf!=null&&bssssf.equals(dlr[j])){
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
								contentlist.get(j).indexOf("��",contentlist.get(j).indexOf("��")) + 1);
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
			//������������Χ
			setXzglfw(wssscyrModel);
			if(StEnum.hasStzt(sssf))
				qz=wssscyrModel.getSscyr();
			String dsrzw=wssscyrModel.getDsrzw();
			if (dsrzw != null) {
				wssscyrModel.setDsrzw(dsrzw);
				if("��ʦ".equals(dsrzw)){
					wssscyrModel.setXgsscyr(qz);
				}
			}
			wssscyrModellist.add(wssscyrModel);
		}

		setFddbr(wssscyrModellist);
		//����������ҵ
		setTshy(wssscyrModellist);

		return wssscyrModellist;
	}

	private void setXzglfw(WssscyrModel wssscyrModel) {
		//��������Χ
		String xzglfw=null;
		String dsrmc=wssscyrModel.getSscyr();//����������
		String dsrssdw=wssscyrModel.getSsdw();//���������ϵ�λ
		if (dsrmc!=null&&dsrssdw!=null){
			if (dsrssdw.equals("����")) {
				xzglfw = XzglfwSelector.selectXzglfw(dsrmc);
			}
		}
		wssscyrModel.setXzglfw(xzglfw);
	}


	private void setBglx(WssscyrModel wssscyrModel,String ssjl,List<String> ajjbqk) {
		// TODO Auto-generated method stub
		String bglx=null;
		if(ajjbqk!=null){
			ArrayList<String> ajjbqkfd = WsAnalyse.getWholeContent(ajjbqk
					.get(0));
			for(int i=0;i<ajjbqkfd.size();i++){
				String item=ajjbqkfd.get(i);
				if(item.contains(wssscyrModel.getSscyr()+"��׼")){
					bglx="������������׼����";
				}
			}
		}
		if(!StringUtil.isBlank(ssjl)){
			if(ssjl.contains("��������")){
				bglx="����������Ϊ�ĸ������";
			}else{
				bglx="��������������Ϊ����������";
			}
		}
		wssscyrModel.setBglx(bglx);
	}

	private void setTshy(List<WssscyrModel> wssscyrModellist) {
		// TODO Auto-generated method stub
		//���������һ���Ƿ��������˴��ڹ�����λ�ģ�һ���������˾��ǹ�˾��һ���ǵ����˵�ְλ
		for(int i=0;i<wssscyrModellist.size();i++){
			if(wssscyrModellist.get(i).getGzdw()!=null){
				setTshyDlr(wssscyrModellist.get(i));
			}else if(wssscyrModellist.get(i).getDsrzw()!=null){
				setTshyDsrzw(wssscyrModellist.get(i));
			}

			if(wssscyrModellist.get(i).getTshy()==null){
				setTshySsr(wssscyrModellist.get(i));
			}else if(wssscyrModellist.get(i).getTshy()==null){
				setTshyAll(wssscyrModellist.get(i));
			}
		}
	}

	private void setTshyDsrzw(WssscyrModel wssscyrModel) {
		String tshy=null;//������ҵ
		String dsrzw=wssscyrModel.getDsrzw();//������ְ��
		if(dsrzw!=null){
			tshy=TshyEnum.selectTshy(dsrzw);
			wssscyrModel.setTshy(tshy);
		}

	}

	private void setTshySsr(WssscyrModel wssscyrModel) {
		// TODO Auto-generated method stub
		String tshy=null;//������ҵ
		String dsrmc=wssscyrModel.getSscyr();//����������
		if(dsrmc!=null){
			tshy=TshyEnum.selectTshy(dsrmc);
			wssscyrModel.setTshy(tshy);
		}
	}

	private void setTshyDlr(WssscyrModel wssscyrModel) {
		// TODO Auto-generated method stub
		String tshy=null;
		String gzdw=wssscyrModel.getGzdw();//������λ
		if(gzdw!=null){
			tshy=TshyEnum.selectTshy(gzdw);
			wssscyrModel.setTshy(tshy);
		}
	}

	private void setTshyAll(WssscyrModel wssscyrModel) {
		// TODO Auto-generated method stub
		String tshy=null;//������ҵ
		String dsrall=wssscyrModel.getSscyrallinfo();//������ȫ����Ϣ
		if(dsrall!=null){
			tshy=TshyEnum.selectTshy(dsrall);
			wssscyrModel.setTshy(tshy);
		}

	}




	public boolean equalsPunc(String temp){
		boolean equalsPunc=false;
		if(temp.equals("��")||temp.equals("��")||temp.equals("��")||temp.equals("��")||temp.equals("��")){
			equalsPunc=true;
		}
		return equalsPunc;
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
		}else if(name!=null&&(name.endsWith("����")||name.endsWith("��")||name.endsWith("�ֵ����´�"))){
			wssscyrModel.setDwxz("����");
		}else if (name!=null&&(name.endsWith("������"))){
			wssscyrModel.setDwxz("������ҵ");
		}
	}
	public static void setFddbr(List<WssscyrModel> wssscyrModellist ){
		int ssfIndex = -1;
		int ysfIndex = -1;
		boolean ssf = false;
		boolean ysf = false;
		for(int i=0;i<wssscyrModellist.size();i++){
			String ssdw = wssscyrModellist.get(i).getSssf();//�������
			if(!ssf && ssdw.indexOf("ԭ��")==0){
				ssfIndex = i;
				ssf = true;
			}
			if(ssdw!=null){
				if(!ysf && ssdw.indexOf("����")==0){
					ysfIndex = i;
					ysf = true;
				}
			}
		}
		String ssfDbr = "";
		String ysfDbr = "";
		for(int i=0;i<wssscyrModellist.size();i++){
			//ԭ�淨��������
			if(ssfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "����������") &&((i<ysfIndex)||ysfIndex==-1)){
				ssfDbr = wssscyrModellist.get(i).getSscyr();
//        		������������˵Ĺ�����λ����ԭ��
				wssscyrModellist.get(i).setGzdw(wssscyrModellist.get(ssfIndex).getSscyr());
				wssscyrModellist.get(i).setGzdwxz(wssscyrModellist.get(ssfIndex).getDwxz());
			}else if(ysfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "����������") && i>ysfIndex){
				ysfDbr = wssscyrModellist.get(i).getSscyr();
//        		������������˵Ĺ�����λ���Ǳ���
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
