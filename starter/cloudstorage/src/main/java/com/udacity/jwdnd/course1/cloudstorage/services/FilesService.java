package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesService {


    int saveFile(MultipartFile file, Integer userid) throws IOException;


    Object getFiles(Integer userid);

    int deleteFile(Files file, Integer userid);

    Files getFilebyFileid(Integer fileid);

    boolean isFilenameAvailable(String filename);

}