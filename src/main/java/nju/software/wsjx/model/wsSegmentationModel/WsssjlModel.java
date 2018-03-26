package nju.software.wsjx.model.wsSegmentationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjd.model.gxWsModel.ZyfyModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.FsModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.SsrqydsrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;

/**
 * hufk
 * �������ϼ�¼
 */
public class WsssjlModel {
	private String ay;//����
	private String wzay;//��������
	private String ajly;//������Դ
	private String ajsj;//�����漰
	private String ktsl;//��ͥ����
	private String aydm;//���ɴ���
	private ArrayList<String> ktrq;//��ͥ����(;)
	private ArrayList<String> qsah;//ǰ�󰸺�(;)
	private ArrayList<String>zsscajah;//������鰸������
	private String qsfy;//ǰ��Ժ
	private String ktslxx;//��ͥ������Ϣ
	private String bgkslyy;//����������ԭ��
	private String larq;//�������� 
	
	private String ysajsycx;//һ�󰸼����ó��� 
	private String jyzpt;//����ת��ͨ 
	private String ysajly;//һ�󰸼���Դ 
	private String slrq;//��������
	private String spzz;//������֯
	private String drsp;//��������
	private String sqcsrq;//���볷������
	private HashMap<String,String> qxrxx;//ȱϯ����Ϣ(����)
	private HashMap<String,String> ctrxx;//��ͥ����Ϣ(����)
	private String qsrq;//��������
	private String bgzyldct;//������Ҫ�쵼��ͥ
	private String xzxwzl;//������Ϊ����
	private String xzqqxwzl;//������Ȩ��Ϊ����
	
	private String jysyjycx;//�������ü��׳���
	private String msbfjxsl;//�������²��ּ�������
	private String ssxz;//��������
	private String jcy;//���Ա
	private String js;//���Ա��ɫ
	private ArrayList<WsssjlZkjlModel> wsssjlZkjl;//�������ϼ�¼ָ�ؼ�¼
	private String qszay;//����������
	private String gsjg;//���߻���
	private String gsah;//���߰���
	private String slztqfdmsss;//���������𸽴���������
	private String jcyjyyqsl;//���Ժ������������
	private String snft;//���귨ͥ
	
	private String xzesqsah;//��������ǰ�󰸺�
	private String xzzsqsah;//��������ǰ�󰸺�
	private String xszsqsah;//��������ǰ�󰸺�
	private String qsahlasj;//ǰ�󰸺�����ʱ��
	private String qsland;//ǰ���������
	private String qsfyjc;//ǰ��Ժ���
	private String fyjb;//ǰ��Ժ����
	private String qsahsxh;//ǰ�󰸺�˳���
	private String qscpsj;//ǰ�����ʱ��
	private String qswszl;//ǰ����������
	private String qsajyl;//ǰ�󰸼�����
	private String qsjafs;//ǰ��᰸��ʽ
	private String qssj;//ǰ����
	
	private String sshksfw;//���߻��߷�Χ
	private String qspj;//ǰ���о�
	private String xsesqsah;//���¶���ǰ�󰸺�
	private String bzfymc;//��׼��Ժ����
	private String qsgsjg;//ԭ���߻���

	private String yg;//ԭ�� ��Ͻ ��һ�� ���������
	private String bg;//���� ��Ͻ ��һ�� ���������
	private String lafy;//������Ժ ��Ͻ
	private String ssr;//������ ��Ͻ
	private String sscdfymc;//���߲ö���Ժ���� ��Ͻ
	private String sscdah;//���߲ö����� ��Ͻ
	private List<ZyfyModel> zymf;//���鷨Ժ ��Ͻ
	private String bqfymc;//����ʱ�� ��Ͻ
	private String bqsj;//���뷨Ժ ��Ͻ

	public String gxString(){
		return "temo{" +
				"yg='" + yg + '\'' +
				", bg='" + bg + '\'' +
				", lafy='" + lafy + '\'' +
				", ssr='" + ssr + '\'' +
				", sscdfymc='" + sscdfymc + '\'' +
				", sscdah='" + sscdah + '\'' +
				", zymf=" + zymf +
				", bqfymc='" + bqfymc + '\'' +
				", bqsj='" + bqsj + '\'' +
				'}';
	}

