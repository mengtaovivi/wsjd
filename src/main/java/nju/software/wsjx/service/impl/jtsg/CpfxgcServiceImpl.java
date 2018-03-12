package nju.software.wsjx.service.impl.jtsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.jtsg.CpfxgcService;
import nju.software.wsjx.util.StringUtil;

public class CpfxgcServiceImpl implements CpfxgcService{

	@Override
	public void getGcf(List<String> cpfxgc,List<WssscyrModel> wssscyrModellist) {
		// TODO Auto-generated method stub
//		����ÿ����з�������
		int count = 0;
		List<Pattern> patternList = new ArrayList<>();
		for(WssscyrModel cyr:wssscyrModellist){
			String reg = cyr.getSscyr()+"[\u4e00-\u9fa5]*[��,�е�]+[\u4e00-\u9fa5]*����";
//			String reg = cyr.getSscyr()+"[^x00-xff]*[��,�е�]+[^x00-xff]*����";
			Pattern p = Pattern.compile(reg);
			patternList.add(p);
		}
		Matcher m ;
		for(String content:cpfxgc){
			for(Pattern p:patternList){
				m = p.matcher(content);
				if(m.find()){
					count++;
					int index = patternList.indexOf(p);
					wssscyrModellist.get(index).setJtsgzr(m.group().replaceAll(wssscyrModellist.get(index).getSscyr(), ""));
				}
			}
		}
		if(count==0){
			
		}
		System.out.println(1);
	}
	 
	public void setQlywr(List<String> cpfxgc,List<WssscyrModel> wssscyrModellist){
        String pjjg = "";
        for(String content:cpfxgc){
        	pjjg = pjjg+content;
        }
		HashMap<String,String> qlr = new HashMap<String, String>();
		HashMap<String,String> ywr = new HashMap<String, String>();
//		����������index
		int firstIndex = getMin(pjjg);//��ǰ����
		
		HashMap<String, Integer> nameIndexMap = new HashMap<String, Integer>();
		for(WssscyrModel cyr:wssscyrModellist){
			if(!StringUtil.isBlank(cyr.getSscyr())){
				String name  = cyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				nameIndexMap.put(name, nameIndex);
			}
		}
//		������������ڶ���֮��ģ�������ǰ�������flag=false
		boolean flag=true;
		if(firstIndex>-1&&firstIndex!=pjjg.length()){
			for(Map.Entry<String, Integer> entry:nameIndexMap.entrySet()){
				if(entry.getValue()>firstIndex){
					flag=false;
					break;
				}
			}
		}
		
//		��һ������֧���Ķ��ʣ�֮ǰΪ�����ˣ�֮��ΪȨ����
//		������е��������ڶ���֮ǰ������ʹ��ǰ�󷽷�
		if(!flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				if(nameIndex>-1 &&nameIndex<firstIndex ){
					//������������
					sscyr.setSfcdpczr("�е��⳥����");
					ywr.put(name, sscyr.getSssf());
				}else if(nameIndex>-1 &&nameIndex>firstIndex){
					sscyr.setSfcdpczr("���е��⳥����");
				}
			}
		}else if(flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
//			���������ڶ���֮ǰ
			int qlIndex=-1;
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				if(!StringUtil.isBlank(name)){
					String ssdwname = sscyr.getSssf()+sscyr.getSscyr(); 
//					��������˴��ڣ���ô����������ǵ�һ��Ȩ����
					if(nameIndexMap.get(name)>-1 &&(pjjg.indexOf("��"+name)>0||(pjjg.indexOf("��"+ssdwname)>0))){
						sscyr.setSfcdpczr("���е��⳥����");
						qlIndex=nameIndexMap.get(name);
						break;
					}
				}
			}
			if(qlIndex!=-1){
				for( WssscyrModel  sscyr:wssscyrModellist){
					String name = sscyr.getSscyr(); 
					if(!StringUtil.isBlank(name)){
						if(nameIndexMap.get(name)>-1 && nameIndexMap.get(name)<qlIndex){
							sscyr.setSfcdpczr("�е��⳥����");
						}
					}
					
				}
			}
		}
	}
	/**
	 * ���Ȩ�����������жϵ��н�㣬����һ������֧���Ķ���λ��
	 * @param content
	 * @return
	 */
	public static int getMin(String content){
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.add(content.indexOf("�⳥"));
		indexList.add(content.indexOf("֧��"));
		indexList.add(content.indexOf("�е�"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("����"));
		indexList.add(content.indexOf("�⸶"));
		indexList.add(content.indexOf("����"));
		int result=content.length();
		for(Integer index:indexList){
			if(index!=-1 && index<result){
				result = index;
			}
		}
		return result;
	}
}
