package com.kpk.hellowStreams.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDownLoadServiceUtil {
    private Path foundFile;

    public Resource getFileResource(String fileCode) throws IOException {
        Path fileUploadDirectory = Paths.get("filesave");

        Files.list(fileUploadDirectory).forEach(file->{
            if(file.getFileName().toString().startsWith(fileCode)){
                foundFile = file;
                return;
            }
        });
        if(foundFile!=null){
            return new UrlResource(foundFile.toUri());
        }
        return null;
    }

}
