package com.example.decorate.services.curtain;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.CurtainAttribute;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.repositorys.curtain.CurtainAttributeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class CurtainAttributeService {
    private final CurtainAttributeRepository curtainAttributeRepository;

    public void saveCurtainAttributes(Curtain curtain, List<Attribute> attributes) {
        KeyHolder keyHolder = curtain.getKey();
        for (Attribute attribute : attributes) {
            curtainAttributeRepository.save(new CurtainAttribute(attribute, curtain, keyHolder));
        }
    }

    public void updateCurtainAttributes(Curtain curtain, List<Attribute> attributes) {
        List<Long> activeCurtainAttributeIdList = new ArrayList<>();
        Long curtainId = curtain.getId();

        for (Attribute attribute : attributes) {
            KeyHolder curtainKey = curtain.getKey();
            Long attributeId = attribute.getId();

            Optional<CurtainAttribute> curtainAttribute = curtainAttributeRepository
                    .fetchByAttributeIdAndCurtainId(attributeId, curtainId);

            CurtainAttribute persistentCurtainAttribute = curtainAttribute
                    .orElseGet(this::createNewPersistentCurtainAttribute);

            updateCurtainAttribute(curtain, attribute, curtainKey, persistentCurtainAttribute);

            Long activeCurtainAttributeId = persistentCurtainAttribute.getId();
            activeCurtainAttributeIdList.add(activeCurtainAttributeId);
        }
        curtainAttributeRepository.deleteCurtainNotUsedAttributes(activeCurtainAttributeIdList, curtainId);
    }

    public List<CurtainAttribute> findAllCurtainAttributeByCurtainId(Long curtainId) {
        return curtainAttributeRepository.fetchAllByCurtainId(curtainId);
    }

    public List<CurtainAttribute> findCurtainAttributeByAttributes(List<Attribute> attributes, Long curtainId) {
        Long numberOfConditions = (long) attributes.size();
        return curtainAttributeRepository.findCurtainAttributeByAttributesAndCurtain(attributes,
                numberOfConditions,
                curtainId);
    }

    public void deleteAllByCurtainId(Long curtainId) {
        curtainAttributeRepository.deleteAllByCurtainId(curtainId);
    }

    private void updateCurtainAttribute(Curtain curtain, Attribute attribute, KeyHolder key, CurtainAttribute persistentCurtainAttribute) {
        persistentCurtainAttribute.setAttribute(attribute);
        persistentCurtainAttribute.setCurtain(curtain);
        persistentCurtainAttribute.setKey(key);
        persistentCurtainAttribute.setModified(Instant.now());
    }

    private CurtainAttribute createNewPersistentCurtainAttribute() {
        CurtainAttribute curtainAttr = new CurtainAttribute();
        curtainAttributeRepository.save(curtainAttr);
        return curtainAttr;
    }
}
