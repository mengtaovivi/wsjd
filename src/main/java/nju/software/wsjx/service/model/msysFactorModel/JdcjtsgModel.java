package nju.software.wsjx.service.model.msysFactorModel;

import java.util.List;

public class JdcjtsgModel {

	/**
	 * �ο������ȡ��model�����������Ҫ��ȡ��Ҫ������
	 */
	
	/**
	 * ������Ŀ
	 */
	private String sgfssj;//һ���¹ʷ���ʱ�䣺������ʱ��
	private String sgfsdd;//�����¹ʷ����ص�
	private String sgjg;//�����¹ʾ���
	private List<String> sgzrrdqk;//�ġ��¹������϶����
	private List<String> sgclph;//�塢�¹ʳ����ƺ�
	private List<String> sgclsyz;//�����¹ʳ���������
	private List<String> sgcljsy;//�ߡ��¹ʳ�����ʻԱ
	private List<String> sgcljqxbxgs;//�ˡ��¹ʳ�����ǿ�ձ��չ�˾
	private List<String> sgclsyszxbxgs;//�š��¹ʳ�����ҵ�����ձ��չ�˾
	private List<String> syszxpcxe;//ʮ����ҵ�������⳥�޶�
	private List<String> sgqtpczrzt;//ʮһ���¹������⳥��������
	private String ydfpckse;//ʮ�����ѵ渶�⳥������
	private String dfr;//ʮ�����渶��

    private String sglx;//�¹�����
    private String shlx;//�����ͣ���Ϊ�����˲С���������
    private String sfCcss;//�Ƿ��вƲ���ʧ
    private String sfsjjqx;//�Ƿ��Ͻ���ǿ��
    private String sfsjsyx;//�Ƿ��Ͻ���ҵ��
    private String hasZrrds;//�Ƿ��н�ͨ�����϶���
    private String tcmz;//���չ�˾�Ƿ��������
	
	/**
	 * һ�����������á����ܺ����˲����á����ܺ�����������
	 * */
	private String ylfse;//ʮ�ġ�ҽ�Ʒ�����
	private String zysj;//ʮ�塢סԺʱ��
	private String zyhsbzfse;//ʮ����סԺ��ʳ����������
	private String hlq;//ʮ�ߡ�������
	private String hlfse;//ʮ�ˡ����������
	private String yyq;//ʮ�š�Ӫ����
	private String yyfse;//��ʮ��Ӫ��������
	private String wgq;//��ʮһ������
	private String wgfse;//��ʮ�����󹤷�����
	private String jtfse;//��ʮ������ͨ������
	private String zsfse;//��ʮ�ġ�ס�޷�����
	
	/**
	 * ���ܺ����˲е�����һ�������𺦼�����
	 * */
	private String cjdjjdjlsj;//��ʮ�塢�м��ȼ���������ʱ��
	private String cjdjjdjl;//��ʮ�����м��ȼ���������
	private String bfyrshfse_meim;//��ʮ�ߡ������������������
	private String cjpcjse;//��ʮ�ˡ��м��⳥������
	private String jsshfwj_meim;//��ʮ�š������𺦸�ο��
	private String cjshfzjfse;//��ʮ���м�������߷�����
	private String dchhlylcdjdyj;//��ʮһ�����к��������̶ȼ������
	private String dchhlfse;//��ʮ�������к��������
	
	/**
	 * ���ܺ�������������һ�������𺦼�����
	 * */
	private String swpcjse;//��ʮ�塢�����⳥������
	private String bfyrshfse_death;//��ʮ�ġ������������������
	private String jsshfwjse_death;//��ʮ�ߡ������𺦸�ο������
	private String szfse;//��ʮ�ˡ�ɥ�������
	private String shrqsjtfse;//��ʮ�š��ܺ�����������ɥ������֧���Ľ�ͨ������
	private String shrqszsfse;//��ʮ���ܺ�����������ɥ������֧����ס�޷�����
	private String shrqswgfse;//��ʮһ���ܺ�����������ɥ������֧�����󹤷�����
	
	/**
	 * �Ʋ���ʧ����
	 * */
	private String clwxfse;//ʮ�ġ�����ά�޷�����
	private String czwpssfse;//ʮ�塢������Ʒ��ʧ������
	private String sjfse;//ʮ����ʩ�ȷ�����
	private String clczfse;//ʮ�ߡ��������÷�����
	private String tyqj;//ʮ�ˡ�ͣ���ڼ�
	private String tyssse;//ʮ�š�ͣ����ʧ����
	private String tctdxjtgjfyse;//��ʮ��ͨ������Խ�ͨ���߷�������


