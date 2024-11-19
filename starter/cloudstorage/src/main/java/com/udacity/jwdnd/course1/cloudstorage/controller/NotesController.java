package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotesController {

    private NotesService notesService;
    private UserService userService;

    public NotesController(NotesService notesService, UserService userService) {
        this.notesService = notesService;
        this.userService = userService;
    }

    @PostMapping("/notes/addNote")
    public String addNote(Authentication authentication, Notes notes, Model model) {
        Users user = this.userService.getUser(authentication.getName());
        Integer userid = user.getUserid();
        Integer noteid = notes.getNoteid();
        Notes existingNote = notesService.getNote(noteid);

        if(existingNote==null) {
            this.notesService.addNote(notes, userid);
            model.addAttribute("successMessage", true);
            return "result";
        }else{
            this.notesService.updateNote(notes, userid);
            model.addAttribute("successMessage", true);
            return "result";
        }
    }

    //TODO add error handling
    @GetMapping("/notes/{noteid}/deleteNote")
    public String deleteNote(@PathVariable Integer noteid, Model model) {
        this.notesService.deleteNote(noteid);
        model.addAttribute("successMessage",true);
        return "result";
    }
}
