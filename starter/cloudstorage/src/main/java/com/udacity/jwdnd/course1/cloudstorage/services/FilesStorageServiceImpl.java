package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FilesStorageServiceImpl implements FilesService {


    private FilesMapper filesMapper;

    public FilesStorageServiceImpl(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    @Override
    public int saveFile(MultipartFile multipartFile, Integer userid) throws IOException {
        Files file = new Files();
        file.setFilename(multipartFile.getOriginalFilename());
        file.setContenttype(multipartFile.getContentType());
        file.setFilesize(multipartFile.getSize());
        file.setUserid(userid);
        file.setFiledata(multipartFile.getBytes());
        return filesMapper.insert(file);
    }

    public List<Files> getFiles(Integer userid){
        return filesMapper.getFilebyuserid(userid);
    }

    public int deleteFile(Files file, Integer userid){
        file.setUserid(userid);
        return filesMapper.delete(file);
    }

    public Files getFilebyFileid(Integer fileid){
        return filesMapper.getFileid(fileid);
    }

}