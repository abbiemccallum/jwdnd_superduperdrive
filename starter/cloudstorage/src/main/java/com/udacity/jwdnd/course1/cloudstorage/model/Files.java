package com.udacity.jwdnd.course1.cloudstorage.model;

public class Files {

private Integer fileid;
private String filename;
private String contenttype;
private Long filesize;
private Integer userid;
private byte[] filedata;

    public Files(Integer fileid, String filename, String contenttype, Long filesize, Integer userid, byte[] filedata) {
        this.fileid = fileid;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Files() {

    }

    public Integer getFileid() {
        return fileid;
    }
    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getContenttype() {
        return contenttype;
    }
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
    public Long getFilesize() {
        return filesize;
    }
    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public byte[] getFiledata() {
        return filedata;
    }
    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
