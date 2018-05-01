package cn.scene.model;

public class SceneReport {
    private Integer id;

    private Integer sceneId;

    private String ip;

    private String reason;

    private Byte isDispose; //已处理

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Byte getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(Byte isDispose) {
        this.isDispose = isDispose;
    }
}