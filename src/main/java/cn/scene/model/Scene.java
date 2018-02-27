package cn.scene.model;

import java.util.Date;

public class Scene {
    private Integer id;

    private Integer userId;

    private String code;

    private String cover;

    private String music;

    private String mTitle;

    private String title;

    private String describes;

    private Date times;

    private String sTimes;

    private Integer types;

    private Integer hitCount;

    private Integer jifen;

    private Integer fromScene;

    private Integer counts;

    private Byte isIssue;

    private Byte isDelicate;

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

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music == null ? null : music.trim();
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle == null ? null : mTitle.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
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

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Byte getIsIssue() {
        return isIssue;
    }

    public void setIsIssue(Byte isIssue) {
        this.isIssue = isIssue;
    }

    public Byte getIsDelicate() {
        return isDelicate;
    }

    public void setIsDelicate(Byte isDelicate) {
        this.isDelicate = isDelicate;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
}