    public String getSglx() {
        return sglx;
    }

    public void setSglx(String sglx) {
        this.sglx = sglx;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getSfCcss() {
        return sfCcss;
    }

    public void setSfCcss(String sfCcss) {
        this.sfCcss = sfCcss;
    }

    public String getSfsjjqx() {
        return sfsjjqx;
    }

    public void setSfsjjqx(String sfsjjqx) {
        this.sfsjjqx = sfsjjqx;
    }

    public String getSfsjsyx() {
        return sfsjsyx;
    }

    public void setSfsjsyx(String sfsjsyx) {
        this.sfsjsyx = sfsjsyx;
    }

    public String getHasZrrds() {
        return hasZrrds;
    }

    public void setHasZrrds(String hasZrrds) {
        this.hasZrrds = hasZrrds;
    }

    public String getTcmz() {
        return tcmz;
    }

    public void setTcmz(String tcmz) {
        this.tcmz = tcmz;
    }


    public String getSgfssj() {
		return sgfssj;
	}

	public void setSgfssj(String sgfssj) {
		this.sgfssj = sgfssj;
	}

	public String getSgfsdd() {
		return sgfsdd;
	}

	public void setSgfsdd(String sgfsdd) {
		this.sgfsdd = sgfsdd;
	}

	public String getSgjg() {
		return sgjg;
	}

	public void setSgjg(String sgjg) {
		this.sgjg = sgjg;
	}

	public List<String> getSgzrrdqk() {
		return sgzrrdqk;
	}

	public void setSgzrrdqk(List<String> sgzrrdqk) {
		this.sgzrrdqk = sgzrrdqk;
	}

	public List<String> getSgclph() {
		return sgclph;
	}

	public void setSgclph(List<String> sgclph) {
		this.sgclph = sgclph;
	}

	public List<String> getSgclsyz() {
		return sgclsyz;
	}

	public void setSgclsyz(List<String> sgclsyz) {
		this.sgclsyz = sgclsyz;
	}

	public List<String> getSgcljsy() {
		return sgcljsy;
	}

	public void setSgcljsy(List<String> sgcljsy) {
		this.sgcljsy = sgcljsy;
	}

	public List<String> getSgcljqxbxgs() {
		return sgcljqxbxgs;
	}

	public void setSgcljqxbxgs(List<String> sgcljqxbxgs) {
		this.sgcljqxbxgs = sgcljqxbxgs;
	}

	public List<String> getSgclsyszxbxgs() {
		return sgclsyszxbxgs;
	}

	public void setSgclsyszxbxgs(List<String> sgclsyszxbxgs) {
		this.sgclsyszxbxgs = sgclsyszxbxgs;
	}

	public List<String> getSyszxpcxe() {
		return syszxpcxe;
	}

	public void setSyszxpcxe(List<String> syszxpcxe) {
		this.syszxpcxe = syszxpcxe;
	}

	public List<String> getSgqtpczrzt() {
		return sgqtpczrzt;
	}

	public void setSgqtpczrzt(List<String> sgqtpczrzt) {
		this.sgqtpczrzt = sgqtpczrzt;
	}

	public String getYdfpckse() {
		return ydfpckse;
	}

	public void setYdfpckse(String ydfpckse) {
		this.ydfpckse = ydfpckse;
	}

	public String getDfr() {
		return dfr;
	}

	public void setDfr(String dfr) {
		this.dfr = dfr;
	}

	public String getYlfse() {
		return ylfse;
	}

	public void setYlfse(String ylfse) {
		this.ylfse = ylfse;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getZyhsbzfse() {
		return zyhsbzfse;
	}

	public void setZyhsbzfse(String zyhsbzfse) {
		this.zyhsbzfse = zyhsbzfse;
	}

	public String getHlq() {
		return hlq;
	}

	public void setHlq(String hlq) {
		this.hlq = hlq;
	}

	public String getHlfse() {
		return hlfse;
	}

	public void setHlfse(String hlfse) {
		this.hlfse = hlfse;
	}

	public String getYyq() {
		return yyq;
	}

	public void setYyq(String yyq) {
		this.yyq = yyq;
	}

	public String getYyfse() {
		return yyfse;
	}

	public void setYyfse(String yyfse) {
		this.yyfse = yyfse;
	}

	public String getWgq() {
		return wgq;
	}

	public void setWgq(String wgq) {
		this.wgq = wgq;
	}

	public String getWgfse() {
		return wgfse;
	}

	public void setWgfse(String wgfse) {
		this.wgfse = wgfse;
	}

	public String getJtfse() {
		return jtfse;
	}

	public void setJtfse(String jtfse) {
		this.jtfse = jtfse;
	}

	public String getZsfse() {
		return zsfse;
	}

	public void setZsfse(String zsfse) {
		this.zsfse = zsfse;
	}

	public String getCjdjjdjlsj() {
		return cjdjjdjlsj;
	}

	public void setCjdjjdjlsj(String cjdjjdjlsj) {
		this.cjdjjdjlsj = cjdjjdjlsj;
	}

	public String getCjdjjdjl() {
		return cjdjjdjl;
	}

	public void setCjdjjdjl(String cjdjjdjl) {
		this.cjdjjdjl = cjdjjdjl;
	}

	public String getBfyrshfse_meim() {
		return bfyrshfse_meim;
	}

	public void setBfyrshfse_meim(String bfyrshfse) {
		this.bfyrshfse_meim = bfyrshfse;
	}

	public String getCjpcjse() {
		return cjpcjse;
	}

	public void setCjpcjse(String cjpcjse) {
		this.cjpcjse = cjpcjse;
	}

	public String getJsshfwj_meim() {
		return jsshfwj_meim;
	}

	public void setJsshfwj_meim(String jsshfwj) {
		this.jsshfwj_meim = jsshfwj;
	}

	public String getCjshfzjfse() {
		return cjshfzjfse;
	}

	public void setCjshfzjfse(String cjshfzjfse) {
		this.cjshfzjfse = cjshfzjfse;
	}

	public String getDchhlylcdjdyj() {
		return dchhlylcdjdyj;
	}

	public void setDchhlylcdjdyj(String dchhlylcdjdyj) {
		this.dchhlylcdjdyj = dchhlylcdjdyj;
	}

	public String getDchhlfse() {
		return dchhlfse;
	}

	public void setDchhlfse(String dchhlfse) {
		this.dchhlfse = dchhlfse;
	}

	public String getSwpcjse() {
		return swpcjse;
	}

	public void setSwpcjse(String swpcjse) {
		this.swpcjse = swpcjse;
	}

	public String getShrqsjtfse() {
		return shrqsjtfse;
	}

	public void setShrqsjtfse(String shrqsjtfse) {
		this.shrqsjtfse = shrqsjtfse;
	}

	public String getShrqszsfse() {
		return shrqszsfse;
	}

	public void setShrqszsfse(String shrqszsfse) {
		this.shrqszsfse = shrqszsfse;
	}

	public String getShrqsblszsyzcdwgfse() {
		return shrqswgfse;
	}

	public void setShrqsblszsyzcdwgfse(String shrqswgfse) {
		this.shrqswgfse = shrqswgfse;
	}

	public String getClwxfse() {
		return clwxfse;
	}

	public void setClwxfse(String clwxfse) {
		this.clwxfse = clwxfse;
	}

	public String getCzwpssfse() {
		return czwpssfse;
	}

	public void setCzwpssfse(String czwpssfse) {
		this.czwpssfse = czwpssfse;
	}

	public String getSjfse() {
		return sjfse;
	}

	public void setSjfse(String sjfse) {
		this.sjfse = sjfse;
	}

	public String getClczfse() {
		return clczfse;
	}

	public void setClczfse(String clczfse) {
		this.clczfse = clczfse;
	}

	public String getTyqj() {
		return tyqj;
	}

	public void setTyqj(String tyqj) {
		this.tyqj = tyqj;
	}

	public String getTyssse() {
		return tyssse;
	}

	public void setTyssse(String tyssse) {
		this.tyssse = tyssse;
	}

	public String getTctdxjtgjfyse() {
		return tctdxjtgjfyse;
	}

	public void setTctdxjtgjfyse(String tctdxjtgjfyse) {
		this.tctdxjtgjfyse = tctdxjtgjfyse;
	}

	public String getSzfse() {
		return szfse;
	}

	public void setSzfse(String szfse) {
		this.szfse = szfse;
	}

	public String getBfyrshfse_death() {
		return bfyrshfse_death;
	}

	public void setBfyrshfse_death(String bfyrshfse_death) {
		this.bfyrshfse_death = bfyrshfse_death;
	}

	public String getJsshfwjse_death() {
		return jsshfwjse_death;
	}

	public void setJsshfwjse_death(String jsshfwjse_death) {
		this.jsshfwjse_death = jsshfwjse_death;
	}
}
