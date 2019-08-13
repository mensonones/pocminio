package com.br.minio.miniodemo.model;

import java.util.Date;

public class ObjectMessage {

    private String objectName;

    private Integer size;

    private Date lastModified;

    private Boolean isDir;

    private String storageClass;

    private String etag;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getIsDir() {
        return isDir;
    }

    public void setIsDir(Boolean dir) {
        isDir = dir;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
