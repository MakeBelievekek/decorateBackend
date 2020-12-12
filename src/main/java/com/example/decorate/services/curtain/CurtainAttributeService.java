package com.example.decorate.services.curtain;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.CurtainAttribute;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.repositorys.curtain.CurtainAttributeRepository;
import com.example.decorate.services.AttributeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class CurtainAttributeService {
    private final CurtainAttributeRepository curtainAttributeRepository;
    private final AttributeService attributeService;

    public void saveCurtainAttributes(Curtain curtain,
                                      List<AttributeCreationFormData> attributeListItemData,
                                      KeyHolder keyHolder) {
        for (AttributeCreationFormData curtainAttribute : attributeListItemData) {
            Attribute persistentAttribute = attributeService.saveAttribute(curtainAttribute);
            CurtainAttribute attribute = CurtainAttribute.builder()
                    .attribute(persistentAttribute)
                    .curtain(curtain)
                    .key(keyHolder)
                    .build();
            curtainAttributeRepository.save(attribute);
        }
    }
}
