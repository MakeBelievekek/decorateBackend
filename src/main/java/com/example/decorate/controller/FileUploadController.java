package com.example.decorate.controller;

import com.example.decorate.domain.ResponseMessage;
import com.example.decorate.service.ExcelHelper;
import com.example.decorate.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/public/fileupload")
public class FileUploadController {
    private ExcelService excelService;

    @Autowired
    public FileUploadController(ExcelService excelService) {

        this.excelService = excelService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping()
    public ResponseEntity<String> HelloFromFile() {
        return ResponseEntity.status(OK).body("hello from new file controller");
    }
}
