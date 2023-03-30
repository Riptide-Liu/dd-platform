package com.riptide.ddplatform.service;

import com.riptide.ddplatform.domin.APIResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    APIResult upload(MultipartFile file);
    APIResult download(String file_name);
}
