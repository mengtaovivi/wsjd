package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;





import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.service.TestPjjgService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.service.model.WsfdModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XMLReader;

public class TestPjjgServiceImpl implements TestPjjgService{
	private XMLReader xr;
	private TestMethodServiceImpl ts;

	/**
	 * �����о����
	 */
		@Override
		public TestModel testPjjg(TestModel testModel, WsfdModel wsfdModel,
				String inputpath, String filename, String specialpath, String[] node)
				throws XPathExpressionException, ParserConfigurationException,
				SAXException, IOException {
			// TODO Auto-generated method stub
			XMLReader xr=new XMLReader();
			TestMethodServiceImpl ts=new TestMethodServiceImpl();
			String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
	        if(wsfdModel!=null){
	        	String cpjg = wsfdModel.getCpjg();
	        	if(StringUtil.equalsIgnoreWhitespace(content, cpjg)){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else{
	        		System.out.println("���ԣ�"+cpjg);	
	        		System.out.println("���"+content);
//	    			FileUtil.fileCopy(inputpath, filename, specialpath+"Monthpecial", filename);
	        	}
	        }
			return testModel;
		}

	@Override
	public TestModel testJafs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		if(wscpjgModel!=null){
//        	String jafs = wscpjgModel.getMsesjafs();//1.���¶���᰸��ʽ
			String jafs = wscpjgModel.getJafs();//2.����һ��᰸��ʽ
//			String jafs = wscpjgModel.getXzysjafs();//3.����һ��᰸��ʽ
//        	if(judegMsesjafs(jafs, content)){
//			if(judegMsysjafs(jafs, content)){//2.����һ��᰸��ʽ������һ��᰸��ʽ
			if(judegMsesjafs(jafs, content)){//2.���¶���᰸��ʽ����������᰸��ʽ
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("���ԣ�"+jafs);	
        		System.out.println("���"+content);
    			FileUtil.fileCopy(inputpath, filename, specialpath+"esjafsSpecial", filename);
        	}
        }
		return testModel;
	}
	
	public  static boolean judegMsesjafs(String jafs,String content){
		boolean eq = false;
		if(StringUtil.equals(jafs, content)){
			eq = true;
		}else if(StringUtil.equals("����", content) && (StringUtil.equals("׼�賷�����߲�����һ���о�", jafs)||StringUtil.equals("׼�賷������", jafs)||StringUtil.equals("���������ߴ���", jafs))){
			eq = true;
		}else if(StringUtil.equals("�����ߴ���", content)&&StringUtil.equals("���������ߴ���", jafs)){
			eq = true;
		}else if(StringUtil.equals("�������ԭ�ö�", content)&&(StringUtil.equals("����ԭ�ö���ָ������", jafs)||StringUtil.equals("����ԭ�ö���ָ������", jafs))){
			eq = true;
		}else if(StringUtil.equals("��������", content)&&StringUtil.equals("����ԭ�в���������", jafs)){
			eq = true;
		}else if(StringUtil.equals("ά��ԭ�ö�", content)&&StringUtil.equals("ά��", jafs)){
			eq = true;
		}
		return eq;
	}
	
	public  static boolean judegMsysjafs(String jafs,String content){
		boolean eq = false;
		if(StringUtil.equals(jafs, content)){
			eq = true;
		}else if(StringUtil.equals("����", content) && StringUtil.equals("׼�賷��", jafs)){
			eq = true;
		}else if(StringUtil.equals("��������", content) && StringUtil.equals("��������", jafs)){
			eq = true;
		}
		return eq;
	}

