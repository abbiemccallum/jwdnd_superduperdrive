package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES")
    List<Notes> getAllNotes();

    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) VALUES(#{noteid}, #{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Notes notes);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void delete(Integer noteid);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Notes getNote(Integer noteid);

    @Update("UPDATE NOTES SET noteTitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int updateNote(Notes notes);

}
