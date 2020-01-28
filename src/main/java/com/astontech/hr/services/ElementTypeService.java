package com.astontech.hr.services;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;

import javax.el.ELManager;
import java.util.List;

public interface ElementTypeService {

    Iterable<ElementType> listAllElementTypes();

    ElementType getElementTypeById(Integer id);

    ElementType saveElementType (ElementType elementType);

    Iterable<ElementType> saveElementTypeList (Iterable<ElementType> elementTypeIterable);

    void deleteElementType(Integer id);

    //custom repo methods

    ElementType findByElementTypeName(String elementTypeName);

    List<ElementType> findAllByElementTypeName(String elementTypeName);

}
