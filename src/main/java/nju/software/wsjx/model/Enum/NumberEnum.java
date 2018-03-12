package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum NumberEnum  {
	A1("1"),
	A2("2"),
	A3("3"),
    A4("4"),
    A5("5"),
    A6("6"),
    A7("7"),
    A8("8"),
    A9("9"),
    A0("0"),
    C1("һ"),
    C2("��"),
    C3("��"),
    C4("��"),
    C5("��"),
    C6("��"),
    C7("��"),
    C8("��"),
    C9("��"),
    C10("ʮ");

    
	NumberEnum(){
		
	}
	private NumberEnum(String content){
		this.content = content;
	}
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> getNumberList() {
		List<String> numberList = new ArrayList<String>();
		for (NumberEnum numberEnum : NumberEnum.values()) {
			numberList.add(numberEnum.getContent());
		}
		return numberList;
	}
	
	public static boolean HasNumber(String content){
		List<String> list=getNumberList();
		for(int i=0;i<list.size();i++){
	    
			if(content.indexOf(list.get(i))<4&&content.indexOf(list.get(i))>-1){
				return true;
			}
		}
		return false;
	}

}

