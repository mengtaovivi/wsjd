package nju.software.wsjx.service;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import org.xml.sax.SAXException;
public interface TestfdService {
	/**
	 * �������׶�
	 */
	public TestModel testWs(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������ϲ����˶�
	 */
	public TestModel testSscyr(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������ϼ�¼��
	 */
	public TestModel testSsjl(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���԰������������
	 */
	public TestModel testAjjbqk(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Բ��з������̶�
	 */
	public TestModel testCpfxgc(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Բ��н����
	 */
	public TestModel testCpjg(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ������β��
	 */
	public TestModel testWw(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ը�¼��
	 */
	public TestModel testFl(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
