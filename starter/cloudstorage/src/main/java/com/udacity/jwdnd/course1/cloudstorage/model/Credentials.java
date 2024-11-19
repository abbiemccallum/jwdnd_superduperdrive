package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {

public Integer credentialid;
public String url;
public String username;
public String key;
public String password;
public Integer userid;

    public Credentials(Integer credentialid, String url, String username, String key, String password, Integer userid) {
        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }

    public Integer getCredentialid() {
        return credentialid;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getUrl() {
        return url;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