	private SsrqydsrModel qsz;//����״ ��һ��
	private SsrqydsrModel fsz;//����״ ��һ��
	private SsrqydsrModel jjct;//�ܾ���ͥ ��һ��
	private SsrqydsrModel cs;//���� ��һ��
	private FsModel fs;//���� ��һ��

	private String bgsqr;//������ ���������
	private String bgsqrq;//�������� ���������

	public String getBgsqr() {
		return bgsqr;
	}

	public void setBgsqr(String bgsqr) {
		this.bgsqr = bgsqr;
	}

	public String getBgsqrq() {
		return bgsqrq;
	}

	public void setBgsqrq(String bgsqrq) {
		this.bgsqrq = bgsqrq;
	}

	public SsrqydsrModel getQsz() {
		return qsz;
	}

	public void setQsz(SsrqydsrModel qsz) {
		this.qsz = qsz;
	}

	public SsrqydsrModel getFsz() {
		return fsz;
	}

	public void setFsz(SsrqydsrModel fsz) {
		this.fsz = fsz;
	}

	public SsrqydsrModel getJjct() {
		return jjct;
	}

	public void setJjct(SsrqydsrModel jjct) {
		this.jjct = jjct;
	}

	public SsrqydsrModel getCs() {
		return cs;
	}

	public void setCs(SsrqydsrModel cs) {
		this.cs = cs;
	}

	public FsModel getFs() {
		return fs;
	}

	public void setFs(FsModel fs) {
		this.fs = fs;
	}

	public String getXzzsqsah() {
		return xzzsqsah;
	}

	public void setXzzsqsah(String xzzsqsah) {
		this.xzzsqsah = xzzsqsah;
	}

	public String getYg() {
		return yg;
	}

