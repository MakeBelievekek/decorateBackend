package com.example.decorate.controller;

import com.example.decorate.domain.ResponseMessage;
import com.example.decorate.services.CsvResolver;
import com.example.decorate.services.CsvService;
import com.example.decorate.services.ExcelHelper;
import com.example.decorate.services.ExcelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/public/fileupload")
public class FileUploadController {
    private final ExcelService excelService;
    private final CsvService csvService;

    @PostMapping()
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIITTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        System.out.println(file.getContentType());
        String message = "";
        if (CsvResolver.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println(message);
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
