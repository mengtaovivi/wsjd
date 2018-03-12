package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {
	public ArrayList<Double> getJc() {
		return jc;
	}

	public void setJc(ArrayList<Double> jc) {
		this.jc = jc;
	}

	private int k;// �ֳɶ��ٴ�
	private int m;// ��������
	private int dataSetLength;// ���ݼ�Ԫ�ظ����������ݼ��ĳ���
	private ArrayList<double[]> dataSet;// ���ݼ�����
	private ArrayList<double[]> center;// ��������
	private ArrayList<ArrayList<double[]>> cluster; // ��
	private ArrayList<Double> jc;// ���ƽ���ͣ�kԽ�ӽ�dataSetLength�����ԽС
	private Random random;
	private ArrayList<ArrayList<Double>> xsdjh; // ���ƶȼ���
	private double minCos;// ��С���ƶ�
	private double avgCos;
	
	
	public double getAvgCos() {
		return avgCos;
	}

	/**
	 * ������ά��
	 */
	private int wd;

	private double maxjl;// �������ĵ����ľ���

	public double getMaxjl() {
		return maxjl;
	}

	private double averageDistance;

	public double getAverageDistance() {
		return averageDistance;
	}

	/**
	 * ����������ԭʼ���ݼ�
	 * 
	 * @param dataSet
	 */

	public void setDataSet(ArrayList<double[]> dataSet) {
		this.dataSet = dataSet;

	}

	/**
	 * ��ȡ�������
	 * 
	 * @return �����
	 */

	public ArrayList<ArrayList<double[]>> getCluster() {
		return cluster;
	}

	/**
	 * ���캯����������Ҫ�ֳɵĴ�����
	 * 
	 * @param k
	 *            ������,��k<=0ʱ������Ϊ1����k��������Դ�ĳ���ʱ����Ϊ����Դ�ĳ���
	 */
	public Kmeans(int k, int wd) {
		if (k <= 0) {
			k = 1;
		}
		this.k = k;
		if (wd <= 0) {
			wd = 1;
		}
		this.wd = wd;
		minCos = 100;// ��С���ƶ�

	}

	/**
	 * ��ʼ��
	 */
	private void init() {
		maxjl = 0;
		m = 0;
		random = new Random();
		if (dataSet == null || dataSet.size() == 0) {
			initDataSet();
		}
		dataSetLength = dataSet.size();
		if (k > dataSetLength) {
			k = dataSetLength;
		}
		center = initCenters();
		cluster = initCluster();
		jc = new ArrayList<Double>();
	}

	/**
	 * ���������δ��ʼ�����ݼ���������ڲ��������ݼ�
	 */
	private void initDataSet() {
		dataSet = new ArrayList<double[]>();
		// ����{6,3}��һ���ģ����Գ���Ϊ15�����ݼ��ֳ�14�غ�15�ص���Ϊ0
		double[][] dataSetArray = new double[][] { { 8, 2 }, { 3, 4 },
				{ 2, 5 }, { 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 },
				{ 5, 3 }, { 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 },
				{ 8, 6 } };

		for (int i = 0; i < dataSetArray.length; i++) {
			dataSet.add(dataSetArray[i]);
		}
	}

	/**
	 * ��ʼ���������������ֳɶ��ٴؾ��ж��ٸ����ĵ� ʹ�������ѡȡһ���㣬Ȼ�󲻶�ȡ��������㼯��Զ�ĵ㣬���ε�����ֱ��ѡȡ����k��
	 * 
	 * @return ���ĵ㼯
	 */
	private ArrayList<double[]> initCenters() {
		ArrayList<double[]> center = new ArrayList<double[]>();
		int[] randoms = new int[k];
		int temp = random.nextInt(dataSetLength);
		randoms[0] = temp;
		for (int i = 1; i < k; i++) {
			randoms[i] = sx(randoms, i);
		}

		// ����������������
		/*
		 * for (int i = 0; i < k; i++) { System.out.println("test1:randoms[" + i
		 * + "]=" + randoms[i]); }
		 */

		// System.out.println();
		for (int i = 0; i < k; i++) {
			center.add(dataSet.get(randoms[i]));// ���ɳ�ʼ����������
		}
		return center;
	}

	/**
	 * ��ȡ��randoms��Զ��λ��
	 * 
	 * @param randoms
	 * @return
	 */
	private int sx(int[] randoms, int count) {
		int place = 0;
		int maxDistance = 0;
		for (int i = 0; i < dataSet.size(); i++) {
			int distance = 0;
			if (isSx(randoms, count, i))
				continue;
			for (int j = 0; j < count; j++) {
				distance += Math.sqrt(errorSquare(dataSet.get(randoms[j]),
						dataSet.get(i)));
			}
			/* System.out.println(distance+"******************"+i); */
			if (distance >= maxDistance) {

				if (distance == maxDistance && random.nextInt(10) < 5) {
					continue;
				}
				maxDistance = distance;
				place = i;
			}
		}
		return place;
	}

	/**
	 * ����λ��index�Ƿ�ѡȡ��
	 * 
	 * @param randoms
	 * @param index
	 * @return
	 */
	private boolean isSx(int[] randoms, int count, int index) {
		for (int i = 0; i < count; i++)
			if (index == randoms[i]) {
				return true;
			}
		return false;
	}

	/**
	 * ��ʼ���ؼ���
	 * 
	 * @return һ����Ϊk�صĿ����ݵĴؼ���
	 */
	private ArrayList<ArrayList<double[]>> initCluster() {
		ArrayList<ArrayList<double[]>> cluster = new ArrayList<ArrayList<double[]>>();
		for (int i = 0; i < k; i++) {
			cluster.add(new ArrayList<double[]>());
		}

		return cluster;
	}

	/**
	 * ����������֮��ľ���
	 * 
	 * @param element
	 *            ��1
	 * @param center
	 *            ��2
	 * @return ����
	 */
	private double distance(double[] element, double[] center) {
		double distance = 0.0f;
		double z = 0;
		// System.out.println(center.length + ":" + element.length);
		for (int i = 0; i < wd; i++) {
			z = z + (element[i] - center[i]) * (element[i] - center[i]);
		}
		distance = (double) Math.sqrt(z);

		return distance;
	}

	/**
	 * ��ȡ���뼯������С�����λ��
	 * 
	 * @param distance
	 *            ��������
	 * @return ��С�����ھ��������е�λ��
	 */
	private int minDistance(double[] distance) {
		double minDistance = distance[0];
		int minLocation = 0;
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] < minDistance) {
				minDistance = distance[i];
				minLocation = i;
			} else if (distance[i] == minDistance) // �����ȣ��������һ��λ��
			{
				if (random.nextInt(10) < 5) {
					minLocation = i;
				}
			}
		}

		return minLocation;
	}

	/**
	 * ���ģ�����ǰԪ�طŵ���С����������صĴ���
	 */
	private void clusterSet() {
		double[] distance = new double[k];
		for (int i = 0; i < dataSetLength; i++) {
			for (int j = 0; j < k; j++) {
				distance[j] = distance(dataSet.get(i), center.get(j));
				// System.out.println("test2:"+"dataSet["+i+"],center["+j+"],distance="+distance[j]);

			}
			int minLocation = minDistance(distance);
			/*
			 * System.out.println("test3:" + "dataSet[" + i + "],minLocation=" +
			 * minLocation);
			 */
			// System.out.println();

			cluster.get(minLocation).add(dataSet.get(i));// ���ģ�����ǰԪ�طŵ���С����������صĴ���

		}
	}

	/**
	 * ���������ƽ���ķ���
	 * 
	 * @param element
	 *            ��1
	 * @param center
	 *            ��2
	 * @return ���ƽ��
	 */
	private double errorSquare(double[] element, double[] center) {
		int length = wd;
		double errSquare = 0;
		for (int i = 0; i < length; i++) {
			errSquare = errSquare + (element[i] - center[i])
					* (element[i] - center[i]);
		}

		return errSquare;
	}

	/**
	 * �������ƽ����׼��������
	 */
	private void countRule() {
		double jcF = 0;
		maxjl = 0;
		double total = 0;
		for (int i = 0; i < cluster.size(); i++) {
			for (int j = 0; j < cluster.get(i).size(); j++) {
				double xldistance = errorSquare(cluster.get(i).get(j),
						center.get(i));
				jcF += xldistance;
				xldistance = Math.sqrt(xldistance);
				total = total + xldistance;
				if (xldistance > maxjl) {
					maxjl = xldistance;
				}
			}
		}
		averageDistance = total / dataSetLength;
		jc.add(jcF);
	}

	/**
	 * �����µĴ����ķ���
	 */
	private void setNewCenter() {

		for (int i = 0; i < k; i++) {
			int n = cluster.get(i).size();
			if (n != 0) {
				double[] newCenter = new double[wd];
				for (int mm = 0; mm < wd; mm++) {
					for (int j = 0; j < n; j++) {
						newCenter[mm] += cluster.get(i).get(j)[mm];
					}
					newCenter[mm] = newCenter[mm] / n;
				}
				center.set(i, newCenter);
			}
		}
	}

	/**
	 * ��ӡ���ݣ�������
	 * 
	 * @param dataArray
	 *            ���ݼ�
	 * @param dataArrayName
	 *            ���ݼ�����
	 */
	public void printDataArray(ArrayList<double[]> dataArray,
			String dataArrayName) {
		for (int i = 0; i < dataArray.size(); i++) {

			for (int j = 0; j < wd; j++)
				if (j == 0)
					System.out.print(dataArrayName + "[" + i + "]={"
							+ dataArray.get(i)[j] + ",");
				else {
					System.out.print(+dataArray.get(i)[j] + ",");
				}
			System.out.println();
		}
		System.out.println("===================================");
	}

	private double getCos(double x1[], double x2[]) {
		double fz = 0;
		int length = x1.length;
		for (int i = 0; i < length; i++) {
			fz = fz + x1[i] * x2[i];
		}
		double fm = 0;
		for (int i = 0; i < length; i++) {
			fm = fm + x1[i] * x1[i];
		}
		double fm2 = 0;
		for (int i = 0; i < length; i++) {
			fm2 = fm2 + x2[i] * x2[i];
		}
		fm = Math.sqrt(fm2) * Math.sqrt(fm);
		if (fm != 0)
			return (fz / fm) * 100;
		return 0;

	}

	/**
	 * Kmeans�㷨���Ĺ��̷���
	 */
	private void kmeans() {
		init();
		/*
		 * printDataArray(dataSet, "initDataSet"); printDataArray(center,
		 * "initCenter");
		 */

		// ѭ�����飬ֱ������Ϊֹ
		while (true) {
			clusterSet();
			// System.out.println("m:"+m);
			// for(int i=0;i<cluster.size();i++)
			// {
			// printDataArray(cluster.get(i),"cluster["+i+"]");
			// }

			countRule();

			// System.out.println("count:" + "jc[" + m + "]=" + jc.get(m));

			// System.out.println();
			// �����ˣ��������
			if (m != 0) {
				if (jc.get(m) - jc.get(m - 1) == 0) {
					break;
				}
			}

			setNewCenter();
			// printDataArray(center, "newCenter");
			m++;
			// System.out.println("m:"+m);
			cluster.clear();
			cluster = initCluster();
		}

		xsdjh = new ArrayList<ArrayList<Double>>();
		double sum=0;
		int num=0;
		for (int i = 0; i < cluster.size(); i++) {
			ArrayList<Double> result = new ArrayList<Double>();

			if (cluster.get(i).size() == 0)
				xsdjh.add(result);
			else {

				
				for (int j = 0; j < cluster.get(i).size(); j++) {
					double cos = getCos(center.get(i), cluster.get(i).get(j));
					// printDataArray(cluster.get(i), "cluster");
					// System.out.println(cos+"::cos");
					if(cos<100){
					sum+=cos;
					num++;
					}
					if (cos < minCos)
						minCos = cos;
					result.add(cos);
				}
				xsdjh.add(result);
			}

		}
        avgCos=sum/num;
		// System.out.println(minCos);
		// System.out.println("note:the times of repeat:m="+m);//�����������
	}

	/**
	 * ִ���㷨
	 */
	public void execute() {
		/*
		 * long startTime = System.currentTimeMillis();
		 * System.out.println("kmeans begins");
		 */
		kmeans();
		/*
		 * long endTime = System.currentTimeMillis();
		 * System.out.println("kmeans running time=" + (endTime - startTime) +
		 * "ms"); System.out.println("kmeans ends"); System.out.println();
		 */
	}

	public ArrayList<ArrayList<Double>> getXsdjh() {
		return xsdjh;
	}

	public double getMinCos() {
		return minCos;
	}

}
