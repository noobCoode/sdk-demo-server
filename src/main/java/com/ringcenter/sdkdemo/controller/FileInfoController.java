package com.ringcenter.sdkdemo.controller;


import com.ringcenter.sdkdemo.factory.ResponseEntityFactory;
import com.ringcenter.sdkdemo.model.FileInfo;
import com.ringcenter.sdkdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xudongdong
 * @since 2020-04-30
 */
@RestController
@RequestMapping("/files")
public class FileInfoController {

    private FileService fileService;

    @Autowired
    public FileInfoController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "")
    public ResponseEntity saveFile(@RequestBody FileInfo fileInfo) {
        return ResponseEntityFactory.commonResult(fileService.saveFile(fileInfo));
    }

    @GetMapping(value = "{title}")

    public ResponseEntity getFileDetail(@PathVariable("title") String title) {
        return ResponseEntityFactory.commonResult(fileService.getFileDetail(title));
    }

    @GetMapping(value = "")
    public ResponseEntity getFileSimpleList() {
        return ResponseEntityFactory.commonResult(fileService.getFileSimpleList());
    }

    @PutMapping(value = "{title}")
    public ResponseEntity updateFile(@PathVariable("title") String title, @RequestBody FileInfo fileInfo) {
        return ResponseEntityFactory.commonResult(fileService.updateFile(title, fileInfo));
    }

    @GetMapping(value = "download/{title}")
    public void downloadFile(@PathVariable("title") String title, HttpServletResponse response) throws IOException {
        fileService.downloadFileByTitle(title, response);
    }

}
