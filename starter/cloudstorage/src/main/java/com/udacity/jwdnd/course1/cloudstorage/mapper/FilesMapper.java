package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

//    The user should be able to upload files and see any files they previously uploaded.
//    The user should be able to view/download or delete previously-uploaded files.
//    Any errors related to file actions should be displayed. For example, a user should not be able to upload two files with the same name, but they'll never know unless you tell them!

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List <Files> getFilebyuserid(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata} )")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insert(Files files);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid} AND userid = #{userid}")
    int delete(Files file);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    Files getFileid(Integer fileid);

}
