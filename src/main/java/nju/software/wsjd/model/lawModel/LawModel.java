package nju.software.wsjd.model.lawModel;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashMap;

/**
 * Created by 69401 on 2018/3/18.
 */
@Document
public class LawModel {

    @Id
    private String _id;

    @Indexed
    @Field("��������")
    private String lawname;

    @Field("��������")
    private LinkedHashMap<String,TiaoModel> content;

    @Field("ʱЧ��")
    private String timelimit;

    @Field("Ч������")
    private String level;

    @Field("��������")
    private String publishtime;

    @Field("ʵʩ����")
    private String starttime;

    public String getLawname() {
        return lawname;
    }

    public void setLawname(String lawname) {
        this.lawname = lawname;
    }

    public LinkedHashMap<String, TiaoModel> getContent() {
        return content;
    }

    public void setContent(LinkedHashMap<String, TiaoModel> content) {
        this.content = content;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
}
