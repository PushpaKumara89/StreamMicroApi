package com.pushpakumaracode.controllers;
import com.pushpakumaracode.FileUploadResponse;
import com.pushpakumaracode.Order;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "api/v1/massages")
public class MassageController {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;


    public MassageController(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/text")
    public ResponseEntity<Object> publish(@RequestBody Order Order){
        /*kafkaTemplate.send("users", Order);*/
        return new ResponseEntity<Object>("The massage Sand Successfully", HttpStatus.OK);
    }


    @PostMapping("/media")
    public ResponseEntity<FileUploadResponse> image(@RequestParam("File") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("\\.");
        String extension = "."+split[split.length - 1];
        String randomCode = RandomStringUtils.randomAlphabetic(8);
        Date time =new Date();
        String t=new SimpleDateFormat("yyyy_MM_dd_hh_mm-ss_ms_").format(time);
        String file_name = t+randomCode+extension;

        FileUploadResponse response = new FileUploadResponse(file_name,file.getSize() ,"http://localhost:8081/medea/downloadFile/"+file_name);

        kafkaTemplate.send("users",file_name,file.getBytes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
