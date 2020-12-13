package com.example.decorate.controller;

import com.example.decorate.services.CsvResolver;
import com.example.decorate.services.CsvService;
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
    private final CsvService csvService;

    @PostMapping()
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        String message = "";
        if (CsvResolver.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println(message);
                return ResponseEntity.status(EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(BAD_REQUEST).body(message);
    }

    @GetMapping()
    public ResponseEntity<String> HelloFromFile() {
        return ResponseEntity.status(OK).body("hello from new file controller");
    }
}
