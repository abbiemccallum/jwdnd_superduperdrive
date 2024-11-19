package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }


    public void addCredentials(Credentials credentials, Integer userid) {

        //generate key
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encryptKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encryptKey);
        //set key and encrypted password
        credentials.setKey(encryptKey);
        credentials.setPassword(encryptedPassword);

        Credentials newCredentials = new Credentials(null, credentials.getUrl(), credentials.getUsername(), encryptKey, encryptedPassword, userid);
        credentialsMapper.addCredential(newCredentials);
    }

    public List<Credentials> getCredentials(Integer userid) {
        return credentialsMapper.getAllCredentials();
    }

    public void deleteCredentials(Integer credentialid) {
        credentialsMapper.delete(credentialid);
    }

    public Credentials getCredential(Integer credentialid) {
        return credentialsMapper.getCredential(credentialid);
    }

    public void updateCredential(Credentials credentials, Integer userid) {
        //generate key
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encryptKey = Base64.getEncoder().encodeToString(key);

        Credentials updatedCredential = credentialsMapper.getCredential(credentials.getCredentialid());
        updatedCredential.setKey(encryptKey);
        updatedCredential.setPassword(encryptionService.encryptValue(credentials.getPassword(),encryptKey));
        updatedCredential.setUrl(credentials.getUrl());
        updatedCredential.setUsername(credentials.getUsername());
        credentialsMapper.updateCredential(updatedCredential);
    }

}
