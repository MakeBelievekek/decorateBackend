package com.example.decorate.service;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.repository.CurtainRepository;
import com.example.decorate.repository.KeyHolderRepository;
import com.example.decorate.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExcelService {


    CurtainRepository curtainRepository;
    WallpaperRepository wallpaperRepository;
    KeyHolderRepository keyHolderRepository;
    List<Wallpaper> wallpapers = new ArrayList<>();
    List<Curtain> curtains = new ArrayList<>();

    @Autowired
    public ExcelService(CurtainRepository curtainRepository, WallpaperRepository wallpaperRepository,
                        KeyHolderRepository keyHolderRepository) {
        this.curtainRepository = curtainRepository;
        this.wallpaperRepository = wallpaperRepository;
        this.keyHolderRepository = keyHolderRepository;
    }

    public void save(MultipartFile file) {
        try {
            List<ExcelData> products = ExcelHelper.excelToTutorials(file.getInputStream());
            for (ExcelData product : products) {
                KeyHolder keyHolder = new KeyHolder();
                switch (product.getTypeOfProduct()) {
                    case "WALLPAPER":
                        this.keyHolderRepository.save(keyHolder);
                        Wallpaper wallpaper = new Wallpaper(product, keyHolder);
                        wallpaperRepository.save(wallpaper);
                        break;
                    case "CURTAIN":
                        this.keyHolderRepository.save(keyHolder);
                        Curtain curtain = new Curtain(product, keyHolder);
                        curtainRepository.save(curtain);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
