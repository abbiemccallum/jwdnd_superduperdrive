package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/home")
public class HomeController {

    private FilesService filesService;
    private UserService userService;
    private NotesService notesService;
    private CredentialsService credentialsService;
    private EncryptionService encryptionService;
    public HomeController(FilesService fileService, UserService userService, NotesService notesService, CredentialsService credentialsService, EncryptionService encryptionService){
        this.filesService = fileService;
        this.userService = userService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.encryptionService = encryptionService;

    }
    @GetMapping()
    public String homeView(Authentication authentication, Model model) {
        String username = authentication.getName();
        Users user = userService.getUser(username);
//        if (user!=null){
            model.addAttribute("files", this.filesService.getFiles(user.getUserid()));
            model.addAttribute("notes", this.notesService.getNotes(user.getUserid()));
            model.addAttribute("credentials", this.credentialsService.getCredentials(user.getUserid()));
            model.addAttribute("encryptionService", this.encryptionService);

            return "home";

        }


}
