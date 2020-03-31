package com.seifi.jobclube.core.models;

import com.seifi.jobclube.common.models.edo.AdvertiseEdo;
import com.seifi.jobclube.common.models.edo.PostEdo;
import com.seifi.jobclube.common.models.edo.ReviewEdo;
import com.seifi.jobclube.core.entitys.AdvertiseEntity;
import com.seifi.jobclube.core.entitys.PostEntity;
import com.seifi.jobclube.core.entitys.ReviewEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityEdoMapper {

    public static AdvertiseEdo toEdo(AdvertiseEntity model) throws InvocationTargetException, IllegalAccessException {
        AdvertiseEdo edo = new AdvertiseEdo();

        BeanUtils.copyProperties(edo , model);

        return edo;
    }

    public static ReviewEdo toEdo(ReviewEntity model) throws InvocationTargetException, IllegalAccessException {
        ReviewEdo edo = new ReviewEdo();

        BeanUtils.copyProperties(edo , model);

        return edo;
    }

    public static PostEdo toEdo(PostEntity model) throws InvocationTargetException, IllegalAccessException {
        PostEdo edo = new PostEdo();

        BeanUtils.copyProperties(edo , model);

        return edo;
    }

    public static Optional<AdvertiseEdo> toAdvertiseEdoOptional(Optional<AdvertiseEntity> model) throws InvocationTargetException, IllegalAccessException {
        if(model.isPresent() == false){
            return Optional.empty();
        }

        return Optional.of(toEdo(model.get()));
    }

    public static Optional<ReviewEdo> toReviewEdoOptional(Optional<ReviewEntity> model) throws InvocationTargetException, IllegalAccessException {
        if(model.isPresent() == false){
            return Optional.empty();
        }

        return Optional.of(toEdo(model.get()));
    }

    public static Optional<PostEdo> toPostEdoOptional(Optional<PostEntity> model) throws InvocationTargetException, IllegalAccessException {
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

    public static ReviewEntity fromEdo(ReviewEdo edo) throws InvocationTargetException, IllegalAccessException {
        ReviewEntity model = new ReviewEntity();

        BeanUtils.copyProperties(model , edo);

        return model;
    }

    public static PostEntity fromEdo(PostEdo edo) throws InvocationTargetException, IllegalAccessException {
        PostEntity model = new PostEntity();

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

    public static List<ReviewEdo> toReviewEdoList(List<ReviewEntity> modelsList) throws InvocationTargetException, IllegalAccessException {
        List<ReviewEdo> edoList = new ArrayList<>();

        for(ReviewEntity model : modelsList){
            edoList.add(toEdo(model));
        }

        return edoList;
    }

    public static List<PostEdo> toPostEdoList(List<PostEntity> modelsList) throws InvocationTargetException, IllegalAccessException {
        List<PostEdo> edoList = new ArrayList<>();

        for(PostEntity model : modelsList){
            edoList.add(toEdo(model));
        }

        return edoList;
    }
}
