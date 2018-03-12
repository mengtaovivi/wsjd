package nju.software.wsjx.service.impl;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;
import nju.software.wsjx.service.TestSscyrService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XMLReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestSscyrServiceImpl implements TestSscyrService{


	/**
	 * ���Ե����˶�
	 */
	@Override
	public TestModel testSscyrqj(TestModel testModel, WsfdModel wsfdModel,
								 String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String sscryqj=wsfdModel.getSscyr();
		String content=xr.getXMLNode(inputpath+"//"+filename, node[1]);
		if (sscryqj == null || content == null) {
			System.out.println(sscryqj + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "sscryqjspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_2(content, sscryqj)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(sscryqj + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "sscryqjspecial",
					filename);
		}
		return null;
	}

	@Override
	public TestModel testSscyrmc(TestModel testModel,
								 List<WssscyrModel> wssscyrModellist, String inputpath,
								 String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "//"
				+ filename, node[1]);
		int index = 0;
		if (wssscyrModellist != null) {
			for (int i = 0; i < wssscyrModellist.size(); i++) {
				WssscyrModel wssscyrModel = wssscyrModellist.get(i);
				String sscyrmc = wssscyrModel.getSscyr();
				boolean b = false;
				for (int j = 0; j < contentlist.size(); j++) {
					if(sscyrmc!=null&&contentlist.get(j)!=null){
						if (ts.judgeNode_2(sscyrmc,contentlist.get(j))) {
							index++;
							b = true;
							break;
						}
					}

				}
				if (b == false) {
					System.out.println("���ϲ����ˣ�" + sscyrmc + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "sscyrmcspecial", filename);
				}
			}
			if (index == wssscyrModellist.size()) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;

	}


	/**
	 * �������ϵ�λ
	 */
	@Override
	public TestModel testSsdw(TestModel testModel,
							  List<WssscyrModel> wssscyrModellist, String inputpath,
							  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "//"
				+ filename, node[1]);
		int index = 0;
		int temp =0;
		if (wssscyrModellist != null) {
			for (int i = 0; i < wssscyrModellist.size(); i++) {
				WssscyrModel wssscyrModel = wssscyrModellist.get(i);
				String ssdw = wssscyrModel.getSsdw();
				boolean b = false;
				if(ssdw!=null){
					temp++;
					for (int j = 0; j < contentlist.size(); j++) {
						if (ts.judgeNode_1(ssdw,contentlist.get(j))) {
							index++;
							b = true;
							break;
						}
					}
				}else{
					b=true;
				}
				if (b == false) {
					System.out.println("���ϵ�λ��" + ssdw + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "ssdwspecial", filename);
				}
			}
			if (index == temp) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;
	}
	/**
	 * ����ԭ�����ϵ�λ
	 */
	@Override
	public TestModel testYsssdw(TestModel testModel,
								List<WssscyrModel> wssscyrModellist, String inputpath,
								String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "//"
				+ filename, node[1]);
		int index = 0;
		int temp = 0;
		if (wssscyrModellist != null) {
			for (int i = 0; i < wssscyrModellist.size(); i++) {
				WssscyrModel wssscyrModel = wssscyrModellist.get(i);
				String ysssdw = wssscyrModel.getYsssdw();
				boolean b = false;
				if(ysssdw!=null){
					temp++;
					for (int j = 0; j < contentlist.size(); j++) {

						if (ts.judgeNode_1(ysssdw,contentlist.get(j))) {
							index++;
							b = true;
							break;
						}

					}
				}else{
					b=true;
				}
				if (b == false) {
					System.out.println("ԭ�����ϵ�λ��" + ysssdw + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "ysssdwspecial", filename);
				}
			}
			if (index == temp) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSssf(TestModel testModel,
							  List<WssscyrModel> wssscyrModellist, String inputpath,
							  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "//"
				+ filename, node[1]);
		int index = 0;
		if (wssscyrModellist != null) {
			for (int i = 0; i < wssscyrModellist.size(); i++) {
				WssscyrModel wssscyrModel = wssscyrModellist.get(i);
				String sssf = wssscyrModel.getSssf();
				boolean b = false;
				for (int j = 0; j < contentlist.size(); j++) {
					if(contentlist.get(j)!=null&&sssf!=null){
						if (ts.judgeNode_2(contentlist.get(j),sssf)) {
							index++;
							b = true;
							break;
						}
					}

				}
				if (b == false) {
					System.out.println("������ݣ�" + sssf + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "sssfspecial", filename);
				}
			}
			if (index == wssscyrModellist.size()) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testZj(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		List<String> contents1 = xr.getXMLNodelist(inputpath+"//"+filename, node[1]);
		List<String> contents2 = xr.getXMLNodelist(inputpath+"//"+filename, node[2]);
		HashMap<String,WssscyrModel > map1 = new HashMap<String,WssscyrModel>();
		HashMap<String,WssscyrModel > map2 = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getZjlx())){
					map1.put(sscyr.getZjlx(), sscyr);
				}
				if(!StringUtil.isBlank(sscyr.getZjhm())){
					map2.put(sscyr.getZjhm(), sscyr);
				}
			}
		}
		boolean flag = true;
		if(!contents1.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contents1){
				if(!map1.containsKey(content)){
					flag=false;
					System.out.println("����֤�����ͣ�"+content);
					break;
				}
			}
		}
		if(!contents1.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contents2){
				if(!map2.containsKey(content)){
					flag=false;
					System.out.println("����֤�����룺"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"zjspecial", filename);

		}
		return testModel;
	}

	@Override
	public TestModel testCsrq(TestModel testModel,
							  List<WssscyrModel> wssscyrModellist, String inputpath,
							  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "//"
				+ filename, node[1]);
		int index = 0;
		int temp = 0;
		if (wssscyrModellist != null) {
			for (int i = 0; i < wssscyrModellist.size(); i++) {
				WssscyrModel wssscyrModel = wssscyrModellist.get(i);
				String csrq = wssscyrModel.getCsrq();
				boolean b = false;
				if (csrq != null) {
					temp++;
					for (int j = 0; j < contentlist.size(); j++) {
						if (ts.judgeNode_1(csrq, contentlist.get(j))) {
							index++;
							b = true;
							break;
						}
					}
				} else {
					b = true;
				}
				if (b == false) {
					System.out.println("�������ڣ�" + csrq + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "csrqspecial", filename);
				}
			}
			if (index == temp) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testDsrdz(TestModel testModel,
							   List<WssscyrModel> wssscyrModellist, String inputpath,
							   String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "//"
				+ filename, node[1]);
		int index = 0;
		int temp = 0;
		if (wssscyrModellist != null) {
			for (int i = 0; i < wssscyrModellist.size(); i++) {
				WssscyrModel wssscyrModel = wssscyrModellist.get(i);
				String dsrdz = wssscyrModel.getDsrdz();
				boolean b = false;
				if (dsrdz != null) {
					temp++;
					for (int j = 0; j < contentlist.size(); j++) {
						if (ts.judgeNode_2(contentlist.get(j),dsrdz)) {
							index++;
							b = true;
							break;
						}
					}
				} else {
					b = true;
				}
				if (b == false) {
					System.out.println("�����˵�ַ��" + dsrdz + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "dsrdzspecial", filename);
				}
			}
			if (index == temp) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;
	}
	/**
	 * ���Ե�����ְ��
	 */
	@Override
	public TestModel testDsrzw(TestModel testModel,
							   List<WssscyrModel> wssscyrModellist, String inputpath,
							   String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		int index=0;
		int temp=0;
		if(wssscyrModellist!=null){
			for(int i=0;i<wssscyrModellist.size();i++ ){
				WssscyrModel wssscyrModel=wssscyrModellist.get(i);
				String dsrzw=wssscyrModel.getDsrzw();
				boolean b = false;
				if(dsrzw!=null){
					temp++;
					for(int j=0;j<contentlist.size();j++ ){
						if(dsrzw.equals("ũ��")){
							index++;
							b=true;
							break;
						}else if(ts.judgeNode_1(dsrzw, contentlist.get(j))){
							index++;
							b=true;
							break;
						}

					}
				}else{
					b=true;
				}
				if(b==false){
					System.out.println("������ְλ��"+dsrzw+"δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath+"dsrzwspecial", filename);
				}
			}
			if(index==temp){
				testModel.setCoNum(testModel.getCoNum()+1);
			}
		}
		return testModel;

	}
	/**
	 * �����Ա�
	 */
	@Override
	public TestModel testXB(TestModel testModel,
							List<WssscyrModel> wssscyrModellist, String inputpath,
							String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		int index=0;
		int temp=0;
		if(wssscyrModellist!=null){
			for(int i=0;i<wssscyrModellist.size();i++ ){
				WssscyrModel wssscyrModel=wssscyrModellist.get(i);
				String xb=wssscyrModel.getXb();
				boolean b = false;
				if(xb!=null){
					temp++;
					for(int j=0;j<contentlist.size();j++ ){
						if(ts.judgeNode_1(xb, contentlist.get(j))){
							index++;
							b=true;
							break;
						}

					}
				}else{
					b=true;
				}
				if(b==false){
					System.out.println("�Ա�"+xb+"δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath+"xbspecial", filename);
				}
			}
			if(index==temp){
				testModel.setCoNum(testModel.getCoNum()+1);
			}
		}
		return testModel;
	}
	/**
	 * ��������
	 */
	@Override
	public TestModel testMZ(TestModel testModel,
							List<WssscyrModel> wssscyrModellist, String inputpath,
							String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		int index=0;
		int temp=0;
		if(wssscyrModellist!=null){
			for(int i=0;i<wssscyrModellist.size();i++ ){
				WssscyrModel wssscyrModel=wssscyrModellist.get(i);
				String mz=wssscyrModel.getMz();
				boolean b = false;
				if(mz!=null){
					temp++;
					for(int j=0;j<contentlist.size();j++ ){
						if(ts.judgeNode_1(mz, contentlist.get(j))){
							index++;
							b=true;
							break;
						}

					}
				}else{
					b=true;
				}
				if(b==false){
					System.out.println("���壺"+mz+"δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath+"mzspecial", filename);
				}
			}
			if(index==temp){
				testModel.setCoNum(testModel.getCoNum()+1);
			}
		}
		return testModel;
	}

	/**
	 * ���Թ���
	 * @param testModel
	 * @param wssscyrModelList
	 * @param inputpath
	 * @param filename
	 * @param specialpath
	 * @param node
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Override
	public TestModel testGJ(TestModel testModel, List<WssscyrModel> wssscyrModelList, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"//"+filename, node[1]);
		HashMap<String,WssscyrModel > map = new HashMap<String,WssscyrModel>();
		if(wssscyrModelList!=null){
			for(WssscyrModel sscyr:wssscyrModelList){
				if(!StringUtil.isBlank(sscyr.getGj())){
					map.put(sscyr.getGj(), sscyr);
				}
			}
		}
		boolean flag = true;
		if(!contents.get(0).equals("��")&&wssscyrModelList!=null){
			for(String content:contents){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("���"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//");
			//	FileUtil.fileCopy(inputpath, filename, specialpath+"gjspecial", filename);

		}
		return testModel;
	}

	/**
	 * ���Ե�λ����
	 * @param testModel
	 * @param wssscyrModels
	 * @param inputpath
	 * @param filename
	 * @param specialpath
	 * @param node
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Override
	public TestModel testDwxz(TestModel testModel,
							  List<WssscyrModel> wssscyrModels, String inputpath,
							  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"//"+filename, node[1]);
		HashMap<String,WssscyrModel > map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getDwxz())){
					map.put(sscyr.getDwxz(), sscyr);
				}
			}
		}
		boolean flag = true;
		if(!contents.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contents){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("���"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//////////////////");
			FileUtil.fileCopy(inputpath, filename, specialpath+"dwxzspecial", filename);
		}
		return testModel;
	}
	/**
	 * ���Ե������Ƿ��ٻ�
	 */
	@Override
	public TestModel testDsrsfzh(TestModel testModel,
								 List<WssscyrModel> wssscyrModels, String inputpath,
								 String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel > map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getDsrsfzh())){
					map.put(sscyr.getDsrsfzh(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//////////////////");
			FileUtil.fileCopy(inputpath, filename, specialpath+"dsrsfzhspecial", filename);
		}
		return testModel;
	}
	/**
	 * ����������ҵ
	 */
	@Override
	public TestModel testTshy(TestModel testModel,
							  List<WssscyrModel> wssscyrModels, String inputpath,
							  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel > map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getTshy())){
					map.put(sscyr.getTshy(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					FileUtil.fileCopy(inputpath, filename, specialpath+"tshyspecial", filename);
					break;

				}
			}
		}


		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"tshyspecial", filename);
		}
		return testModel;

	}
	/**
	 * �����������ɹ�ϵ����
	 */
	@Override
	public TestModel testXzflgxzt(TestModel testModel,
								  List<WssscyrModel> wssscyrModels, String inputpath,
								  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getXzfagxzt())){
					map.put(sscyr.getXzfagxzt(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"xzflgxztspecial", filename);
		}
		return testModel;
	}
	/**
	 * ���Ա�������
	 */
	@Override
	public TestModel testBglx(TestModel testModel,
							  List<WssscyrModel> wssscyrModels, String inputpath,
							  String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getBglx())){
					map.put(sscyr.getBglx(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"bglxspecial", filename);
		}


		return testModel;
	}
	/**
	 * ������֯��������
	 */
	@Override
	public TestModel testZzjgdm(TestModel testModel,
								List<WssscyrModel> wssscyrModels, String inputpath,
								String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getZzjgdm())){
					map.put(sscyr.getZzjgdm(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					//System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"zjjgdmspecial", filename);
		}
		return testModel;
	}

	/**
	 * ������������Χ
	 * @param testModel
	 * @param wssscyrModels
	 * @param inputpath
	 * @param filename
	 * @param specialpath
	 * @param node
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Override
	public TestModel testXzglfw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		ArrayList<String> contentlistfather=xr.getXMLNodelist(inputpath+"//"+filename,node[2]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getXzglfw())){
					map.put(sscyr.getXzglfw(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(contentlistfather.get(0).equals("��")){
			if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
				for(String content:contentlist){
					if(!map.containsKey(content)){
						flag=false;
						System.out.println("����:"+content);
						break;
					}
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"xzflfwspecial", filename);
		}
		return testModel;
	}

	/**
	 * �����Ļ��̶�
	 * @param testModel
	 * @param wssscyrModels
	 * @param inputpath
	 * @param filename
	 * @param specialpath
	 * @param node
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Override
	public TestModel testWhcd(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getDsrwhcd())){
					map.put((sscyr.getDsrwhcd()).substring(0,2)+"��ҵ", sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"whcdspecial", filename);
		}
		return testModel;
	}

	/**
	 * ����ѧλ
	 */
	@Override
	public TestModel testXw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getDsrxw())){
					map.put(sscyr.getDsrxw(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"xwspecial", filename);
		}
		return testModel;
	}
	/**
	 * ����������ò
	 */
	@Override
	public TestModel testZzmm(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getZzmm())){
					map.put(sscyr.getZzmm(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"zzmmspecial", filename);
		}
		return testModel;
	}
	/**
	 * ��Ȼ�����
	 */
	@Override
	public TestModel testZrrsf(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getZrrsf())){
					map.put(sscyr.getZrrsf(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"zrrsfspecial", filename);
		}
		return testModel;
	}
	/**
	 * ���Ի�����
	 */
	@Override
	public TestModel testHjd(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getHjd())){
					map.put(sscyr.getHjd(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"hjdspecial", filename);
		}
		return testModel;
	}
	/**
	 * �����Ƿ񱻺���
	 */
	@Override
	public TestModel testBhr(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getIsBhr())){
					map.put(sscyr.getIsBhr(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"bhrspecial", filename);
		}
		return testModel;
	}

	/**
	 * ���Ը�����������ԭ��������
	 */
	@Override
	public TestModel testMsssygrlx(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getMsssygrlx())){
					map.put(sscyr.getMsssygrlx(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"msssygrlxspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testXszrnl(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getXszrablity())){
					map.put(sscyr.getXszrablity(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"xszrnlspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testHxkyqfz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getHxkyqfz())){
					map.put(sscyr.getHxkyqfz(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"hxkyqspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testJskyqfz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getJskyqfz())){
					map.put(sscyr.getJskyqfz(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"xszrnlspecial", filename);
		}
		return testModel;
	}

	/**
	 * �����Ѻ����
	 * @param testModel
	 * @param wssscyrModels
	 * @param inputpath
	 * @param filename
	 * @param specialpath
	 * @param node
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Override
	public TestModel testJycs(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(!StringUtil.isBlank(sscyr.getXjycs())){
					map.put(sscyr.getXjycs(), sscyr);
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			FileUtil.fileCopy(inputpath, filename, specialpath+"jycsspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQzcszl(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(sscyr.getQzcsList()!=null){
					for (QzcsModel qzcsModel:sscyr.getQzcsList()){
						if (qzcsModel.getQzcsCategory()!=null)
							map.put(qzcsModel.getQzcsCategory(), sscyr);
					}
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"qzcszlspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQzcssj(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(sscyr.getQzcsList()!=null){
					for (QzcsModel qzcsModel:sscyr.getQzcsList()){
						if (qzcsModel.getQzcsTime()!=null)
							map.put(qzcsModel.getQzcsTime(), sscyr);
					}
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"qzcssjspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQzcsdw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		HashMap<String,WssscyrModel> map = new HashMap<String,WssscyrModel>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(sscyr.getQzcsList()!=null){
					for (QzcsModel qzcsModel:sscyr.getQzcsList()){
						if (qzcsModel.getQzcsDw()!=null)
							map.put(qzcsModel.getQzcsDw(), sscyr);
					}
				}
			}
		}
		boolean flag=true;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				if(!map.containsKey(content)){
					flag=false;
					System.out.println("����:"+content);
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"qzcsdwspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQzcsyy(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath, String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		XMLReader xr=new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath+ "//"
				+ filename, node[1]);
		ArrayList<String> list = new ArrayList<String>();
		if(wssscyrModels!=null){
			for(WssscyrModel sscyr:wssscyrModels){
				if(sscyr.getQzcsList()!=null){
					for (QzcsModel qzcsModel:sscyr.getQzcsList()){
						if (qzcsModel.getQscsReason()!=null)
							for (String qzcsyy:qzcsModel.getQscsReason()){
								list.add(qzcsyy);
							}

					}
				}
			}
		}
		boolean flag=false;
		if(!contentlist.get(0).equals("��")&&wssscyrModels!=null){
			for(String content:contentlist){
				for (String qzcsyy:list) {
					if (StringUtil.contains(qzcsyy,content)){
						flag=true;
						break;
					}
				}
				if (!flag){
					System.out.println("����:"+content);
				}
			}
		}else {
			flag=true;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);

		}else{
			//System.out.println("//");
			//FileUtil.fileCopy(inputpath, filename, specialpath+"qzcsyyspecial", filename);
		}
		return testModel;
	}
}
