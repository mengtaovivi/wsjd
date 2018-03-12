package nju.software.wsjx.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * ��ֵ��صĹ�����
 * @author zym
 *
 */
public class NumberUtil {
	/**
	 * ��ֵ������λС��
	 * 
	 * @param convert
	 *            ת��ǰ��ֵ
	 * @return ת�����ַ���
	 */
	public static String changeNumberType(BigDecimal big) {

		big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}
	
	/**
	 * ��double ��ౣ��placeλ��
	 * @param value
	 * @param place ����λ��
	 * @return
	 */
	public static String changeNumber(double value, int place){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(place);
		nf.setGroupingUsed(false);
		return nf.format(value);
	}
}
