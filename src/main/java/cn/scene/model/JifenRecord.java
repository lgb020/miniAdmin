package cn.scene.model;

import java.util.Date;

public class JifenRecord {
    private Integer id;

    private Integer userId;

    private Integer jifen;

    private String type;

    private String descirbe;

    private Date times;

    private String sTimes;

    private Boolean isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJifen() {
        return jifen;
    }

    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescirbe() {
        return descirbe;
    }

    public void setDescirbe(String descirbe) {
        this.descirbe = descirbe == null ? null : descirbe.trim();
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getsTimes() {
        return sTimes;
    }

    public void setsTimes(String sTimes) {
        this.sTimes = sTimes;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}