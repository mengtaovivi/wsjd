package nju.software.wsjx.model.wsSegmentationModel;

import java.io.IOException;
import java.util.List;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import org.jdom.JDOMException;

import nju.software.wsjx.util.XmlUtil;


/**
 * �������������model
 * @author lr12
 *
 */
public class WsModel {

	private List<WscpfxgcFtModel> PreWscpfxgcFtModels;

	public List<WscpfxgcFtModel> getPreWscpfxgcFtModels() {
		return PreWscpfxgcFtModels;
	}

	public void setPreWscpfxgcFtModels(List<WscpfxgcFtModel> preWscpfxgcFtModels) {
		PreWscpfxgcFtModels = preWscpfxgcFtModels;
	}

	private DocType docType;

	public DocType getDocType() {
		return docType;
	}

	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	private WswsModel wswsModel;//��������model
	
	private List<WssscyrModel> wssscyrModels;//�������ϲ�����models
	
	private WsajjbqkModel wsajjbqkModel;//���鰸���������ģ��
	
	private WsssjlModel wsssjlModel;//�������ϼ�¼ģ��
	
	private WscpfxgcModel wscpfxgcModel;//������з�������ģ��
	
	private WscpjgModel wscpjgModel;//������н��ģ��
	
	private WswwModel wswwModel;//������βģ��
	
	private String wsqw;//ȫ��
	private String wswsSegment;//���׶���
	private List<String> wssscyrSegment;//���ϲ����˶���
	private String wsajjbqSegment;//���������������
	private String wsssjlSegment;//���ϼ�¼����
	private String wscpfxgcSegment;//���з������̶���
	private String wscpjgSegment;//���н������
	private String wswwSegment;//��β����
	private String wsfl;//��¼
	
	public String getWsqw() {
		return wsqw;
	}

	public void setWsqw(String wsqw) {
		this.wsqw = wsqw;
	}

	public String getWsfl() {
		return wsfl;
	}

	public void setWsfl(String wsfl) {
		this.wsfl = wsfl;
	}

	public WsModel() {
		super();
	}

	public WsModel(WswsModel wswsModel, List<WssscyrModel> wssscyrModels,
			WsajjbqkModel wsajjbqkModel, WsssjlModel wsssjlModel,
			WscpfxgcModel wscpfxgcModel, WscpjgModel wscpjgModel,
			WswwModel wswwModel) {
		super();
		this.wswsModel = wswsModel;
		this.wssscyrModels = wssscyrModels;
		this.wsajjbqkModel = wsajjbqkModel;
		this.wsssjlModel = wsssjlModel;
		this.wscpfxgcModel = wscpfxgcModel;
		this.wscpjgModel = wscpjgModel;
		this.wswwModel = wswwModel;
	}

	public WswsModel getWswsModel() {
		return wswsModel;
	}

	public void setWswsModel(WswsModel wswsModel) {
		this.wswsModel = wswsModel;
	}

	public List<WssscyrModel> getWssscyrModels() {
		return wssscyrModels;
	}

	public void setWssscyrModels(List<WssscyrModel> wssscyrModels) {
		this.wssscyrModels = wssscyrModels;
	}

	public WsajjbqkModel getWsajjbqkModel() {
		return wsajjbqkModel;
	}

	public void setWsajjbqkModel(WsajjbqkModel wsajjbqkModel) {
		this.wsajjbqkModel = wsajjbqkModel;
	}

	public WsssjlModel getWsssjlModel() {
		return wsssjlModel;
	}

	public void setWsssjlModel(WsssjlModel wsssjlModel) {
		this.wsssjlModel = wsssjlModel;
	}

	public WscpfxgcModel getWscpfxgcModel() {
		return wscpfxgcModel;
	}

	public void setWscpfxgcModel(WscpfxgcModel wscpfxgcModel) {
		this.wscpfxgcModel = wscpfxgcModel;
	}

	public WscpjgModel getWscpjgModel() {
		return wscpjgModel;
	}

	public void setWscpjgModel(WscpjgModel wscpjgModel) {
		this.wscpjgModel = wscpjgModel;
	}

	public WswwModel getWswwModel() {
		return wswwModel;
	}

	public void setWswwModel(WswwModel wswwModel) {
		this.wswwModel = wswwModel;
	}

	public String getWswsSegment() {
		return wswsSegment;
	}

	public void setWswsSegment(String wswsSegment) {
		this.wswsSegment = wswsSegment;
	}

	public List<String> getWssscyrSegment() {
		return wssscyrSegment;
	}

	public void setWssscyrSegment(List<String> wssscyrSegment) {
		this.wssscyrSegment = wssscyrSegment;
	}

	public String getWsajjbqSegment() {
		return wsajjbqSegment;
	}

	public void setWsajjbqSegment(String wsajjbqSegment) {
		this.wsajjbqSegment = wsajjbqSegment;
	}

	public String getWsssjlSegment() {
		return wsssjlSegment;
	}

	public void setWsssjlSegment(String wsssjlSegment) {
		this.wsssjlSegment = wsssjlSegment;
	}

	public String getWscpfxgcSegment() {
		return wscpfxgcSegment;
	}

	public void setWscpfxgcSegment(String wscpfxgcSegment) {
		this.wscpfxgcSegment = wscpfxgcSegment;
	}

	public String getWscpjgSegment() {
		return wscpjgSegment;
	}

	public void setWscpjgSegment(String wscpjgSegment) {
		this.wscpjgSegment = wscpjgSegment;
	}

	public String getWswwSegment() {
		return wswwSegment;
	}

	public void setWswwSegment(String wswwSegment) {
		this.wswwSegment = wswwSegment;
	}
	/**
	 * ��wsModelת��ΪXml
	 * @param outputPath
	 * @param outputName
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void transformToXml(String outputPath,String outputName) throws IOException, JDOMException{
		XmlUtil.BuildXMLDoc(this, null, outputPath, outputName);
	}
	
	
}
