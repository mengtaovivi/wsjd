package nju.software.wsjx.service;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface TestSscyrService {
	/**
	 * ���Ե����˶�
	 */
	public TestModel testSscyrqj(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������ϲ���������
	 */
	public TestModel testSscyrmc(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����������
	 */
	public TestModel testSssf(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������ϵ�λ
	 */
	public TestModel testSsdw(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ԭ�����ϵ�λ
	 */
	public TestModel testYsssdw(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Գ�������
	 */
	public TestModel testCsrq(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ե����˵�ַ
	 */
	public TestModel testDsrdz(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ե�����ְ��
	 */
	public TestModel testDsrzw(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����Ա�
	 */
	public TestModel testXB(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * ��������
	 */
	public TestModel testMZ(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Թ���
	 */
	public  TestModel testGJ(TestModel testModel, List<WssscyrModel> wssscyrModelList, String inputpath,
                             String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ե�λ����
	 */
	public TestModel testDwxz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ե������Ƿ��ٻ�
	 */
	public TestModel testDsrsfzh(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����������ҵ
	 */
	public TestModel testTshy(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����������ɹ�ϵ����
	 */
	public TestModel testXzflgxzt(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա�������
	 */
	public TestModel testBglx(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ������֯��������
	 */
	public TestModel testZzjgdm(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ������������Χ
	 */
	public TestModel testXzglfw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����֤�����ͺͺ���
	 */
	public TestModel testZj(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����Ļ��̶�
	 */
	public TestModel testWhcd(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ѧλ
	 */
	public TestModel testXw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����������ò
	 */
	public TestModel testZzmm(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ��Ȼ�����
	 */
	public TestModel testZrrsf(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ի�����
	 */
	public TestModel testHjd(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����Ƿ񱻺���
	 */
	public TestModel testBhr(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ը�����������ԭ��������
	 */
	public TestModel testMsssygrlx(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                   String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����������������
	 */
	public TestModel testXszrnl(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ի��̿����ڷ���
	 */
	public TestModel testHxkyqfz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Լ��Ϳ����ڷ���
	 */
	public TestModel testJskyqfz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����Ѻ����
	 */
	public TestModel testJycs(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǿ�ƴ�ʩ����
	 */
	public TestModel testQzcszl(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǿ�ƴ�ʩʱ��
	 */
	public TestModel testQzcssj(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǿ�ƴ�ʩִ�е�λ
	 */
	public TestModel testQzcsdw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǿ�ƴ�ʩԭ����
	 */
	public TestModel testQzcsyy(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
