package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.TestXspjjgService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.service.model.xs.FjxModel;
import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XMLReader;

public class TestXspjjgServiceImpl implements TestXspjjgService{

	@Override
	public TestModel testKssz(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getKssz();//��������
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testSsqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getSsqx();
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testTjcl(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getSstjcl();
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testTcgxyy(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getTcgxyy();
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testJafs(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String jafs = pjjgModel.getJafs();
//		if(judgeXsysJafs(jafs, content)){
		if(judgeXsesJafs(jafs, content)){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+jafs);
			System.out.println("���"+content);
		}
		return testModel;
	}

	public boolean judgeXsysJafs(String jafs,String content){
		if(StringUtil.equals(jafs, content)||StringUtil.equals(content, "��")){
			return true;
		}else if((StringUtil.equals(jafs, "��������")||StringUtil.equals(jafs, "��������")||StringUtil.equals(jafs, "����Ǽ�����"))&&StringUtil.equals(content, "��������")){
			return true;
		}else if((StringUtil.equals(jafs, "׼�賷��")||StringUtil.equals(jafs, "�����ߴ���"))&&StringUtil.equals(content, "����")){
			return true;
		}else if(StringUtil.equals(jafs, "��ֹ����")&&StringUtil.equals(content, "��ֹ")){
			return true;
		}else if (StringUtil.equals(content, "����")){
			return true;
		}
		return false;
	}

	public boolean judgeXsesJafs(String jafs,String content){
		if(StringUtil.equals(jafs, content)||StringUtil.equals(content, "��")){
			return true;
		}else if((StringUtil.contains(jafs, "����ԭ�ö�")||StringUtil.contains(jafs, "���û��Υ�����òö�")||StringUtil.contains(jafs, "׼�������߲�����һ�����"))
				&&StringUtil.contains(content, "�������ԭ�ö�")){
			return true;
		}else if(StringUtil.contains(jafs, "����")&&StringUtil.contains(content, "����")){
			return true;
		}else if(StringUtil.contains(jafs, "��ֹ����")&&StringUtil.contains(content, "��ֹ")){
			return true;
		}
		return false;
	}
	@Override
	public TestModel testYsxsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String jafs = pjjgModel.getYsxsbfpjjg();
		if(StringUtil.equals(content, jafs)){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("һ�����²��ֲ��н�����ԣ�"+jafs);
			System.out.println("һ�����²��ֲ��н�����"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testXspjjgfzsscyr(TestModel testModel,
			XsPjjgModel pjjgModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getSscyr())){
					cyrList.add(fzModel.getSscyr());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ԣ�"+cyrList);
			System.out.println("���"+contents);
		}
		return testModel;
	}
	
	/**
	 * @param content
	 * @param tests
	 * @return
	 */
	public boolean containsStr(List<String> test,List<String> contents){
		for(int i=0;i<contents.size();i++){
			if(!test.contains(contents.get(i))){
				return false;
			}
		}
		return true;
	}

	@Override
	public TestModel testBsdzpfzm(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZm()!=null && !StringUtil.isBlank(pf.getZm().getZm())){
							 zmList.add(pf.getZm().getZm());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�����������ԣ�"+zmList);
			System.out.println("�����������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testBsxzpfzm(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getPjzzm()!=null && !StringUtil.isBlank(fzModel.getPjzzm().getZm())){
					cyrList.add(fzModel.getPjzzm().getZm());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ԣ�"+cyrList);
			System.out.println("���"+contents);
		}
		return testModel;
	}
	
	public TestModel testZxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZx()!=null && !StringUtil.isBlank(pf.getZx().getZxlb())){
							 zmList.add(pf.getZx().getZxlb());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�������������ԣ�"+zmList);
			System.out.println("������������"+contents);
		}
		return testModel;
	}
	