	public void setYg(String yg) {
		this.yg = yg;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getLafy() {
		return lafy;
	}

	public void setLafy(String lafy) {
		this.lafy = lafy;
	}

	public String getSsr() {
		return ssr;
	}

	public void setSsr(String ssr) {
		this.ssr = ssr;
	}

	public String getSscdfymc() {
		return sscdfymc;
	}

	public void setSscdfymc(String sscdfymc) {
		this.sscdfymc = sscdfymc;
	}

	public String getSscdah() {
		return sscdah;
	}

	public void setSscdah(String sscdah) {
		this.sscdah = sscdah;
	}

	public List<ZyfyModel> getZymf() {
		return zymf;
	}

	public void setZymf(List<ZyfyModel> zymf) {
		this.zymf = zymf;
	}

	public String getBqfymc() {
		return bqfymc;
	}

	public void setBqfymc(String bqfymc) {
		this.bqfymc = bqfymc;
	}

	public String getBqsj() {
		return bqsj;
	}

	public void setBqsj(String bqsj) {
		this.bqsj = bqsj;
	}

	public String getAy() {
		return ay;
	}
	public void setAy(String ay) {
		this.ay = ay;
	}
	public String getWzay() {
		return wzay;
	}
	public void setWzay(String wzay) {
		this.wzay = wzay;
	}
	public String getAjly() {
		return ajly;
	}
	public void setAjly(String ajly) {
		this.ajly = ajly;
	}
	public String getAjsj() {
		return ajsj;
	}
	public void setAjsj(String ajsj) {
		this.ajsj = ajsj;
	}
	public String getKtsl() {
		return ktsl;
	}
	public void setKtsl(String ktsl) {
		this.ktsl = ktsl;
	}
	public String getAydm() {
		return aydm;
	}
	public void setAydm(String aydm) {
		this.aydm = aydm;
	}
	public ArrayList<String> getKtrq() {
		return ktrq;
	}
	public void setKtrq(ArrayList<String> ktrq) {
		this.ktrq = ktrq;
	}
	public ArrayList<String> getQsah() {
		return qsah;
	}
	public void setQsah(ArrayList<String> qsah) {
		this.qsah = qsah;
	}
	public String getQsfy() {
		return qsfy;
	}
	public ArrayList<String> getZsscajah(){
		return zsscajah;
	}
	public void setZsscajah(ArrayList<String> zsscajah){
		this.zsscajah=zsscajah;
	}
	public void setQsfy(String qsfy) {
		this.qsfy = qsfy;
	}
	public String getKtslxx() {
		return ktslxx;
	}
	public void setKtslxx(String ktslxx) {
		this.ktslxx = ktslxx;
	}
	public String getBgkslyy() {
		return bgkslyy;
	}
	public void setBgkslyy(String bgkslyy) {
		this.bgkslyy = bgkslyy;
	}
	public String getLarq() {
		return larq;
	}
	public void setLarq(String larq) {
		this.larq = larq;
	}
	
	public String getYsajsycx() {
		return ysajsycx;
	}
	public void setYsajsycx(String ysajsycx) {
		this.ysajsycx = ysajsycx;
	}
	public String getJyzpt() {
		return jyzpt;
	}
	public void setJyzpt(String jyzpt) {
		this.jyzpt = jyzpt;
	}
	public String getYsajly() {
		return ysajly;
	}
	public void setYsajly(String ysajly) {
		this.ysajly = ysajly;
	}
	public String getSlrq() {
		return slrq;
	}
	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}
	public String getSpzz() {
		return spzz;
	}
	public void setSpzz(String spzz) {
		this.spzz = spzz;
	}
	public String getDrsp() {
		return drsp;
	}
	public void setDrsp(String drsp) {
		this.drsp = drsp;
	}
	public String getSqcsrq() {
		return sqcsrq;
	}
	public void setSqcsrq(String sqcsrq) {
		this.sqcsrq = sqcsrq;
	}
	public HashMap<String, String> getQxrxx() {
		return qxrxx;
	}
	public void setQxrxx(HashMap<String, String> qxrxx) {
		this.qxrxx = qxrxx;
	}
	public HashMap<String, String> getCtrxx() {
		return ctrxx;
	}
	public void setCtrxx(HashMap<String, String> ctrxx) {
		this.ctrxx = ctrxx;
	}
	public String getXzxwzl() {
		return xzxwzl;
	}
	public void setXzxwzl(String xzxwzl) {
		this.xzxwzl = xzxwzl;
	}
	public String getXzqqxwzl() {
		return xzqqxwzl;
	}
	public void setXzqqxwzl(String xzqqxwzl) {
		this.xzqqxwzl = xzqqxwzl;
	}
	public String getBgzyldct() {
		return bgzyldct;
	}
	public void setBgzyldct(String bgzyldct) {
		this.bgzyldct = bgzyldct;
	}
	public String getQsrq() {
		return qsrq;
	}
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	public String getSsxz() {
		return ssxz;
	}
	public void setSsxz(String ssxz) {
		this.ssxz = ssxz;
	}
	public String getQszay() {
		return qszay;
	}
	public void setQszay(String qszay) {
		this.qszay = qszay;
	}
	public String getGsjg() {
		return gsjg;
	}
	public void setGsjg(String gsjg) {
		this.gsjg = gsjg;
	}
	public String getGsah() {
		return gsah;
	}
	public void setGsah(String gsah) {
		this.gsah = gsah;
	}
	public String getJcyjyyqsl() {
		return jcyjyyqsl;
	}
	public void setJcyjyyqsl(String jcyjyyqsl) {
		this.jcyjyyqsl = jcyjyyqsl;
	}
	public String getSnft() {
		return snft;
	}
	public void setSnft(String snft) {
		this.snft = snft;
	}
	public String getJysyjycx() {
		return jysyjycx;
	}
	public void setJysyjycx(String jysyjycx) {
		this.jysyjycx = jysyjycx;
	}
	public ArrayList<WsssjlZkjlModel> getWsssjlZkjl() {
		return wsssjlZkjl;
	}
	public void setWsssjlZkjl(ArrayList<WsssjlZkjlModel> wsssjlZkjl) {
		this.wsssjlZkjl = wsssjlZkjl;
	}
	public String getSlztqfdmsss() {
		return slztqfdmsss;
	}
	public void setSlztqfdmsss(String slztqfdmsss) {
		this.slztqfdmsss = slztqfdmsss;
	}
	public String getJcy() {
		return jcy;
	}
	public void setJcy(String jcy) {
		this.jcy = jcy;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getQsfyjc() {
		return qsfyjc;
	}
	public void setQsfyjc(String qsfyjc) {
		this.qsfyjc = qsfyjc;
	}
	public String getQsahsxh() {
		return qsahsxh;
	}
	public void setQsahsxh(String qsahsxh) {
		this.qsahsxh = qsahsxh;
	}
	public String getXzesqsah() {
		return xzesqsah;
	}
	public void setXzesqsah(String xzesqsah) {
		this.xzesqsah = xzesqsah;
	}
	public String getQswszl() {
		return qswszl;
	}
	public void setQswszl(String qswszl) {
		this.qswszl = qswszl;
	}
	public String getQsjafs() {
		return qsjafs;
	}
	public void setQsjafs(String qsjafs) {
		this.qsjafs = qsjafs;
	}
	public String getQssj() {
		return qssj;
	}
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	public String getMsbfjxsl() {
		return msbfjxsl;
	}
	public void setMsbfjxsl(String msbfjxsl) {
		this.msbfjxsl = msbfjxsl;
	}
	public String getQsland() {
		return qsland;
	}
	public void setQsland(String qsland) {
		this.qsland = qsland;
	}
	public String getFyjb() {
		return fyjb;
	}
	public void setFyjb(String fyjb) {
		this.fyjb = fyjb;
	}
	public String getQscpsj() {
		return qscpsj;
	}
	public void setQscpsj(String qscpsj) {
		this.qscpsj = qscpsj;
	}
	public String getQsajyl() {
		return qsajyl;
	}
	public void setQsajyl(String qsajyl) {
		this.qsajyl = qsajyl;
	}
	public String getSshksfw() {
		return sshksfw;
	}
	public void setSshksfw(String sshksfw) {
		this.sshksfw = sshksfw;
	}
	public String getQspj() {
		return qspj;
	}
	public void setQspj(String qspj) {
		this.qspj = qspj;
	}
	public String getXsesqsah() {
		return xsesqsah;
	}
	public void setXsesqsah(String xsesqsah) {
		this.xsesqsah = xsesqsah;
	}
	public String getXszsqsah() {
		return xszsqsah;
	}
	public void setXszsqsah(String xszsqsah) {
		this.xszsqsah = xszsqsah;
	}
	public String getQsahlasj() {
		return qsahlasj;
	}
	public void setQsahlasj(String qsahlasj) {
		this.qsahlasj = qsahlasj;
	}
	public String getBzfymc() {
		return bzfymc;
	}
	public void setBzfymc(String bzfymc) {
		this.bzfymc = bzfymc;
	}
	public String getQsgsjg() {
		return qsgsjg;
	}
	public void setQsgsjg(String qsgsjg) {
		this.qsgsjg = qsgsjg;
	}
	@Override
	public String toString() {
		return "WsssjlModel [ay=" + ay + ", wzay=" + wzay + ", ajly=" + ajly
				+ ", ajsj=" + ajsj + ", ktsl=" + ktsl + ", aydm=" + aydm
				+ ", ktrq=" + ktrq + ", qsah=" + qsah + ", qsfy=" + qsfy
				+ ", ktslxx=" + ktslxx + ", bgkslyy=" + bgkslyy + ", larq="
				+ larq + ", ysajsycx=" + ysajsycx + ", jyzpt=" + jyzpt
				+ ", ysajly=" + ysajly + ", slrq=" + slrq + ", spzz=" + spzz
				+ ", drsp=" + drsp + ", sqcsrq=" + sqcsrq + ", qxrxx=" + qxrxx
				+ ", ctrxx=" + ctrxx + ", qsrq=" + qsrq + ", bgzyldct="
				+ bgzyldct + ", xzxwzl=" + xzxwzl + ", xzqqxwzl=" + xzqqxwzl
				+ ", jysyjycx=" + jysyjycx + ", msbfjxsl=" + msbfjxsl
				+ ", ssxz=" + ssxz + ", jcy=" + jcy + ", js=" + js
				+ ", wsssjlZkjl=" + wsssjlZkjl + ", qszay=" + qszay + ", gsjg="
				+ gsjg + ", gsah=" + gsah + ", slztqfdmsss=" + slztqfdmsss
				+ ", jcyjyyqsl=" + jcyjyyqsl + ", snft=" + snft + ", xzesqsah="
				+ xzesqsah + ", qsland=" + qsland + ", qsfyjc=" + qsfyjc
				+ ", fyjb=" + fyjb + ", qsahsxh=" + qsahsxh + ", qscpsj="
				+ qscpsj + ", qswszl=" + qswszl + ", qsajyl=" + qsajyl
				+ ", qsjafs=" + qsjafs + ", qssj=" + qssj + ", sshksfw="
				+ sshksfw + ", qspj=" + qspj + ", xsesqsah=" + xsesqsah
				+ ", bzfymc=" + bzfymc + ", qsgsjg=" + qsgsjg + "]";
	}
	
	
}
