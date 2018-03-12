package nju.software.wsjx.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

import org.xml.sax.SAXException;

/**
 * ������Եİ�����Ϣģ��
 * @author super
 *
 */
public interface TestModelService {
	/**
	 * ���Ծ��취Ժ�ڵ�
	 * @param wsnr
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 */
	public List judgeJBFY(WswsModel wswsmodel, String inputpath, String filename, List testModellist) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	
}
