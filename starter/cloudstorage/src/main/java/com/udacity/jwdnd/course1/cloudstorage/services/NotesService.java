package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public void addNote(Notes notes, Integer userid) {
        Notes newNote = new Notes(null, notes.getNotetitle(), notes.getNotedescription(), userid);
        notesMapper.addNote(newNote);
    }

    public List<Notes> getNotes(Integer userid) {
        return notesMapper.getAllNotes();
    }

    public void deleteNote(Integer noteid){
        notesMapper.delete(noteid);
    }

    public Notes getNote(Integer noteid){
        return notesMapper.getNote(noteid);
    }

    public void updateNote(Notes notes, Integer userid){
        Notes updatedNote = new Notes(notes.getNoteid(), notes.getNotetitle(), notes.getNotedescription(), notes.getUserid());
        notesMapper.updateNote(updatedNote);
    }
}
