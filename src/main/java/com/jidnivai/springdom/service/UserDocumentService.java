package com.jidnivai.springdom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.springdom.entity.UserDocument;
import com.jidnivai.springdom.repository.UserDocumentRepository;

@Service
public class UserDocumentService {
    @Autowired
    UserDocumentRepository userDocumentRepository;

    
    public List<UserDocument> getAllUserDocuments() {
        return userDocumentRepository.findAll();
    }

    public UserDocument getUserDocument(String id) {
        return userDocumentRepository.findById(id).orElse(null);
    }

    public UserDocument createUserDocument(UserDocument userDocument) {
        return userDocumentRepository.save(userDocument);
    }

    public UserDocument updateUserDocument( String id, UserDocument userDocument) {
        userDocument.setId(id);
        return userDocumentRepository.save(userDocument);
    }

    public void deleteUserDocument(String id) {
        userDocumentRepository.deleteById(id);
    }
}
