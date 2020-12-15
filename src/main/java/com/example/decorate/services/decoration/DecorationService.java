package com.example.decorate.services.decoration;

import com.example.decorate.domain.Decoration;
import com.example.decorate.domain.ProductKey;
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

import static com.example.decorate.domain.ProductType.DECORATION;
import static com.example.decorate.exception.ExceptionMessages.DECORATION_NOT_EXISTS;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class DecorationService {
    private final ProductKeyService productKeyService;
    private final DecorationRepository decorationRepository;
    private final AttributeService attributeService;
    private final ImageService imageService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;

    public void saveDecoration(ProductCreationFormData productCreationFormData) {
        ProductKey decorationProductKey = new ProductKey();
        productKeyService.saveKey(decorationProductKey, DECORATION);

        Decoration decoration = new Decoration(productCreationFormData, decorationProductKey);
        decorationRepository.save(decoration);

        List<AttributeCreationFormData> decorationAttributes = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createProductAttributeItems(decorationAttributes, decorationProductKey);
        imageService.saveImageList(productCreationFormData.getImageList(), decorationProductKey);
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
        ProductKey decorationProductKey = decoration.getProductKey();

        entityUpdateService.setDecorationUpdatedValues(decoration, decorationModel);
        attributeService.updateProductAttributes(decorationProductKey, decorationModel.getAttributes());
        imageService.updateProductImages(decorationProductKey, decorationModel.getImageList());

        decorationRepository.save(decoration);
    }

    public void deleteDecoration(Long decorationId) {
        Decoration decoration = getDecorationById(decorationId);
        ProductKey decorationProductKey = decoration.getProductKey();

        imageService.deleteProductImages(decorationProductKey);
        attributeService.deleteProductAttributeItems(decorationProductKey);
        productKeyService.deleteKeyHolder(decorationProductKey);

        decorationRepository.delete(decoration);
    }

    private Decoration getDecorationById(Long decorationId) {
        return decorationRepository.findById(decorationId)
                .orElseThrow(() -> new DecorateBackendException(DECORATION_NOT_EXISTS.getMessage()));
    }
}
