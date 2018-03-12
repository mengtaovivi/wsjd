package nju.software.wsjx.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.service.model.WsfdModel;
import nju.software.wsjx.service.model.xs.FjxModel;
import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;
import nju.software.wsjx.service.model.xs.ZmModel;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class XsXmlUtil {
	public static void BuildXMLDoc(WsfdModel wsfdModel,XsPjjgModel pjjgModel,String outputpath,String filename,String path) throws IOException, JDOMException {     
		FileUtil fileUtil = new FileUtil();  

		String specialpath="C:\\Users\\super\\Desktop\\";

		// �������ڵ� ȫ�� ,��ȫ��Ϊ�գ���������ȡ����special�ļ���
		if(wsfdModel.getQw()!=null){		 
			Element root = new Element("QW").setAttribute("value", wsfdModel.getQw());     
			root.setAttribute("nameCN","ȫ��");
			// �����ڵ���ӵ��ĵ��У�     
			Document Doc = new Document(root);   

			if(wsfdModel.getCpjg()!=null){
				Element pjjg=new Element ("PJJG").setAttribute("value",wsfdModel.getCpjg()); 
				pjjg.setAttribute("nameCN","�о����");
				root.addContent(pjjg);
//				�Ƿ������ϽȨ����
				if(!StringUtil.isBlank(pjjgModel.getTcgxyy())){
					Element sftcggxqyy=new Element ("SFTCGXQYY").setAttribute("value",pjjgModel.getTcgxyy()); 
					sftcggxqyy.setAttribute("nameCN","�����ϽȨ����");
					pjjg.addContent(sftcggxqyy);
				}
//				�᰸��ʽ
				if(!StringUtil.isBlank(pjjgModel.getJafs())){
					Element jafs=new Element ("JAFS").setAttribute("value",pjjgModel.getJafs()); 
					jafs.setAttribute("nameCN","�᰸��ʽ");
					pjjg.addContent(jafs);
				}
//				һ�����²����о����
				if(!StringUtil.isBlank(pjjgModel.getYsxsbfpjjg())){
					Element ysxsbf=new Element ("YSXSBFPJJG").setAttribute("value",pjjgModel.getYsxsbfpjjg()); 
					ysxsbf.setAttribute("nameCN","һ�����²����о����");
					pjjg.addContent(ysxsbf);
				}
//				�������²��ֲ��н��
				if(!StringUtil.isBlank(pjjgModel.getFdmscpjg())){
					Element ysxsbf=new Element ("FDMSBFCPJG").setAttribute("value",pjjgModel.getFdmscpjg()); 
					ysxsbf.setAttribute("nameCN","�������²��ֲ��н��");
					pjjg.addContent(ysxsbf);
				}
//				�����о��������
				if(pjjgModel.getPjjgfzModels()!=null && pjjgModel.getPjjgfzModels().size()>0){
					for(XspjjgfzModel fzModel:pjjgModel.getPjjgfzModels()){
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
				if(!StringUtil.isBlank(pjjgModel.getDfdmspccl())){
					Element ysxsbf=new Element ("DFDMSPCDCL").setAttribute("value",pjjgModel.getDfdmspccl()); 
					ysxsbf.setAttribute("nameCN","�Ը��������⳥�Ĵ���");
					pjjg.addContent(ysxsbf);
				}
				if(pjjgModel.getFdmspjfzModel()!=null && !StringUtil.isBlank(pjjgModel.getFdmspjfzModel().getNr())){
					Element ysxsbf=new Element ("FDMSPJJGFZ").setAttribute("value",pjjgModel.getFdmspjfzModel().getNr()); 
					ysxsbf.setAttribute("nameCN","���������о��������");
					pjjg.addContent(ysxsbf);
					if(pjjgModel.getFdmspjfzModel().getBcje()!=null){
						for(String s:pjjgModel.getFdmspjfzModel().getBcje()){
							Element je=new Element ("PCJE").setAttribute("value",s); 
							je.setAttribute("nameCN","�⳥���");
							ysxsbf.addContent(je);
						}
					}
					if(pjjgModel.getFdmspjfzModel().getBpcr()!=null){
						for(String s:pjjgModel.getFdmspjfzModel().getBpcr()){
							Element je=new Element ("BPCR").setAttribute("value",s); 
							je.setAttribute("nameCN","���⳥��");
							ysxsbf.addContent(je);
						}
					}
					if(pjjgModel.getFdmspjfzModel().getPcr()!=null){
						for(String s:pjjgModel.getFdmspjfzModel().getPcr()){
							Element je=new Element ("PCR").setAttribute("value",s); 
							je.setAttribute("nameCN","�⳥��");
							ysxsbf.addContent(je);
						}
					}
				}
//				���·������
				if(!StringUtil.isBlank(pjjgModel.getKssz())){
					Element ysxsbf=new Element ("KSSZ").setAttribute("value",pjjgModel.getKssz()); 
					ysxsbf.setAttribute("nameCN","��������");
					pjjg.addContent(ysxsbf);
				}
				if(!StringUtil.isBlank(pjjgModel.getSsqx())){
					Element ysxsbf=new Element ("SSQX").setAttribute("value",pjjgModel.getSsqx()); 
					ysxsbf.setAttribute("nameCN","��������");
					pjjg.addContent(ysxsbf);
				}
				if(!StringUtil.isBlank(pjjgModel.getSstjcl())){
					Element ysxsbf=new Element ("SSTJCL").setAttribute("value",pjjgModel.getSstjcl()); 
					ysxsbf.setAttribute("nameCN","�����ύ����");
					pjjg.addContent(ysxsbf);
				}
				if(pjjgModel.getCsrjh()!=null && pjjgModel.getCsrjh().size()>0){
					Element ysxsbf=new Element ("CSRJH").setAttribute("nameCN","�����˼���");
					pjjg.addContent(ysxsbf);
					for(String s:pjjgModel.getCsrjh()){
						Element csr=new Element ("CSR").setAttribute("value",s); 
						csr.setAttribute("nameCN","������");
						ysxsbf.addContent(csr);
					}
				}
				if(!StringUtil.isBlank(pjjgModel.getCslx())){
					Element ysxsbf=new Element ("CSLX").setAttribute("value",pjjgModel.getCslx()); 
					ysxsbf.setAttribute("nameCN","��������");
					pjjg.addContent(ysxsbf);
				}
			}
			// ʹxml�ļ� ����Ч��  
			Format format = Format.getPrettyFormat();  
			XMLOutputter XMLOut = new XMLOutputter(format);  
			XMLOut.output(Doc, new FileOutputStream(outputpath+"\\"+filename+".xml"));  	
		}else{
//			fileUtil.fileCopy(path, filename, specialpath+"qwspecial", filename);
		}

	}  
	
}
