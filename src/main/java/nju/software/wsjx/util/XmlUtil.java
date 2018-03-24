package nju.software.wsjx.util;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.FsModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.SsrqydsrModel;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QkqkModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcZdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.*;
import nju.software.wsjx.service.model.xs.*;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ��wsModel����XMl
 * @author wangzh
 *
 */
public class XmlUtil {	
	public static void BuildXMLDoc(WsModel wsModel,			
			List<WsxszjdModel> wsxszjdModellist, 
			String outputpath, String filename)
					throws IOException, JDOMException {
		if(wsModel == null)
			return;
		String parseName = wsModel.getWswsModel().getParseName();												
		// �������ڵ� ȫ�� ,��ȫ��Ϊ�գ���������ȡ����special�ļ���
		if (wsModel.getWsqw() != null) {
			Element root = new Element("QW").setAttribute("value",
					wsModel.getWsqw());
			root.setAttribute("nameCN", "ȫ��");
			// �����ڵ���ӵ��ĵ��У�
			Document Doc = new Document(root);
			// �������׽ڵ�
			buildWs(root,wsModel);							
			// �������ϲ����˽ڵ�
			buildSscyr(root, wsModel);			
			// �������ϼ�¼�ڵ�
			buildSsjl(root, wsModel);			
			// ����������������ڵ�
			buildAjjbq(root, wsModel,wsxszjdModellist);	
			//�������з������̽ڵ�
			buildCpfxgc(root, wsModel);
			//�����Ƿ�Ϊ���°�����������Ӧ���н���ڵ�
			if(parseName!=null && parseName.contains("����")){
				buildXspjjg(wsModel, root);
			}else{
				buildPjjg(root, wsModel);
			}
			//������β�ڵ�
			buildWw(root, wsModel);
			// ������¼�ڵ�
			buildFl(root, wsModel);
			// ʹxml�ļ� ����Ч��
			Format format = Format.getPrettyFormat();
			XMLOutputter XMLOut = new XMLOutputter(format);
			XMLOut.output(Doc, new FileOutputStream(outputpath + "\\"
					+ filename + ".xml"));
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"qwspecial",
			// filename);
		}

	}
	/**
	 * �������׽ڵ�
	 * @param root
	 * @param wsModel
	 */
	private static void buildWs(Element root,WsModel wsModel){
		if(wsModel == null)
			return;
		// �������׽ڵ� ,������Ϊ�գ���������ȡ����wsspecial�ļ���
		if (wsModel.getWswsSegment() != null) {
			WswsModel wswsModel = wsModel.getWswsModel();
			Element ws = new Element("WS").setAttribute("value",
					wsModel.getWswsSegment());
			ws.setAttribute("nameCN", "����");
			root.addContent(ws);
			// ��������������λ�ڵ�
			if (wswsModel.getWszzdw() != null) {
				Element wszzdw = new Element("WSZZDW").setAttribute(
						"value", wswsModel.getWszzdw());
				wszzdw.setAttribute("nameCN", "����������λ");
				ws.addContent(wszzdw);
			}
			// �������취Ժ�ڵ� ,�����취ԺΪ�գ���������ȡ����jbfyspecial�ļ���
			if (wswsModel.getJbfy() != null) {
				Element jbfy = new Element("JBFY").setAttribute("value",
						wswsModel.getJbfy());
				jbfy.setAttribute("nameCN", "���취Ժ");
				ws.addContent(jbfy);
				if (wswsModel.getFyjb() != null) {
					Element fyjb = new Element("FYJB").setAttribute(
							"value", wswsModel.getFyjb());
					fyjb.setAttribute("nameCN", "��Ժ����");
					jbfy.addContent(fyjb);
				}
				if (wswsModel.getXzqhProv() != null) {
					Element xzqhProv = new Element("XZQH_P").setAttribute(
							"value", wswsModel.getXzqhProv());
					xzqhProv.setAttribute("nameCN", "��������(ʡ)");
					jbfy.addContent(xzqhProv);
				}
				if (wswsModel.getXzqhCity() != null) {
					Element xzqhCity = new Element("XZQH_C").setAttribute(
							"value", wswsModel.getXzqhCity());
					xzqhCity.setAttribute("nameCN", "��������(��)");
					jbfy.addContent(xzqhCity);
				}
			} else {
//				fileUtil.fileCopy(path, filename, specialpath
//						+ "jbfyspecial", filename);
			}
			// �����������ƽڵ� ,����������Ϊ�գ���������ȡ����wsmcspecial�ļ���
			if (wswsModel.getWsmc() != null) {
				Element wsmc = new Element("WSMC").setAttribute("value",
						wswsModel.getWsmc());
				wsmc.setAttribute("nameCN", "��������");
				ws.addContent(wsmc);
			} else {
//				fileUtil.fileCopy(path, filename, specialpath
//						+ "wsmcspecial", filename);
			}
			// �������Žڵ� ,������Ϊ�գ���������ȡ����ahspecial�ļ���
			if (wswsModel.getAh() != null) {
				Element ah = new Element("AH").setAttribute("value",
						wswsModel.getAh());
				ah.setAttribute("nameCN", "����");
				ws.addContent(ah);
			} else {
//				fileUtil.fileCopy(path, filename,
//						specialpath + "ahspecial", filename);
			}
			// ����������Ƚڵ� ,���������Ϊ�գ���������ȡ����landspecial�ļ���
			if (wswsModel.getLand() != null) {
				Element land = new Element("LAND").setAttribute("value",
						wswsModel.getLand());
				land.setAttribute("nameCN", "�������");
				ws.addContent(land);
			} else {
				// fileUtil.fileCopy(path, filename,
				// specialpath+"landspecial", filename);
			}
			// �����������ʽڵ�
			if (wswsModel.getAjxz() != null) {
				Element ajxz = new Element("AJXZ").setAttribute("value",
						wswsModel.getAjxz());
				ajxz.setAttribute("nameCN", "��������");
				ws.addContent(ajxz);
			}
			// ������������ڵ�
			if (wswsModel.getWszl() != null) {
				Element wszl = new Element("WSZL").setAttribute("value",
						wswsModel.getWszl());
				wszl.setAttribute("nameCN", "��������");
				ws.addContent(wszl);
			}
			// �������г���ڵ�
			if (wswsModel.getSpcx() != null) {
				Element spcx = new Element("SPCX").setAttribute("value",
						wswsModel.getSpcx());
				spcx.setAttribute("nameCN", "���г���");
				ws.addContent(spcx);
			}
			// �����������ͽڵ�
			if (wswsModel.getAjlx() != null) {
				Element ajlx = new Element("AJLX").setAttribute("value",
						wswsModel.getAjlx());
				ajlx.setAttribute("nameCN", "��������");
				ws.addContent(ajlx);
			}							
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"wsspecial",
			// filename);
		}		
		
	}
	private static Element buildElement(Element root, String name, String nameCN, String value){
		Element element = new Element(name);
		if(value != null)
			element.setAttribute("value", value);
		if(nameCN != null)
			element.setAttribute("nameCN", nameCN);
		root.addContent(element);
		return element;
	}

