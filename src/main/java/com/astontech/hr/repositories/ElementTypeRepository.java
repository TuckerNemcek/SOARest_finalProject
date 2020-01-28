package com.astontech.hr.repositories;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementTypeRepository extends CrudRepository<ElementType, Integer> {

    ElementType findByElementTypeName (String elementTypeName);

    List<ElementType> findAllByElementTypeName(String elementTypeName);

    int countByElementTypeName (String elementTypeName);

    ElementType findById (int elementTypeId);

    ElementType getElementTypeById(Integer elementTypeId);
}
