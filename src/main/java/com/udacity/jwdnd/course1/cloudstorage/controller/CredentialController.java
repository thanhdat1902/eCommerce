package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home/credential")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;
    public CredentialController(CredentialService credentialService, UserMapper userMapper, CredentialMapper credentialMapper){
        this.credentialService = credentialService;
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }
    @PostMapping
    public String addCredentialHandler( Credential credential, RedirectAttributes redirectAttributes) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = this.userMapper.getUser(username);
        int userid = user.getUserId();
        if(credential.getCredentialid() != null){
            this.credentialService.updateCredential(credential);
        }else{
            Credential credentialExist = this.credentialMapper.getCredentialByUsername(userid, credential);
            if(credentialExist != null) {
                redirectAttributes.addAttribute("errorMessage", "Credential already existed");
                return "redirect:/result?error";
            }
            this.credentialService.storeCredential(credential, userid);
        }
        return "redirect:/result?success";
        // checkFileUpload

    }



    @GetMapping("/delete")
    public String deleteCredential (@RequestParam("id") int credentialid, Authentication authentication){


        if(credentialid > 0){
            this.credentialService.deleteCredential(credentialid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";

    }
}