	/**
	 * �������ϲ����˽ڵ�
	 * @param root
	 * @param wsModel
	 */
	private static void buildSscyr(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// �������ϲ����˽ڵ� ,�����ϲ�����Ϊ�գ���������ȡ����sscyrspecial�ļ���
		if(wsModel.getWssscyrSegment()!=null && wsModel.getWssscyrSegment().size()>0){
			List<String> sscyr = wsModel.getWssscyrSegment();	
			List<WssscyrModel> wssscyrModellist = wsModel.getWssscyrModels();
			WscpjgModel wscpjgModel = wsModel.getWscpjgModel();
			Element sscyrqj=new Element ("SSCYRQJ").setAttribute("value",ListToString.List2String(wsModel.getWssscyrSegment()));
			sscyrqj.setAttribute("nameCN","���ϲ�����ȫ��");
			root.addContent(sscyrqj);
			for(int i=0;i<sscyr.size();i++){
				WssscyrModel wssscyrModel=wssscyrModellist.get(i);
				Element sscyren=new Element ("SSCYR").setAttribute("value",sscyr.get(i));
				sscyren.setAttribute("nameCN","���ϲ�����");
				sscyrqj.addContent(sscyren);

				// �������ϲ��������ƽڵ�
				if(wssscyrModel.getSscyr()!=null){
					Element sscyrmc=new Element ("SSCYRMC").setAttribute("value",wssscyrModel.getSscyr());
					sscyrmc.setAttribute("nameCN","���ϲ���������");
					sscyren.addContent(sscyrmc);
				}
				// ����������ݽڵ�
				if(wssscyrModel.getSssf()!=null){
					Element sssf=new Element ("SSSF").setAttribute("value",wssscyrModel.getSssf());
					sssf.setAttribute("nameCN","�������");
					sscyren.addContent(sssf);
				}
				//�������ϵ�λ�ڵ�
				if(wssscyrModel.getSsdw()!=null){
					Element ssdw=new Element ("SSDW").setAttribute("value",wssscyrModel.getSsdw());
					ssdw.setAttribute("nameCN","���ϵ�λ");
					sscyren.addContent(ssdw);
				}
				//����ԭ�����ϵ�λ�ڵ�
				if(wssscyrModel.getYsssdw()!=null){
					Element ysssdw=new Element ("YSSSDW").setAttribute("value",wssscyrModel.getYsssdw());
					ysssdw.setAttribute("nameCN","ԭ�����ϵ�λ");
					sscyren.addContent(ysssdw);
				}
				//�����������ϵ�λ�ڵ�
				if(wssscyrModel.getEsssdw()!=null){
					Element esssdw=new Element ("ESSSDW").setAttribute("value",wssscyrModel.getEsssdw());
					esssdw.setAttribute("nameCN","�������ϵ�λ");
					sscyren.addContent(esssdw);
				}
				//�����������ɹ�ϵ����
				if(wssscyrModel.getXzfagxzt()!=null){
					Element xzflgxzt=new Element("XZFLGXZT").setAttribute("value",wssscyrModel.getXzfagxzt());
					xzflgxzt.setAttribute("nameCN","�������ɹ�ϵ����");
					sscyren.addContent(xzflgxzt);
				}
				//�������������ڵ�
				if(wssscyrModel.getDsrlb()!=null){
					Element dsrlb=new Element("DSRLB").setAttribute("value", wssscyrModel.getDsrlb());
					dsrlb.setAttribute("nameCN","���������");
					sscyren.addContent(dsrlb);
				}
				// �������������ͽڵ�
				if(wssscyrModel.getDsrlx()!=null){
					Element dsrlx=new Element ("DSRLX").setAttribute("value",wssscyrModel.getDsrlx());
					dsrlx.setAttribute("nameCN","����������");
					sscyren.addContent(dsrlx);
				}
				// ���������Ա�ڵ�
				if(wssscyrModel.getXb()!=null){
					Element xb=new Element ("XB").setAttribute("value",wssscyrModel.getXb());
					xb.setAttribute("nameCN","�Ա�");
					sscyren.addContent(xb);
				}
				// ��������ڵ�
				if(wssscyrModel.getMz()!=null){
					Element mz = new Element ("MZ").setAttribute("value",wssscyrModel.getMz());
					mz.setAttribute("nameCN", "����");
					sscyren.addContent(mz);
				}
				// �����������ڽڵ�
				if(wssscyrModel.getCsrq()!=null){
					Element csrq=new Element ("CSRQ").setAttribute("value",wssscyrModel.getCsrq());
					csrq.setAttribute("nameCN","��������");
					sscyren.addContent(csrq);
					//���������սڵ�
					Element year = new Element("Year").setAttribute("value",wssscyrModel.getYear());
					year.setAttribute("nameCN","��");
					csrq.addContent(year);
					Element month = new Element("Month").setAttribute("value",wssscyrModel.getMonth());
					month.setAttribute("nameCN","��");
					csrq.addContent(month);
					Element day = new Element("Day").setAttribute("value",wssscyrModel.getDay());
					day.setAttribute("nameCN","��");
					csrq.addContent(day);
				}
				//�����������Ļ��̶Ƚڵ�
				if(wssscyrModel.getDsrwhcd()!=null){
					Element whcd = new Element ("WHCD").setAttribute("value",wssscyrModel.getDsrwhcd());
					whcd.setAttribute("nameCN","�Ļ��̶�");
					sscyren.addContent(whcd);
				}
				//���������ڵ�
				if (wssscyrModel.getGj()!=null){
					Element gj = new Element("GJ").setAttribute("value",wssscyrModel.getGj());
					gj.setAttribute("nameCN","����");
					sscyren.addContent(gj);
				}
//				// ����������ְ��ڵ�
//				if(wssscyrModel.getDsrzw()!=null){
//					Element dsrzw = new Element ("DSRZW").setAttribute("value", wssscyrModel.getDsrzw());
//					dsrzw.setAttribute("nameCN","������ְ��");
//					sscyren.addContent(dsrzw);
//				}
				//�����������ڵؽڵ�
				if (wssscyrModel.getHjd()!=null){
					Element hjszd = new Element("HJSZD").setAttribute("value",wssscyrModel.getHjd());
					hjszd.setAttribute("nameCN","�������ڵ�");
					sscyren.addContent(hjszd);
				}
				// ���������˵�ַ�ڵ�
				if(wssscyrModel.getDsrdz()!=null){
					Element dsrdz=new Element ("DSRDZ").setAttribute("value",wssscyrModel.getDsrdz());
					dsrdz.setAttribute("nameCN","�����˵�ַ");
					sscyren.addContent(dsrdz);
				}
				// ����֤������ڵ�
				if (wssscyrModel.getZjhm()!=null||wssscyrModel.getZjlx()!=null){
					Element zjinfo=new Element ("ZJXX").setAttribute("nameCN","֤����Ϣ");
					if (wssscyrModel.getZjlx()!=null){
						Element zjlx=new Element("ZJLX").setAttribute("value",wssscyrModel.getZjlx());
						zjlx.setAttribute("nameCN","֤������");
						zjinfo.addContent(zjlx);
					}
					if(wssscyrModel.getZjhm()!=null){
						Element zjhm=new Element ("ZJHM").setAttribute("value",wssscyrModel.getZjhm());
						zjhm.setAttribute("nameCN","֤������");
						zjinfo.addContent(zjhm);
					}

				}

				if(wssscyrModel.getDtqk()!=null){
					Element dtqk=new Element ("DTQK").setAttribute("value",wssscyrModel.getDtqk());
					dtqk.setAttribute("nameCN","��ͥ���");
					sscyren.addContent(dtqk);
				}
				//��λ����
				if(!StringUtil.isBlank(wssscyrModel.getDwxz())){
					Element dwxz=new Element ("DWXZ").setAttribute("value",wssscyrModel.getDwxz());
					dwxz.setAttribute("nameCN","��λ����");
					sscyren.addContent(dwxz);
				}	
				//����������
				if(!StringUtil.isBlank(wssscyrModel.getFddbr())){
					Element fddbr=new Element ("FDDBR").setAttribute("value",wssscyrModel.getFddbr());
					fddbr.setAttribute("nameCN","��λ����������");
					sscyren.addContent(fddbr);
				}
				//��λְ�����
				if(!StringUtil.isBlank(wssscyrModel.getGzdw())||!StringUtil.isBlank(wssscyrModel.getDsrzw())){
					Element dwzefz=new Element ("DWZWFZ").setAttribute("nameCN","��λְ�����");
					sscyren.addContent(dwzefz);
					if(!StringUtil.isBlank(wssscyrModel.getDsrzw())){
						if(wssscyrModel.getDsrzw()!=null){
							Element zw=new Element ("ZW").setAttribute("value",wssscyrModel.getDsrzw());
							zw.setAttribute("nameCN","ְ��");
							dwzefz.addContent(zw);
						}
					}
					if(!StringUtil.isBlank(wssscyrModel.getGzdw())){
						Element dwmc =new Element ("DWMC").setAttribute("value",wssscyrModel.getGzdw());
						dwmc.setAttribute("nameCN","��λ����");
						dwzefz.addContent(dwmc);
					}
					if(!StringUtil.isBlank(wssscyrModel.getGzdwxz())){
						Element dwxz =new Element ("DWXZ").setAttribute("value",wssscyrModel.getGzdwxz());
						dwxz.setAttribute("nameCN","��λ����");
						dwzefz.addContent(dwxz);
					}
				}
				//�������Ƿ��ٻ�
				if(wssscyrModel.getDsrsfzh()!=null){
					Element dsrsfzh=new Element ("DSRSFZH").setAttribute("value",wssscyrModel.getDsrsfzh());
					dsrsfzh.setAttribute("nameCN","�������Ƿ��ٻ�");
					sscyren.addContent(dsrsfzh);
				}
				//������ҵ
				if(wssscyrModel.getTshy()!=null){
					Element tshy=new Element ("TSHY").setAttribute("value",wssscyrModel.getTshy());
					tshy.setAttribute("nameCN","������ҵ");
					sscyren.addContent(tshy);
				}
				//��������
				if(wssscyrModel.getBglx()!=null){
					Element bglx=new Element("BGLX").setAttribute("value", wssscyrModel.getBglx());
					bglx.setAttribute("nameCN", "��������");
					sscyren.addContent(bglx);
				}
				//��ͨ�¹� �Ƿ�е�����
				if(wssscyrModel.getJtsgzr()!=null){
					Element sfcdzr=new Element("JTSFZR").setAttribute("value", wssscyrModel.getJtsgzr());
					sfcdzr.setAttribute("nameCN", "��ͨ�¹�����");
					sscyren.addContent(sfcdzr);
				}
				//��֯��������
				if(wssscyrModel.getZzjgdm()!=null){
					Element zzjgdm=new Element("ZZJGDM").setAttribute("value", wssscyrModel.getZzjgdm());
					zzjgdm.setAttribute("nameCN", "��֯��������");
					sscyren.addContent(zzjgdm);
				}
				//��������Χ
				if (wssscyrModel.getXzglfw()!=null){
					Element xzglfw=new Element("XZGLFW").setAttribute("value", wssscyrModel.getXzglfw());
					xzglfw.setAttribute("nameCN", "��������Χ");
					sscyren.addContent(xzglfw);
				}
				//��Ȼ�����
				if (wssscyrModel.getZrrsf()!=null){
					Element zrrsf=new Element("ZRRSF").setAttribute("value", wssscyrModel.getZrrsf());
					zrrsf.setAttribute("nameCN", "��Ȼ�����");
					sscyren.addContent(zrrsf);
				}
				//�Ƿ񱻺���
				if (wssscyrModel.getIsBhr()!=null){
					Element isBhr = new Element("SFBHR").setAttribute("value",wssscyrModel.getIsBhr());
					isBhr.setAttribute("nameCN","�Ƿ񱻺���");
					sscyren.addContent(isBhr);
				}
				//������������ԭ�������
				if (wssscyrModel.getMsssygrlx()!=null){
					Element msssygrlx = new Element("FDMSSSYGRLX").setAttribute("value",wssscyrModel.getMsssygrlx());
					msssygrlx.setAttribute("nameCN","������������ԭ�������");
					sscyren.addContent(msssygrlx);
				}
				if (wssscyrModel.getXszrablity()!=null){
					Element xszrnl = new Element("XSZRNL").setAttribute("value",wssscyrModel.getXszrablity());
					xszrnl.setAttribute("nameCN","������������");
					sscyren.addContent(xszrnl);
				}
				//�������ڷ���
				if (wssscyrModel.getHxkyqfz()!=null){
					Element hxkyqfz = new Element("HXKYQNFZ").setAttribute("value",wssscyrModel.getHxkyqfz());
					hxkyqfz.setAttribute("nameCN","���̿������ڷ���");
					sscyren.addContent(hxkyqfz);
				}
				//�������ڷ���
				if (wssscyrModel.getJskyqfz()!=null){
					Element jskyqfz = new Element("JSKYQNFZ").setAttribute("value",wssscyrModel.getJskyqfz());
					jskyqfz.setAttribute("nameCN","���Ϳ������ڷ���");
					sscyren.addContent(jskyqfz);
				}
				//���Ѻ����
				if (wssscyrModel.getXjycs()!=null){
					Element xjycs = new Element("XJYCS").setAttribute("value",wssscyrModel.getXjycs());
					xjycs.setAttribute("nameCN","���Ѻ����");
					sscyren.addContent(xjycs);
				}
				//ǿ�ƴ�ʩ
				if (wssscyrModel.getQzcsList()!=null){
					for (QzcsModel qzcsModel:wssscyrModel.getQzcsList()) {
						Element qzcs = new Element("QZCS").setAttribute("nameCN","ǿ�ƴ�ʩ");
						//ǿ�ƴ�ʩ����
						if (qzcsModel.getQzcsCategory()!=null){
							Element qzcszl = new Element("QZCSZL").setAttribute("value",qzcsModel.getQzcsCategory());
							qzcszl.setAttribute("nameCN","ǿ�ƴ�ʩ����");
							qzcs.addContent(qzcszl);
						}
						//ǿ�ƴ�ʩʱ��
						if (qzcsModel.getQzcsTime()!=null){
							Element qzcstime = new Element("QZCSZXSJ").setAttribute("value",qzcsModel.getQzcsTime());
							qzcstime.setAttribute("nameCN","ǿ�ƴ�ʩִ��ʱ��");
							qzcs.addContent(qzcstime);
						}
						//ǿ�ƴ�ʩ��λ
						if (qzcsModel.getQzcsDw()!=null){
							Element qzcsdw = new Element("QZCSZXDW").setAttribute("value",qzcsModel.getQzcsDw());
							qzcsdw.setAttribute("nameCN","ǿ�ƴ�ʩִ�е�λ");
							qzcs.addContent(qzcsdw);
						}
						//ǿ�ƴ�ʩԭ��
						if (qzcsModel.getQscsReason()!=null){
							Element qzcsReasonGroup = new Element("QZCSYYFZ").setAttribute("nameCN","ǿ�ƴ�ʩԭ����");
							for(int j=0;j<qzcsModel.getQscsReason().size();j++) {
								if (qzcsModel.getQscsReason()!=null){
									Element qzcsyy = new Element("QZCSYY").setAttribute("value",qzcsModel.getQscsReason().get(0));
									qzcsyy.setAttribute("nameCN","ǿ�ƴ�ʩԭ��");
									qzcsReasonGroup.addContent(qzcsyy);
									break;
								}
							}
							qzcs.addContent(qzcsReasonGroup);
						}
						sscyren.addContent(qzcs);
					}

				}
				//ǰ���Ӽ�
				if (wssscyrModel.getQkqkList()!=null){
					for (QkqkModel qkqkModel:wssscyrModel.getQkqkList()){
						Element qklj = new Element("QKLJ").setAttribute("nameCN","ǰ���Ӽ�");
						//ǰ�����
						if (qkqkModel.getQklb()!=null){
							Element qklb= new Element("QKLB").setAttribute("value",qkqkModel.getQklb());
							qklb.setAttribute("nameCN","ǰ�����");
							qklj.addContent(qklb);
						}
						//����ʱ��
						if (qkqkModel.getCfTime()!=null){
							Element cfTime = new Element("CFSJ").setAttribute("value",qkqkModel.getCfTime());
							cfTime.setAttribute("nameCN","����ʱ��");
							qklj.addContent(cfTime);
						}
						//����ԭ��
						if (qkqkModel.getCfReason()!=null){
							Element cfReasonGroup = new Element("CFYYZ").setAttribute("nameCN","����ԭ����");
							for(int j=0;j<qkqkModel.getCfReason().size();j++) {
								if (qkqkModel.getCfReason()!=null){
									Element cfyy = new Element("CFYY").setAttribute("value",qkqkModel.getCfReason().get(0));
									cfyy.setAttribute("nameCN","����ԭ��");
									cfReasonGroup.addContent(cfyy);
									break;
								}
							}
						}
						//������λ
						if (qkqkModel.getCfdw()!=null){
							Element cfdw = new Element("CFDW").setAttribute("value",qkqkModel.getCfdw());
							cfdw.setAttribute("nameCN","������λ");
							qklj.addContent(cfdw);
						}
						//������ʽ
						if (qkqkModel.getCfxs()!=null){
							Element cfxs = new Element("CFXS").setAttribute("value",qkqkModel.getCfxs());
							cfxs.setAttribute("nameCN","������ʽ");
							qklj.addContent(cfxs);
						}
						//��������
						if (qkqkModel.getCfxq()!=null){
							Element cfxqGroup = new Element("CFXQZ").setAttribute("nameCN", "����������");
							for (String xq:qkqkModel.getCfxq()){
								Element cfxq = new Element("CFXQ").setAttribute("value",xq);
								cfxq.setAttribute("nameCN","��������");
								cfxqGroup.addContent(cfxq);
							}
							qklj.addContent(cfxqGroup);
						}
						//�����ͷ���
						if (qkqkModel.getXmsfTime()!=null){
							Element xmsfTime = new Element("XMSFRQ").setAttribute("value",qkqkModel.getXmsfTime());
							xmsfTime.setAttribute("nameCN","�����ͷ�����");
							qklj.addContent(xmsfTime);
						}
						sscyren.addContent(qklj);
					}
				}

			}
//			��ͨ�¹ʱ��չ�˾�Ƿ���Ϊ����
			if(wscpjgModel!=null && wscpjgModel.getBsgssfbg()!=null){
				Element bx=new Element ("SSCYRQJ").setAttribute("value",wscpjgModel.getBsgssfbg());
				bx.setAttribute("nameCN","���չ�˾�Ƿ���Ϊ����");
				sscyrqj.addContent(bx);
			}
		}else{
//			fileUtil.fileCopy(path, filename, specialpath+"sscyrspecial", filename);
		}
		
		
	}
	/**
	 * �������ϼ�¼�ڵ�
	 * @param root
	 * @param wsModel
	 */
	private static void buildSsjl(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// �������ϼ�¼�ڵ�
		if (wsModel.getWsssjlSegment() != null) {
			WsssjlModel wsssjlModel = wsModel.getWsssjlModel();
			Element ssjl = new Element("SSJL").setAttribute("value",
					wsModel.getWsssjlSegment());
			ssjl.setAttribute("nameCN", "���ϼ�¼");
			//-----18.3.22----
			//����״
			if(wsssjlModel.getQsz() != null){
				SsrqydsrModel qsz1 = wsssjlModel.getQsz();
				Element element = buildElement(ssjl, "QSZ", "����״", null);
				buildElement(element, "QSR", qsz1.getSf(), qsz1.getName());
				buildElement(element, "RQ", "����", qsz1.getDate());
			}
			//����״
			if(wsssjlModel.getFsz() != null){
				SsrqydsrModel fsz1 = wsssjlModel.getFsz();
				Element element = buildElement(ssjl, "FSZ", "����״", null);
				buildElement(element, "FSR", fsz1.getSf(), fsz1.getName());
				buildElement(element, "RQ", "����", fsz1.getDate());
			}
			//�ܾ���ͥ
			if(wsssjlModel.getJjct() != null){
				SsrqydsrModel jjct1 = wsssjlModel.getJjct();
				Element element = buildElement(ssjl, "JJCT", "�ܾ���ͥ", null);
				buildElement(element, "DSR", jjct1.getSf(), jjct1.getName());
				buildElement(element, "RQ", "����", jjct1.getDate());
			}
			//����
			if(wsssjlModel.getCs() != null){
				SsrqydsrModel cs1 = wsssjlModel.getCs();
				Element cs = buildElement(ssjl, "CS","����",null);
				buildElement(cs, "CSR", cs1.getSf(), cs1.getName());
				buildElement(cs, "RQ", "����", cs1.getDate());
			}
			//����
			if (wsssjlModel.getFs() != null){
				FsModel fs = wsssjlModel.getFs();
				Element element = buildElement(ssjl, "FS", "����", null);
				buildElement(element, "YG", "ԭ��", fs.getYg());
				buildElement(element, "BG", "����", fs.getBg());
				buildElement(element, "RQ", "����", fs.getFsrq());
			}
			//-----end 18.3.22-----
			//-----18.3.8-----
			// ����ԭ��ڵ�
			if (wsssjlModel.getYg() != null) {
				buildElement(ssjl, "YG", "ԭ��", wsssjlModel.getYg());
			}
			// ��������ڵ�
			if (wsssjlModel.getBg() != null) {
				buildElement(ssjl, "BG", "����", wsssjlModel.getBg());
			}
			// ����������Ժ�ڵ�
			if (wsssjlModel.getLafy() != null) {
				buildElement(ssjl, "LAFY", "������Ժ", wsssjlModel.getLafy());
			}
			// ���������˽ڵ�
			if (wsssjlModel.getSsr() != null) {
				buildElement(ssjl, "SSR", "������", wsssjlModel.getSsr());
			}
			// �������߲ö��ڵ�
			if (wsssjlModel.getSscdah() != null && wsssjlModel.getSscdfymc() != null) {
				Element sscd = buildElement(ssjl, "SSCD", "���߲ö�", null);
				buildElement(sscd, "SSCDAH", "����", wsssjlModel.getSscdah());
				buildElement(sscd, "SSCDFYMC", "��Ժ����", wsssjlModel.getSscdfymc());
			}
			//-------end 18.3.8------
			// �������ɽڵ�
			if (wsssjlModel.getAy() != null) {
				Element ay = new Element("AY").setAttribute("value",
						wsssjlModel.getAy());
				ay.setAttribute("nameCN", "����");
				ssjl.addContent(ay);
			}
			// ����������Դ�ڵ�
			if (wsssjlModel.getAjly() != null) {
				Element ajly = new Element("AJLY").setAttribute("value",
						wsssjlModel.getAjly());
				ajly.setAttribute("nameCN", "������Դ");
				ssjl.addContent(ajly);
			}
			// ���������漰�ڵ�
			if (wsssjlModel.getAjsj() != null) {
				Element ajsj = new Element("AJSJ").setAttribute("value",
						wsssjlModel.getAjsj());
				ajsj.setAttribute("nameCN", "�����漰");
				ssjl.addContent(ajsj);
			}
			// ������ͥ����ڵ�
			if (wsssjlModel.getKtsl() != null) {
				Element ktsl = new Element("KTSL").setAttribute("value",
						wsssjlModel.getKtsl());
				ktsl.setAttribute("nameCN", "��ͥ����");
				ssjl.addContent(ktsl);
			}
			// ������ͥ���ڽڵ�
			if (wsssjlModel.getKtrq() != null) {
				for (int i = 0; i < wsssjlModel.getKtrq().size(); i++) {
					if (wsssjlModel.getKtrq().get(i) != null) {
						Element ktrq = new Element("KTRQ").setAttribute(
								"value", wsssjlModel.getKtrq().get(i));
						ktrq.setAttribute("nameCN", "��ͥ����");
					}
				}
			}
			// ����ǰ�󰸺Žڵ�
			if (wsssjlModel.getQsah() != null) {
				for (int i = 0; i < wsssjlModel.getQsah().size(); i++) {
					Element qsah = new Element("QSAH").setAttribute(
							"value", wsssjlModel.getQsah().get(i));
					qsah.setAttribute("nameCN", "ǰ�󰸺�");
					ssjl.addContent(qsah);
				}
			}
			// ������ͥ������Ϣ�ڵ�
			if (wsssjlModel.getKtslxx() != null) {
				Element ktslxx = new Element("KTSLXX").setAttribute(
						"value", wsssjlModel.getKtslxx());
				// ��������������ԭ��ڵ�
				if (wsssjlModel.getKtslxx().contains("������")) {
					if (wsssjlModel.getBgkslyy() != null) {
						Element bgkslyy = new Element("BGKSLYY")
								.setAttribute("value",
										wsssjlModel.getBgkslyy());
						bgkslyy.setAttribute("nameCN", "����������ԭ��");
						ktslxx.addContent(bgkslyy);
					}
				}
				ktslxx.setAttribute("nameCN", "��ͥ������Ϣ");
				ssjl.addContent(ktslxx);
			}
			// �����������ɽڵ�
			if (wsssjlModel.getWzay() != null) {
				Element wzay = new Element("WZAY").setAttribute("value",
						wsssjlModel.getWzay());
				wzay.setAttribute("nameCN", "��������");
				ssjl.addContent(wzay);
			}
			// ������ͥ���Ա��ؽڵ�
			if (wsssjlModel.getJcy() != null) {
				Element ctspy = new Element("CTSPY").setAttribute("nameCN",
						"��ͥ����Ա");
				Element jcyfz = new Element("JCYFZ").setAttribute("nameCN",
						"���Ա����");
				Element js = new Element("JS").setAttribute("value",
						wsssjlModel.getJs());
				js.setAttribute("nameCN", "��ɫ");
				Element jcy = new Element("JCY").setAttribute("value",
						wsssjlModel.getJcy());
				jcy.setAttribute("nameCN", "���Ա");
				jcyfz.addContent(js);
				jcyfz.addContent(jcy);
				ctspy.addContent(jcyfz);
				ssjl.addContent(ctspy);
			}
			// �����������ʽڵ�
			if (wsssjlModel.getSsxz() != null) {
				Element ssxz = new Element("SSXZ").setAttribute("value",
						wsssjlModel.getSsxz());
				ssxz.setAttribute("nameCN", "��������");
				ssjl.addContent(ssxz);
			}
			// �������Ժ������������
			if (wsssjlModel.getJcyjyyqsl() != null) {
				Element jcyjyyqsl = new Element("JCYJYYQSL").setAttribute(
						"value", wsssjlModel.getJcyjyyqsl());
				jcyjyyqsl.setAttribute("nameCN", "���Ժ������������");
				ssjl.addContent(jcyjyyqsl);
			}
			// �������귨ͥ�ڵ�
			if (wsssjlModel.getSnft() != null) {
				Element snft = new Element("SNFT").setAttribute("value",
						wsssjlModel.getSnft());
				snft.setAttribute("nameCN", "���귨ͥ");
				ssjl.addContent(snft);
			}
			// ����ָ����Ϣ��ؽڵ�
			if (wsssjlModel.getWsssjlZkjl() != null) {
				Element zkxx = new Element("ZKXX").setAttribute("nameCN",
						"ָ����Ϣ");
				for (int i = 0; i < wsssjlModel.getWsssjlZkjl().size(); i++) {
					Element zkjl = new Element("ZKJL").setAttribute(
							"nameCN", "ָ�ؼ�¼");
					ArrayList<WsssjlZkzmModel> zkzmModellist = wsssjlModel
							.getWsssjlZkjl().get(i).getZkzmModelist();
					ArrayList<String> xgr = wsssjlModel.getWsssjlZkjl()
							.get(i).getXgr();
					for (int j = 0; j < zkzmModellist.size(); j++) {
						if (zkzmModellist != null
								&& zkzmModellist.get(j).getWzzm() != null) {
							Element zkzm = new Element("ZKZM")
									.setAttribute("value", zkzmModellist
											.get(j).getZkzm());
							zkzm.setAttribute("nameCN", "ָ������");
							Element wzzm = new Element("WZZM")
									.setAttribute("value", zkzmModellist
											.get(j).getWzzm());
							wzzm.setAttribute("nameCN", "��������");
							if (zkzmModellist.get(j).getZmdm() != null) {
								Element zmdm = new Element("ZMDM")
										.setAttribute("value",
												zkzmModellist.get(j)
														.getZmdm());
								zmdm.setAttribute("nameCN", "��������");
								zkzm.addContent(zmdm);
							}
							zkzm.addContent(wzzm);
							zkjl.addContent(zkzm);
						}
					}
					for (int j = 0; j < xgr.size(); j++) {
						if (xgr != null && !xgr.get(j).contains("���Ժ")) {
							Element xgrlist = new Element("XGR")
									.setAttribute("value", xgr.get(j));
							xgrlist.setAttribute("nameCN", "�����");
							zkjl.addContent(xgrlist);
						}
					}
					zkxx.addContent(zkjl);
				}
				if (zkxx.getChild("ZKJL") != null) {
					ssjl.addContent(zkxx);
				}
			}
			// �������������ɽڵ�
			if (wsssjlModel.getQszay() != null) {
				Element qszay = new Element("QSZAY").setAttribute("value",
						wsssjlModel.getQszay());
				qszay.setAttribute("nameCN", "����������");
				ssjl.addContent(qszay);
			}
			// �����������ü��׳���ڵ�
			if (wsssjlModel.getJysyjycx() != null) {
				Element jysyjycx = new Element("JYSYJYCX").setAttribute(
						"value", wsssjlModel.getJysyjycx());
				jysyjycx.setAttribute("nameCN", "�������ü��׳���");
				ssjl.addContent(jysyjycx);
			}
			// �������ɴ���ڵ�
			if (wsssjlModel.getAydm() != null) {
				Element aydm = new Element("AYDM").setAttribute("value",
						wsssjlModel.getAydm());
				aydm.setAttribute("nameCN", "���ɴ���");
				ssjl.addContent(aydm);
			}
			// ����������Ϊ����ڵ�
			if (wsssjlModel.getXzxwzl() != null) {
				Element xzxwzl = new Element("XZXWZL").setAttribute(
						"value", wsssjlModel.getXzxwzl());
				xzxwzl.setAttribute("nameCN", "������Ϊ����");
				ssjl.addContent(xzxwzl);
			}// ����������Ȩ��Ϊ����ڵ�
			if (wsssjlModel.getXzqqxwzl() != null) {
				Element xzqqxwzl = new Element("XZQQXWZL").setAttribute(
						"value", wsssjlModel.getXzqqxwzl());
				xzqqxwzl.setAttribute("nameCN", "������Ȩ��Ϊ����");
				ssjl.addContent(xzqqxwzl);
			}
			// ������������ǰ�󰸺���ؽڵ�
			if (wsssjlModel.getXzesqsah() != null) {
				Element xzesqsah = new Element("QSAH").setAttribute(
						"value", wsssjlModel.getXzesqsah());
				xzesqsah.setAttribute("nameCN", "ǰ�󰸺�");
				if (wsssjlModel.getQsland() != null) {
					Element qsland = new Element("QSLAND").setAttribute(
							"value", wsssjlModel.getQsland());
					qsland.setAttribute("nameCN", "ǰ���������");
					xzesqsah.addContent(qsland);
				}
				if (wsssjlModel.getQsfyjc() != null) {
					Element qsfyjc = new Element("QSFYJC").setAttribute(
							"value", wsssjlModel.getQsfyjc());
					qsfyjc.setAttribute("nameCN", "ǰ��Ժ���");
					xzesqsah.addContent(qsfyjc);
				}
				if (wsssjlModel.getQsahsxh() != null) {
					Element qsahsxh = new Element("QSAHSXH").setAttribute(
							"value", wsssjlModel.getQsahsxh());
					qsahsxh.setAttribute("nameCN", "ǰ�󰸺�˳���");
					xzesqsah.addContent(qsahsxh);
				}
				ssjl.addContent(xzesqsah);
			}
			// ���������������������νڵ�
			if (wsssjlModel.getXsesqsah() != null
					|| wsssjlModel.getQsfy() != null
					|| wsssjlModel.getQscpsj() != null) {
				Element ajylysljgd = new Element("AJYLYSLJGD").setAttribute("nameCN","������������������");
				// �������¶���ǰ�󰸺���ؽڵ�
				if (wsssjlModel.getXsesqsah() != null) {
					Element xsesqsah = new Element("QSAH").setAttribute(
							"value", wsssjlModel.getXsesqsah());
					xsesqsah.setAttribute("nameCN", "ǰ�󰸺�");
					if (wsssjlModel.getQsland() != null) {
						Element qsland = new Element("QSLAND").setAttribute(
								"value", wsssjlModel.getQsland());
						qsland.setAttribute("nameCN", "ǰ���������");
						xsesqsah.addContent(qsland);
					}
					if (wsssjlModel.getQsfyjc() != null) {
						Element qsfyjc = new Element("QSFYJC").setAttribute(
								"value", wsssjlModel.getQsfyjc());
						qsfyjc.setAttribute("nameCN", "ǰ��Ժ���");
						xsesqsah.addContent(qsfyjc);
					}
					if (wsssjlModel.getQsahsxh() != null) {
						Element qsahsxh = new Element("QSAHSXH").setAttribute(
								"value", wsssjlModel.getQsahsxh());
						qsahsxh.setAttribute("nameCN", "ǰ�󰸺�˳���");
						xsesqsah.addContent(qsahsxh);
					}
					ajylysljgd.addContent(xsesqsah);
				}
				// ��������ǰ��Ժ��ؽڵ�
				if (wsssjlModel.getQsfy() != null) {
					Element qsfy = new Element("QSFY").setAttribute("value",
							wsssjlModel.getQsfy());
					qsfy.setAttribute("nameCN", "ǰ��Ժ");
					if (wsssjlModel.getFyjb() != null) {
						Element fyjb = new Element("FYJB").setAttribute(
								"value", wsssjlModel.getFyjb());
						fyjb.setAttribute("nameCN", "��Ժ����");
						qsfy.addContent(fyjb);
					}
					if (wsssjlModel.getBzfymc() != null) {
						Element bzfymc = new Element("BZFYMC").setAttribute(
								"value", wsssjlModel.getBzfymc());
						bzfymc.setAttribute("nameCN", "��׼��Ժ����");
						qsfy.addContent(bzfymc);
					}
					ajylysljgd.addContent(qsfy);
				}
				// ����ǰ�����ʱ��ڵ�
				if (wsssjlModel.getQscpsj() != null) {
					Element qscpsj = new Element("QSCPSJ").setAttribute(
							"value", wsssjlModel.getQscpsj());
					qscpsj.setAttribute("nameCN", "ǰ�����ʱ��");
					ajylysljgd.addContent(qscpsj);
				}
				// ����ǰ���о��ڵ�
				if (wsssjlModel.getQspj() != null) {
					Element qspj = new Element("QSPJ").setAttribute("value",
							wsssjlModel.getQspj());
					qspj.setAttribute("nameCN", "ǰ���о�");
					ajylysljgd.addContent(qspj);
				}
				// ����ǰ����������ڵ�
				if (wsssjlModel.getQswszl() != null) {
					Element qswszl = new Element("QSWSZL").setAttribute(
							"value", wsssjlModel.getQswszl());
					qswszl.setAttribute("nameCN", "ǰ����������");
					ajylysljgd.addContent(qswszl);
				}
				// ����ǰ���߻���
				if (wsssjlModel.getQsgsjg() != null) {
					Element qsgsjg = new Element("QSGSJG").setAttribute(
							"value", wsssjlModel.getQsgsjg());
					qsgsjg.setAttribute("nameCN", "ǰ���߻���");
					ajylysljgd.addContent(qsgsjg);
				}
				// ����ǰ���󼶽ڵ�
				if (wsssjlModel.getQssj() != null) {
					Element qssjl = new Element("QSSJ").setAttribute("value",
							wsssjlModel.getQssj());
					qssjl.setAttribute("nameCN", "ǰ����");
					ajylysljgd.addContent(qssjl);
				}
				// ����ǰ�󰸼������ڵ�
				if (wsssjlModel.getQsajyl() != null) {
					Element qsajyl = new Element("QSAJYL").setAttribute(
							"value", wsssjlModel.getQsajyl());
					qsajyl.setAttribute("nameCN", "ǰ�󰸼�����");
					ajylysljgd.addContent(qsajyl);
				}
				// ����ǰ��᰸��ʽ�ڵ�
				if (wsssjlModel.getQsjafs() != null) {
					Element qsjafs = new Element("QSJAFS").setAttribute(
							"value", wsssjlModel.getQsjafs());
					qsjafs.setAttribute("nameCN", "ǰ��᰸��ʽ");
					ajylysljgd.addContent(qsjafs);
				}
				ssjl.addContent(ajylysljgd);
			}
			// �������߻��߷�Χ�ڵ�
			if (wsssjlModel.getSshksfw() != null) {
				Element sshksfw = new Element("SSHKSFW").setAttribute(
						"value", wsssjlModel.getSshksfw());
				sshksfw.setAttribute("nameCN", "���߻��߷�Χ");
				ssjl.addContent(sshksfw);
			}
			// �����������ڽڵ�
			if (wsssjlModel.getLarq() != null) {
				Element larq = new Element("LARQ").setAttribute("value",
						wsssjlModel.getLarq());
				larq.setAttribute("nameCN", "��������");
				ssjl.addContent(larq);
			}
			// ����һ�󰸼����ó���ڵ�
			if (wsssjlModel.getYsajsycx() != null) {
				Element ysajsycx = new Element("YSAJSYCX").setAttribute(
						"value", wsssjlModel.getYsajsycx());
				ysajsycx.setAttribute("nameCN", "һ�󰸼����ó���");
				ssjl.addContent(ysajsycx);
			}
			// ��������ת��ͨ�ڵ�
			if (wsssjlModel.getJyzpt() != null) {
				Element jyzpt = new Element("JYZPT").setAttribute("value",
						wsssjlModel.getJyzpt());
				jyzpt.setAttribute("nameCN", "����ת��ͨ");
				ssjl.addContent(jyzpt);
			}
			// ����������֯�ڵ�
			if (wsssjlModel.getSpzz() != null) {
				Element spzz = new Element("SPZZ").setAttribute("value",
						wsssjlModel.getSpzz());
				spzz.setAttribute("nameCN", "������֯");
				ssjl.addContent(spzz);
			}
			// ����һ�󰸼���Դ�ڵ�
			if (wsssjlModel.getYsajly() != null) {
				Element ysajly = new Element("YSAJLY").setAttribute(
						"value", wsssjlModel.getYsajly());
				ysajly.setAttribute("nameCN", "һ�󰸼���Դ");
				ssjl.addContent(ysajly);
			}
			// �������߻��ؽڵ�
			if (wsssjlModel.getGsjg() != null) {
				Element gsjg = new Element("GSJG").setAttribute("value",
						wsssjlModel.getGsjg());
				gsjg.setAttribute("nameCN", "���߻���");
				ssjl.addContent(gsjg);
			}
			// �������߰��Žڵ�
			if (wsssjlModel.getGsah() != null) {
				Element gsah = new Element("GSAH").setAttribute("value",
						wsssjlModel.getGsah());
				gsah.setAttribute("nameCN", "���߰���");
				ssjl.addContent(gsah);
			}
			// �����������ڽڵ�
			if (wsssjlModel.getSlrq() != null) {
				Element slrq = new Element("SLRQ").setAttribute("value",
						wsssjlModel.getSlrq());
				slrq.setAttribute("nameCN", "��������");
				ssjl.addContent(slrq);
			}
			// �����������ڽڵ�
			if (wsssjlModel.getQsrq() != null) {
				Element qsrq = new Element("QSRQ").setAttribute("value",
						wsssjlModel.getQsrq());
				qsrq.setAttribute("nameCN", "��������");
				ssjl.addContent(qsrq);
			}
			// �������볷�����ڽڵ�
			if (wsssjlModel.getSqcsrq() != null) {
				Element sqcsrq = new Element("SQCSRQ").setAttribute(
						"value", wsssjlModel.getSqcsrq());
				sqcsrq.setAttribute("nameCN", "���볷������");
				ssjl.addContent(sqcsrq);
			}
			// ����������Ҫ�쵼��ͥ�ڵ�
			if (wsssjlModel.getBgzyldct() != null) {
				Element bgzyldct = new Element("BGZYLDCT").setAttribute(
						"value", wsssjlModel.getBgzyldct());
				bgzyldct.setAttribute("nameCN", "������Ҫ�쵼��ͥ");
				ssjl.addContent(bgzyldct);
			}
			// ����������ȱϯ��ȱϯ��������ȱϯ�����ϵ�λ�ڵ�
			if (wsssjlModel.getQxrxx()!=null && !wsssjlModel.getQxrxx().isEmpty()) {
				Element dsrqx = new Element("DXRQX");
				dsrqx.setAttribute("nameCN", "������ȱϯ");
				HashMap<String, String> map = wsssjlModel.getQxrxx();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getKey() != null) {
						Element qxrxm = new Element("XM").setAttribute(
								"value", entry.getKey());
						qxrxm.setAttribute("nameCN", "����");
						if (entry.getValue() != null) {
							Element qxrssdw = new Element("SSDW")
									.setAttribute("value", entry.getValue());
							qxrssdw.setAttribute("nameCN", "���ϵ�λ");
							qxrxm.addContent(qxrssdw);
						}
						dsrqx.addContent(qxrxm);
					}
				}
				ssjl.addContent(dsrqx);
			}
			// ������ͥ��������Ϣ����ͥ����������ͥ��������ݽڵ�
			if (wsssjlModel.getCtrxx()!=null && !wsssjlModel.getCtrxx().isEmpty()) {
				Element dsrqx = new Element("CTDSRXX");
				dsrqx.setAttribute("nameCN", "��ͥ��������Ϣ");
				HashMap<String, String> map = wsssjlModel.getCtrxx();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getKey() != null) {
						Element ctrxm = new Element("CTRXM").setAttribute(
								"value", entry.getKey());
						ctrxm.setAttribute("nameCN", "��ͥ������");
						if (entry.getValue() != null) {
							Element ctrssdw = new Element("SSDW")
									.setAttribute("value", entry.getValue());
							ctrssdw.setAttribute("nameCN", "���ϵ�λ");
							ctrxm.addContent(ctrssdw);
						}
						dsrqx.addContent(ctrxm);
					}
				}
				ssjl.addContent(dsrqx);
			}
			root.addContent(ssjl);
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"ssjlspecial",
			// filename);
		}
		
	}
	/**
	 * ����������������ڵ�
	 * @param root
	 * @param wsModel
	 * @param wsxszjdModellist
	 */
	private static void buildAjjbq(Element root,WsModel wsModel,List<WsxszjdModel> wsxszjdModellist){
		if(wsModel == null)
			return;
		// ����������������ڵ� ,��Ϊ�գ���������ȡ����ajjbqkspecial�ļ���		
		if (wsModel.getWsajjbqSegment() != null) {
			if (wsModel.getWsajjbqSegment() != null) {
				WsajjbqkModel wsajjbqkModel = wsModel.getWsajjbqkModel();
				Element ajjbqk = new Element("AJJBQK").setAttribute(
						"value", wsModel.getWsajjbqSegment());
				ajjbqk.setAttribute("nameCN", "�����������");
				root.addContent(ajjbqk);
				if (wsajjbqkModel.getQysdl() != null) {
					Element qysdl = new Element("QYSDL").setAttribute(
							"value", wsajjbqkModel.getQysdl());
					qysdl.setAttribute("nameCN", "ǰһ�����");
					ajjbqk.addContent(qysdl);
					if (wsajjbqkModel.getQyspjd() != null) {
						Element qyspjd = new Element("QYSPJD")
						.setAttribute("value",
								wsajjbqkModel.getQyspjd());
						qyspjd.setAttribute("nameCN", "ǰһ���о���");
						qysdl.addContent(qyspjd);
					}
				}
				if (wsajjbqkModel.getQsdl() != null) {
					Element qsdl = new Element("QSDL").setAttribute(
							"value", wsajjbqkModel.getQsdl());
					qsdl.setAttribute("nameCN", "ǰ�����");
					ajjbqk.addContent(qsdl);
					if (wsajjbqkModel.getQsygscd() != null) {
						Element qsygscd = new Element("QSYGSCD")
						.setAttribute("value",
								wsajjbqkModel.getQsygscd());
						qsygscd.setAttribute("nameCN", "ǰ��ԭ���߳ƶ�");
						qsdl.addContent(qsygscd);
					}
					if (wsajjbqkModel.getQsbgbcd() != null) {
						Element qsbgbcd = new Element("QSBGBCD")
						.setAttribute("value",
								wsajjbqkModel.getQsbgbcd());
						qsbgbcd.setAttribute("nameCN", "ǰ�󱻸��ƶ�");
						qsdl.addContent(qsbgbcd);
					}
					if (wsajjbqkModel.getQsfsscd() != null) {
						Element qsfsscd = new Element("QSFSSCD")
						.setAttribute("value",
								wsajjbqkModel.getQsfsscd());
						qsfsscd.setAttribute("nameCN", "ǰ�����߳ƶ�");
						qsdl.addContent(qsfsscd);
					}
					if (wsajjbqkModel.getQspjd() != null) {
						Element qspjd = new Element("QSPJD").setAttribute(
								"value", wsajjbqkModel.getQspjd());
						qspjd.setAttribute("nameCN", "ǰ���о���");
						qsdl.addContent(qspjd);
					}
					if (wsajjbqkModel.getQssld() != null) {
						List<String> qssldlist = wsajjbqkModel.getQssld();
						for (int i = 0; i < qssldlist.size(); i++) {
							Element qssld = new Element("QSSLD")
							.setAttribute("value", qssldlist.get(i));
							qssld.setAttribute("nameCN", "ǰ�������");
							qsdl.addContent(qssld);
						}
					}
				}
				//-----18.3.23-----
				//���������
				if (wsajjbqkModel.getFsqqModel() != null){
					FsqqModel fsqqModel = wsajjbqkModel.getFsqqModel();
					Element e_fs = new Element("FSQQD").setAttribute("nameCN", "���������");
					Element fsr = new Element("FSR").setAttribute("nameCN", "������");
					fsr.setAttribute("value", fsqqModel.getFsr());
					Element fsqq = new Element("FSQQ").setAttribute("nameCN", "��������");
					fsqq.setAttribute("value", fsqqModel.getFsqq());
					Element sshly = new Element("SSHLY").setAttribute("nameCN", "��ʵ������");
					sshly.setAttribute("value", fsqqModel.getSshly());
					e_fs.addContent(fsr);
					e_fs.addContent(fsqq);
					e_fs.addContent(sshly);
					ajjbqk.addContent(e_fs);
				}
				//���������
				if (wsajjbqkModel.getSsqqModel() != null){
					SsqqModel ssqqModel = wsajjbqkModel.getSsqqModel();
					Element e_ssqq = new Element("SSQQD").setAttribute("nameCN", "���������");
					Element qsr = new Element("QSR").setAttribute("nameCN", "������");
					qsr.setAttribute("value", ssqqModel.getQsr());
					Element ssqq = new Element("FSQQ").setAttribute("nameCN", "��������");
					ssqq.setAttribute("value", ssqqModel.getSsqq());
					Element sshly = new Element("SSHLY").setAttribute("nameCN", "��ʵ������");
					sshly.setAttribute("value", ssqqModel.getSshly());
					e_ssqq.addContent(qsr);
					e_ssqq.addContent(ssqq);
					e_ssqq.addContent(sshly);
					ajjbqk.addContent(e_ssqq);
				}
				//׷�ӵ����˶�
				if (wsajjbqkModel.getZjdsrModel() != null){
					ZjdsrModel zjdsrModel = wsajjbqkModel.getZjdsrModel();
					Element e_zjdsr = new Element("ZJDSRD").setAttribute("nameCN", "׷�ӵ����˶�");
					Element dsr = new Element("DSR").setAttribute("nameCN", "������");
					dsr.setAttribute("value", zjdsrModel.getDsr());
					Element sqr = new Element("SQR").setAttribute("nameCN", "������");
					sqr.setAttribute("value", zjdsrModel.getSqr());
					Element sqsj = new Element("SQSJ").setAttribute("nameCN", "����ʱ��");
					sqsj.setAttribute("value", zjdsrModel.getSqsj());
					Element sshly = new Element("SSHLY").setAttribute("nameCN", "��ʵ������");
					sshly.setAttribute("value", zjdsrModel.getSshly());
					e_zjdsr.addContent(dsr);
					e_zjdsr.addContent(sqr);
					e_zjdsr.addContent(sqsj);
					e_zjdsr.addContent(sshly);
					ajjbqk.addContent(e_zjdsr);
				}
				//-----end 18.3.23-----
				//-----18.3-------
				if(wsajjbqkModel.getYsfylyd() != null){
					Element ysfyly = new Element("YSFYLYD").setAttribute(
							"value", wsajjbqkModel.getYsfylyd());
					ysfyly.setAttribute("nameCN", "���ͷ�Ժ���ɶ�");
					ajjbqk.addContent(ysfyly);
				}
				if(wsajjbqkModel.getBqfylyd() != null){
					Element bqfylyd = new Element("BQFYLYD").setAttribute(
							"value", wsajjbqkModel.getBqfylyd());
					bqfylyd.setAttribute("nameCN", "���뷨Ժ���ɶ�");
					ajjbqk.addContent(bqfylyd);
				}
				//----end 18.3----
				if (wsajjbqkModel.getYgscd() != null) {
					Element ygscd = new Element("YGSCD").setAttribute(
							"value", wsajjbqkModel.getYgscd());
					ygscd.setAttribute("nameCN", "ԭ���߳ƶ�");
					ajjbqk.addContent(ygscd);
					//��ͨ�¹�ԭ���߳ƶ� ���Ͻ��
					if(wsajjbqkModel.getSsqqjeList()!=null && wsajjbqkModel.getSsqqjeList().size()>0){
						Element jeele = new Element("SSJE").setAttribute("nameCN", "��ͨ�¹�ԭ���߳����Ͻ��");
						ygscd.addContent(jeele);
						for(PjjeModel je:wsajjbqkModel.getSsqqjeList()){
							Element jesl =  new Element("SSJESL").setAttribute(
									"value", je.getValue());
							jesl.setAttribute("nameCN", "���");
							jeele.addContent(jesl);
							if(je.getCategorys()!=null && je.getCategorys().size()>0){
								for(String lx:je.getCategorys()){
									Element lxing= new Element("SSJELX").setAttribute(
											"value", lx);
									lxing.setAttribute("nameCN", "����");
									jesl.addContent(lxing);
								}
							}
							if(je.getJsfs()!=null){
								Element jsff= new Element("JSFF").setAttribute(
										"value",je.getJsfs());
								jsff.setAttribute("nameCN", "���㷽��");
								jesl.addContent(jsff);
							}
							if(je.getKssj()!=null){
								Element jsff= new Element("KSSJ").setAttribute(
										"value",je.getKssj());
								jsff.setAttribute("nameCN", "��ʼʱ��");
								jesl.addContent(jsff);
							}
							if(je.getJssj()!=null){
								Element jsff= new Element("JSSJ").setAttribute(
										"value",je.getJssj());
								jsff.setAttribute("nameCN", "����ʱ��");
								jesl.addContent(jsff);
							}
						}
					}
				}
				if (wsajjbqkModel.getBgbcd() != null) {
					Element bgbcd = new Element("BGBCD").setAttribute(
							"value", wsajjbqkModel.getBgbcd());
					bgbcd.setAttribute("nameCN", "�����ƶ�");
					ajjbqk.addContent(bgbcd);
				}
				if (wsajjbqkModel.getFsscd() != null) {
					Element fsscd = new Element("FSSCD").setAttribute(
							"value", wsajjbqkModel.getFsscd());
					fsscd.setAttribute("nameCN", "�����߳ƶ�");
					ajjbqk.addContent(fsscd);
				}
				if (wsajjbqkModel.getFsbcd() != null) {
					Element fsbcd = new Element("FSBCD").setAttribute(
							"value", wsajjbqkModel.getFsbcd());
					fsbcd.setAttribute("nameCN", "���߱�ƶ�");
					ajjbqk.addContent(fsbcd);
				}
				if (wsajjbqkModel.getDsryjd() != null) {
					Element dsryjd = new Element("DSRYJD").setAttribute(
							"value", wsajjbqkModel.getDsryjd());
					dsryjd.setAttribute("nameCN", "�����������");
					ajjbqk.addContent(dsryjd);
				}
				if (wsajjbqkModel.getZjd() != null) {
					List<String> zjdlist = wsajjbqkModel.getZjd();
					for (int i = 0; i < zjdlist.size(); i++) {
						Element zjd = new Element("ZJD").setAttribute(
								"value", zjdlist.get(i));
						zjd.setAttribute("nameCN", "֤�ݶ�");
						ajjbqk.addContent(zjd);
					}
				}
				if (wsajjbqkModel.getCmssd() != null) {
					List<String> cmssdlist = wsajjbqkModel.getCmssd();
					for (int i = 0; i < cmssdlist.size(); i++) {
						Element cmssd = new Element("CMSSD").setAttribute(
								"value", cmssdlist.get(i));
						cmssd.setAttribute("nameCN", "������ʵ��");
						if (wsajjbqkModel.getSgxq() != null) {
							Element sgxq = new Element("SGXQ").setAttribute(
									"value", wsajjbqkModel.getSgxq());
							sgxq.setAttribute("nameCN", "�¹�����");
							cmssd.addContent(sgxq);
							if (wsajjbqkModel.getSgsj() != null) {
								Element sgsj = new Element("SGSJ").setAttribute(
										"value", wsajjbqkModel.getSgsj());
								sgsj.setAttribute("nameCN", "�¹�ʱ��");
								sgxq.addContent(sgsj);
							}
							if (wsajjbqkModel.getSgdd() != null) {
								Element sgdd = new Element("SGDD").setAttribute(
										"value", wsajjbqkModel.getSgdd());
								sgdd.setAttribute("nameCN", "�¹ʵص�");
								sgxq.addContent(sgdd);
							}
						}
						if (wsajjbqkModel.getJdcglr() != null) {
							Element jdcglr = new Element("JDCGLR").setAttribute(
									"value", wsajjbqkModel.getJdcglr());
							jdcglr.setAttribute("nameCN", "������������");
							cmssd.addContent(jdcglr);
						}
						if (wsajjbqkModel.getJdcsyr() != null) {
							Element jdcsyr = new Element("JDCSYR").setAttribute(
									"value", wsajjbqkModel.getJdcsyr());
							jdcsyr.setAttribute("nameCN", "������������");
							cmssd.addContent(jdcsyr);
						}
						if (wsajjbqkModel.getGajgrdyj() != null) {
							Element gajgrdyj = new Element("GAJGRDYJ").setAttribute(
									"value", wsajjbqkModel.getGajgrdyj());
							gajgrdyj.setAttribute("nameCN", "���������϶����");
							cmssd.addContent(gajgrdyj);
						}
						if (wsajjbqkModel.getShrjzd() != null) {
							Element shrjzd = new Element("SHRJZD").setAttribute(
									"value", wsajjbqkModel.getShrjzd());
							shrjzd.setAttribute("nameCN", "�ܺ��˾�ס��");
							cmssd.addContent(shrjzd);
						}
						if (wsajjbqkModel.getShrzy() != null) {
							Element shrzy = new Element("SHRZY").setAttribute(
									"value", wsajjbqkModel.getShrzy());
							shrzy.setAttribute("nameCN", "�ܺ���ְҵ");
							cmssd.addContent(shrzy);
						}
						if (wsajjbqkModel.getSftb() != null) {
							Element sftb = new Element("SFTB").setAttribute(
									"value", wsajjbqkModel.getSftb());
							sftb.setAttribute("nameCN", "�Ƿ�Ͷ��");
							cmssd.addContent(sftb);
						}
						if (wsajjbqkModel.getTbxz() != null) {
							Element tbxz = new Element("TBXZ").setAttribute(
									"value", wsajjbqkModel.getTbxz());
							tbxz.setAttribute("nameCN", "Ͷ������");
							cmssd.addContent(tbxz);
						}
						if (wsajjbqkModel.getSfzbxqn() != null) {
							Element sfzbxqn = new Element("SFZBXQN").setAttribute(
									"value", wsajjbqkModel.getSfzbxqn());
							sfzbxqn.setAttribute("nameCN", "�Ƿ��ڱ�������");
							cmssd.addContent(sfzbxqn);
						}
						if (wsajjbqkModel.getSfxqpf() != null) {
							Element sfxqpf = new Element("SFXQPF").setAttribute(
									"value", wsajjbqkModel.getSfxqpf());
							sfxqpf.setAttribute("nameCN", "�Ƿ������⸶");
							cmssd.addContent(sfxqpf);
						}
						if (wsajjbqkModel.getShangQing() != null) {
							Element shangQing = new Element("SQ").setAttribute(
									"value", wsajjbqkModel.getShangQing());
							shangQing.setAttribute("nameCN", "����");
							cmssd.addContent(shangQing);
						}
						if (wsajjbqkModel.getRealPay() != null) {
							Element sjzcqk = new Element("SJZCQK").setAttribute(
									"value", wsajjbqkModel.getRealPay());
							sjzcqk.setAttribute("nameCN", "ʵ��֧�����");
							cmssd.addContent(sjzcqk);
						}
						if (wsajjbqkModel.getIdentifyContent() != null) {
							Element xgjd = new Element("XGJD").setAttribute(
									"value", wsajjbqkModel.getIdentifyContent());
							xgjd.setAttribute("nameCN", "��ؼ���");
							cmssd.addContent(xgjd);
						}
						if (wsajjbqkModel.getJdsfkk() != null) {
							Element jdsfkk = new Element("JDSFKK").setAttribute(
									"value", wsajjbqkModel.getJdsfkk());
							jdsfkk.setAttribute("nameCN", "�����Ƿ�ɿ�");
							cmssd.addContent(jdsfkk);
						}
						ajjbqk.addContent(cmssd);
					}
				}
				if (wsajjbqkModel.getXzsszyd() != null) {
					Element xzsszyd = new Element("XZSSZYD").setAttribute(
							"value", wsajjbqkModel.getXzsszyd());
					xzsszyd.setAttribute("nameCN", "�������������");
					ajjbqk.addContent(xzsszyd);
				}
				if(wsajjbqkModel.getBsdl()!=null){
					Element bsdl=new Element ("BSDL").setAttribute("value",wsajjbqkModel.getBsdl()); 
					bsdl.setAttribute("nameCN","�������");
					ajjbqk.addContent(bsdl);
					if(wsajjbqkModel.getSsrscd()!=null){
						Element ssrscd=new Element ("SSRSCD").setAttribute("value",wsajjbqkModel.getSsrscd()); 
						ssrscd.setAttribute("nameCN","�������߳ƶ�");
						bsdl.addContent(ssrscd);
					}
					if(wsajjbqkModel.getBssrbcd()!=null){
						List<String> bssrbcdlist=wsajjbqkModel.getBssrbcd();
						for(int i=0;i<bssrbcdlist.size();i++){
							Element bssrbcd=new Element ("BSSRBCD").setAttribute("value",bssrbcdlist.get(i)); 
							bssrbcd.setAttribute("nameCN","�������˱�ƶ�");
							bsdl.addContent(bssrbcd);
						}
					}
					if(wsajjbqkModel.getBszjd()!=null){
						List<String> bszjdlist=wsajjbqkModel.getBszjd();
						for(int i=0;i<bszjdlist.size();i++){
							Element bszjd=new Element ("BSZJD").setAttribute("value",bszjdlist.get(i)); 
							bszjd.setAttribute("nameCN","����֤�ݶ�");
							bsdl.addContent(bszjd);
						}
					}
					if(wsajjbqkModel.getBssld()!=null){
						List<String> bssldlist=wsajjbqkModel.getBssld();
						for(int i=0;i<bssldlist.size();i++){
							Element bssld=new Element ("BSSLD").setAttribute("value",bssldlist.get(i)); 
							bssld.setAttribute("nameCN","���������");
							bsdl.addContent(bssld);
						}
					}
				}
				if(wsajjbqkModel.getZkdl()!=null){
					Element zkdl=new Element ("ZKDL").setAttribute("value",wsajjbqkModel.getZkdl()); 
					zkdl.setAttribute("nameCN","ָ�ض���");
					ajjbqk.addContent(zkdl);
					if(wsajjbqkModel.getZkss()!=null){
						Element zkss=new Element ("ZKSS").setAttribute("value",wsajjbqkModel.getZkss()); 
						zkss.setAttribute("nameCN","ָ����ʵ");
						zkdl.addContent(zkss);
					}
					if(wsajjbqkModel.getZkzj()!=null){
						Element zkzj=new Element ("ZKZJ").setAttribute("value",wsajjbqkModel.getZkzj()); 
						zkzj.setAttribute("nameCN","ָ��֤��");
						zkdl.addContent(zkzj);
					}
					if(wsajjbqkModel.getZkyj()!=null){
						Element zkyj=new Element ("ZKYJ").setAttribute("value",wsajjbqkModel.getZkyj()); 
						zkyj.setAttribute("nameCN","ָ�����");
						zkdl.addContent(zkyj);
					}
				}
				if(wsajjbqkModel.getBhdl()!=null){
					Element bhdl=new Element ("BHDL").setAttribute("value",wsajjbqkModel.getBhdl()); 
					bhdl.setAttribute("nameCN","�绤����");
					ajjbqk.addContent(bhdl);
					if(wsajjbqkModel.getBgrbc()!=null){
						List<String> bgrbc=wsajjbqkModel.getBgrbc();
						for(int i=0;i<bgrbc.size();i++){
							Element bgr=new Element ("BGRBC").setAttribute("value",bgrbc.get(i)); 
							bgr.setAttribute("nameCN","�����˱��");
							bhdl.addContent(bgr);
						}
					}
					if(wsajjbqkModel.getBhrbh()!=null){
						List<String> bhrbh=wsajjbqkModel.getBhrbh();
						for(int i=0;i<bhrbh.size();i++){
							Element bhr=new Element ("BHRBH").setAttribute("value",bhrbh.get(i)); 
							bhr.setAttribute("nameCN","�绤�˱绤");
							bhdl.addContent(bhr);
						}
					}
				}
				if(wsajjbqkModel.getFdmsssqqd()!=null){
					Element fdmsssqqd=new Element ("FDMSSSQQD").setAttribute("value",wsajjbqkModel.getFdmsssqqd()); 
					fdmsssqqd.setAttribute("nameCN","�����������������");
					ajjbqk.addContent(fdmsssqqd);
				}
				if(wsajjbqkModel.getXsbssld()!=null){
					Element xsbssld=new Element ("BSSLD").setAttribute("value",wsajjbqkModel.getXsbssld()); 
					xsbssld.setAttribute("nameCN","���������");
					ajjbqk.addContent(xsbssld);
					if(wsxszjdModellist!=null){
						for(int i=0;i<wsxszjdModellist.size();i++){
							WsxszjdModel wsxszjdmodel=wsxszjdModellist.get(i);
							Element zjfz=new Element ("ZJFZ"); 
							zjfz.setAttribute("nameCN","֤�ݷ���");
							xsbssld.addContent(zjfz);
							if(wsxszjdmodel.getRdss()!=null){
								Element rdss=new Element ("RDSS").setAttribute("value",wsxszjdmodel.getRdss()); 
								rdss.setAttribute("nameCN","�϶���ʵ");
								zjfz.addContent(rdss);
							}
							if(wsxszjdmodel.getZjjl()!=null){
								List<String>zjjllist=wsxszjdmodel.getZjjl();
								for(int j=0;j<zjjllist.size();j++){
									Element zjjl=new Element ("ZJJL").setAttribute("value",zjjllist.get(j)); 
									zjjl.setAttribute("nameCN","֤�ݼ�¼");
									zjfz.addContent(zjjl);
								}

							}
						}
					}
				}
				if(wsajjbqkModel.getXsqssld()!=null){
					Element xsqssld=new Element ("QSSLD").setAttribute("value",wsajjbqkModel.getXsqssld()); 
					xsqssld.setAttribute("nameCN","ǰ�������");
					ajjbqk.addContent(xsqssld);
					if(wsajjbqkModel.getQscmssyzj()!=null){
						Element qscmssyzj=new Element ("QSCMSSYZJ").setAttribute("value",wsajjbqkModel.getQscmssyzj()); 
						qscmssyzj.setAttribute("nameCN","ǰ�������ʵ��֤��");
						xsqssld.addContent(qscmssyzj);						
					}
					if(wsajjbqkModel.getQscpyzypjjg()!=null){
						Element qscpyzypjjg=new Element ("QSCPYZYPJJG").setAttribute("value",wsajjbqkModel.getQscpyzypjjg()); 
						qscpyzypjjg.setAttribute("nameCN","ǰ�����Ҫּ���о����");
						xsqssld.addContent(qscpyzypjjg);						
					}
				}
				if(wsajjbqkModel.getSsssbhyj()!=null){
					Element ssssbhyj=new Element ("SSSSBHYJ").setAttribute("value",wsajjbqkModel.getSsssbhyj()); 
					ssssbhyj.setAttribute("nameCN","�������߱绤���");
					ajjbqk.addContent(ssssbhyj);						
				}
				if(wsajjbqkModel.getGsjgctyj()!=null){
					Element jcjgyj=new Element ("JCJGYJ").setAttribute("value",wsajjbqkModel.getGsjgctyj()); 
					jcjgyj.setAttribute("nameCN","���������");
					ajjbqk.addContent(jcjgyj);						
				}
			}
		} else {
			// fileUtil.fileCopy(path, filename,
			// specialpath+"ajjbqkspecial", filename);
		}		
	}
	/**
	 * �������з������̽ڵ�
	 * @param root
	 * @param wsModel
	 */
	private static void buildCpfxgc(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// �������з������̽ڵ� ,��Ϊ�գ���������ȡ����cpfxgcspecial�ļ���
		if (wsModel.getWscpfxgcSegment() != null) {
			WscpfxgcModel wscpfxgcModel = wsModel.getWscpfxgcModel();
			Element cpfxgc = new Element("CPFXGC").setAttribute("value",
					wsModel.getWscpfxgcSegment());
			cpfxgc.setAttribute("nameCN", "���з�������");
			root.addContent(cpfxgc);
			if (wscpfxgcModel != null) {
				// �����᰸��ʽ���ͽڵ�
				if (wscpfxgcModel.getJafslx() != null) {
					Element jafslx = new Element("JAFSLX").setAttribute(
							"value", wscpfxgcModel.getJafslx());
					jafslx.setAttribute("nameCN", "�᰸��ʽ����");
					cpfxgc.addContent(jafslx);
				}
				// �����Ƿ񾭹���������ڵ�
				if (wscpfxgcModel.getSfjgxzfy() != null) {
					Element sfjgxzfy = new Element("SFJGXZFY")
							.setAttribute("value",
									wscpfxgcModel.getSfjgxzfy());
					sfjgxzfy.setAttribute("nameCN", "�Ƿ񾭹���������");
					cpfxgc.addContent(sfjgxzfy);
				}
				// ����������ΪΥ�����Ƚڵ�
				if (wscpfxgcModel.getXzxwwfbj() != null) {
					Element xzxwwfbj = new Element("XZXWWFBJ")
							.setAttribute("value",
									wscpfxgcModel.getXzxwwfbj());
					xzxwwfbj.setAttribute("nameCN", "������ΪΥ������");
					cpfxgc.addContent(xzxwwfbj);
				}
				// �����Ƿ����������⳥�ڵ�
				if (wscpfxgcModel.getXzpc() != null) {
					Element sftqxzpc = new Element("SFTQXZPC")
							.setAttribute("value", wscpfxgcModel.getXzpc());
					sftqxzpc.setAttribute("nameCN", "�Ƿ����������⳥");
					cpfxgc.addContent(sftqxzpc);
				}
				// ������ͥǰ���볷�����߽ڵ�
				if (wscpfxgcModel.getKtqsqchss() != null) {
					Element ktqsqchss = new Element("KTQSQCHSS")
							.setAttribute("value",
									wscpfxgcModel.getKtqsqchss());
					ktqsqchss.setAttribute("nameCN", "��ͥǰ���볷������");
					cpfxgc.addContent(ktqsqchss);
				}
				// �����᰸��ʽ�ڵ�
				// �������ɷ������ƣ���Ŀ����Ŀ�ڵ�
				if (wscpfxgcModel.getFtModellist() != null) {
					ArrayList<WscpfxgcFtModel> ftModellist = wscpfxgcModel
							.getFtModellist();
					for (int i = 0; i < ftModellist.size(); i++) {
						Element flftmc = new Element("FLFTMC")
								.setAttribute("value", ftModellist.get(i)
										.getFlftmc());
						flftmc.setAttribute("nameCN", "���ɷ�������");
						if (ftModellist.get(i).getFtMap() != null) {
							Map<String, String> ftMap = ftModellist.get(i)
									.getFtMap();
							for (Map.Entry<String, String> entry : ftMap
									.entrySet()) {
								Element tm = new Element("TM")
										.setAttribute("value",
												entry.getKey());
								tm.setAttribute("nameCN", "��Ŀ");
								if (entry.getValue() != "û�п�Ŀ") {
									Element km = new Element("KM")
											.setAttribute("value",
													entry.getValue());
									km.setAttribute("nameCN", "��Ŀ");
									tm.addContent(km);
								}
								flftmc.addContent(tm);
							}
						}
						Element sfcxf = new Element("SFCXF")
								.setAttribute("value", ftModellist.get(i)
										.getSfcxf());
						sfcxf.setAttribute("nameCN", "�Ƿ��ǳ���");
						flftmc.addContent(sfcxf);
						cpfxgc.addContent(flftmc);
					}
				}
				// �����������������ؽڵ�

				if (wscpfxgcModel.getFdlxModel() != null
						|| wscpfxgcModel.getFdlxModel() != null) {
					Element lxqk = new Element("LXQK").setAttribute(
							"nameCN", "�������");
					if (wscpfxgcModel.getFdlxModel() != null) {
						ArrayList<WscpfxgcFdlxModel> fdlxModel = wscpfxgcModel
								.getFdlxModel();
						for (int j = 0; j < fdlxModel.size(); j++) {
							// ��������������ڽڵ�
							Element fdlxqj = new Element("FDLXQJ")
									.setAttribute("nameCN", "�����������");
							if (wscpfxgcModel.getFdlxModel().get(j)
									.getXgr() != null) {
								ArrayList<String> xgrList = wscpfxgcModel
										.getFdlxModel().get(j).getXgr();
								for (int k = 0; k < xgrList.size(); k++) {
									// ��������˽ڵ�
									Element xgr = new Element("XGR")
											.setAttribute("value",
													xgrList.get(k));
									xgr.setAttribute("nameCN", "�����");
									fdlxqj.addContent(xgr);
								}
							}
							// ������ڽڵ�
							Element qj = new Element("QJ").setAttribute(
									"value", fdlxModel.get(j).getQj());
							qj.setAttribute("nameCN", "���");
							fdlxqj.addContent(qj);
							// ��������������ͽڵ�
							ArrayList<String> lxqjlbList = fdlxModel.get(j)
									.getLxqjlb();
							for (int k = 0; k < lxqjlbList.size(); k++) {
								Element lxqjlb = new Element("LXQJLB")
										.setAttribute("value",
												lxqjlbList.get(k));
								lxqjlb.setAttribute("nameCN", "�����������");
								fdlxqj.addContent(lxqjlb);
							}
							lxqk.addContent(fdlxqj);
						}
					}
					if (wscpfxgcModel.getZdlxModel() != null) {
						ArrayList<WscpfxgcZdlxModel> zdlxModel = wscpfxgcModel
								.getZdlxModel();
						for (int j = 0; j < zdlxModel.size(); j++) {
							// �����ö�������ڽڵ�
							Element zdlxqj = new Element("ZDLXQJ")
									.setAttribute("nameCN", "�ö��������");
							ArrayList<String> xgrList = wscpfxgcModel
									.getZdlxModel().get(j).getXgr();
							for (int k = 0; k < xgrList.size(); k++) {
								// ��������˽ڵ�
								Element xgr = new Element("XGR")
										.setAttribute("value",
												xgrList.get(k));
								xgr.setAttribute("nameCN", "�����");
								zdlxqj.addContent(xgr);
							}
							// �����ö���ڽڵ�
							Element qj = new Element("QJ").setAttribute(
									"value", zdlxModel.get(j).getQj());
							qj.setAttribute("nameCN", "���");
							zdlxqj.addContent(qj);
							// ��������������ͽڵ�
							ArrayList<String> lxqjlbList = zdlxModel.get(j)
									.getLxqjlb();
							for (int k = 0; k < lxqjlbList.size(); k++) {
								Element lxqjlb = new Element("LXQJLB")
										.setAttribute("value",
												lxqjlbList.get(k));
								lxqjlb.setAttribute("nameCN", "�����������");
								zdlxqj.addContent(lxqjlb);
							}
							lxqk.addContent(zdlxqj);
						}
					}
					if (lxqk.getChild("ZDLXQJ") != null) {
						cpfxgc.addContent(lxqk);
					}
				}
				// ������ͬ����ڵ�
				if (wscpfxgcModel.getGtfz() != null) {
					Element gtfz = new Element("GTFZ").setAttribute(
							"value", wscpfxgcModel.getGtfz());
					gtfz.setAttribute("nameCN", "��ͬ����");
					cpfxgc.addContent(gtfz);
				}
				// ����������ͬ���������ڵ�
				if (wscpfxgcModel.getBgrtyrzcx() != null) {
					Element bgrtyrzcx = new Element("BGRTYRZCX")
							.setAttribute("value",
									wscpfxgcModel.getBgrtyrzcx());
					bgrtyrzcx.setAttribute("nameCN", "������ͬ���������");
					cpfxgc.addContent(bgrtyrzcx);
				}
			}
		} else {
			// fileUtil.fileCopy(path, filename,
			// specialpath+"cpfxgcspecial", filename);
		}		
	}
	/**
	 * ����֮��İ������� �о����
	 * @param root
	 * @param wsModel
	 */
	private static void buildPjjg(Element root,WsModel wsModel){
		if(wsModel == null)
			return;
		WscpjgModel wscpjgModel = wsModel.getWscpjgModel();
		if (wsModel.getWscpjgSegment() != null) {
			Element cpjg = new Element("CPJG").setAttribute("value",
					wsModel.getWscpjgSegment());
			cpjg.setAttribute("nameCN", "���н��");
			root.addContent(cpjg);
			// �����᰸��ʽ�ڵ�
			if (wscpjgModel.getJafs() != null) {
				Element jafs = new Element("JAFS").setAttribute("value",
						wscpjgModel.getJafs());
				jafs.setAttribute("nameCN", "�᰸��ʽ");
				cpjg.addContent(jafs);
			}
			//��ͨ�¹� ͬʱ������Ȩ���뱣�չ�˾���⳥˳��
			if(wscpjgModel.getPcsxsfzq()!=null){
				Element pcsxsfzq = new Element("PCSFSXZQ").setAttribute("value",
						wscpjgModel.getPcsxsfzq());
				pcsxsfzq.setAttribute("nameCN", "ͬʱ������Ȩ���뱣�չ�˾���⳥˳��");
				cpjg.addContent(pcsxsfzq);
			}
			// �������н�����ݽڵ�
			if (wscpjgModel.getPjjgList() != null
					&& wscpjgModel.getPjjgList().size() > 0) {
				for (PjjgnrModel pjjgnrModel : wscpjgModel.getPjjgList()) {
					Element pjjg = new Element("JTPJJG").setAttribute(
							"value", pjjgnrModel.getPjjgnr());
					pjjg.setAttribute("nameCN", "������ж�");
					cpjg.addContent(pjjg);
					// Ȩ����
					if (pjjgnrModel.getQlr() != null) {
						for (Map.Entry<String, String> entry : pjjgnrModel
								.getQlr().entrySet()) {
							if (!StringUtil.isBlank(entry.getKey())) {
								Element qlr = new Element("QLR")
								.setAttribute("value",
										entry.getKey());
								qlr.setAttribute("nameCN", "Ȩ����");
								pjjg.addContent(qlr);
								// ���ϵ�λ
								if (!StringUtil.isBlank(entry.getValue())) {
									Element qlrdw = new Element("QLRSSDW")
									.setAttribute("value",
											entry.getValue());
									qlrdw.setAttribute("nameCN", "���ϵ�λ");
									qlr.addContent(qlrdw);
								}
							}

						}
					}
					// ������
					if (pjjgnrModel.getYwr() != null) {
						for (Map.Entry<String, String> entry : pjjgnrModel
								.getYwr().entrySet()) {
							if (!StringUtil.isBlank(entry.getKey())) {
								Element ywr = new Element("YWR")
								.setAttribute("value",
										entry.getKey());
								ywr.setAttribute("nameCN", "������");
								pjjg.addContent(ywr);
								// ���ϵ�λ
								if (!StringUtil.isBlank(entry.getValue())) {
									Element ywrdw = new Element("YWRSSDW")
									.setAttribute("value",
											entry.getValue());
									ywrdw.setAttribute("nameCN", "���ϵ�λ");
									ywr.addContent(ywrdw);
								}
							}
						}
					}
					// �о�ִ������
					if (!StringUtil.isBlank(pjjgnrModel.getPjzxqx())) {
						Element qx = new Element("PJZXQX").setAttribute(
								"value", pjjgnrModel.getPjzxqx());
						qx.setAttribute("nameCN", "�о�ִ������");
						pjjg.addContent(qx);
					}
					// �о����
					if (pjjgnrModel.getPjjeList() != null
							&& pjjgnrModel.getPjjeList().size() > 0) {
						for (PjjeModel jemodel : pjjgnrModel.getPjjeList()) {
							if (jemodel != null
									&& !StringUtil.isBlank(jemodel
											.getValue())) {
								Element pjje = new Element("PJJE")
								.setAttribute("nameCN", "�о����");
								pjjg.addContent(pjje);
								Element je = new Element("JE")
								.setAttribute("value",
										jemodel.getValue());
								je.setAttribute("nameCN", "���");
								pjje.addContent(je);
								if (jemodel.getCategorys() != null
										&& jemodel.getCategorys().size() > 0) {
									for (String s : jemodel.getCategorys()) {
										if (!StringUtil.isBlank(s)) {
											Element jezl = new Element(
													"JELX").setAttribute(
															"value", s);
											jezl.setAttribute("nameCN",
													"�������");
											pjje.addContent(jezl);
										}
									}
								}
							}
						}
					}
				}
			}
			// ������������ѽڵ�
			if (wscpjgModel.getSsfModel() != null
					&& !StringUtil.isBlank(wscpjgModel.getSsfModel()
							.getSsfjl())) {
				WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
				Element ajslf = new Element("SSFCD").setAttribute("nameCN",
						"���Ϸѳе�");
				cpjg.addContent(ajslf);
				Element cljl = new Element("SSFCDJL").setAttribute("value",
						wscpjgModel.getSsfModel().getSsfjl());
				cljl.setAttribute("nameCN", "���Ϸѳе���¼");
				ajslf.addContent(cljl);
				// ���Ϸѽ��
				if (ssfModel.getSsfjeModels() != null
						&& ssfModel.getSsfjeModels().size() > 0) {
					for (WsCpjgssfjeModel jeModel : ssfModel
							.getSsfjeModels()) {
						if (!StringUtil.isBlank(jeModel.getValue())) {
							Element sfzcssqq = new Element("SSFJE")
							.setAttribute("value",
									jeModel.getValue());
							sfzcssqq.setAttribute("nameCN", "���Ϸѽ��");
							cljl.addContent(sfzcssqq);
							if (!StringUtil.isBlank(jeModel.getCategory())) {
								Element zl = new Element("SSFZL")
								.setAttribute("value",
										jeModel.getCategory());
								zl.setAttribute("nameCN", "���Ϸ�����");
								cljl.addContent(zl);
							}
						}
					}
				}
				// ���Ϸѳе�
				if (ssfModel.getSsfcdModels() != null
						&& ssfModel.getSsfcdModels().size() > 0) {
					for (WscpjgssfcdModel cdModel : ssfModel
							.getSsfcdModels()) {
						if (!StringUtil.isBlank(cdModel.getCdr())) {
							Element cdr = new Element("SSFCDR")
							.setAttribute("value", cdModel.getCdr());
							cdr.setAttribute("nameCN", "�е���");
							cljl.addContent(cdr);
							if (!StringUtil.isBlank(cdModel.getCdje())) {
								Element cdje = new Element("SSFCDJE")
								.setAttribute("value",
										cdModel.getCdje());
								cdje.setAttribute("nameCN", "�е����");
								cdr.addContent(cdje);
							}
							if (!StringUtil.isBlank(cdModel.getCdfs())) {
								Element cdfs = new Element("SSFCDFS")
								.setAttribute("value",
										cdModel.getCdfs());
								cdfs.setAttribute("nameCN", "�е���ʽ");
								cdr.addContent(cdfs);
							}
							if (!StringUtil.isBlank(cdModel.getCdrdw())) {
								Element cddw = new Element("SSFCDDW")
								.setAttribute("value",
										cdModel.getCdrdw());
								cddw.setAttribute("nameCN", "�е��˵�λ");
								cdr.addContent(cddw);
							}
						}

					}
				}
			}

			// �����Ƿ�֧����������ڵ�
			if (wscpjgModel.getSfzcssqq() != null) {
				Element sfzcssqq = new Element("SFZCSSQQ").setAttribute(
						"value", wscpjgModel.getSfzcssqq());
				sfzcssqq.setAttribute("nameCN", "�Ƿ�֧����������");
				cpjg.addContent(sfzcssqq);
			}
			// ����ʤ�߷��ڵ�
			if (wscpjgModel.getSbsf() != null) {
				Element sbsf = new Element("SBSF").setAttribute("value",
						wscpjgModel.getSbsf());
				sbsf.setAttribute("nameCN", "ʤ�߷�");
				cpjg.addContent(sbsf);
			}
			if (wscpjgModel.getSffhcs() != null) {
				Element fhcs = new Element("SFFHCS").setAttribute("value",
						wscpjgModel.getSffhcs());
				fhcs.setAttribute("nameCN", "�Ƿ񷢻�����");
				cpjg.addContent(fhcs);
			}
			if (wscpjgModel.getFhcsyy() != null) {
				Element yy = new Element("FHCSYY").setAttribute("value",
						wscpjgModel.getFhcsyy());
				yy.setAttribute("nameCN", "��������ԭ��");
				cpjg.addContent(yy);
			}
			// �᰸��Ķ�
			if (wscpjgModel.getJabde() != null) {
				Element jabde = new Element("JABDE").setAttribute("nameCN",
						"�᰸��Ľ��");
				cpjg.addContent(jabde);
				for (String je : wscpjgModel.getJabde()) {
					Element jaje = new Element("JABDE_JE").setAttribute(
							"value", je + "Ԫ");
					jaje.setAttribute("nameCN", "���");
					jabde.addContent(jaje);
				}
			}
			// �᰸����ܶ�
			if (wscpjgModel.getJabdze() != null) {
				Element jazje = new Element("JABDZE").setAttribute("value",
						wscpjgModel.getJabdze());
				jazje.setAttribute("nameCN", "�᰸����ܶ�");
				cpjg.addContent(jazje);
			}
			// ��������
			if (!StringUtil.isBlank(wscpjgModel.getKssz())) {
				Element kssz = new Element("KSSZ").setAttribute("value",
						wscpjgModel.getKssz());
				kssz.setAttribute("nameCN", "��������");
				cpjg.addContent(kssz);
			}
			// �����ύ����
			if (!StringUtil.isBlank(wscpjgModel.getSstjcl())) {
				Element sstjcl = new Element("SSTJCL").setAttribute(
						"value", wscpjgModel.getSstjcl());
				sstjcl.setAttribute("nameCN", "�����ύ����");
				cpjg.addContent(sstjcl);
			}
			// ��������
			if (!StringUtil.isBlank(wscpjgModel.getSsqx())) {
				Element ssqx = new Element("SSQX").setAttribute("value",
						wscpjgModel.getSsqx());
				ssqx.setAttribute("nameCN", "��������");
				cpjg.addContent(ssqx);
			}
			// �����˼���
			if (wscpjgModel.getCsrjh() != null
					&& wscpjgModel.getCsrjh().size() > 0) {
				Element csrjh = new Element("CSRJH").setAttribute("nameCN",
						"�����˼���");
				cpjg.addContent(csrjh);
				for (String s : wscpjgModel.getCsrjh()) {
					if (!StringUtil.isBlank(s)) {
						Element ssqx = new Element("CSR").setAttribute(
								"value", s);
						ssqx.setAttribute("nameCN", "������");
						csrjh.addContent(ssqx);
					}
				}
			}
			// ��������
			if (!StringUtil.isBlank(wscpjgModel.getCslx())) {
				Element cslx = new Element("CSLX").setAttribute("value",
						wscpjgModel.getCslx());
				cslx.setAttribute("nameCN", "��������");
				cpjg.addContent(cslx);
			}
			// �����ϽȨ����
			if (!StringUtil.isBlank(wscpjgModel.getSftcgxyy())) {
				Element cslx = new Element("TCGXQYY").setAttribute("value",
						wscpjgModel.getSftcgxyy());
				cslx.setAttribute("nameCN", "�Ƿ������ϽȨ����");
				cpjg.addContent(cslx);
			}
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"cpjgspecial",
			// filename);
		}
	}
	/**
	 * �����о����
	 * @param wsModel
	 * @param root
	 */
	private static void buildXspjjg(WsModel wsModel,Element root){
		if(wsModel == null)
			return;
		WscpjgModel cpjgModel = wsModel.getWscpjgModel();
		if(cpjgModel == null)
			return;
		if(wsModel.getWscpjgSegment()!=null){
			Element pjjg=new Element ("PJJG").setAttribute("value",wsModel.getWscpjgSegment()); 
			pjjg.setAttribute("nameCN","�о����");
			root.addContent(pjjg);
			//				�Ƿ������ϽȨ����
			if(!StringUtil.isBlank(cpjgModel.getTcgxyy())){
				Element sftcggxqyy=new Element ("SFTCGXQYY").setAttribute("value",cpjgModel.getTcgxyy()); 
				sftcggxqyy.setAttribute("nameCN","�����ϽȨ����");
				pjjg.addContent(sftcggxqyy);
			}
			//				�᰸��ʽ
			if(!StringUtil.isBlank(cpjgModel.getJafs())){
				Element jafs=new Element ("JAFS").setAttribute("value",cpjgModel.getJafs()); 
				jafs.setAttribute("nameCN","�᰸��ʽ");
				pjjg.addContent(jafs);
			}
			//				һ�����²����о����
			if(!StringUtil.isBlank(cpjgModel.getYsxsbfpjjg())){
				Element ysxsbf=new Element ("YSXSBFPJJG").setAttribute("value",cpjgModel.getYsxsbfpjjg()); 
				ysxsbf.setAttribute("nameCN","һ�����²����о����");
				pjjg.addContent(ysxsbf);
			}
			//				�������²��ֲ��н��
			if(!StringUtil.isBlank(cpjgModel.getFdmscpjg())){
				Element ysxsbf=new Element ("FDMSBFCPJG").setAttribute("value",cpjgModel.getFdmscpjg()); 
				ysxsbf.setAttribute("nameCN","�������²��ֲ��н��");
				pjjg.addContent(ysxsbf);
			}
			//				�����о��������
			if(cpjgModel.getPjjgfzModels()!=null && cpjgModel.getPjjgfzModels().size()>0){
				for(XspjjgfzModel fzModel:cpjgModel.getPjjgfzModels()){
					if(!StringUtil.isBlank(fzModel.getEslxjg()) || !StringUtil.isBlank(fzModel.getEslxjg())){
						Element fz =new Element ("XSPJJGFZ").setAttribute("nameCN","�����о��������");
						pjjg.addContent(fz);
						
						if(fzModel.getSscyr()!=null){
							Element sscyr=new Element ("SSCYR").setAttribute("value",fzModel.getSscyr()); 
							sscyr.setAttribute("nameCN","���ϲ�����");
							fz.addContent(sscyr);
						}
						
						Element sscyr=new Element ("BSPJJG").setAttribute("nameCN","�����о����");
						fz.addContent(sscyr);
						
						if(!StringUtil.isBlank(fzModel.getEslxjg())){
							Element e=new Element ("ESLXJG").setAttribute("value",fzModel.getEslxjg()); 
							e.setAttribute("nameCN","�������̽��");
							sscyr.addContent(e);
						}
						if(!StringUtil.isBlank(fzModel.getEslxjg())){
							Element e=new Element ("ESSLJG").setAttribute("value",fzModel.getEssljg()); 
							e.setAttribute("nameCN","����������");
							sscyr.addContent(e);
						}
					}else{
						Element fz =new Element ("XSPJJGFZ").setAttribute("nameCN","�����о��������");
						pjjg.addContent(fz);
						if(fzModel.getSscyr()!=null){
							Element sscyr=new Element ("SSCYR").setAttribute("value",fzModel.getSscyr()); 
							sscyr.setAttribute("nameCN","���ϲ�����");
							fz.addContent(sscyr);
						}
						if(fzModel.getDzpf()!=null){
							for(PfModel pf:fzModel.getDzpf()){
								Element dzpf=new Element ("DZPF").setAttribute("value",pf.getPfnr()); 
								dzpf.setAttribute("nameCN","�����з�");
								fz.addContent(dzpf);
								if(pf.getZm()!=null){
									Element zm=new Element ("ZM").setAttribute("value",pf.getZm().getZm()); 
									zm.setAttribute("nameCN","����");
									dzpf.addContent(zm);
								}
								if(pf.getZx()!=null){
									Element zx=new Element ("ZX").setAttribute("nameCN","����");
									dzpf.addContent(zx);
									if(pf.getZx().getZxlb()!=null){
										Element zxlb=new Element ("ZXLB").setAttribute("value",pf.getZx().getZxlb()); 
										zxlb.setAttribute("nameCN","�������");
										zx.addContent(zxlb);
									}
									if(pf.getZx().getZxxq()!=null){
										Element zxlb=new Element ("ZXQX").setAttribute("value",pf.getZx().getZxxq()); 
										zxlb.setAttribute("nameCN","��������");
										zx.addContent(zxlb);
									}
								}
								if(pf.getFjxList()!=null){
									for(FjxModel fjx:pf.getFjxList()){
										Element fj=new Element ("FJX").setAttribute("nameCN","������");
										dzpf.addContent(fj);
										if(fjx.getLb()!=null){
											Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
											lb.setAttribute("nameCN","���������");
											fj.addContent(lb);
											if(StringUtil.equals(fjx.getLb(), "����")||StringUtil.equals(fjx.getLb(), "û�ո��˲��ֲƲ�")){
												if(fjx.getSe()!=null){
													Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
													se.setAttribute("nameCN","����");
													lb.addContent(se);
												}
												if(fjx.getBz()!=null){
													Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
													bz.setAttribute("nameCN","����");
													lb.addContent(bz);
												}
											}else if(StringUtil.equals(fjx.getLb(), "��������Ȩ��")){
												if(fjx.getQx()!=null){
													Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
													qx.setAttribute("nameCN","����");
													lb.addContent(qx);
												}
											}
										}
									}
								}
								if(pf.getHx()!=null){
									Element hx=new Element ("HX").setAttribute("nameCN","����");
									dzpf.addContent(hx);
									if(pf.getHx().getZxlb()!=null){
										Element lb=new Element ("HXLB").setAttribute("value",pf.getHx().getZxlb()); 
										lb.setAttribute("nameCN","�������");
										hx.addContent(lb);
									}
									if(pf.getHx().getZxxq()!=null){
										Element lb=new Element ("QX").setAttribute("value",pf.getHx().getZxxq()); 
										lb.setAttribute("nameCN","��������");
										hx.addContent(lb);
									}
								}
								if(pf.getPjjglx()!=null){
									Element pjjglx=new Element ("PJJGLX").setAttribute("value",pf.getPjjglx()); 
									pjjglx.setAttribute("nameCN","�о��������");
									dzpf.addContent(pjjglx);
								}
							}
						}

						if(fzModel.getZxpf()!=null){
							Element zxpf=new Element ("ZXPF").setAttribute("value",fzModel.getZxpf().getPfnr()); 
							zxpf.setAttribute("nameCN","ִ���з�");
							fz.addContent(zxpf);
							if(fzModel.getZxpf().getZx()!=null){
								Element zx=new Element ("ZX").setAttribute("nameCN","����");
								zxpf.addContent(zx);
								if(fzModel.getZxpf().getZx().getZxlb()!=null){
									Element zxlb=new Element ("ZXLB").setAttribute("value",fzModel.getZxpf().getZx().getZxlb()); 
									zxlb.setAttribute("nameCN","�������");
									zx.addContent(zxlb);
								}
								if(fzModel.getZxpf().getZx().getZxxq()!=null){
									Element zxlb=new Element ("ZXQX").setAttribute("value",fzModel.getZxpf().getZx().getZxxq()); 
									zxlb.setAttribute("nameCN","��������");
									zx.addContent(zxlb);
								}
							}
							if(fzModel.getZxpf().getFjxList()!=null){
								for(FjxModel fjx:fzModel.getZxpf().getFjxList()){
									Element fj=new Element ("FJX").setAttribute("nameCN","������");
									zxpf.addContent(fj);
									if(fjx.getLb()!=null){
										Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
										lb.setAttribute("nameCN","���������");
										fj.addContent(lb);
										if(StringUtil.equals(fjx.getLb(), "����")||StringUtil.equals(fjx.getLb(), "û�ո��˲��ֲƲ�")){
											if(fjx.getSe()!=null){
												Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
												se.setAttribute("nameCN","����");
												lb.addContent(se);
											}
											if(fjx.getBz()!=null){
												Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
												bz.setAttribute("nameCN","����");
												lb.addContent(bz);
											}
										}else if(StringUtil.equals(fjx.getLb(), "��������Ȩ��")){
											if(fjx.getQx()!=null){
												Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
												qx.setAttribute("nameCN","����");
												lb.addContent(qx);
											}
										}
									}
								}
							}
							if(fzModel.getZxpf().getHx()!=null){
								Element hx=new Element ("HX").setAttribute("nameCN","����");
								zxpf.addContent(hx);
								if(fzModel.getZxpf().getHx().getZxlb()!=null){
									Element lb=new Element ("HXLB").setAttribute("value",fzModel.getZxpf().getHx().getZxlb()); 
									lb.setAttribute("nameCN","�������");
									hx.addContent(lb);
								}
								if(fzModel.getZxpf().getHx().getZxxq()!=null){
									Element lb=new Element ("QX").setAttribute("value",fzModel.getZxpf().getHx().getZxxq()); 
									lb.setAttribute("nameCN","��������");
									hx.addContent(lb);
								}
							}
							if(fzModel.getZxpf().getPjjglx()!=null){
								Element pjjglx=new Element ("PJJGLX").setAttribute("value",fzModel.getZxpf().getPjjglx()); 
								pjjglx.setAttribute("nameCN","�о��������");
								zxpf.addContent(pjjglx);
							}
						}
						if(fzModel.getPjzzm()!=null){
							ZmModel pjzzmModel = fzModel.getPjzzm();
							if(pjzzmModel.getZm()!=null){
								Element pjzzm=new Element ("PJZZM").setAttribute("value",pjzzmModel.getZm()); 
								pjzzm.setAttribute("nameCN","�о�������");
								fz.addContent(pjzzm);
							}
						}
						if(fzModel.getXqksrq()!=null || fzModel.getXqjsrq()!=null){
							Element xqqzrq=new Element ("XQQZRQ").setAttribute("nameCN","������ֹ����");
							fz.addContent(xqqzrq);
							if(fzModel.getXqksrq()!=null){
								Element xqksrq=new Element ("XQKSRQ").setAttribute("value",fzModel.getXqksrq()); 
								xqksrq.setAttribute("nameCN","���ڿ�ʼ����");
								xqqzrq.addContent(xqksrq);
							}
							if(fzModel.getXqjsrq()!=null){
								Element xqjsrq=new Element ("XQJSRQ").setAttribute("value",fzModel.getXqjsrq()); 
								xqjsrq.setAttribute("nameCN","���ڽ�������");
								xqqzrq.addContent(xqjsrq);
							}
						}
						if(fzModel.getXqzdbf()!=null){
							Element xqzdbf=new Element ("XQZDBF").setAttribute("value",fzModel.getXqzdbf()); 
							xqzdbf.setAttribute("nameCN","�����۵ְ취");
							fz.addContent(xqzdbf);
						}
						if(fzModel.getMzhwzsf()!=null){
							Element xqzdbf=new Element ("MZHWZSF").setAttribute("value",fzModel.getMzhwzsf()); 
							xqzdbf.setAttribute("nameCN","����������ͷ�");
							fz.addContent(xqzdbf);
						}
						if(fzModel.getSzbf()!=null){
							Element xqzdbf=new Element ("SZBF").setAttribute("value",fzModel.getSzbf()); 
							xqzdbf.setAttribute("nameCN","���ﲢ��");
							fz.addContent(xqzdbf);
						}
						if(fzModel.getHblx()!=null){
							Element xqzdbf=new Element ("HBLX").setAttribute("value",fzModel.getHblx()); 
							xqzdbf.setAttribute("nameCN","�ϲ�����");
							fz.addContent(xqzdbf);
						}
						//ԭ��
						if(fzModel.getYzpf()!=null &&fzModel.getYzpf().size()>0 ){
							Element yzpjjg=new Element ("YZPJJG").setAttribute("nameCN","ԭ���о����");
							fz.addContent(yzpjjg);
							for(PfModel yzpf:fzModel.getYzpf()){

								Element yz=new Element ("YZPF").setAttribute("value",yzpf.getPfnr()); 
								yz.setAttribute("nameCN","ԭ���з�");
								yzpjjg.addContent(yz);


								if(yzpf.getZm()!=null){
									Element zm=new Element ("YZZM").setAttribute("value",yzpf.getZm().getZm()); 
									zm.setAttribute("nameCN","����");
									yz.addContent(zm);
								}
								if(yzpf.getZx()!=null){
									Element zx=new Element ("ZX").setAttribute("nameCN","����");
									yz.addContent(zx);
									if(yzpf.getZx().getZxlb()!=null){
										Element zxlb=new Element ("ZXLB").setAttribute("value",yzpf.getZx().getZxlb()); 
										zxlb.setAttribute("nameCN","�������");
										zx.addContent(zxlb);
									}
									if(yzpf.getZx().getZxxq()!=null){
										Element zxlb=new Element ("ZXQX").setAttribute("value",yzpf.getZx().getZxxq()); 
										zxlb.setAttribute("nameCN","��������");
										zx.addContent(zxlb);
									}
								}
								if(yzpf.getFjxList()!=null){
									for(FjxModel fjx:yzpf.getFjxList()){
										Element fj=new Element ("FJX").setAttribute("nameCN","������");
										yz.addContent(fj);
										if(fjx.getLb()!=null){
											Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
											lb.setAttribute("nameCN","���������");
											fj.addContent(lb);
											if(StringUtil.equals(fjx.getLb(), "����")||StringUtil.equals(fjx.getLb(), "û�ո��˲��ֲƲ�")){
												if(fjx.getSe()!=null){
													Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
													se.setAttribute("nameCN","����");
													lb.addContent(se);
												}
												if(fjx.getBz()!=null){
													Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
													bz.setAttribute("nameCN","����");
													lb.addContent(bz);
												}
											}else if(StringUtil.equals(fjx.getLb(), "��������Ȩ��")){
												if(fjx.getQx()!=null){
													Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
													qx.setAttribute("nameCN","����");
													lb.addContent(qx);
												}
											}
										}
									}
								}
								if(yzpf.getHx()!=null){
									Element hx=new Element ("HX").setAttribute("nameCN","����");
									yz.addContent(hx);
									if(yzpf.getHx().getZxlb()!=null){
										Element lb=new Element ("HXLB").setAttribute("value",yzpf.getHx().getZxlb()); 
										lb.setAttribute("nameCN","�������");
										hx.addContent(lb);
									}
									if(yzpf.getHx().getZxxq()!=null){
										Element lb=new Element ("QX").setAttribute("value",yzpf.getHx().getZxxq()); 
										lb.setAttribute("nameCN","��������");
										hx.addContent(lb);
									}
								}
							}
						}
					}
				}
			}
			//�������
			if(!StringUtil.isBlank(cpjgModel.getDfdmspccl())){
				Element ysxsbf=new Element ("DFDMSPCDCL").setAttribute("value",cpjgModel.getDfdmspccl()); 
				ysxsbf.setAttribute("nameCN","�Ը��������⳥�Ĵ���");
				pjjg.addContent(ysxsbf);
			}
			if(cpjgModel.getFdmspjfzModel()!=null && !StringUtil.isBlank(cpjgModel.getFdmspjfzModel().getNr())){
				Element ysxsbf=new Element ("FDMSPJJGFZ").setAttribute("value",cpjgModel.getFdmspjfzModel().getNr()); 
				ysxsbf.setAttribute("nameCN","���������о��������");
				pjjg.addContent(ysxsbf);
				if(cpjgModel.getFdmspjfzModel().getBcje()!=null){
					for(String s:cpjgModel.getFdmspjfzModel().getBcje()){
						Element je=new Element ("PCJE").setAttribute("value",s); 
						je.setAttribute("nameCN","�⳥���");
						ysxsbf.addContent(je);
					}
				}
				if(cpjgModel.getFdmspjfzModel().getBpcr()!=null){
					for(String s:cpjgModel.getFdmspjfzModel().getBpcr()){
						Element je=new Element ("BPCR").setAttribute("value",s); 
						je.setAttribute("nameCN","���⳥��");
						ysxsbf.addContent(je);
					}
				}
				if(cpjgModel.getFdmspjfzModel().getPcr()!=null){
					for(String s:cpjgModel.getFdmspjfzModel().getPcr()){
						Element je=new Element ("PCR").setAttribute("value",s); 
						je.setAttribute("nameCN","�⳥��");
						ysxsbf.addContent(je);
					}
				}
			}
			//				���·������
			if(!StringUtil.isBlank(cpjgModel.getKssz())){
				Element ysxsbf=new Element ("KSSZ").setAttribute("value",cpjgModel.getKssz()); 
				ysxsbf.setAttribute("nameCN","��������");
				pjjg.addContent(ysxsbf);
			}
			if(!StringUtil.isBlank(cpjgModel.getSsqx())){
				Element ysxsbf=new Element ("SSQX").setAttribute("value",cpjgModel.getSsqx()); 
				ysxsbf.setAttribute("nameCN","��������");
				pjjg.addContent(ysxsbf);
			}
			if(!StringUtil.isBlank(cpjgModel.getSstjcl())){
				Element ysxsbf=new Element ("SSTJCL").setAttribute("value",cpjgModel.getSstjcl()); 
				ysxsbf.setAttribute("nameCN","�����ύ����");
				pjjg.addContent(ysxsbf);
			}
			if(cpjgModel.getCsrjh()!=null && cpjgModel.getCsrjh().size()>0){
				Element ysxsbf=new Element ("CSRJH").setAttribute("nameCN","�����˼���");
				pjjg.addContent(ysxsbf);
				for(String s:cpjgModel.getCsrjh()){
					Element csr=new Element ("CSR").setAttribute("value",s); 
					csr.setAttribute("nameCN","������");
					ysxsbf.addContent(csr);
				}
			}
			if(!StringUtil.isBlank(cpjgModel.getCslx())){
				Element ysxsbf=new Element ("CSLX").setAttribute("value",cpjgModel.getCslx()); 
				ysxsbf.setAttribute("nameCN","��������");
				pjjg.addContent(ysxsbf);
			}
		}
	}
	/**
	 * ������β�ڵ�
	 * @param root
	 * @param wsModel
	 */
	private static void buildWw(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// ������β�ڵ� ,��Ϊ�գ���������ȡ����wwspecial�ļ���
		if (wsModel.getWswwSegment() != null) {
			WswwModel wswwModel = wsModel.getWswwModel();
			Element ww = new Element("WW").setAttribute("value",
					wsModel.getWswwSegment());
			ww.setAttribute("nameCN", "��β");
			root.addContent(ww);
			// ������β��Ϣ�ڵ�
			// ������֯��Ա�ڵ�
			if (wswwModel.getSpzzcyMap() != null) {
				Map<String, String> spzzcyMap = wswwModel.getSpzzcyMap();
				for (Map.Entry<String, String> entry : spzzcyMap.entrySet()) {
					Element spzzcy = new Element("SPZZCY").setAttribute(
							"nameCN", "������֯��Ա");
					Element spryxm = new Element("SPRYXM").setAttribute(
							"value", entry.getKey());
					spryxm.setAttribute("nameCN", "������Ա����");
					spzzcy.addContent(spryxm);
					Element spryjs = new Element("SPRYJS").setAttribute(
							"value", entry.getValue());
					spryjs.setAttribute("nameCN", "������Ա��ɫ");
					spzzcy.addContent(spryjs);
					ww.addContent(spzzcy);
				}
			}
			// �������ڽڵ�
			if (wswwModel.getWsrq() != null) {
				Element wsrq = new Element("CPSJ").setAttribute("value",
						wswwModel.getWsrq());
				wsrq.setAttribute("nameCN", "����ʱ��");
				ww.addContent(wsrq);
				// ���������սڵ�
				if (wswwModel.getYear() != null) {
					Element year = new Element("Year").setAttribute(
							"value", wswwModel.getYear());
					year.setAttribute("nameCN", "�᰸���");
					wsrq.addContent(year);
				}
				if (wswwModel.getWsrq() != null) {
					Element time = new Element("JANYR").setAttribute(
							"value", wswwModel.getWsrq());
					time.setAttribute("nameCN", "�᰸������");
					wsrq.addContent(time);
				}
				if (wswwModel.getYearAndMonth() != null) {
					Element yearmonth = new Element("JANY").setAttribute(
							"value", wswwModel.getYearAndMonth());
					yearmonth.setAttribute("nameCN", "�᰸����");
					wsrq.addContent(yearmonth);
				}
				if (wswwModel.getMonth() != null) {
					Element month = new Element("Month").setAttribute(
							"value", wswwModel.getMonth());
					month.setAttribute("nameCN", "��");
					wsrq.addContent(month);
				}
				if (wswwModel.getDay() != null) {
					Element day = new Element("Day").setAttribute("value",
							wswwModel.getDay());
					day.setAttribute("nameCN", "��");
					wsrq.addContent(day);
				}
			}
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"wwspecial",
			// filename);
		}
		
	}
	/**
	 * ������¼�ڵ�
	 * @param root
	 * @param wsModel
	 */
	private static void buildFl(Element root,WsModel wsModel){
		if(wsModel == null)
			return;
		if (wsModel.getWsfl() != null) {
			Element fl = new Element("FL").setAttribute("value",
					wsModel.getWsfl());
			fl.setAttribute("nameCN", "��¼");
			root.addContent(fl);
		}
	}
}
