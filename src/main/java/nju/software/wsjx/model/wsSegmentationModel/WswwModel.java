package nju.software.wsjx.model.wsSegmentationModel;

import java.util.HashMap;
import java.util.List;



/**
 * ������βmodel
 * @author lr12
 *
 */
public class WswwModel {
	private HashMap<String, String> spzzcyMap;//������֯��Ա
	private String wsrq=null;//��������
	private String year;//�᰸���
	private String month;//�᰸�·�
	private String day;//�᰸��
	private String yearAndMonth;//�᰸���£�
	
	
	
	public HashMap<String, String> getSpzzcyMap() {
		return spzzcyMap;
	}
	public void setSpzzcyMap(HashMap<String, String> spzzcyMap) {
		this.spzzcyMap = spzzcyMap;
	}
	public String getWsrq() {
		return wsrq;
	}
	public void setWsrq(String wsrq) {
		this.wsrq = wsrq;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	@Override
	public String toString() {
		return "WswwModel [spzzcyMap=" + spzzcyMap + ", wsrq=" + wsrq
				+ ", year=" + year + ", month=" + month + ", day=" + day
				+ ", yearAndMonth=" + yearAndMonth + "]";
	}
	
	

}

