package nju.software.wsjx.service;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;
import org.xml.sax.SAXException;
public interface TestXspjjgService {
	/**
	 * ���Կ�������
	 */
	public TestModel testKssz(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ������������
	 */
	public TestModel testSsqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���������ύ����
	 */
	public TestModel testTjcl(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���������Ͻ����
	 */
	public TestModel testTcgxyy(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸��ʽ
	 */
	public TestModel testJafs(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����һ�����²����о����
	 */
	public TestModel testYsxsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���������о�����������ϲ�����
	 */
	public TestModel testXspjjgfzsscyr(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                       String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-����
	 */
	public TestModel testBsdzpfzm(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-����
	 */
	public TestModel testBsdzpfzmdm(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-��������
	 */
	public TestModel testBsxzpfzm(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-�������
	 */
	public TestModel testZxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-ִ���з�-�������
	 */
	public TestModel testZxpfZxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-��������
	 */
	public TestModel testZxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-ִ���з�-��������
	 */
	public TestModel testZxpfZxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-�������
	 */
	public TestModel testHxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-��������
	 */
	public TestModel testHxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-ִ���з�-�������
	 */
	public TestModel testZxHxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-ִ���з�-��������
	 */
	public TestModel testZxHxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����з�-�о��������
	 */
	public TestModel testPjjglx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-ִ���з�-�о��������
	 */
	public TestModel testZxPjjglx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-���ڿ�ʼ����
	 */
	public TestModel testXqksrq(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-���ڽ�������
	 */
	public TestModel testXqjsrq(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�����۵ְ취
	 */
	public TestModel testXqzdbf(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-���ﲢ��
	 */
	public TestModel testSzbf(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-����������ͷ�
	 */
	public TestModel testMzhwzsf(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���-����-�ϲ�����
	 */
	public TestModel testHblx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���ԶԸ��������⳥�Ĵ���
	 */
	public TestModel testDfdmspcdcl(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���ԶԸ������²��н��
	 */
	public TestModel testFdmsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է���-����-���������
	 */
	public TestModel testFjxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է���-����-����������
	 */
	public TestModel testFjxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է���-����-�����̽��
	 */
	public TestModel testFjxje(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է���-����-�����̱���
	 */
	public TestModel testFjxbz(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է���-����ԭ��
	 */
	public TestModel testGpyy(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է���-���ŷ���
	 */
	public TestModel testJtfz(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
