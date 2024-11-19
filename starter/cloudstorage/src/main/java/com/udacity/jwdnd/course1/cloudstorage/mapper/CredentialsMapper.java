package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credentials> getAllCredentials();

    @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) VALUES(#{credentialid}, #{url}, #{username}, #{key}, #{password}, #{userid} )")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addCredential(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    void delete(Integer credentialid);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credentials getCredential(Integer credentialid);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}  WHERE credentialid = #{credentialid}")
    int updateCredential(Credentials credentials);
}
