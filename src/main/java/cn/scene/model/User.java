package cn.scene.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String photo;

    private Integer jifen;

    private Byte isMember;

    private Date startTime;

    private Integer alNumbers;

    private Integer numbers;

    private Boolean isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getJifen() {
        return jifen;
    }

    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }

    public Byte getIsMember() {
        return isMember;
    }

    public void setIsMember(Byte isMember) {
        this.isMember = isMember;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getAlNumbers() {
        return alNumbers;
    }

    public void setAlNumbers(Integer alNumbers) {
        this.alNumbers = alNumbers;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}