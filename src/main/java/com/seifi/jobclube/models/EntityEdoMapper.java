package com.seifi.jobclube.models;

import com.seifi.jobclube.entitys.AdvertiseEntity;
import com.seifi.jobclube.models.edo.AdvertiseEdo;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityEdoMapper {

    public static AdvertiseEdo toEdo(AdvertiseEntity model) throws InvocationTargetException, IllegalAccessException {
        AdvertiseEdo edo = new AdvertiseEdo();
        /*edo.setCreatedAt(model.getCreatedAt());
        edo.setDescription(model.getDescription());
        edo.setId(model.getId());
        edo.setPhoto(model.getPhoto());
        edo.setStatus(model.getStatus());*/

        BeanUtils.copyProperties(edo , model);

        return edo;
    }

    public static Optional<AdvertiseEdo> toEdoOptional(Optional<AdvertiseEntity> model) throws InvocationTargetException, IllegalAccessException {
        if(model.isPresent() == false){
            return Optional.empty();
        }

        return Optional.of(toEdo(model.get()));
    }




    public static AdvertiseEntity fromEdo(AdvertiseEdo edo) throws InvocationTargetException, IllegalAccessException {
        AdvertiseEntity model = new AdvertiseEntity();

        BeanUtils.copyProperties(model , edo);

        return model;
    }


    public static List<AdvertiseEdo> toAdvertiseEdoList(List<AdvertiseEntity> modelsList) throws InvocationTargetException, IllegalAccessException {
        List<AdvertiseEdo> edoList = new ArrayList<>();

        for(AdvertiseEntity model : modelsList){
            edoList.add(toEdo(model));
        }

        return edoList;
    }

}
