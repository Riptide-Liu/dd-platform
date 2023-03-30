package com.riptide.ddplatform.controller;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public APIResult upload(MultipartFile file){
        return fileService.upload(file);
    }

    @GetMapping("/download")
    public APIResult getFileUrl(@NotNull @RequestParam(value = "fileName") String fileName) {
       return fileService.download(fileName);
    }

}
