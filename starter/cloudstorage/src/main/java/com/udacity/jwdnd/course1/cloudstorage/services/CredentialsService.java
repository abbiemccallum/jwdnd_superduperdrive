package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }


    public void addCredentials(Credentials credentials, Integer userid) {
        // Encrypt the password with a unique salt
        Map<String, String> encryptedData = encryptionService.encryptPassword(credentials.getPassword());
        String encryptedPassword = encryptedData.get("encryptedPassword");
        String key = encryptedData.get("key");

        credentials.setPassword(encryptedPassword);
        credentials.setKey(key);
        credentialsMapper.addCredential(credentials);
    }

    public List<Credentials> getCredentials(Integer userid) {
        List<Credentials> credentialsList = credentialsMapper.getAllCredentials();

        // Decrypt password when displaying/editing
        for (Credentials credential : credentialsList) {
            String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
            credential.setPassword(decryptedPassword);
        }

        return credentialsList;
    }

    public void deleteCredentials(Integer credentialid) {
        credentialsMapper.delete(credentialid);
    }

    public Credentials getCredentialsbyId(Integer credentialid) {
        return credentialsMapper.getCredential(credentialid);
    }

    public List<Credentials> getCredentialsEncrypted(Integer userid) {
        return credentialsMapper.getAllCredentials();
    }

    public List<Credentials> getCredentialsDecrypted(Integer userid) {
        List<Credentials> credentialsList = credentialsMapper.getAllCredentials();

        // Decrypt password when displaying/editing
        for (Credentials credential : credentialsList) {
            String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
            credential.setPassword(decryptedPassword);
        }

        return credentialsList;
    }
    public Credentials getCredentialbyId(Integer credentialid) {
        Credentials credential = credentialsMapper.getCredential(credentialid);
        if (credential != null) {
            String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
            credential.setPassword(decryptedPassword);
        }
        return credential;
    }

    public void updateCredential(Credentials credential, Integer userid) {
        Map<String, String> encryptedData = encryptionService.encryptPassword(credential.getPassword());
        credential.setPassword(encryptedData.get("encryptedPassword"));
        credential.setKey(encryptedData.get("key"));
        credentialsMapper.updateCredential(credential);
    }
}
