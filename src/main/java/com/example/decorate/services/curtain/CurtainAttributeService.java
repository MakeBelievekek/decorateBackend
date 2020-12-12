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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class CurtainAttributeService {
    private final CurtainAttributeRepository curtainAttributeRepository;

    public void saveCurtainAttributes(Curtain curtain,
                                      List<Attribute> attributes) {
        KeyHolder keyHolder = curtain.getKey();
        for (Attribute attribute : attributes) {
            curtainAttributeRepository.save(new CurtainAttribute(attribute, curtain, keyHolder));
        }
    }

    public void updateCurtainAttributes(Curtain curtain, List<Attribute> attributes) {
        List<Long> activeCurtainAttributeIdList = new ArrayList<>();
        Long curtainId = curtain.getId();
        for (Attribute attribute : attributes) {
            KeyHolder key = curtain.getKey();
            Long attributeId = attribute.getId();

            Optional<CurtainAttribute> curtainAttribute = curtainAttributeRepository
                    .fetchByAttributeIdAndCurtainId(attributeId, curtainId);

            CurtainAttribute persistentCurtainAttribute = curtainAttribute
                    .orElseGet(() ->
                    {
                        CurtainAttribute curtainAttr = new CurtainAttribute(attribute, curtain, key);
                        return curtainAttributeRepository.save(curtainAttr);
                    });
            activeCurtainAttributeIdList.add(persistentCurtainAttribute.getId());
        }
        log.info("lista elemei:"  + activeCurtainAttributeIdList.toString());
        log.info("curtain id:"  + curtainId);
        curtainAttributeRepository.deleteCurtainNotUsedAttributes(activeCurtainAttributeIdList, curtainId);
    }

    public List<CurtainAttribute> findAllCurtainAttributeByCurtainId(Long curtainId) {
        return curtainAttributeRepository.fetchAllByCurtainId(curtainId);
    }
}
