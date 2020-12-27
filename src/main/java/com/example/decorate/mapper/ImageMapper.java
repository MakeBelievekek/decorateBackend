package com.example.decorate.mapper;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.dto.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

    ImageModel imageModelFromImage(Image image);

    List<ImageModel> imagesToImageModelList(List<Image> images);
}
