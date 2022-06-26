package com.kpk.hellowStreams.controller;

import com.kpk.hellowStreams.service.FileDownLoadServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/medea")
@CrossOrigin
@Log4j2
public class FileDownloadController {

    private final FileDownLoadServiceUtil downLoadService;

    public FileDownloadController(FileDownLoadServiceUtil downLoadService) {
        this.downLoadService = downLoadService;
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource;
        String contentType;
        try {
            resource = downLoadService.getFileResource(fileName);
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            /*if (resource==null){
                System.out.println("ok");
                return new ResponseEntity<>("File Not found..", HttpStatus.NOT_FOUND);
            }*/
            log.info("D o w n l o a d  S o m e  F i l e  N a m e  I s "+fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }catch (NullPointerException e){
            log.error(fileName+"  W h i c h  F i l e  I s  N o t  F o u n d . . . ");
            return new ResponseEntity<>("File Not found..", HttpStatus.NOT_FOUND);
        }
    }
}
