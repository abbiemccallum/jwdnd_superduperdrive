package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class FilesController {

    private final UserService userService;

    @Autowired
    FilesService storageService;

    public FilesController(UserService userService, FilesService storageService) {
        this.userService = userService;
        this.storageService = storageService;
    }

// TODO fix success / error messages
    @PostMapping("files/uploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication authentication, Model model) {
        Users user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserid();
        try {
            storageService.saveFile(file, userId);

            model.addAttribute("uploadSuccess", true);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("uploadError", true);
            return "redirect:/home";
        }
    }

// TODO fix success / error messages
    @GetMapping("files/{fileid}/deleteFile")
    public String deleteFile(Files file, Authentication authentication, Model model) {
        Users user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserid();
        try {
            storageService.deleteFile(file, userId);
            model.addAttribute("deleteSuccess", true);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("uploadError", true);
            return "redirect:/home";
        }
    }

    @GetMapping(path = "files/viewFile/{fileid}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileid) throws IOException {
        Files file = storageService.getFilebyFileid(fileid);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFilename());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(file.getFiledata());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}