	@Override
	public TestModel testSffhcs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
			XMLReader xr = new XMLReader();
			TestMethodServiceImpl ts=new TestMethodServiceImpl();
			String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
			if(wscpjgModel!=null){
	        	String result = wscpjgModel.getSffhcs();
	        	if(content!=null && result!=null && ts.judgeNode_1(result, content)){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else if(content==null && result==null ){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else{
	        		System.out.println("���ԣ�"+result);	
	        		System.out.println("���"+content);
	    			FileUtil.fileCopy(inputpath, filename, specialpath+"sffhcsSpecial", filename);
	        	}
	        }
			return testModel;
	}

	@Override
	public TestModel testMsesffcsyy(TestModel testModel,
			WscpjgModel wscpjgModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
			XMLReader xr = new XMLReader();
			TestMethodServiceImpl ts=new TestMethodServiceImpl();
			String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
			if(wscpjgModel!=null){
	        	String fhcsyy = wscpjgModel.getFhcsyy();
	        	if((content!=null && fhcsyy!=null&&ts.judgeNode_2(fhcsyy, content))||
	        			(StringUtil.equals("��ʵ����֤�ݲ���", content)&&StringUtil.equals("ԭ�о��϶�������ʵ����", fhcsyy))){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else if(((content!=null && content.equals("��"))|| content==null) && fhcsyy==null){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else{
	        		System.out.println("yy���ԣ�"+fhcsyy);	
	        		System.out.println("yy���"+content);
	    			FileUtil.fileCopy(inputpath, filename, specialpath+"fhcsyySpecial", filename);
	        	}
	        }
			return testModel;
	}

	@Override
	public TestModel testAjslf(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		ArrayList<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		if(wscpjgModel!=null){
        	String ajslf = wscpjgModel.getAjslf();
        	boolean flag = false;
//        	�����������
        	if(contents.size()!=0){
        		if(contents.size()==1 && StringUtil.equalsIgnoreWhitespace("��", contents.get(0))){
        			testModel.setCoNum(testModel.getCoNum()+1);
        			flag=true;
        		}else{
        			String contentAll = "";
	        		for(String content : contents){
	        			contentAll = contentAll+content;
	            	}
	        		if(StringUtil.contains(contentAll, ajslf)){
            			testModel.setCoNum(testModel.getCoNum()+1);
            			flag=true;
            		}
        		}
        	}
        	if(flag==false){
        		System.out.println("���� "+ajslf);
        		if(contents.size()>0){
        			System.out.println("���"+contents.get(contents.size()-1));
        		}
        		
    			FileUtil.fileCopy(inputpath, filename, specialpath+"ajslfSpecial", filename);
        	}
        }
		return testModel;
	}

	@Override
	public TestModel testJabdje(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		if(wscpjgModel!=null){
			if(judgeJe(content, wscpjgModel.getJabdze())){
				testModel.setCoNum(testModel.getCoNum()+1);
			}
			else{
				System.out.println("����"+wscpjgModel.getJabdze());
				System.out.println("����"+content);
				FileUtil.fileCopy(inputpath, filename, specialpath+"jabdSpecial", filename);
			}
		}
		return null;
	}
	
	public static boolean  judgeJe(String content,String test){
		if(StringUtil.equals(content,test)){
			return true;
		}
		if(content.equals("��")&&test==null){
			return true;
		}
		if(content.indexOf("Ԫ")>0&&StringUtil.indexOf(test, "Ԫ")>0){
			try{
				Double a = Double.parseDouble(content.substring(0, content.length()-1));
				Double b = Double.parseDouble(test.substring(0, test.length()-1));
				return a.doubleValue()==b.doubleValue();
			}catch(Exception e){
				return false;
			}
		}
		return false;
	}

	@Override
	public TestModel testQlr(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		HashMap<String, String> qlrMap = new HashMap<String, String>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel pjjgnrModel:pjjgnrModels){
				if(pjjgnrModel.getQlr()!=null){
					for(Map.Entry<String, String> entry:pjjgnrModel.getQlr().entrySet()){
						qlrMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		boolean flag=true;
		if(contents.size()>0&&!contents.get(0).equals("��")){
			for(String content:contents){
				if(!qlrMap.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"qlrSpecial", filename);
			System.out.println("����Ȩ����"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testYwr(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		HashMap<String, String> qlrMap = new HashMap<String, String>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel pjjgnrModel:pjjgnrModels){
				if(pjjgnrModel.getYwr()!=null){
					for(Map.Entry<String, String> entry:pjjgnrModel.getYwr().entrySet()){
						qlrMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		boolean flag=true;
		if(contents.size()>0&&!contents.get(0).equals("��")){
			for(String content:contents){
				if(!qlrMap.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"qlrSpecial", filename);
			System.out.println("���������ˣ�"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testPjzxqx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		HashMap<String, PjjgnrModel> map = new HashMap<String, PjjgnrModel>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel model:pjjgnrModels){
				if(!StringUtil.isBlank(model.getPjzxqx())){
					map.put(model.getPjzxqx(), model);
				}
			}
		}
		boolean flag=true;
		if(contents.size()>0 && !contents.get(0).equals("��")){
			for(String content:contents){
				if(!map.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"qlrSpecial", filename);
			System.out.println("..................");
		}
		return testModel;
	}

	@Override
	public TestModel testSsxx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = wscpjgModel.getKssz();//��������
//		String ssfy = wscpjgModel.getSsqx();//��������
//		String ssfy = wscpjgModel.getSstjcl();//�ύ���߲���
		if(StringUtil.equals(content, ssfy) || (StringUtil.equals("��", content)&&ssfy==null)||(content.equals("��")&&!StringUtil.isBlank(ssfy))){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+ssfy);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testCsrjh(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<String> csrjh = wscpjgModel.getCsrjh();
		boolean flag = true;
		if(csrjh==null && !contents.get(0).equals("��")){
			flag = false;
		}else if(csrjh!=null&&contents.size()>0 && !contents.get(0).equals("��")){
			for(String content:contents){
				if(!csrjh.contains(content)){
					flag=false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"csrjhSpecial", filename);
			System.out.println("���ԣ�"+csrjh);
			System.out.println("���"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testCslx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String cslx = wscpjgModel.getCslx();
		if(StringUtil.equals(content, cslx)||(StringUtil.equals("��", content)&&cslx==null)){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("���ԣ�"+cslx);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testCdr(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
		boolean flag=true;
		List<String> cdStringList = new ArrayList<String>();
		if(ssfModel!=null && ssfModel.getSsfcdModels()!=null && !contents.get(0).equals("��")){
			for(WscpjgssfcdModel cdmodel:ssfModel.getSsfcdModels()){
//				cdStringList.add(cdmodel.getCdr());//�е���
				cdStringList.add(cdmodel.getCdje());//�е����
			}
			for(String content:contents){
//				if(!cdStringList.contains(content)){
				if(!containsStr(content, cdStringList)){
					flag=false;
					break;
				}
			}
		}else if (!contents.get(0).equals("��")&&
				(ssfModel==null || (ssfModel!=null&&ssfModel.getSsfcdModels()==null) )){
			flag=false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("���ԣ�"+cdStringList);
			System.out.println("���"+contents);
		}
		return testModel;
	}
	
	/**
	 * content �Ƿ������ tests��
	 * @param content
	 * @param tests
	 * @return
	 */
	public static boolean containsStr(String content,List<String> tests){
		for(int i=0;i<tests.size();i++){
			if(StringUtil.contains(tests.get(i), content)){
				return true;
			}
		}
		return false;
	}
	
	public TestModel testCdfs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
		boolean flag=true;
		List<String> cdStringList = new ArrayList<String>();
		if(ssfModel!=null && ssfModel.getSsfcdModels()!=null && !contents.get(0).equals("��")){
			for(WscpjgssfcdModel cdmodel:ssfModel.getSsfcdModels()){
				cdStringList.add(cdmodel.getCdfs());
			}
			for(String content:contents){
//				if(!cdStringList.contains(content)){
				if(!containsStr(content, cdStringList)){
					flag=false;
					break;
				}
			}
		}else if (!contents.get(0).equals("��")&&
				(ssfModel==null || (ssfModel!=null&&ssfModel.getSsfcdModels()==null) )){
			flag=false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("���ԣ�"+cdStringList);
			System.out.println("���"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testSsfje(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
		boolean flag = true;
		List<String> jeList = new ArrayList<String>();
		if(ssfModel!=null && ssfModel.getSsfjeModels()!=null && !contents.get(0).equals("��")){
			for(WsCpjgssfjeModel jeModel:ssfModel.getSsfjeModels()){
//				jeList.add(jeModel.getValue());//���Ϸѽ��
				jeList.add(jeModel.getCategory());//���Ϸѽ��
			}
			for(String content:contents){
				if(!jeList.contains(content)){
					flag = false;
					break;
				}
			}
		}else if(!contents.get(0).equals("��")
				&&((ssfModel!=null && ssfModel.getSsfjeModels()==null)||ssfModel==null)){
			flag = false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+jeList);
			System.out.println("���"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testSfzcygssqq(TestModel testModel,
			WscpjgModel wscpjgModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String sfzcygssqq = wscpjgModel.getSfzcssqq();
		if(StringUtil.equals(content, sfzcygssqq)||content.equals("��")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("���ԣ�"+sfzcygssqq);
			System.out.println("���"+content);
		}
		return testModel;
	}

	@Override
	public TestModel testPjzrcdfs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents=xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		boolean flag = true;
		if(pjjgnrModels!=null&&!contents.get(0).equals("��")){
			List<String> cdfs = new ArrayList<String>();
			for(PjjgnrModel model:pjjgnrModels){
				if(!StringUtil.isBlank(model.getPjzrcdfs())){
					cdfs.add(model.getPjzrcdfs());
				}
			}
			for(String content:contents){
				if(!cdfs.contains(content)){
					flag=false;
					break;
				}
			}
		}else if(!contents.get(0).equals("��")
				&& (pjjgnrModels==null || (pjjgnrModels!=null &&pjjgnrModels.size()==0))){
			flag = false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
//			System.out.println("���ԣ�"+sfzcygssqq);
			System.out.println("���"+contents);
		}
		return null;
	}
	
	@Override
	public TestModel testPjje(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		List<String> jeList = new ArrayList<String>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel model:pjjgnrModels){
				if(model!=null && model.getPjjeList()!=null){
					for(PjjeModel s:model.getPjjeList()){
						jeList.add(s.getValue());
					}
				}
			}
		}
		boolean flag = true;
		if(jeList.size()>0 && !contents.get(0).equals("��")){
			for(String content:contents){
				if(!contansJe(content, jeList)){
					flag=false;
					break;
				}
			}
		}else if(jeList.size()==0 &&  !contents.get(0).equals("��")){
			flag=false;
		}
		
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+jeList);
			System.out.println("���"+contents);
		}
		return testModel;
	}
	
	public static boolean contansJe(String content,List<String> tests){
		if(tests.contains(content)){
			return true;
		}
		for(String s:tests){
			if(judgeJe(content, s)){
				return true;
			}
		}
		return false;
	}

	@Override
	public TestModel testPjjelx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<String> tests = new ArrayList<String>();
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		if(pjjgnrModels!=null){
			for(PjjgnrModel pjjgnrModel:pjjgnrModels){
				List<PjjeModel> pjjeModels = pjjgnrModel.getPjjeList();
				if(pjjeModels!=null){
					for(PjjeModel pjjeModel : pjjeModels){
						List<String> pjjelxList = pjjeModel.getCategorys();
						if(pjjelxList!=null){
							tests.addAll(pjjelxList);
						}
					}
				}
			}
		}
		boolean flag = true;
		if(tests.size()>0&&!contents.get(0).equals("��")){
			for(String content:contents){
				if(!tests.contains(content)){
					flag = false;
					break;
				}
			}
		}else if(tests.size()==0 && !contents.get(0).equals("��")){
			flag = false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+tests);
			System.out.println("���"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testGxyy(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String  content  = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		if(StringUtil.equals(content, wscpjgModel.getSftcgxyy())){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("���ԣ�"+wscpjgModel.getSftcgxyy());
			System.out.println("���"+content);
		}
		
		
		
		return testModel;
	}
	
}
