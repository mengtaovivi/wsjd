package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;

public class WsModelWrapper {
	public WsModel wsModel;
	public List<String> errInfoList;//�쳣��ջ��Ϣ
	public String wjm;//�ļ���
	
	public WsModelWrapper(){
		super();
	}

	public WsModel getWsModel() {
		return wsModel;
	}

	public void setWsModel(WsModel wsModel) {
		this.wsModel = wsModel;
	}

	public List<String> getErrInfoList() {
		return errInfoList;
	}

	public void setErrInfoList(List<String> errInfoList) {
		this.errInfoList = errInfoList;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

}
