package com.jidnivai.springdom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jidnivai.springdom.entity.UserDocument;


public interface UserDocumentRepository extends MongoRepository<UserDocument, String> {
    
}