	@Override
	public TestModel testZxpfZxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getZx()!=null){
					 if(!StringUtil.isBlank(fzModel.getZxpf().getZx().getZxlb())){
						 zmList.add(fzModel.getZxpf().getZx().getZxlb());
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("ִ�����������ԣ�"+zmList);
			System.out.println("ִ����������"+contents);
		}
		return testModel;
	}
	public TestModel testZxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZx()!=null && !StringUtil.isBlank(pf.getZx().getZxxq())){
							 zmList.add(pf.getZx().getZxxq());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�����������޲��ԣ�"+zmList);
			System.out.println("�����������޻��"+contents);
		}
		return testModel;
	}
	
	@Override
	public TestModel testZxpfZxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getZx()!=null){
					 if(!StringUtil.isBlank(fzModel.getZxpf().getZx().getZxxq())){
						 zmList.add(fzModel.getZxpf().getZx().getZxxq());
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("ִ���������޲��ԣ�"+zmList);
			System.out.println("ִ���������޻��"+contents);
		}
		return testModel;
	}
	public TestModel testHxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getHx()!=null && !StringUtil.isBlank(pf.getHx().getZxlb())){
							 zmList.add(pf.getHx().getZxlb());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ﻺ��������޲��ԣ�"+zmList);
			System.out.println("���ﻺ������"+contents);
		}
		return testModel;
	}
	public TestModel testHxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getHx()!=null && !StringUtil.isBlank(pf.getHx().getZxxq())){
							 zmList.add(pf.getHx().getZxxq());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ﻺ��������޲��ԣ�"+zmList);
			System.out.println("���ﻺ������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testZxHxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getHx()!=null && !StringUtil.isBlank(fzModel.getZxpf().getHx().getZxlb())){
					zmList.add(fzModel.getZxpf().getHx().getZxlb());
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("ִ�л���������޲��ԣ�"+zmList);
			System.out.println("ִ�л�������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testZxHxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getHx()!=null && !StringUtil.isBlank(fzModel.getZxpf().getHx().getZxxq())){
					zmList.add(fzModel.getZxpf().getHx().getZxxq());
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("ִ���з�����������޲��ԣ�"+zmList);
			System.out.println("ִ���з���������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testPjjglx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(!StringUtil.isBlank(pf.getPjjglx())){
							 zmList.add(pf.getPjjglx());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�����о��������޲��ԣ�"+zmList);
			System.out.println("�����о����ͻ��"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testZxPjjglx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && !StringUtil.isBlank(fzModel.getZxpf().getPjjglx())){
					zmList.add(fzModel.getZxpf().getPjjglx());
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("ִ���з��о�������Ͳ��ԣ�"+zmList);
			System.out.println("ִ���з��о�������ͻ��"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testXqksrq(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getXqksrq())){
					cyrList.add(fzModel.getXqksrq());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ڿ�ʼ���ԣ�"+cyrList);
			System.out.println("���ڿ�ʼ���"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testXqjsrq(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getXqjsrq())){
					cyrList.add(fzModel.getXqjsrq());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ڽ������ԣ�"+cyrList);
			System.out.println("���ڽ������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testXqzdbf(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getXqzdbf())){
					cyrList.add(fzModel.getXqzdbf());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�����۵ְ취���ԣ�"+cyrList);
			System.out.println("�����۵ְ취��ʼ���"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testSzbf(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getSzbf())){
					cyrList.add(fzModel.getSzbf());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���ﲢ�����ԣ�"+cyrList);
			System.out.println("���ﲢ�����"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testMzhwzsf(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getMzhwzsf())){
					cyrList.add(fzModel.getMzhwzsf());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("����������ͷŲ��ԣ�"+cyrList);
			System.out.println("����������ͷŻ��"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testHblx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getHblx())){
					cyrList.add(fzModel.getHblx());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�ϲ����̲��ԣ�"+cyrList);
			System.out.println("�ϲ����̻��"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testDfdmspcdcl(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getDfdmspccl();
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testFdmsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getFdmscpjg();
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getLb())){
									 zmList.add(fjx.getLb()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���︽����������޲��ԣ�"+zmList);
			System.out.println("���︽��������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getQx())){
									 zmList.add(fjx.getQx()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���︽�����������޲��ԣ�"+zmList);
			System.out.println("���︽�������޻��"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxje(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getSe())){
									 zmList.add(fjx.getSe()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���︽�����������޲��ԣ�"+zmList);
			System.out.println("���︽��������"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxbz(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getBz())){
									 zmList.add(fjx.getBz()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("���︽���̱������޲��ԣ�"+zmList);
			System.out.println("���︽���̱��ֻ��"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testGpyy(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
			XMLReader xr = new XMLReader();
			List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
			List<String> gpyyz = pjjgModel.getGpyy();
			boolean flag = true;
			if(!contents.get(0).equals("��") && gpyyz!=null){
				for(String content:contents){
					if(!gpyyz.contains(content)){
						flag = false;
						break;
					}
				}
			}
			
			if(flag){
				testModel.setCoNum(testModel.getCoNum()+1);
			}else{
					System.out.println("���ԣ�"+gpyyz);
				System.out.println("���"+contents);
			}
			return testModel;
	}

	@Override
	public TestModel testJtfz(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getJtfz();//���ŷ���
		if(StringUtil.equals(content, ssfy) || content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testBsdzpfzmdm(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("��")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZm()!=null && !StringUtil.isBlank(pf.getZm().getZmdm())){
							 zmList.add(pf.getZm().getZmdm());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("�����������ԣ�"+zmList);
			System.out.println("�����������"+contents);
		}
		return testModel;
	}

}
