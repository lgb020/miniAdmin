package cn.scene.model;

import java.util.Date;

public class Scene {
    private Integer id;

    private Integer userId;

    private String code;

    private String cover;

    private Integer music;

    private String title;

    private String descirbes;

    private Date times;

    private Integer type;

    private Integer hitCount;

    private Integer jifen;

    private Integer fromScene;

    private Integer count;

    private Byte isIssue;

    private Byte isDeliacte;

    private Byte isDel;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getMusic() {
        return music;
    }

    public void setMusic(Integer music) {
        this.music = music;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescirbes() {
        return descirbes;
    }

    public void setDescirbes(String descirbes) {
        this.descirbes = descirbes == null ? null : descirbes.trim();
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Integer getJifen() {
        return jifen;
    }

    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }

    public Integer getFromScene() {
        return fromScene;
    }

    public void setFromScene(Integer fromScene) {
        this.fromScene = fromScene;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getIsIssue() {
        return isIssue;
    }

    public void setIsIssue(Byte isIssue) {
        this.isIssue = isIssue;
    }

    public Byte getIsDeliacte() {
        return isDeliacte;
    }

    public void setIsDeliacte(Byte isDeliacte) {
        this.isDeliacte = isDeliacte;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
}