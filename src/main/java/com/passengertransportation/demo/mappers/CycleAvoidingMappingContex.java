package com.passengertransportation.demo.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

public class CycleAvoidingMappingContex {
    private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType){
        return targetType.cast(knownInstances.get(source));
    }

    @BeforeMapping
    public void storedMappedInstance(Object source, @MappingTarget Object target){
        knownInstances.put(source, target);
    }
}