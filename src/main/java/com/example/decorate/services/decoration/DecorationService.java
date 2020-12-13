package com.example.decorate.services.decoration;

import com.example.decorate.domain.Decoration;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.DecorationModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.decoration.DecorationRepository;
import com.example.decorate.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.decorate.domain.ProductType.CURTAIN;
import static com.example.decorate.domain.ProductType.DECORATION;
import static com.example.decorate.exception.ExceptionMessages.DECORATION_NOT_EXISTS;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class DecorationService {
    private final KeyHolderService keyHolderService;
    private final DecorationRepository decorationRepository;
    private final AttributeService attributeService;
    private final ImageService imageService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;

    public void saveDecoration(ProductCreationFormData productCreationFormData) {
        KeyHolder keyHolder = new KeyHolder();
        keyHolderService.saveKey(keyHolder, DECORATION);

        Decoration decoration = new Decoration(productCreationFormData, keyHolder);
        decorationRepository.save(decoration);

        List<AttributeCreationFormData> decorationAttributes = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createDecorationAttributes(decoration, decorationAttributes);
        imageService.saveImageList(productCreationFormData.getImageList(), keyHolder.getId(), DECORATION);
    }

    public DecorationModel getDecoration(Long decorationId) {
        Decoration decoration = getDecorationById(decorationId);

        return modelCreatorService.createDecorationModel(decoration);
    }

    public List<DecorationModel> getAllDecorations() {
        List<Decoration> allDecorations = decorationRepository.getAllDecorations();
        List<DecorationModel> allDecorationModels = new ArrayList<>();

        for (Decoration decoration : allDecorations) {
            allDecorationModels.add(modelCreatorService.createDecorationModel(decoration));
        }
        return allDecorationModels;
    }

    public void updateDecoration(Long decorationId, DecorationModel decorationModel) {
        Decoration decoration = getDecorationById(decorationId);

        entityUpdateService.setDecorationUpdatedValues(decoration, decorationModel);
        attributeService.updateDecorationAttributes(decoration, decorationModel.getAttributes());
        imageService.updateProductImages(decorationId, decorationModel.getImageList(), CURTAIN);

        decorationRepository.save(decoration);
    }

    public void deleteDecoration(Long decorationId) {
        Decoration decoration = getDecorationById(decorationId);

        imageService.deleteProductImages(decorationId);
        attributeService.deleteProductAttributeItems(decoration);
        keyHolderService.deleteKeyHolder(decorationId);

        decorationRepository.delete(decoration);
    }

    private Decoration getDecorationById(Long decorationId) {
        return decorationRepository.findById(decorationId)
                .orElseThrow(() -> new DecorateBackendException(DECORATION_NOT_EXISTS.getMessage()));
    }
}
