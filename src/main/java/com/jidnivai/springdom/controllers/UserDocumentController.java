package com.jidnivai.springdom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.springdom.entity.UserDocument;
import com.jidnivai.springdom.service.UserDocumentService;

@RestController
@RequestMapping("/userDocument")
public class UserDocumentController {
    @Autowired
    UserDocumentService userDocumentService;

    @GetMapping("/test")
    public List<UserDocument> getMethodName() {
        try {
            UserDocument userDocument = new UserDocument();
            // userDocument.setId("1");
            userDocument.setUsername("Jidnivai");
            userDocument.setEmail("Vt5bY@example.com");
            userDocument.setAge(20);
             userDocumentService.createUserDocument(userDocument).toString();
             return userDocumentService.getAllUserDocuments();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/getAll")
    public List<UserDocument> getAllUserDocuments() {
        return userDocumentService.getAllUserDocuments();
    }

    @GetMapping("/getById/{id}")
    public UserDocument getUserDocument(@PathVariable String id) {
        return userDocumentService.getUserDocument(id);
    }

    @PostMapping("/create")
    public UserDocument createUserDocument(@RequestBody UserDocument userDocument) {
        return userDocumentService.createUserDocument(userDocument);
    }

    @PutMapping("/update/{id}")
    public UserDocument updateUserDocument(@PathVariable String id, @RequestBody UserDocument userDocument) {
        return userDocumentService.updateUserDocument(id, userDocument);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserDocument(@PathVariable String id) {
        userDocumentService.deleteUserDocument(id);
    }

}
