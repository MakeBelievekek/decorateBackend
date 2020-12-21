package com.example.decorate.mapper;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.dto.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

    ImageModel imageModelFromImage(Image image);
}
