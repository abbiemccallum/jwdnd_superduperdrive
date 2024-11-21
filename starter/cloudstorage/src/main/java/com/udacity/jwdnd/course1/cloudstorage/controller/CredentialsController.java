package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CredentialsController {
    
    private CredentialsService credentialsService;
    private UserService userService;


    public CredentialsController(CredentialsService credentialsService, UserService userService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping("/credentials/addCredentials")
    public String addCredential(Authentication authentication, Credentials credentials, Model model) {
        Users user = this.userService.getUser(authentication.getName());
        Integer userid = user.getUserid();
        Integer credentialid = credentials.getCredentialid();
//        Credentials existingCredential = credentialsService.getCredentials(credentialid);

        if(credentialid==null) {
            this.credentialsService.addCredentials(credentials, userid);
            model.addAttribute("successMessage", true);
            return "result";
        }else{
            this.credentialsService.updateCredential(credentials, userid);
            model.addAttribute("successMessage", true);
            return "result";
        }
    }



    @GetMapping("/credentials/{credentialid}/deleteCredential")
    public String deleteCredential(@PathVariable Integer credentialid, Model model) {
        this.credentialsService.deleteCredentials(credentialid);
        model.addAttribute("successMessage",true);
        return "result";
    }
}
